package com.example.EcommerceApplication.Service;

import com.example.EcommerceApplication.Dto.Request.ItemRequest;
import com.example.EcommerceApplication.Entity.Item;
import com.example.EcommerceApplication.Exception.CustomerNotFoundException;
import com.example.EcommerceApplication.Exception.ProductNotFoundException;

public interface ItemService {

    Item addItem(ItemRequest itemRequest) throws Exception;
}
