package com.hibernate.homework.entity;

import com.hibernate.demo.DateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;
import java.util.Date;
import java.util.List;


public class EmployeeMain {

    public static void main(String[] args) throws ParseException {

        // define configuration
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            // create employees
            String dateOfBirth1 = "01/01/1980";
            String dateOfBirth2 = "01/03/1981";
            String dateOfBirth3 = "01/05/1982";
            String dateOfBirth4 = "01/07/1983";
            String dateOfBirth5 = "01/09/1984";

            Date theDateOfBirth1 = DateUtils.parseDate(dateOfBirth1);
            Date theDateOfBirth2 = DateUtils.parseDate(dateOfBirth1);
            Date theDateOfBirth3 = DateUtils.parseDate(dateOfBirth1);
            Date theDateOfBirth4 = DateUtils.parseDate(dateOfBirth1);
            Date theDateOfBirth5 = DateUtils.parseDate(dateOfBirth1);

            Employee employee1 = new Employee("Henry", "Thierry", theDateOfBirth1, "Arsenal");
            Employee employee2 = new Employee("Gerrard", "Steven", theDateOfBirth2, "Liverpool");
            Employee employee3 = new Employee("Scholes", "Paul", theDateOfBirth3, "Manchester United");
            Employee employee4 = new Employee("Del Piero", "Alessandro", theDateOfBirth4, "Juventus");
            Employee employee5 = new Employee("Di Maria", "Angel", theDateOfBirth5, "Paris Saint-Germain");

            // save employees to the table
            System.out.println("Saving new employees: ");
            session.save(employee1);
            session.save(employee2);
            session.save(employee3);
            session.save(employee4);
            session.save(employee5);
            System.out.println(employee1.toString() + '\n' + employee2 + '\n' + employee3 + '\n' + employee4 + '\n' + employee5);
            session.getTransaction().commit();

            // retrieve objects with ID = 2 and ID = 5
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            List<Employee> queryEmployee = session.createQuery("FROM Employee e WHERE id = '2' OR id = '5'").getResultList();
            System.out.println("\nEmployees with ID = 2 and ID = 5: ");
            for (Employee tempEmployee : queryEmployee) {
                System.out.println(tempEmployee);
            }
            session.getTransaction().commit();


            // direct query to employee with ID = 4
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            Employee myEmployee = session.get(Employee.class, 4);
            System.out.println("\nEmployee with ID = 4:");
            System.out.println(myEmployee);
            session.getTransaction().commit();

            // update objects
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            Employee employeeUpdate = session.get(Employee.class, 1);
            employeeUpdate.setCompany("PSG");
            System.out.println("\nEmployee with ID = 1 has changed his company from Arsenal to " + employeeUpdate.getCompany());
            System.out.println(employeeUpdate.getCompany());
            session.getTransaction().commit();


            // delete objects
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            System.out.println("\nDeleting employee with ID = 3:");
            session.createQuery("DELETE FROM Employee WHERE id ='3'").executeUpdate();
            session.getTransaction().commit();

            // list left employees
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            System.out.println();
            List<Employee> leftEmployees = session.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
            for (Employee tempEmployee : leftEmployees) {
                System.out.println(tempEmployee);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }
}
