import java.util.Scanner;

public class Banco {
    private static Scanner scanner = new Scanner(System.in);
    private static int opcion = 0;

    public static void main(String[] args) {
        Menu menu = new Menu();
        
        while (opcion != 6) {
            menu.mostrarOpciones();
            System.out.println("===================");
            System.out.print("Elige una de las opciones: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            
            menu.manejarOpciones(opcion);
        }
        
        System.out.println("Saliendo del programa... ¡Hasta la próxima!");
        scanner.close();
    }
}

class Menu {
    private Cliente cliente = new Cliente();
    private Empleado empleado = new Empleado();
    private Cuenta cuenta = new Cuenta();

    public void mostrarOpciones() {
        String opciones[] = {"Añadir cliente", "Mostrar clientes", "Consultar saldo", "Añadir empleado", "Mostrar empleados", "Crear cuenta", "Mostrar cuentas", "Salir"};
        System.out.println("\n====== BANCO ======");
        for (int i = 0; i < opciones.length; i++) {
            System.out.println((i + 1) + ". " + opciones[i]);
        }
    }

    public void manejarOpciones(int opcion) {
        switch (opcion) {
            case 1:
                cliente.añadirCliente();
                break;
            case 2:
                cliente.mostrarCliente();
                break;
            case 3:
                cliente.consultarSaldo();
                break;
            case 4:
                empleado.añadirEmpleado();
                break;
            case 5:
                empleado.mostrarEmpleado();
                break;
            case 6:
            	cuenta.crearCuenta();
                break;
            case 7:
            	cuenta.mostrarCuenta();
            	break;
            case 8:
            	System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Esa opción no existe");
        }
    }
}

class Cliente {
    private static Scanner scanner = new Scanner(System.in);
    private String[] cliente = new String[5];
    private double saldoTotal = 0;
    private boolean clienteRegistrado = false;

    public void añadirCliente() {
        System.out.println("Añadiendo cliente...");
        String[] opcionesCliente = {"DNI", "Nombre", "Primer Apellido", "Segundo Apellido", "Email"};

        for (int i = 0; i < opcionesCliente.length; i++) {
            System.out.print(opcionesCliente[i] + ": ");
            cliente[i] = scanner.nextLine();
        }

        System.out.print("Saldo inicial: ");
        saldoTotal = scanner.nextDouble();
        scanner.nextLine();

        clienteRegistrado = true;
        System.out.println("Cliente añadido correctamente!");
        Volver.esperar();
    }

    public void mostrarCliente() {
        if (!clienteRegistrado) {
            System.out.println("No hay cliente registrado. Debes añadir uno primero.");
            añadirCliente();
            return;
        }

        System.out.println("\n===== CLIENTE REGISTRADO =====");
        String[] opcionesCliente = {"DNI", "Nombre", "Primer Apellido", "Segundo Apellido", "Email"};
        
        for (int i = 0; i < cliente.length; i++) {
            System.out.println(opcionesCliente[i] + ": " + cliente[i]);
        }
        System.out.println("Saldo total: " + saldoTotal + "€");
        
        Volver.esperar();
    }

    public void consultarSaldo() {
        if (!clienteRegistrado) {
            System.out.println("No hay cliente registrado. Debes añadir uno primero.");
            añadirCliente();
            return;
        }

        System.out.println("Saldo total del cliente: " + saldoTotal + "€");
        Volver.esperar();
    }
}

class Empleado {
    public void añadirEmpleado() {
        System.out.println("Añadiendo empleado...");
        Volver.esperar();
    }
    
    public void mostrarEmpleado() {
    	System.out.println("Mostrando Empleados");
    	Volver.esperar();
    }
}

class Cuenta { // Para crear cuenta que te pida primero el tipo y despues para que cliente y si no existe cliente que te salte par crearlo antes
    public void crearCuenta() {
        System.out.println("Creando cuenta...");
        Volver.esperar();
    }
    
    public void mostrarCuenta() { // Para mostrar que te pida el tipo de la cuenta y despues algun campo del cliente 
    	System.out.println("Mostrado cuentas");
    	Volver.esperar();
    }
}

class Volver {
    private static Scanner scanner = new Scanner(System.in);
    public static void esperar() {
        System.out.println("Dale a ENTER para volver al menú...");
        scanner.nextLine();
    }
}
