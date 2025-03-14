package Empleado;

import Cliente.Contacto;
import Cliente.Direccion;

public class Empleado {
    private String dni;
    private String nombre;
    private String apellidos;
    private String usuario;
    private String contrasena;
    private Contacto contacto;
    private Direccion direccion;

    public Empleado(String dni, String nombre, String apellidos, String usuario, String contrasena, Contacto contacto, Direccion direccion) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.contacto = contacto;
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return dni + " - " + nombre + " " + apellidos + " (" + usuario + ")\n" + contacto + "\n" + direccion;
    }
}
