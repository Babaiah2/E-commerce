package com.example.EcommerceApplication.Repository;

import com.example.EcommerceApplication.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    Customer findByMobNo(String mobNo);
    Customer findByEmailId(String emailId);
}
