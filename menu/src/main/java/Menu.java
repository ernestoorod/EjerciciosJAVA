import java.util.Scanner;

public class Menu{
	private static int opcion;

	public void mostrarOpciones() { // El texto que saldra
		 String opciones[];
		 opciones = new String[4];
		 
		 System.out.println("\n===== MENÚ =====");
		 opciones[0] = "Opcion 1";
		 opciones[1] = "Opcion 2";
		 opciones[2] = "Opcion 3";
		 opciones[3] = "Salir";
		 
		 /* Recorre el array con una variable i que se ejecuta = opciones existan en el array debido al opciones.lenght
		  * Despues el sysout es para que me saque el numero y las opciones. */
		 
		 for(int i = 0; i < opciones.length; i++) {
			 System.out.println((i + 1) + ". " + opciones[i]);
		 }
	 }
	 
	 public void manejarOpcion(int opcion) { // La accion que tiene que salir al darle a la opcion elegida
		 switch(opcion) {
		 	case 1: 
		 		System.out.println("Has elegido la opcion 1");
		 		break;
		 	case 2: 
		 		System.out.println("Has elegido la opcion 2");
		 		break;
		 	case 3: 
		 		System.out.println("Has elegido la opcion 3");
		 		break;
		 	case 4:
		 		break;
		 	default:
		 		System.out.println("Esa opcion no existe, elije otra");
		 }
	 }
	 
	 public void salir() { // Salir del menu
		 System.out.println("Cerrando el programa...");
	 }
	 
	 public static void main(String args[]) {
		 Scanner scanner = new Scanner(System.in);
		 Menu menu = new Menu(); // Objeto menu de la clase Menu
		 opcion = 0;
		 
		 while(opcion != 4) {
			 menu.mostrarOpciones();
			 System.out.println("================");
			 System.out.println("Elije una opcion: ");
			 opcion = scanner.nextInt();
	         menu.manejarOpcion(opcion);
		 }
		 
		 menu.salir();
		 scanner.close();
	 }	 
}