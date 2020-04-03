package com.hibernate.one2manuuni;

import com.hibernate.one2manuuni.entity.Course;
import com.hibernate.one2manuuni.entity.Instructor;
import com.hibernate.one2manuuni.entity.InstructorDetail;
import com.hibernate.one2manuuni.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndReviewsDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate-1toManyUni.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // start a transaction
            session.beginTransaction();

            // create a course
            Course course = new Course("Pacman - How to score 1b points");

            // add some reviews
            course.addReview(new Review("Great course!"));
            course.addReview(new Review("Worth every penny"));
            course.addReview(new Review("Better than nothing..."));
            course.addReview(new Review("Where is my moneyback???"));

            // save the course and let cascading save everything else
            System.out.println("Saving the course:" + course);
            System.out.println(course.getReviews());
            session.save(course);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            session.close();
            factory.close();
        }

    }
}
