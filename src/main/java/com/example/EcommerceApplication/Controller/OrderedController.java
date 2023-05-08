package com.example.EcommerceApplication.Controller;

import com.example.EcommerceApplication.Dto.Request.OrderRequest;
import com.example.EcommerceApplication.Dto.Response.OrderResponse;
import com.example.EcommerceApplication.Service.OrderedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ordered")
public class OrderedController {

    @Autowired
    OrderedService orderedService;

    @PostMapping("/direct_place")
    public ResponseEntity directOrder(@RequestBody OrderRequest orderRequest){
        try{
            OrderResponse orderResponse = orderedService.placeOrder(orderRequest);
            return new ResponseEntity(orderResponse, HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
