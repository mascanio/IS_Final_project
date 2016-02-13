package baseDeDatos.usuarios;

public class User {

	public static final String NO_PASSWORD_YET = "%NO_PASS%";

	public enum RolEmpleado {
		CAJERO, SUPERVISOR, GERENTE, ADMIN
	}

	private int id;
	private Integer pass;
	private RolEmpleado rol;

	public RolEmpleado getRol() {
		return rol;
	}

	public User() {

	}

	public User(int id, Integer pass, RolEmpleado rol) {
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

	public RolEmpleado getEmpleado() {
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
