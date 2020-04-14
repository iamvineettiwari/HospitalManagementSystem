/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process;

import DB.DBConnection;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Vineet Tiwari
 */
public class FeedbackConf extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String subject = request.getParameter("subject");
            String message = request.getParameter("message");
            
            if (name == "" || email == "" || phone == "" || subject == "" || message == "") {
                response.sendRedirect("feedback?errMsg=All the fields are required !");
            } else {
                DBConnection db = new DBConnection();
                db.pstmt = db.con.prepareStatement("INSERT INTO feedback (user_name, user_email, user_phone, subject, message) VALUES (?,?,?,?,?)");
                db.pstmt.setString(1, name);
                db.pstmt.setString(2, email);
                db.pstmt.setString(3, phone);
                db.pstmt.setString(4, subject);
                db.pstmt.setString(5, message);
                int done = db.pstmt.executeUpdate();
                if (done > 0) {
                    response.sendRedirect("feedback?succMsg=Thanks for your feedback, we will contact you soon for your concern or will take a look at this.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
