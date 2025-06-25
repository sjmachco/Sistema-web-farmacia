/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Empleado;
import modelo.Login;
import modelo.Modelo_empleado;

/**
 *
 * @author TIVITO
 */
@WebServlet(name = "Empleado_controller", urlPatterns = {"/Empleado_controller"})
public class Empleado_controller extends HttpServlet {

    private final Modelo_empleado m_empleado = new Modelo_empleado();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Empleado empleado, empleado_data;
        String id_empleado = request.getParameter("id");
        String action = request.getParameter("action");
        List<Empleado> list = new ArrayList<>();
        list = m_empleado.listEmployee();
        RequestDispatcher dispatcher = null;
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (id_empleado != null) {
            empleado = m_empleado.getEmpleado(Integer.valueOf(id_empleado));
            if (empleado != null) {
                response.setStatus(200);
                String data = gson.toJson(empleado);
                out.print(data);
            } else{
                response.setStatus(500);
                out.print("Empleado no encontrado");
            }
        } else {
            if (action.equals("regist_usuario")) {
                dispatcher = request.getRequestDispatcher("view/registrar_empleado.jsp");
                dispatcher.forward(request, response);
            } else if (action.equals("list_empl")) {
                String list_empleado = gson.toJson(list);
                out.print(list_empleado);
            } else if(action.equals("mostrar_datos")){
                dispatcher = request.getRequestDispatcher("view/menu_principal.jsp");
                dispatcher.forward(request, response);
            } else {
                if(action.equals("datos_empl")){
                    if(session != null){
                        String user_name = (String) session.getAttribute("usuario");
                        empleado_data = m_empleado.getUsername(user_name);
                        String data_empleado = gson.toJson(empleado_data);
                        out.print(data_empleado);
                    }
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Empleado empleado;
        Login login;
        Map<String, String> mensajes = new HashMap<>();
        List<String> lista_username = new ArrayList<>();
        List<String> lista_dni = new ArrayList<>();
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        boolean existe_us, existe_dni;
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String id_empleado = request.getParameter("id");
        String nombres = request.getParameter("nombre");
        String ape = request.getParameter("apellidos");
        String dni = request.getParameter("dni");
        String tel = request.getParameter("telefono");
        String direc = request.getParameter("direccion");
        String correo = request.getParameter("correo");
        Double sueldo = Double.valueOf(request.getParameter("sueldo"));
        String sexo = request.getParameter("sexo");
        Integer estado = Integer.valueOf(request.getParameter("estado"));
        String user_name = request.getParameter("usuario");
        String pass = request.getParameter("pass");

        lista_username = m_empleado.listLogin();
        lista_dni = m_empleado.listDni();
        existe_us = m_empleado.existUsername(lista_username, user_name);
        existe_dni = m_empleado.existeDni(lista_dni, dni);

        if (!existe_dni || !id_empleado.equals("0")) {
            mensajes.put("existe_dni", "false");
            if (!existe_us || !id_empleado.equals("0")) {
                mensajes.put("existe_us", "false");
                if (id_empleado.equals("0")) {
                    empleado = new Empleado(0, nombres, ape, direc, dni, tel, sexo,
                            correo, sueldo, estado, "", "");
                    try {
                        m_empleado.createEmployee(empleado);
                        Integer id_empl_creado = m_empleado.getIdEmployee();
                        login = new Login(0, user_name, pass, id_empl_creado);
                        m_empleado.createUsuario(login, id_empl_creado);
                        mensajes.put("hecho", "Empleado creado.");
                    } catch (SQLException ex) {
                        Logger.getLogger(Empleado_controller.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    Integer id_empl = Integer.valueOf(id_empleado);
                    empleado = new Empleado(id_empl, nombres, ape, direc, "", tel, sexo,
                            correo, sueldo, estado, "", pass);
                    try {
                        boolean update_empl = m_empleado.updateEmployee(empleado);
                        boolean update_login = m_empleado.updateLogin(empleado);
                        if (update_empl && update_login) {
                            response.setStatus(200);
                            mensajes.put("hecho", "Empleado actualizado");
                        } else {
                            response.setStatus(500);
                            mensajes.put("hecho", "Error al actualizar");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Empleado_controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                mensajes.put("existe_us", "true");
            }
        } else {
            mensajes.put("existe_dni", "true");
        }
        String datos = gson.toJson(mensajes);
        out.print(datos);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id_empleado = request.getParameter("id");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        if (id_empleado != null) {
            try {
                boolean delete = m_empleado.deleteEmployee(Integer.valueOf(id_empleado));
                if (delete) {
                    response.setStatus(200);
                    out.print("true");
                } else {
                    response.setStatus(500);
                    out.print("false");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Empleado_controller.class.getName()).log(Level.SEVERE, null, ex);
            }
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
