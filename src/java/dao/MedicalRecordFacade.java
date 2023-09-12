/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Prescription;
import model.MedicalRecord;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/**
 *
 * @author ad
 */
public class MedicalRecordFacade {

    public List<MedicalRecord> selectAll() throws ClassNotFoundException, SQLException {
        List<MedicalRecord> list = new ArrayList<>();
        //Tao ket noi database
        Connection con = DbContext.getConnection();
        //Thuc hien lenh SQL
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select * from MedicalRecord");
        while (rs.next()) {
            //Doc mau tin va them vao object account
            MedicalRecord medicalrecord = new MedicalRecord();
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
            list.add(medicalrecord);
        }
        //Dong ket noi database
        con.close();
        return list;
    }

    public List<Float> IncomeInYear(int year) throws ClassNotFoundException, SQLException {
        List<Float> list = null;
        Connection con = DbContext.getConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("EXEC IncomeYearByMonth @year = " + year + ";");
        list = new ArrayList<>();
        while (rs.next()) {
            //Doc mau tin va them vao object account
            list.add(rs.getFloat("Income"));
        }
        con.close();

        return list;
    }

    public List<MedicalRecord> getAllWithStatus(int DocId, String status) throws ClassNotFoundException, SQLException {
        List<MedicalRecord> list = null;
        Connection con = DbContext.getConnection();
        status = "'" + status + "'";
        String GetDocId = DocId == 0 ? "null" : Integer.toString(DocId);
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("EXEC SelectAllMRWithStatusAndIDDoc @doc_id =" + GetDocId + ", @status = " + status + ", @patient_id = null;");
        list = new ArrayList<>();
        while (rs.next()) {
            //Doc mau tin va them vao object account
            MedicalRecord medicalrecord = new MedicalRecord();
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
            list.add(medicalrecord);
        }
        con.close();

        return list;
    }

    public List<MedicalRecord> getAllWithStatusPatient(int PatientId, String status) throws ClassNotFoundException, SQLException {
        List<MedicalRecord> list = null;
        Connection con = DbContext.getConnection();
        status = "'" + status + "'";
        String GetPatientId = PatientId == 0 ? "null" : Integer.toString(PatientId);
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("EXEC SelectAllMRWithStatusAndIDDoc @doc_id =null, @status = " + status + ", @patient_id = " + GetPatientId + ";");
        list = new ArrayList<>();
        while (rs.next()) {
            //Doc mau tin va them vao object account
            MedicalRecord medicalrecord = new MedicalRecord();
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
            list.add(medicalrecord);
        }
        con.close();
        return list;
    }

    public List<MedicalRecord> getAllDoneWithDateTime(String year, String month, String day, String status) throws ClassNotFoundException, SQLException {
        List<MedicalRecord> list = null;
        Connection con = DbContext.getConnection();
        year = year == null ? "null" : year;
        month = month == null ? "null" : month;
        day = day == null ? "null" : day;
        status = "'" + status + "'";

        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("EXEC SelectAllMRWithDateTime @year = " + year + ", @month = " + month + ",@day=" + day + ",@status=" + status + ";");
        list = new ArrayList<>();
        while (rs.next()) {
            //Doc mau tin va them vao object account
            MedicalRecord medicalrecord = new MedicalRecord();
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
            list.add(medicalrecord);
        }
        con.close();
        return list;
    }

    public List<MedicalRecord> getAllDoneWithDateTime(String year, String month, String day, String status, int index, int pageSize) throws ClassNotFoundException, SQLException {
        List<MedicalRecord> list = null;
        Connection con = DbContext.getConnection();
        year = year == null ? "null" : year;
        month = month == null ? "null" : month;
        day = day == null ? "null" : day;
        status = "'" + status + "'";
        String sql = String.format("EXEC PagingMR @year =%s, @month = %s, @day= %s, @status = " + status + ", @page = %s, @pageSize = %s;", year, month, day, (index - 1) * pageSize, pageSize);
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        list = new ArrayList<>();
        while (rs.next()) {
            //Doc mau tin va them vao object account
            MedicalRecord medicalrecord = new MedicalRecord();
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
            list.add(medicalrecord);
        }
        con.close();
        return list;
    }

    public List<MedicalRecord> getAllDoneWithPatientIdPaging(int patientId, String status, int page, int pageSize) throws ClassNotFoundException, SQLException {
        List<MedicalRecord> list = null;
        Connection con = DbContext.getConnection();
        status = "'" + status + "'";
        String sql = String.format("EXEC SelectAllMRWithStatusAndIDDocPaging @doc_id = null, @patient_id = " + patientId + ", @status = " + status + ", @page = %s, @pageSize = %s;", (page - 1) * pageSize, pageSize);
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        list = new ArrayList<>();
        while (rs.next()) {
            //Doc mau tin va them vao object account
            MedicalRecord medicalrecord = new MedicalRecord();
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
            list.add(medicalrecord);
        }
        con.close();
        return list;
    }

