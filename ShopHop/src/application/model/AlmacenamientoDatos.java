package application.model;

//Importar lo relacionado con lectura y escritura
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

//Importar lo relacionado al Json
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class AlmacenamientoDatos {
	// Lectura Usuarios del archivo Json
	public static ArrayList<Usuario> leerUsuariosJson() {
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
	
	// Escritura de Usuarios del archivo Json
	public static void escribirUsuariosJson(ArrayList<Usuario> listaUsers) {
		Gson g = new GsonBuilder().setPrettyPrinting().create();
		
		try(FileWriter w = new FileWriter("Data/Usuarios.json")){
			g.toJson(listaUsers, w);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Lectura del Catálogo del archivo Json
	public static ArrayList<Producto> leerCatalogoJson() {
		Gson g = new Gson();
		ArrayList<Producto> listaProducts = new ArrayList<>();
		try (FileReader r = new FileReader("Data/Catalogo.json")){
			java.lang.reflect.Type lista = new TypeToken<ArrayList<Producto>>() {}.getType();
			listaProducts = g.fromJson(r, lista);
			if(listaProducts == null) {
				listaProducts = new ArrayList<>();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaProducts;
	}
	
	// Escritura del Catálogo del archivo Json
	public static void escribirCatalogoJson(ArrayList<Producto> listaProducts) {
		Gson g = new GsonBuilder().setPrettyPrinting().create();
		
		try(FileWriter w = new FileWriter("Data/Catalogo.json")){
			g.toJson(listaProducts, w);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Lectura de la Cesta del archivo Json
	public static ArrayList<Producto> leerProductoCestaJson(String ruta) {
		Gson g = new Gson();
		ArrayList<Producto> listaProductosCesta = new ArrayList<>();
		try (FileReader r = new FileReader(ruta)){
			java.lang.reflect.Type lista = new TypeToken<ArrayList<Producto>>() {}.getType();
			listaProductosCesta = g.fromJson(r, lista);
			if(listaProductosCesta == null) {
				listaProductosCesta = new ArrayList<>();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaProductosCesta;
	}
	
	// Escritura de la Cesta del archivo Json
	public static void escribirProductoCestaJson(String ruta, ArrayList<Producto> listaProductosCesta) {
		Gson g = new GsonBuilder().setPrettyPrinting().create();
		
		try(FileWriter w = new FileWriter(ruta)){
			g.toJson(listaProductosCesta, w);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Vaciar la Cesta del archivo Json
	public static void vaciarProductoCestaJson(String ruta) {
		Gson g = new GsonBuilder().setPrettyPrinting().create();
		
		try(FileWriter w = new FileWriter(ruta)){
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Clase esInt que asegura que lo introducido por el usuario es un entero
	// en caso no serlo, lo pedirá otra vez
	public static int esInt() {
		int n = -1;
		Scanner sc = new Scanner(System.in);
		do {
			try {
				System.out.print("->");
				n = sc.nextInt();
				break;
			} catch (Exception e) {System.out.println("- - - Numero no Numerico Entero - - -"); sc.nextLine();}
		} while (true);
		return n;
	}
	
	// Clase esInt que asegura que lo introducido por el usuario es un flotante
	public static float esFloat() {
		float n = -1;
		Scanner sc = new Scanner(System.in);
		do {
			try {
				System.out.print("->");
				n = sc.nextFloat();
				break;
			} catch (Exception e) {System.out.println("- - - Valor no Numerico - - -"); sc.nextLine();}
		} while (true);
		return n;
	}
}