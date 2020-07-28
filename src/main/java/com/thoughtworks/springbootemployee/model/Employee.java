package com.thoughtworks.springbootemployee.model;

public class Employee {
    private int id;
    private int age;
    private String name;
    private String gender;
    private double salary;

    public Employee(int id, int age, String name, String gender, double salary) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.gender = gender;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
