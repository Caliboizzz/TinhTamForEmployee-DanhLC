/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author Danh
 */
public class Department {
    private int id;
    private String name;

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public static Department findDepartmentById(List<Department> departmentList , int id) {
        for (Department department : departmentList) {
            if (department.getId() == id) {
                return department;
            }
        }
        return null; 
    }
    
}
