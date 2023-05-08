package com.example.EcommerceApplication.Dto.Response;

import com.example.EcommerceApplication.Entity.Customer;
import com.example.EcommerceApplication.Entity.Item;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class OrderResponse {

    String orderNo;

    int totalValue;

    Date orderDate;

    String cardUsed;


    List<ItemResponse> items;

    String customerName;
}
