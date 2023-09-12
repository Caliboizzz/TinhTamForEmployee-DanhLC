/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Medicine;
import model.Prescription;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ad
 */
public class PrescriptionFacade {

    public List<Prescription> getPrescriptionByMr(int mrId) throws ClassNotFoundException, SQLException {
        List<Prescription> list = null;
        list = new ArrayList<>();
        MedicineFacade mf = new MedicineFacade();
        List<Medicine> listMedicine = mf.selectAll();
        Connection con = DbContext.getConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("EXEC getPrescription @mr_id = " + mrId + ";");
        while (rs.next()) {
            Prescription prescription = new Prescription();
            prescription.setId(rs.getInt("id"));
            prescription.setDosageMorning(rs.getString("DosageMorning"));
            prescription.setDosageAfternoon(rs.getString("DosageAfternoon"));
            prescription.setDosageEvening(rs.getString("DosageEvening"));
            prescription.setDosageNight(rs.getString("DosageNight"));
            prescription.setNote(rs.getString("note"));
            prescription.setQuantity(rs.getInt("quantity"));
            prescription.setMedicalRecordId(rs.getInt("medicalRecordId"));
            prescription.setMedicineId(rs.getInt("medicineId"));
            prescription.setMedicine(Medicine.findMedicineById(listMedicine, (rs.getInt("medicineId"))));
            list.add(prescription);
        }
        con.close();
        return list;
    }

}
