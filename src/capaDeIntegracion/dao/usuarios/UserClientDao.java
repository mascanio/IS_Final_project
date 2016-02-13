package capaDeIntegracion.dao.usuarios;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import baseDeDatos.usuarios.BDUsuarios;
import baseDeDatos.usuarios.WrongFormatUsersTxt;
import capaDeNegocio.transfer.TOUsuario;

public interface UserClientDao {
	
	TOUsuario buscaElemento(int id);
	void eliminaElemento(int id);
	void agregaElemento(TOUsuario elemento);
	void linkBD(BDUsuarios bd);
	void crearBackup() throws IOException;
	void cargarBackup(InputStream is) throws IOException, WrongFormatUsersTxt;
	void guardarBD() throws IOException;
	void cargarBD() throws FileNotFoundException, IOException, WrongFormatUsersTxt;
}
