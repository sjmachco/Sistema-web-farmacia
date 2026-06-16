/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import modelo.Empleado;
import modelo.Login;
import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author TIVITO
 */
public class Modelo_empleado {

    Connection cn;
    Conexion conexion = new Conexion();

    public List<Empleado> listEmployee(){
        cn = conexion.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = cn.prepareStatement("SELECT E.id_empleado, nombre, apellidos, direccion, dni, "
                    + "telefono, sexo, correo, sueldo, estado, L.user_name, L.clave FROM empleado AS E "
                    + "INNER JOIN login AS L ON E.id_empleado = L.id_empleado ORDER BY E.id_empleado");
            rs = ps.executeQuery();
            List<Empleado> list = new ArrayList<>();
            while (rs.next()) {
                Empleado e = new Empleado();
                e.setId_empleado(rs.getInt(1));
                e.setNombres(rs.getString(2));
                e.setApellidos(rs.getString(3));
                e.setDireccion(rs.getString(4));
                e.setDni(rs.getString(5));
                e.setTelefono(rs.getString(6));
                e.setSexo(rs.getString(7));
                e.setCorreo(rs.getString(8));
                e.setSueldo(rs.getDouble(9));
                e.setEstado(rs.getInt(10));
                e.setUsuario(rs.getString(11));
                e.setClave(rs.getString(12));
                list.add(e);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return null;
        } finally {
            conexion.desconectar();
        }
    }

    public List<String> listLogin() {
        cn = conexion.getConexion();
        PreparedStatement ps;
        ResultSet rs;
        List<String> list = new ArrayList<>();
        try {
            ps = cn.prepareStatement("SELECT user_name FROM login");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return null;
        } finally{
            conexion.desconectar();
        }
    }
    
    public List<String> listDni(){
        PreparedStatement ps;
        ResultSet rs;
        cn = conexion.getConexion();
        List<String> lista = new ArrayList<>();
        
        try {
            ps = cn.prepareStatement("SELECT dni FROM empleado");
            rs = ps.executeQuery();
            
            while(rs.next()){
                lista.add(rs.getString(1));
            }
            return lista;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return null;
        } finally{
            conexion.desconectar();
        }
    }

