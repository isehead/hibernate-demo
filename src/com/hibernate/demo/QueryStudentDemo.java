package com.hibernate.demo;

import com.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

import static com.hibernate.demo.TextColors.*;

public class QueryStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // start a transaction
            session.beginTransaction();

            // query students
            List<Student> theStudents = session.createQuery("from Student").getResultList();
            System.out.println(ANSI_BLUE + "\nList of all students:" + ANSI_RESET);
            displayStudents(theStudents);

            // query students: lastName = 'Doe'
            theStudents = session.createQuery("from Student s where s.lastName = 'Duck'").getResultList();
            System.out.println(ANSI_BLUE + "\nStudents with lastName 'Duck':" + ANSI_RESET);

            // display the students
            displayStudents(theStudents);

            // query students: lastName = 'Doe' OR firstName = 'Daffy'
            theStudents = session.createQuery("from Student s where s.lastName = 'Doe' or firstName = 'Daffy'").getResultList();
            System.out.println(ANSI_BLUE + "\nStudents with lastName 'Doe' OR firstName 'Daffy':" + ANSI_RESET);

            // display the students
            displayStudents(theStudents);

            // query students: email LIKE %gmail.com
            theStudents = session.createQuery("from Student s where s.email LIKE '%@gmail.com'").getResultList();
            System.out.println(ANSI_BLUE + "\nStudents with email on gmail:" + ANSI_RESET);

            // display the students
            displayStudents(theStudents);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("\nDone!\n");

        } finally {
            factory.close();
        }

    }

    private static void displayStudents(List<Student> theStudents) {
        // display the students
        for (Student tempStudent : theStudents)
            System.out.println(tempStudent);
    }
}
