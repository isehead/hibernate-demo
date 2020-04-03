package com.hibernate.one2manuuni;

import com.hibernate.one2manuuni.entity.Course;
import com.hibernate.one2manuuni.entity.Instructor;
import com.hibernate.one2manuuni.entity.InstructorDetail;
import com.hibernate.one2manuuni.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseAndReviewsDemo {

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

            // get the course
            int theId = 10;
            Course course = session.get(Course.class, theId);

            // print the course with reviews
            System.out.println(course);
            System.out.println(course.getReviews());

            // delete the course
            session.delete(course);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            session.close();
            factory.close();
        }

    }
}
