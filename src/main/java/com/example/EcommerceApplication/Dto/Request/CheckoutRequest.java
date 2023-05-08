package com.example.EcommerceApplication.Dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class CheckoutRequest {

    int customerId;

    String cardNo;

    int cvv;


}
