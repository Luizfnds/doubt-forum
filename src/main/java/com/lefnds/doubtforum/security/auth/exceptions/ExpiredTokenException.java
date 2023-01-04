package com.lefnds.doubtforum.security.auth.exceptions;

public class ExpiredTokenException extends RuntimeException {

    public ExpiredTokenException() {
        super( "Expired token" );
    }

}
