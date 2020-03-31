package com.hibernate.demo;

import com.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {

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

            // delete the student
            System.out.println("Deleting student: " + myStudent);
            session.delete(myStudent);

            // delete student with id = 2
            System.out.println("Deleting student with id = 2:");
            session.createQuery("DELETE FROM Student WHERE Id = 2").executeUpdate();

            //commit the transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        } finally {
            factory.close();
        }

    }
}