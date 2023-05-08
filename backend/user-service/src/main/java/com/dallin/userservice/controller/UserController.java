package com.dallin.userservice.controller;

import com.dallin.userservice.model.AppUser;
import com.dallin.userservice.model.LoginCredentials;
import com.dallin.userservice.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jose.JwaAlgorithm;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dallin.userservice.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    private final TokenService tokenService;

    public UserController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/add")
    public ResponseEntity<AppUser> addUser(@RequestBody AppUser appUser) {
        userService.createUser(appUser);
        return ResponseEntity.status(HttpStatus.CREATED).header("Location", "users/" + appUser.getId()).body(appUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<AppUser> getUser(@PathVariable Integer userId) {
        AppUser appUser = userService.getUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(appUser);
    }
    
    @PutMapping("/update")
    public ResponseEntity<AppUser> updateUser(@RequestBody AppUser appUser) {
        userService.updateUser(appUser);
        return ResponseEntity.status(HttpStatus.OK).header("Location", "users/" + appUser.getId()).body(appUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
        AppUser appUser = getUser(userId).getBody();
        userService.deleteUser(appUser);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> Login (@RequestBody LoginCredentials credentials) {
        AppUser appUser = userService.loadUserByUsername(credentials.getUsername());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(credentials.getPassword(), appUser.getPassword())) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

            // create the authentication token with the user's username, password, and authorities
            Authentication authentication = new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword(), authorities);
            String jwtToken = tokenService.generateToken(authentication);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("\"" + jwtToken + "\"");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
}
