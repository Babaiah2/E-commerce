package com.example.EcommerceApplication.Convertor;

import com.example.EcommerceApplication.Dto.Request.ProductRequest;
import com.example.EcommerceApplication.Dto.Response.ProductResponse;
import com.example.EcommerceApplication.Entity.Product;
import com.example.EcommerceApplication.Enums.ProductStatus;

public class ProductConvertor {

    public static Product ProductRequestToProduct(ProductRequest productRequest){
        return Product.builder()
                .name(productRequest.getProductName())
                .price(productRequest.getPrice())
                .productCategory(productRequest.getProductCategory())
                .quantity(productRequest.getQuantity())
                .productStatus(ProductStatus.AVAILABLE)
                .build();

    }
    public static ProductResponse ProductToProductResponse(Product product){
        return ProductResponse.builder()
                .productName(product.getName())
                .quantity(product.getQuantity())
                .productStatus(product.getProductStatus())
                .build();
    }
}
