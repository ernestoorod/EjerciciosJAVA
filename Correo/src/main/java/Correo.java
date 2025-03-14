import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

interface EnviarCorreo {
    void enviar(Mensaje m);
}

interface RecepcionCorreo {
    void recibir();
}

interface OperacionesCorreo extends EnviarCorreo, RecepcionCorreo {
}

class Persona implements OperacionesCorreo {
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

    @Override
    public void enviar(Mensaje m) {
        m.getDestinatario().getBandejaEntrada().add(m);
        this.bandejaSalida.add(m);
    }

    @Override
    public void recibir() {
        menuRecibir();
    }

    public void menuRecibir() {
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
        if (bandejaEntrada.isEmpty()) {
            System.out.println("No hay mensajes en la bandeja de entrada.");
            return;
        }

        System.out.println("Mensajes en bandeja de entrada:");
        for (int i = 0; i < bandejaEntrada.size(); i++) {
            Mensaje m = bandejaEntrada.get(i);
            System.out.println((i + 1) + ". " + m.getAsunto() + " - " + (m.isVisto() ? "Leído" : "No leído"));
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Seleccione un mensaje para leer (0 para salir): ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        if (opcion > 0 && opcion <= bandejaEntrada.size()) {
            Mensaje mensaje = bandejaEntrada.get(opcion - 1);
            mensaje.marcarComoVisto();
            System.out.println("Asunto: " + mensaje.getAsunto());
            System.out.println("De: " + mensaje.getRemitente());
            System.out.println("Fecha: " + mensaje.getFechaCreacion());
            System.out.println("Contenido: " + mensaje.getContenido());
        }
    }

    public void responderMensaje() {
        if (bandejaEntrada.isEmpty()) {
            System.out.println("No hay mensajes para responder.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Seleccione el número del mensaje a responder: ");
        int indice = scanner.nextInt();
        scanner.nextLine();

        if (indice > 0 && indice <= bandejaEntrada.size()) {
            Mensaje mensajeOriginal = bandejaEntrada.get(indice - 1);
            System.out.println("Escriba su respuesta:");
            String respuesta = scanner.nextLine();

            Mensaje respuestaMensaje = new Mensaje(
                respuesta,
                mensajeOriginal.getDestinatario(),
                "Re: " + mensajeOriginal.getAsunto(),
                "Respuesta de " + this,
                new Date()
            );

            enviar(respuestaMensaje);
            System.out.println("Mensaje enviado.");
        } else {
            System.out.println("Selección no válida.");
        }
    }

    public void eliminarMensaje() {
        if (bandejaEntrada.isEmpty()) {
            System.out.println("No hay mensajes para eliminar.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Seleccione el número del mensaje a eliminar: ");
        int indice = scanner.nextInt();
        scanner.nextLine();

        if (indice > 0 && indice <= bandejaEntrada.size()) {
            bandejaEntrada.remove(indice - 1);
            System.out.println("Mensaje eliminado.");
        } else {
            System.out.println("Selección no válida.");
        }
    }
}

class Cliente extends Persona {
}

class Empleado extends Persona {
}

class Mensaje {
    private String contenido;
    private Persona destinatario;
    private String asunto;
    private String remitente;
    private Date fechaCreacion;
    private boolean visto;

    public Mensaje(String contenido, Persona destinatario, String asunto, String remitente, Date fechaCreacion) {
        this.contenido = contenido;
        this.destinatario = destinatario;
        this.asunto = asunto;
        this.remitente = remitente;
        this.fechaCreacion = fechaCreacion;
        this.visto = false;
    }

    public String getContenido() {
    	return contenido; 
    }
    
    public Persona getDestinatario() {
    	return destinatario; 
    }
    
    public String getAsunto() {
    	return asunto; 
    }
    
    public String getRemitente() {
    	return remitente; 
    }
    
    public Date getFechaCreacion() {
    	return fechaCreacion; 
    }
    
    public boolean isVisto() {
    	return visto; 
    }
    
    public void marcarComoVisto() {
    	this.visto = true; 
    }
}

public class Correo {
    public static void main(String[] args) {
        Empleado emp = new Empleado();
        Cliente cli = new Cliente();

        Mensaje m1 = new Mensaje("Hola, ¿cómo estás?", cli, "Saludo", "Empleado1", new Date());
        Mensaje m2 = new Mensaje("Necesito ayuda con mi cuenta", cli, "Soporte", "Empleado1", new Date());
        
        emp.enviar(m1);
        emp.enviar(m2);

        cli.recibir();
    }
}
