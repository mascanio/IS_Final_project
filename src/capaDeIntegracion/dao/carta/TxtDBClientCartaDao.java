package capaDeIntegracion.dao.carta;

import capaDeNegocio.transfer.TOElementoCarta;
import baseDeDatos.carta.BDCarta;
import baseDeDatos.carta.ElementoCarta;

public class TxtDBClientCartaDao implements CartaClientDao {

	private BDCarta bd;
	
	public TxtDBClientCartaDao() {
		
	}
	
	@Override
	public void linkBD(BDCarta bd) {
		this.bd = bd;
	}

	@Override
	public TOElementoCarta buscaElemento(String nombre) {
		TOElementoCarta to = new TOElementoCarta();
		
		baseDeDatos.carta.ElementoCarta elem = bd.getElemto(nombre);
		
		to.setNombre(elem.getNombre());
		to.setPrecio(elem.getPrecio());
		
		return to;
	}

	@Override
	public void eliminaElemento(String nombre) {
		bd.eliminaElemento(nombre);		
	}

	@Override
	public void agregaElemento(TOElementoCarta elemento) {
		baseDeDatos.carta.ElementoCarta elem = new ElementoCarta(elemento.getNombre(), elemento.getPrecio());
		bd.addElemento(elem);	
	}	
	
}
