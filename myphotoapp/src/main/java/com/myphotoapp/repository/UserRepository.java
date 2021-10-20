package com.myphotoapp.repository;

import com.myphotoapp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    List<User> findAllByName(String name);
    List<User> findAllByEmail(String email);
}
