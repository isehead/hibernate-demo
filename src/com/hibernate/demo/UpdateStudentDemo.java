package com.hibernate.demo;

import com.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            int studentId = 1;

            // get a new session and start a transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("\nGetting student with id: " + studentId);
            Student myStudent = session.get(Student.class, studentId);
            System.out.println("Get complete: " + myStudent);

            System.out.println("\nUpdating student's name with id = 1...");
            myStudent.setFirstName("Scooby");

            //commit the transaction
            session.getTransaction().commit();


            // Another query
            session = factory.getCurrentSession();
            session.beginTransaction();

            // update email for all students
            System.out.println("Update email for all students");
            session.createQuery("UPDATE Student set email = 'test@gmail.com'")
                    .executeUpdate();

            session.getTransaction().commit();

        } finally {
            factory.close();
        }

    }
}