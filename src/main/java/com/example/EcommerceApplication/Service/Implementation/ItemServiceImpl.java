package com.example.EcommerceApplication.Service.Implementation;

import com.example.EcommerceApplication.Convertor.ItemConvertor;
import com.example.EcommerceApplication.Dto.Request.CustomerRequest;
import com.example.EcommerceApplication.Dto.Request.ItemRequest;
import com.example.EcommerceApplication.Entity.Customer;
import com.example.EcommerceApplication.Entity.Item;
import com.example.EcommerceApplication.Entity.Product;
import com.example.EcommerceApplication.Enums.ProductStatus;
import com.example.EcommerceApplication.Exception.CustomerNotFoundException;
import com.example.EcommerceApplication.Exception.ProductNotFoundException;
import com.example.EcommerceApplication.Repository.CustomerRepository;
import com.example.EcommerceApplication.Repository.ItemRepository;
import com.example.EcommerceApplication.Repository.ProductRepository;
import com.example.EcommerceApplication.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ItemRepository itemRepository;


    @Override
    public Item addItem(ItemRequest itemRequest) throws Exception {

        Customer customer;

        try{
            customer = customerRepository.findById(itemRequest.getCustomerId()).get();
        }
        catch(Exception e){
            throw new CustomerNotFoundException("Invalid Customer Id");
        }

        Product product;
        try{
            product = productRepository.findById(itemRequest.getProductId()).get();
        }
        catch(Exception e){
            throw new ProductNotFoundException("Invalid ProductId");
        }

        if(itemRequest.getRequiredQuantity()> product.getQuantity()||product.getProductStatus()!= ProductStatus.AVAILABLE){
            throw new Exception("required quantity is "+itemRequest.getRequiredQuantity()+ "but available quantity is "+product.getQuantity());
        }

        Item item = ItemConvertor.itemRequestToItem(itemRequest);

        item.setProduct(product);

        product.getItemList().add(item);

        return itemRepository.save(item);
    }
}
