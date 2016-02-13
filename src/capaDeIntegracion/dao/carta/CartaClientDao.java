package capaDeIntegracion.dao.carta;

import baseDeDatos.carta.BDCarta;
import capaDeNegocio.transfer.TOElementoCarta;

public interface CartaClientDao {
	
	TOElementoCarta buscaElemento(String nombre);
	void eliminaElemento(String nombre);
	void agregaElemento(TOElementoCarta elemento);
	void linkBD(BDCarta bd);
	
}
