package capaDeIntegracion.dao.carta;

//Patron Factoria
//Patron Dao
public abstract class FactoryCartaDao {

	public static final int TXT_BD_FACTORY = 1;

	// Otros tipos de BD

	public abstract CartaClientDao getClient();

	public static FactoryCartaDao getFactory(int id) {
		switch (id) {
		case TXT_BD_FACTORY:
			return new TxtFactoryClientCartaDao();
		default:
			throw new IllegalArgumentException();
		}
	}

}
