package capaDeIntegracion.dao.usuarios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import baseDeDatos.usuarios.BDUsuarios;
import baseDeDatos.usuarios.EscrituraUsuarios;
import baseDeDatos.usuarios.LecturaUsuarios;
import baseDeDatos.usuarios.User;
import baseDeDatos.usuarios.WrongFormatUsersTxt;
import capaDeNegocio.transfer.TOUsuario;

public class TxtDBUserDao implements UserClientDao {

	private BDUsuarios bd;

	public TxtDBUserDao() {

	}

	public void linkBD(BDUsuarios bd) {
		this.bd = bd;
	}

	@Override
	public TOUsuario buscaElemento(int id) {
		TOUsuario to = new TOUsuario();

		baseDeDatos.usuarios.User elem = bd.searchUser(id);
		
		if(elem == null)
			return null;
		
		to.setId(elem.getId());
		to.setPass(elem.getPass());
		to.setRol(elem.getRol());
		
		return to;
	}

	@Override
	public void eliminaElemento(int id) {
		bd.deleteUser(id);
	}

	@Override
	public void agregaElemento(TOUsuario elemento) {
		baseDeDatos.usuarios.User user = new User(elemento.getId(), elemento.getPass(), elemento.getRol());
		bd.addUser(user);		
	}
	
	@Override
	public void crearBackup() throws IOException
	{
		EscrituraUsuarios.guardarbackupBD(bd);
	}

	@Override
	public void cargarBackup(InputStream is) throws IOException, WrongFormatUsersTxt {
		linkBD(LecturaUsuarios.cargarBD(is));
		
	}

	@Override
	public void guardarBD() throws IOException {
		EscrituraUsuarios.guardarBD(bd);
	}

	@Override
	public void cargarBD() throws FileNotFoundException, IOException, WrongFormatUsersTxt {
		linkBD(LecturaUsuarios.cargarBD(new FileInputStream("usersBD.txt")));
		
	}

}
