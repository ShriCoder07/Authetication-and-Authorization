package com.example.demo.exception;

public class UserAlredyREgister extends RuntimeException{
    public UserAlredyREgister(String msg){
        super(msg);
    }
}
