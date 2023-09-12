/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Medicine;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Danh
 */
public class MedicineFacade {
    public List<Medicine> selectAll() throws SQLException, ClassNotFoundException {
        List<Medicine> list = null;
        //Tạo connection để kết nối vào DBMS
        Connection con = DbContext.getConnection();
        //Tạo đối tượng statement
        Statement stm = con.createStatement();
        //Thực thi lệnh SELECT
        ResultSet rs = stm.executeQuery("select * from Medicine where InStock > 0");
        list = new ArrayList<>();
        while (rs.next()) {
            Medicine medicine = new Medicine();
            medicine.setId(rs.getInt("id"));
            medicine.setName(rs.getString("name"));
            medicine.setInStock(rs.getInt("InStock"));
            medicine.setPrice(rs.getFloat("Price"));
            medicine.setCategoryID(rs.getInt("CategoryID"));
            medicine.setSupplierID(rs.getInt("SupplierID"));
            list.add(medicine);
        }
        con.close();
        return list;
    }
    
        public void updateInstock(int medicineId, int quantity) throws ClassNotFoundException, SQLException {
        //tao ket noi db
        Connection con = DbContext.getConnection();
        //truy xuat co tham so
        PreparedStatement stm = con.prepareStatement("EXEC subtractMedicineQuantity @medicineId= ?, @quantity = ? ;");
        //cung cap gia tri cho tham so
        stm.setInt(1, medicineId);;
        stm.setInt(2, quantity);
        //thuc thi cau lenh sql
        int count = stm.executeUpdate();
        //dong ket noi db
        con.close();
    }
    public Medicine getWithId(int id) throws SQLException, ClassNotFoundException {

        //Tạo connection để kết nối vào DBMS
        Connection con = DbContext.getConnection();
        //Tạo đối tượng statement
        Statement stm = con.createStatement();
        //Thực thi lệnh SELECT
        ResultSet rs = stm.executeQuery("select * from Medicine where id="+id);
        Medicine medicine = new Medicine();
        if (rs.next()) {
            
            medicine.setId(rs.getInt("id"));
            medicine.setName(rs.getString("name"));
            medicine.setInStock(rs.getInt("InStock"));
            medicine.setPrice(rs.getFloat("Price"));
            medicine.setCategoryID(rs.getInt("CategoryID"));
            medicine.setSupplierID(rs.getInt("SupplierID"));
        }
        con.close();
        return medicine;
    }
}
