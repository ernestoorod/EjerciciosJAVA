/**
*
*	Principalmente lo que necesito es un menu de opciones
*	en el que salgan las opciones tipicas de iniciar el juego
*	reglas, resultado y salir.
*
*	Despues la funcionalidad seran un cuadro de 3 x 3 en el que
*	el jugador 1 coloca circulos y el jugador2 tacha
*	
*	La partida termina cuando uno de los dos jugadores haga tres en raya
*	
*	Cosas a tener en cuenta:
*	- No puedes poner un circulo en una X, ni un circulo enciam de otro, ni una X encima de otra
*	- Tiene que ir por rondas primero siempre el jugador 1 y despues el 2 y asi todo el rato
*	- Si se completan todos los cuadros y no hay ganador es empate
*
*/

import java.util.Scanner;

public class TresEnRaya {
    private static char[][] tablero = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };
    private static char jugadorActual = 'X';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean juegoEnCurso = true;

        while (juegoEnCurso) {
            imprimirTablero();
            System.out.println("Jugador " + jugadorActual + ", ingrese su movimiento (fila y columna, ej: 1 2): ");
            int fila = scanner.nextInt() - 1;
            int columna = scanner.nextInt() - 1;

            if (esMovimientoValido(fila, columna)) {
                tablero[fila][columna] = jugadorActual;
                if (verificarGanador()) {
                    imprimirTablero();
                    System.out.println("¡El jugador " + jugadorActual + " ha ganado!");
                    juegoEnCurso = false;
                } else if (esEmpate()) {
                    imprimirTablero();
                    System.out.println("¡Empate!");
                    juegoEnCurso = false;
                } else {
                    cambiarTurno();
                }
            } else {
                System.out.println("Movimiento inválido, intenta de nuevo.");
            }
        }
        scanner.close();
    }

    private static void imprimirTablero() {
        System.out.println("-------------");
        for (char[] fila : tablero) {
            System.out.print("| ");
            for (char celda : fila) {
                System.out.print(celda + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    private static boolean esMovimientoValido(int fila, int columna) {
        return fila >= 0 && fila < 3 && columna >= 0 && columna < 3 && tablero[fila][columna] == ' ';
    }

    private static void cambiarTurno() {
        jugadorActual = (jugadorActual == 'X') ? 'O' : 'X';
    }

    private static boolean verificarGanador() {
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] == jugadorActual && tablero[i][1] == jugadorActual && tablero[i][2] == jugadorActual) return true;
            if (tablero[0][i] == jugadorActual && tablero[1][i] == jugadorActual && tablero[2][i] == jugadorActual) return true;
        }
        if (tablero[0][0] == jugadorActual && tablero[1][1] == jugadorActual && tablero[2][2] == jugadorActual) return true;
        if (tablero[0][2] == jugadorActual && tablero[1][1] == jugadorActual && tablero[2][0] == jugadorActual) return true;
        return false;
    }

    private static boolean esEmpate() {
        for (char[] fila : tablero) {
            for (char celda : fila) {
                if (celda == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}

