package com.example.EcommerceApplication.Service.Implementation;

import com.example.EcommerceApplication.Convertor.ProductConvertor;
import com.example.EcommerceApplication.Dto.Request.ProductRequest;
import com.example.EcommerceApplication.Dto.Response.ProductResponse;
import com.example.EcommerceApplication.Entity.Item;
import com.example.EcommerceApplication.Entity.Product;
import com.example.EcommerceApplication.Entity.Seller;
import com.example.EcommerceApplication.Enums.ProductCategory;
import com.example.EcommerceApplication.Enums.ProductStatus;
import com.example.EcommerceApplication.Exception.SellerNotExistException;
import com.example.EcommerceApplication.Repository.ProductRepository;
import com.example.EcommerceApplication.Repository.SellerRepository;
import com.example.EcommerceApplication.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductResponse addProduct(ProductRequest productRequest) throws SellerNotExistException {
        Seller seller;

        try{
            seller = sellerRepository.findById(productRequest.getSellerId()).get();
        }
        catch(Exception e){
            throw new SellerNotExistException("Invalid Seller id");
        }

        Product product = ProductConvertor.ProductRequestToProduct(productRequest);
        product.setSeller(seller);

        seller.getProducts().add(product);

        sellerRepository.save(seller);
        return ProductConvertor.ProductToProductResponse(product);
    }

    @Override
    public List<ProductResponse> getProductByCategory(ProductCategory category) {
        List<Product> products = productRepository.findByProductCategory(category);

        List<ProductResponse> productResponses = new ArrayList<>();

        for(Product product:products){
            productResponses.add(ProductConvertor.ProductToProductResponse(product));
        }

        return productResponses;

    }

    @Override
    public List<ProductResponse> getProductsByPriceAndCategory(int price, String category) {

        List<Product> products = productRepository.getProductByPriceAndCategory(price,category);
        List<ProductResponse> productResponses = new ArrayList<>();

        for(Product product:products){
            productResponses.add(ProductConvertor.ProductToProductResponse(product));

        }
        return productResponses;
    }

    @Override
    public void reduceProductQuantity(Item item) throws Exception {
        Product product = item.getProduct();
        int quantity = item.getRequiredQuantity();
        int currentQuantity = product.getQuantity();
        if(quantity>currentQuantity){
            throw new Exception("Out of stock");
        }
        product.setQuantity(currentQuantity-quantity);
        if(product.getQuantity()==0){
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        }
    }
}
