package com.hibernate.one2manuuni;

import com.hibernate.one2manuuni.entity.Course;
import com.hibernate.one2manuuni.entity.Instructor;
import com.hibernate.one2manuuni.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate-1toMany.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // start a transaction
            session.beginTransaction();

            // get the course from the database
            int theId = 10;
            Course course = session.get(Course.class, theId);

            // delete the course
            System.out.println("Deleting the course: " + course);
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
