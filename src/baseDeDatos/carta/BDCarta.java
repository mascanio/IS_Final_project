package baseDeDatos.carta;

import java.util.TreeMap;

import baseDeDatos.BD;

public class BDCarta implements BD {
	private TreeMap<String, ElementoCarta> carta;

	public BDCarta() {
		this.carta = new TreeMap<String, ElementoCarta>();
	}

	public void eliminaElemento(String s) {
		carta.remove(s);
	}

	public void addElemento(ElementoCarta elem) {
		carta.put(elem.getNombre(), elem);
	}

	public ElementoCarta getElemto(String nombre) {
		return carta.get(nombre);
	}
}
