package com.beacon.transmitter.api.exceptions;

/**
 * Created by Yuriy on 2016-07-08.
 */
public class WrongCredentialsException extends RuntimeException {

    public WrongCredentialsException() {
        super("Email or Password are wrong.");
    }
}
