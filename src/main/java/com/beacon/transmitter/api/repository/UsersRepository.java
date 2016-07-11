package com.beacon.transmitter.api.repository;

import com.beacon.transmitter.api.repository.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Yuriy on 2016-07-07.
 */
public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByEmail(String email);

}
