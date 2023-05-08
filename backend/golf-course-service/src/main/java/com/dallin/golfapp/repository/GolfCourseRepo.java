package com.dallin.golfapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dallin.golfapp.model.GolfCourse;

public interface GolfCourseRepo extends JpaRepository<GolfCourse, Integer> {

    public GolfCourse findGolfCourseByCourseName(String courseName);
    
}