    public List<MedicalRecord> getAllDoneWithDateTime0(String year, String month, String day, String status) throws ClassNotFoundException, SQLException {
        List<MedicalRecord> list = null;
        Connection con = DbContext.getConnection();
        year = year == null ? "null" : year;
        month = month == null ? "null" : month;
        day = day == null ? "null" : day;
        status = "'" + status + "'";
        String sql = String.format("EXEC SelectAllMRWithDateTime @year =" + year + ", @month = " + month + ", @day= " + day + ", @status = " + status + ";");
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        list = new ArrayList<>();
        while (rs.next()) {
            //Doc mau tin va them vao object account
            MedicalRecord medicalrecord = new MedicalRecord();
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
            list.add(medicalrecord);
        }
        con.close();
        return list;
    }

    public MedicalRecord getDetailsWithId(int mrId) throws ClassNotFoundException, SQLException {
        Connection con = DbContext.getConnection();
        String sql = String.format("EXEC readOnlyMr @mr_id = " + mrId + "");
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        MedicalRecord medicalrecord = new MedicalRecord();
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

    public void checkout(int id, int cashierId) throws ClassNotFoundException, SQLException {
        Connection con = DbContext.getConnection();
        PreparedStatement stm = con.prepareStatement("update MedicalRecord set Status='Done',CashierID=? where id=?");
        stm.setInt(1, cashierId);
        stm.setInt(2, id);
        int count = stm.executeUpdate();
        con.close();
    }

    public int count(String year, String month, String day) throws ClassNotFoundException, SQLException {
        int n = 0;
        //Tao ket noi database
        Connection con = DbContext.getConnection();
        year = year == null ? "null" : year;
        month = month == null ? "null" : month;
        day = day == null ? "null" : day;
        //Thuc hien lenh SQL
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("EXEC CountPage @status='done', @year = " + year + ", @month = " + month + ", @day= " + day + ";");
        if (rs.next()) {
            n = rs.getInt(1);
        }
        //Dong ket noi database
        con.close();
        return n;
    }

    public void create(MedicalRecord medicalRecord) throws ClassNotFoundException, SQLException {
        // Tạo đối tượng Calendar và cài đặt múi giờ cho Việt Nam
        Calendar calendar = Calendar.getInstance();
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Ho_Chi_Minh");
        calendar.setTimeZone(timeZone);
        // Lấy ngày giờ hiện tại ở múi giờ Việt Nam
        java.util.Date nowDateTime = calendar.getTime();
//tao ket noi db
        Connection con = DbContext.getConnection();
        //truy xuat co tham so
        PreparedStatement stm = con.prepareStatement("insert into MedicalRecord ([DateCreated], [Status], [DoctorID], [PatientID], [ReceptionistID]) values( ?, ?, ?, ?, ?)");
        //cung cap gia tri cho tham so
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        stm.setString(1, sdf.format(nowDateTime));
        stm.setString(2, medicalRecord.getStatus());
        stm.setInt(3, medicalRecord.getDoctorId());
        stm.setInt(4, medicalRecord.getPatientId());
        stm.setInt(5, medicalRecord.getReceptionistId());

        //thuc thi cau lenh sql
        int count = stm.executeUpdate();
        //dong ket noi db
        con.close();
    }

    public void examine(List<Prescription> listPrescription, String reCheckDate, String note, float totalPrice, int mrId) throws ClassNotFoundException, SQLException {
        //Tao ket noi database
        Connection con = DbContext.getConnection();
        MedicineFacade mf = new MedicineFacade();
        try {
            //Bắt đầu transaction
            con.setAutoCommit(false);
            //Insert data vào table OrderHeader
            PreparedStatement stm = con.prepareStatement("update MedicalRecord set Status='WaitingCashier',ReCheckDate=" + reCheckDate + ",Note=?,TotalPrice=? where id=?");
            //stm.setString(1, reCheckDate);
            stm.setString(1, note);
            stm.setFloat(2, totalPrice);
            stm.setInt(3, mrId);
            int count = stm.executeUpdate();

            //Insert data vào table OrderDetail
            stm = con.prepareStatement("insert into Prescription values(?, ?, ?, ?, ?, ?, ?, ?)");
            for (Prescription prescription : listPrescription) {
                stm.setString(1, prescription.getDosageMorning());
                stm.setString(2, prescription.getDosageAfternoon());
                stm.setString(3, prescription.getDosageEvening());
                stm.setString(4, prescription.getDosageNight());
                stm.setString(5, prescription.getNote());
                stm.setInt(6, prescription.getQuantity());
                stm.setInt(7, prescription.getMedicalRecordId());
                stm.setInt(8, prescription.getMedicineId());
                mf.updateInstock(prescription.getMedicineId(), prescription.getQuantity());
                count = stm.executeUpdate();

            }
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

}
