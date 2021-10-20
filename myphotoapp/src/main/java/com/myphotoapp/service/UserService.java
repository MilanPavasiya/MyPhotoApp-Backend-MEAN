package com.myphotoapp.service;

import com.google.firebase.auth.FirebaseAuthException;
import com.myphotoapp.model.User;
import com.myphotoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    private FirebaseService firebaseService;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public User findUserById(String id) {
        return userRepository.findById(id).get();
    }

    public List<User> findAllByName(String name) {
        return userRepository.findAllByName(name);
    }

    public List<User> findAllByEmail(String email) {
        return userRepository.findAllByEmail(email);
    }

    public User getById(String idToken, String email) throws IOException, FirebaseAuthException {
        List<User> userList = userRepository.findAll();
        for (User user:userList) {
            if(user.getEmail().equalsIgnoreCase(email)){
                return user;
            }
        }
        return  null;
    }

    public User updateProfilePhotoUrl(String profilePhotoUrl, String email) {
        List<User> userList = userRepository.findAll();
        for (User user:userList) {
            if(user.getEmail().equalsIgnoreCase(email)){
                System.out.println(" inside the if condition ---- ");
                user.setProfilePhotoUrl(profilePhotoUrl);
                System.out.println(user.getProfilePhotoUrl());
                userRepository.save(user);
                return user;
            }
        }
        return null;
    }
}
