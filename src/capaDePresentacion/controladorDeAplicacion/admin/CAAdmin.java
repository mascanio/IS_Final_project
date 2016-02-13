package capaDePresentacion.controladorDeAplicacion.admin;

import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import baseDeDatos.usuarios.EscrituraUsuarios;
import baseDeDatos.usuarios.User.RolEmpleado;
import baseDeDatos.usuarios.WrongFormatUsersTxt;
import capaDeNegocio.servicioDeAplicacion.Admin;
import capaDePresentacion.CFPrincipal;
import capaDePresentacion.controladorDeAplicacion.login.CALogin;
import capaDePresentacion.gui.admin.PantallaAdmin;
import capaDePresentacion.gui.admin.CrearUsuario;
import capaDePresentacion.gui.admin.PreguntaEditar;
import capaDePresentacion.gui.admin.SeleccionarUsuario;

@SuppressWarnings("unused")
public class CAAdmin implements ActionAdminListener {

	private PantallaAdmin pantallaAdmin;
	private Admin admin;

	public CAAdmin() {

		this.admin = new Admin();

	}

	public void start() {
		pantallaAdmin = new PantallaAdmin(this);
		pantallaAdmin.init();
		CFPrincipal.changePanel(pantallaAdmin);
	}

	public void crearUsuario(PantallaAdmin admin) {
		CrearUsuario user = new CrearUsuario(this, "Crear Usuario");
	

	}

	public void editarUsuario(PantallaAdmin admin) {

		SeleccionarUsuario user = new SeleccionarUsuario(this, "Editar Usuario");
	}

	public void borrarUsuario(PantallaAdmin admin) {

		SeleccionarUsuario user = new SeleccionarUsuario(this, "Borrar Usuario");
	}

	public void cargarBackup(PantallaAdmin admin) {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("Elige el backup a cargar");
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.getSelectedFile();
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			InputStream is = null;
			try {
				is = new FileInputStream (chooser.getSelectedFile());			
			} catch (FileNotFoundException e) {
				admin.onError("Error al cargar la base de datos", admin);
			}
				try {
					this.admin.cargarBackup(is);
					admin.popUp("BackUp cargado");
				} catch (IOException | WrongFormatUsersTxt e) {
					admin.onError("Error al cargar la base de datos", admin);
				}
		} else {
			pantallaAdmin.onError("No se ha seleccionado archivo ", admin);
		}
	}

	public void crearBackup(PantallaAdmin admin) {
		try {
			this.admin.crearBackup();
			admin.popUp("BackUp creado");
		} catch (IOException e) {
			pantallaAdmin.onError("Error al guardar la base de datos ", admin);
		}
		
	}

	

	@Override
	public void actionAdminPerformed(ActionEvent e) {
		String source = e.getActionCommand().toString();

		if (source.compareToIgnoreCase("Nuevo Usuario") == 0)
			crearUsuario(pantallaAdmin);
		else if (source.compareToIgnoreCase("Editar Usuario") == 0)
			editarUsuario(pantallaAdmin);
		else if (source.compareToIgnoreCase("Borrar Usuario") == 0)
			borrarUsuario(pantallaAdmin);
		else if (source.compareToIgnoreCase("Crear Backup") == 0)
			crearBackup(pantallaAdmin);
		else if (source.compareToIgnoreCase("Cargar Backup") == 0)
			cargarBackup(pantallaAdmin);
		else if (source.compareToIgnoreCase("Salir") == 0) {
			CFPrincipal.changePanel(CALogin.getPantallaLogin());
		}
	}

	public void intro(String id, String accion, String rolString) {
		
		if (accion.compareToIgnoreCase("Crear Usuario") == 0) {
			RolEmpleado rol = parsearRol(rolString);
			try {
				admin.crearUser(Integer.parseInt(id), rol);
			} catch (Exception e) {
				pantallaAdmin.onError(e.getMessage(), pantallaAdmin);
			}
		} else if (accion.compareToIgnoreCase("Editar Usuario") == 0) {
			PreguntaEditar pregunta = new PreguntaEditar(this,Integer.parseInt(id));			
		}
			
		 else
			admin.borrarUsuario(Integer.parseInt(id));
	}

	public void editarElegido(String selectedItem,int id, RolEmpleado rol) {
try{
		if (selectedItem.compareToIgnoreCase("Reset Pass")==0)
			admin.reset(id);
		else 		
			admin.editarUser(id,rol);
} 
catch (NoSuchElementException e)
{
	pantallaAdmin.onError(e.getMessage(),pantallaAdmin);
}
		
	}
	public RolEmpleado parsearRol(String rolString) {
		RolEmpleado rol = null;
		switch (rolString) {
		case "Cajero":
			rol = RolEmpleado.CAJERO;
			break;
		case "Supervisor":
			rol = RolEmpleado.SUPERVISOR;
			break;
		case "Gerente":
			rol = RolEmpleado.GERENTE;
			break;
		case "Admin":
			rol = RolEmpleado.ADMIN;
			break;
		}
		return rol;
	}
}
