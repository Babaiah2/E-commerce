package com.example.EcommerceApplication.Dto.Response;

import com.example.EcommerceApplication.Enums.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level= AccessLevel.PRIVATE)
public class ProductResponse {

    String productName;

    int quantity;

    ProductStatus productStatus;
}
