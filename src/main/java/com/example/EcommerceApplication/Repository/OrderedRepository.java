package com.example.EcommerceApplication.Repository;

import com.example.EcommerceApplication.Entity.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedRepository extends JpaRepository<Ordered,Integer> {
}
