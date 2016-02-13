package capaDePresentacion.gui.admin;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import capaDePresentacion.CFPrincipal;
import capaDePresentacion.controladorDeAplicacion.admin.CAAdmin;
import capaDePresentacion.gui.recursos.Teclado;

@SuppressWarnings("serial")
public class CrearUsuario extends JDialog {

	private JLabel usuario;
	private JLabel cargo;
	private JTextField usuarioIn;
	private JComboBox<String> cargoIn;

	private TecladoPass teclado;
	private JPanel panelSuperior;
	private String puesto;
	private String accion;

	private CAAdmin controlador;

	public CrearUsuario(CAAdmin controlador,String action) {
		super(CFPrincipal.getFrameView(), true);
		this.controlador = controlador;
		accion=action;
		init();
	}

	public void init() {
		this.setPreferredSize(new Dimension(500, 500));
		this.setMinimumSize(new Dimension(500, 500));

		this.setLocationRelativeTo(null);
		this.setUndecorated(true);

		this.pack();
		this.toFront();
		this.setLayout(new GridLayout(2, 1, 0, 0));

		panelSuperior = new JPanel(new GridLayout(4, 1, 0, 0));
	
		usuario = new JLabel(accion);
		usuario.setHorizontalAlignment(JLabel.CENTER);
		usuario.setFont(new Font("Arial", 0, 20));
		usuario.setOpaque(true);
		usuario.setBackground(Color.BLACK);
		usuario.setForeground(Color.white);

		this.cargo = new JLabel("SESION " + puesto);
		cargo.setHorizontalAlignment(JLabel.CENTER);
		cargo.setFont(new Font("Arial", 0, 36));
		cargo.setOpaque(true);
		cargo.setBackground(Color.BLACK);
		cargo.setForeground(Color.white);

		this.cargo = new JLabel("CARGO");
		cargo.setHorizontalAlignment(JLabel.CENTER);
		cargo.setFont(new Font("Arial", 0, 36));
		cargo.setOpaque(true);
		cargo.setBackground(Color.BLACK);
		cargo.setForeground(Color.white);

		this.usuarioIn = new JTextField();
		usuarioIn.setEditable(false);
		usuarioIn.setFont(new Font("Arial", 0, 36));
		usuarioIn.setOpaque(true);
		usuarioIn.setBackground(Color.GREEN);
		usuarioIn.setText("");

		String[] puestos = {"Cajero", "Supervisor","Gerente","Admin"};
		cargoIn= new JComboBox<>(puestos);
		cargoIn.setFont(new Font("Arial", 0, 20));
	

		this.teclado = new TecladoPass();

		this.panelSuperior.add(cargo);
		this.panelSuperior.add(usuario);
		this.panelSuperior.add(usuarioIn);
		this.panelSuperior.add(cargo);
		this.panelSuperior.add(cargoIn);
		this.add(panelSuperior);
		this.add(teclado);

		this.setVisible(true);
	}

	public void cerrar() {
		this.dispose();
	}

	private class TecladoPass extends Teclado {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String c = ((Component) e.getSource()).getName();

			if (c.equalsIgnoreCase("borrar")) {
				usuarioIn.setText("");
			} else if (c.equalsIgnoreCase("intro")) {
	              controlador.intro(usuarioIn.getText(),usuario.getText(),(String) cargoIn.getSelectedItem());
	              cerrar();
			}

			else if (c == "atras") {
				cerrar();
			} else if (c == "doble") {
				usuarioIn.setText(usuarioIn.getText() + "00");
			} else {
				for (int i = 0; i < 10; i++) {
					if (c.equalsIgnoreCase("" + i)) {

						usuarioIn.setText(usuarioIn.getText() + i);
					}
				}
			}
		
		}
	}
	
	public void resetUserText() {
		usuarioIn.setText("");
	}

	public void resetPassText() {

	}
	
	public void swap() {
		usuarioIn.setBackground(Color.RED);

	}
	
	public void setPassText(int n) {

	}

	public void setIdtest(int id) {
		usuarioIn.setText("" + id);
	}

	public void reset() {
		resetPassText();
		resetPassText();
		
		usuarioIn.setBackground(Color.GREEN);

	}
}