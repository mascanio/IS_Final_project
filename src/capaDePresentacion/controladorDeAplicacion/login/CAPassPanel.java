package capaDePresentacion.controladorDeAplicacion.login;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import capaDeNegocio.servicioDeAplicacion.Login;
import capaDePresentacion.gui.login.PantallaLogin;
import capaDePresentacion.gui.login.PassPanel;
import capaDePresentacion.gui.recursos.ErrorDialog;

public class CAPassPanel implements ActionTecladoPassListener {

	private PassPanel passPanel;

	public CAPassPanel(String usuario, PantallaLogin pantallaLogin) {
		passPanel = new PassPanel(usuario, this);

		passPanel.init();
	}

	private void loginValido() {
		passPanel.cerrar();
		CALogin.loginValido();
	}

	@Override
	public void actionTecladoPassPerformed(ActionEvent arg0) {
		String c = ((Component) arg0.getSource()).getName();

		if (c.equalsIgnoreCase("borrar")) {
			if (Login.getService().isCampoUsuarioActivo()) {
				Login.getService().resetId();
				passPanel.resetUserText();
				Login.getService().resetId();
			} else {
				passPanel.resetPassText();
				Login.getService().resetPass();
			}
		} else if (c.equalsIgnoreCase("intro")) {
			if (Login.getService().isCampoUsuarioActivo()) {
				Login.getService().setCampoUsuarioActivo(false);
				passPanel.swap();
			} else {
				if (Login.getService().validarUsuario()) {
					loginValido();
					CALogin.loginValido();
				} else {
					JOptionPane.showMessageDialog(CALogin.getPantallaLogin(),
							"LOGIN INCORRECTO", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					// TODO ERROR
					ErrorDialog error = new ErrorDialog("a", "a");
					error.init();
					Login.getService().reset();
					passPanel.reset();
					
				}
			}

		} else if (c == "atras") {
			passPanel.cerrar();
			Login.getService().reset();
			passPanel.reset();
		} else if (c == "doble") {
			Login.getService().doble();
			passPanel.setIdtest(Login.getService().getId());
			passPanel.setPassText(Login.getService().getPass());
		} else {
			for (int i = 0; i < 10; i++) {
				if (c.equalsIgnoreCase("" + i)) {
					Login.getService().addNum(i);
					passPanel.setIdtest(Login.getService().getId());
					passPanel.setPassText(Login.getService().getPass());
				}
			}
		}
	}

}
