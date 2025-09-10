package com.cfs.bookMyShow.exception;

public class UserNotFoundException extends  RuntimeException {
    public UserNotFoundException(String userNotFound) {
        super(userNotFound);
    }
}
