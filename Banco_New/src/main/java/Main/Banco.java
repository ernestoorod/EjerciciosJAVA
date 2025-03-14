// Banco.java
package Main;

import Cliente.Cliente;
import Cliente.Contacto;
import Cliente.Direccion;
import Empleado.Empleado;
import Cuenta.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Banco {
    public static void main(String[] args) {
        // Crear dirección y contacto de clientes
        Direccion direccionCliente1 = new Direccion("Calle Mayor", "10", "2", "B", "Madrid", "España", "28013");
        Contacto contactoCliente1 = new Contacto(
            Arrays.asList("cliente1@email.com"),
            Arrays.asList("600123456", "911223344")
        );
        Cliente cliente1 = new Cliente("12345678A", "Juan", "Pérez", contactoCliente1, direccionCliente1);

        // Crear dirección y contacto de empleados
        Direccion direccionEmpleado1 = new Direccion("Gran Vía", "5", "1", "A", "Barcelona", "España", "08010");
        Contacto contactoEmpleado1 = new Contacto(
            Arrays.asList("empleado1@banco.com"),
            Arrays.asList("622334455", "931112233")
        );
        Empleado empleado1 = new Empleado("87654321B", "Ana", "López", "ana.lopez", "password123", contactoEmpleado1, direccionEmpleado1);

        // Crear cuentas
        CuentaNomina cuentaNomina = new CuentaNomina("12345678A", "111222333", 2000);
        CuentaVivienda cuentaVivienda = new CuentaVivienda("12345678A", "444555666", 5000, 100000);
        CuentaAhorro cuentaAhorro = new CuentaAhorro("12345678A", "777888999", 3000);

        // Imprimir información inicial
        System.out.println("--- Cliente ---");
        System.out.println(cliente1);

        System.out.println("\n--- Empleado ---");
        System.out.println(empleado1);

        System.out.println("\n--- Cuentas ---");
        System.out.println(cuentaNomina);
        System.out.println(cuentaVivienda);
        System.out.println(cuentaAhorro);

        // Realizar operaciones
        System.out.println("\n--- Operaciones bancarias ---");
        cuentaNomina.realizarIngreso(2000);
        System.out.println("Saldo tras ingreso en cuenta nómina: " + cuentaNomina.getSaldo());

        cuentaNomina.realizarTransferencia(500);
        System.out.println("Saldo tras transferencia en cuenta nómina: " + cuentaNomina.getSaldo());

        cuentaVivienda.cancelarImporteHipoteca(10000);
        System.out.println("Resto de hipoteca tras pago: " + cuentaVivienda.verRestoHipoteca());

        cuentaAhorro.realizarTransferenciaConComision(1000);
        System.out.println("Saldo tras transferencia en cuenta ahorro con comisión: " + cuentaAhorro.getSaldo());
        System.out.println("Comisiones cobradas: " + cuentaAhorro.verComisionesCobradas());

        
    }

    public static void transferirDinero(Map<String, Cuenta> cuentas, String cuentaOrigen, String cuentaDestino, double monto) {
        if (cuentas.containsKey(cuentaOrigen) && cuentas.containsKey(cuentaDestino)) {
            Cuenta origen = cuentas.get(cuentaOrigen);
            Cuenta destino = cuentas.get(cuentaDestino);

            if (origen.getSaldo() >= monto) {
                origen.realizarTransferencia(monto);
                destino.realizarIngreso(monto);
                System.out.println("Transferencia de " + monto + "€ de " + cuentaOrigen + " a " + cuentaDestino);
                System.out.println("Nuevo saldo en " + cuentaOrigen + ": " + origen.getSaldo());
                System.out.println("Nuevo saldo en " + cuentaDestino + ": " + destino.getSaldo());
            } else {
                System.out.println("Fondos insuficientes en " + cuentaOrigen);
            }
        } else {
            System.out.println("Una de las cuentas no existe.");
        }
    }
}
