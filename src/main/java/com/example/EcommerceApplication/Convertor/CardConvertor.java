package com.example.EcommerceApplication.Convertor;

import com.example.EcommerceApplication.Dto.Request.CardRequest;
import com.example.EcommerceApplication.Dto.Response.CardResponse;
import com.example.EcommerceApplication.Entity.Card;

public class CardConvertor {

    public static Card CardRequestToCard(CardRequest cardRequest){
        return Card.builder()
                .cardNo(cardRequest.getCardNo())
                .cvv(cardRequest.getCvv())
                .cardType(cardRequest.getCardType())
                .expiryDate(cardRequest.getExpiryDate())
                .build();
    }
    public static CardResponse CardToCardResponse(Card card){
        return CardResponse.builder()
                .cardNo(card.getCardNo())
                .cardType(card.getCardType())
                .expiryDate(card.getExpiryDate())
                .build();
    }
}
