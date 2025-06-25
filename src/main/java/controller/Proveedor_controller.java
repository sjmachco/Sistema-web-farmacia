/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Modelo_proveedor;
import java.util.List;
import java.util.ArrayList;
import modelo.Proveedor;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TIVITO
 */
public class Proveedor_controller extends HttpServlet {

    Modelo_proveedor m_proveedor = new Modelo_proveedor();

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
        String action = request.getParameter("action");
        String id_prov = request.getParameter("id");
        List<Proveedor> list_prov = new ArrayList<>();
        Proveedor proveedor = new Proveedor();
        Gson gson = new Gson();
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        list_prov = m_proveedor.listProveedor();
        RequestDispatcher dispatcher = null;

        if (id_prov == null) {
            if (action.equals("regist_prov")) {
                dispatcher = request.getRequestDispatcher("view/registrar_proveedor.jsp");
                dispatcher.forward(request, response);
            } else if (action.equals("getListProv")) {
                String list_proveedor = gson.toJson(list_prov);
                out.print(list_proveedor);
            }
        } else{
            Integer id = Integer.valueOf(id_prov);
            proveedor = m_proveedor.getProveedorId(id);
            if(proveedor != null){
                response.setStatus(200);
                String datos = gson.toJson(proveedor);
                out.print(datos);
            } else{
                response.setStatus(500);
                out.print("Error");
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> lista_prov = m_proveedor.listProveedorName();
        Map<String, String> mensajes = new HashMap<>();
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        boolean existe;

        String id_proveedor = request.getParameter("id_proveedor");
        String nombre = request.getParameter("nombre");
        String ruc = request.getParameter("ruc");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        String pais = request.getParameter("pais");
        String estado = request.getParameter("estado");
        existe = m_proveedor.existeProveedor(lista_prov, nombre);

        if (!existe) {
            mensajes.put("existe", "false");
            Integer estado_prov = Integer.valueOf(estado);
            if (id_proveedor.equals("0")) {
                Proveedor prov = new Proveedor(0, nombre, ruc, telefono, direccion, pais, estado_prov);
                try {
                    m_proveedor.insertProveedor(prov);
                } catch (SQLException ex) {
                    Logger.getLogger(Proveedor_controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                mensajes.put("hecho", "Proveedor creado.");
            } else {
                Integer id_prov = Integer.valueOf(id_proveedor);
                Proveedor prov = new Proveedor(id_prov, nombre, ruc, telefono, direccion, pais, estado_prov);
                try {
                    boolean update_prov = m_proveedor.updateProveedor(prov);
                    if (update_prov) {
                        response.setStatus(200);
                        mensajes.put("hecho", "Proveedor actualizado");
                    } else {
                        response.setStatus(500);
                        mensajes.put("hecho", "Error en la actualizacion");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Proveedor_controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            mensajes.put("existe", "true");
        }
        String json = gson.toJson(mensajes);
        out.print(json);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id_proveedor = request.getParameter("id");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        if (id_proveedor != null) {
            Integer id_prov = Integer.valueOf(id_proveedor);
            try {
                boolean delete = m_proveedor.deleteProveedor(id_prov);
                if (delete) {
                    response.setStatus(200);
                    out.print("true");
                } else {
                    response.setStatus(500);
                    out.print("false");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Proveedor_controller.class.getName()).log(Level.SEVERE, null, ex);
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
