/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ad
 */
public class MedicalRecord {
    private int id;
    private Date dateCreated;
    private String status;
    private String note;
    private Date reCheckDate;
    private float totalPrice;
    private int doctorId;
    private int patientId;
    private int cashierId;
    private int receptionistId;
    private String patientName;
    private String doctorName;
    private String receptionistName;
    private String cashierName;

    public MedicalRecord() {
    }
    
    
    
    public MedicalRecord(int id, Date dateCreated, String status, String note, Date reCheckDate, float totalPrice, int doctorId, int patientId, int cashierId, int receptionistId) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.status = status;
        this.note = note;
        this.reCheckDate = reCheckDate;
        this.totalPrice = totalPrice;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.cashierId = cashierId;
        this.receptionistId = receptionistId;
    }

    public MedicalRecord(Date dateCreated, String status, String note, Date reCheckDate, float totalPrice, int doctorId, int patientId, int cashierId, int receptionistId) {
        this.dateCreated = dateCreated;
        this.status = status;
        this.note = note;
        this.reCheckDate = reCheckDate;
        this.totalPrice = totalPrice;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.cashierId = cashierId;
        this.receptionistId = receptionistId;
    }

    public MedicalRecord(String status, int doctorId, int patientId, int receptionistId) {
        this.status = status;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.receptionistId = receptionistId;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }
    
    public String getDateFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return sdf.format(dateCreated);
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getReCheckDate() {
        return reCheckDate;
    }

    public void setReCheckDate(Date reCheckDate) {
        this.reCheckDate = reCheckDate;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getCashierId() {
        return cashierId;
    }

    public void setCashierId(int cashierId) {
        this.cashierId = cashierId;
    }

    public int getReceptionistId() {
        return receptionistId;
    }

    public void setReceptionistId(int receptionistId) {
        this.receptionistId = receptionistId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getReceptionistName() {
        return receptionistName;
    }

    public String getCashierName() {
        return cashierName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setReceptionistName(String receptionistName) {
        this.receptionistName = receptionistName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    
    
    
}
