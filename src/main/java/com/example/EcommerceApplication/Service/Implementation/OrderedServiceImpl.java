package com.example.EcommerceApplication.Service.Implementation;

import com.example.EcommerceApplication.Dto.Request.OrderRequest;
import com.example.EcommerceApplication.Dto.Response.ItemResponse;
import com.example.EcommerceApplication.Dto.Response.OrderResponse;
import com.example.EcommerceApplication.Entity.*;
import com.example.EcommerceApplication.Exception.CustomerNotFoundException;
import com.example.EcommerceApplication.Exception.InvalidCardException;
import com.example.EcommerceApplication.Exception.LessQuantityOfItemsException;
import com.example.EcommerceApplication.Exception.ProductNotFoundException;
import com.example.EcommerceApplication.Repository.CardRepository;
import com.example.EcommerceApplication.Repository.CustomerRepository;
import com.example.EcommerceApplication.Repository.OrderedRepository;
import com.example.EcommerceApplication.Repository.ProductRepository;
import com.example.EcommerceApplication.Service.OrderedService;
import com.example.EcommerceApplication.Service.ProductService;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderedServiceImpl implements OrderedService {
    @Autowired
    ProductService productService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CardRepository cardRepository;
    @Autowired
    OrderedRepository orderedRepository;


    @Override
    public Ordered placeOrder(Customer customer, Card card) throws Exception {

        Cart cart = customer.getCart();

        Ordered order = new Ordered();

        order.setOrderNo(String.valueOf(UUID.randomUUID()));

        String maskedCardNo = generateMaskedCard(card.getCardNo());

        order.setCardUsed(maskedCardNo);
        order.setCustomer(customer);

        List<Item> orderedItems = new ArrayList<>();
        for(Item item: cart.getItems()){
            try{
                productService.reduceProductQuantity(item);
                orderedItems.add(item);
            }
            catch(Exception e){
                throw new Exception("out of stock");
            }
        }
        order.setItems(orderedItems);

        for(Item item:orderedItems){
            item.setOrder(order);
        }
        order.setTotalValue(cart.getCartTotal());

        return order;
    }

    @Override
    public OrderResponse placeOrder(OrderRequest orderRequest) throws CustomerNotFoundException, LessQuantityOfItemsException, InvalidCardException, ProductNotFoundException {
        Customer customer;
        try{
            customer = customerRepository.findById(orderRequest.getCustomerId()).get();
        }
        catch (Exception e){
            throw new CustomerNotFoundException("Customer Id is invalid !!");
        }

        Product product;
        try{
            product = productRepository.findById(orderRequest.getProductId()).get();
        }
        catch(Exception e){
            throw new ProductNotFoundException("Product doesn't exist");
        }

        Card card = cardRepository.findByCardNo(orderRequest.getCardNo());
        if(card==null || card.getCvv()!=orderRequest.getCvv() || card.getCustomer()!=customer){
            throw new InvalidCardException("Your card is not valid!!");
        }

        Item item = Item.builder()
                .requiredQuantity(orderRequest.getRequiredQuantity())
                .product(product)
                .build();
        try{
            productService.reduceProductQuantity(item);
        }
        catch (Exception e){
            throw new LessQuantityOfItemsException("quantity of product is less than the rwquired quantity");
        }

        Ordered order = new Ordered();
        order.setOrderNo(String.valueOf(UUID.randomUUID()));
        String maskedCardNo = generateMaskedCard(card.getCardNo());
        order.setCardUsed(maskedCardNo);
        order.setCustomer(customer);
        order.setTotalValue(item.getRequiredQuantity()*product.getPrice());
        order.getItems().add(item);

        customer.getOrderList().add(order);
        item.setOrder(order);
        product.getItemList().add(item);

        Ordered savedOrder = orderedRepository.save(order); // order and item

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderDate(savedOrder.getOrderDate());
        orderResponse.setCardUsed(savedOrder.getCardUsed());
        orderResponse.setCustomerName(customer.getName());
        orderResponse.setOrderNo(savedOrder.getOrderNo());
        orderResponse.setTotalValue(savedOrder.getTotalValue());

        List<ItemResponse> items = new ArrayList<>();
        for(Item itemEntity: savedOrder.getItems()){
            ItemResponse itemResponse = new ItemResponse();
            itemResponse.setPriceOfOneItem(itemEntity.getProduct().getPrice());
            itemResponse.setTotalPrice(itemEntity.getRequiredQuantity()*itemEntity.getProduct().getPrice());
            itemResponse.setProductName(itemEntity.getProduct().getName());
            itemResponse.setQuantity(itemEntity.getRequiredQuantity());

            items.add(itemResponse);
        }

        orderResponse.setItems(items);
        return orderResponse;
    }

    public String generateMaskedCard(String cardNo){
        String maskedCardNo = "";
        for(int i = 0;i<cardNo.length()-4;i++)
            maskedCardNo += 'X';
        maskedCardNo += cardNo.substring(cardNo.length()-4);
        return maskedCardNo;

    }
}
