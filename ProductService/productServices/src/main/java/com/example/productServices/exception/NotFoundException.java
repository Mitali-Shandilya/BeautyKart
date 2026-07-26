package com.example.productServices.exception;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String msg){
        super(msg);
    }
}
