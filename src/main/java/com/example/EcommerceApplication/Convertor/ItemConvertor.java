package com.example.EcommerceApplication.Convertor;

import com.example.EcommerceApplication.Dto.Request.ItemRequest;
import com.example.EcommerceApplication.Dto.Response.ItemResponse;
import com.example.EcommerceApplication.Entity.Item;

public class ItemConvertor {

    public static Item itemRequestToItem(ItemRequest itemRequest){
        return Item.builder()
                .requiredQuantity(itemRequest.getRequiredQuantity())
                .build();
    }

    public static ItemResponse itemToItemResponse(Item item){
        return ItemResponse.builder()
                .priceOfOneItem(item.getProduct().getPrice())
                .productName(item.getProduct().getName())
                .quantity(item.getRequiredQuantity())
                .totalPrice(item.getRequiredQuantity()*item.getProduct().getPrice())
                .build();
    }
}
