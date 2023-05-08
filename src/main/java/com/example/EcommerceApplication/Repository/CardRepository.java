package com.example.EcommerceApplication.Repository;

import com.example.EcommerceApplication.Entity.Card;
import com.example.EcommerceApplication.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {


    Card findByCardNo(String cardNo);
}
