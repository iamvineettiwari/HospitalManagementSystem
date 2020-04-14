/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process;

import DB.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.*;

public class LoginConf extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String user = request.getParameter("user");
            String pass = request.getParameter("pass");
            String type = request.getParameter("type");
            if (user == "" || pass == "" || type == "" || type == null) {
                response.sendRedirect("Login?errMsg=All the fields are required.");
            } else {
                DBConnection db = new DBConnection();
                db.stmt = db.con.createStatement();
                db.rst = db.stmt.executeQuery("SELECT pass,type,status,id FROM login_mstr WHERE email='" + user + "'");
                if (db.rst.next()) {
                    String db_pass = db.rst.getString(1);
                    String db_type = db.rst.getString(2);
                    String db_status = db.rst.getString(3);
                    if (db_status.equals("1")) {
                        if (pass.equals(db_pass)) {
                            if (db_type.equalsIgnoreCase(type)) {
                                if (db_type.equalsIgnoreCase(type) && db_type.equalsIgnoreCase("Admin")) {
                                    HttpSession session = request.getSession();
                                    session.setAttribute("user_type", "Admin");
                                    session.setAttribute("s_user", user);
                                    session.setAttribute("user_id", db.rst.getString(4));
                                    response.sendRedirect("Admin");
                                } else {
                                    if (db_type.equalsIgnoreCase(type) && db_type.equalsIgnoreCase("Doctor")) {
                                        HttpSession session = request.getSession();
                                        session.setAttribute("user_type", "Doctor");
                                        session.setAttribute("s_user", user);
                                    session.setAttribute("user_id", db.rst.getString(4));
                                        response.sendRedirect("DoctorDashboard");
                                    } else if (db_type.equalsIgnoreCase(type) && db_type.equalsIgnoreCase("Patient")) {
                                        HttpSession session = request.getSession();
                                        session.setAttribute("user_type", "Patient");
                                        session.setAttribute("s_user", user);
                                    session.setAttribute("user_id", db.rst.getString(4));
                                        response.sendRedirect("Dashboard");
                                    }
                                }
                            } else {
                                response.sendRedirect("Login?errMsg=User type crediential is invalid !");
                            }
                        } else {
                            response.sendRedirect("Login?errMsg=Password is invalid !");
                        }
                    } else {
                        response.sendRedirect("Login?errMsg=Sorry you are not approved !");
                    }
                } else {
                    response.sendRedirect("Login?errMsg=Username is invalid !");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println(e);
        }
    }
}
