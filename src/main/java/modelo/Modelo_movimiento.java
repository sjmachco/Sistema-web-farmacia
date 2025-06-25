/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author TIVITO
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import config.Conexion;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.sql.CallableStatement;

public class Modelo_movimiento {
    Connection cn;
    Conexion conexion = new Conexion();
    
    public List<Movimiento> listMovimientos(){
        PreparedStatement ps = null;
        ResultSet rs = null;
        cn = conexion.getConexion();
        List<Movimiento> lista = new ArrayList<>();
        
        try {
            ps = cn.prepareStatement("SELECT id_movimiento, cantidad, tipo_movimiento, fecha_hora, "
                    + "concat(E.nombre, ' ', E.apellidos) AS datos, P.nombre, total, precio_unitario "
                    + "FROM movimientos AS M INNER JOIN empleado AS E ON M.id_empleado = E.id_empleado "
                    + "INNER JOIN producto AS P ON M.id_producto = P.id_producto ORDER BY id_movimiento");
            rs = ps.executeQuery();
            while(rs.next()){
                Movimiento mov = new Movimiento();
                mov.setId_movimiento(rs.getInt(1));
                mov.setCantidad(rs.getInt(2));
                mov.setTipo_movimiento(rs.getString(3));
                LocalDateTime fecha_hora = rs.getObject(4, LocalDateTime.class);
                String fecha_hora_aux = String.valueOf(fecha_hora);
                mov.setFecha_hora(fecha_hora_aux.replace("T", " "));
                mov.setUser_name(rs.getString(5));
                mov.setProducto(rs.getString(6));
                mov.setTotal(rs.getDouble(7));
                mov.setPrecio_unitario(rs.getDouble(8));
                lista.add(mov);
            }
            return lista;
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return null;
    }
    
    public void insertMovimiento(Movimiento movimiento) throws SQLException{
        PreparedStatement ps = null;
        cn = conexion.getConexion();
        try {
            ps = cn.prepareStatement("INSERT INTO movimientos(cantidad, tipo_movimiento, "
                    + "id_producto, id_empleado, total, precio_unitario) VALUES(?, ?, ?, ?, ?, ?)");
            ps.setInt(1, movimiento.getCantidad());
            ps.setString(2, movimiento.getTipo_movimiento());
            //ps.setObject(3, movimiento.getFecha_hora());
            ps.setInt(3, Integer.parseInt(movimiento.getProducto()));
            ps.setInt(4, Integer.parseInt(movimiento.getUser_name()));
            ps.setDouble(5, movimiento.getTotal());
            ps.setDouble(6, movimiento.getPrecio_unitario());
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally{
            ps.close();
            cn.close();
        }
    }
    
    public boolean updateMovimiento(Movimiento movimiento) throws SQLException{
        PreparedStatement ps = null;
        cn = conexion.getConexion();
        
        try {
            ps = cn.prepareStatement("UPDATE movimientos SET cantidad = ?, id_producto = ? "
                    + "WHERE id_movimiento = ?");
            ps.setInt(1, movimiento.getCantidad());
            ps.setInt(2, Integer.valueOf(movimiento.getProducto()));
            ps.setInt(3, movimiento.getId_movimiento());
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
    
    public boolean deleteMovimiento(Integer id_movimiento) throws SQLException{
        PreparedStatement ps = null;
        cn = conexion.getConexion();
        
        try {
            ps = cn.prepareStatement("DELETE FROM movimientos WHERE id_movimiento = ?");
            ps.setInt(1, id_movimiento);
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
    
    public Movimiento getIdMovimiento(Integer id_movimiento){
        PreparedStatement ps;
        cn = conexion.getConexion();
        ResultSet rs;
        Movimiento movimiento = new Movimiento();
        
        try {
            ps = cn.prepareStatement("SELECT id_movimiento, cantidad, tipo_movimiento, P.nombre "
                    + "FROM movimiento AS M INNER JOIN producto AS P ON M.id_producto = P.id_producto "
                    + "WHERE id_movimiento = ?");
            ps.setInt(1, id_movimiento);
            rs = ps.executeQuery();
            while(rs.next()){
                movimiento.setId_movimiento(rs.getInt(1));
                movimiento.setCantidad(rs.getInt(2));
                movimiento.setTipo_movimiento(rs.getString(3));
                movimiento.setProducto(rs.getString(4));
            }
            return movimiento;
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return null;
    }
    
    public Integer getIdUserName(String user_name){
        PreparedStatement ps;
        ResultSet rs;
        Integer id_user_name = null;
        cn = conexion.getConexion();
        
        try {
            ps = cn.prepareStatement("SELECT id_empleado FROM login WHERE user_name = ?");
            ps.setString(1, user_name);
            rs = ps.executeQuery();
            while(rs.next())
                id_user_name = rs.getInt(1);
            return id_user_name;
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return null;
    }
    
    public void updateStockProducto(Integer cantidad, Integer id_producto){
        PreparedStatement ps;
        cn = conexion.getConexion();
        
        try {
            ps = cn.prepareStatement("UPDATE producto set stock = ? WHERE id_producto = ?");
            ps.setInt(1, cantidad);
            ps.setInt(2, id_producto);
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
    
    public Integer getCantidad(Integer id_producto){
        PreparedStatement ps;
        ResultSet rs;
        Integer cantidad = null;
        cn = conexion.getConexion();
       
        try {
            ps = cn.prepareStatement("SELECT stock FROM producto WHERE id_producto = ?");
            ps.setInt(1, id_producto);
            rs = ps.executeQuery();
            while(rs.next())
                cantidad = rs.getInt(1);
            return cantidad;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return null;
        }
    }
    
    public Double getPrecioProducto(String tipo, Integer id_producto){
        CallableStatement cs;
        ResultSet rs;
        Double precio_unitario = null;
        try {
            cs = cn.prepareCall("{CALL getPrecio(?, ?)}");
            cs.setString(1, tipo);
            cs.setInt(2, id_producto);
            rs = cs.executeQuery();
            while(rs.next()){
                precio_unitario = rs.getDouble("precio_unitario");
            }
            return precio_unitario;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return null;
        }
    }
}
