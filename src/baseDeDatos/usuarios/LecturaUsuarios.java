package baseDeDatos.usuarios;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LecturaUsuarios {

	public static BDUsuarios cargarBD(InputStream file)
			throws WrongFormatUsersTxt, IOException {

		DataInputStream entrada = new DataInputStream(file);
		BufferedReader buffer = new BufferedReader(new InputStreamReader(
				entrada));

		String aux = "";
		User user;
		BDUsuarios users = new BDUsuarios();
		Integer id, pass;
		aux = buffer.readLine();

		if (!aux.equalsIgnoreCase("beginusers"))
			throw new WrongFormatUsersTxt();
		else {
			aux = buffer.readLine();
			while (!aux.equalsIgnoreCase("endusers")) {
				try {
					id = Integer.parseInt(aux);
				} catch (NumberFormatException e) {
					throw new WrongFormatUsersTxt();
				}

				aux = buffer.readLine();
				if (aux.compareTo(User.NO_PASSWORD_YET)!=0) {
					try {
						pass = Integer.parseInt(aux);
					} catch (NumberFormatException e) {
						throw new WrongFormatUsersTxt();
					}
				} else
					pass = null;

				aux = buffer.readLine();
				if (aux.equalsIgnoreCase("cajero"))
					user = new Cajero(id, pass, User.RolEmpleado.CAJERO);
				else if (aux.equalsIgnoreCase("Supervisor"))
					user = new Supervisor(id, pass, User.RolEmpleado.SUPERVISOR);
				else if (aux.equalsIgnoreCase("gerente"))
					user = new Gerente(id, pass, User.RolEmpleado.GERENTE);
				else if (aux.equalsIgnoreCase("admin"))
					user = new Admin(id, pass, User.RolEmpleado.ADMIN);
				else
					throw new WrongFormatUsersTxt();

				users.addUser(user);

				aux = buffer.readLine();
				if (!aux.equalsIgnoreCase(","))
					throw new WrongFormatUsersTxt();
				aux = buffer.readLine();
			}
		}
		return users;

	}

}
