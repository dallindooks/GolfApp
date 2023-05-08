package com.dallin.golfapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dallin.golfapp.model.CourseHole;

public interface CourseHoleRepo extends JpaRepository<CourseHole, Integer> {
    
}
