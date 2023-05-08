package com.example.EcommerceApplication.Service;

import com.example.EcommerceApplication.Dto.Request.ProductRequest;
import com.example.EcommerceApplication.Dto.Response.ProductResponse;
import com.example.EcommerceApplication.Entity.Item;
import com.example.EcommerceApplication.Enums.ProductCategory;
import com.example.EcommerceApplication.Exception.SellerNotExistException;

import java.util.List;

public interface ProductService {





    ProductResponse addProduct(ProductRequest productRequest) throws SellerNotExistException;
     List<ProductResponse> getProductByCategory(ProductCategory category) ;
     List<ProductResponse> getProductsByPriceAndCategory(int price, String category) ;

    void reduceProductQuantity(Item item) throws Exception;
}
