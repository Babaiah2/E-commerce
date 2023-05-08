package com.example.EcommerceApplication.Convertor;

import com.example.EcommerceApplication.Dto.Request.CustomerRequest;
import com.example.EcommerceApplication.Dto.Response.CustomerResponse;
import com.example.EcommerceApplication.Entity.Customer;

public class CustomerConvertor {

    public static Customer CustomerRequestToCustomer(CustomerRequest customerRequest){
        return Customer.builder()
                .name(customerRequest.getName())
                .age(customerRequest.getAge())
                .mobNo(customerRequest.getMobNo())
                .emailId(customerRequest.getEmailId())
                .address(customerRequest.getAddress())
                .build();
    }

    public static CustomerResponse customerToCustomerResponse(Customer customer){
        return CustomerResponse.builder()
                .name(customer.getName())
                .emailId(customer.getEmailId())
                .build();
    }
}
