package com.example.EcommerceApplication.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SellerRequest {
    String name;

    String emailId;

    Integer age;

    String mobNo;
}
