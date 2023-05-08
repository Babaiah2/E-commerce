package com.example.EcommerceApplication.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CartResponse {
    int cartTotal;
    int noOfItems;
    String customerName;

    List<ItemResponse> itemResponses;
}
