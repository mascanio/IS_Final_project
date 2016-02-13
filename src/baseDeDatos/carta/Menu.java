package baseDeDatos.carta;

import java.util.ArrayList;

public class Menu extends ElementoCarta {

	private ArrayList<Producto> listaProductos;
	
	public Menu(String nombre, double precio) {
		super(nombre, precio);
		this.listaProductos = new ArrayList<Producto>();
	}
	
	public void addProducto(Producto p) {
		this.listaProductos.add(p);
	}
	
	public void eliminarProducto(Producto p) {
		this.listaProductos.remove(p);
	}
	
	public ArrayList<Producto> getListaProducto() {
		return listaProductos;
	}
	
}
