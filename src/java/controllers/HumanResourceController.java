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

/**
 *
 * @author Danh
 */
@WebServlet(name = "HumanResourceController", urlPatterns = {"/humanResource"})
public class HumanResourceController extends HttpServlet {

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
                index(session, request, response);
                break;
            case "doctorManagement":
                doctorManagement(session, request, response);
                break;
            case "employeeManagement":
                employeeManagement(session, request, response);
                break;
            case "activate":
                activate(session, request, response);
                break;
            case "deactivate":
                deactivate(session, request, response);
                break;
            case "delete":
                delete(session, request, response);
                break;
            case "update":
                update(session, request, response);
                break;
            case "updateHandler":
                updateHandler(session, request, response);
                break;
            case "detail":
                detail(session, request, response);
                break;
        }
    }

    protected void index(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            AccountFacade af = new AccountFacade();
            List<Account> list = af.selectAllNonActivated();

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

    protected void doctorManagement(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String op = request.getParameter("op") == null ? "" : request.getParameter("op");
            String employeeId = request.getParameter("employeeId");
            EmployeeFacade ef = new EmployeeFacade();
            List<Employee> listSearch = ef.selectAllDoctor(null);
            List<Employee> list = null;
            switch (op) {
                case "search":
                    list = ef.selectAllDoctor(employeeId);
                    //System.out.println("test here" +employeeId);
                    break;
                default:
                    list = ef.selectAllDoctor(null);
                    break;
            }
            request.setAttribute("list", list);
            request.setAttribute("listSearch", listSearch);
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

    protected void employeeManagement(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String op = request.getParameter("op") == null ? "" : request.getParameter("op");
            String employeeId = request.getParameter("employeeId");
            EmployeeFacade ef = new EmployeeFacade();
            List<Employee> listSearch = ef.selectAllEmployee(null);
            List<Employee> list = null;
            switch (op) {
                case "search":
                    list = ef.selectAllEmployee(employeeId);
                    //System.out.println("test here" +employeeId);
                    break;
                default:
                    list = ef.selectAllEmployee(null);
                    break;
            }
            request.setAttribute("list", list);
            request.setAttribute("listSearch", listSearch);
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

    protected void activate(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int accId = Integer.parseInt(request.getParameter("accId"));
            AccountFacade af = new AccountFacade();
            af.activate(accId);
            System.out.print("ACCOUNT ID HERE " + accId);
            response.sendRedirect(request.getContextPath() + "/humanResource/index.do");
        } catch (Exception ex) {
            //In thong tin chi tiet ve exception trong cua so Output de nguoi lap trinh xem
            ex.printStackTrace();
            //Chuyen den trang thong bao loi
            request.setAttribute("message", ex.getMessage());
            request.setAttribute("action", "error");
            request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
        }
    }

    protected void deactivate(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String actionPre = request.getParameter("actionPre");
            int accId = Integer.parseInt(request.getParameter("accId"));
            AccountFacade af = new AccountFacade();
            af.deactivate(accId);
            System.out.print("ACCOUNT ID HERE " + accId);
            response.sendRedirect(request.getContextPath() + "/humanResource/" + actionPre + ".do");
        } catch (Exception ex) {
            //In thong tin chi tiet ve exception trong cua so Output de nguoi lap trinh xem
            ex.printStackTrace();
            //Chuyen den trang thong bao loi
            request.setAttribute("message", ex.getMessage());
            request.setAttribute("action", "error");
            request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
        }
    }

    protected void delete(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int accId = Integer.parseInt(request.getParameter("accId"));
            AccountFacade af = new AccountFacade();
            af.delete(accId);
            response.sendRedirect(request.getContextPath() + "/humanResource/index.do");
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
            int empId = Integer.parseInt(request.getParameter("empId"));
            String actionPre = request.getParameter("actionPre");
            EmployeeFacade ef = new EmployeeFacade();
            Employee employee = ef.selectEmployee(empId);
            DepartmentFacade df = new DepartmentFacade();
            List<Department> lstDep = df.selectAll();
            request.setAttribute("listDepartment", lstDep);
            request.setAttribute("actionPre", actionPre);
            request.setAttribute("employee", employee);
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
            int empId = Integer.parseInt(request.getParameter("empId"));
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            float salary = Float.parseFloat(request.getParameter("salary"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date birthday = sdf.parse(request.getParameter("birthday"));
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String sex = request.getParameter("sex");
            int DepId = Integer.parseInt(request.getParameter("department"));

            Employee employee = new Employee(salary, DepId, empId, firstName, lastName, sex, birthday, address, phone, email, password, Account.mappingRole(DepId));

            String actionPre = request.getParameter("actionPre");
            EmployeeFacade ef = new EmployeeFacade();
            ef.update(employee);

            response.sendRedirect(request.getContextPath() + "/humanResource/" + actionPre + ".do");
        } catch (Exception ex) {
            //In thong tin chi tiet ve exception trong cua so Output de nguoi lap trinh xem
            ex.printStackTrace();
            //Chuyen den trang thong bao loi
            request.setAttribute("message", ex.getMessage());
            request.setAttribute("action", "error");
            request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
        }
    }
    protected void detail(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int empId = Integer.parseInt(request.getParameter("empId"));
            String actionPre = request.getParameter("actionPre");
            EmployeeFacade ef = new EmployeeFacade();
            Employee employee = ef.selectEmployee(empId);
            DepartmentFacade df = new DepartmentFacade();
            List<Department> lstDep = df.selectAll();
            request.setAttribute("listDepartment", lstDep);
            request.setAttribute("actionPre", actionPre);
            request.setAttribute("employee", employee);
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
