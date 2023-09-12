/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Department;
import model.Employee;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import utils.Hash;

/**
 *
 * @author Danh
 */
public class EmployeeFacade {

    public void register(Employee employee) throws ClassNotFoundException, NoSuchAlgorithmException, SQLException {
        //Tao ket noi database
        Connection con = DbContext.getConnection();

        try {
            //Bắt đầu transaction
            con.setAutoCommit(false);
            //Insert data vào table Account
            PreparedStatement stm = con.prepareStatement("insert into Account values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, employee.getEmail());
            stm.setString(2, Hash.hash(employee.getPassword()));
            stm.setString(3, employee.getLastName());
            stm.setString(4, employee.getFirstName());
            stm.setString(5, employee.getAddress());
            stm.setString(6, employee.getPhone());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            stm.setString(7, sdf.format(employee.getBirthday()));

            stm.setString(8, employee.getSex());
            stm.setString(9, employee.getRole());
            stm.setString(10, "NonActivated");
            int count = stm.executeUpdate();

            //Lấy account id được phát sinh tự động
            try (ResultSet rs = stm.getGeneratedKeys()) {
                if (rs.next()) {
                    employee.setId(rs.getInt(1));
                } else {
                    throw new SQLException("Inserting account failed.");
                }
            }

            //Insert data vào table Employee
            stm = con.prepareStatement("insert into Employee values(?, ?, ?)");
            stm.setInt(1, employee.getId());
            stm.setFloat(2, employee.getSalary());
            stm.setInt(3, employee.getDepartmentID());
            count = stm.executeUpdate();
            //Kết thúc transaction
            con.commit();
        } catch (SQLException ex) {
            try {
                //Undo transaction
                con.rollback();
            } catch (SQLException ex1) {
                throw ex1;
            }
            throw ex;
        }

        //Dong ket noi database
        con.close();
    }

    public List<Employee> selectAll(String role, String status, String DepId) throws ClassNotFoundException, SQLException {
        List<Employee> list = new ArrayList<>();
        role = "'" + role + "'";
        status = "'" + status + "'";
        //Tao ket noi database
        Connection con = DbContext.getConnection();
        //Thuc hien lenh SQL
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("EXEC SelectEmployeeWithRole @role = N" + role + " , @medicalRecord_status = N" + status + ",@department_id = " + DepId + ";");
        while (rs.next()) {
            //Doc mau tin va them vao object Employee
            //Employee(float salary, int departmentID, int id, String firstName, String lastName, String sex, Date birthday, String address, String phone, String email, String password, String role)
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setLastName(rs.getString("LastName"));
            employee.setFirstName(rs.getString("FirstName"));
            employee.setPhone(rs.getString("Phone"));
            employee.setSex(rs.getString("Sex"));
            employee.setAddress(rs.getString("Address"));
            employee.setEmail(rs.getString("Email"));
            employee.setPassword(rs.getString("Password"));
            employee.setBirthday(rs.getDate("Birthday"));
            employee.setRole(rs.getString("Role"));
            employee.setDepartmentName(rs.getString("DepartmentName"));
            employee.setProcessingCount(rs.getInt("ProcessingCount"));
            list.add(employee);
        }
        //Dong ket noi database
        con.close();
        return list;
    }

    public List<Employee> selectAllDoctor(String id) throws ClassNotFoundException, SQLException {
        String searchValue = id == null ? "": ("and Account.ID ="+id);
        List<Employee> list = new ArrayList<>();
        DepartmentFacade df = new DepartmentFacade();
        List<Department> listDepartment = df.selectAll();
        //Tao ket noi database
        Connection con = DbContext.getConnection();
        //Thuc hien lenh SQL
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM account JOIN employee ON account.id = employee.id Where Account.role = 'doctor' and Account.Status='Activated' "+searchValue);
        while (rs.next()) {
            //Doc mau tin va them vao object Employee
            //Employee(float salary, int departmentID, int id, String firstName, String lastName, String sex, Date birthday, String address, String phone, String email, String password, String role)
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setLastName(rs.getString("LastName"));
            employee.setFirstName(rs.getString("FirstName"));
            employee.setPhone(rs.getString("Phone"));
            employee.setSex(rs.getString("Sex"));
            employee.setAddress(rs.getString("Address"));
            employee.setEmail(rs.getString("Email"));
            employee.setPassword(rs.getString("Password"));
            employee.setBirthday(rs.getDate("Birthday"));
            employee.setRole(rs.getString("Role"));
            employee.setSalary(rs.getFloat("Salary"));
            employee.setDepartment(Department.findDepartmentById(listDepartment, rs.getInt("DepartmentID")));
            list.add(employee);
        }
        //Dong ket noi database
        con.close();
        return list;
    }


    public List<Employee> selectAllEmployee(String id) throws ClassNotFoundException, SQLException {
        String searchValue = id == null ? "": ("and Account.ID ="+id);
        List<Employee> list = new ArrayList<>();
        DepartmentFacade df = new DepartmentFacade();
        List<Department> listDepartment = df.selectAll();
        //Tao ket noi database
        Connection con = DbContext.getConnection();
        //Thuc hien lenh SQL
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM account JOIN employee ON account.id = employee.id Where Account.role != 'doctor' and Account.Status='Activated' "+searchValue);
        while (rs.next()) {
            //Doc mau tin va them vao object Employee
            //Employee(float salary, int departmentID, int id, String firstName, String lastName, String sex, Date birthday, String address, String phone, String email, String password, String role)
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setLastName(rs.getString("LastName"));
            employee.setFirstName(rs.getString("FirstName"));
            employee.setPhone(rs.getString("Phone"));
            employee.setSex(rs.getString("Sex"));
            employee.setAddress(rs.getString("Address"));
            employee.setEmail(rs.getString("Email"));
            employee.setPassword(rs.getString("Password"));
            employee.setBirthday(rs.getDate("Birthday"));
            employee.setRole(rs.getString("Role"));
            employee.setSalary(rs.getFloat("Salary"));
            employee.setDepartment(Department.findDepartmentById(listDepartment, rs.getInt("DepartmentID")));
            list.add(employee);
        }
        //Dong ket noi database
        con.close();
        return list;
    }

    public Employee selectEmployee(int id) throws ClassNotFoundException, SQLException {
        Employee employee = new Employee();
        DepartmentFacade df = new DepartmentFacade();
        List<Department> listDepartment = df.selectAll();
        //Tao ket noi database
        Connection con = DbContext.getConnection();
        //Thuc hien lenh SQL
        PreparedStatement stm = con.prepareStatement("SELECT * FROM account JOIN employee ON account.id = employee.id Where Account.Id = ?");
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            //Doc mau tin va them vao object Employee
            //Employee(float salary, int departmentID, int id, String firstName, String lastName, String sex, Date birthday, String address, String phone, String email, String password, String role)

            employee.setId(rs.getInt("id"));
            employee.setLastName(rs.getString("LastName"));
            employee.setFirstName(rs.getString("FirstName"));
            employee.setPhone(rs.getString("Phone"));
            employee.setSex(rs.getString("Sex"));
            employee.setAddress(rs.getString("Address"));
            employee.setEmail(rs.getString("Email"));
            employee.setPassword(rs.getString("Password"));
            employee.setBirthday(rs.getDate("Birthday"));
            employee.setRole(rs.getString("Role"));
            employee.setSalary(rs.getFloat("Salary"));
            employee.setDepartmentID(rs.getInt("DepartmentID"));
        }
        //Dong ket noi database
        con.close();
        return employee;
    }

    public void update(Employee employee) throws SQLException, ClassNotFoundException {
        //Tạo connection để kết nối vào DBMS
        Connection con = DbContext.getConnection();
        //Tạo đối tượng PreparedStatement
        PreparedStatement stm = con.prepareStatement("UPDATE Account SET LastName = ?,FirstName = ?,Address = ?,Phone = ?,Birthday = ?,Sex = ?,Role = ? WHERE ID = ?;"
                + " UPDATE Employee set Salary = ?, DepartmentID = ? WHERE ID = ?;");
        stm.setString(1, employee.getLastName());
        stm.setString(2, employee.getFirstName());
        stm.setString(3, employee.getAddress());
        stm.setString(4, employee.getPhone());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        stm.setString(5, sdf.format(employee.getBirthday()));

        stm.setString(6, employee.getSex());
        stm.setString(7, employee.getRole());
        stm.setInt(8, employee.getId());
        stm.setFloat(9, employee.getSalary());
        stm.setInt(10, employee.getDepartmentID());
        stm.setInt(11, employee.getId());
        //Thực thi lệnh sql
        int count = stm.executeUpdate();
        //Đóng kết nối
        con.close();
    }
}
