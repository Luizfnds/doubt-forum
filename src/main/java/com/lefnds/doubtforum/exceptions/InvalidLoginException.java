package com.lefnds.doubtforum.exceptions;

public class InvalidLoginException extends RuntimeException {

    public InvalidLoginException() {
        super( "Password has been incorrect" );
    }

}
