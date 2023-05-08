package com.example.EcommerceApplication.Service.Implementation;

import com.example.EcommerceApplication.Controller.CardController;
import com.example.EcommerceApplication.Convertor.CardConvertor;
import com.example.EcommerceApplication.Dto.Request.CardRequest;
import com.example.EcommerceApplication.Dto.Response.CardResponse;
import com.example.EcommerceApplication.Entity.Card;
import com.example.EcommerceApplication.Entity.Customer;
import com.example.EcommerceApplication.Exception.CustomerNotFoundException;
import com.example.EcommerceApplication.Repository.CustomerRepository;
import com.example.EcommerceApplication.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public CardResponse addCard(CardRequest cardRequest) throws CustomerNotFoundException {

        Customer customer = customerRepository.findByMobNo(cardRequest.getMobNo());
        if(customer==null){
            throw new CustomerNotFoundException("Sorry! The customer doesn't exists");
        }

        Card card = CardConvertor.CardRequestToCard(cardRequest);
        card.setCustomer(customer);

        customer.getCards().add(card);

        customerRepository.save(customer);

        //response

        return CardConvertor.CardToCardResponse(card);
    }
}
