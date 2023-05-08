package com.example.EcommerceApplication.Service.Implementation;

import com.example.EcommerceApplication.Convertor.CustomerConvertor;
import com.example.EcommerceApplication.Dto.Request.CustomerRequest;
import com.example.EcommerceApplication.Dto.Response.CustomerResponse;
import com.example.EcommerceApplication.Entity.Cart;
import com.example.EcommerceApplication.Entity.Customer;
import com.example.EcommerceApplication.Exception.CustomerAlreadyExists;
import com.example.EcommerceApplication.Repository.CustomerRepository;
import com.example.EcommerceApplication.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerResponse addCustomer(CustomerRequest customerRequest) throws CustomerAlreadyExists {

        if(customerRepository.findByMobNo(customerRequest.getMobNo())!=null){
            throw new CustomerAlreadyExists("customer exists with this "+customerRequest.getMobNo());
        }

        if(customerRepository.findByEmailId(customerRequest.getEmailId())!=null){
            throw new CustomerAlreadyExists(("customer exists with this "+customerRequest.getEmailId()));
        }
         Customer customer = CustomerConvertor.CustomerRequestToCustomer(customerRequest);

         Cart cart = Cart.builder()
                 .cartTotal(0)
                 .numberOfItems(0)
                 .customer(customer)
                 .build();

         customer.setCart(cart);

         customerRepository.save(customer);

         //Response


        return CustomerConvertor.customerToCustomerResponse(customer);
    }
}
