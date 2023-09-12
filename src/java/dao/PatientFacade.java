/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Patient;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.MedicalRecord;

/**
 *
 * @author Danh
 */
public class PatientFacade {

    public int register(Patient patient) throws ClassNotFoundException, NoSuchAlgorithmException, SQLException {
        //Tao ket noi database
        Connection con = DbContext.getConnection();

        try {
            //Bắt đầu transaction
            con.setAutoCommit(false);
            //Insert data vào table Account
            PreparedStatement stm = con.prepareStatement("insert into Account values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, patient.getEmail());
            stm.setString(2, patient.getPassword());
            stm.setString(3, patient.getLastName());
            stm.setString(4, patient.getFirstName());
            stm.setString(5, patient.getAddress());
            stm.setString(6, patient.getPhone());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            stm.setString(7, sdf.format(patient.getBirthday()));

            stm.setString(8, patient.getSex());
            stm.setString(9, patient.getRole());
            stm.setString(10,"NULL");
            int count = stm.executeUpdate();

            //Lấy account id được phát sinh tự động
            try (ResultSet rs = stm.getGeneratedKeys()) {
                if (rs.next()) {
                    patient.setId(rs.getInt(1));
                } else {
                    throw new SQLException("Inserting account failed.");
                }
            }

            //Insert data vào table Patient
            stm = con.prepareStatement("insert into Patient values(?, ?)");
            stm.setInt(1, patient.getId());
            stm.setString(2, patient.getHealthInsurance());
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
        return patient.getId();
    }

    public List<Patient> selectAll() throws ClassNotFoundException, SQLException {
        List<Patient> list = new ArrayList<>();
        //Tao ket noi database
        Connection con = DbContext.getConnection();
        //Thuc hien lenh SQL
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select * from Patient INNER JOIN Account ON Patient.ID = Account.id");
        while (rs.next()) {
            //Doc mau tin va them vao object Patient
            //Patient(String healthInsurance, int id, String firstName, String lastName, String sex, Date birthday, String address, String phone, String email, String password, String role)
            Patient patient = new Patient();
            patient.setId(rs.getInt("id"));
            patient.setHealthInsurance(rs.getString("HealthInsurance"));
            patient.setLastName(rs.getString("LastName"));
            patient.setFirstName(rs.getString("FirstName"));
            patient.setPhone(rs.getString("Phone"));
            patient.setSex(rs.getString("Sex"));
            patient.setAddress(rs.getString("Address"));
            patient.setEmail(rs.getString("Email"));
            patient.setPassword(rs.getString("Password"));
            patient.setBirthday(rs.getDate("Birthday"));
            patient.setRole(rs.getString("Role"));
            list.add(patient);
        }
        //Dong ket noi database
        con.close();
        return list;
    }

    public List<Patient> selectAll(int index, int pageSize) throws ClassNotFoundException, SQLException {
        List<Patient> list = new ArrayList<>();
        //Tao ket noi database
        Connection con = DbContext.getConnection();
        //Thuc hien lenh SQL
        String sql = String.format("select * from Patient INNER JOIN Account ON Patient.ID = Account.id order by Patient.ID offset %d rows fetch next %d rows only", (index - 1) * pageSize, pageSize);
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while (rs.next()) {
            //Doc mau tin va them vao object Patient
            //Patient(String healthInsurance, int id, String firstName, String lastName, String sex, Date birthday, String address, String phone, String email, String password, String role)
            Patient patient = new Patient();
            patient.setId(rs.getInt("id"));
            patient.setHealthInsurance(rs.getString("HealthInsurance"));
            patient.setLastName(rs.getString("LastName"));
            patient.setFirstName(rs.getString("FirstName"));
            patient.setPhone(rs.getString("Phone"));
            patient.setSex(rs.getString("Sex"));
            patient.setAddress(rs.getString("Address"));
            patient.setEmail(rs.getString("Email"));
            patient.setPassword(rs.getString("Password"));
            patient.setBirthday(rs.getDate("Birthday"));
            patient.setRole(rs.getString("Role"));
            list.add(patient);
        }
        //Dong ket noi database
        con.close();
        return list;
    }

    public List<Patient> select(String phone) throws ClassNotFoundException, SQLException {
        List<Patient> list = new ArrayList<>();
        phone = "'" + phone + "'";
        //Tao ket noi database
        Connection con = DbContext.getConnection();
        //Thuc hien lenh SQL
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select * from Patient INNER JOIN Account ON Patient.ID = Account.id where Account.Phone=" + phone);
        while (rs.next()) {
            //Doc mau tin va them vao object Patient
            //Patient(String healthInsurance, int id, String firstName, String lastName, String sex, Date birthday, String address, String phone, String email, String password, String role)
            Patient patient = new Patient();
            patient.setId(rs.getInt("id"));
            patient.setHealthInsurance(rs.getString("HealthInsurance"));
            patient.setLastName(rs.getString("LastName"));
            patient.setFirstName(rs.getString("FirstName"));
            patient.setPhone(rs.getString("Phone"));
            patient.setSex(rs.getString("Sex"));
            patient.setAddress(rs.getString("Address"));
            patient.setEmail(rs.getString("Email"));
            patient.setPassword(rs.getString("Password"));
            patient.setBirthday(rs.getDate("Birthday"));
            patient.setRole(rs.getString("Role"));
            list.add(patient);
        }
        //Dong ket noi database
        con.close();
        return list;
    }

    public int count() throws ClassNotFoundException, SQLException {
        int n = 0;
        //Tao ket noi database
        Connection con = DbContext.getConnection();
        //Thuc hien lenh SQL
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select count(*) from Patient");
        if (rs.next()) {
            n = rs.getInt(1);
        }
        //Dong ket noi database
        con.close();
        return n;
    }
    
    public int countMRWithPatientId(int patientId) throws ClassNotFoundException, SQLException {
        int n = 0;
        //Tao ket noi database
        Connection con = DbContext.getConnection();
        //Thuc hien lenh SQL
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select count(*) from MedicalRecord where PatientID = "+ patientId +"");
        if (rs.next()) {
            n = rs.getInt(1);
        }
        //Dong ket noi database
        con.close();
        return n;
    }
    
    public Patient selectPatient(int id) throws ClassNotFoundException, SQLException {
        Patient patient = new Patient();
        //Tao ket noi database
        Connection con = DbContext.getConnection();
        //Thuc hien lenh SQL
        PreparedStatement stm = con.prepareStatement("SELECT * FROM account JOIN patient ON account.id = patient.id Where Account.Id = ?");
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            //Doc mau tin va them vao object Employee
            //Employee(float salary, int departmentID, int id, String firstName, String lastName, String sex, Date birthday, String address, String phone, String email, String password, String role)

            patient.setId(rs.getInt("id"));
            patient.setLastName(rs.getString("LastName"));
            patient.setFirstName(rs.getString("FirstName"));
            patient.setPhone(rs.getString("Phone"));
            patient.setSex(rs.getString("Sex"));
            patient.setHealthInsurance(rs.getString("HealthInsurance"));
            patient.setAddress(rs.getString("Address"));
            patient.setEmail(rs.getString("Email"));
            patient.setPassword(rs.getString("Password"));
            patient.setBirthday(rs.getDate("Birthday"));
            patient.setRole(rs.getString("Role"));
        }
        //Dong ket noi database
        con.close();
        return patient;
    }
    
    public MedicalRecord getMrWithPatientId (int id) throws ClassNotFoundException, SQLException {
        MedicalRecord medicalrecord = new MedicalRecord();
        //Tao ket noi database
        Connection con = DbContext.getConnection();
        PreparedStatement stm = con.prepareStatement("SELECT * FROM MedicalRecord WHERE PatientID = ?");
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            medicalrecord.setId(rs.getInt("id"));
            medicalrecord.setDateCreated(rs.getDate("dateCreated"));
            medicalrecord.setStatus(rs.getString("status"));
            medicalrecord.setNote(rs.getString("note"));
            medicalrecord.setReCheckDate(rs.getDate("reCheckDate"));
            medicalrecord.setTotalPrice(rs.getFloat("totalPrice"));
            medicalrecord.setDoctorId(rs.getInt("doctorId"));
            medicalrecord.setPatientId(rs.getInt("patientId"));
            medicalrecord.setCashierId(rs.getInt("cashierId"));
            medicalrecord.setReceptionistId(rs.getInt("receptionistId"));
            medicalrecord.setCashierName(rs.getString("CashierName"));
            medicalrecord.setReceptionistName(rs.getString("ReceptionistName"));
            medicalrecord.setDoctorName(rs.getString("DoctorName"));
            medicalrecord.setPatientName(rs.getString("PatientName"));
        }
        //Doc mau tin va them vao object account
        con.close();
        return medicalrecord;
    }
    
    public void update(Patient patient) throws SQLException, ClassNotFoundException {
        //Tạo connection để kết nối vào DBMS
        Connection con = DbContext.getConnection();
        //Tạo đối tượng PreparedStatement
        PreparedStatement stm = con.prepareStatement("UPDATE Account SET LastName = ?,FirstName = ?,Address = ?,Phone = ?,Birthday = ?,Sex = ? WHERE ID = ?;"
                + " UPDATE Patient set HealthInsurance = ? WHERE ID = ?;");
        stm.setString(1, patient.getLastName());
        stm.setString(2, patient.getFirstName());
        stm.setString(3, patient.getAddress());
        stm.setString(4, patient.getPhone());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        stm.setString(5, sdf.format(patient.getBirthday()));

        stm.setString(6, patient.getSex());
        stm.setInt(7, patient.getId());

        stm.setString(8, patient.getHealthInsurance());
        stm.setInt(9, patient.getId());
        //Thực thi lệnh sql
        int count = stm.executeUpdate();
        //Đóng kết nối
        con.close();
    }
}
