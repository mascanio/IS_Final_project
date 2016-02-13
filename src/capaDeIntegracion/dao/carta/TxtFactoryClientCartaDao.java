package capaDeIntegracion.dao.carta;

public class TxtFactoryClientCartaDao extends FactoryCartaDao {

	@Override
	public CartaClientDao getClient() {
		return new TxtDBClientCartaDao();
	}

}
