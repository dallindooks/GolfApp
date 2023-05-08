package com.dallin.golfapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dallin.golfapp.model.GolfCourse;
import com.dallin.golfapp.service.GolfCourseService;

@RestController
@RequestMapping("/golfcourse")
public class GolfCourseController {

    @Autowired
    private GolfCourseService golfCourseService;
    
    @PostMapping("/add")
    public ResponseEntity<GolfCourse> createGolfCourse(@RequestBody GolfCourse golfCourse) {
        golfCourseService.addGolfCourse(golfCourse);
        return ResponseEntity.status(HttpStatus.CREATED)
                    .header("Location", "courses/" + golfCourse.getId())
                    .body(golfCourse);

    }

    @GetMapping("/{courseId}")
    public ResponseEntity<GolfCourse> getGolfCourseById(@PathVariable Integer courseId) {
        GolfCourse golfCourse = golfCourseService.getGolfCourseById(courseId).get();
        return ResponseEntity.status(HttpStatus.OK).body(golfCourse);
    }

    @PutMapping("/update")
    public ResponseEntity<GolfCourse> updateGolfCourse(@RequestBody GolfCourse golfCourse) {
        golfCourseService.updateGolfCourse(golfCourse);
        return ResponseEntity.status(HttpStatus.OK)
                    .header("Location", "courses/" + golfCourse.getId())
                    .body(golfCourse);

    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteGolfCourse(@PathVariable Integer courseId) {
        GolfCourse golfCourse = golfCourseService.getGolfCourseById(courseId).get();
        golfCourseService.deleteGolfCourse(golfCourse);
        return ResponseEntity.noContent().build();
    }
}
