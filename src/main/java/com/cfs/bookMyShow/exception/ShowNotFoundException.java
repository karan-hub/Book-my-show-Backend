package com.cfs.bookMyShow.exception;

public class ShowNotFoundException extends RuntimeException{
    public ShowNotFoundException(String showNotFound) {
        super(showNotFound);
    }
}
