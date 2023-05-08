package com.example.EcommerceApplication.Convertor;

import com.example.EcommerceApplication.Dto.Request.SellerRequest;
import com.example.EcommerceApplication.Dto.Response.SellerResponse;
import com.example.EcommerceApplication.Entity.Seller;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SellerConvertor {

    public static Seller SellerRequestToSeller(SellerRequest sellerRequest){
        return Seller.builder()
                .age(sellerRequest.getAge())
                .emailId(sellerRequest.getEmailId())
                .name(sellerRequest.getName())
                .mobNo(sellerRequest.getMobNo())
                .build();
    }

    public static SellerResponse SellerToSellerResponse(Seller seller){
        return SellerResponse.builder()
                .name(seller.getName())
                .emailId(seller.getEmailId())
                .build();
    }
}
