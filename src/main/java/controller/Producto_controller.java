package controller;

import java.io.IOException;
import java.io.PrintWriter;
import com.google.gson.Gson;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Modelo_producto;
import modelo.Producto;

/**
 *
 * @author TIVE
 */
@WebServlet(name = "Producto_controller", urlPatterns = {"/Producto_controller"})
public class Producto_controller extends HttpServlet {

    private final Modelo_producto m_producto = new Modelo_producto();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        String id_producto = request.getParameter("id");
        String action = request.getParameter("action");
        RequestDispatcher dispatcher = null;
        List<Producto> list = new ArrayList<>();
        list = m_producto.listaProductos();
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        if (id_producto == null) {
            if (action.equals("regist_prod")) {
                dispatcher = request.getRequestDispatcher("view/registrar_producto.jsp");
                dispatcher.forward(request, response);
            } else if (action.equals("getListProd")) {
                String list_productos = gson.toJson(list);
                out.print(list_productos);
            }
        } else {
            Integer id_prod = Integer.valueOf(id_producto);
            Producto producto = m_producto.getIdProducto(id_prod);
            if (producto != null) {
                String data_prod = gson.toJson(producto);
                response.setStatus(200);
                out.print(data_prod);
            } else {
                response.setStatus(500);
                out.print("Error al obtener datos.");
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
        List<Producto> list = new ArrayList<>();
        Map<String, String> mensajes = new HashMap<>();
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        boolean existe;

        String id_producto = request.getParameter("id_prod");
        String nombre = request.getParameter("nombre");
        String precio_compra = request.getParameter("precio_compra");
        String precio_venta = request.getParameter("precio_venta");
        String fecha_fa = request.getParameter("fecha_fa");
        String fecha_ven = request.getParameter("fecha_ven");
        String stock = request.getParameter("stock");
        String marca = request.getParameter("marca");
        String presentacion = request.getParameter("presentacion");
        String restriccion = request.getParameter("restriccion");
        String estado = request.getParameter("estado");
        String select_cat = request.getParameter("select_cat");
        String select_prov = request.getParameter("select_prov");

        existe = m_producto.existProducto(list, nombre);

        if (!existe) {
            mensajes.put("existe", "false");
            Integer id_prod = Integer.valueOf(id_producto);
            Double p_compra = Double.valueOf(precio_compra);
            Double p_venta = Double.valueOf(precio_venta);
            /*LocalDate f_fabr = LocalDate.parse(fecha_fa);
            LocalDate f_venc = LocalDate.parse(fecha_ven);*/
            Integer stock_aux = Integer.valueOf(stock);
            Integer estado_aux = Integer.valueOf(estado);
            if (id_producto.equals("0")) {
                try {
                    Producto producto = new Producto(0, nombre, p_compra,
                            p_venta, fecha_fa, fecha_ven, stock_aux, marca, presentacion,
                            restriccion, estado_aux, select_cat, select_prov);
                    m_producto.insertProductos(producto);
                } catch (SQLException ex) {
                    Logger.getLogger(Producto_controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                mensajes.put("hecho", "Producto creado.");
            } else {
                Producto producto = new Producto(id_prod, nombre, p_compra,
                        p_venta, fecha_fa, fecha_ven, stock_aux, marca, presentacion,
                        restriccion, estado_aux, select_cat, select_prov);
                try {
                    boolean update_prod = m_producto.updateProducto(producto);
                    if (update_prod) {
                        response.setStatus(200);
                        mensajes.put("hecho", "Producto actualizado");
                    } else {
                        response.setStatus(500);
                        mensajes.put("hecho", "Error en la actualizacion");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Producto_controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            mensajes.put("existe", "true");
        }
        String datos = gson.toJson(mensajes);
        out.print(datos);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id_producto = request.getParameter("id");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        if (id_producto != null) {
            try {
                Integer id_prod = Integer.valueOf(id_producto);
                boolean delete = m_producto.deleteProducto(id_prod);
                if (delete) {
                    response.setStatus(200);
                    out.print("true");
                } else {
                    response.setStatus(500);
                    out.print("false");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Producto_controller.class.getName()).log(Level.SEVERE, null, ex);
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
