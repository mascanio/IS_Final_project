package capaDePresentacion.controladorDeAplicacion.cajero;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import capaDeNegocio.servicioDeAplicacion.Cajero;
import capaDeNegocio.transfer.TOElementoCarta;
import capaDePresentacion.CFPrincipal;
import capaDePresentacion.controladorDeAplicacion.login.CALogin;
import capaDePresentacion.gui.cajero.PantallaPago;
import capaDePresentacion.gui.cajero.PantallaPedido;
import capaDePresentacion.gui.cajero.TicketPanel;
import capaDePresentacion.gui.recursos.ErrorDialog;

public class CACajero implements ActionPagoListener, ActionPedidoListener,
		ActionTecladoPedidoListener, ActionTecladoPagoListener {

	private PantallaPago pantallaPago;
	private PantallaPedido pantallaPedido;

	private TicketPanel ticketPanelPedido, ticketPanelPago;

	private Cajero servicio;

	public void start() {
		servicio = new Cajero();
		ticketPanelPedido = new TicketPanel(this);
		pantallaPedido = new PantallaPedido(this, ticketPanelPedido);
		CFPrincipal.changePanel(pantallaPedido);
	}

	public void pedidoAPago() {
		servicio.iniPago();

		ticketPanelPago = TicketPanel.generateTicketFromTO(
				servicio.getTicket(), this);
		ticketPanelPago.setEliminerEnabled(false);

		pantallaPago = new PantallaPago(this, ticketPanelPago);
		pantallaPago.setSiguienteEnabled(false);
		pantallaPago.setAtrasEnabled(true);
		servicio.resetImporte();
		pantallaPago.setImporteText(0);
		pantallaPago.setTotalText(servicio.getTotalNum());
		pantallaPago.setCambioText(0);

		CFPrincipal.changePanel(pantallaPago);
	}

	public void pagoAPedido() {
		servicio = new Cajero();
		servicio.guardaTicket();

		ticketPanelPedido = new TicketPanel(this);
		pantallaPedido = new PantallaPedido(this, ticketPanelPedido);

		CFPrincipal.changePanel(pantallaPedido);
	}

	public void pagoAtaras() {
		ticketPanelPedido.setEliminerEnabled(true);
		CFPrincipal.changePanel(pantallaPedido);
	}

	private void salir() {
		CFPrincipal.changePanel(CALogin.getPantallaLogin());
	}
	
	public void quitarElementoDeTicket(String s) {
		servicio.removeItem(s);
	}

	public void setCantidad(int n) {
		servicio.setCantidadActual(n);
	}

	public boolean isCantidadActivo() {
		return servicio.isCantidadActivo();
	}
	
	

	@Override
	public void actionPedidoPerformed(ActionEvent arg0) {
		String s = ((Component) arg0.getSource()).getName();

		if (s.equalsIgnoreCase("cantidad")) {
			servicio.invertirCantidadActivo();
		} else { // elemento de carta seleccionado

			TOElementoCarta elem = servicio.añadirElementoATicket(s);
			ticketPanelPedido.addItem(elem, servicio.getCantidadActual());

			pantallaPedido.resetCantidad();

			servicio.setCantidadActual(0);
			servicio.setCantidadActivo(false);
		}
	}

	@Override
	public void actionTecladoPedidoListener(ActionEvent arg0) {
		String c = ((Component) arg0.getSource()).getName();

		if (c.equalsIgnoreCase("intro")) {
			if(servicio.consultaCantidad())
			pedidoAPago();
			
		} else if (c.equalsIgnoreCase("cerrar")) {
			salir();
		} else if (isCantidadActivo()) {
			if (c.equalsIgnoreCase("doble")) {
				servicio.addCantidad(0);
				servicio.addCantidad(0);

				pantallaPedido.setCantidad(servicio.getCantidadActual());
			} else if (c.equalsIgnoreCase("borrar")) {
				servicio.resetCantidad();

				pantallaPedido.resetCantidad();
			} else {
				for (int i = 0; i < 10; i++) {
					if (c.equalsIgnoreCase("" + i)) {
						servicio.addCantidad(i);
						pantallaPedido
								.setCantidad(servicio.getCantidadActual());
					}
				}
			}
		}
	}

	@Override
	public void actionPagoPerformed(ActionEvent arg0) {
		String s = ((Component) arg0.getSource()).getName();

		if (s.equalsIgnoreCase("Sueprvisor")) {

		} else {
			if (servicio.pago(s)) { // Si el pago es correcto
				pantallaPago.setImporteText(servicio.getImporteNum());
				pantallaPago.setTotalText(servicio.getTotalNum());
				pantallaPago.setCambioText(servicio.getCambioNum());

				if (!servicio.isTarjetaActivo())
					pantallaPago.setTarjetaEnabled(false);

				pantallaPago.setAtrasEnabled(false);

				if (servicio.isPagoAcabado()) {
					pantallaPago.setSiguienteEnabled(true);
					pantallaPago.setEfectivoEnabled(false);
					pantallaPago.setTarjetaEnabled(false);
					pantallaPago.setChequeEnabled(false);
				}
			} else {
				
				JOptionPane.showMessageDialog(pantallaPago, "NO SE PUEDE COMPLETAR EL PAGO", "ERROR", JOptionPane.ERROR_MESSAGE);
				servicio.resetCantidad();
				pantallaPedido.resetCantidad();
				
				//TODO ERROR
				ErrorDialog e = new ErrorDialog("a", "a");
				e.init();
			}
		}
	}

	@Override
	public void actionTecladoPagoListener(ActionEvent arg0) {
		String c = ((Component) arg0.getSource()).getName();

		if (c.equalsIgnoreCase("intro")) {
			pagoAPedido();
		} else if (c.equalsIgnoreCase("atras")) {
			pagoAtaras();
		} else if (c.equalsIgnoreCase("doble")) {
			servicio.addDobleCero();
		} else if (c.equalsIgnoreCase("punto")) {
			servicio.addPunto();
		} else if (c.equalsIgnoreCase("borrar")) {
			servicio.resetImporte();
		} else {
			for (int i = 0; i < 10; i++) {
				if (c.equalsIgnoreCase("" + i)) {
					servicio.addImporte(i);
				}
			}
		}
		pantallaPago.setImporteText(servicio.getImporteNum());
	}
}
