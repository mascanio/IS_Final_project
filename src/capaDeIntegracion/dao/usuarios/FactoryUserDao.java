package capaDeIntegracion.dao.usuarios;

//Patron Factoria
//Patron Dao
public abstract class FactoryUserDao {

	public static final int TXT_BD_FACTORY = 1;

	// Otros tipos de BD

	public abstract UserClientDao getClient();

	public static FactoryUserDao getFactory(int id) {
		switch (id) {
		case TXT_BD_FACTORY:
			return new TxtFactoryClientUsuerDao();
		default:
			throw new IllegalArgumentException();
		}
	}

}
