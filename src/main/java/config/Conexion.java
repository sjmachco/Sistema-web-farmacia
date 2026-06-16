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
    private final String url = System.getenv("DB_URL_DEPLOY_SUPABASE");
    //private final String url = "jdbc:mysql://localhost:3306/farmacia";
    private final String driver = "org.postgresql.Driver";
    //private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String user = System.getenv("DB_USER_NAME_DEPLOY_SUPABASE");
    private final String pass = System.getenv("DB_PASSWORD_DEPLOY_SUPABASE");

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

    public void desconectar() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                conexion = null;
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
}
