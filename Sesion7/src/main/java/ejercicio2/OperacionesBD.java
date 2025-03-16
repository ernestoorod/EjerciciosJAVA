package ejercicio2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OperacionesBD {

    private static Connection conexion;
    private static Statement st;

    public static void main(String[] args) {
        conexion = Conexion.getConexion();
        
        try {
            if (conexion != null) {
                st = conexion.createStatement();
                // Operaciones
                crearTabla();
                insertarDatos();
                leerDatos();
                actualizarDatos();
                borrarDatos();
                recuperarPedidos();
                borrarTabla();
            } else {
                System.out.println("Conexion fallida");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (conexion != null) {
                try {
                    conexion.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public static void crearTabla() throws SQLException {
        st.execute("USE tienda_curso_serbatic");
        st.executeUpdate("CREATE TABLE IF NOT EXISTS alumno ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "nombre VARCHAR(255), "
                + "apellidos VARCHAR(255), "
                + "telefono VARCHAR(255))");
    }

    public static void insertarDatos() throws SQLException {
        String nombre = "Ernesto";
        String apellidos = "Rodriguez Rodriguez";
        String telefono = "645699537";

        String query = "INSERT INTO alumno (nombre, apellidos, telefono) VALUES ('" + nombre + "', '" + apellidos + "', '" + telefono + "');";
        int resultado = st.executeUpdate(query);

        if (resultado == 0) {
            System.out.println("No se ha podido insertar");
        }
        conexion.commit();
    }

    public static void leerDatos() throws SQLException {
        String query = "SELECT * FROM alumno";
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            System.out.println(rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4));
        }
        rs.close();
    }

    public static void actualizarDatos() throws SQLException {
        String query = "UPDATE alumno SET nombre='Paco' WHERE id=1";
        int res = st.executeUpdate(query);
        if (res == 0) {
            System.out.println("No se ha podido actualizar");
        }
    }

    public static void borrarDatos() throws SQLException {
        String query = "DELETE FROM alumno WHERE id=1";
        int res = st.executeUpdate(query);
        if (res == 0) {
            System.out.println("No se ha podido borrar");
        }
    }

    public static void borrarTabla() throws SQLException {
        st.executeUpdate("DROP TABLE IF EXISTS alumno");
    }

    public static List<String> recuperarPedidos() throws SQLException {
        List<String> pedidos = new ArrayList<>();
        String query = "SELECT p.id, p.fecha, u.nombre FROM pedidos p INNER JOIN usuarios u ON p.usuario_id = u.id";
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            String pedido = "Pedido ID: " + rs.getInt(1) + ", Fecha: " + rs.getDate(2) + ", Usuario: " + rs.getString(3);
            pedidos.add(pedido);
        }
        rs.close();

        // Imprimir pedidos recuperados
        for (String pedido : pedidos) {
            System.out.println(pedido);
        }

        return pedidos;
    }
}
