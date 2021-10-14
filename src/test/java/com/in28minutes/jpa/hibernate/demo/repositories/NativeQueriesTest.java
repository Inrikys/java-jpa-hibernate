package com.in28minutes.jpa.hibernate.demo.repositories;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entities.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest(classes = DemoApplication.class)
class NativeQueriesTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    void shouldExecuteNativeQuery() {
        Query query = em.createNativeQuery("select * from course", Course.class);
        List resultList = query.getResultList();
        logger.info("select * from Course -> {}", resultList);
    }

    @Test
    void shouldExecuteNativeQueryWithParameter() {
        Query query = em.createNativeQuery("select * from course where id = ?", Course.class);
        query.setParameter(1, 10001L);
        List resultList = query.getResultList();
        logger.info("select * from Course -> {}", resultList);
    }

    @Test
    void shouldExecuteNativeQueryWithNamedParameter() {
        Query query = em.createNativeQuery("select * from course where id = :id", Course.class);
        query.setParameter("id", 10001L);
        List resultList = query.getResultList();
        logger.info("select * from Course -> {}", resultList);
    }

    @Test
    @Transactional
    void shouldExecuteNativeQueryToUpdate() {
        Query query = em.createNativeQuery("update course set last_updated_date = sysdate()");
        int numberOfRowsUpdated = query.executeUpdate();
        logger.info("Number of rows updated -> {}", numberOfRowsUpdated);
    }

}
