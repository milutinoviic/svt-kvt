package com.example.sitpass.exceptions;

public class EmailExistsException extends Exception{
    public EmailExistsException(String message){
        super(message);
    }
}
