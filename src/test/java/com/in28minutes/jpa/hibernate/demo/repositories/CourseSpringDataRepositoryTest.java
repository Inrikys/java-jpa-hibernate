package com.in28minutes.jpa.hibernate.demo.repositories;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entities.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = DemoApplication.class)
class CourseSpringDataRepositoryTest {

    @Autowired
    CourseSpringDataRepository courseRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    void findByIdShouldGetACourse() {
        Optional<Course> courseOptional = courseRepository.findById(10001L);
        logger.info("is present(boolean): {}", courseOptional.isPresent());
    }

    @Test
    void shouldSortUsingCriteria() {
        List<Course> courseList = courseRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
        logger.info("Sort using criteria: {}", courseList);
    }
}
