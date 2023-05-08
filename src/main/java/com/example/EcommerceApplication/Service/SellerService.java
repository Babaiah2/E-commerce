package com.example.EcommerceApplication.Service;

import com.example.EcommerceApplication.Dto.Request.SellerRequest;
import com.example.EcommerceApplication.Dto.Response.SellerResponse;
import com.example.EcommerceApplication.Entity.Seller;
import com.example.EcommerceApplication.Exception.SellerAlreadyExistsException;
import com.example.EcommerceApplication.Exception.SellerNotExistException;

import java.util.List;

public interface SellerService {
    SellerResponse addSeller(SellerRequest sellerRequest) throws SellerAlreadyExistsException;

    SellerResponse getSellerByEmailId(String EmailId) throws SellerAlreadyExistsException, SellerNotExistException;

    Seller getSellerById(int id);

    List<Seller> getAllSellers();

    SellerResponse updateSellerByEmailId(String emailId);

    String deleteSellerByEmailId(String emailId);

    String deleteSellerById(int id);

    List<Seller> getSellersByAge(int age);
}
