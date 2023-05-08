package com.example.EcommerceApplication.Service;

import com.example.EcommerceApplication.Dto.Request.CardRequest;
import com.example.EcommerceApplication.Dto.Response.CardResponse;
import com.example.EcommerceApplication.Exception.CustomerNotFoundException;

public interface CardService {
    CardResponse addCard(CardRequest cardRequest) throws CustomerNotFoundException;
}
