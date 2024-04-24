package application.model;

import java.util.ArrayList;
import java.util.Scanner;

// Manejo del Json
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;

// Aproximar a 2 decimales
import java.text.DecimalFormat;

public class Usuario {
	// Atributos
	private String nombre;
	private String psw;
	private String direccion;
	private String telefono;
	private int comprasTotales;		// N° de compras realizadas por el usuarios
	private float dineroGastado;	// Dinero gastado en total en todas las compras
	
	// Métodos
		// Constructor
	public Usuario(String nombre, String psw, String direccion, String telefono) {
		this.nombre = nombre;
		this.psw = psw;
		this.direccion = direccion;
		this.telefono = telefono;
		this.comprasTotales = 0;
		this.dineroGastado = 0;
		// Crea un archivo Json que será la cesta de compra personal del usuario
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter archivoJson = new FileWriter("Data/Cestas/CestaDe" + nombre + ".json")) {
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
		// Mostrar menú usuario
	public void showUserMenu() {
		int opcion = 0;
		do {
			System.out.println("¿QUE DESEA HACER?");
			System.out.println("1.Ver catálogo");
			System.out.println("2.Modificar datos personales");
			System.out.println("3.Agregar productos a la cesta");
			System.out.println("4.Ver cesta");
			System.out.println("5.Pagar cesta");
			System.out.println("6.Salir");
			opcion = AlmacenamientoDatos.esInt();
			
			switch (opcion) {
			case 1:
				showCatalogue();
				break;
			case 2:
				modificarDatosPersonales();
				break;
			case 3:
				agregarProductoALaCesta();
				break;
			case 4:
				verCesta();
				break;
			case 5:
				pagarCesta();
				break;
			case 6:
				System.out.println("ADIOS\n");
				break;
			default:
				System.out.println("- - - Opción no válida - - -");
				break;
			}
		} while (opcion != 6);
	}
	
		// Imprimir catálogo
	public void showCatalogue() {
		ArrayList<Producto> listaProducts = AlmacenamientoDatos.leerCatalogoJson();
		
		System.out.println("C A T A L O G O");
		for (int i = 0; i < listaProducts.size(); i++) {
			System.out.println(listaProducts.get(i).nombre + " - " + listaProducts.get(i).getPrecio() + " €");
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
		this.telefono = nuevoTelefono;
		System.out.print("Introduzca la nueva dirección:\n->");
		String nuevaDireccion = scUsuario.next();
		this.direccion = nuevaDireccion;
		//System.out.print(index);
		listaUsers.get(index).setTelefono(nuevoTelefono);
		listaUsers.get(index).setDireccion(nuevaDireccion);
		
		// Hacer la modificación en el archivo Json
		AlmacenamientoDatos.escribirUsuariosJson(listaUsers);
	}
	
	public void agregarProductoALaCesta() {
		boolean existe = false;
		ArrayList<Producto> listaProducts = AlmacenamientoDatos.leerCatalogoJson();
		ArrayList<Producto> listaProductosCesta = AlmacenamientoDatos.leerProductoCestaJson("Data/Cestas/CestaDe" + nombre + ".json");
		
		System.out.print("Escribe el nombre del producto a agregar:\n->");
		Scanner scUsuario = new Scanner(System.in);
		String nombreProducto = scUsuario.next();
		
		// Bucle para comprobar si existe el producto que escribiste
		for (int i = 0; i < listaProducts.size(); i++) {
			if (nombreProducto.toUpperCase().equals(listaProducts.get(i).getNombre().toUpperCase())) {
				Producto producto = listaProducts.get(i);
				listaProductosCesta.add(producto);
				AlmacenamientoDatos.escribirProductoCestaJson("Data/Cestas/CestaDe" + nombre + ".json", listaProductosCesta);
				existe = true;
				System.out.println("- - - Producto agregado a la cesta - - -");
			}
		}
		if (!existe) {System.out.println("- - - El producto escrito no existe - - -");}
	}
	
	public void verCesta(){
		float subtotal = 0;
		System.out.println("=========== CESTA DE " + nombre + " ===========");
		ArrayList<Producto> listaProductosCesta = AlmacenamientoDatos.leerProductoCestaJson("Data/Cestas/CestaDe" + nombre + ".json");
		
		// Bucle que imprime los productos de la cesta
		for (int i = 0; i < listaProductosCesta.size(); i++) {
			System.out.println(listaProductosCesta.get(i).getNombre() + " - " + listaProductosCesta.get(i).getPrecio() + " €");
			subtotal += listaProductosCesta.get(i).getPrecio();
		}
		
		// Aproxima a 2 decimales
		DecimalFormat formato = new DecimalFormat("#.##");
        System.out.println("\nSUBTOTAL: " + formato.format(subtotal) + " €");
		
		// Imprime '===' exactamente igual que arriba
		System.out.print("=================================");
		for (int i = 0; i < nombre.length(); i++) {System.out.print("=");}
		System.out.println("\n");
	}
	
	public void pagarCesta() {
		float total = 0;
		ArrayList<Producto> listaProductosCesta = AlmacenamientoDatos.leerProductoCestaJson("Data/Cestas/CestaDe" + nombre + ".json");
		ArrayList<Producto> listaCatalogo = AlmacenamientoDatos.leerCatalogoJson();
		ArrayList<Usuario> listaUsuarios = AlmacenamientoDatos.leerUsuariosJson();
		Scanner scUsuario = new Scanner(System.in);
		
		// Bucle que calcula el total
		for (int i = 0; i < listaProductosCesta.size(); i++) {
			total += listaProductosCesta.get(i).getPrecio();
		}
		// Aproxima a 2 decimales
		DecimalFormat formato = new DecimalFormat("#.##");
		if (total > 0) {
			System.out.print("¿Deseas pagar " + formato.format(total) + " €?  (si/no)\n->");
			String respuesta = scUsuario.next();
			
			if (respuesta.toUpperCase().equals("SI")) {
				System.out.println("- - - Cesta pagada con éxito - - -");
				System.out.println("En breves llegaremos a " + direccion);
				
				// Agregar cuantas veces haz comprado un producto
				for(int i = 0; i < listaCatalogo.size(); i++) {
					for(int j = 0; j < listaProductosCesta.size(); j++) {
						
						if (listaCatalogo.get(i).getID() == listaProductosCesta.get(j).getID()) {
							listaCatalogo.get(i).setVecesComprado(listaCatalogo.get(i).getVecesComprado() + 1);
						}
					}
					
				}
				AlmacenamientoDatos.escribirCatalogoJson(listaCatalogo);
				
				// Limpiar la cesta, vaciarla
				AlmacenamientoDatos.vaciarProductoCestaJson("Data/Cestas/CestaDe" + nombre + ".json");
				
				// Actualizamos la información del usuario
				for (int i = 0; i < listaUsuarios.size(); i++) {
					if (listaUsuarios.get(i).getNombre().equals(this.nombre)) {
						listaUsuarios.get(i).setComprasTotales(listaUsuarios.get(i).getComprasTotales() + 1);
						listaUsuarios.get(i).setDineroGastado(listaUsuarios.get(i).getDineroGastado() + total);
					}
				}
				AlmacenamientoDatos.escribirUsuariosJson(listaUsuarios);
				
			} else {System.out.println("- - - Operación Cancelada - - -");}
		} else {System.out.println("- - - No hay productos en tu cesta - - -");}
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
	
	public int getComprasTotales() {return comprasTotales;}
	
	public float getDineroGastado() {return dineroGastado;}
	
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
	
	public void setComprasTotales(int nuevasCompasTotales) {comprasTotales = nuevasCompasTotales;}
	
	public void setDineroGastado(float nuevoDineroGastado) {dineroGastado = nuevoDineroGastado;}
}