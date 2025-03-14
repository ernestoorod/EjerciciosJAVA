package Cliente;

import java.util.List;

public class Cliente {
    private String dni;
    private String nombre;
    private String apellidos;
    private Contacto contacto;
    private Direccion direccion;
    private double saldoTotal;

    public Cliente(String dni, String nombre, String apellidos, Contacto contacto, Direccion direccion) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.contacto = contacto;
        this.direccion = direccion;
        this.saldoTotal = 0.0; // Inicialmente sin saldo
    }

    public void actualizarSaldo(double saldo) {
        this.saldoTotal += saldo;
    }

    @Override
    public String toString() {
        return dni + " - " + nombre + " " + apellidos + "\n" + contacto + "\n" + direccion + "\nSaldo Total: " + saldoTotal;
    }
}
