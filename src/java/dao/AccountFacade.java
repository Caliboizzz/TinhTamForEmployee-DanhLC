/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Account;
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
public class AccountFacade {

    public Account login(String email, String password) throws ClassNotFoundException, SQLException {
        Account account = null;
        //Tao ket noi database
        Connection con = DbContext.getConnection();
        //Thuc hien lenh SQL
        PreparedStatement stm = con.prepareStatement("select * from Account where Email=? and Password=?");
        stm.setString(1, email);
        stm.setString(2, password);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            //Doc mau tin va them vao object account
            account = new Account();
            account.setId(rs.getInt("id"));
            account.setLastName(rs.getString("LastName"));
            account.setFirstName(rs.getString("FirstName"));
            account.setAddress(rs.getString("Address"));
            account.setPhone(rs.getString("Phone"));
            account.setEmail(rs.getString("Email"));
            account.setPassword(rs.getString("Password"));
            account.setSex(rs.getString("Sex"));
            account.setBirthday(rs.getDate("Birthday"));
            account.setRole(rs.getString("Role"));
            account.setStatus(rs.getString("Status"));
        }
        //Dong ket noi database
        con.close();
        return account;
    }

    public Boolean checkEmailExist(String email) throws ClassNotFoundException, SQLException {

        //Tao ket noi database
        Connection con = DbContext.getConnection();
        //Thuc hien lenh SQL
        PreparedStatement stm = con.prepareStatement("select * from Account where Email=?");
        stm.setString(1, email);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }

    public List<Account> selectAllNonActivated() throws SQLException, ClassNotFoundException {
        List<Account> list = null;
        //Tạo connection để kết nối vào DBMS
        Connection con = DbContext.getConnection();
        //Tạo đối tượng statement
        Statement stm = con.createStatement();
        //Thực thi lệnh SELECT
        ResultSet rs = stm.executeQuery("select * from Account where Account.Status='NonActivated' and Role != 'patient'");
        list= new ArrayList();
        while (rs.next()) {
            Account account = new Account();
            account.setId(rs.getInt("id"));
            account.setLastName(rs.getString("LastName"));
            account.setFirstName(rs.getString("FirstName"));
            account.setAddress(rs.getString("Address"));
            account.setPhone(rs.getString("Phone"));
            account.setEmail(rs.getString("Email"));
            account.setPassword(rs.getString("Password"));
            account.setSex(rs.getString("Sex"));
            account.setBirthday(rs.getDate("Birthday"));
            account.setRole(rs.getString("Role"));
            account.setStatus(rs.getString("Status"));
            list.add(account);
        }
        con.close();
        return list;
    }
    
    public void activate(int id) throws ClassNotFoundException, SQLException {
        Connection con = DbContext.getConnection();
        PreparedStatement stm = con.prepareStatement("update Account set Status='Activated' where id=?");
        stm.setInt(1, id);
        int count = stm.executeUpdate();
        con.close();
    }
    public void deactivate(int id) throws ClassNotFoundException, SQLException {
        Connection con = DbContext.getConnection();
        PreparedStatement stm = con.prepareStatement("update Account set Status='NonActivated' where id=?");
        stm.setInt(1, id);
        int count = stm.executeUpdate();
        con.close();
    }
    
    public void delete(int id) throws SQLException, ClassNotFoundException {
        //Tạo connection để kết nối vào DBMS
        Connection con = DbContext.getConnection();
        //Tạo đối tượng PreparedStatement
        PreparedStatement stm = con.prepareStatement("DELETE FROM employee WHERE id = ?; DELETE FROM account WHERE id = ?;");
        stm.setInt(1, id);
        stm.setInt(2, id);
        //Thực thi lệnh sql
        int count = stm.executeUpdate();
        con.close();
    }
}
