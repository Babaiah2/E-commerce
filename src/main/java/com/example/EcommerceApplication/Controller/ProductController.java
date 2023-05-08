package com.example.EcommerceApplication.Controller;

import com.example.EcommerceApplication.Dto.Request.ProductRequest;
import com.example.EcommerceApplication.Dto.Response.ProductResponse;
import com.example.EcommerceApplication.Enums.ProductCategory;
import com.example.EcommerceApplication.Exception.SellerNotExistException;
import com.example.EcommerceApplication.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequest productRequest) throws SellerNotExistException {
        try {
            ProductResponse productResponse = productService.addProduct(productRequest);
            return new ResponseEntity(productResponse, HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getby/{category}")
    public List<ProductResponse> getProductsByCategory(@PathVariable("category")ProductCategory category){
        return productService.getProductByCategory(category);
    }

    @GetMapping("/get/{price}/{category}")
    public List<ProductResponse> getProductsByPriceAndCategory(@PathVariable("price") int price,
                                                               @PathVariable("category") String category){
        return productService.getProductsByPriceAndCategory(price,category);
    }

    // Get all product by seller email id

    // delete a product by seller id and product id

    // return top 5 cheapest products

    // return top 5 costliest products

    // return all out of stock products

    // return all available products

    // return all products that have quantity less than 10

    // return the cheapest product in a particular category

    // return the costliest product in a particular category
}
