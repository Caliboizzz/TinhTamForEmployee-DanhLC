/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Danh
 */
public class Prescription {
    private int id;
    private String DosageMorning;
    private String DosageAfternoon;
    private String DosageEvening;
    private String DosageNight;
    private String note;
    private int quantity;
    private int medicalRecordId;
    private int medicineId;
    private Medicine medicine;

    public Prescription(int id, String DosageMorning, String DosageAfternoon, String DosageEvening, String DosageNight, String note, int quantity, int medicalRecordId, int medicineId, Medicine medicine) {
        this.id = id;
        this.DosageMorning = DosageMorning;
        this.DosageAfternoon = DosageAfternoon;
        this.DosageEvening = DosageEvening;
        this.DosageNight = DosageNight;
        this.note = note;
        this.quantity = quantity;
        this.medicalRecordId = medicalRecordId;
        this.medicineId = medicineId;
        this.medicine = medicine;
    }



    public Medicine getMedicine() {
        return medicine;
    }
    
    
    
    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    

    public Prescription() {
    }

    public Prescription(int id, String DosageMorning, String DosageAfternoon, String DosageEvening, String DosageNight, String note, int quantity, int medicalRecordId, int medicineId) {
        this.id = id;
        this.DosageMorning = DosageMorning;
        this.DosageAfternoon = DosageAfternoon;
        this.DosageEvening = DosageEvening;
        this.DosageNight = DosageNight;
        this.note = note;
        this.quantity = quantity;
        this.medicalRecordId = medicalRecordId;
        this.medicineId = medicineId;
    }

    public Prescription(String DosageMorning, String DosageAfternoon, String DosageEvening, String DosageNight, String note, int quantity, int medicalRecordId, int medicineId) {
        this.DosageMorning = DosageMorning;
        this.DosageAfternoon = DosageAfternoon;
        this.DosageEvening = DosageEvening;
        this.DosageNight = DosageNight;
        this.note = note;
        this.quantity = quantity;
        this.medicalRecordId = medicalRecordId;
        this.medicineId = medicineId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDosageMorning() {
        return DosageMorning;
    }

    public void setDosageMorning(String DosageMorning) {
        this.DosageMorning = DosageMorning;
    }

    public String getDosageAfternoon() {
        return DosageAfternoon;
    }

    public void setDosageAfternoon(String DosageAfternoon) {
        this.DosageAfternoon = DosageAfternoon;
    }

    public String getDosageEvening() {
        return DosageEvening;
    }

    public void setDosageEvening(String DosageEvening) {
        this.DosageEvening = DosageEvening;
    }

    public String getDosageNight() {
        return DosageNight;
    }

    public void setDosageNight(String DosageNight) {
        this.DosageNight = DosageNight;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMedicalRecordId() {
        return medicalRecordId;
    }

    public void setMedicalRecordId(int medicalRecordId) {
        this.medicalRecordId = medicalRecordId;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }
    
    
}
