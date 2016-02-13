package capaDeNegocio.transfer;

import java.util.ArrayList;
import java.util.Iterator;


@SuppressWarnings("unused")
public class TOTicket {
	private int idCajero;
	private int hora; // TODO

	public class TOElementoCartaExt extends TOElementoCarta {
		private int cantidad;

		public TOElementoCartaExt(TOElementoCarta elem, int cantidad) {
			super(elem.getNombre(), elem.getPrecio());
			this.cantidad = cantidad;
		}
		
		public int getCantidad() {
			return cantidad;
		}
	}

	private ArrayList<TOElementoCartaExt> listaElementos;

	public TOTicket() {
		this.listaElementos = new ArrayList<TOElementoCartaExt>();
	}

	public TOTicket(int idCajero, int hora) {
		super();
		this.listaElementos = new ArrayList<TOElementoCartaExt>();
		this.idCajero = idCajero;
		this.hora = hora;
	}

	public void addElemento(TOElementoCarta elem, int cantidad) {
		listaElementos.add(new TOElementoCartaExt(elem, cantidad));
	}

	public void eliminaElemento(String s) {
		Iterator<TOElementoCartaExt> iterator = listaElementos.iterator();

		while (iterator.hasNext()
				&& !iterator.next().getNombre().equalsIgnoreCase(s)) {
		}

		iterator.remove();
	}

	public double getPrecioTotal() {
		Iterator<TOElementoCartaExt> iterator = listaElementos.iterator();
		
		double r = 0;
		TOElementoCartaExt elem;
		
		while (iterator.hasNext()) {
			elem = iterator.next();
			r+= elem.getPrecio() * elem.getCantidad();
		}
		
		return r;
	}
	
	public  ArrayList<TOElementoCartaExt> getLista() {
		return this.listaElementos;
	}
}
