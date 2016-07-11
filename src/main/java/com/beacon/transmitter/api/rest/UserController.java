package com.beacon.transmitter.api.rest;

import com.beacon.transmitter.api.exceptions.WrongCredentialsException;
import com.google.gson.Gson;
import com.beacon.transmitter.api.exceptions.UserCreatedException;
import com.beacon.transmitter.api.exceptions.UserNotFoundException;
import com.beacon.transmitter.api.repository.UsersRepository;
import com.beacon.transmitter.api.repository.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Yuriy on 2016-07-07.
 */


@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Gson gson;

    @Autowired
    UserController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    ResponseEntity<?> register(@RequestBody Users input) {

        LOGGER.info("===> register: [" + gson.toJson(input) + "]");

        this.validateNewEmail(input.getEmail());

        Users newUser = new Users.Builder()
                .setUserName(input.getUsername())
                .setEmail(input.getEmail())
                .setPassword(passwordEncoder.encode(input.getPassword()))
                .setIsActivated(true)
                .createUsers();

        usersRepository.save(newUser);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    ResponseEntity<?> login(@RequestBody Users input) {

        String result = gson.toJson(this.validateUser(input.getEmail(), input.getPassword()));
        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }

    private Users validateUser(String email, String password) {
        Users users = this.usersRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
        boolean isMatched = passwordEncoder.matches(password, users.getPassword());
        if (!isMatched) throw new WrongCredentialsException();
        else return users;
    }

    private void validateNewEmail(String email) {
        Users users = this.usersRepository.findByEmail(email).orElse(null);
        if (users != null) throw new UserCreatedException(email);
    }

}
