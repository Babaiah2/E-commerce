package com.example.EcommerceApplication.Dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class OrderRequest {
    int customerId;

    int productId;

    int requiredQuantity;

    String cardNo;

    int cvv;
}
