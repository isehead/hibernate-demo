package com.hibernate.many2many;

import com.hibernate.many2many.entity.Student;
import com.hibernate.many2many.entity.Course;
import com.hibernate.many2many.entity.Instructor;
import com.hibernate.many2many.entity.InstructorDetail;
import com.hibernate.many2many.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentsDemo {

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

            // create a course
            Course course = new Course("Pacman - How to score 1b points");

            // saving the course
            System.out.println("\nSaving the course: " + course);
            session.save(course);

            // create the students
            Student student1 = new Student("John", "Doe", "jd@gmail.com");
            Student student2 = new Student("Mary", "Bloody", "bm@gmail.com");

            // add students to the course
            course.addStudentToCourse(student1);
            course.addStudentToCourse(student2);

            // save the students
            System.out.println("\nSaving students...");
            session.save(student1);
            session.save(student2);
            System.out.println("Saved students: " + course.getStudents());

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            session.close();
            factory.close();
        }

    }
}
