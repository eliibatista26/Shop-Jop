package application.main;

import java.util.ArrayList;
import java.util.Scanner;

import application.model.Usuario;
public class Main {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int opcion = 0;
		while(opcion!=3) {
			System.out.println("BIENVENIDO A SHOP-HOP");
			System.out.println("¿QUE DESEA HACER?");
			System.out.println("1.Iniciar Sesión");
			System.out.println("2.Registrarse");
			System.out.println("3.Salir");
			opcion = sc.nextInt();
			
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
				System.out.println("Opción no válida");
				break;
			}
	
		}
	}

	private static void registroUser() {
		ArrayList<Usuario> listaUsers = leerJson();
		
	}

	private static ArrayList<Usuario> leerJson() {
		// TODO Auto-generated method stub
		return null;
	}

	private static void inicioSesion() {
		System.out.println("Introduce tu nombre de usuario");
		String user = sc.next();
		System.out.println("Introduce tu contraseña");
		String psw = sc.next();
		
		if(user.equals("user1") & psw.equals("123")) {
			System.out.println("Bienvenido "+ user);
		}else {
			System.out.println("usuario o contraseña incorrecta");
		}
	}

}
