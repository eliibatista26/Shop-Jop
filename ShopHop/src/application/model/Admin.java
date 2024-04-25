package application.model;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 *@author Diego Diaz, Pablo Embil, Joseph Vasquez, Elizabeth Nuñez
 *@version 1.0
 *
 */

public class Admin {
	/**
	 *@param nombre El parámetro nombre define el nombre de usuario del administrador
	 *@param psw El parámetro psw define la contraseña del administrador
	 *@param dirección El parámetro dirección define la dirección de correo del administrador
	 *@param telefono El parámetro telefono define el telefono del administrador
	 */
	private String nombre;
	private String psw;
	private String direccion;
	private String telefono;
	
	
	/**
	 * Constructor el administrador
	 */
	public Admin(String nombre, String psw, String direccion, String telefono) {
		this.nombre = nombre;
		this.psw = psw;
		this.direccion = direccion;
		this.telefono = telefono;
	} // Cierre del Constructor
	
	
	/**
	 * Método que genera el menú del administrador
	 */
	public void menuAdministrador() {
		int opcion = 0;
		do {
			System.out.println("Hola Administrador " + nombre + "\n¿QUE DESEA HACER?");
			System.out.println("1.Agregar un producto al catálogo");
			System.out.println("2.Ver compras totales");
			System.out.println("3.Ver información de usuarios registrados");
			System.out.println("4.Salir");
			
			opcion = AlmacenamientoDatos.esInt();
			
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
		}while (opcion != 4);
	} //Cierre del método menuAdministrador()
	
	/**
	 * Método que agrega el producto del catálogo
	 */
	private void agregarProductoCatalogo() {
		Scanner scAdmin = new Scanner(System.in);
		boolean esRepetidoNombre = false, esRepetidoId = false;
		ArrayList<Producto> listaProducts = AlmacenamientoDatos.leerCatalogoJson();
		
		System.out.print("Introduzca el nombre del producto\n->");
		String nombreProducto = scAdmin.next();
		// Bucle que comprueba que el nombre del producto ingresado no se haya registrado previamente
		for (int i = 0; i < listaProducts.size(); i++) {
			if(listaProducts.get(i).getNombre().toUpperCase().equals(nombreProducto.toUpperCase())) {
				System.out.println("- - - El nombre del producto " + listaProducts.get(i).getNombre() + " ya se ha registrado previamente - - -");
				esRepetidoNombre = true;
			}
		}
		if(!esRepetidoNombre) {
			System.out.print("Introduzca el precio del producto\n");
			float precio = AlmacenamientoDatos.esFloat();
			System.out.print("Introduzca el id del producto\n");
			int idProducto = AlmacenamientoDatos.esInt();
			
			// Bucle que comprueba que el id del producto ingresado no se haya registrado previamente
			for (int i = 0; i < listaProducts.size(); i++) {
				if(listaProducts.get(i).getID() == idProducto) {
					System.out.println("- - - El id del producto " + listaProducts.get(i).getID() + " ya se ha registrado previamente para el producto " + listaProducts.get(i).getNombre() + " - - -");
					esRepetidoId = true;
				}
			}
			
			if (!esRepetidoId) {
				Producto p = new Producto(nombreProducto, precio, idProducto);
				listaProducts.add(p);
				
				// Agregar producto al archivo Json
				AlmacenamientoDatos.escribirCatalogoJson(listaProducts);
				
				System.out.println("- - - Producto Agregado con Éxito - - -");
			}
		}
	} //Cierre del método AgregarProductoCatálogo()

	/**
	 * Método que te muestra las compras totales
	 * @return Las compras totales
	 */
	public void verComprasTotales() {
		ArrayList<Producto> listaProducts = AlmacenamientoDatos.leerCatalogoJson();
		
		System.out.println("C O M P R A S    D E L    C A T A L O G O");
		for (int i = 0; i < listaProducts.size(); i++) {
			System.out.println(listaProducts.get(i).getID() + ":" + listaProducts.get(i).nombre + " - Veces Comprado: " + listaProducts.get(i).getVecesComprado());
		}
		System.out.println();
	} //Cierre del método verComprasTotales()
	
	/**
	 * Método que te muestra todos los usuarios registrados
	 * @return Todos los usuarios registrados
	 */
	public void verUsuariosRegistrados() {
		ArrayList<Usuario> listaUsers = AlmacenamientoDatos.leerUsuariosJson();
		
		System.out.println("U S U A R I O S    R E G I S T R A D O S");
		for (int i = 0; i < listaUsers.size(); i++) {
			System.out.println((i + 1) + ". " + listaUsers.get(i).getNombre() + " - Teléfono: " + listaUsers.get(i).getTelefono() + " - Dirección: " + listaUsers.get(i).getDireccion() + " - N° de Compras: " + listaUsers.get(i).getComprasTotales() + " - Dinero Gastado: " + listaUsers.get(i).getDineroGastado());
		}
		System.out.println();
	} //Cierre del método verUsuariosRegistrados()

	/**
	 * Método que te retorna el nombre de usuario del administrador
	 * @return Nombre de usuario del administrador
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Método que te retorna la contraseña del administrador
	 * @return Contraseña del administrador
	 */
	public String getPsw() {
		return psw;
	}

	/**
	 * Método que te retorna la dirección de correo del administrador
	 * @return Dirección de correo del administrador
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Método que te retorna el teléfono del administrador
	 * @return Teléfono del administrador
	 */
	public String getTelefono() {
		return telefono;
	}
}