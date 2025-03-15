import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Métodos para insertar elementos, Métodos para eliminar elementos, Métodos para examinar elementos, Métodos adicionales
 */

public class Colas {
    public static void main(String[] args) {
        
        Deque<String> deque = new ArrayDeque<>();
        
        /*
         *	METODOS PARA INSERTAR ELEMENTOS
         */
        
        // Insertar elementos al inicio de la deque
        deque.addFirst("A");
        
        // Insertar elementos al final de la deque
        deque.addLast("B");
        
        // Inserta el elemento al inicio de la deque; devuelve false si no se puede insertar
        try {
            deque.addFirst(null);
            deque.addFirst("C");
        } catch (Exception e) {
            System.out.println("false");
        }
        
        // Inserta el elemento al final de la deque; devuelve false si no se puede insertar.
        try {
            deque.addLast(null);
            deque.addLast("D");
        } catch (Exception e) {
            System.out.println("false");
        }
        
        System.out.println(deque);
        
        /*
         *	METODOS PARA ELIMINAR ELEMENTOS
         */
        
        // Elimina y devuelve el primer elemento; lanza una excepción si la deque está vacía
        try {
            String primero = deque.removeFirst();
            System.out.println("Primer elemento eliminado: " + primero);
        } catch (Exception e) {
            System.out.println("La deque está vacía");
        }
        
        // Elimina y devuelve el último elemento; lanza una excepción si la deque está vacía.
        try {
            String ultimo = deque.removeLast();
            System.out.println("Último elemento eliminado: " + ultimo);
        } catch (Exception e) {
            System.out.println("La deque está vacía");
        }
        
        // Elimina y devuelve el primer elemento; devuelve null si la deque está vacía.
        String primero = deque.pollFirst();
        System.out.println("Primer elemento eliminado (pollFirst): " + primero);
        
        // Elimina y devuelve el último elemento; devuelve null si la deque está vacía.
        String ultimo = deque.pollLast();
        System.out.println("Último elemento eliminado (pollLast): " + ultimo);
        
        /*
         *	METODOS PARA EXAMINAR ELEMENTOS
         */
        
        // Devuelve (sin eliminar) el primer elemento; lanza una excepción si la deque está vacía.
        try {
            String primerElemento = deque.getFirst();
            System.out.println("Primer elemento (getFirst): " + primerElemento);
        } catch (Exception e) {
            System.out.println("La deque está vacía");
        }
        
        // Devuelve (sin eliminar) el último elemento; lanza una excepción si la deque está vacía.
        try {
            String ultimoElemento = deque.getLast();
            System.out.println("Último elemento (getLast): " + ultimoElemento);
        } catch (Exception e) {
            System.out.println("La deque está vacía");
        }
        
        // Devuelve (sin eliminar) el primer elemento; devuelve null si la deque está vacía.
        String primerElemento = deque.peekFirst();
        System.out.println("Primer elemento (peekFirst): " + primerElemento);
        
        // Devuelve (sin eliminar) el último elemento; devuelve null si la deque está vacía.
        String ultimoElemento = deque.peekLast();
        System.out.println("Último elemento (peekLast): " + ultimoElemento);
        
        /*
         *	METODOS ADICIONALES
         */
        
        // Inserta el elemento en el frente de la deque (equivalente a addFirst(e)), útil para tratar la deque como una pila.
        deque.push("E");
        System.out.println("Elemento insertado con push: " + deque);
        
        // Elimina y devuelve el primer elemento (equivalente a removeFirst()), útil para operaciones de pila.
        String elementoPop = deque.pop();
        System.out.println("Elemento eliminado con pop: " + elementoPop);
        
    }
}