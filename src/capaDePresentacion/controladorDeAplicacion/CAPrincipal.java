package capaDePresentacion.controladorDeAplicacion;

import java.io.FileNotFoundException;
import java.io.IOException;

import baseDeDatos.carta.BDCarta;
import baseDeDatos.usuarios.BDUsuarios;
import baseDeDatos.usuarios.WrongFormatUsersTxt;
import capaDeIntegracion.dao.carta.CartaClientDao;
import capaDeIntegracion.dao.carta.FactoryCartaDao;
import capaDeIntegracion.dao.usuarios.FactoryUserDao;
import capaDeIntegracion.dao.usuarios.UserClientDao;
import capaDePresentacion.CFPrincipal;

public class CAPrincipal {

	private static CFPrincipal controladorFrontal;

	private static FactoryCartaDao factoryCartaDao;
	private static FactoryUserDao factoryUserDao;

	private static CartaClientDao cartaDao;
	private static UserClientDao userDao;

	public static CartaClientDao getCartaDao() {
		return cartaDao;
	}

	public static UserClientDao getUserDao() {
		return userDao;
	}

	public static void tpvStart(BDCarta bdCarta, BDUsuarios bdUsuarios) {
		factoryCartaDao = FactoryCartaDao
				.getFactory(FactoryCartaDao.TXT_BD_FACTORY);
		cartaDao = factoryCartaDao.getClient();
		cartaDao.linkBD(bdCarta);

		factoryUserDao = FactoryUserDao
				.getFactory(FactoryUserDao.TXT_BD_FACTORY);
		userDao = factoryUserDao.getClient();
		userDao.linkBD(bdUsuarios);

		try {
			userDao.cargarBD();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (WrongFormatUsersTxt e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		controladorFrontal = CFPrincipal.getCFPrincipal();
		
		controladorFrontal.start();		
	}
}
