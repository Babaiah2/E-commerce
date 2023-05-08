package com.example.EcommerceApplication.Controller;

import com.example.EcommerceApplication.Dto.Request.CardRequest;
import com.example.EcommerceApplication.Dto.Response.CardResponse;
import com.example.EcommerceApplication.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardService cardService;

    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequest cardRequest){
        try{
            CardResponse cardResponse = cardService.addCard(cardRequest);
            return new ResponseEntity(cardResponse, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