    public void createEmployee(Empleado empleado) throws SQLException {
        cn = conexion.getConexion();
        PreparedStatement ps = null;

        if (cn != null) {
            try {
                ps = cn.prepareStatement("INSERT INTO empleado(nombre, apellidos, "
                        + "direccion, dni, telefono, sexo, correo, sueldo, estado) "
                        + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
                ps.setString(1, empleado.getNombres());
                ps.setString(2, empleado.getApellidos());
                ps.setString(3, empleado.getDireccion());
                ps.setString(4, empleado.getDni());
                ps.setString(5, empleado.getTelefono());
                ps.setString(6, empleado.getSexo());
                ps.setString(7, empleado.getCorreo());
                ps.setDouble(8, empleado.getSueldo());
                ps.setInt(9, empleado.getEstado());
                ps.execute();
            } catch (SQLException e) {
                System.out.println(e.toString());
            } finally {
                conexion.desconectar();
            }
        }
    }

    public void createUsuario(Login login, Integer id_empleado){
        cn = conexion.getConexion();
        PreparedStatement ps = null;

        if (cn != null) {
            try {
                ps = cn.prepareStatement("INSERT INTO login (user_name, clave, id_empleado) VALUES(?, ?, ?)");
                ps.setString(1, login.getUser_name());
                ps.setString(2, login.getClave());
                ps.setInt(3, id_empleado);
                ps.execute();
            } catch (SQLException e) {
                System.out.println(e.toString());
            } finally{
                conexion.desconectar();
            }
        }
    }

    public boolean updateEmployee(Empleado empleado) throws SQLException {
        cn = conexion.getConexion();
        PreparedStatement ps = null;

        if (cn != null) {
            try {
                ps = cn.prepareStatement("UPDATE empleado SET nombre = ?, apellidos = ?, "
                        + "direccion = ?, telefono = ?, sexo = ?, correo = ?, sueldo = ?, estado = ? "
                        + "WHERE id_empleado = ?");
                ps.setString(1, empleado.getNombres());
                ps.setString(2, empleado.getApellidos());
                ps.setString(3, empleado.getDireccion());
                ps.setString(4, empleado.getTelefono());
                ps.setString(5, empleado.getSexo());
                ps.setString(6, empleado.getCorreo());
                ps.setDouble(7, empleado.getSueldo());
                ps.setInt(8, empleado.getEstado());
                ps.setInt(9, empleado.getId_empleado());
                if(ps.executeUpdate() == 1)
                    return true;
            } catch (SQLException e) {
                System.out.println(e.toString());
            } finally {
                conexion.desconectar();
            }
        }
        return false;
    }
    
    public boolean updateLogin(Empleado empleado){
        PreparedStatement ps;
        cn = conexion.getConexion();
        
        try {
            ps = cn.prepareStatement("UPDATE login SET clave = ? WHERE id_empleado = ?");
            ps.setString(1, empleado.getClave());
            ps.setInt(2, empleado.getId_empleado());
            if(ps.executeUpdate() == 1)
                return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally{
            conexion.desconectar();
        }
        return false;
    }

    public boolean deleteEmployee(Integer id_empleado) throws SQLException {
        cn = conexion.getConexion();
        PreparedStatement ps = null;

        if (cn != null) {
            try {
                ps = cn.prepareStatement("DELETE FROM empleado WHERE id_empleado = ?");
                ps.setInt(1, id_empleado);
                if(ps.executeUpdate() == 1)
                    return true;
            } catch (SQLException e) {
                System.out.println(e.toString());
            } finally {
                conexion.desconectar();
            }
        }
        return false;
    }

    public Integer getIdEmployee() {
        cn = conexion.getConexion();
        PreparedStatement ps;
        ResultSet rs;
        Integer id_empleado = null;
        if (cn != null) {
            try {
                ps = cn.prepareStatement("SELECT MAX(id_empleado) FROM empleado");
                rs = ps.executeQuery();
                while (rs.next()) {
                    id_empleado = rs.getInt(1);
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            } finally{
                conexion.desconectar();
            }
        }
        return id_empleado;
    }
    
    public Empleado getEmpleado(Integer id_empleado){
        PreparedStatement ps;
        ResultSet rs;
        cn = conexion.getConexion();
        Empleado empleado = new Empleado();
        
        try {
            ps = cn.prepareStatement("SELECT E.id_empleado, nombre, apellidos, direccion, "
                    + "dni, telefono, sexo, correo, sueldo, estado, L.user_name, L.clave FROM empleado as E "
                    + "INNER JOIN login AS L ON E.id_empleado = L.id_empleado WHERE E.id_empleado = ?");
            ps.setInt(1, id_empleado);
            rs = ps.executeQuery();
            
            while(rs.next()){
                empleado.setId_empleado(rs.getInt(1));
                empleado.setNombres(rs.getString(2));
                empleado.setApellidos(rs.getString(3));
                empleado.setDireccion(rs.getString(4));
                empleado.setDni(rs.getString(5));
                empleado.setTelefono(rs.getString(6));
                empleado.setSexo(rs.getString(7));
                empleado.setCorreo(rs.getString(8));
                empleado.setSueldo(rs.getDouble(9));
                empleado.setEstado(rs.getInt(10));
                empleado.setUsuario(rs.getString(11));
                empleado.setClave(rs.getString(12));
            }
            return empleado;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return null;
        } finally{
            conexion.desconectar();
        }
    }
    
    public Empleado getUsername(String user_name){
        PreparedStatement ps;
        ResultSet rs;
        cn = conexion.getConexion();
        Empleado empleado = new Empleado();
        
        try {
            ps = cn.prepareStatement("SELECT nombre, apellidos, direccion, dni, telefono, correo FROM empleado AS E "
                    + "INNER JOIN login AS L ON E.id_empleado = L.id_empleado WHERE L.user_name = ?");
            ps.setString(1, user_name);
            rs = ps.executeQuery();
            while(rs.next()){
                empleado.setNombres(rs.getString(1));
                empleado.setApellidos(rs.getString(2));
                empleado.setDireccion(rs.getString(3));
                empleado.setDni(rs.getString(4));
                empleado.setTelefono(rs.getString(5));
                empleado.setCorreo(rs.getString(6));
            }
            return empleado;
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally{
            conexion.desconectar();
        }
        return null;
    }
    
    public boolean existUsername(List<String> list, String user_name) {
        boolean existe = false;
        if (list != null) {
            for (String user : list) {
                if (Objects.equals(user, user_name)) {
                    existe = true;
                    break;
                }
            }
        }
        return existe;
    }
    
    public boolean existeDni(List<String> lista, String dni){
        boolean existe = false;
        if(lista != null){
            for(String list : lista){
                if(Objects.equals(list, dni)){
                    existe = true;
                    break;
                }
            }
        }
        return existe;
    }
}
