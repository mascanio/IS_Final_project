package capaDeNegocio.transfer;

import baseDeDatos.usuarios.User.RolEmpleado;

public class TOUsuario {
	public static final String NO_PASSWORD_YET = "%NO_PASS%";

	private int id;
	private Integer pass;
	private RolEmpleado rol;

	public static boolean validaRol(TOUsuario user, RolEmpleado rol) {

		return (rol == user.getRol())
				|| (user.getRol() != RolEmpleado.ADMIN && user.getRol()
						.ordinal() > rol.ordinal());
	}

	public TOUsuario() {

	}

	public TOUsuario(int id, Integer pass, RolEmpleado rol) {
		this.id = id;
		this.pass = pass;
		this.rol = rol;
	}

	public int getId() {
		return id;
	}

	public Integer getPass() {
		return pass;
	}

	public RolEmpleado getRol() {
		return rol;
	}

	public void setRol(RolEmpleado rol) {
		this.rol = rol;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPass(Integer pass) {
		this.pass = pass;
	}
}
