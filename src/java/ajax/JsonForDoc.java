/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajax;

import model.MedicalRecord;
import model.Medicine;
import java.util.List;

/**
 *
 * @author Danh
 */
public class JsonForDoc {
    List<MedicalRecord> listMR;
    List<Medicine> listM;

    public JsonForDoc() {
    }

    public JsonForDoc(List<MedicalRecord> listMR, List<Medicine> listM) {
        this.listMR = listMR;
        this.listM = listM;
    }

    public List<MedicalRecord> getListMR() {
        return listMR;
    }

    public void setListMR(List<MedicalRecord> listMR) {
        this.listMR = listMR;
    }

    public List<Medicine> getListM() {
        return listM;
    }

    public void setListM(List<Medicine> listM) {
        this.listM = listM;
    }
    
    
}
