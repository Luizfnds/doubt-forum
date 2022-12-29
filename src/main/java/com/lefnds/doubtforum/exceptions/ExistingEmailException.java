package com.lefnds.doubtforum.exceptions;

public class ExistingEmailException extends RuntimeException {

    public ExistingEmailException() {
        super( "Email not registered" );
    }

}
