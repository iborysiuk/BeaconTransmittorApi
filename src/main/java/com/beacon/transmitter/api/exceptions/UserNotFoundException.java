package com.beacon.transmitter.api.exceptions;

/**
 * Created by Yuriy on 2016-07-07.
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String email) {
        super("Couldn't find a user with email '" + email + "'.");
    }

}
