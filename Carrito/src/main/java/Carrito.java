/**
 * En esta aplicacion la idea es que te salga un menu con Comprar productos, Carrito, Salir
 * En el menu de compar productos que salgan 4 opciones y un scanner next int en el que le pases la opcion y meta el producto en el carrito que has pasasdo por scanner
 * En el menu de carrito saldran 2 opciones productos a comprar, proceder al pago, al darle a productos a comprar saldra el nombre y la cantidad de productos a compar
 * Despues en la clase productos tienes 4 campos ID, Nombre, Stock, Precio
 * Me metes los productos de forma simuladada desde el main. Hazlo con mapeo
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Producto {
    int id;
    String nombre;
    int stock;
    double precio;

    public Producto(int id, String nombre, int stock, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
    }
}

public class Carrito {
    private Map<Integer, Producto> productos;
    private Map<Integer, Integer> carrito;
    private Scanner scanner;

    public Carrito() {
        productos = new HashMap<>();
        carrito = new HashMap<>();
        scanner = new Scanner(System.in);
        inicializarProductos();
    }

    private void inicializarProductos() {
        productos.put(1, new Producto(1, "Barra de pan", 17, 1.0));
        productos.put(2, new Producto(2, "Huevos", 15, 3.50));
        productos.put(3, new Producto(3, "Leche", 20, 0.90));
        productos.put(4, new Producto(4, "Lentejas", 25, 2.20));
    }

    public void ejecutar() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n=== MENU: ===");
            System.out.println("1. Comprar productos");
            System.out.println("2. Carrito");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opcion: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    comprarProducto();
                    break;
                case 2:
                    verCarrito();
                    break;
                case 3:
                    salir = true;
                    System.out.println("Saliendo del programa...¡Hasta la proxima!");
                    break;
                default:
                    System.out.println("Esa opion no existe");
            }
        }
        scanner.close();
    }

    private void comprarProducto() {
        System.out.println("\n=== Productos disponibles: ===");
        for (Map.Entry<Integer, Producto> entry : productos.entrySet()) {
            Producto p = entry.getValue();
            System.out.println(p.id + ". " + p.nombre + " - " +("Precio : " + p.precio + "€")  + " - " + ("Stock : " + p.stock));
        }
        System.out.print("Seleccione el ID del producto para agregar al carrito: ");
        int idProducto = scanner.nextInt();
        if (productos.containsKey(idProducto)) {
            Producto producto = productos.get(idProducto);
            if (producto.stock > 0) {
                carrito.put(idProducto, carrito.getOrDefault(idProducto, 0) + 1);
                producto.stock--;
                System.out.println("Producto agregado al carrito.");
            } else {
                System.out.println("Lo sentimos, no hay stock disponible.");
            }
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private void verCarrito() {
        System.out.println("\n=== Carrito: ===");
        if (carrito.isEmpty()) {
            System.out.println("El carrito está vacío.");
        } else {
            double total = 0;
            for (Map.Entry<Integer, Integer> entry : carrito.entrySet()) {
                Producto p = productos.get(entry.getKey());
                int cantidad = entry.getValue();
                total += p.precio * cantidad;
                System.out.println(p.nombre + " - Cantidad: " + cantidad + " - Precio Total: " + (p.precio * cantidad));
            }
            System.out.println("Total a pagar: $" + total);
            System.out.println("1. Proceder al pago");
            System.out.println("2. Volver al menú");
            System.out.print("Seleccione una opción: ");
            int opcionPago = scanner.nextInt();
            if (opcionPago == 1) {
                System.out.println("Pago realizado con éxito. ¡Gracias por su compra!");
                carrito.clear();
            }
        }
    }

    public static void main(String[] args) {
        Carrito carrito = new Carrito();
        carrito.ejecutar();
    }
}

