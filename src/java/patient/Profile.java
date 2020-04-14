/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient;

import DB.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Vineet Tiwari
 */
public class Profile extends HttpServlet {

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
                            + "<title>Dashboard | Patient | Hosptital Management System</title>"
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
                            + "<li class=\"nav-item\">"
                            + "<a class=\"nav-link\" href=\"PasswordChange\">"
                            + "<i class=\"fas fa-wrench\"></i>"
                            + "<span>Change Password</span></a>"
                            + "</li>"
                            + "</ul>"
                            + "<div id=\"content-wrapper\" class=\"d-flex flex-column\">"
                            + "<div id=\"content\">"
                            + "<nav class=\"navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow\">"
                            + "<ul class=\"navbar-nav ml-auto\">"
                            + "<li class=\"nav-item active dropdown no-arrow\">"
                            + "<a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"userDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">"
                            + "<span class=\"mr-2 d-lg-inline text-gray-600 small\">Patient <i class=\"fas fa-user\"></i></span>"
                            + "</a>"
                            + "<div class=\"dropdown-menu dropdown-menu-right shadow animated--grow-in\" aria-labelledby=\"userDropdown\">"
                            + "<a class=\"dropdown-item\" href=\"Profile\">"
                            + "<i class=\"fas fa-user fa-sm fa-fw mr-2 text-gray-400\"></i>"
                            + "Profile"
                            + "</a>"
                            + "<div class=\"dropdown-divider\"></div>"
                            + "<a class=\"dropdown-item\" href=\"#\" data-toggle=\"modal\" data-target=\"#logoutModal\">"
                            + "<i class=\"fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400\"></i>"
                            + "Logout"
                            + "</a>"
                            + "</div>"
                            + "</li>"
                            + "</ul>"
                            + "</nav>"
                            + "<div class=\"container-fluid\">"
                            + "<div class=\"row\">"
                            + "<div class=\"col-xl-8 col-lg-8 offset-xl-2 offset-lg-2\" style='margin-top: 100px;'>"
                            + "<div class=\"card shadow mb-4\">"
                            + "<div class=\"card-header py-3 d-flex flex-row align-items-center justify-content-between\">"
                            + "<h6 class=\"m-0 font-weight-bold text-primary\">Profile </h6>"
                            + "</div>"
                            + "<div class=\"card-body\">"
                            + "<table class='table table-stripped'>");
                    DBConnection db = new DBConnection();
                    db.pstmt = db.con.prepareStatement("SELECT * FROM patient_reg WHERE pid = ?");
                    db.pstmt.setString(1, session.getAttribute("user_id").toString());
                    db.rst = db.pstmt.executeQuery();
                    if (db.rst.next()) {
                        out.println("<tr>"
                                + "<th style='border-top: 0px;'>Name </th><td style='border-top: 0px;'>" + db.rst.getString(2) + "</td>"
                                + "<th style='border-top: 0px;'>Father Name </th><td style='border-top: 0px;'>" + db.rst.getString(3) + "</td>"
                                + "</tr>"
                                + "<tr>"
                                + "<th>Email </th><td>" + db.rst.getString(4) + "</td>"
                                + "<th>Mobile </th><td>" + db.rst.getString(6) + "</td>"
                                + "</tr>"
                                + "<tr>"
                                + "<th>Address </th><td colspan='3'>" + db.rst.getString(7) + ", " + db.rst.getString(8) + ", " + db.rst.getString(10) + " - " + db.rst.getString(9) + "</td>"
                                + "</tr>"
                                + "</table>");
                    }
                    out.println("</div>"
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
                            + ""
                            + "</body>"
                            + ""
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
