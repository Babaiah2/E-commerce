package com.example.EcommerceApplication.Dto.Request;

import com.example.EcommerceApplication.Enums.ProductCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {

    int sellerId;

    String ProductName;

    int price;
    int quantity;

    ProductCategory productCategory;


}
