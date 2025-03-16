package ejercicio1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    static String bd = "tienda_curso_serbatic";
    static String login = "root";
    static String password = "";
    static String host = "localhost"; // localhost
    
    static String url = "jdbc:mysql://";
    static Connection conexion; // atributo para guardar el objeto Connection
        
    public static Connection getConexion() {
        // SINGLETON
        if (conexion == null) {
            if (crearConexion()) {
                System.out.println("Conexión establecida correctamente.");
            } else {
                System.out.println("Error al establecer la conexión.");
            }
        }
        return conexion;
    }
    
    // devuelve true si se ha creado correctamente
    public static boolean crearConexion() {
        try {
            // cargo el driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url + host + "/" + bd, login, password);    
            conexion.setAutoCommit(false);
            return true;
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error general: " + e.getMessage());
            return false;
        }
    }

    public static void desconectar() {
        try {
            if (conexion != null) {
                conexion.close();
                conexion = null;
                System.out.println("La conexión a la base de datos " + bd + " ha terminado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        getConexion();
        desconectar();
    }
}
