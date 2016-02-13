package baseDeDatos.usuarios;

import java.util.TreeMap;

public class BDUsuarios {

	private TreeMap<Integer, User> lista; // ID-User

	public BDUsuarios() {
		lista = new TreeMap<Integer, User>();
	}

	public void addUser(User user) {
		lista.put(user.getId(), user);
	}

	/*
	 * user es la id del user a borrar
	 */

	public void deleteUser(int userID) {
		lista.remove(userID);
	}

	/*
	 * Busco el usuario y si esta en la lista devuelvo el User y sino devuelvo
	 * null
	 */

	public User searchUser(int userID) {
		return lista.get(userID);
	}
	
	public TreeMap<Integer, User> getLista()
	{
		return lista;
	}
}
