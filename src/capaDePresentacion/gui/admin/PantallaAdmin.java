package capaDePresentacion.gui.admin;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import capaDePresentacion.controladorDeAplicacion.admin.CAAdmin;
import capaDePresentacion.gui.recursos.PanelConFondo;
import capaDePresentacion.gui.recursos.MyButton;

@SuppressWarnings("serial")
public class PantallaAdmin extends PanelConFondo implements ActionListener {

	private MyButton nuevoUsuario;
	private MyButton editarUsuario;
	private MyButton borrarUsuario;
	private MyButton crearBackup;
	private MyButton cargarBackup;
	private MyButton salir;

	private CAAdmin controlador;

	public PantallaAdmin(CAAdmin control) {
		controlador = control;
	}

	public void init() {		
		setVisible(true);
		this.setImage("TheGodBurguer.jpg");	
		
		this.setMinimumSize(new Dimension(900, 600));
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS));
		panelBotones.setOpaque(false);
		panelBotones.setVisible(true);

		

		this.nuevoUsuario = new MyButton("Nuevo Usuario", 120, 70);
		this.nuevoUsuario.setAlignmentY(Component.CENTER_ALIGNMENT);
		this.nuevoUsuario.setName("Nuevo Usuario");

		this.editarUsuario = new MyButton("Editar Usuario", 120, 70);
		this.editarUsuario.setAlignmentY(Component.CENTER_ALIGNMENT);
		this.editarUsuario.setName("Editar Usuario");

		this.borrarUsuario = new MyButton("Borrar Usuario", 120, 70);
		this.borrarUsuario.setAlignmentY(Component.CENTER_ALIGNMENT);
		this.borrarUsuario.setName("Borrar Usario");

		this.crearBackup = new MyButton("Crear Backup", 120, 70);
		this.crearBackup.setAlignmentY(Component.CENTER_ALIGNMENT);
		this.crearBackup.setName("Crear Backup");

		this.cargarBackup = new MyButton("Cargar Backup", 120, 70);
		this.cargarBackup.setAlignmentY(Component.CENTER_ALIGNMENT);
		this.cargarBackup.setName("Cargar Backup");
		
		this.salir = new MyButton("Salir", 120, 70);
		this.salir.setAlignmentY(Component.CENTER_ALIGNMENT);
		this.salir.setName("SALIR");
		this.salir.setBackground(Color.RED);


		panelBotones.add(Box.createHorizontalGlue());
		panelBotones.add(salir);
		panelBotones.add(Box.createHorizontalGlue());
		panelBotones.add(nuevoUsuario);
		panelBotones.add(Box.createHorizontalGlue());
		panelBotones.add(editarUsuario);
		panelBotones.add(Box.createHorizontalGlue());
		panelBotones.add(borrarUsuario);
		panelBotones.add(Box.createHorizontalGlue());
		panelBotones.add(crearBackup);
		panelBotones.add(Box.createHorizontalGlue());
		panelBotones.add(cargarBackup);
		panelBotones.add(Box.createHorizontalGlue());
		

		this.nuevoUsuario.addActionListener(this);
		this.editarUsuario.addActionListener(this);
		this.borrarUsuario.addActionListener(this);
		this.crearBackup.addActionListener(this);
		this.cargarBackup.addActionListener(this);
		this.salir.addActionListener(this);

		this.add(Box.createVerticalGlue());
		this.add(Box.createVerticalGlue());
		this.add(Box.createVerticalGlue());
		this.add(panelBotones);
		this.add(Box.createVerticalGlue());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controlador.actionAdminPerformed(e);
	}

	public void onError(String msg, PantallaAdmin admin) {
		JTextArea text = new JTextArea();
		text.setOpaque(false);
		text.setText(msg);
		JOptionPane.showMessageDialog(admin, text, "ERROR",
				JOptionPane.ERROR_MESSAGE);
	}

	public void popUp(String string) {
		JTextArea text = new JTextArea();
		text.setOpaque(false);
		text.setText(string);
		JOptionPane.showMessageDialog(this, string, "Exito",
				JOptionPane.INFORMATION_MESSAGE);
		
	}


}
