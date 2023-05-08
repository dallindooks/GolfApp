package com.dallin.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dallin.userservice.model.AppUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<AppUser, Integer> {

    @Query("SELECT u FROM AppUser u WHERE u.userName = :username")
    public AppUser findAppUserByUsername(@Param("username")String username);


}
