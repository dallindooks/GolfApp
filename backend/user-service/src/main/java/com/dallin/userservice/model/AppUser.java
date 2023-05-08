package com.dallin.userservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"User\"")
public class AppUser {
    
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @Id Integer id;

    private String userName;

    private String password;

    private String email;


}
