package com.in28minutes.jpa.hibernate.demo;

import com.in28minutes.jpa.hibernate.demo.entities.Course;
import com.in28minutes.jpa.hibernate.demo.entities.Review;
import com.in28minutes.jpa.hibernate.demo.entities.Student;
import com.in28minutes.jpa.hibernate.demo.repositories.CourseRepository;
import com.in28minutes.jpa.hibernate.demo.repositories.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EntityManager em;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Course course = courseRepository.save(new Course("Microservices 10 steps"));

        course.setName("updated name");
        courseRepository.save(course);

        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review("5", "Great Hands-on Stuff."));
        reviews.add(new Review("5", "Hatsoff."));

        courseRepository.addReviewsForCourse(course.getId(), reviews);

        studentRepository.insertStudentAndCourse(new Student("Henrique"), new Course("Pringles"));
    }
}
