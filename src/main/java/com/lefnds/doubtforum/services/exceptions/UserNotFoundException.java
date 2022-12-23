package com.lefnds.doubtforum.services.exceptions;

public class UserNotFoundException extends RuntimeException {
    public static final Long serialVersionUID = 1L;

    public UserNotFoundException(String msg) {
        super(msg);
    }
}
