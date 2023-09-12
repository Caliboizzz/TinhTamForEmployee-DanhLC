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
import dao.PrescriptionFacade;
import model.Medicine;
import dao.MedicineFacade;
import model.Prescription;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "CashierController", urlPatterns = {"/cashier"})
public class CashierController extends HttpServlet {

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
                index(session, request, response);
            case "income":
                income(session, request, response);
                break;
            case "chart":
                chart(session, request, response);
                break;
            case "details":
                details(session, request, response);
                break;
            case "info":
                info(session, request, response);
                break;
            case "checkout":
                checkout(session, request, response);
                break;
            case "ajaxController":
                //code xu ly
                ajaxController(session, request, response);
                break;
        }
    }

    protected void index(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Account acc = (Account) session.getAttribute("account");
            MedicalRecordFacade mrf = new MedicalRecordFacade();
            List<MedicalRecord> list = mrf.getAllDoneWithDateTime(null, null, null, "WaitingCashier");
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
            int mrId = Integer.parseInt(request.getParameter("mrId"));
            Account acc = (Account) session.getAttribute("account");
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
    
    protected void info(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        try {
            int mrId = Integer.parseInt(request.getParameter("mrId"));
            Account acc = (Account) session.getAttribute("account");
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

    protected void checkout(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        try {
            int id = Integer.parseInt(request.getParameter("mrId"));
            Account acc = (Account) session.getAttribute("account");
            MedicalRecordFacade mrf = new MedicalRecordFacade();
            mrf.checkout(id, acc.getId());
            response.sendRedirect(request.getContextPath() + "/cashier/index.do");
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("message", ex.getMessage());
            request.setAttribute("action", "error");
            request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
        }
    }

    protected void income(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Account acc = (Account) session.getAttribute("account");
            MedicalRecordFacade mrf = new MedicalRecordFacade();
            List<MedicalRecord> list = null;
            List<MedicalRecord> listPrice = null;
            String date = request.getParameter("date");
            String sPage = request.getParameter("page");
            int page = 1;
            int pageSize = 15;
            if (sPage != null) {
                page = Integer.parseInt(sPage);
            }
            int count = 0;
            int numOfPages = 0;
            if (date != "") {
                String op = request.getParameter("op") == null ? "" : request.getParameter("op");
                switch (op) {
                    case "day":
                        LocalDate ld = LocalDate.parse(date);
                        int d = ld.getDayOfMonth();
                        String day = String.valueOf(d);
                        int m0 = ld.getMonthValue();
                        String month0 = String.valueOf(m0);
                        int y0 = ld.getYear();
                        String year0 = String.valueOf(y0);
                        list = mrf.getAllDoneWithDateTime(year0, month0, day, "Done", page, pageSize);
                        listPrice = mrf.getAllDoneWithDateTime0(year0, month0, day, "Done");
                        count = mrf.count(year0, month0, day);
                        session.setAttribute("op", op);
                        session.setAttribute("date", date);
                        break;
                    case "month":
                        LocalDate lm = LocalDate.parse(date);
                        int m = lm.getMonthValue();
                        String month = String.valueOf(m);
                        list = mrf.getAllDoneWithDateTime(null, month, null, "Done", page, pageSize);
                        listPrice = mrf.getAllDoneWithDateTime0(null, month, null, "Done");
                        count = mrf.count(null, month, null);
                        session.setAttribute("op", op);
                        session.setAttribute("date", date);
                        break;
                    case "year":
                        LocalDate ly = LocalDate.parse(date);
                        int y = ly.getYear();
                        String year = String.valueOf(y);
                        list = mrf.getAllDoneWithDateTime(year, null, null, "Done", page, pageSize);
                        listPrice = mrf.getAllDoneWithDateTime0(year, null, null, "Done");
                        count = mrf.count(year, null, null);
                        session.setAttribute("op", op);
                        session.setAttribute("date", date);
                        break;
                    default:
                        list = mrf.getAllDoneWithDateTime(null, null, null, "Done", page, pageSize);
                        listPrice = mrf.getAllDoneWithDateTime0(null, null, null, "Done");
                        count = mrf.count(null, null, null);
                        session.setAttribute("op", null);
                        session.setAttribute("date", null);
                        break;
                }
            } else {
                list = mrf.getAllDoneWithDateTime(null, null, null, "Done", page, pageSize);
                listPrice = mrf.getAllDoneWithDateTime0(null, null, null, "Done");
                count = mrf.count(null, null, null);
            }
            float total = 0;
            for (MedicalRecord mr : listPrice) {
                total += mr.getTotalPrice();
            }

            numOfPages = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
            request.setAttribute("page", page);
            request.setAttribute("numOfPages", numOfPages);
            request.setAttribute("total", total);
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

    protected void chart(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Account acc = (Account) session.getAttribute("account");
            MedicalRecordFacade mrf = new MedicalRecordFacade();
            List<MedicalRecord> listPrice = null;
            String year = request.getParameter("year") == null ? Integer.toString(Year.now().getValue()) : request.getParameter("year");

            listPrice = mrf.getAllDoneWithDateTime0(year, null, null, "Done");
            List<Float> dataChart = mrf.IncomeInYear(Integer.parseInt(year));
            float total = 0;
            for (MedicalRecord mr : listPrice) {
                total += mr.getTotalPrice();
            }
            request.setAttribute("year", year);
            request.setAttribute("dataChart", dataChart);
            request.setAttribute("total", total);
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
            List<MedicalRecord> list = mrf.getAllWithStatus(0, "WaitingCashier");
            Gson gson = new Gson();
            switch (op) {
                case "getMedicalRecord":
                    String json = gson.toJson(list);
                    out.println(json);
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CashierController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CashierController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CashierController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CashierController.class.getName()).log(Level.SEVERE, null, ex);
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
