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
import config.Conexion;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Modelo_login {

    Connection cn;
    Conexion conexion = new Conexion();

    public List<Login> listLogin() {
        PreparedStatement ps;
        cn = conexion.getConexion();
        ResultSet rs;
        Login login = new Login();
        List<Login> lista = new ArrayList<>();
        try {
            ps = cn.prepareStatement("SELECT id_login, user_name, clave, id_empleado FROM login");
            rs = ps.executeQuery();
            while (rs.next()) {
                login.setId_login(rs.getInt(1));
                login.setUser_name(rs.getString(2));
                login.setClave(rs.getString(3));
                login.setId_empleado(rs.getInt(4));
                lista.add(login);
            }
            return lista;
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public Integer validarLogin(String user_name, String pass) {
        PreparedStatement ps;
        ResultSet rs;
        cn = conexion.getConexion();
        Integer valor = 0;

        try {
            ps = cn.prepareStatement("SELECT COUNT(*) FROM login WHERE user_name = ? AND clave = ?");
            ps.setString(1, user_name);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                valor = rs.getInt(1);
            }
            return valor;
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return null;
    }
    
    public String getNameLogin(String user_name){
        PreparedStatement ps;
        ResultSet rs;
        cn = conexion.getConexion();
        String nombre = null;
        
        try {
            ps = cn.prepareStatement("SELECT E.nombre FROM login AS L "
                    + "INNER JOIN empleado AS E ON L.id_empleado = E.id_empleado WHERE user_name = ?");
            ps.setString(1, user_name);
            rs = ps.executeQuery();
            while(rs.next())
                nombre = rs.getString(1);
            return nombre;
            
        } catch (SQLException e) {
            System.out.println(e.toString());
            return null;
        }
    }
}
