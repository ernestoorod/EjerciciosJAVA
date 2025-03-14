import java.util.*;

public class HundirLaFlota {
    private static int opcion;
    private Scanner scanner = new Scanner(System.in);
    private Map<String, Set<String>> usuario1Barcos = new HashMap<>();
    private Map<String, Set<String>> usuario2Barcos = new HashMap<>();
    private Set<String> usuario1Disparos = new HashSet<>();
    private Set<String> usuario2Disparos = new HashSet<>();
    private String[] barcos = {"Portaaviones (5 casillas)", "Acorazado (4 casillas)", "Crucero (3 casillas)", "Submarino (3 casillas)", "Destructor (2 casillas)"};
    private int[] tamanos = {5, 4, 3, 3, 2};
    private String ultimoResultado = null;
    public void mostrarOpciones() {
        String opciones[] = {"Empezar partida", "Reglas", "Resultado", "Salir"};
        System.out.println("\n===== HUNDIR LA FLOTA =====");
        for (int i = 0; i < opciones.length; i++) {
            System.out.println((i + 1) + ". " + opciones[i]);
        }
    }

    public void manejarOpcion(int opcion) {
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
                System.out.println("Esa opción no existe, elige otra.");
        }
    }

    public void jugarPartida() {
        System.out.println("¡Bienvenido a la partida de Hundir la Flota!");
        usuario1Barcos.clear();
        usuario2Barcos.clear();
        usuario1Disparos.clear();
        usuario2Disparos.clear();
        
        System.out.println("===========================");
        System.out.println("Usuario 1, coloca tus barcos:");
        colocarBarcos(usuario1Barcos);
        
        System.out.println("===========================");
        System.out.println("Usuario 2, coloca tus barcos:");
        colocarBarcos(usuario2Barcos);
        
        jugarTurnos();
    }

    private void colocarBarcos(Map<String, Set<String>> tablero) {
        for (int i = 0; i < barcos.length; i++) {
            System.out.println("Coloca tu " + barcos[i] + " ingresando " + tamanos[i] + " coordenadas separadas por espacio:");
            while (true) {
                String[] coordenadas = scanner.nextLine().split(" ");
                if (coordenadas.length != tamanos[i]) {
                    System.out.println("Número incorrecto de casillas. Deben ser exactamente " + tamanos[i] + ". Inténtalo de nuevo.");
                    continue;
                }
                boolean valido = true;
                Set<String> tempSet = new HashSet<>();
                for (String c : coordenadas) {
                    if (!c.matches("[A-J]([1-9]|10)")) {
                        System.out.println("Error: Coordenada inválida " + c + ". Usa una letra de A-J y un número del 1-10.");
                        valido = false;
                        break;
                    }
                    if (tablero.values().stream().anyMatch(set -> set.contains(c))) {
                        System.out.println("Error: Las coordenadas " + c + " ya están ocupadas. Intenta de nuevo.");
                        valido = false;
                        break;
                    }
                    tempSet.add(c);
                }
                if (valido && coordenadasConectadas(coordenadas)) {
                    tablero.put(barcos[i], tempSet);
                    break;
                } else {
                    System.out.println("Error: Las coordenadas deben ser contiguas y estar alineadas en fila o columna. Intenta de nuevo.");
                }
            }
        }
    }

    private boolean coordenadasConectadas(String[] coordenadas) {
        if (coordenadas.length == 1) return true;

        Set<Character> filas = new HashSet<>();
        Set<Integer> columnas = new HashSet<>();
        
        for (String c : coordenadas) {
            filas.add(c.charAt(0));
            columnas.add(Integer.parseInt(c.substring(1)));
        }
        
        if (filas.size() == 1) {
            List<Integer> listaColumnas = new ArrayList<>(columnas);
            Collections.sort(listaColumnas);
            return esConsecutivo(listaColumnas);
        }
        
        if (columnas.size() == 1) {
            List<Character> listaFilas = new ArrayList<>(filas);
            Collections.sort(listaFilas);
            return esConsecutivo(listaFilas);
        }

        return false;
    }

    private <T extends Comparable<T>> boolean esConsecutivo(List<T> lista) {
        for (int i = 1; i < lista.size(); i++) {
            if (lista.get(i - 1).compareTo(lista.get(i)) + 1 != 0) {
                return false;
            }
        }
        return true;
    }

    private void jugarTurnos() {
        boolean juegoActivo = true;
        boolean turnoUsuario1 = true;
        
        while (juegoActivo) {
            String jugador = turnoUsuario1 ? "Usuario 1" : "Usuario 2";
            Map<String, Set<String>> barcosOponente = turnoUsuario1 ? usuario2Barcos : usuario1Barcos;
            Set<String> disparosJugador = turnoUsuario1 ? usuario1Disparos : usuario2Disparos;
            
            System.out.println(jugador + ", ingresa la coordenada a disparar:");
            String disparo = scanner.nextLine();
            
            if (!disparo.matches("[A-J]([1-9]|10)")) {
                System.out.println("Error: Coordenada inválida. Intenta de nuevo.");
                continue;
            }
            if (disparosJugador.contains(disparo)) {
                System.out.println("Ya has disparado en esa coordenada. Intenta de nuevo.");
                continue;
            }
            
            disparosJugador.add(disparo);
            boolean impacto = false;
            for (Map.Entry<String, Set<String>> entry : barcosOponente.entrySet()) {
                if (entry.getValue().contains(disparo)) {
                    System.out.println("¡Impacto! Has acertado a un barco.");
                    System.out.println("\n");
                    entry.getValue().remove(disparo);
                    impacto = true;
                    if (entry.getValue().isEmpty()) {                   	
                        System.out.println("¡Has hundido el " + entry.getKey() + "!");
                        System.out.println("\n");
                        barcosOponente.remove(entry.getKey());
                    }
                    break;
                }
            }
            if (!impacto) {
                System.out.println("Agua. No has acertado.");
                System.out.println("\n");
                turnoUsuario1 = !turnoUsuario1;
            }
            if (barcosOponente.isEmpty()) {
                System.out.println(jugador + " ha ganado la partida. ¡Felicidades!");
                System.out.println("\n");
                ultimoResultado = jugador + " ha ganado la partida.";
                juegoActivo = false;
            }
        }
        resultado();
    }

    public void reglas() {
        System.out.println("Reglas básicas del juego Hundir la Flota");
        System.out.println("\n== Funcionamiento Básico del Juego ==");
        System.out.println("Cada jugador coloca sus barcos en un tablero y trata de hundir los barcos del oponente antes de que hundan los suyos.");
        volver();
    }

    public void resultado() {
        if (ultimoResultado != null) {
            System.out.println("Resultado de la última partida: " + ultimoResultado);
        } else {
            System.out.println("No hay partidas jugadas aún.");
        }
        volver();
    }

    public void salir() {
        System.out.println("Saliendo del juego... ¡Hasta la próxima!");
    }

    private void volver() {
        System.out.println("\nPresiona ENTER para volver al menú...");
        scanner.nextLine();
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        HundirLaFlota hundirLaFlota = new HundirLaFlota();

        while (opcion != 4) {
            hundirLaFlota.mostrarOpciones();
            System.out.println("===========================");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            hundirLaFlota.manejarOpcion(opcion);
        }
        scanner.close();
    }
}