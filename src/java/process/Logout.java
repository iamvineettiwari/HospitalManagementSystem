/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Vineet Tiwari
 */
public class Logout extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            session.removeAttribute("user_id");
            session.removeAttribute("user_type");
            session.removeAttribute("s_user");
            session.invalidate();
            response.sendRedirect("Home");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
