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
import javax.persistence.criteria.*;
import java.util.List;

@SpringBootTest(classes = DemoApplication.class)
class CriteriaQueryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;
    // BUILD QUERY USING JAVA
    // 1. Use Criteria Builder to create a Criteria Query returning the
    // expected result object
    // 2. Define roots for tables which are involved in the query
    // 3. Define Predicates etc using Criteria Builder
    // 4. Add Predicates etc to the Criteria Query
    // 5. Build the TypedQuery using the Entity Manager and Criteria Query

    @Test
    void shouldReturnCourseList() {
        // Select c from Course c

        // 1. Use Criteria Builder to create a Criteria Query returning the
        // expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        // 3. Define Predicates etc using Criteria Builder

        // 4. Add Predicates etc to the Criteria Query

        // 5. Build the TypedQuery using the Entity Manager and Criteria Query
        TypedQuery<Course> resultList = em.createQuery(cq.select(courseRoot));
        logger.info("Select c from Course c -> {}", resultList);
    }

    @Test
    void shouldReturnCourseListHaving10Steps() {
        // Select c from Course c where name like '%10 Steps'

        // 1. Use Criteria Builder to create a Criteria Query returning the
        // expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        // 3. Define Predicates etc using Criteria Builder
        Predicate like10Steps = cb.like(courseRoot.get("name"), "%10 Steps");

        // 4. Add Predicates etc to the Criteria Query
        cq.where(like10Steps);

        // 5. Build the TypedQuery using the Entity Manager and Criteria Query
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("Select c from Course c where name like '%10 Steps' -> {}", resultList);
    }

    @Test
    void shouldReturnAllCoursesWithoutStudents() {
        // Select c from Course c where c.students is empty

        // 1. Use Criteria Builder to create a Criteria Query returning the
        // expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        // 3. Define Predicates etc using Criteria Builder
        Predicate studentsIsEmpty = cb.isEmpty(courseRoot.get("students"));

        // 4. Add Predicates etc to the Criteria Query
        cq.where(studentsIsEmpty);

        // 5. Build the TypedQuery using the Entity Manager and Criteria Query
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("Select c from Course c where c.students is empty -> {}", resultList);
    }

    @Test
    void shouldReturnCoursesJoinStudents() {
        // Select c from Course c join c.students

        // 1. Use Criteria Builder to create a Criteria Query returning the
        // expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        // 3. Define Predicates etc using Criteria Builder
        Join<Object, Object> join = courseRoot.join("students");

        // 4. Add Predicates etc to the Criteria Query


        // 5. Build the TypedQuery using the Entity Manager and Criteria Query
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("Select c from Course c join c.students -> {}", resultList);
    }

    @Test
    void shouldReturnCoursesLeftJoinStudents() {
        // Select c from Course c join c.students

        // 1. Use Criteria Builder to create a Criteria Query returning the
        // expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        // 3. Define Predicates etc using Criteria Builder
        Join<Object, Object> join = courseRoot.join("students", JoinType.LEFT);

        // 4. Add Predicates etc to the Criteria Query


        // 5. Build the TypedQuery using the Entity Manager and Criteria Query
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("Select c from Course c join c.students -> {}", resultList);
    }
}
