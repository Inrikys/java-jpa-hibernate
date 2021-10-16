package com.in28minutes.jpa.hibernate.demo.entities;

import javax.persistence.*;

@Table(name = "review")
@Entity
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    private String rating;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public Review() {
    }

    public Review(String rating, String description) {
        this.rating = rating;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Course getCourse() {
        return course;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", rating='" + rating + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
