/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsenid.webapp;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;

/**
 * Created by hsenid.
 * @author hsenid
 */
public class ContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        //Get parameters and create a connection using the DBCon class.
        ServletContext context = sce.getServletContext();
        String host = context.getInitParameter("host");
        String database = context.getInitParameter("database");
        String user = context.getInitParameter("user");
        String password = context.getInitParameter("password");
        DBCon.CreateConnection(host, database, user, password);       
    }

    public void contextDestroyed(ServletContextEvent sce) {
        try {
            DBCon.getConnection().close();
        } catch (SQLException e) {
        }
    }
}
