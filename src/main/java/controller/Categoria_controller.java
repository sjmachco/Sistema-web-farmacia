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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Categoria;
import modelo.Modelo_categoria;

/**
 *
 * @author TIVITO
 */
@WebServlet(name = "Categoria_controller", urlPatterns = {"/Categoria_controller"})
public class Categoria_controller extends HttpServlet {

    private final Modelo_categoria m_categoria = new Modelo_categoria();

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Categoria categoria = new Categoria();
        String id_cat = request.getParameter("id");
        String action = request.getParameter("action");
        RequestDispatcher dispatcher = null;
        List<Categoria> list = new ArrayList<>();
        list = m_categoria.listCategoria();
        Gson gson = new Gson();
        String list_categoria = gson.toJson(list);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        if (id_cat == null) {
            if (action.equals("regist_cat")) {
                //response.getWriter().write("{\"lista\": list_categoria}");
                //request.setAttribute("list_cat", list);
                //out.print(list_categoria);
                dispatcher = request.getRequestDispatcher("view/registrar_categoria.jsp");
                dispatcher.forward(request, response);
            } else if (action.equals("getListCat")) {
                out.print(list_categoria);
            }
        } else {
            Integer id_categoria = Integer.valueOf(id_cat);
            categoria = m_categoria.getCategoria(id_categoria);
            if (categoria != null) {
                response.setStatus(200);
                out.print(gson.toJson(categoria));
            } else {
                response.setStatus(404);
                out.print("Categoria no encontrada");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Categoria categoria = new Categoria();
        Map<String, String> mensajes = new HashMap<>();
        List<String> list_name = m_categoria.listCategoriaName();
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        boolean existe;

        String id_categoria = request.getParameter("id_categoria");
        String nombreCategoria = request.getParameter("nombreCategoria");
        String valor_estado = request.getParameter("estado");

        existe = m_categoria.existCategoria(list_name, nombreCategoria); //true
        //Metodo opcional para el envio multiple de datos
        /*response.setContentType("application/json");
        response.getWriter().write("{\"existeCategoria\": true}");*/
        if (!existe) {
            mensajes.put("existe", "false");
            if (id_categoria.equals("0")) {
                categoria.setNombre(nombreCategoria);
                categoria.setEstado(Integer.valueOf(valor_estado));
                try {
                    m_categoria.insertCategoria(categoria);
                } catch (SQLException ex) {
                    Logger.getLogger(Categoria_controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                mensajes.put("hecho", "Categoria creada.");
                //out.print("Categoria registrado con exito");
            } else {
                categoria.setId_categoria(Integer.valueOf(id_categoria));
                categoria.setNombre(nombreCategoria);
                categoria.setEstado(Integer.valueOf(valor_estado));
                try {
                    boolean data_update = m_categoria.updateCategoria(categoria);
                    if (data_update) {
                        response.setStatus(200);
                        mensajes.put("hecho", "Datos actualizados");
                    } else {
                        response.setStatus(500);
                        mensajes.put("hecho", "Actualizacion fallida");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Categoria_controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else{
            mensajes.put("existe", "true");
        }
        String json = gson.toJson(mensajes);
        out.print(json);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id_cat = request.getParameter("id");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        if (id_cat != null) {
            try {
                boolean delete = m_categoria.deleteCategoria(Integer.valueOf(id_cat));
                if (delete) {
                    response.setStatus(200);
                    out.print("true");
                } else {
                    response.setStatus(500);
                    out.print("false");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Categoria_controller.class.getName()).log(Level.SEVERE, null, ex);
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
