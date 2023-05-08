package com.example.EcommerceApplication.Controller;

import com.example.EcommerceApplication.Dto.Request.CheckoutRequest;
import com.example.EcommerceApplication.Dto.Request.ItemRequest;
import com.example.EcommerceApplication.Dto.Response.CartResponse;
import com.example.EcommerceApplication.Dto.Response.OrderResponse;
import com.example.EcommerceApplication.Entity.Item;
import com.example.EcommerceApplication.Service.CartService;
import com.example.EcommerceApplication.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    ItemService itemService;

    @PostMapping("/add")
    public ResponseEntity addCart(@RequestBody ItemRequest itemRequest) throws Exception {

        try{
            Item savedItem = itemService.addItem(itemRequest);
            CartResponse cartResponse = cartService.addToCart(itemRequest.getCustomerId(),savedItem);
             return new ResponseEntity(cartResponse, HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }


    }
    @PostMapping("/checkout")
    public ResponseEntity checkOutCart(@RequestBody CheckoutRequest checkoutRequest) throws Exception {

        try{
            OrderResponse orderResponse = cartService.checkoutCart(checkoutRequest);
            return new ResponseEntity(orderResponse,HttpStatus.CREATED);
        }
        catch(Exception e){
            return  new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
