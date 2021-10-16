package com.in28minutes.jpa.hibernate.demo.repositories;

import com.in28minutes.jpa.hibernate.demo.entities.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class ReviewRepository {

    @Autowired
    EntityManager em;

    public Review findById(Long id) {
        return em.find(Review.class, id);
    }

    public Review save(Review review) {
        if (review.getId() == null) {
            em.persist(review);
        } else {
            em.merge(review);
        }
        return review;
    }

    public void deleteById(Long id) {
        Review review = findById(id);
        em.remove(review);
    }
}
