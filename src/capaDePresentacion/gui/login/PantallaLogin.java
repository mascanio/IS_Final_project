package capaDePresentacion.gui.login;

import java.awt.*;

import javax.swing.*;

import capaDePresentacion.controladorDeAplicacion.login.CALogin;
import capaDePresentacion.gui.recursos.PanelConFondo;
import capaDePresentacion.gui.recursos.MyButton;

@SuppressWarnings("serial")
public class PantallaLogin extends PanelConFondo {

	private MyButton cajero;
	private MyButton supervisor;
	private MyButton gerente;
	private MyButton administrador;

	private CALogin controlador;

	// GraphicsDevice graf

	public PantallaLogin() {
		init();
		// grafica=GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		// grafica.setFullScreenWindow(this);

	}

	private void init() {
		this.controlador = CALogin.getCALogin();

		setVisible(true);

		this.setImage("TheGodBurguer.jpg");	
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS));
		panelBotones.setOpaque(false);
		panelBotones.setVisible(true);

		this.cajero = new MyButton("CAJERO", 120, 70);
		this.cajero.setAlignmentY(Component.CENTER_ALIGNMENT);
		this.cajero.setName("cajero");
		
		this.supervisor = new MyButton("SUPERVISOR", 120, 70);
		this.supervisor.setAlignmentY(Component.CENTER_ALIGNMENT);
		this.supervisor.setName("supervisor");
		
		this.gerente = new MyButton("GERENTE", 120, 70);
		this.gerente.setAlignmentY(Component.CENTER_ALIGNMENT);
		this.gerente.setName("GERENTE");
		
		this.administrador = new MyButton("ADMIN", 120, 70);
		this.administrador.setAlignmentY(Component.CENTER_ALIGNMENT);
		this.administrador.setName("administrador");
		
		panelBotones.add(Box.createHorizontalGlue());
		panelBotones.add(cajero);
		panelBotones.add(Box.createHorizontalGlue());
		panelBotones.add(supervisor);
		panelBotones.add(Box.createHorizontalGlue());
		panelBotones.add(gerente);
		panelBotones.add(Box.createHorizontalGlue());
		panelBotones.add(administrador);
		panelBotones.add(Box.createHorizontalGlue());

		this.cajero.addActionListener(controlador);
		this.supervisor.addActionListener(controlador);
		this.gerente.addActionListener(controlador);
		this.administrador.addActionListener(controlador);

		this.add(Box.createVerticalGlue());
		this.add(Box.createVerticalGlue());
		this.add(Box.createVerticalGlue());
		this.add(panelBotones);
		this.add(Box.createVerticalGlue());

	}

}
