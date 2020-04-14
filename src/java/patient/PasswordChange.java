/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient;

import DB.DBConnection;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Vineet Tiwari
 */
public class PasswordChange extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setHeader("cache-control", "no-cache, no-store, must-revalidate");
            HttpSession session = request.getSession();
            if (session.getAttribute("user_type") != null) {
                if (session.getAttribute("user_type").toString().equalsIgnoreCase("patient")) {

                    PrintWriter out = response.getWriter();
                    response.setContentType("text/html");
                    out.println("<!DOCTYPE html>"
                            + "<html lang=\"en\">"
                            + "<head>"
                            + "<meta charset=\"utf-8\">"
                            + "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">"
                            + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">"
                            + "<meta name=\"description\" content=\"\">"
                            + "<meta name=\"author\" content=\"\">"
                            + "<title>Dashboard | Admin | Hosptital Management System</title>"
                            + "<link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.7.2/css/all.css\" integrity=\"sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr\" crossorigin=\"anonymous\">"
                            + "<link href=\"https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i\" rel=\"stylesheet\">"
                            + "<link href=\"css/sb-admin-2.min.css\" rel=\"stylesheet\">"
                            + "</head>"
                            + "<body id=\"page-top\">"
                            + "<div id=\"wrapper\">"
                            + "<ul class=\"navbar-nav bg-gradient-primary sidebar sidebar-dark accordion\" id=\"accordionSidebar\">"
                            + "<a class=\"sidebar-brand d-flex align-items-center justify-content-center\" href=\"Dashboard\">"
                            + "<div class=\"sidebar-brand-icon rotate-n-15\">"
                            + "<i class=\"fas fa-laugh-wink\"></i>"
                            + "</div>"
                            + "<div class=\"sidebar-brand-text mx-3\">HMS</div>"
                            + "</a>"
                            + "<hr class=\"sidebar-divider my-0\">"
                            + "<li class=\"nav-item\">"
                            + "<a class=\"nav-link\" href=\"Dashboard\">"
                            + "<i class=\"fas fa-fw fa-tachometer-alt\"></i>"
                            + "<span>Dashboard</span></a>"
                            + "</li>"
                            + "<hr class=\"sidebar-divider\">"
                            + "<li class=\"nav-item\">"
                            + "<a class=\"nav-link\" href=\"BookAppointment\">"
                            + "<i class=\"fas fa-user-md\"></i>"
                            + "<span>Book Appointment</span></a>"
                            + "</li>"
                            + "<li class=\"nav-item\">"
                            + "<a class=\"nav-link\" href=\"AppointmentStatus\">"
                            + "<i class=\"fas fa-users\"></i>"
                            + "<span>View Appointment</span></a>"
                            + "</li>"
                            + "<li class=\"nav-item active\">"
                            + "<a class=\"nav-link\" href=\"PasswordChange\">"
                            + "<i class=\"fas fa-wrench\"></i>"
                            + "<span>Change Password</span></a>"
                            + "</li>"
                            + "</ul>"
                            + "<div id=\"content-wrapper\" class=\"d-flex flex-column\">"
                            + "<div id=\"content\">"
                            + "<nav class=\"navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow\">"
                            + "<ul class=\"navbar-nav ml-auto\">"
                            + "<li class=\"nav-item dropdown no-arrow\">"
                            + "<a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"userDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">"
                            + "<span class=\"mr-2 d-lg-inline text-gray-600 small\">Patient <i class=\"fas fa-user\"></i></span>"
                            + "</a>"
                            + "<div class=\"dropdown-menu dropdown-menu-right shadow animated--grow-in\" aria-labelledby=\"userDropdown\">"
                            + "<a class=\"dropdown-item\" href=\"Profile\">"
                            + "<i class=\"fas fa-user fa-sm fa-fw mr-2 text-gray-400\"></i>"
                            + "Profile"
                            + "</a>"
                            + "<div class=\"dropdown-divider\"></div>"
                            + "<a class=\"dropdown-item\" href=\"\" data-toggle=\"modal\" data-target=\"#logoutModal\">"
                            + "<i class=\"fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400\"></i>"
                            + "Logout"
                            + "</a>"
                            + "</div>"
                            + "</li>"
                            + "</ul>"
                            + "</nav>"
                            + "<div class=\"container-fluid\">");
                    if (request.getParameter("errMsg") != null) {
                        out.println("<div class='alert alert-danger text-center col-md-6 offset-md-3'>" + request.getParameter("errMsg") + "</div>");
                    }
                    if (request.getParameter("succMsg") != null) {
                        out.println("<div class='alert alert-success text-center col-md-6 offset-md-3'>" + request.getParameter("succMsg") + "</div>");
                    }
                    out.println("<div class=\"row\">"
                            + "<div class=\"col-xl-6 offset-xl-3 col-lg-6 offset-lg-3\">"
                            + "<div class=\"card shadow mb-4\">"
                            + "<div class=\"card-header py-3 d-flex flex-row align-items-center justify-content-between\">"
                            + "<h6 class=\"m-0 font-weight-bold text-primary\">Change Password</h6>"
                            + "</div>"
                            + "<div class=\"card-body\">"
                            + "<form class=\"user\" action=\"\" method=\"post\">"
                            + "<div class=\"form-group\">"
                            + "<label>Old Password</label>"
                            + "<input type=\"password\" class=\"form-control form-control-user\" name=\"oldpass\">"
                            + "</div>"
                            + "<div class=\"form-group\">"
                            + "<label>New Password</label>"
                            + "<input type=\"password\" class=\"form-control form-control-user\" name=\"npass\">"
                            + "</div>"
                            + "<div class=\"form-group\">"
                            + "<label>Confirm New Password</label>"
                            + "<input type=\"password\" class=\"form-control form-control-user\" name=\"cnpass\">"
                            + "</div>"
                            + "<input type=\"submit\" name=\"changePass\" value=\"Change Password\"  class=\"btn btn-primary btn-user\">"
                            + "</form>"
                            + "</div>"
                            + "</div>"
                            + "</div>"
                            + "</div>"
                            + "</div>"
                            + "</div>"
                            + "<footer class=\"sticky-footer bg-white\">"
                            + "<div class=\"container my-auto\">"
                            + "<div class=\"copyright text-center my-auto\">"
                            + "<span>Copyright &copy; Hospital Management System 2019</span>"
                            + "</div>"
                            + "</div>"
                            + "</footer>"
                            + "</div>"
                            + "<a class=\"scroll-to-top rounded\" href=\"#page-top\">"
                            + "<i class=\"fas fa-angle-up\"></i>"
                            + "</a>"
                            + "<div class=\"modal fade\" id=\"logoutModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">"
                            + "<div class=\"modal-dialog\" role=\"document\">"
                            + "<div class=\"modal-content\">"
                            + "<div class=\"modal-header\">"
                            + "<h5 class=\"modal-title\" id=\"exampleModalLabel\">Ready to Leave?</h5>"
                            + "<button class=\"close\" type=\"button\" data-dismiss=\"modal\" aria-label=\"Close\">"
                            + "<span aria-hidden=\"true\">×</span>"
                            + "</button>"
                            + "</div>"
                            + "<div class=\"modal-body\">Select \"Logout\" below if you are ready to end your current session.</div>"
                            + "<div class=\"modal-footer\">"
                            + "<button class=\"btn btn-secondary\" type=\"button\" data-dismiss=\"modal\">Cancel</button>"
                            + "<a class=\"btn btn-primary\" href=\"Logout\">Logout</a>"
                            + "</div>"
                            + "</div>"
                            + "</div>"
                            + "</div>"
                            + "<script src=\"js/jquery-3.3.1.min.js\"></script>"
                            + "<script src=\"js/bootstrap.bundle.min.js\"></script>"
                            + "<script src=\"js/jquery.easing.min.js\"></script>"
                            + "<script src=\"js/sb-admin-2.min.js\"></script>"
                            + "</body>"
                            + "</html>");

                    if (request.getParameter("changePass") != null) {
                        String old = request.getParameter("oldpass");
                        String npas = request.getParameter("npass");
                        String cpas = request.getParameter("cnpass");
                        if (old == "" || npas == "" || cpas == "") {
                            response.sendRedirect("PasswordChange?errMsg=All the fields are required !");
                        } else {
                            DBConnection db = new DBConnection();
                            db.pstmt = db.con.prepareStatement("SELECT ppass FROM patient_reg WHERE pid = ?");
                            db.pstmt.setString(1, session.getAttribute("user_id").toString());
                            db.rst = db.pstmt.executeQuery();
                            if (db.rst.next()) {
                                if (old.equals(db.rst.getString(1))) {
                                    if (npas.equals(cpas)) {
                                        db.pstmt = db.con.prepareStatement("UPDATE patient_reg SET ppass = ? WHERE pid = ?");
                                        db.pstmt.setString(1, npas);
                                        db.pstmt.setString(2, session.getAttribute("user_id").toString());
                                        int done = db.pstmt.executeUpdate();
                                        if (done > 0) {
                                            db.pstmt = db.con.prepareStatement("UPDATE login_mstr SET pass = ? WHERE id = ? AND type = ?");
                                            db.pstmt.setString(1, npas);
                                            db.pstmt.setString(2, session.getAttribute("user_id").toString());
                                            db.pstmt.setString(3, "Patient");
                                            int done_all = db.pstmt.executeUpdate();
                                            if (done_all > 0) {
                                                response.sendRedirect("PasswordChange?succMsg=Successfully changed your password !");
                                            } else {
                                                response.sendRedirect("PasswordChange?errMsg=Something went wrong !");
                                            }
                                        } else {
                                            response.sendRedirect("PasswordChange?errMsg=Something went wrong !");
                                        }
                                    } else {
                                        response.sendRedirect("PasswordChange?errMsg=New password and confirm password does not matched!");
                                    }
                                } else {
                                    response.sendRedirect("PasswordChange?errMsg=Old Password is incorrect !");
                                }
                            }
                        }
                    }
                } else {
                    response.sendRedirect("Home");
                }
            } else {
                response.sendRedirect("Home");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
