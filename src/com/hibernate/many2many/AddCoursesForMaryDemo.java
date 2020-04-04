package com.hibernate.many2many;

import com.hibernate.many2many.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddCoursesForMaryDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate-ManytoMany.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // start a transaction
            session.beginTransaction();

            // get the student Mary from the DB
            int theId = 2;
            Student student = session.get(Student.class, theId);

            // create more courses
            System.out.println("Creating courses:");
            Course course1 = new Course("Introduction to Hibernate");
            Course course2 = new Course("Cooking steak in 5 minutes");
            Course course3 = new Course("Child upbringing basic stuff");
            Course course4 = new Course("Sales manager crash course");

            // add student to courses
            course1.addStudentToCourse(student);
            course2.addStudentToCourse(student);
            course3.addStudentToCourse(student);
            course4.addStudentToCourse(student);
            System.out.println(student.getCourseList());

            // save the courses
            session.save(course1);
            session.save(course2);
            session.save(course3);
            session.save(course4);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            session.close();
            factory.close();
        }

    }
}
