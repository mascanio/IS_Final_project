package capaDeNegocio.servicioDeAplicacion;

import baseDeDatos.usuarios.User.RolEmpleado;
import capaDeIntegracion.dao.usuarios.UserClientDao;
import capaDeNegocio.transfer.TOUsuario;
import capaDePresentacion.controladorDeAplicacion.CAPrincipal;

public class Login {

	private static Login singleton;
	private static TOUsuario usuarioLogeado;
	private static RolEmpleado rolSeleccionado;
	private static RolEmpleado rolLogeado;

	private int id, pass;

	private boolean campoUsuarioActivo;

	public boolean isCampoUsuarioActivo() {
		return campoUsuarioActivo;
	}

	public void setCampoUsuarioActivo(boolean campoUsuarioActivo) {
		this.campoUsuarioActivo = campoUsuarioActivo;
	}

	public boolean validarUsuario() {
		UserClientDao bd = CAPrincipal.getUserDao();

		TOUsuario user = bd.buscaElemento(id);
		if (user == null) // No existe
			return false;

		// Validar rol
		if (!TOUsuario.validaRol(user, rolSeleccionado))
			return false;

		if (user.getPass() == null) { // Nueva contraseña
			user.setPass(pass);
			bd.agregaElemento(user);
			usuarioLogeado = user;
			rolLogeado = rolSeleccionado;
			return true;
		} else if (user.getPass() == pass) {
			usuarioLogeado = user;
			rolLogeado = rolSeleccionado;
			return true;
		} else
			return false;
	}

	public void doble() {
		if (campoUsuarioActivo) {
			id *= 100;
		} else {
			pass *= 100;
		}
	}

	public Integer getId() {
		return id;
	}

	public void resetId() {
		id = 0;
	}

	public void resetPass() {
		pass = 0;
	}

	public Integer getPass() {
		return pass;
	}

	public void addNum(int n) {
		if (campoUsuarioActivo)
			id = id * 10 + n;
		else
			pass = pass * 10 + n;
	}

	public static Login getService() {
		if (singleton == null)
			singleton = new Login();

		return singleton;
	}

	public void reset() {
		resetPass();
		resetId();
		this.campoUsuarioActivo = true;
	}

	private Login() {
		this.campoUsuarioActivo = true;
	}

	public static TOUsuario getUsuarioLogeado() {
		return usuarioLogeado;
	}

	public static RolEmpleado getRolLogeado() {
		return rolLogeado;
	}

	public static RolEmpleado getRolSeleccionado() {
		return rolSeleccionado;
	}

	public static void setRolSeleccionado(RolEmpleado rolSeleccionado) {
		Login.rolSeleccionado = rolSeleccionado;
	}

}
