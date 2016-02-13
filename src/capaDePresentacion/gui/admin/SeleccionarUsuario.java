package capaDePresentacion.gui.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import capaDePresentacion.CFPrincipal;
import capaDePresentacion.controladorDeAplicacion.admin.CAAdmin;
import capaDePresentacion.gui.recursos.Teclado;

@SuppressWarnings("serial")
public class SeleccionarUsuario extends JDialog {

	protected JLabel usuario;
	protected JTextField usuarioIn;
	protected TecladoPass teclado;
	protected JPanel panelSuperior;
	protected CAAdmin controlador;

	public SeleccionarUsuario(CAAdmin controlador, String accion) {
		super(CFPrincipal.getFrameView(), true);
		this.controlador = controlador;
		this.setPreferredSize(new Dimension(500, 460));
		this.setMinimumSize(new Dimension(500, 460));
		String action= accion;
		
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);

		this.pack();
		this.toFront();
		//this.setLayout(new GridLayout(2, 1, 0, 0));

		panelSuperior = new JPanel(new GridLayout(2, 1, 0, 0));
		// this.setLayout(new GridLayout(6,1,0,0));

		usuario = new JLabel(action);
		usuario.setHorizontalAlignment(JLabel.CENTER);
		usuario.setFont(new Font("Arial", 0, 20));
		usuario.setOpaque(true);
		usuario.setBackground(Color.BLACK);
		usuario.setForeground(Color.white);

		usuarioIn = new JTextField();
		usuarioIn.setEditable(false);
		usuarioIn.setFont(new Font("Arial", 0, 36));
		usuarioIn.setOpaque(true);
		usuarioIn.setBackground(Color.GREEN);
		usuarioIn.setText("");

		this.teclado = new TecladoPass();

		this.panelSuperior.add(usuario);
		this.panelSuperior.add(usuarioIn);
		this.setLayout(new BorderLayout());
		this.add(panelSuperior, BorderLayout.NORTH);
		this.add(teclado, BorderLayout.CENTER);
		//this.add(panelSuperior, 0);
		//this.add(teclado,1);
		this.setVisible(true);

	}

	private void cerrar() {
		this.dispose();
	}

	private class TecladoPass extends Teclado {

		@Override
		public void actionPerformed(ActionEvent e) {
			String c = ((Component) e.getSource()).getName();

			if (c.equalsIgnoreCase("borrar")) {
				usuarioIn.setText("");
			} else if (c.equalsIgnoreCase("intro")) {
	              controlador.intro(usuarioIn.getText(),usuario.getText(),null);
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

}
