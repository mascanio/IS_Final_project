package capaDePresentacion.controladorDeAplicacion.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import baseDeDatos.usuarios.User.RolEmpleado;
import capaDeNegocio.servicioDeAplicacion.Login;
import capaDePresentacion.CFPrincipal;
import capaDePresentacion.controladorDeAplicacion.CAPrincipal;
import capaDePresentacion.controladorDeAplicacion.admin.CAAdmin;
import capaDePresentacion.controladorDeAplicacion.cajero.CACajero;
import capaDePresentacion.gui.login.PantallaLogin;

//Patron singleton
public class CALogin implements ActionListener {

	private static PantallaLogin pantallaLogin;
	private static CALogin singleton;
	@SuppressWarnings("unused")
	private static CAPassPanel passControler;

	public static CALogin getCALogin() {
		if (singleton == null)
			singleton = new CALogin();

		return singleton;
	}

	public static PantallaLogin getPantallaLogin() {
		if (pantallaLogin == null)
			pantallaLogin = new PantallaLogin();

		return pantallaLogin;
	}

	private CALogin() {

	}

	public void start() {
		CFPrincipal.changePanel(getPantallaLogin());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String usuario = e.getActionCommand();
		
		Login.setRolSeleccionado(RolEmpleado.valueOf(usuario));
		Login.getService().reset();
		
		passControler = new CAPassPanel(usuario, pantallaLogin);
	}

	public static void loginValido() {
		Login.getService().reset();
		try {
			CAPrincipal.getUserDao().guardarBD();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Ver quien logea y cambiar vista
		switch (Login.getRolLogeado()) {
		case ADMIN:
			CAAdmin ca = new CAAdmin();
			ca.start();
			break;
		case CAJERO:
			CACajero cj = new CACajero();
			cj.start();
			break;
		case GERENTE:
			break;
		case SUPERVISOR:
			break;
		}
	}

}
