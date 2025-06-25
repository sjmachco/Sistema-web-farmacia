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
import modelo.Movimiento;
import modelo.Modelo_movimiento;

/**
 *
 * @author TIVITO
 */
public class Movimiento_controller extends HttpServlet {

    private final Modelo_movimiento m_movimiento = new Modelo_movimiento();

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
        RequestDispatcher dispatcher = null;
        List<Movimiento> lista = new ArrayList<>();
        lista = m_movimiento.listMovimientos();
        Gson gson = new Gson();
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (action.equals("regist_mov")) {
            dispatcher = request.getRequestDispatcher("view/registrar_movimiento.jsp");
            dispatcher.forward(request, response);
        } else if (action.equals("getListMov")) {
            String data = gson.toJson(lista);
            out.print(data);
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
        HttpSession session = request.getSession(false);
        String id_mov = request.getParameter("id_movimiento");
        String select_prod = request.getParameter("select_prod");
        String cantidad = request.getParameter("cantidad");
        String t_mov = request.getParameter("t_mov");
        Map<String, String> mensajes = new HashMap<>();
        Gson gson = new Gson();
        PrintWriter out = response.getWriter();
        Movimiento movimiento = null;
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Integer cant = Integer.valueOf(cantidad);

        if (id_mov.equals("0")) {
            try {
                if (session != null) {
                    Integer id_producto = Integer.valueOf(select_prod);
                    Integer total = 0;
                    String user_name = (String) session.getAttribute("usuario");
                    String id_user_name = String.valueOf(m_movimiento.getIdUserName(user_name));
                    Double precio_unitario = m_movimiento.getPrecioProducto(t_mov, id_producto);
                    Double precio_total = precio_unitario * cant;
                    movimiento = new Movimiento(0, select_prod, cant, t_mov, id_user_name, "", precio_unitario, precio_total);
                    Integer cant_prod = m_movimiento.getCantidad(id_producto);
                    System.out.println(t_mov);
                    System.out.println(movimiento.getTipo_movimiento());
                    if (t_mov.equals("compra")) {
                        total = cant_prod + cant;
                        m_movimiento.insertMovimiento(movimiento);
                        m_movimiento.updateStockProducto(total, id_producto);
                        mensajes.put("hecho", "Movimiento creado");
                    } else if (t_mov.equals("venta")) {
                        if (cant_prod == 0) {
                            mensajes.put("hecho", "No hay stock.");
                        } else if (cant > cant_prod) {
                            mensajes.put("hecho", "Cantidad insuficiente.");
                        } else {
                            total = cant_prod - cant; 
                            m_movimiento.insertMovimiento(movimiento);
                            m_movimiento.updateStockProducto(total, id_producto);
                            mensajes.put("hecho", "Movimiento creado");
                        }
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(Movimiento_controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String data = gson.toJson(mensajes);
        out.print(data);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
