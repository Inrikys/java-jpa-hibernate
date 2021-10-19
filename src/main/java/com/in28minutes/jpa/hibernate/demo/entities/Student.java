package com.in28minutes.jpa.hibernate.demo.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "student")
@Entity
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    // Traz apenas quando student.getPassport() for chamado
    // No caso de entity manager ser usado, é necessário utilizar @Transactional
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passport_id")
    private Passport passport;

    @ManyToMany
    @JoinTable(name = "student_course", joinColumns = @JoinColumn(name ="student_id"),
            inverseJoinColumns = @JoinColumn(name="course_id"))
    private List<Course> courses = new ArrayList<>();

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
