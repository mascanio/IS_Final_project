package capaDeIntegracion.dao.usuarios;

public class TxtFactoryClientUsuerDao extends FactoryUserDao {

	@Override
	public UserClientDao getClient() {
		return new TxtDBUserDao();
	}

}
