package com.hibernate.homework.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class EmployeeMain {

    public static void main(String[] args) {

        // define configuration
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            // create employees
            Employee employee1 = new Employee("Henry", "Thierry", "Arsenal");
            Employee employee2 = new Employee("Gerrard", "Steven", "Liverpool");
            Employee employee3 = new Employee("Scholes", "Paul", "Manchester United");
            Employee employee4 = new Employee("Del Piero", "Alessandro", "Juventus");
            Employee employee5 = new Employee("Di Maria", "Angel", "Paris Saint-Germain");

            // save employees to the table
            System.out.println("Saving new employees: ");
            session.save(employee1);
            session.save(employee2);
            session.save(employee3);
            session.save(employee4);
            session.save(employee5);
            System.out.println(employee1.toString() + '\n' + employee2 + '\n' + employee3 + '\n' + employee4 + '\n' + employee5);

            // retrieve objects with ID = 2 and ID = 5
            List<Employee> queryEmployee = session.createQuery("FROM Employee e WHERE e.id = '2' OR e.id = '5'").getResultList();
            System.out.println("\nEmployees with ID = 2 and ID = 5: ");
            for (Employee tempEmployee : queryEmployee) {
                System.out.println(tempEmployee);
            }

            // direct query to employee with ID = 4
            Employee myEmployee = session.get(Employee.class, 4);
            System.out.println("\nEmployee with ID = 4:");
            System.out.println(myEmployee);

            // update objects
            Employee employeeUpdate = session.get(Employee.class, 1);
            employeeUpdate.setCompany("PSG");
            System.out.println("\nEmployee with ID = 1 has changed his company from Arsenal to " + employeeUpdate.getCompany());

            // delete objects
            System.out.println("\nDeleting employee with ID = 3:");
            session.createQuery("DELETE FROM Employee WHERE id ='3'").executeUpdate();

            // list left employees
            System.out.println();
            List<Employee> leftEmployees = session.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
            for (Employee tempEmployee : leftEmployees){
                System.out.println(tempEmployee);
            }

        } finally {
            sessionFactory.close();
        }
    }
}
