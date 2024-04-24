package application.model;

import java.util.ArrayList;
import java.util.Scanner;

public class Admin {
	// Atributos
	private String nombre;
	private String psw;
	private String direccion;
	private String telefono;
	
	// Métodos
		// Constructor
	public Admin(String nombre, String psw, String direccion, String telefono) {
		this.nombre = nombre;
		this.psw = psw;
		this.direccion = direccion;
		this.telefono = telefono;
	}
	
		// Menú Administrador
	public void menuAdministrador() {
		System.out.println("Hola Administrador " + nombre + "\n¿QUE DESEA HACER?");
		System.out.println("1.Agregar un producto al catálogo");
		System.out.println("2.Ver compras totales");
		System.out.println("3.Ver usuarios registrados");
		System.out.println("4.Salir");
		int opcion = AlmacenamientoDatos.esInt();
		
		switch (opcion) {
		case 1:
			agregarProductoCatalogo();
			break;
		case 2:
			verComprasTotales();
			break;
		case 3:
			verUsuariosRegistrados();
			break;
		case 4:
			System.out.println("ADIOS");
			break;

		default:
			System.out.println("- - - Opción no válida - - -");
			break;
		}
	}
	
	private void agregarProductoCatalogo() {
		Scanner scAdmin = new Scanner(System.in);
		boolean esRepetido = false;
		ArrayList<Producto> listaProducts = AlmacenamientoDatos.leerCatalogoJson();
		
		System.out.print("Introduzca el nombre del producto\n->");
		String nombreProducto = scAdmin.next();
		// Bucle que comprueba que el nombre de Producto ingresado no se haya registrado previamente
		for (int i = 0; i < listaProducts.size(); i++) {
			if(listaProducts.get(i).getNombre().toUpperCase().equals(nombreProducto.toUpperCase())) {
				System.out.println("- - - El nombre del producto " + listaProducts.get(i).getNombre() + " ya se ha registrado previamente - - -");
				esRepetido = true;
			}
		}
		if(!esRepetido) {
			System.out.print("Introduzca el precio del producto\n");
			float precio = AlmacenamientoDatos.esFloat();
			System.out.print("Introduzca el id del producto\n");
			int id = AlmacenamientoDatos.esInt();
			
			Producto p = new Producto(nombreProducto, precio, id);
			listaProducts.add(p);
			
			// Agregar Producto al archivo Json
			AlmacenamientoDatos.escribirCatalogoJson(listaProducts);
			
			System.out.println("- - - Producto Agregado con Éxito - - -");
		}
	}

	public void verComprasTotales() {
		ArrayList<Producto> listaProducts = AlmacenamientoDatos.leerCatalogoJson();
		
		System.out.println("C O M P R A S    D E L    C A T A L O G O");
		for (int i = 0; i < listaProducts.size(); i++) {
			System.out.println(listaProducts.get(i).getID() + ":" + listaProducts.get(i).nombre + " - " + listaProducts.get(i).getVecesComprado());
		}
	}
	
	public void verUsuariosRegistrados() {
		ArrayList<Usuario> listaUsers = AlmacenamientoDatos.leerUsuariosJson();
		
		System.out.println("U S U A R I O S    R E G I S T R A D O S");
		for (int i = 0; i < listaUsers.size(); i++) {
			System.out.println((i + 1) + ". " + listaUsers.get(i).getNombre() + " - " + listaUsers.get(i).getTelefono() + " - " + listaUsers.get(i).getDireccion());
		}
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
}