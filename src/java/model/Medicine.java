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
public class Medicine {

    private int id;
    private String name;
    private int inStock;
    private float price;
    private int categoryID;
    private int supplierID;

    public Medicine() {
    }

    public Medicine(String name, int inStock, float price, int categoryID, int supplierID) {
        this.name = name;
        this.inStock = inStock;
        this.price = price;
        this.categoryID = categoryID;
        this.supplierID = supplierID;
    }

    public Medicine(int id, String name, int inStock, float price, int categoryID, int supplierID) {
        this.id = id;
        this.name = name;
        this.inStock = inStock;
        this.price = price;
        this.categoryID = categoryID;
        this.supplierID = supplierID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public static Medicine findMedicineById(List<Medicine> medicineList, int id) {
        for (Medicine medicine : medicineList) {
            if (medicine.getId() == id) {
                return medicine;
            }
        }
        return null; 
    }

}
