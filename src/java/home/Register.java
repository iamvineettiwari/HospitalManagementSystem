/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

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
public class Register extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            response.setHeader("cache-control", "no-cache, no-store, must-revalidate");
            HttpSession session = request.getSession();
            if (session.getAttribute("user_type") != null) {
                if (session.getAttribute("user_type").toString().equalsIgnoreCase("admin")) {
                    response.sendRedirect("Admin");
                } else if (session.getAttribute("user_type").toString().equalsIgnoreCase("doctor")) {
                    response.sendRedirect("DoctorDashboard");
                } else if (session.getAttribute("user_type").toString().equalsIgnoreCase("patient")) {
                    response.sendRedirect("Dashboard");
                }
            }
            out.println("<!DOCTYPE html>"
                    + "<html lang=\"en\">"
                    + "<head>"
                    + "<meta http-equiv=\"content-type\" content=\"text/html;charset=UTF-8\" />"
                    + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">"
                    + "<meta name=\"description\" content=\"\">"
                    + "<meta name=\"author\" content=\"\">"
                    + ""
                    + "<title>Hospital Management System</title>"
                    + "<link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">"
                    + "<link href=\"https://fonts.googleapis.com/css?family=Lato:400,100,100italic,300,300italic,400italic,700,700italic,900,900italic\" rel=\"stylesheet\" type=\"text/css\">"
                    + "<link href=\"https://fonts.googleapis.com/css?family=Roboto:400,100,300,100italic,300italic,400italic,500,500italic,700,700italic,900,900italic\" rel=\"stylesheet\" type=\"text/css\">"
                    + "<link href=\"font-awesome/css/font-awesome.min.css\" rel=\"stylesheet\">"
                    + "<link href=\"js/plugins/camera/css/camera.css\" rel=\"stylesheet\">"
                    + "<link href=\"js/plugins/magnific-popup/magnific-popup.css\" rel=\"stylesheet\">"
                    + "<link href=\"js/plugins/range-slider/css/bootstrap-slider.min.css\" rel=\"stylesheet\">"
                    + "<link href=\"css/style.css\" rel=\"stylesheet\">"
                    + "<link href=\"css/responsive.css\" rel=\"stylesheet\">"
                    + ""
                    + "</head>"
                    + "<body>"
                    + "<header class=\"main-header\">"
                    + "<div class=\"container px-md-0\">"
                    + "<div class=\"top-bar d-none d-md-block\">"
                    + "<div class=\"row\">"
                    + "<div class=\"col-md-6 col-sm-12\">"
                    + "<span>Opening Hours:</span>  Mon To Fri - 9:00 - 20:00, Sat &amp; Sun - 10:00 - 15:00"
                    + "</div>"
                    + "<div class=\"col-md-6 col-sm-12\">"
                    + "<ul class=\"list-unstyled list-inline\">"
                    + "<li class=\"list-inline-item\"><a href=\"mailto:care@yourhosptialsname.com\">"
                    + "<i class=\"fa fa-envelope-o\"></i>"
                    + "care@yourhosptialsname.com"
                    + "</a></li>"
                    + "<li class=\"list-inline-item\"><i class=\"fa fa-phone\"></i> Call Us: 9998887771</li>"
                    + "</ul>"
                    + "</div>"
                    + "</div>"
                    + "</div>"
                    + "<nav id=\"nav\" class=\"navbar navbar-expand-lg\" role=\"navigation\">"
                    + "<div class=\"container px-md-0\">"
                    + "<a href=\"index.html\" class=\"navbar-brand\">"
                    + "<i class=\"fa fa-heartbeat\"></i>"
                    + "HMS"
                    + "</a>"
                    + "<button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#mainNav\" aria-controls=\"mainNav\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">"
                    + "<span class=\"navbar-toggler-icon fa fa-bars\"></span>"
                    + "</button>"
                    + "<div id=\"mainNav\" class=\"navbar-collapse collapse\">"
                    + "<ul class=\"nav navbar-nav ml-auto\">"
                    + "<li class=\"nav-item\">"
                    + "<a href=\"Home\" class=\"nav-link\">Home</a>"
                    + "</li>"
                    + "<li class=\"nav-item\">"
                    + "<a href=\"about\" class=\"nav-link\">"
                    + "About"
                    + "</a>"
                    + "</li>"
                    + "<li class=\"nav-item\">"
                    + "<a href=\"feedback\" class=\"nav-link\">"
                    + "Feedback"
                    + "</a>"
                    + "</li>"
                    + "<li class=\"nav-item\">"
                    + "<a href=\"Login\" class=\"nav-link\">"
                    + "Login"
                    + "</a>"
                    + "</li>"
                    + "<li class=\"nav-item active\">"
                    + "<a href=\"Register\" class=\"nav-link\">"
                    + "Register"
                    + "</a>"
                    + "</li>"
                    + "</ul>"
                    + "</div>"
                    + "</div>"
                    + "</nav>"
                    + "</div>"
                    + "</header>"
                    + "<div class=\"main-banner eight\">"
                    + "<div class=\"container px-md-0\">"
                    + "<h2><span>Register</span></h2>"
                    + "</div>"
                    + "</div>"
                    + "<div class=\"breadcrumb\">"
                    + "<div class=\"container px-md-0\">"
                    + "<ul class=\"list-unstyled list-inline\">"
                    + "<li class=\"list-inline-item\"><a href=\"index-2.html\">Home</a></li>"
                    + "<li class=\"list-inline-item active\">Register</li>"
                    + "</ul>"
                    + "</div>"
                    + "</div>		"
                    + "<div class=\"container px-md-0 main-container\">"
                    + "<div class=\"row\">"
                    + "<div class=\"col-md-10 offset-md-1 col-sm-12\">"
                    + "<h3 class=\"main-heading3\">Create Account</h3>");
            if (request.getParameter("errMsg") != null) {
                out.println("<div class='alert alert-danger text-center'>" + request.getParameter("errMsg") + "</div>");
            }
            if (request.getParameter("succMsg") != null) {
                out.println("<div class='alert alert-success text-center'>" + request.getParameter("succMsg") + "</div>");
            }
            out.println("<div class=\"box2 form-box\">"
                    + "<p>By creating an account on our website you will be able to appoint doctor for your treatment from your home without any problems</p><br>"
                    + "<form action='PatientRegConf' method='post'>"
                    + "<div class=\"row\">"
                    + "<div class=\"form-group col-md-6\">"
                    + "<label>Name</label>"
                    + "<input type=\"text\" name=\"name\" class=\"form-control\">"
                    + "</div>"
                    + "<div class=\"form-group col-md-6\">"
                    + "<label>Father's Name</label>"
                    + "<input type=\"text\" name=\"fname\" class=\"form-control\">"
                    + "</div>"
                    + "</div>"
                    + "<div class=\"form-group\">"
                    + "<label for=\"register-email\">Email</label>"
                    + "<input type=\"email\"  name=\"email\" class=\"form-control\">"
                    + "</div>"
                    + "<div class=\"row\">"
                    + "<div class=\"form-group col-md-6\">"
                    + "<label>Password</label>"
                    + "<input type=\"password\" name=\"password\" class=\"form-control\">"
                    + "</div>"
                    + "<div class=\"form-group col-md-6\">"
                    + "<label>Contact Number</label>"
                    + "<input type=\"number\" name=\"contact\" class=\"form-control\">"
                    + "</div>"
                    + "</div>"
                    + "<div class=\"form-group\">"
                    + "<label for=\"register-address\">Address</label>"
                    + "<textarea name=\"address\" rows=\"4\" class=\"form-control\"></textarea>"
                    + "</div>"
                    + "<div class=\"row\">"
                    + "<div class=\"form-group col-md-6\">"
                    + "<label>City</label>"
                    + "<input type=\"text\" name=\"city\" class=\"form-control\">"
                    + "</div>"
                    + "<div class=\"form-group col-md-6\">"
                    + "<label>Postal Code</label>"
                    + "<input type=\"number\" name=\"postal\" class=\"form-control\">"
                    + "</div>"
                    + "</div>"
                    + "<div class=\"row\">"
                    + "<div class=\"form-group col-md-12\">"
                    + "<select name=\"state\" class=\"form-control\">"
                    + "<option value=\"Andhra Pradesh\"> Andhra Pradesh</option>"
                    + "<option value=\"Arunachal Pradesh\">Arunachal Pradesh</option>"
                    + "<option value=\"Assam\">Assam</option>"
                    + "<option value=\"Bihar\">Bihar</option>"
                    + "<option value=\"Chhattisgarh\">Chhattisgarh</option>"
                    + "<option value=\"Goa\">Goa</option>"
                    + "<option value=\"Gujarat\">Gujarat</option>"
                    + "<option value=\"Haryana\">Haryana</option>"
                    + "<option value=\"Himachal Pradesh\">Himachal Pradesh</option>"
                    + "<option value=\"Jammu and Kashmir\">Jammu and Kashmir</option>"
                    + "<option value=\"Jharkhand\">Jharkhand</option>"
                    + "<option value=\"Karnataka\">Karnataka</option>"
                    + "<option value=\"Kerala\">Kerala</option>"
                    + "<option value=\"Madhya Pradesh\">Madhya Pradesh</option>"
                    + "<option value=\"Maharashtra\">Maharashtra</option>"
                    + "<option value=\"Manipur\">Manipur</option>"
                    + "<option value=\"Meghalaya\">Meghalaya</option>"
                    + "<option value=\"Mizoram\">Mizoram</option>"
                    + "<option value=\"Nagaland\">Nagaland</option>"
                    + "<option value=\"Odisha\">Odisha</option>"
                    + "<option value=\"Punjab\">Punjab</option>"
                    + "<option value=\"Rajasthan\">Rajasthan</option>"
                    + "<option value=\"Sikkim\">Sikkim</option>"
                    + "<option value=\"Tamil Nadu\">Tamil Nadu</option>"
                    + "<option value=\"Telangana\">Telangana</option>"
                    + "<option value=\"Tripura\">Tripura</option>"
                    + "<option value=\"Uttar Pradesh\">Uttar Pradesh</option>"
                    + "<option value=\"Uttarakhand\">Uttar Pradesh</option>"
                    + "<option value=\"West Bengal\">West Bengal</option>"
                    + "</select>"
                    + "</div>"
                    + "</div>"
                    + "<button type=\"submit\" class=\"btn btn-1\">Create Account</button>"
                    + "</form></div>"
                    + "</div>"
                    + "</div>"
                    + "</div>"
                    + "<footer class=\"main-footer\">"
                    + "<div class=\"copyright\">"
                    + "<div class=\"container px-md-0 clearfix text-center text-md-left\">"
                    + "<p class=\"float-md-left mb-2 mb-md-0\">"
                    + "&copy; Copyright 2019. AlL Rights Reserved By <span>HMS</span>"
                    + "</p>"
                    + "</div>"
                    + "</div>"
                    + "</footer>"
                    + "<script src=\"js/jquery-3.3.1.min.js\"></script>"
                    + "<script src=\"js/popper.min.js\"></script>"
                    + "<script src=\"js/bootstrap.min.js\"></script>"
                    + "<script src=\"js/plugins/camera/js/jquery.mobile.customized.min.js\"></script>"
                    + "<script src=\"js/plugins/camera/js/jquery.easing.1.3.js\"></script>"
                    + "<script src=\"js/plugins/camera/js/camera.min.js\"></script>	"
                    + "<script src=\"js/plugins/shuffle/jquery.shuffle.modernizr.min.js\"></script>"
                    + "<script src=\"js/plugins/magnific-popup/jquery.magnific-popup.min.js\"></script>"
                    + "<script src=\"js/plugins/range-slider/js/bootstrap-slider.min.js\"></script>"
                    + "<script src=\"https://maps.googleapis.com/maps/api/js\"></script>	"
                    + "<script src=\"js/custom.js\"></script>	"
                    + "</body>"
                    + "</html>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
