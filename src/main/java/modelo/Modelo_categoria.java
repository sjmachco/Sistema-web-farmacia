/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import config.Conexion;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author TIVITO
 */
public class Modelo_categoria {

    public List<Categoria> listCategoria() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion conexion = new Conexion();
        Connection cn = conexion.getConexion();
        List<Categoria> list = new ArrayList<>();
        try {
            ps = cn.prepareStatement("SELECT id_categoria, nombre, estado FROM categoria");
            rs = ps.executeQuery();
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId_categoria(rs.getInt(1));
                categoria.setNombre(rs.getString(2));
                categoria.setEstado(rs.getInt(3));
                list.add(categoria);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return null;
        } finally {
            conexion.desconectar();
        }
    }

    public void insertCategoria(Categoria categoria) throws SQLException {
        PreparedStatement ps = null;
        Conexion conexion = new Conexion();
        Connection cn = conexion.getConexion();
        try {
            ps = cn.prepareStatement("INSERT INTO categoria(nombre, estado) VALUES(?, ?)");
            ps.setString(1, categoria.getNombre());
            ps.setInt(2, categoria.getEstado());
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            conexion.desconectar();
        }
    }

    public boolean updateCategoria(Categoria categoria) throws SQLException {
        PreparedStatement ps = null;
        Conexion conexion = new Conexion();
        Connection cn = conexion.getConexion();
        try {
            ps = cn.prepareStatement("UPDATE categoria set nombre = ?, estado = ? WHERE id_categoria = ?");
            ps.setString(1, categoria.getNombre());
            ps.setInt(2, categoria.getEstado());
            ps.setInt(3, categoria.getId_categoria());
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            conexion.desconectar();
        }
        return false;
    }

    public boolean deleteCategoria(Integer id_categoria) throws SQLException {
        PreparedStatement ps = null;
        Conexion conexion = new Conexion();
        Connection cn = conexion.getConexion();
        try {
            ps = cn.prepareStatement("DELETE FROM categoria where id_categoria = ?");
            ps.setInt(1, id_categoria);
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            conexion.desconectar();
        }
        return false;
    }

    public boolean existCategoria(List<String> list, String nombre) {
        boolean existe = false;
        for (String cat : list) {
            if (Objects.equals(cat, nombre)) {
                existe = true;
                break;
            }
        }
        return existe;
    }

    public List<String> listCategoriaName() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion conexion = new Conexion();
        Connection cn = conexion.getConexion();
        List<String> list = new ArrayList<>();
        try {
            ps = cn.prepareStatement("SELECT nombre FROM categoria");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return null;
        } finally {
            conexion.desconectar();
        }
    }

    public Categoria getCategoria(Integer id_cat) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion conexion = new Conexion();
        Connection cn = conexion.getConexion();
        Categoria categoria = new Categoria();
        try {
            ps = cn.prepareStatement("SELECT id_categoria, nombre, estado FROM categoria WHERE id_categoria = ?");
            ps.setInt(1, id_cat);
            rs = ps.executeQuery();
            while (rs.next()) {
                categoria.setId_categoria(rs.getInt(1));
                categoria.setNombre(rs.getString(2));
                categoria.setEstado(rs.getInt(3));
            }
            return categoria;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return null;
        } finally {
            conexion.desconectar();
        }
    }
}
