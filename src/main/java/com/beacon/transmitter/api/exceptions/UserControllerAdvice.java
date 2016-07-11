package com.beacon.transmitter.api.exceptions;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Yuriy on 2016-07-08.
 */
@ControllerAdvice
public class UserControllerAdvice {

    private static final String ERROR = "error";

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    VndErrors userNotFoundExceptionHandler(UserNotFoundException e) {
        return new VndErrors(ERROR, e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(WrongCredentialsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    VndErrors wrongCredentialsExceptionHandler(WrongCredentialsException e) {
        return new VndErrors(ERROR, e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(UserCreatedException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    VndErrors userCreatedExceptionHandler(UserCreatedException e) {
        return new VndErrors(ERROR, e.getMessage());
    }
}
