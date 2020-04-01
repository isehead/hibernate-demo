package com.hibernate.one2one;

import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {

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
            // create the objects
//            Instructor tempInstructor = new Instructor("Chad", "Darby", "darby@l2code.com");
//            InstructorDetail tempInstructorDetail = new InstructorDetail("http://youtube.com","L2Code");
            Instructor tempInstructor = new Instructor("Thierry", "Henry", "th@l2code.com");
            InstructorDetail tempInstructorDetail = new InstructorDetail("psports","football");

            // associate the objects
            tempInstructor.setInstructorDetail(tempInstructorDetail);

            // start a transaction
            session.beginTransaction();

            // save the instructor, CascadeType.ALL includes saving associated object
            System.out.println("Saving instructor: " + tempInstructor);
            session.save(tempInstructor);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            factory.close();
        }

    }
}
