package com.hibernate.one2many;

import com.hibernate.one2many.entity.Course;
import com.hibernate.one2many.entity.Instructor;
import com.hibernate.one2many.entity.InstructorDetail;
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

            // create sample courses
            Course course1 = new Course("Air Guitar");
            Course course2 = new Course("Pinball course");

            // add courses to the instructor
            instructor.addCourse(course1);
            instructor.addCourse(course2);

            // save the courses
            session.save(course1);
            session.save(course2);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            session.close();
            factory.close();
        }

    }
}
