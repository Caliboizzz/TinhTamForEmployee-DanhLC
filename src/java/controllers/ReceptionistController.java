/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import model.Account;
import model.Department;
import dao.DepartmentFacade;
import model.Employee;
import dao.EmployeeFacade;
import model.MedicalRecord;
import dao.MedicalRecordFacade;
import model.Patient;
import dao.PatientFacade;
import dao.PrescriptionFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Prescription;

/**
 *
 * @author Danh
 */
@WebServlet(name = "ReceptionistController", urlPatterns = {"/receptionist"})
public class ReceptionistController extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String controller = (String) request.getAttribute("controller");
        String action = (String) request.getAttribute("action");
        HttpSession session = request.getSession();
        switch (action) {
            case "index":
                //code xu ly
                index(request, response);
                break;
            case "registerHandler":
                //code xu ly
                registerHandler(request, response);
                break;
            case "transfer":
                //code xu ly
                transfer(request, response);
                break;
            case "details":
                //code xu ly
                details(session, request, response);
                break;
            case "info":
                info(session, request, response);
                break;
            case "transferHandler":
                //code xu ly
                transferHandler(session, request, response);
                break;
            case "update":
                update(session, request, response);
                break;
            case "updateHandler":
                updateHandler(session, request, response);
                break;
        }
    }

    protected void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            PatientFacade pf = new PatientFacade();
            String op = request.getParameter("op") == null ? "" : request.getParameter("op");
            String searchKey = request.getParameter("searchKey");
            List<Patient> list = null;
            int page = 1;
            int pageSize = 10;
            String sPage = request.getParameter("page");
            if (sPage != null) {
                page = Integer.parseInt(sPage);
            }
            int count = 0;
            int numOfPages = 0;
            switch (op) {
                case "search":
                    if (!searchKey.equals("")) {
                        list = pf.select(searchKey);
                    } else {
                        list = pf.selectAll(page, pageSize);
                        count = pf.count();
                    }
                    break;
                case "clear":
                    searchKey = null;
                    list = pf.selectAll(page, pageSize);
                    count = pf.count();
                    break;
                default:
                    list = pf.selectAll(page, pageSize);
                    count = pf.count();
                    break;
            }
            numOfPages = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
            request.setAttribute("page", page);
            request.setAttribute("numOfPages", numOfPages);
            request.setAttribute("searchKey", searchKey);
            request.setAttribute("list", list);
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

    protected void registerHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date birthday = sdf.parse(request.getParameter("birthday"));
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String healthInsurance = request.getParameter("healthInsurance") == null ? "" : request.getParameter("healthInsurance");
            String sex = request.getParameter("sex");
            //System.out.print("value test" + healthInsurance);
            //Patient(String healthInsurance, String firstName, String lastName, String sex, Date birthday, String address, String phone, String email, String password, String role)
            Patient patient = new Patient(healthInsurance, firstName, lastName, sex, birthday, address, phone, "", "", "patient");
            PatientFacade pf = new PatientFacade();
            int idNewPatient = pf.register(patient);
            response.sendRedirect(request.getContextPath() + "/receptionist/transfer.do?patientId=" + idNewPatient);
        } catch (Exception ex) {
            //In thong tin chi tiet ve exception trong cua so Output de nguoi lap trinh xem
            ex.printStackTrace();
            //Chuyen den trang thong bao loi
            request.setAttribute("message", ex.getMessage());
            request.setAttribute("action", "error");
            request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
        }
    }

    protected void transfer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            EmployeeFacade ef = new EmployeeFacade();
            int patientId = Integer.parseInt(request.getParameter("patientId"));
            String depId = request.getParameter("depId") == null ? "null" : request.getParameter("depId");
            List<Employee> list = ef.selectAll("doctor", "WaitingDoctor", depId);

            DepartmentFacade df = new DepartmentFacade();
            List<Department> listDepartment = df.selectAllofDoctor();
            request.setAttribute("patientId", patientId);
            request.setAttribute("depId", depId);
            request.setAttribute("listDepartment", listDepartment);
            request.setAttribute("list", list);
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

    protected void details(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        try {
            int patientId = Integer.parseInt(request.getParameter("patientId"));
            PatientFacade pf = new PatientFacade();
            Patient patient = new Patient();
            MedicalRecordFacade mrf = new MedicalRecordFacade();
            String sPage = request.getParameter("page");
            int page = 1;
            int pageSize = 5;
            if (sPage != null) {
                page = Integer.parseInt(sPage);
            }
            int count = pf.countMRWithPatientId(patientId);
            int numOfPages = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
            List<MedicalRecord> list = mrf.getAllDoneWithPatientIdPaging(patientId, "Done", page, pageSize);
            patient = pf.selectPatient(patientId);
            request.setAttribute("patient", patient);
            request.setAttribute("list", list);
            request.setAttribute("numOfPages", numOfPages);
            request.setAttribute("page", page);
            request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("message", ex.getMessage());
            request.setAttribute("action", "error");
            request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
        }
    }

    protected void info(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        try {
            int mrId = Integer.parseInt(request.getParameter("mrId"));
            MedicalRecordFacade mrf = new MedicalRecordFacade();
            MedicalRecord mr = new MedicalRecord();
            mr = mrf.getDetailsWithId(mrId);
            PrescriptionFacade pf = new PrescriptionFacade();
            List<Prescription> list = pf.getPrescriptionByMr(mrId);
            request.setAttribute("mr", mr);
            request.setAttribute("list", list);
            request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("message", ex.getMessage());
            request.setAttribute("action", "error");
            request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
        }
    }

    protected void transferHandler(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Account account = (Account) session.getAttribute("account");
            int receptionistId = account.getId();
            //Lấy id bác sĩ và bệnh nhân
            int doctorId = Integer.parseInt(request.getParameter("doctorId"));
            int patientId = Integer.parseInt(request.getParameter("patientId"));
            String Status = "WaitingDoctor";
            MedicalRecordFacade mf = new MedicalRecordFacade();
            MedicalRecord medicalRecord = new MedicalRecord(Status, doctorId, patientId, receptionistId);
            mf.create(medicalRecord);
            response.sendRedirect(request.getContextPath() + "/receptionist/index.do");
        } catch (Exception ex) {
            //In thong tin chi tiet ve exception trong cua so Output de nguoi lap trinh xem
            ex.printStackTrace();
            //Chuyen den trang thong bao loi
            request.setAttribute("message", ex.getMessage());
            request.setAttribute("action", "error");
            request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
        }
    }

    protected void update(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int patientId = Integer.parseInt(request.getParameter("patientId"));
            PatientFacade pf = new PatientFacade();
            Patient patient = pf.selectPatient(patientId);
            request.setAttribute("patient", patient);
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

    protected void updateHandler(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int patientId = Integer.parseInt(request.getParameter("patientId"));
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String healthInsurance = request.getParameter("healthInsurance");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date birthday = sdf.parse(request.getParameter("birthday"));
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String sex = request.getParameter("sex");

            //public Patient(String healthInsurance, int id, String firstName, String lastName, String sex, Date birthday, String address, String phone, String email, String password, String role) 
            Patient patient = new Patient(healthInsurance, patientId, firstName, lastName, sex, birthday, address, phone, "", "", "patient");

            PatientFacade pf = new PatientFacade();
            pf.update(patient);

            response.sendRedirect(request.getContextPath() + "/receptionist/index.do");
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReceptionistController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReceptionistController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReceptionistController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReceptionistController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
