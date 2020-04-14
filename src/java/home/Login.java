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
public class Login extends HttpServlet {

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
                    + "<meta charset=\"utf-8\">"
                    + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">"
                    + "<meta name=\"description\" content=\"\">"
                    + "<meta name=\"author\" content=\"\">"
                    + "<title>Hospitals - Bootstrap 4 Template</title>"
                    + "<link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">"
                    + "<link href=\"https://fonts.googleapis.com/css?family=Lato:400,100,100italic,300,300italic,400italic,700,700italic,900,900italic\" rel=\"stylesheet\" type=\"text/css\">"
                    + "<link href=\"https://fonts.googleapis.com/css?family=Roboto:400,100,300,100italic,300italic,400italic,500,500italic,700,700italic,900,900italic\" rel=\"stylesheet\" type=\"text/css\">"
                    + "<link href=\"font-awesome/css/font-awesome.min.css\" rel=\"stylesheet\">"
                    + "<link href=\"js/plugins/camera/css/camera.css\" rel=\"stylesheet\">"
                    + "<link href=\"js/plugins/magnific-popup/magnific-popup.css\" rel=\"stylesheet\">"
                    + "<link href=\"js/plugins/range-slider/css/bootstrap-slider.min.css\" rel=\"stylesheet\">"
                    + "<link href=\"css/style.css\" rel=\"stylesheet\">"
                    + "<link href=\"css/responsive.css\" rel=\"stylesheet\">"
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
                    + "<li class=\"nav-item active\">"
                    + "<a href=\"Login\" class=\"nav-link\">"
                    + "Login"
                    + "</a>"
                    + "</li>"
                    + "<li class=\"nav-item\">"
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
                    + "<h2><span>Login</span></h2>"
                    + "</div>"
                    + "</div>"
                    + "<div class=\"breadcrumb\">"
                    + "<div class=\"container px-md-0\">"
                    + "<ul class=\"list-unstyled list-inline\">"
                    + "<li class=\"list-inline-item\"><a href=\"index-2.html\">Home</a></li>"
                    + "<li class=\"list-inline-item active\">Login</li>"
                    + "</ul>"
                    + "</div>"
                    + "</div>		"
                    + "<div class=\"container px-md-0 main-container\">"
                    + "<div class=\"row\">"
                    + "<div class=\"col-md-6 offset-md-3 col-sm-12\">"
                    + "<h3 class=\"main-heading3\">Login</h3>");
            if (request.getParameter("errMsg") != null) {
                out.println("<div class=\"alert alert-danger\">" + request.getParameter("errMsg") + "</div>");
            }
            out.println("<div class=\"box2 form-box\">"
                    + "<form action=\"LoginConf\" method=\"post\">"
                    + "<div class=\"form-group\">"
                    + "<label>Select User Type</label>"
                    + "<select name=\"type\" class=\"form-control\">"
                    + "<option selected disabled>Select . . . </option>"
                    + "<option value=\"Admin\">Admin</option>"
                    + "<option value=\"Patient\">Patient</option>"
                    + "<option value=\"Doctor\">Doctor</option>"
                    + "</select>"
                    + "</div>"
                    + "<div class=\"form-group\">"
                    + "<label>Username</label>"
                    + "<input type=\"text\" name=\"user\" class=\"form-control\">"
                    + "</div>"
                    + "<div class=\"form-group\">"
                    + "<label>Password</label>"
                    + "<input type=\"password\" name=\"pass\" class=\"form-control\">"
                    + "</div>"
                    + "<!--<p><a href=\"#\"><em>Forgot Your Password</em></a></p>-->"
                    + "<button type=\"submit\" class=\"btn btn-1\">Sign In</button>"
                    + "</div></form>"
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
