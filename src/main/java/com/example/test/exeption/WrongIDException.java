package com.example.test.exeption;

public class WrongIDException extends Exception{
    public WrongIDException(String message) {
        super(message);
    }
}
