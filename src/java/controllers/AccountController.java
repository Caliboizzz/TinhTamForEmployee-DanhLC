/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import model.Account;
import dao.AccountFacade;
import model.Department;
import dao.DepartmentFacade;
import model.Employee;
import dao.EmployeeFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.Hash;

/**
 *
 * @author Danh
 */
@WebServlet(name = "AccountController", urlPatterns = {"/account"})
public class AccountController extends HttpServlet {

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
            case "login":
                login(session, request, response);
                break;
            case "logout":
                logout(session, request, response);
                break;
            case "register":
                register(request, response);
                break;
            case "registerHandler":
                registerHandler(request, response);
                break;
        }

    }

    protected void login(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            AccountFacade af = new AccountFacade();
            Account account = af.login(email, Hash.hash(password));
            if (account != null) {
                if (account.getStatus().equals("Activated")) {
                    String role = account.getRole().equals("patient") ? "home" : account.getRole();
                    response.sendRedirect(request.getContextPath() + "/" + role + "/index.do");
                    session.setAttribute("account", account);
                } else {
                    request.setAttribute("message", "Tài khoản chưa kích hoạt.");
                    request.getRequestDispatcher("/home/index.do").forward(request, response);
                }

            } else {

                //Quay về home page & thông báo lỗi
                request.setAttribute("message", "Tài khoản và mật khẩu không chính xác.");
                request.getRequestDispatcher("/home/index.do").forward(request, response);
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

    protected void logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //Xóa các thông tin lưu trong session
            session.invalidate();
            response.sendRedirect(request.getContextPath() + "/home/index.do");
        } catch (Exception ex) {
            //In thong tin chi tiet ve exception trong cua so Output de nguoi lap trinh xem
            ex.printStackTrace();
            //Chuyen den trang thong bao loi
            request.setAttribute("message", ex.getMessage());
            request.setAttribute("action", "error");
            request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
        }
    }

    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            DepartmentFacade df = new DepartmentFacade();
            List<Department> lstDep = df.selectAll();
            request.setAttribute("listDepartment", lstDep);
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
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String sex = request.getParameter("sex");
            int DepId = Integer.parseInt(request.getParameter("department"));

            Employee employee = new Employee(0, DepId, firstName, lastName, sex, birthday, address, phone, email, password, Account.mappingRole(DepId));

            EmployeeFacade ef = new EmployeeFacade();
            AccountFacade af = new AccountFacade();
            if (af.checkEmailExist(email) == false) {
                ef.register(employee);
                response.sendRedirect(request.getContextPath() + "/home/index.do");
            } else {
                request.setAttribute("message", "Email đã tồn tại");
                request.getRequestDispatcher("/account/register.do").forward(request, response);
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
