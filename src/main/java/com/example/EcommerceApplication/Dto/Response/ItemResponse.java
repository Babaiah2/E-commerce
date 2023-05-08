package com.example.EcommerceApplication.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ItemResponse {
    String productName;

    int priceOfOneItem;

    int totalPrice;

    int quantity;
}
