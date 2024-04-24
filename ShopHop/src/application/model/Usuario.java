package application.model;

import java.util.ArrayList;
import java.util.Scanner;

public class Usuario {
	// Atributos
	private String nombre;
	private String psw;
	private String direccion;
	private String telefono;
	
	// Métodos
		// Constructor
	public Usuario(String nombre, String psw, String direccion, String telefono) {
		this.nombre = nombre;
		this.psw = psw;
		this.direccion = direccion;
		this.telefono = telefono;
	}
	
		// Mostrar menú usuario
	public void showUserMenu() {
		System.out.println("¿QUE DESEA HACER?");
		System.out.println("1.Ver catálogo");
		System.out.println("2.Modificar datos personales");
		System.out.println("3.Salir");
		int opcion = AlmacenamientoDatos.esInt();
		
		switch (opcion) {
		case 1:
			showCatalogue();
			break;
		case 2:
			modificarDatosPersonales();
			break;
		case 3:
			System.out.println("ADIOS");
			break;
		default:
			System.out.println("- - - Opción no válida - - -");
			break;
		}
	}
	
		// Imprimir catálogo
	public void showCatalogue() {
		ArrayList<Producto> listaProducts = AlmacenamientoDatos.leerCatalogoJson();
		
		System.out.println("C A T A L O G O");
		for (int i = 0; i < listaProducts.size(); i++) {
			System.out.println(listaProducts.get(i).nombre + " - " + listaProducts.get(i).getPrecio());
		}
	}
	
	public void modificarDatosPersonales() {
		Scanner scUsuario = new Scanner(System.in);
		int index = -1;
		ArrayList<Usuario> listaUsers = AlmacenamientoDatos.leerUsuariosJson();
		
		// Bucle para coger el index del usuario a modificar
		for (int i = 0; i < listaUsers.size(); i++) {
			if (listaUsers.get(i).getNombre().equals(this.nombre)) {index = i;}
		}
		
		System.out.println("M O D I F I C A R    D A T O S    P E R S O N A L E S");
		System.out.print("Introduzca el nuevo teléfono:\n->");
		String nuevoTelefono = scUsuario.next();
		System.out.print("Introduzca la nueva dirección:\n->");
		String nuevaDireccion = scUsuario.next();
		System.out.print(index);
		listaUsers.get(index).setTelefono(nuevoTelefono);
		listaUsers.get(index).setDireccion(nuevaDireccion);
		
		// Hacer la modificación en el archivo Json
		AlmacenamientoDatos.escribirUsuariosJson(listaUsers);
	}

		// Getters
	public String getNombre() {
		return nombre;
	}

	public String getPsw() {
		return psw;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getTelefono() {
		return telefono;
	}
	
		// Setters
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setPsw(String psw) {
		this.psw = psw;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}