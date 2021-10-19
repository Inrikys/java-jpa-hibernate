package com.in28minutes.jpa.hibernate.demo.repositories;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entities.Course;
import com.in28minutes.jpa.hibernate.demo.entities.Review;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SpringBootTest(classes = DemoApplication.class)
class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    EntityManager em;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    void findByIdShouldGetACourse() {
        Course course = courseRepository.findById(10001L);
        Assertions.assertEquals("JPA in 10 Steps", course.getName());
    }

    // DirtiesContext will reset the data. It won't change any data.
    @Test
    @DirtiesContext
    void deleteById_basic() {
        courseRepository.deleteById(10002L);
        Assertions.assertNull(courseRepository.findById(10002L));
    }

    @Test
    @DirtiesContext
    void save_basic() {
        // cenario
        Course course = courseRepository.findById(10001L);
        Assertions.assertEquals("JPA in 10 Steps", course.getName());

        // acao
        course.setName("updated name");
        courseRepository.save(course);

        // verificacao
        Course verifiedCourse = courseRepository.findById(10001L);
        Assertions.assertEquals("updated name", verifiedCourse.getName());
    }

    @Test
    @Transactional
    public void addReviewsForCourse() {
        Course course = courseRepository.findById(10003L);
        logger.info("course.getReviews() -> {}", course.getReviews());

        // add 2 reviews
        Review review1 = new Review("5", "Great Hands-on Stuff.");
        Review review2 = new Review("5", "Hatsoff.");
        course.getReviews().add(review1);
        course.getReviews().add(review2);

        reviewRepository.save(review1);
        reviewRepository.save(review2);
        courseRepository.save(course);
    }

    @Test
    @Transactional
    void retrieveReviewsForCourse() {
        Course course = courseRepository.findById(10001L);
        logger.info("course.getReviews() -> {}", course.getReviews());
    }

    @Test
    @Transactional
    void retrieveCourseForReview() {
        Review review = em.find(Review.class, 50001L);
        logger.info("course.getReviews() -> {}", review.getCourse());
    }


}
