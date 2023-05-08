package com.example.EcommerceApplication.Controller;

import com.example.EcommerceApplication.Dto.Request.CustomerRequest;
import com.example.EcommerceApplication.Dto.Response.CustomerResponse;
import com.example.EcommerceApplication.Exception.CustomerAlreadyExists;
import com.example.EcommerceApplication.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody CustomerRequest customerRequest) throws CustomerAlreadyExists {
        try {
            CustomerResponse customerResponse = customerService.addCustomer(customerRequest);
            return new ResponseEntity(customerResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    // view all customers

    // get a customer by email/mob

    // get all customers whose age is greater than 25

    // get all customers who use VISA card

    // update a customer info by email

    // delete a customer by email/mob
}
