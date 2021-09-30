package com.in28minutes.jpa.hibernate.demo.repositories;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entities.Course;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = DemoApplication.class)
class CourseRepositoryTest {

	@Autowired
	CourseRepository courseRepository;

	@Test
	void findByIdShouldGetACourse() {
		Course course = courseRepository.findById(10001L);
		Assertions.assertEquals("JPA in 10 Steps", course.getName());
	}

}
