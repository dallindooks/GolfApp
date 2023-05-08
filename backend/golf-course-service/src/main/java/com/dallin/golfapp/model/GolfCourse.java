package com.dallin.golfapp.model;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@AllArgsConstructor
@Entity
@Table(name = "\"Golf Course\"")
public class GolfCourse implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private @Id Integer id;

    private String courseName;

    private String mainPhotoLocation;

    private String description;

    private String address;

    private String phoneNumber;
    
    @OneToMany(mappedBy = "golfCourse", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<CourseHole> holes;

    public GolfCourse() {}
}
