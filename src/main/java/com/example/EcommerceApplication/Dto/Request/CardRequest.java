package com.example.EcommerceApplication.Dto.Request;

import com.example.EcommerceApplication.Enums.CardType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class CardRequest {
    String MobNo;
    String cardNo;

    int cvv;

    Date expiryDate;


    CardType cardType;
}
