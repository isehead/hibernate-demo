package com.hibernate.eagervslazydemo;

import com.hibernate.eagervslazydemo.entity.Course;
import com.hibernate.eagervslazydemo.entity.Instructor;
import com.hibernate.eagervslazydemo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.hibernate.query.Query;

public class FetchJoinDemo {

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

            Query<Instructor> query = session.createQuery("SELECT i FROM Instructor i JOIN FETCH i.courseList " +
                    "WHERE i.id=:theInstructorId", Instructor.class);

            // set parameter on query
            query.setParameter("theInstructorId", theId);

            // execute query and get the instructor
            Instructor instructor = query.getSingleResult();
            System.out.println("Requested instructor: " + instructor);

            // commit transaction
            session.getTransaction().commit();

            // create session on purpose
            session.close();
            System.out.println("Done!");

            System.out.println("Courses: " + instructor.getCourseList());

        } finally {
            session.close();
            factory.close();
        }

    }
}
