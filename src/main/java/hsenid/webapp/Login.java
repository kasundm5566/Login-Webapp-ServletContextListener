/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsenid.webapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hsenid.
 * @author hsenid
 */
public class Login extends HttpServlet{

    User user;
    static String error="Error in username or password!";

    @Override
    /**
     * This method will gets parameters/values sent by the login.jsp and process them.
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user = new User(req.getParameter("uname"), req.getParameter("pass"));
        
        /*boolean status=Validate(user);
        if(status){
            resp.sendRedirect("success.jsp");
        }else{
            req.setAttribute("error_msg", "User name and password does not match!");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            rd.forward(req, resp);
        }*/

        boolean status = ValidateByDB(user);
        if (status) {
            resp.sendRedirect("success.jsp");
        } else {
            req.setAttribute("error_msg", error);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            rd.forward(req, resp);  
        }

    }
    
    /**
     * @param user
     * Passing a user to validate username and password
     * @return status
     * Returns whether user passed the validation or not
     */
    public boolean Validate(User user) {
        boolean status = false;
        status = user.getUsername().equals("test") && user.getPassword().equals("123");
        return status;
    }
    
    /**
     * @param user
     * Passing a user to validate username and password
     * @return status
     * Returns whether user passed the validation or not
     */
    public static boolean ValidateByDB(User user) {
        boolean status = false;
        try {
            Connection connection=DBCon.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT Name FROM user_cred WHERE Name=\"" + user.getUsername() + "\" && pass=md5(\"" + user.getPassword() + "\");";
            ResultSet result = statement.executeQuery(query);
            status = result.first();
        } catch (Exception e) {
            error="Something bad happened. Try again later.";
        }
        return status;
    }
}
