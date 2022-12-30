package com.lefnds.doubtforum.exceptions;

public class ExpiredTokenException extends RuntimeException {

    public ExpiredTokenException() {
        super( "Expired token" );
    }

}
