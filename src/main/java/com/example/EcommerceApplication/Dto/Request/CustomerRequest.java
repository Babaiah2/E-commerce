package com.example.EcommerceApplication.Dto.Request;


import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class CustomerRequest {

    String name;

    String emailId;

    int age;

    String mobNo;

    String address;
}
