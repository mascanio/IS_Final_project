package capaDePresentacion.gui.cajero;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import capaDePresentacion.controladorDeAplicacion.cajero.CACajero;
import capaDePresentacion.gui.recursos.MyButton;
import capaDePresentacion.gui.recursos.Teclado;

@SuppressWarnings("serial")
public class PantallaPedido extends JPanel implements ActionListener {

	private MyButton hamburguesa;
	private MyButton postre;
	private MyButton bebida;
	private MyButton menu;
	private MyButton complemento;
	private MyButton cantidad;

	private MyButton supervisor;

	private TecladoCaja teclado;
	private TicketPanel ticketPanel;

	private JPanel derecho;
	private JPanel izquierdo;
	private JPanel abajoDerecho;
	private JPanel superCantidad;

	private JTextField cantidadText;

	private CACajero controlador;

	public TicketPanel getTicketPanel() {
		return ticketPanel;
	}
	
	public void setCantidad(int n) {
		this.cantidadText.setText("" + n);
	}
	
	public void resetCantidad() {
		cantidadText.setText("");
	}
	
	public PantallaPedido(CACajero caCajero, TicketPanel ticketPanel) {

		this.controlador = caCajero;

		this.derecho = new JPanel(new GridLayout(2, 1));
		this.derecho.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		this.derecho.setBackground(Color.BLACK);
		this.derecho.setVisible(true);

		this.izquierdo = new JPanel(new GridLayout(5, 0, 30, 30));
		this.izquierdo.setBorder(BorderFactory
				.createEmptyBorder(30, 30, 30, 30));
		this.izquierdo.setBackground(Color.BLACK);
		this.izquierdo.setVisible(true);

		this.abajoDerecho = new JPanel(new GridLayout(0, 2));
		this.abajoDerecho.setBorder(BorderFactory.createEmptyBorder(10, 0, 10,
				10));
		this.abajoDerecho.setBackground(Color.BLACK);
		this.abajoDerecho.setVisible(true);

		this.superCantidad = new JPanel(new GridLayout(3, 1, 0, 20));
		this.superCantidad.setBorder(BorderFactory.createEmptyBorder(30, 30,
				30, 30));
		this.superCantidad.setBackground(Color.BLACK);
		this.superCantidad.setVisible(true);

		this.setLayout(new GridLayout(0, 2));

		this.supervisor = new MyButton("SUPER");
		// this.supervisor.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
		this.supervisor.setFont(new Font("Arial", Font.BOLD, 35));
		this.supervisor.setBackground(Color.green);
		this.superCantidad.add(supervisor);

		this.hamburguesa = new MyButton("Hamburguesa");
		this.hamburguesa.setMaximumSize(new Dimension(50, 50));
		this.hamburguesa.setMinimumSize(new Dimension(10, 10));
		this.hamburguesa.addActionListener(this);
		this.izquierdo.add(hamburguesa);

		this.postre = new MyButton("Postre");
		this.izquierdo.add(postre);
		this.postre.addActionListener(this);

		this.bebida = new MyButton("Bebida");
		this.izquierdo.add(bebida);
		this.bebida.addActionListener(this);

		this.menu = new MyButton("Menu");
		this.izquierdo.add(menu);
		this.menu.addActionListener(this);

		this.complemento = new MyButton("Complemento");
		this.izquierdo.add(complemento);
		this.complemento.addActionListener(this);

		this.cantidadText = new JTextField("");
		this.superCantidad.add(cantidadText);
		this.cantidadText.setFont(new Font("Arial", Font.BOLD, 50));
		this.cantidadText.setEditable(false);
		this.cantidadText.setHorizontalAlignment(JTextField.RIGHT);

		this.cantidad = new MyButton("CANTIDAD");
		this.cantidad.setFont(new Font("Arial", Font.BOLD, 35));
		this.cantidad.setBackground(Color.MAGENTA);
		this.cantidad.addActionListener(this);
		this.superCantidad.add(cantidad);

		this.ticketPanel = ticketPanel;
		this.derecho.add(new JScrollPane(ticketPanel));
		

		this.ticketPanel.setBackground(Color.PINK);

		this.teclado = new TecladoCaja();

		this.add(izquierdo);
		this.abajoDerecho.add(superCantidad);
		this.abajoDerecho.add(teclado);
		this.derecho.add(abajoDerecho);
		this.add(derecho);

		this.teclado.setTextAtras("CERRAR");

		this.setVisible(true);
		this.setBackground(Color.BLACK);

	}

	private class TecladoCaja extends Teclado {

		@Override
		public void actionPerformed(ActionEvent e) {
			controlador.actionTecladoPedidoListener(e);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controlador.actionPedidoPerformed(e);
	}

}
