/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import ajax.JsonForDoc;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Account;
import model.MedicalRecord;
import dao.MedicalRecordFacade;
import model.Medicine;
import dao.MedicineFacade;
import model.Prescription;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Danh
 */
@WebServlet(name = "DoctorController", urlPatterns = {"/doctor"})
public class DoctorController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String controller = (String) request.getAttribute("controller");
        String action = (String) request.getAttribute("action");
        HttpSession session = request.getSession();

        switch (action) {
            case "index":
                //code xu ly
                index(session, request, response);
                break;
            case "ajaxController":
                //code xu ly
                ajaxController(session, request, response);
                break;
        }
    }

    protected void index(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            Account acc = (Account) session.getAttribute("account");
            request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
        } catch (Exception ex) {
            //In thong tin chi tiet ve exception trong cua so Output de nguoi lap trinh xem
            ex.printStackTrace();
            //Chuyen den trang thong bao loi
            request.setAttribute("message", ex.getMessage());
            request.setAttribute("action", "error");
            request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
        }
    }

    protected void ajaxController(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String op = request.getParameter("op");
            PrintWriter out = response.getWriter();
            Account acc = (Account) session.getAttribute("account");
            MedicalRecordFacade mrf = new MedicalRecordFacade();
            List<MedicalRecord> list = mrf.getAllWithStatus(acc.getId(), "WaitingDoctor");
            MedicineFacade mf = new MedicineFacade();
            List<Medicine> listMedicine = mf.selectAll();
            Gson gson = new Gson();
            switch (op) {
                case "getMedicalRecord":

                    JsonForDoc jsonForDoc = new JsonForDoc(list, listMedicine);

                    String json = gson.toJson(jsonForDoc);
                    out.println(json);
                    break;
                case "sendMedicalRecord":
                    Gson gsonArray = new GsonBuilder().setPrettyPrinting().create();
                    int mrId = Integer.parseInt(request.getParameter("mrId"));
                    String diagnose = request.getParameter("diagnose");
                    String reCheckDate = "".equals(request.getParameter("reCheckDate")) ? "NULL" : "'" + request.getParameter("reCheckDate") + "'";
                    Integer[] medIds = gsonArray.fromJson(request.getParameter("medIds"), Integer[].class);
                    Integer[] quantities = gsonArray.fromJson(request.getParameter("quantities"), Integer[].class);
                    String[] mornings = gsonArray.fromJson(request.getParameter("mornings"), String[].class);
                    String[] afternoons = gsonArray.fromJson(request.getParameter("afternoons"), String[].class);
                    String[] evenings = gsonArray.fromJson(request.getParameter("evenings"), String[].class);
                    String[] nights = gsonArray.fromJson(request.getParameter("nights"), String[].class);
                    String[] notes = gsonArray.fromJson(request.getParameter("notes"), String[].class);
                    float[] prices = gsonArray.fromJson(request.getParameter("prices"), float[].class);

                    List<Prescription> listPrescription = new ArrayList<>();
                    float totalPrice = 0;
                    for (int i = 0; i < medIds.length; i++) {
                        // public Prescription(String DosageMorning, String DosageAfternoon, String DosageEvening, String DosageNight, String note, int quantity, int medicalRecordId, int medicineId)
                        listPrescription.add(new Prescription(mornings[i], afternoons[i], evenings[i], nights[i], notes[i], quantities[i], mrId, medIds[i]));
                        totalPrice += prices[i] * quantities[i];

                    }

                    //String test = gson.toJson(listPrescription);
                    //System.out.println("Test: " + diagnose);
                    mrf.examine(listPrescription, reCheckDate, diagnose,totalPrice, mrId);
                    break;
            }

        } catch (Exception ex) {
            //In thong tin chi tiet ve exception trong cua so Output de nguoi lap trinh xem
            ex.printStackTrace();
            //Chuyen den trang thong bao loi
            request.setAttribute("message", ex.getMessage());
            request.setAttribute("action", "error");
            request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
