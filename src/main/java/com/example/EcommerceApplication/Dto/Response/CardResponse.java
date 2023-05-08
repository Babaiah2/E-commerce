package com.example.EcommerceApplication.Dto.Response;

import com.example.EcommerceApplication.Enums.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class CardResponse {
    String cardNo;

    Date expiryDate;
    CardType cardType;
}
