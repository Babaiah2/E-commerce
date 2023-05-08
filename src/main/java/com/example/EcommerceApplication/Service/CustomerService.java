package com.example.EcommerceApplication.Service;

import com.example.EcommerceApplication.Dto.Request.CustomerRequest;
import com.example.EcommerceApplication.Dto.Response.CustomerResponse;
import com.example.EcommerceApplication.Exception.CustomerAlreadyExists;

public interface CustomerService {
    CustomerResponse addCustomer(CustomerRequest customerRequest) throws CustomerAlreadyExists;
}
