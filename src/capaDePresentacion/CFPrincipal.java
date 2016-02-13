package capaDePresentacion;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import capaDePresentacion.controladorDeAplicacion.login.CALogin;

public class CFPrincipal {
	private static JFrame frameView;
	private static CFPrincipal singleton;

	public static CFPrincipal getCFPrincipal() {
		if (singleton == null) {
			singleton = new CFPrincipal();
		}
		
		return singleton;
	}
	
	public static JFrame getFrameView() {
		if(frameView == null)
			frameView = new JFrame();
		
		return frameView;
	}
	
	private CFPrincipal() {
		getFrameView().setTitle("TPV - THE GOD BURGER");
	}
	
	public void start() {
		changePanel(CALogin.getPantallaLogin());
		frameView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameView.setLocationRelativeTo(null); //Hace que la ventana se ejecute en
				// el centro de la pantalla
		frameView.setVisible(true);
				// Maximiza la pantalla nada más empezar
		frameView.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frameView.setMinimumSize(new Dimension(900, 600));
	}

	
	public static void changePanel(JPanel panel) {
		frameView.setContentPane(panel);
		frameView.repaint();
		frameView.revalidate();
	}
}
