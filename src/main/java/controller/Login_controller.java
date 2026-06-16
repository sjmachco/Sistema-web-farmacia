/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import modelo.Modelo_login;

/**
 *
 * @author TIVITO
 */
@WebServlet(name = "Login_controller", urlPatterns = {"/Login_controller"})
public class Login_controller extends HttpServlet {

    private final Modelo_login m_login = new Modelo_login();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        String action = request.getParameter("action_l");
        HttpSession session = request.getSession(false);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        if (action != null && action.equals("logout")) {
            session.invalidate();
            dispatcher = request.getRequestDispatcher("view/login.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        String usuario = request.getParameter("usuario");
        String pass = request.getParameter("pass");
        //String user_name = getServletContext().getInitParameter("username");
        //String clave = getServletContext().getInitParameter("clave");
        Integer valor = m_login.validarLogin(usuario, pass);
        //String nombre = m_login.getNameLogin(usuario);
        if (valor != null) {
            if (valor != 0) {
                session.setAttribute("usuario", usuario);
                //request.setAttribute("tipo_us", usuario);
                dispatcher = request.getRequestDispatcher("view/nav_admin.jsp");
                //out.print("true");
            } else {
                //out.print("false");
                request.setAttribute("nologin", "Usuario y/o contraseña incorrecta");
                dispatcher = request.getRequestDispatcher("view/login.jsp");

            }
            dispatcher.forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
