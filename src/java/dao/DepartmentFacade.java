/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Department;
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
public class DepartmentFacade {
    public List<Department> selectAll() throws SQLException, ClassNotFoundException {
        List<Department> list = null;
        //Tạo connection để kết nối vào DBMS
        Connection con = DbContext.getConnection();
        //Tạo đối tượng statement
        Statement stm = con.createStatement();
        //Thực thi lệnh SELECT
        ResultSet rs = stm.executeQuery("select * from Department");
        list = new ArrayList<>();
        while (rs.next()) {
            Department department = new Department();
            department.setId(rs.getInt("id"));
            department.setName(rs.getString("name"));
            list.add(department);
        }
        con.close();
        return list;
    }
    public List<Department> selectAllofDoctor() throws SQLException, ClassNotFoundException {
        List<Department> list = null;
        //Tạo connection để kết nối vào DBMS
        Connection con = DbContext.getConnection();
        //Tạo đối tượng statement
        Statement stm = con.createStatement();
        //Thực thi lệnh SELECT
        ResultSet rs = stm.executeQuery("select * from Department WHERE id NOT IN (1, 2, 7);");
        list = new ArrayList<>();
        while (rs.next()) {
            Department department = new Department();
            department.setId(rs.getInt("id"));
            department.setName(rs.getString("name"));
            list.add(department);
        }
        con.close();
        return list;
    }
}
