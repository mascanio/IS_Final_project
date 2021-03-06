package capaDePresentacion.gui.login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import capaDePresentacion.CFPrincipal;
import capaDePresentacion.controladorDeAplicacion.login.ActionTecladoPassListener;
import capaDePresentacion.gui.recursos.Teclado;

@SuppressWarnings("serial")
public class PassPanel extends JDialog {

	private JLabel usuario;
	private JLabel cargo;
	private JLabel contraseņa;
	private JTextField usuarioIn;
	private JPasswordField contraseņaIn;

	private TecladoPass teclado;
	private JPanel panelSuperior;
	private String puesto;

	private ActionTecladoPassListener controlador;

	// Panel con numeros de 1 al 9 el . 00 y botones de intro y atras
	// private Numeros numeros;

	public PassPanel(String puesto, ActionTecladoPassListener controlador) {
		super(CFPrincipal.getFrameView(), true);
		this.controlador = controlador;

		this.puesto = puesto;
	}

	public void init() {
		this.setPreferredSize(new Dimension(500, 500));
		this.setMinimumSize(new Dimension(500, 500));

		this.setLocationRelativeTo(null);
		this.setUndecorated(true);

		this.pack();
		this.toFront();
		this.setLayout(new GridLayout(2, 1, 0, 0));

		panelSuperior = new JPanel(new GridLayout(5, 1, 0, 0));
		// this.setLayout(new GridLayout(6,1,0,0));

		usuario = new JLabel("USUARIO");
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

		this.contraseņa = new JLabel("CONTRASEŅA");
		contraseņa.setHorizontalAlignment(JLabel.CENTER);
		contraseņa.setFont(new Font("Arial", 0, 36));
		contraseņa.setOpaque(true);
		contraseņa.setBackground(Color.BLACK);
		contraseņa.setForeground(Color.white);

		this.usuarioIn = new JTextField();
		usuarioIn.setEditable(false);
		usuarioIn.setFont(new Font("Arial", 0, 36));
		usuarioIn.setOpaque(true);
		usuarioIn.setBackground(Color.GREEN);
		usuarioIn.setText("");

		this.contraseņaIn = new JPasswordField();
		contraseņaIn.setFont(new Font("Arial", 0, 36));
		contraseņaIn.setOpaque(true);
		contraseņaIn.setBackground(Color.RED);
		contraseņaIn.setEditable(false);

		this.teclado = new TecladoPass();

		this.panelSuperior.add(cargo);
		this.panelSuperior.add(usuario);
		this.panelSuperior.add(usuarioIn);
		this.panelSuperior.add(contraseņa);
		this.panelSuperior.add(contraseņaIn);
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
			controlador.actionTecladoPassPerformed(e);
		}
	}
	
	public void resetUserText() {
		usuarioIn.setText("");
	}

	public void resetPassText() {
		contraseņaIn.setText("");
	}
	
	public void swap() {
		usuarioIn.setBackground(Color.RED);
		contraseņaIn.setBackground(Color.GREEN);
	}
	
	public void setPassText(int n) {
		if (n != 0)
			contraseņaIn.setText("" + n);
	}

	public void setIdtest(int id) {
		usuarioIn.setText("" + id);
	}

	public void reset() {
		resetPassText();
		resetUserText();
		
		usuarioIn.setBackground(Color.GREEN);
		contraseņaIn.setBackground(Color.RED);
	}
}
