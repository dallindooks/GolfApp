package com.dallin.userservice.service;

import com.dallin.userservice.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dallin.userservice.repository.UserRepository;

@Service
public class UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AppUser createUser(AppUser appUser) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(appUser.getPassword());
        appUser.setPassword(hashedPassword);

        return userRepository.save(appUser);
    }

    public AppUser getUser(Integer userId) {
        return userRepository.findById(userId).get();
    }

    public void deleteUser(AppUser appUser) {
        userRepository.delete(appUser);
    }

    public AppUser updateUser(AppUser appUser) {
        return userRepository.save(appUser);
    }

    public AppUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findAppUserByUsername(username);
    }
}
