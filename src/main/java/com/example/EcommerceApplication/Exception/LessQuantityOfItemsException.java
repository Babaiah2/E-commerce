package com.example.EcommerceApplication.Exception;

public class LessQuantityOfItemsException extends Exception{

    public LessQuantityOfItemsException(String message){
        super(message);
    }
}
