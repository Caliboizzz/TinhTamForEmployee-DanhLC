/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Danh
 */
public class Employee extends Account {

    private float salary;
    private int departmentID;
    private String departmentName;
    private int processingCount;
    private Department department;
    
    public Employee() {
        super();
    }

    public Employee(float salary, int departmentID, int id, String firstName, String lastName, String sex, Date birthday, String address, String phone, String email, String password, String role) {
        super(id, firstName, lastName, sex, birthday, address, phone, email, password, role);
        this.salary = salary;
        this.departmentID = departmentID;
    }

    public Employee(float salary, int departmentID, String firstName, String lastName, String sex, Date birthday, String address, String phone, String email, String password, String role) {
        super(firstName, lastName, sex, birthday, address, phone, email, password, role);
        this.salary = salary;
        this.departmentID = departmentID;
    }

    public float getSalary() {
        return salary;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public int getProcessingCount() {
        return processingCount;
    }

    public void setProcessingCount(int processingCount) {
        this.processingCount = processingCount;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    
    
    
}
