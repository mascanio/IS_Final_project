package capaDePresentacion.gui.cajero;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import capaDePresentacion.controladorDeAplicacion.cajero.CACajero;
import capaDePresentacion.gui.recursos.MyButton;
import capaDePresentacion.gui.recursos.Teclado;

@SuppressWarnings("serial")
public class PantallaPago extends JPanel implements ActionListener {

	private MyButton supervisor;
	private MyButton efectivo;
	private MyButton tarjeta;
	private MyButton cheque;

	private JLabel totalLabel;
	private JLabel importeLabel;
	private JLabel cambioLabel;

	private JTextField totalText;
	private JTextField importeText;
	private JTextField cambioText;

	private TecladoCaja teclado;
	private TicketPanel ticket;

	private JPanel derecho;
	private JPanel izquierdo;
	private JPanel abajoDerecho;
	private JPanel superCantidad;

	private JPanel izquierdoSuperior;
	private JPanel izquierdoMedio;
	private JPanel izquierdoInferior;

	private CACajero controlador;

	private DecimalFormat df;

	public PantallaPago(CACajero caCajero, TicketPanel ticket) {

		df = new DecimalFormat();
		df.setMinimumFractionDigits(2);

		this.controlador = caCajero;

		this.derecho = new JPanel(new GridLayout(2, 1));
		this.derecho.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		this.derecho.setBackground(Color.BLACK);
		this.derecho.setVisible(true);

		this.izquierdo = new JPanel(new GridLayout(3, 0));
		this.izquierdo.setBorder(BorderFactory
				.createEmptyBorder(30, 30, 30, 30));
		this.izquierdo.setBackground(Color.BLACK);
		this.izquierdo.setVisible(true);

		this.izquierdoSuperior = new JPanel();
		this.izquierdoSuperior.setLayout((new GridLayout(3, 0)));
		this.izquierdoSuperior.setBackground(Color.black);

		this.totalLabel = new JLabel("TOTAL");
		this.totalLabel.setHorizontalAlignment(JLabel.CENTER);
		this.totalLabel.setFont(new Font("Arial", Font.BOLD, 35));
		this.totalLabel.setForeground(Color.white);
		this.totalLabel.setBackground(Color.black);
		this.izquierdoSuperior.add(this.totalLabel);

		this.totalText = new JTextField();
		this.totalText.setHorizontalAlignment(JLabel.CENTER);
		this.totalText.setFont(new Font("Arial", Font.BOLD, 35));
		this.totalText.setForeground(Color.white);
		this.totalText.setBackground(Color.black);
		this.totalText.setEditable(false);
		this.izquierdoSuperior.add(this.totalText);

		this.importeLabel = new JLabel("IMPORTE");
		this.importeLabel.setHorizontalAlignment(JLabel.CENTER);
		this.importeLabel.setFont(new Font("Arial", Font.BOLD, 35));
		this.importeLabel.setForeground(Color.white);
		this.importeLabel.setBackground(Color.black);
		this.izquierdoSuperior.add(this.importeLabel);

		this.izquierdoMedio = new JPanel();
		this.izquierdoMedio.setLayout((new GridLayout(3, 0)));
		this.izquierdoMedio.setBackground(Color.black);

		this.importeText = new JTextField();
		this.importeText.setHorizontalAlignment(JLabel.CENTER);
		this.importeText.setFont(new Font("Arial", Font.BOLD, 35));
		this.importeText.setForeground(Color.white);
		this.importeText.setBackground(Color.black);
		this.importeText.setEditable(false);
		this.izquierdoMedio.add(this.importeText);

		this.cambioLabel = new JLabel("CAMBIO");
		this.cambioLabel.setHorizontalAlignment(JLabel.CENTER);
		this.cambioLabel.setFont(new Font("Arial", Font.BOLD, 35));
		this.cambioLabel.setForeground(Color.white);
		this.cambioLabel.setBackground(Color.black);
		this.izquierdoMedio.add(this.cambioLabel);

		this.cambioText = new JTextField();
		this.cambioText.setHorizontalAlignment(JLabel.CENTER);
		this.cambioText.setFont(new Font("Arial", Font.BOLD, 35));
		this.cambioText.setForeground(Color.white);
		this.cambioText.setBackground(Color.black);
		this.cambioText.setEditable(false);
		this.izquierdoMedio.add(this.cambioText);

		this.izquierdoInferior = new JPanel();
		this.izquierdoInferior.setLayout((new GridLayout(0, 3, 40, 0)));
		this.izquierdoInferior.setBorder(BorderFactory.createEmptyBorder(40, 0,
				40, 0));
		this.izquierdoInferior.setBackground(Color.black);

		this.efectivo = new MyButton("EFECTIVO");
		this.efectivo.addActionListener(this);
		this.izquierdoInferior.add(efectivo);

		this.tarjeta = new MyButton("TARJETA");
		this.tarjeta.addActionListener(this);
		this.izquierdoInferior.add(tarjeta);

		this.cheque = new MyButton("CHEQUE");
		this.cheque.addActionListener(this);
		this.izquierdoInferior.add(cheque);

		this.izquierdo.add(izquierdoSuperior);
		this.izquierdo.add(izquierdoMedio);
		this.izquierdo.add(izquierdoInferior);

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

		this.ticket = ticket;
		this.derecho.add(new JScrollPane(ticket));

		this.ticket.setBackground(Color.PINK);

		this.teclado = new TecladoCaja();

		this.add(izquierdo);
		this.abajoDerecho.add(superCantidad);
		this.abajoDerecho.add(teclado);
		this.derecho.add(abajoDerecho);
		this.add(derecho);

		this.teclado.setTextAtras("ATRAS");

		this.setVisible(true);
		this.setBackground(Color.BLACK);
	}

	private class TecladoCaja extends Teclado {

		@Override
		public void actionPerformed(ActionEvent e) {
			controlador.actionTecladoPagoListener(e);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		controlador.actionPagoPerformed(arg0);
	}

	/*
	 * CAMBIO DE LOS JTEXT
	 */

	public void setTotalText(double totalNum) {
		if (totalNum != 0)
			this.totalText.setText("" + df.format(totalNum) + " $");
		else
			this.totalText.setText("");
	}

	public void setImporteText(double importeNum) {
		this.importeText.setText("" + df.format(importeNum) + " $");
	}

	public void setCambioText(double cambionum) {
		this.cambioText.setText("" + df.format(cambionum) + " $");
	}

	/*
	 * CAMBIO ESTADO BOTONES
	 */

	public void setTarjetaEnabled(boolean b) {
		this.tarjeta.setEnabled(b);
	}
	
	public void setChequeEnabled(boolean b) {
		this.cheque.setEnabled(b);
	}
	
	public void setEfectivoEnabled(boolean b) {
		this.efectivo.setEnabled(b);
	}

	public void setAtrasEnabled(boolean b) {
		this.teclado.setAtrasEnabled(b);
	}

	public void setSiguienteEnabled(boolean b) {
		this.teclado.setIntroEnabled(b);
	}

}
