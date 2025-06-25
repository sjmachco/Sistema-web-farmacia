package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author TIVE
 */
public class Modelo_producto {

    Connection cn;
    Conexion conexion = new Conexion();

    public List<Producto> listaProductos() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        cn = conexion.getConexion();
        List<Producto> lista = new ArrayList<>();

        try {
            ps = cn.prepareStatement("SELECT id_producto, P.nombre, C.nombre, PR.nombre, precio_compra, "
                    + "precio_venta, fecha_fabricacion, fecha_vencimiento, stock, "
                    + "marca, presentacion, restricciones, P.estado FROM producto as P "
                    + "INNER JOIN categoria as C on P.id_categoria = C.id_categoria "
                    + "INNER JOIN proveedor as PR on P.id_proveedor = PR.id_proveedor ORDER BY id_producto ASC");
            rs = ps.executeQuery();

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId_producto(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setN_categoria(rs.getString(3));
                producto.setN_proveedor(rs.getString(4));
                producto.setPrecio_compra(rs.getDouble(5));
                producto.setPrecio_venta(rs.getDouble(6));
                Date fecha_auxf = rs.getDate(7);
                producto.setFecha_fabricacion(fecha_auxf.toString());
                Date fecha_auxv = rs.getDate(8);
                producto.setFecha_vencimiento(fecha_auxv.toString());
                producto.setStock(rs.getInt(9));
                producto.setMarca(rs.getString(10));
                producto.setPresentacion(rs.getString(11));
                producto.setRestriccion(rs.getString(12));
                producto.setEstado(rs.getInt(13));
                lista.add(producto);
            }
            return lista;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public void insertProductos(Producto producto) throws SQLException {
        PreparedStatement ps = null;
        cn = conexion.getConexion();

        try {
            ps = cn.prepareStatement("INSERT INTO producto(nombre, precio_compra, "
                    + "precio_venta, fecha_fabricacion, fecha_vencimiento, stock, marca, "
                    + "presentacion, restricciones, estado, id_categoria, id_proveedor) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio_compra());
            ps.setDouble(3, producto.getPrecio_venta());
            /*LocalDate fecha_auxf = producto.getFecha_fabricacion();
            LocalDate fecha_auxv = producto.getFecha_vencimiento();*/
            ps.setDate(4, Date.valueOf(producto.getFecha_fabricacion()));
            ps.setDate(5, Date.valueOf(producto.getFecha_vencimiento()));
            ps.setInt(6, producto.getStock());
            ps.setString(7, producto.getMarca());
            ps.setString(8, producto.getPresentacion());
            ps.setString(9, producto.getRestriccion());
            ps.setInt(10, producto.getEstado());
            ps.setInt(11, Integer.parseInt(producto.getN_categoria()));
            ps.setInt(12, Integer.parseInt(producto.getN_proveedor()));
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            ps.close();
            cn.close();
        }
    }

    public boolean updateProducto(Producto producto) throws SQLException {
        PreparedStatement ps = null;
        cn = conexion.getConexion();

        try {
            ps = cn.prepareStatement("UPDATE producto SET nombre = ?, precio_compra = ?, "
                    + "precio_venta = ?, fecha_fabricacion = ?, fecha_vencimiento = ?, "
                    + "stock = ?, marca = ?, presentacion = ?, restricciones = ?, "
                    + "estado = ?, id_categoria = ?, id_proveedor = ? WHERE id_producto = ?");
            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio_compra());
            ps.setDouble(3, producto.getPrecio_venta());
            Date fecha_fra = Date.valueOf(producto.getFecha_fabricacion());
            ps.setDate(4, fecha_fra);
            Date fecha_venc = Date.valueOf(producto.getFecha_vencimiento());
            ps.setDate(5, fecha_venc);
            ps.setInt(6, producto.getStock());
            ps.setString(7, producto.getMarca());
            ps.setString(8, producto.getPresentacion());
            ps.setString(9, producto.getRestriccion());
            ps.setInt(10, producto.getEstado());
            ps.setInt(11, Integer.parseInt(producto.getN_categoria()));
            ps.setInt(12, Integer.parseInt(producto.getN_proveedor()));
            ps.setInt(13, producto.getId_producto());
            if(ps.executeUpdate() == 1)
                return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally{
            ps.close();
            cn.close();
        }
        return false;
    }

    public boolean deleteProducto(Integer id_producto) throws SQLException {
        PreparedStatement ps = null;
        cn = conexion.getConexion();

        try {
            ps = cn.prepareStatement("DELETE FROM producto WHERE id_producto = ?");
            ps.setInt(1, id_producto);
            if(ps.executeUpdate() == 1)
                return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            ps.close();
            cn.close();
        }
        return false;
    }

    public boolean existProducto(List<Producto> list, String nombre) {
        boolean existe = false;
        for (Producto pro : list) {
            if (Objects.equals(pro.getNombre(), nombre)) {
                existe = true;
                break;
            }
        }
        return existe;
    }
    
    public Producto getIdProducto(Integer id_producto){
        PreparedStatement ps = null;
        cn = conexion.getConexion();
        ResultSet rs = null;
        Producto producto = new Producto();
        
        try {
            ps = cn.prepareStatement("SELECT id_producto, nombre, precio_compra, precio_venta, fecha_fabricacion, "
                    + "fecha_vencimiento, stock, marca, presentacion, restricciones, estado, id_categoria, "
                    + "id_proveedor FROM producto WHERE id_producto = ?");
            ps.setInt(1, id_producto);
            rs = ps.executeQuery();
            while(rs.next()){
                producto.setId_producto(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setPrecio_compra(rs.getDouble(3));
                producto.setPrecio_venta(rs.getDouble(4));
                producto.setFecha_fabricacion(rs.getString(5));
                producto.setFecha_vencimiento(rs.getString(6));
                producto.setStock(rs.getInt(7));
                producto.setMarca(rs.getString(8));
                producto.setPresentacion(rs.getString(9));
                producto.setRestriccion(rs.getString(10));
                producto.setEstado(rs.getInt(11));
                String id_cat = String.valueOf(rs.getInt(12));
                String id_prov = String.valueOf(rs.getInt(13));
                producto.setN_categoria(id_cat);
                producto.setN_proveedor(id_prov);
            }
            return producto;
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return null;
    }
}
