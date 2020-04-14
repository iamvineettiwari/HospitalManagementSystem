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
public class RegisterDoctorConf extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String dn = request.getParameter("dname");
            String em = request.getParameter("demail");
            String pass = request.getParameter("password");
            String r1 = request.getParameter("spl");
            String sp = request.getParameter("degree");
            String addr = request.getParameter("address");
            String fe = request.getParameter("fee");
            if (dn == "" || em == "" || pass == "" || sp == "" || r1 == "" || addr == "" || fe == "") {
                response.sendRedirect("Admin?errMsg=All the fields are required !");
            } else {
                DBConnection db = new DBConnection();
                db.pstmt = db.con.prepareStatement("INSERT INTO doctor_reg (dname,pass,email,specialization,degree,address,fee,date)values(?,?,?,?,?,?,?,curdate())");
                db.pstmt.setString(1, dn);
                db.pstmt.setString(2, pass);
                db.pstmt.setString(3, em);
                db.pstmt.setString(4, sp);
                db.pstmt.setString(5, r1);
                db.pstmt.setString(6, addr);
                db.pstmt.setString(7, fe);
                int i = db.pstmt.executeUpdate();
                if (i > 0) {
                    db.rst = db.pstmt.executeQuery("SELECT LAST_INSERT_ID() FROM doctor_reg");
                    if (db.rst.next()) {
                        String did = db.rst.getString(1);
                        db.pstmt = db.con.prepareStatement("INSERT INTO login_mstr(id, type, email ,pass)values(?,?,?,?)");
                        db.pstmt.setString(1, did);
                        db.pstmt.setString(2, "Doctor");
                        db.pstmt.setString(3, em);
                        db.pstmt.setString(4, pass);
                        int j = db.pstmt.executeUpdate();
                        if (j > 0) {
                            response.sendRedirect("RegisterDoctor?succMsg=Registration of doctor was done successfully .");

                        } else {
                            response.sendRedirect("RegisterDoctor?errMsg=Something went wrong !");

                        }
                    }
                } else {
                    response.sendRedirect("RegisterDoctor?errMsg=Something went wrong !");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
