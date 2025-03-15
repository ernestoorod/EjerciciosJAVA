package main;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

interface EnviarCorreo {
    void enviarCorreo(Persona receptor);
}

interface RecepcionCorreo {
    void bandejaEntrada();
    void bandejaSalida();
}

interface OperacionesCorreo extends EnviarCorreo, RecepcionCorreo {
}

abstract class Persona implements OperacionesCorreo {
    protected Scanner scanner = new Scanner(System.in);
    protected String TipoPersona;
    private ArrayList<Mensaje> bandejaEntrada;
    private ArrayList<Mensaje> bandejaSalida;
    
    public Persona() {
        this.bandejaEntrada = new ArrayList<>();
        this.bandejaSalida = new ArrayList<>();
    }

    public ArrayList<Mensaje> getBandejaEntrada() {
        return bandejaEntrada;
    }

    public ArrayList<Mensaje> getBandejaSalida() {
        return bandejaSalida;
    }

    public abstract void mostrarMenu();

    @Override
    public void enviarCorreo(Persona receptor) { 
        if (receptor == null) {
            System.out.println("Error: El destinatario no existe.");
            return;
        }
    
        Mensaje mensaje = new Mensaje(receptor.TipoPersona, "Mensaje 1", "Este es el primer mensaje", this, new Date());
        Mensaje mensaje2 = new Mensaje(receptor.TipoPersona, "Mensaje 2", "Este es el segundo mensaje", this, new Date());
    
        bandejaSalida.add(mensaje);
        bandejaSalida.add(mensaje2);
    
        receptor.getBandejaEntrada().add(mensaje);
        receptor.getBandejaEntrada().add(mensaje2);
    
        System.out.println("Correos enviados correctamente a " + receptor.TipoPersona);
    }

