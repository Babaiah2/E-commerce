package com.example.EcommerceApplication.Service.Implementation;

import com.example.EcommerceApplication.Convertor.CartConvertor;
import com.example.EcommerceApplication.Convertor.ItemConvertor;
import com.example.EcommerceApplication.Dto.Request.CheckoutRequest;
import com.example.EcommerceApplication.Dto.Response.CartResponse;
import com.example.EcommerceApplication.Dto.Response.ItemResponse;
import com.example.EcommerceApplication.Dto.Response.OrderResponse;
import com.example.EcommerceApplication.Entity.*;
import com.example.EcommerceApplication.Exception.CustomerNotFoundException;
import com.example.EcommerceApplication.Exception.InvalidCardException;
import com.example.EcommerceApplication.Exception.ItemsNotFoundInCart;
import com.example.EcommerceApplication.Repository.CardRepository;
import com.example.EcommerceApplication.Repository.CartRepository;
import com.example.EcommerceApplication.Repository.CustomerRepository;
import com.example.EcommerceApplication.Repository.OrderedRepository;
import com.example.EcommerceApplication.Service.CartService;
import com.example.EcommerceApplication.Service.OrderedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    OrderedService orderedService;

    @Autowired
    OrderedRepository orderedRepository;

    @Override
    public CartResponse addToCart(int customerId, Item item) {

        Customer customer = customerRepository.findById(customerId).get();

        Cart cart = customer.getCart();

        int newTotal = cart.getCartTotal()+item.getRequiredQuantity()*item.getProduct().getPrice();
        cart.setCartTotal(newTotal);
        cart.getItems().add(item);
        cart.setNumberOfItems(cart.getItems().size());
        item.setCart(cart);

        Cart savedCart = cartRepository.save(cart);

        CartResponse cartResponse = CartConvertor.CartToCartResponse(savedCart);

        cartResponse.setCustomerName(customer.getName());

        List<ItemResponse> itemResponseList = new ArrayList<>();

        for(Item itemEntity: savedCart.getItems()){
            ItemResponse itemResponse = ItemConvertor.itemToItemResponse(itemEntity);

            itemResponseList.add(itemResponse);
        }

        cartResponse.setItemResponses(itemResponseList);

        return cartResponse;
    }

    @Override
    public OrderResponse checkoutCart(CheckoutRequest checkoutRequest) throws Exception {
        Customer customer;

        try{
            customer = customerRepository.findById(checkoutRequest.getCustomerId()).get();
        }
        catch(Exception e){
            throw new CustomerNotFoundException("Invalid customerID");
        }

        Card card = cardRepository.findByCardNo(checkoutRequest.getCardNo());

        if(card==null || checkoutRequest.getCvv()!= card.getCvv() || card.getCustomer() != customer){
            throw new InvalidCardException("cardDetails are invalid!!");
        }

        Cart cart = customer.getCart();

        if(cart.getNumberOfItems()==0){
            throw new ItemsNotFoundInCart("Cart is Empty");
        }

        try{
            Ordered order = new Ordered();
            order =orderedService.placeOrder(customer,card);
            customer.getOrderList().add(order);
            resetCart(cart);

            Ordered savedOrder = orderedRepository.save(order);

            //OrderResponse

            OrderResponse orderResponse = new OrderResponse();

            orderResponse.setOrderDate(savedOrder.getOrderDate());
            orderResponse.setCardUsed(savedOrder.getCardUsed());
            orderResponse.setCustomerName(customer.getName());
            orderResponse.setOrderNo(savedOrder.getOrderNo());
            orderResponse.setTotalValue(savedOrder.getTotalValue());

            List<ItemResponse> items = new ArrayList<>();

            for(Item itemEntity: savedOrder.getItems()){
                ItemResponse itemResponse = ItemConvertor.itemToItemResponse(itemEntity);
                items.add(itemResponse);
            }
            orderResponse.setItems(items);

            return orderResponse;
        }
        catch(Exception e){
            throw new Exception(e.getMessage());
        }






    }
    public void resetCart(Cart cart){

        cart.setCartTotal(0);
        for(Item item: cart.getItems()){
            item.setCart(null);
        }
        cart.setNumberOfItems(0);
        cart.getItems().clear();

    }
}
