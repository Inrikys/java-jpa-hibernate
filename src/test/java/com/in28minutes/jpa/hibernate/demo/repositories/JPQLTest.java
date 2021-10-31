package com.in28minutes.jpa.hibernate.demo.repositories;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entities.Course;
import com.in28minutes.jpa.hibernate.demo.entities.Student;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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

    @Test
    void shouldReturnCourseListWithAtList2StudentsFromJPQL() {
        TypedQuery<Course> query = em.createQuery("Select c from Course c where size(c.students) >= 2", Course.class);
        List<Course> courseList = query.getResultList();
        logger.info("Select c from Course c  where c.students >= 2 -> {}", courseList);
    }

    @Test
    void shouldReturnCourseListOrderedByStudentsFromJPQL() {
        TypedQuery<Course> query = em.createQuery("Select c from Course c order by size(c.students) desc", Course.class);
        List<Course> courseList = query.getResultList();
        logger.info("Select c from Course c order by size(c.students) desc -> {}", courseList);
    }

    @Test
    void shouldReturnStudentsListWithPassportsCertainPatternFromJPQL() {
        TypedQuery<Student> query = em.createQuery("Select s from Student s where s.passport.number like '%1234%'", Student.class);
        List<Student> studentList = query.getResultList();
        logger.info("Select s from Student s where s.passport.number like '%1234%' -> {}", studentList);
    }

    @Test
    void joinStudentsCourses() {
        // INNER JOIN
        Query query = em.createQuery("Select c, s from Course c JOIN c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Select c, s from Course c JOIN c.students s SIZE ' -> {}", resultList.size());
        for(Object[] result : resultList){
            logger.info("{} {}", result[0], result[1]);
        }
    }

    @Test
    void leftJoinStudentsCourses() {
        // LEFT JOIN
        Query query = em.createQuery("Select c, s from Course c LEFT JOIN c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Select c, s from Course c LEFT JOIN c.students s SIZE ' -> {}", resultList.size());
        for(Object[] result : resultList){
            logger.info("{} {}", result[0], result[1]);
        }
    }

    @Test
    void crossJoinStudentsCourses() {
        // CROSS JOIN
        // Não relaciona dados, apenas faz o cruzamento de todas as rows
        Query query = em.createQuery("Select c, s from Course c, Student s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Select c, s from Course c, Student s SIZE ' -> {}", resultList.size());
        for(Object[] result : resultList){
            logger.info("{} {}", result[0], result[1]);
        }
    }
}
