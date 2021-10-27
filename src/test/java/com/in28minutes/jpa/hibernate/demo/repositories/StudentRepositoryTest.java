package com.in28minutes.jpa.hibernate.demo.repositories;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entities.Passport;
import com.in28minutes.jpa.hibernate.demo.entities.Student;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SpringBootTest(classes = DemoApplication.class)
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    EntityManager em;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    @Transactional
    void retrieveStudentAndPassportDetails() {
        Student student = em.find(Student.class, 20001L);
        logger.info("Student -> {}", student);
        logger.info("Passport -> {}", student.getPassport());
    }

    @Test
    @Transactional
    void retrievePassportAndAssociatedStudent() {
        Passport passport = em.find(Passport.class, 40001L);
        logger.info("Passport -> {}", passport);
        logger.info("Student -> {}", passport.getStudent());
    }

    @Test
    @Transactional
    void retrieveStudentAndCourse() {
        Student student = em.find(Student.class, 20001L);
        logger.info("student -> {}", student);
        logger.info("courses -> {}", student.getCourses());
    }
}
