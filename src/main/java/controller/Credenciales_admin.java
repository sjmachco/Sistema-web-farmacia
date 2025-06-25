/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

/**
 *
 * @author TIVITO
 */
public class Credenciales_admin implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setInitParameter("username", "admin");
        sce.getServletContext().setInitParameter("clave", "admin123");                 
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Código de destrucción
    }
}
