package baseDeDatos.usuarios;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

public class EscrituraUsuarios {

	public static void guardarBD(BDUsuarios userList) throws IOException {
		@SuppressWarnings("unchecked")
		TreeMap<Integer, User> userListAux = (TreeMap<Integer, User>) userList
				.getLista().clone();

		FileWriter file = new FileWriter("usersBD.txt");
		PrintWriter aux = new PrintWriter(file);
		aux.println("beginusers");
		Integer key = userListAux.lastKey();
		User usuario;
		while (key != null) {
			usuario = userListAux.get(key);
			userListAux.remove(key);
			aux.println(usuario.getId());
			if (usuario.getPass() != null)
				aux.println(usuario.getPass());
			else
				aux.println(User.NO_PASSWORD_YET);
			aux.println(usuario.getEmpleado().toString());
			aux.println(",");
			if (userListAux.size() != 0)
				key = userListAux.lastKey();
			else
				key = null;
		}
		aux.println("endusers");
		aux.close();
	}
	
	public static void guardarbackupBD(BDUsuarios userList) throws IOException {
		@SuppressWarnings("unchecked")
		TreeMap<Integer, User> userListAux = (TreeMap<Integer, User>) userList
				.getLista().clone();
		Date fecha = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String fname = formato.format(fecha);
		fname = fname + ".txt";
		FileWriter file = new FileWriter(fname);
		PrintWriter aux = new PrintWriter(file);
		aux.println("beginusers");
		Integer key = userListAux.lastKey();
		User usuario;
		while (key != null) {
			usuario = userListAux.get(key);
			userListAux.remove(key);
			aux.println(usuario.getId());
			if (usuario.getPass() != null)
				aux.println(usuario.getPass());
			else
				aux.println(User.NO_PASSWORD_YET);
			aux.println(usuario.getEmpleado().toString());
			aux.println(",");
			if (userListAux.size() != 0)
				key = userListAux.lastKey();
			else
				key = null;
		}
		aux.println("endusers");
		aux.close();
	}
}
