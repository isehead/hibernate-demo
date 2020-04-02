package com.hibernate.eagervslazydemo;

import com.hibernate.eagervslazydemo.entity.Course;
import com.hibernate.eagervslazydemo.entity.Instructor;
import com.hibernate.eagervslazydemo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EagerLazyDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate-eagervslazy.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // start a transaction
            session.beginTransaction();

            // get the instructor from the database
            int theId = 1;
            Instructor instructor = session.get(Instructor.class, theId);
            System.out.println("EAGER/ Requested instructor: ");

            // show courses list
            System.out.println("EAGER/ Requested courses: " + instructor);
            System.out.println(instructor.getCourseList());

            // commit transaction
            session.getTransaction().commit();
            System.out.println("EAGER/ Done!");

        } finally {
            session.close();
            factory.close();
        }

    }
}
