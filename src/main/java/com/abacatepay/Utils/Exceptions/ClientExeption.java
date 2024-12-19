package com.abacatepay.Utils.Exceptions;

public class ClientExeption extends RuntimeException{
    public ClientExeption(){
        super("Error while creating AbacatePay Client.");
    }
    public ClientExeption(String message){
        super(message);
    }
    public ClientExeption(String message, Throwable cause){
        super(message, cause);
    }
}
