package com.hibernate.one2manuuni;

import com.hibernate.one2manuuni.entity.Course;
import com.hibernate.one2manuuni.entity.Instructor;
import com.hibernate.one2manuuni.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorCoursesDemo {

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

            // get the instructor from the database
            int theId = 1;
            Instructor instructor = session.get(Instructor.class, theId);

            // show courses list
            System.out.println("Requested instructor is: " + instructor);
            System.out.println(instructor.getCourseList());

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            session.close();
            factory.close();
        }

    }
}
