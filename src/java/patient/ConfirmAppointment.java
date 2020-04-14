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
public class ConfirmAppointment extends HttpServlet {

    String price, did, date, des;

    public void service(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setHeader("cache-control", "no-cache, no-store, must-revalidate");
            HttpSession session = request.getSession();
            if (session.getAttribute("user_type") != null) {
                if (session.getAttribute("user_type").toString().equalsIgnoreCase("patient")) {

                    if (request.getParameter("proceed") != null) {
                        DBConnection db = new DBConnection();
                        String pid = session.getAttribute("user_id").toString();
                        String demail = "", dname = "", spec = "", pname = "", pemail = "", padd = "";
                        db.pstmt = db.con.prepareStatement("SELECT email, dname, specialization FROM doctor_reg WHERE did = ?");
                        db.pstmt.setString(1, did);
                        db.rst = db.pstmt.executeQuery();
                        if (db.rst.next()) {
                            demail = db.rst.getString(1);
                            dname = db.rst.getString(2);
                            spec = db.rst.getString(3);
                        }
                        db.pstmt = db.con.prepareStatement("SELECT pname, pemail, padd FROM patient_reg WHERE pid = ?");
                        db.pstmt.setString(1, pid);
                        db.rst = db.pstmt.executeQuery();
                        if (db.rst.next()) {
                            pname = db.rst.getString(1);
                            pemail = db.rst.getString(2);
                            padd = db.rst.getString(3);
                        }

                        db.pstmt = db.con.prepareStatement("INSERT INTO patient_list (did,pid,dname,demail,specialization,pname,pemail,paddress,fee,disease,date) VALUES (?,?,?,?,?,?,?,?,?,?,CURDATE())");
                        db.pstmt.setString(1, did);
                        db.pstmt.setString(2, pid);
                        db.pstmt.setString(3, dname);
                        db.pstmt.setString(4, demail);
                        db.pstmt.setString(5, spec);
                        db.pstmt.setString(6, pname);
                        db.pstmt.setString(7, pemail);
                        db.pstmt.setString(8, padd);
                        db.pstmt.setString(9, price);
                        db.pstmt.setString(10, des);

                        int done = db.pstmt.executeUpdate();

                        if (done > 0) {
                            response.sendRedirect("BookAppointment?succMsg=Successfully appointment was booked.");
                        } else {
                            response.sendRedirect("BookAppointment?errMsg=Something went wrong while booking appointment !");
                        }

                    } else if (request.getParameter("placeOrder") == null) {
                        response.sendRedirect("BookAppointment");
                    } else {
                        PrintWriter out = response.getWriter();
                        response.setContentType("text/html");
                        did = request.getParameter("did");
                        date = request.getParameter("date");
                        des = request.getParameter("disease");

                        if (did == null || did == "" || date == "" || des == "") {
                            response.sendRedirect("BookAppointment?errMsg=All the fields are required !");
                        } else {
                            DBConnection db = new DBConnection();
                            db.pstmt = db.con.prepareStatement("SELECT * FROM doctor_availabilty WHERE did = ? AND date = ?");
                            db.pstmt.setString(1, did);
                            db.pstmt.setString(2, date);
                            db.rst = db.pstmt.executeQuery();
                            if (db.rst.next()) {
                                response.sendRedirect("BookAppointment?errMsg=Doctor is not avilable for selected date !");
                            } else {
                                db.rst = db.pstmt.executeQuery("SELECT fee FROM doctor_reg WHERE did = '" + did + "'");
                                if (db.rst.next()) {
                                    price = db.rst.getString(1);
                                }

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
                                        + "<li class=\"nav-item active\">"
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
                                        + "<div class=\"col-xl-6 col-lg-6 offset-xl-3 offset-lg-3\">"
                                        + "<div class=\"card shadow mb-4\">"
                                        + "<div class=\"card-header py-3 d-flex flex-row align-items-center justify-content-between\">"
                                        + "<h6 class=\"m-0 font-weight-bold text-primary\">Confirm Appointment</h6>"
                                        + "</div>"
                                        + "<div class=\"card-body\">"
                                        + "<form class=\"user\" action=\"\" method=\"post\">"
                                        + "<div class=\"form-group row\">"
                                        + "<div class=\"col-sm-12 mb-3 mb-sm-0\">"
                                        + "<label style=\"float:right; font-weight: bold;\">Fee - INR. " + price + "</label>"
                                        + "</div>"
                                        + "</div>"
                                        + "<div class=\"form-group\">"
                                        + "<label>Enter Card Number</label>"
                                        + "<input type=\"text\" name=\"card\" class=\"form-control\">"
                                        + "</div>"
                                        + "<div class=\"form-group row\">"
                                        + "<div class=\"col-md-6\">"
                                        + "<label>Expiry Date</label>"
                                        + "<input type=\"text\" name=\"edate\" class=\"form-control\">"
                                        + "</div>"
                                        + "<div class=\"col-md-6\">"
                                        + "<label>CVV</label>"
                                        + "<input type=\"text\" name=\"cvv\" class=\"form-control\">"
                                        + "</div>"
                                        + "</div>"
                                        + "<div class=\"form-group\">"
                                        + "<label>Name On Card </label>"
                                        + "<input type=\"text\" name=\"namec\" class=\"form-control\">"
                                        + "</div>"
                                        + "<input type=\"submit\" name=\"proceed\" value=\"Pay And Proceed\"  class=\"btn btn-primary btn-user btn-block\">"
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
                                        + ""
                                        + "</html>");
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
