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
public class PatientRegConf extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String name = request.getParameter("name");
            String fname = request.getParameter("fname");
            String email = request.getParameter("email");
            String pass = request.getParameter("password");
            String cont = request.getParameter("contact");
            String addr = request.getParameter("address");
            String city = request.getParameter("city");
            String postal = request.getParameter("postal");
            String state = request.getParameter("state");
            
            if (name == "" || fname == "" || email == "" || pass == "" || cont == "" || addr == "" || city == "" || postal == "" || state == "" || state == null) {
                response.sendRedirect("Register?errMsg=All the fields are required !");
            } else {
                DBConnection db = new DBConnection();
                db.pstmt = db.con.prepareStatement("INSERT INTO patient_reg (pname, pfname, pemail, ppass, pmobile, padd, pcity, ppin, pstate) VALUES (?,?,?,?,?,?,?,?,?)");
                db.pstmt.setString(1, name);
                db.pstmt.setString(2, fname);
                db.pstmt.setString(3, email);
                db.pstmt.setString(4, pass);
                db.pstmt.setString(5, cont);
                db.pstmt.setString(6, addr);
                db.pstmt.setString(7, city);
                db.pstmt.setString(8, postal);
                db.pstmt.setString(9, state);
                int first_done = db.pstmt.executeUpdate();
                if (first_done > 0) {
                    db.rst = db.pstmt.executeQuery("SELECT LAST_INSERT_ID() FROM patient_reg");
                    if (db.rst.next()) {
                        String user_id = db.rst.getString(1);
                        db.pstmt = db.con.prepareStatement("INSERT INTO login_mstr (id, type, email, pass) VALUES (?,?,?,?)");
                        db.pstmt.setString(1, user_id);
                        db.pstmt.setString(2, "Patient");
                        db.pstmt.setString(3, email);
                        db.pstmt.setString(4, pass);
                        int done_all = db.pstmt.executeUpdate();
                        if (done_all > 0) {
                            response.sendRedirect("Register?succMsg=Registration was done successfully. You can now login to access your account.");
                        }
                    }
                } else {
                    response.sendRedirect("Register?errMsg=Something went wrong while registration.");
                }
            }
            
        }catch (Exception e) {
            e.printStackTrace();
        }        
    }
    
}
