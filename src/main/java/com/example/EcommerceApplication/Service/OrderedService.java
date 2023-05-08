package com.example.EcommerceApplication.Service;

import com.example.EcommerceApplication.Dto.Request.OrderRequest;
import com.example.EcommerceApplication.Dto.Response.OrderResponse;
import com.example.EcommerceApplication.Entity.Card;
import com.example.EcommerceApplication.Entity.Customer;
import com.example.EcommerceApplication.Entity.Ordered;
import com.example.EcommerceApplication.Exception.CustomerNotFoundException;
import com.example.EcommerceApplication.Exception.InvalidCardException;
import com.example.EcommerceApplication.Exception.LessQuantityOfItemsException;
import com.example.EcommerceApplication.Exception.ProductNotFoundException;
import jakarta.persistence.criteria.Order;

public interface OrderedService {

    Ordered placeOrder(Customer customer, Card card) throws Exception;

    OrderResponse placeOrder(OrderRequest orderRequest) throws CustomerNotFoundException, LessQuantityOfItemsException, InvalidCardException, ProductNotFoundException;
}
