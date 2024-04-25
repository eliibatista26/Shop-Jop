package application.model;

/**
*
*@author Diego Diaz, Pablo Embil, Joseph Vasquez, Elizabeth Nuñez
*@version 1.0
*
*/

public class Producto {
	/**
	 *@param nombre El parámetro nombre define el nombre del producto
	 *@param precio El parámetro precio define el precio del producto
	 *@param id El parámetro id define el id del producto
	 *@param vecesComprado El parámetro vecesComprado define el nº de veces que se ha comprado el producto
	 */
	public String nombre;
	private float precio;
	private int id;
	private int vecesComprado; // Valor global, no por Usuario
	
	/**
	 * Constructor el producto
	 */
	public Producto(String _nombre, float _precio, int _id) {
		this.nombre = _nombre;
		this.precio = _precio;
		this.id = _id;
		this.vecesComprado = 0;
	} // Cierre del Constructor
	
	/**
	 * Método que te retorna el nombre del producto
	 * @return Nombre del producto
	 */
	public String getNombre() {return nombre;}
	/**
	 * Método que te retorna la precio del producto
	 * @return Precio del producto
	 */
	public float getPrecio() {return precio;}
	/**
	 * Método que te retorna el nº de veces que se ha comprado el producto
	 * @return Nº de veces que se ha comprado el producto
	 */
	public int getVecesComprado() {return vecesComprado;}
	/**
	 * Método que te retorna el id del producto
	 * @return ID del producto
	 */
	public int getID() {return id;}
	
	/**
	 * Método que establece el nº de veces que se compra el producto
	 */
	public void setVecesComprado(int nuevasVeces) {vecesComprado = nuevasVeces;}
}