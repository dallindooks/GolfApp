package com.dallin.golfapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dallin.golfapp.model.CourseHole;
import com.dallin.golfapp.model.GolfCourse;
import com.dallin.golfapp.repository.CourseHoleRepo;
import com.dallin.golfapp.repository.GolfCourseRepo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GolfCourseService {

    private final GolfCourseRepo golfCourseRepo;
    private final CourseHoleRepo courseHoleRepo;

    @Autowired
    public GolfCourseService(GolfCourseRepo golfCourseRepo, CourseHoleRepo courseHoleRepo) {
        this.golfCourseRepo = golfCourseRepo;
        this.courseHoleRepo = courseHoleRepo;
    }

    public GolfCourse addGolfCourse(GolfCourse golfCourse) {

        GolfCourse newCourse = golfCourse;

        golfCourseRepo.save(newCourse);

        // auto sets up golf holes
        List<CourseHole> holes = new ArrayList<>();
        for (int i = 1; i < 19; i++) {
            CourseHole newHole = new CourseHole();
            newHole.setGolfCourse(golfCourse);
            newHole.setHoleNumber(i);
            newHole.setHolePhoto("hole_" + newHole.getHoleNumber());
            holes.add(newHole);
            courseHoleRepo.save(newHole);
        }

        // create new directory for new golf course
        String directoryName = golfCourse.getCourseName().replace(" ", "_");
        File directory = new File("C:/Personal-Projects/GolfApp/course-images/" + directoryName);
        if (!directory.exists()) {
            directory.mkdir();
            System.out.println("Directory created successfully!");
        } else {
            System.out.println("Directory already exists!");
        }

        return newCourse;
    }

    public List<GolfCourse> getAllGolfCourses() {
        return golfCourseRepo.findAll();
    }

    public Optional<GolfCourse> getGolfCourseById(Integer courseId) {
        return golfCourseRepo.findById(courseId);
    }

    public GolfCourse updateGolfCourse(GolfCourse golfCourse) {
        return golfCourseRepo.save(golfCourse);
    }

    public void deleteGolfCourse(GolfCourse golfCourse) {
        golfCourseRepo.delete(golfCourse);
    }

}
