package com.example.EcommerceApplication.Service;

import com.example.EcommerceApplication.Dto.Request.CheckoutRequest;
import com.example.EcommerceApplication.Dto.Response.CartResponse;
import com.example.EcommerceApplication.Dto.Response.OrderResponse;
import com.example.EcommerceApplication.Entity.Item;
import com.example.EcommerceApplication.Exception.CustomerNotFoundException;
import com.example.EcommerceApplication.Exception.InvalidCardException;
import com.example.EcommerceApplication.Exception.ItemsNotFoundInCart;

public interface CartService {
    CartResponse addToCart(int customerId, Item savedItem);

    OrderResponse checkoutCart(CheckoutRequest checkoutRequest) throws Exception;
}
