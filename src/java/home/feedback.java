/*
 * To change this template, choose Tools | Templates
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
 * @author Dell
 */
public class feedback extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("cache-control", "no-cache, no-control, must-revalidate");
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
        PrintWriter out = response.getWriter();
        try {
            out.println("<!DOCTYPE html>"
                    + "<html lang=\"en\">"
                    + "<head>"
                    + "<meta charset=\"utf-8\">"
                    + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">"
                    + "<meta name=\"description\" content=\"\">"
                    + "<meta name=\"author\" content=\"\">"
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
                    + "<a href=\"Home\" class=\"navbar-brand\">"
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
                    + "<li class=\"nav-item active\">"
                    + "<a href=\"feedback\" class=\"nav-link\">"
                    + "Feedback"
                    + "</a>"
                    + "</li>"
                    + "<li class=\"nav-item\">"
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
                    + "<div class=\"main-banner one\">"
                    + "<div class=\"container px-md-0\">"
                    + "<h2><span>Feedback</span></h2>"
                    + "</div>"
                    + "</div>"
                    + "<div class=\"breadcrumb\">"
                    + "<div class=\"container px-md-0\">"
                    + "<ul class=\"list-unstyled list-inline\">"
                    + "<li class=\"list-inline-item\"><a href=\"index-2.html\">Home</a></li>"
                    + "<li class=\"list-inline-item active\">Feedback</li>"
                    + "</ul>"
                    + "</div>"
                    + "</div>		"
                    + "<div class=\"container px-md-0\">"
                    + "<div class=\"contact-content\">"
                    + "<div class=\"row\">"
                    + "<div class=\"col-md-8 col-sm-12\">"
                    + "<h3>Get In Touch </h3>");
            if (request.getParameter("errMsg") != null) {
                out.println("<div class='alert alert-danger text-center'>" + request.getParameter("errMsg") + "</div>");
            }
            if (request.getParameter("succMsg") != null) {
                out.println("<div class='alert alert-success text-center'>" + request.getParameter("succMsg") + "</div>");
            }
            out.println("<form method=\"post\" action=\"FeedbackConf\">"
                    + "<div class=\"row\">"
                    + "<div class=\"col-md-6\">"
                    + "<div class=\"form-group\">"
                    + "<label for=\"name\">Name </label>"
                    + "<input type=\"text\" class=\"form-control\" name=\"name\" id=\"name\" required=\"required\">"
                    + "</div>"
                    + "</div>"
                    + "<div class=\"col-md-6\">"
                    + "<div class=\"form-group\">"
                    + "<label for=\"email\">Email Address </label>"
                    + "<input type=\"text\" class=\"form-control\" name=\"email\" id=\"email\" required=\"required\">"
                    + "</div>"
                    + "</div>"
                    + "<div class=\"col-md-6\">"
                    + "<div class=\"form-group\">"
                    + "<label for=\"phoneno\">Contact Number </label>"
                    + "<input type=\"text\" class=\"form-control\" name=\"phone\" id=\"phoneno\" required=\"required\">"
                    + "</div>"
                    + "</div>"
                    + "<div class=\"col-md-6\">"
                    + "<div class=\"form-group\">"
                    + "<label for=\"subject\">Subject </label>"
                    + "<input type=\"text\" class=\"form-control\" name=\"subject\" id=\"subject\" required=\"required\">"
                    + "</div>"
                    + "</div>"
                    + "<div class=\"col-sm-12\">"
                    + "<div class=\"form-group\">"
                    + "<label for=\"message\">Your Message : </label>"
                    + "<textarea class=\"form-control\" rows=\"8\" name=\"message\" id=\"message\" required=\"required\"></textarea>"
                    + "</div>"
                    + "</div>"
                    + "<div class=\"col-sm-12\">"
                    + "<input type=\"submit\" class=\"btn btn-black text-uppercase\" value=\"Submit\">"
                    + "</div>"
                    + "</div>"
                    + "</form>"
                    + "</div>"
                    + "<div class=\"w-100 d-block d-md-none\">"
                    + "<p>&nbsp;</p>"
                    + "</div>"
                    + "<div class=\"col-md-4 col-sm-12\">"
                    + "<div class=\"cblock-1\">"
                    + "<span class=\"icon-wrap\"><i class=\"fa fa-car\"></i></span>"
                    + "<h4>Come and Visit</h4>"
                    + "<ul class=\"list-unstyled\">"
                    + "<li>Analyze Infotech</li>"
                    + "<li>Janpriya Complex, Phase-II, Kursi Road, Teri Puliya, Jankipuram, Lucknow, Uttar Pradesh 226016</li>"
                    + "<li>+91-9898585845</li>"
                    + "</ul>"
                    + "</div>"
                    + "<div class=\"cblock-1\" style=\"margin-bottom: 20px;\">"
                    + "<span class=\"icon-wrap red\"><i class=\"fa fa-ambulance\"></i></span>"
                    + "<h4>Emergency Care?</h4>"
                    + "<ul class=\"list-unstyled\">"
                    + "<li>If you're having a medical emergency,</li>"
                    + "<li>do not wait to contact us.</li>"
                    + "<li>+91-9898585845</li>"
                    + "<li>or visit the closest emergency center.</li>"
                    + "</ul>"
                    + "</div>"
                    + "</div>"
                    + "</div>"
                    + "</div>"
                    + "</div>"
                    + "<div class=\"col-md-12\" style=\"padding: 0px;\"><iframe style=\"width: 100%; height: 300px;\" src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3557.8395161197955!2d80.95418711459939!3d26.90858788312937!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3999580046c00001%3A0x417daf56e9110066!2sAnalyze+Infotech!5e0!3m2!1sen!2sin!4v1552758132154\" frameborder=\"0\" allowfullscreen></iframe></div>"
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
        } finally {
            out.close();
        }
    }
}
