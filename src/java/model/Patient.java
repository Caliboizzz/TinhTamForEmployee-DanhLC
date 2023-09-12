/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Danh
 */
public class Patient extends Account{
    private String healthInsurance;

    public Patient() {
        super();
    }

    public Patient(String healthInsurance, int id, String firstName, String lastName, String sex, Date birthday, String address, String phone, String email, String password, String role) {
        super(id, firstName, lastName, sex, birthday, address, phone, email, password, role);
        this.healthInsurance = healthInsurance;
    }

    public Patient(String healthInsurance, String firstName, String lastName, String sex, Date birthday, String address, String phone, String email, String password, String role) {
        super(firstName, lastName, sex, birthday, address, phone, email, password, role);
        this.healthInsurance = healthInsurance;
    }

    public String getHealthInsurance() {
        return healthInsurance;
    }

    public void setHealthInsurance(String healthInsurance) {
        this.healthInsurance = healthInsurance;
    }
    
    
}
