package com.example.EcommerceApplication.Service.Implementation;

import com.example.EcommerceApplication.Convertor.SellerConvertor;
import com.example.EcommerceApplication.Dto.Request.SellerRequest;
import com.example.EcommerceApplication.Dto.Response.SellerResponse;
import com.example.EcommerceApplication.Entity.Seller;
import com.example.EcommerceApplication.Exception.SellerAlreadyExistsException;
import com.example.EcommerceApplication.Exception.SellerNotExistException;
import com.example.EcommerceApplication.Repository.SellerRepository;
import com.example.EcommerceApplication.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    SellerRepository sellerRepository;
    @Override
    public SellerResponse addSeller(SellerRequest sellerRequest) throws SellerAlreadyExistsException {

        if(sellerRepository.findByEmailId(sellerRequest.getEmailId())!=null)
            throw new SellerAlreadyExistsException("seller exists with same emailId");

        Seller newSeller = SellerConvertor.SellerRequestToSeller(sellerRequest);

        sellerRepository.save(newSeller);

        SellerResponse sellerResponse = SellerConvertor.SellerToSellerResponse(newSeller);

        return sellerResponse;
    }

    @Override
    public SellerResponse getSellerByEmailId(String emailId) throws SellerNotExistException {
        if(sellerRepository.findByEmailId(emailId)==null)
            throw new SellerNotExistException("seller does not exists with email id");


        Seller seller = sellerRepository.findByEmailId(emailId);
         return SellerConvertor.SellerToSellerResponse(seller);

    }

    @Override
    public Seller getSellerById(int id) {
        return null;
    }

    @Override
    public List<Seller> getAllSellers() {
        return null;
    }

    @Override
    public SellerResponse updateSellerByEmailId(String emailId) {
        return null;
    }

    @Override
    public String deleteSellerByEmailId(String emailId) {
        return null;
    }

    @Override
    public String deleteSellerById(int id) {
        return null;
    }

    @Override
    public List<Seller> getSellersByAge(int age) {
        return null;
    }
}
