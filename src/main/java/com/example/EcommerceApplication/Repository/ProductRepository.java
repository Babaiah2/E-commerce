package com.example.EcommerceApplication.Repository;

import com.example.EcommerceApplication.Entity.Product;
import com.example.EcommerceApplication.Enums.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    List<Product>  findByProductCategory(ProductCategory category);

   //normal sql query
   @Query(value = " select * from Product p where p.price >=:price and product_category=:category ",nativeQuery = true)
    //jpa hibernate query @Query(value = "select p from Product p where p.price > :price and p.productCategory=:category")
    List<Product> getProductByPriceAndCategory(int price, String category);
}
