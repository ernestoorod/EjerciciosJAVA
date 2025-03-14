import java.util.Scanner;

public class HundirFlota {
    private static int opcion;
    private Scanner scanner = new Scanner(System.in);
    private static String jugador1 = "";
    private static String jugador2 = "";
    private static String[] jugador1barcos;
    private static String[] jugador2barcos;
    private static boolean[][] tableroJ1 = new boolean[10][10];
    private static boolean[][] tableroJ2 = new boolean[10][10];
    private String[] barcos = {"Portaaviones", "Acorazado", "Crucero", "Submarino", "Destructor"};
    private int[] tamanos = {5, 4, 3, 3, 2};

    public void mostrarOpciones() {
        String opciones[] = {"Iniciar el juego", "Reglas", "Resultado", "Salir"};

        System.out.println("\n==== HUNDIR LA FLOTA ====");
        for (int i = 0; i < opciones.length; i++) {
            System.out.println((i + 1) + ". " + opciones[i]);
        }
    }

    public void manejarOpciones(int opcion) {
        switch (opcion) {
            case 1:
                jugarPartida();
                break;
            case 2:
                reglas();
                break;
            case 3:
                resultado();
                break;
            case 4:
                salir();
                break;
            default:
                System.out.println("Esa opción no existe.");
        }
    }

    public void jugarPartida() {
        System.out.println("Vas a empezar el juego...");
        System.out.println("\nNombre del jugador 1:");
        jugador1 = scanner.nextLine();

        System.out.println("\nNombre del jugador 2:");
        jugador2 = scanner.nextLine();

        System.out.println(jugador1 + ", coloca tus barcos:");
        jugador1barcos = colocarBarcos(tableroJ1);

        System.out.println(jugador2 + ", coloca tus barcos:");
        jugador2barcos = colocarBarcos(tableroJ2);

        jugarTurnos();
    }

    private String[] colocarBarcos(boolean[][] tablero) {
        String[] barcosColocados = new String[5];

        System.out.println("Introduce las posiciones de tus barcos:");
        for (int i = 0; i < barcos.length; i++) {
            System.out.println("\nColoca tu " + barcos[i] + " (" + tamanos[i] + " casillas)");
            for (int j = 0; j < tamanos[i]; j++) {
                System.out.print("Casilla " + (j + 1) + " (ejemplo: A1): ");
                String posicion = scanner.nextLine().toUpperCase();
                int fila = posicion.charAt(0) - 'A';
                int columna = Integer.parseInt(posicion.substring(1)) - 1;
                tablero[fila][columna] = true;
                barcosColocados[i] += posicion + " ";
            }
        }
        System.out.println("\nBarcos colocados correctamente.");
        return barcosColocados;
    }

    private void jugarTurnos() {
        boolean turnoJ1 = true;
        while (!juegoTerminado()) {
            System.out.println("\nTurno de " + (turnoJ1 ? jugador1 : jugador2));
            boolean[][] tableroOponente = turnoJ1 ? tableroJ2 : tableroJ1;
            System.out.print("Introduce la casilla a atacar (ejemplo: B4): ");
            String ataque = scanner.nextLine().toUpperCase();
            int fila = ataque.charAt(0) - 'A';
            int columna = Integer.parseInt(ataque.substring(1)) - 1;
            
            if (tableroOponente[fila][columna]) {
                System.out.println("¡Impacto!");
                tableroOponente[fila][columna] = false;
            } else {
                System.out.println("Agua...");
            }
            turnoJ1 = !turnoJ1;
        }
        System.out.println("\n¡Juego terminado! Ganador: " + (juegoTerminado() ? (turnoJ1 ? jugador2 : jugador1) : "Nadie"));
    }

    private boolean juegoTerminado() {
        return barcosHundidos(tableroJ1) || barcosHundidos(tableroJ2);
    }

    private boolean barcosHundidos(boolean[][] tablero) {
        for (boolean[] fila : tablero) {
            for (boolean casilla : fila) {
                if (casilla) return false;
            }
        }
        return true;
    }

    public void reglas() {
        System.out.println("Cada jugador coloca 5 barcos en una cuadrícula de 10x10. Turnos alternos atacando posiciones hasta hundir todos los barcos del oponente.");
        volver();
    }

    public void resultado() {
        System.out.println("=== RESULTADO DE LA ÚLTIMA PARTIDA ===");
        if (jugador1.isEmpty() || jugador2.isEmpty()) {
            System.out.println("No hay partidas registradas aún.");
        } else {
            System.out.println("Ganador: " + (barcosHundidos(tableroJ1) ? jugador2 : jugador1));
        }
        volver();
    }

    public void salir() {
        System.out.println("Saliendo del programa... ¡Hasta la próxima!");
    }

    public void volver() {
        System.out.println("\nDale ENTER si quieres volver al menú...");
        scanner.nextLine();
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        HundirFlota hundirflota = new HundirFlota();
        opcion = 0;
        while (opcion != 4) {
            hundirflota.mostrarOpciones();
            System.out.println("=========================");
            System.out.println("Elije una de las acciones:");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
            hundirflota.manejarOpciones(opcion);
        }
        scanner.close();
    }
}
