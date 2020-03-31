package com.hibernate.homework.entity;


import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int cid;

    @Column(name = "surname")
    private String surname;

    @Column(name = "given_name")
    private String givenName;

    @Column(name = "company")
    private String company;

    public Employee(String surname, String givenName, String company) {
        this.givenName = givenName;
        this.surname = surname;
        this.company = company;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "cid=" + cid +
                ", givenName='" + givenName + '\'' +
                ", surname='" + surname + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
