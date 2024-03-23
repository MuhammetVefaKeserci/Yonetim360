package com.example.yonetim360.exceptions;

public class MyFileNotFoundException extends RuntimeException{

    public MyFileNotFoundException(String message) {
        super(message);
    }

    public MyFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
