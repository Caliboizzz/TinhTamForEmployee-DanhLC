package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class DbContext {
   public static Connection getConnection() throws ClassNotFoundException, SQLException{
        String url = "jdbc:sqlserver://sql.bsite.net\\MSSQL2016;databaseName=tinhtam_DB;user=tinhtam_DB;password=1";
        Connection con = null;
            //Loading a driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Creating a connection
            con =DriverManager.getConnection(url);
        return con;
        
    } 
   
}
