package application.main;

// Importar lo relacionado con lectura y escritura
import java.util.ArrayList;
import java.util.Scanner;

// Importar clases del paquete modelo
import application.model.*;

public class Main {
	// Atributos
	public static Scanner sc = new Scanner(System.in);
	public static Admin admin1;

	// Métodos
	public static void main(String[] args) {
		// Admin
		admin1 = new Admin("GERENTEMERCADONA", "123456789", "Calle callal", "123456789");
		
		int opcion = 0;
		while(opcion!=3) {
			// Imprimir menú
			System.out.println("BIENVENIDO A SHOP-HOP");
			System.out.println("¿QUE DESEA HACER?");
			System.out.println("1.Iniciar Sesión");
			System.out.println("2.Registrarse");
			System.out.println("3.Salir");
			// Verificar que el valor ingresado es numérico entero
			opcion = AlmacenamientoDatos.esInt();
			
			switch (opcion) {
			case 1:
				inicioSesion();
				break;
			case 2:
				registroUser();
				break;
			case 3:
				System.out.println("ADIOS");
				break;
			default:
				System.out.println("- - - Opción no válida - - -");
				break;
			}
		}
	}

	private static void registroUser() {
		String nombreUsuario;
		String psw;
		String direccion;
		String telefono;
		boolean esRepetido = false;
		ArrayList<Usuario> listaUsers = AlmacenamientoDatos.leerUsuariosJson();
		System.out.println("***REGISTRO***");
		System.out.print("Introduzca su nombre:\n->");
		nombreUsuario = sc.next();
		// Pasamos el nombre a mayúsculas para manejar errores
		nombreUsuario = nombreUsuario.toUpperCase();
		
		// Bucle que comprueba que el nombre ingresado no se haya registrado previamente
		for (int i = 0; i < listaUsers.size(); i++) {
			Usuario u = listaUsers.get(i);
			if(u.getNombre().equals(nombreUsuario)) {
				System.out.println("- - - El nombre de usuario " + u.getNombre() + " ya se ha registrado previamente - - -");
				esRepetido = true;
			}
		}
		
		if(!esRepetido) {
			System.out.print("Introduzca su contraseña:\n->");
			psw = sc.next();
			System.out.print("Introduzca su dirección:\n->");
			direccion = sc.next();
			System.out.print("Introduzca su teléfono:\n->");
			telefono = sc.next();
			
			Usuario u = new Usuario(nombreUsuario, psw, direccion, telefono);
			
			listaUsers.add(u);
			
			AlmacenamientoDatos.escribirUsuariosJson(listaUsers);
			
			System.out.println("- - - Usario registrado correctamente - - -");
		}
	}

	private static void inicioSesion() {
		boolean esCorrecto = false;
		System.out.print("Introduce tu nombre de usuario:\n->");
		String user = sc.next();
		// Pasamos el nombre a mayúsculas para manejar errores
		user = user.toUpperCase();
		System.out.print("Introduce tu contraseña:\n->");
		String psw = sc.next();
		
		// If que comprueba si es el admin el que esta iniciando sesión
		if (user.equals(admin1.getNombre()) && psw.equals(admin1.getPsw())) {admin1.menuAdministrador();}
		else {
			ArrayList<Usuario> listaUsers = AlmacenamientoDatos.leerUsuariosJson();
			
			// Bucle que comprueba que la información ingresada es correcta
			for (int i = 0; i < listaUsers.size(); i++) {
				Usuario u = listaUsers.get(i);
				if(u.getNombre().equals(user) & u.getPsw().equals(psw)) {
					System.out.println("Bienvenido " + u.getNombre());
					esCorrecto = true;
					u.showUserMenu();
				}
			}
			if(!esCorrecto) {
				System.out.println("- - - Usuario o contraseña incorrecta - - -");
			}
		}
	}
}