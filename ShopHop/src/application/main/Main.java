package application.main;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

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
		String nombre;
		String psw;
		String direccion;
		String telefono;
		ArrayList<Usuario> listaUsers = leerJson();
		System.out.println("***REGISTRO***");
		System.out.println("Introduzca su nombre:");
		nombre = sc.next();
		System.out.println("Introduzca su contraseña");
		psw = sc.next();
		System.out.println("Introduzca su dirección");
		direccion = sc.next();
		System.out.println("Introduzca su telefono");
		telefono = sc.next();
		
		Usuario u = new Usuario(nombre, psw, direccion, telefono);
		
		listaUsers.add(u);
		
		escribirJson(listaUsers);
		
		System.out.println("Usario registrado correctamente");
	}

	private static void escribirJson(ArrayList<Usuario> listaUsers) {
		Gson g = new GsonBuilder().setPrettyPrinting().create();
		
		try(FileWriter w = new FileWriter("Data/Usuarios.json")){
			g.toJson(listaUsers,w);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static ArrayList<Usuario> leerJson() {
		Gson g = new Gson();
		ArrayList<Usuario> listaUsuarios = new ArrayList<>();
		try (FileReader r = new FileReader("Data/Usuarios.json")){
			java.lang.reflect.Type lista = new TypeToken<ArrayList<Usuario>>() {}.getType();
			listaUsuarios = g.fromJson(r, lista);
			if(listaUsuarios == null) {
				listaUsuarios = new ArrayList<>();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaUsuarios;
	}

	private static void inicioSesion() {
		boolean esCorrecto = false;
		System.out.println("Introduce tu nombre de usuario");
		String user = sc.next();
		System.out.println("Introduce tu contraseña");
		String psw = sc.next();
		
		ArrayList<Usuario> listaUser = leerJson();
		
		for (int i = 0; i < listaUser.size(); i++) {
			Usuario u = listaUser.get(i);
			if(u.getNombre().equals(user) & u.getPsw().equals(psw)) {
				System.out.println("Bienvenido "+u.getNombre());
				esCorrecto = true;
			}
		}
		
		if(!esCorrecto) {
			System.out.println("Usuario o contraseña incorrecta");
		}
	}

}
