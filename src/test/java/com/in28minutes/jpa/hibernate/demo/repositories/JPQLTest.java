package com.in28minutes.jpa.hibernate.demo.repositories;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entities.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@SpringBootTest(classes = DemoApplication.class)
class JPQLTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    void shouldReturnResultListFromJPQL() {
//        List resultList = em.createQuery("Select c from Course c").getResultList();
        List resultList = em.createNamedQuery("query_get_all_courses").getResultList();
        logger.info("Select c from Course c -> {}", resultList);
    }

    @Test
    void shouldReturnCourseListFromJPQL() {
//        TypedQuery<Course> query = em.createQuery("Select c from Course c", Course.class);
        TypedQuery<Course> query = em.createNamedQuery("query_get_all_courses", Course.class);
        List<Course> courseList = query.getResultList();
        logger.info("Select c from Course c -> {}", courseList);
    }

    @Test
    void shouldReturnCourseWithWhereFromJPQL() {
        TypedQuery<Course> query = em.createNamedQuery("query_get_100_Step_courses", Course.class);
        List<Course> courseList = query.getResultList();
        logger.info("Select c from Course c where name like %100 Steps -> {}", courseList);
    }

    @Test
    void shouldReturnCourseListWithoutStudentsFromJPQL() {
        TypedQuery<Course> query = em.createQuery("Select c from Course c where c.students is empty", Course.class);
        List<Course> courseList = query.getResultList();
        logger.info("Select c from Course c  where c.students is empty -> {}", courseList);
    }
}