    @Override
    public void bandejaEntrada() {
            Scanner scanner = new Scanner(System.in);
            String[] opciones = {"Procesar buzón", "Responder mensaje", "Eliminar mensaje", "Salir"};
            int opcion;

            do {
                System.out.println("\n--- MENÚ BANDEJA DE ENTRADA ---");
                for (int i = 0; i < opciones.length; i++) {
                    System.out.println((i + 1) + ". " + opciones[i]);
                }
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        procesarBuzon();
                        break;
                    case 2:
                        responderMensaje();
                        break;
                    case 3:
                        eliminarMensaje();
                        break;
                    case 4:
                        System.out.println("Saliendo del menú...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } while (opcion != 4);
    }
    
    public void procesarBuzon() {      
        if(bandejaEntrada.isEmpty()) {
        	System.out.println("No hay mensajes en la bandeja de entrada.");
            return;
        }
        
        for(int i = 0; i < bandejaEntrada.size(); i++) {
        	Mensaje m = bandejaEntrada.get(i);
            System.out.println((i + 1) + ". Asunto: " + m.getAsunto() + " - Fecha: " + m.getFechaEnvio());
        }
        
        System.out.println("Seleccione un mensaje para leer (escriba 0 si quiere salir): ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        if (opcion > 0 && opcion <= bandejaEntrada.size()) {
            Mensaje m = bandejaEntrada.get(opcion - 1);
            System.out.println("\n=== Mensaje ===");
            System.out.println("Asunto: " + m.getAsunto());
            System.out.println("Para: " + m.getDestinatario());
            System.out.println("Fecha: " + m.getFechaEnvio());
            System.out.println("Contenido: " + m.getContenido());
        } else if (opcion != 0) {
            System.out.println("Opción inválida.");
        }
    }
    
    public void responderMensaje() {
        if (bandejaEntrada.isEmpty()) {
            System.out.println("No hay mensajes en la bandeja de entrada.");
            return;
        }

        for (int i = 0; i < bandejaEntrada.size(); i++) {
            Mensaje m = bandejaEntrada.get(i);
            System.out.println((i + 1) + ". Asunto: " + m.getAsunto() + " - Fecha: " + m.getFechaEnvio());
        }

        System.out.println("Seleccione un mensaje para responder (escriba 0 si quiere salir): ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        if (opcion > 0 && opcion <= bandejaEntrada.size()) {
            Mensaje mensajeOriginal = bandejaEntrada.get(opcion - 1);
            System.out.println("Escribe la respuesta: ");
            String respuesta = scanner.nextLine();

            Mensaje respuestaMensaje = new Mensaje(
                mensajeOriginal.getRemitente().TipoPersona,
                "Re: " + mensajeOriginal.getAsunto(),
                respuesta,
                this,
                new Date()
            );

            enviarCorreo(mensajeOriginal.getRemitente());
            bandejaSalida.add(respuestaMensaje);

            System.out.println("Respuesta enviada correctamente.");
        } else if (opcion != 0) {
            System.out.println("Opción inválida.");
        }
    }
    
    public void eliminarMensaje() {
    	 if (bandejaEntrada.isEmpty()) {
             System.out.println("No hay mensajes para eliminar.");
             return;
         }

         Scanner scanner = new Scanner(System.in);
         System.out.print("Seleccione el número del mensaje a eliminar: ");
         int opcion = scanner.nextInt();
         scanner.nextLine();

         if (opcion > 0 && opcion <= bandejaEntrada.size()) {
             bandejaEntrada.remove(opcion - 1);
             System.out.println("Mensaje eliminado.");
         } else {
             System.out.println("Selección no válida.");
         }
    }

    @Override
    public void bandejaSalida() {
        System.out.println("Mostrando bandeja de salida...");
        if (bandejaSalida.isEmpty()) {
            System.out.println("No hay mensajes en la bandeja de salida.");
            return;
        }

        for (int i = 0; i < bandejaSalida.size(); i++) {
            Mensaje m = bandejaSalida.get(i);
            System.out.println((i + 1) + ". Asunto: " + m.getAsunto() + " - Fecha: " + m.getFechaEnvio());
        }

        System.out.println("Seleccione un mensaje para leer (escriba 0 si quiere salir): ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        if (opcion > 0 && opcion <= bandejaSalida.size()) {
            Mensaje m = bandejaSalida.get(opcion - 1);
            System.out.println("\n=== Mensaje ===");
            System.out.println("Asunto: " + m.getAsunto());
            System.out.println("Para: " + m.getDestinatario());
            System.out.println("Fecha: " + m.getFechaEnvio());
            System.out.println("Contenido: " + m.getContenido());
        } else if (opcion != 0) {
            System.out.println("Opción inválida.");
        }
    }
}

class Cliente extends Persona {
    public Cliente() {
        this.TipoPersona = "Cliente";
    }
    
    @Override
    public void mostrarMenu() {
        String[] opciones = {"Enviar correo", "Bandeja de entrada", "Bandeja de salida", "Salir"};
        int opcion;
        
        do {
            System.out.println("\n=== MENÚ CLIENTE ===");
            for (int i = 0; i < opciones.length; i++) {
                System.out.println((i + 1) + ". " + opciones[i]);
            }
            System.out.print("Elija una acción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcion) {
                case 1:
                    enviarCorreo(Correo.getEmpleado()); // Enviar correo al empleado
                    break;
                case 2:
                    bandejaEntrada();
                    break;
                case 3:
                    bandejaSalida();
                    break;
                case 4:
                    System.out.println("Saliendo del menú cliente...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }
}

class Empleado extends Persona {
    public Empleado() {
        this.TipoPersona = "Empleado";
    }
    
    @Override
    public void mostrarMenu() {
        String[] opciones = {"Enviar correo", "Bandeja de entrada", "Bandeja de salida", "Salir"};
        int opcion;
        
        do {
            System.out.println("\n=== MENÚ EMPLEADO ===");
            for (int i = 0; i < opciones.length; i++) {
                System.out.println((i + 1) + ". " + opciones[i]);
            }
            System.out.print("Elija una acción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcion) {
                case 1:
                    enviarCorreo(Correo.getCliente()); // Enviar correo al cliente
                    break;
                case 2:
                    bandejaEntrada();
                    break;
                case 3:
                    bandejaSalida();
                    break;
                case 4:
                    System.out.println("Saliendo del menú empleado...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }
}

class Mensaje {
    private String destinatario;
    private String asunto;
    private String contenido;
    private Persona remitente;
    private Date fechaEnvio;
    
    public Mensaje(String destinatario, String asunto, String contenido, Persona remitente, Date fechaEnvio) {
        this.destinatario = destinatario;
        this.asunto = asunto;
        this.contenido = contenido;
        this.remitente = remitente;
        this.fechaEnvio = fechaEnvio;
    }

    public String getDestinatario() { return destinatario; }
    public void setDestinatario(String destinatario) { this.destinatario = destinatario; }

    public String getAsunto() { return asunto; }
    public void setAsunto(String asunto) { this.asunto = asunto; }

    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }

    public Persona getRemitente() { return remitente; }
    public void setRemitente(Persona remitente) { this.remitente = remitente; }

    public Date getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(Date fechaEnvio) { this.fechaEnvio = fechaEnvio; }
}

public class Correo {
    private static Cliente cliente = new Cliente();
    private static Empleado empleado = new Empleado();

    public static Cliente getCliente() {
        return cliente;
    }

    public static Empleado getEmpleado() {
        return empleado;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Cliente");
            System.out.println("2. Empleado");
            System.out.println("3. Salir");
            System.out.print("Seleccione el tipo de usuario: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcion) {
                case 1:
                    cliente.mostrarMenu();
                    break;
                case 2:
                    empleado.mostrarMenu();
                    break;
                case 3:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 3);
        
        scanner.close();
    }
}