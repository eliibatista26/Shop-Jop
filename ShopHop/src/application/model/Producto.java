package application.model;

public class Producto {
	// Atributos
	public String nombre;
	private float precio;
	private int id;
	private int vecesComprado;
	// Métodos
		// Constructor
	public Producto(String _nombre, float _precio, int _id) {
		this.nombre = _nombre;
		this.precio = _precio;
		this.id = _id;
		this.vecesComprado = 0;
	}
	
		// Getters
	public String getNombre() {return nombre;}
	public float getPrecio() {return precio;}
	public int getVecesComprado() {return vecesComprado;}
	public int getID() {return id;}
}