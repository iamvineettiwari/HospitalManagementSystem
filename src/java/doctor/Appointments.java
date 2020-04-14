/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor;

import DB.DBConnection;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Vineet Tiwari
 */
public class Appointments extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setHeader("cache-control", "no-cache, no-store, must-revalidate");
            HttpSession session = request.getSession();
            if (session.getAttribute("user_type") != null) {
                if (session.getAttribute("user_type").toString().equalsIgnoreCase("doctor")) {

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
                            + "<title>Dashboard | Doctor | Hosptital Management System</title>"
                            + "<link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.7.2/css/all.css\" integrity=\"sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr\" crossorigin=\"anonymous\">"
                            + "<link href=\"https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i\" rel=\"stylesheet\">"
                            + "<link href=\"css/sb-admin-2.min.css\" rel=\"stylesheet\">"
                            + "</head>"
                            + "<body id=\"page-top\">"
                            + "<div id=\"wrapper\">"
                            + "<ul class=\"navbar-nav bg-gradient-primary sidebar sidebar-dark accordion\" id=\"accordionSidebar\">"
                            + "<a class=\"sidebar-brand d-flex align-items-center justify-content-center\" href=\"DoctorDashboard\">"
                            + "<div class=\"sidebar-brand-icon rotate-n-15\">"
                            + "<i class=\"fas fa-laugh-wink\"></i>"
                            + "</div>"
                            + "<div class=\"sidebar-brand-text mx-3\">HMS</div>"
                            + "</a>"
                            + "<hr class=\"sidebar-divider my-0\">"
                            + "<li class=\"nav-item\">"
                            + "<a class=\"nav-link\" href=\"DoctorDashboard\">"
                            + "<i class=\"fas fa-fw fa-tachometer-alt\"></i>"
                            + "<span>Dashboard</span></a>"
                            + "</li>"
                            + "<hr class=\"sidebar-divider\">"
                            + "<li class=\"nav-item\">"
                            + "<a class=\"nav-link\" href=\"SetAvailabilty\">"
                            + "<i class=\"fas fa-clock\"></i>"
                            + "<span>Set Availabilty</span></a>"
                            + "</li>"
                            + "<li class=\"nav-item\">"
                            + "<a class=\"nav-link\" href=\"Modifyfee\">"
                            + "<i class=\"fas fa-address-card\"></i>"
                            + "<span>Modify Fee</span></a>"
                            + "</li>"
                            + "<li class=\"nav-item active\">"
                            + "<a class=\"nav-link\" href=\"Appointments\">"
                            + "<i class=\"fas fa-user-md\"></i>"
                            + "<span>Appointments</span></a>"
                            + "</li>"
                            + "<li class=\"nav-item\">"
                            + "<a class=\"nav-link\" href=\"ChangePass\">"
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
                            + "<span class=\"mr-2 d-lg-inline text-gray-600 small\">Doctor <i class=\"fas fa-user\"></i></span>"
                            + "</a>"
                            + "<div class=\"dropdown-menu dropdown-menu-right shadow animated--grow-in\" aria-labelledby=\"userDropdown\">"
                            + "<a class=\"dropdown-item\" href=\"#\" data-toggle=\"modal\" data-target=\"#logoutModal\">"
                            + "<i class=\"fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400\"></i>"
                            + "Logout"
                            + "</a>"
                            + "</div>"
                            + "</li>"
                            + "</ul>"
                            + "</nav>"
                            + "<div class=\"container-fluid\">");
                    if (request.getParameter("errMsg") != null) {
                        out.println("<div class='alert alert-danger text-center'>" + request.getParameter("errMsg") + "</div>");
                    }
                    if (request.getParameter("succMsg") != null) {
                        out.println("<div class='alert alert-success text-center'>" + request.getParameter("succMsg") + "</div>");
                    }
                    out.println(""
                            + "<div class=\"row\">"
                            + "<div class=\"col-xl-12 col-lg-12\">"
                            + "<div class=\"card shadow mb-4\">"
                            + "<div class=\"card-header py-3 d-flex flex-row align-items-center justify-content-between\">"
                            + "<h6 class=\"m-0 font-weight-bold text-primary\">Appointments</h6>"
                            + "</div>"
                            + "<div class=\"card-body table-responsive\">"
                            + "<table class=\"table table-hover\">"
                            + "<thead class=\"thead-dark\">"
                            + "<tr>"
                            + "<th>Patient Name</th>"
                            + "<th>Patient Email</th>"
                            + "<th>Disease </th>"
                            + "<th>Patient Address</th>"
                            + "<th>Doctor Fee</th>"
                            + "<th>Date</th>"
                            + "</tr>"
                            + "</thead>"
                            + "<tbody>");
                    DBConnection db = new DBConnection();
                    db.pstmt = db.con.prepareStatement("SELECT * FROM patient_list WHERE did = ?");
                    db.pstmt.setString(1, session.getAttribute("user_id").toString());
                    db.rst = db.pstmt.executeQuery();
                    if (db.rst.isBeforeFirst()) {
                        while (db.rst.next()) {
                            out.println("<tr>"
                                    + "<td>" + db.rst.getString(7) + "</td>"
                                    + "<td>" + db.rst.getString(8) + "</td>"
                                    + "<td>" + db.rst.getString(9) + "</td>"
                                    + "<td>" + db.rst.getString(10) + "</td>"
                                    + "<td>" + db.rst.getString(12) + "</td>"
                                    + "<td>" + db.rst.getString(11) + "</td>"
                                    + "</tr>");
                        }
                    } else {
                        out.println("<tr><th colspan=\"8\">There are no appointment from you</th></tr>");
                    }
                    out.println("</tbody>"
                            + "</table>"
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
