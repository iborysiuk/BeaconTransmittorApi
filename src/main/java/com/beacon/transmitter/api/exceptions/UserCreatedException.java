package com.beacon.transmitter.api.exceptions;

/**
 * Created by Yuriy on 2016-07-07.
 */
public class UserCreatedException extends RuntimeException {

    public UserCreatedException(String email) {
        super("User with email '" + email + "' have been created.");
    }
}
