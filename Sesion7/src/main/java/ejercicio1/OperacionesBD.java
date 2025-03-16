package ejercicio1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OperacionesBD {

	private static Connection conexion;
    private static Statement st;
	
	public static void main(String[] args) {
		
		conexion = Conexion.getConexion();
		
		try {
			if(conexion != null){
				st = conexion.createStatement();
				// Operaciones
				crearTabla();
				insertarDatos();
				leerDatos();
				actualizarDatos();
				borrarDatos();
				borrarTabla();
			}else {
				System.out.println("Conexion fallida");
			}
		}catch(SQLException e){
			e.printStackTrace();
			if(conexion != null) {
				try {
					conexion.rollback();
				}catch(SQLException e1){
					e1.printStackTrace();
				}
			}
		}
	}
	
	// CREAR TABLA
	public static void crearTabla() throws SQLException{
		st.execute("use tienda_curso_serbatic");

        // Crea la tabla alumno (comprueba que NO existe)
        st.executeUpdate("CREATE TABLE IF NOT EXISTS alumno ("
                + "id INT AUTO_INCREMENT, "
                + "PRIMARY KEY(id), "
                + "nombre VARCHAR(255), "
                + "apellidos VARCHAR(255), "
                + "telefono VARCHAR(255)"
                + ")");
	}
	// INSERTAR DATOS
	public static void insertarDatos() throws SQLException{
		 String nombre = "Ernesto";
	     String apellidos = "Rodriguez Rodriguez";
	     String telefono = "645699537";

	     System.out.println("Insertar registro:");
	     String query = "INSERT INTO alumno (nombre, apellidos, telefono) " + "VALUES ('" + nombre + "', '" + apellidos + "', '" + telefono + "');";
	     System.out.println(query);
	     int resultado = st.executeUpdate(query);

	     if (resultado == 0) {
	    	 System.out.println("No se ha podido insertar");
	     }
	        
	     conexion.commit();
	}
	// LEER DATOS
	public static void leerDatos() throws SQLException{
		System.out.println("Consultar registros:");
        String query = "SELECT * FROM alumno";
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            String nombre = rs.getString(2);
            String apellidos = rs.getString(3);
            String telefono = rs.getString(4);

            System.out.println(nombre + "\t" + apellidos + "\t" + telefono);
        }
        rs.close();
	}
	// ACTUALIZAR DATOS
	public static void actualizarDatos() throws SQLException{
		System.out.println("Actualizar registro:");
        String query = "UPDATE alumno SET nombre='Paco' WHERE id=1";
        System.out.println(query);
        int res = st.executeUpdate(query);
        if (res == 0) {
            System.out.println("No se ha podido actualizar");
        }
	}
	// ELIMINAR DATOS
	public static void borrarDatos() throws SQLException{
		System.out.println("Borrar registro:");
        String query = "DELETE FROM alumno WHERE id=1";
        System.out.println(query);
        int res = st.executeUpdate(query);
        if (res == 0) {
            System.out.println("No se ha podido borrar");
        }
	}
	// BORRAR TABLA
	public static void borrarTabla() throws SQLException{
		st.executeUpdate("DROP TABLE IF EXISTS alumno");
	}
}
