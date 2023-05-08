package com.example.EcommerceApplication.Convertor;

import com.example.EcommerceApplication.Dto.Response.CartResponse;
import com.example.EcommerceApplication.Entity.Cart;

public class CartConvertor {

    public static CartResponse CartToCartResponse(Cart cart){

        return CartResponse.builder()
                .cartTotal(cart.getCartTotal())
                .noOfItems(cart.getNumberOfItems())
                .build();
    }
}
