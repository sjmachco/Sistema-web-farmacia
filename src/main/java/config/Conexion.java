package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TIVE
 */
public class Conexion {
    Connection conexion = null;
    private final String url = "jdbc:mysql://localhost:3306/farmacia";
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String user = "root";
    private final String pass = "admin";

    public Connection getConexion() {
        //Connection cn = null;
        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(url, user, pass);
            //System.out.println("Conexion exitosa");
            return conexion;
        } catch (SQLException e) {
            System.out.println(e.toString());
            //System.out.println("No conecto!");
            return null;
        } catch (ClassNotFoundException ex) {
            //System.out.println("No se conecto!");
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void desconectar(){
        conexion = null;
    }
}
