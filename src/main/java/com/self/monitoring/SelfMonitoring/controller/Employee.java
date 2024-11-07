package com.self.monitoring.SelfMonitoring.controller;

public class Employee {
    private String empName;
    private int empId;
    private int experience;
    private double salary;

    public Employee(String empName, int empId, int experience, double salary) {
        this.empName = empName;
        this.empId = empId;
        this.experience = experience;
        this.salary = salary;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empName='" + empName + '\'' +
                ", empId=" + empId +
                ", experience=" + experience +
                ", salary=" + salary +
                '}';
    }
}
