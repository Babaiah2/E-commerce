package com.example.EcommerceApplication.Controller;

import com.example.EcommerceApplication.Dto.Request.SellerRequest;
import com.example.EcommerceApplication.Dto.Response.SellerResponse;
import com.example.EcommerceApplication.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequest sellerRequest){
        try{
            SellerResponse sellerResponse = sellerService.addSeller(sellerRequest);
            return new ResponseEntity(sellerResponse, HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get_seller_by-emailid")
    public ResponseEntity getSellerByEmailId(@RequestParam("email") String emailId){
        try{
            SellerResponse sellerResponse = sellerService.getSellerByEmailId(emailId);

            return new ResponseEntity(sellerResponse,HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
