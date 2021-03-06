package com.hibernate.one2oneuni;

import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate-1to1.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            // get the instructor by ID
            int theId = 2;
            Instructor tempInstructor = session.get(Instructor.class, theId);

            System.out.println("Found instructor: " + tempInstructor);

            // delete the instructor
            if (tempInstructor != null) {
                System.out.println("Deleting: " + tempInstructor);

                // will also delete "details" object due to cascading
                session.delete(tempInstructor);
            }

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close the session
            session.close();
            factory.close();
        }

    }
}
