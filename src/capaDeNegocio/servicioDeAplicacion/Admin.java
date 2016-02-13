package capaDeNegocio.servicioDeAplicacion;

import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;

import baseDeDatos.usuarios.User.RolEmpleado;
import baseDeDatos.usuarios.WrongFormatUsersTxt;
import capaDeIntegracion.dao.usuarios.UserClientDao;
import capaDeNegocio.transfer.TOUsuario;
import capaDePresentacion.controladorDeAplicacion.CAPrincipal;

public class Admin {

	private UserClientDao bd;

	public Admin() {
		this.bd = CAPrincipal.getUserDao();
	}

	public void crearUser(int id, RolEmpleado rol) throws Exception {
		if(bd.buscaElemento(id)==null)
		{
		TOUsuario nuevo = new TOUsuario(id, null, rol);

		bd.agregaElemento(nuevo);
		try {
			CAPrincipal.getUserDao().guardarBD();
		} catch (IOException e) {}
	}
		else
			throw new Exception("El usuario ya existe");
	}

	public void editarUser(int id, RolEmpleado rol)
			throws NoSuchElementException {
		if (id != Login.getUsuarioLogeado().getId())
			try {
				int pass = bd.buscaElemento(id).getPass();
				TOUsuario nuevo = new TOUsuario(id, pass, rol);
				bd.agregaElemento(nuevo);
			} catch (NullPointerException e) {
				throw new NoSuchElementException(
						"El usuario indicado no existe");
			}

	}

	public void borrarUsuario(int id) {
		if (id != Login.getUsuarioLogeado().getId())
			bd.eliminaElemento(id);
	}

	public void reset(int id) throws NoSuchElementException {
		TOUsuario nuevo = bd.buscaElemento(id);
		if (nuevo != null) {
			nuevo.setPass(null);
			bd.agregaElemento(nuevo);
		} else
			throw new NoSuchElementException("El usuario indicado no existe");

	}

	public void crearBackup() throws IOException {
		bd.crearBackup();
	}

	public void cargarBackup(InputStream is) throws IOException,
			WrongFormatUsersTxt {
		bd.cargarBackup(is);
	}

}
