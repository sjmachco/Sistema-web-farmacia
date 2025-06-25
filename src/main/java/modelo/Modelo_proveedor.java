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
public class Modelo_proveedor {

    Conexion conexion = new Conexion();
    Connection cn;

    public List<Proveedor> listProveedor() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Proveedor> list = new ArrayList<>();
        cn = conexion.getConexion();
        try {
            ps = cn.prepareStatement("SELECT id_proveedor, nombre, ruc, telefono, "
                    + "direccion, pais, estado FROM proveedor");
            rs = ps.executeQuery();
            while (rs.next()) {
                Proveedor prov = new Proveedor();
                prov.setId_proveedor(rs.getInt(1));
                prov.setNombre(rs.getString(2));
                prov.setRuc(rs.getString(3));
                prov.setTelefono(rs.getString(4));
                prov.setDireccion(rs.getString(5));
                prov.setPais(rs.getString(6));
                prov.setEstado(rs.getInt(7));
                list.add(prov);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public void insertProveedor(Proveedor proveedor) throws SQLException {
        PreparedStatement ps = null;
        cn = conexion.getConexion();

        try {
            ps = cn.prepareStatement("INSERT INTO proveedor(nombre, ruc, telefono, "
                    + "direccion, pais, estado) VALUES(?, ?, ?, ?, ?, ?)");
            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getRuc());
            ps.setString(3, proveedor.getTelefono());
            ps.setString(4, proveedor.getDireccion());
            ps.setString(5, proveedor.getPais());
            ps.setInt(6, proveedor.getEstado());
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            ps.close();
            cn.close();
        }
    }

    public boolean updateProveedor(Proveedor proveedor) throws SQLException {
        PreparedStatement ps = null;
        cn = conexion.getConexion();

        try {
            ps = cn.prepareStatement("UPDATE proveedor set nombre = ?, ruc = ?, "
                    + "telefono = ?, direccion = ?, pais = ?, estado = ? where id_proveedor = ?");
            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getRuc());
            ps.setString(3, proveedor.getTelefono());
            ps.setString(4, proveedor.getDireccion());
            ps.setString(5, proveedor.getPais());
            ps.setInt(6, proveedor.getEstado());
            ps.setInt(7, proveedor.getId_proveedor());
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            ps.close();
            cn.close();
        }
        return false;
    }

    public boolean existeProveedor(List<String> lista, String nombre) {
        boolean existe = false;
        for (String prov : lista) {
            if (Objects.equals(prov, nombre)) {
                existe = true;
                break;
            }
        }
        return existe;
    }

    public List<String> listProveedorName() {
        List<String> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        cn = conexion.getConexion();

        try {
            ps = cn.prepareStatement("SELECT nombre FROM proveedor");
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString(1));
            }
            return lista;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public boolean deleteProveedor(Integer id_proveedor) throws SQLException {
        PreparedStatement ps = null;
        cn = conexion.getConexion();

        try {
            ps = cn.prepareStatement("DELETE FROM proveedor WHERE id_proveedor = ?");
            ps.setInt(1, id_proveedor);
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            ps.close();
            cn.close();
        }
        return false;
    }

    public Proveedor getProveedorId(Integer id_proveedor) {
        PreparedStatement ps = null;
        cn = conexion.getConexion();
        ResultSet rs = null;
        Proveedor proveedor = new Proveedor();
        try {
            ps = cn.prepareStatement("SELECT id_proveedor, nombre, ruc, telefono, "
                    + "direccion, pais, estado FROM proveedor WHERE id_proveedor = ?");
            ps.setInt(1, id_proveedor);
            rs = ps.executeQuery();
            while (rs.next()) {
                proveedor.setId_proveedor(rs.getInt(1));
                proveedor.setNombre(rs.getString(2));
                proveedor.setRuc(rs.getString(3));
                proveedor.setTelefono(rs.getString(4));
                proveedor.setDireccion(rs.getString(5));
                proveedor.setPais(rs.getString(6));
                proveedor.setEstado(rs.getInt(7));
            }
            return proveedor;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
}
