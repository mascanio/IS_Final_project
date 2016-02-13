package capaDePresentacion.gui.cajero;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import capaDeNegocio.transfer.TOElementoCarta;
import capaDeNegocio.transfer.TOTicket;
import capaDeNegocio.transfer.TOTicket.TOElementoCartaExt;
import capaDePresentacion.controladorDeAplicacion.cajero.CACajero;
import capaDePresentacion.gui.recursos.MyButton;

@SuppressWarnings("serial")
public class TicketPanel extends JPanel {

	private int numElements;
	private ArrayList<ListItem> list;

	private CACajero controler;

	class ListItem extends JPanel implements ActionListener {
		private String nombre;
		private int contador, indexAtList;
		private double precio;

		private JTextArea textArea, countArea, priceArea;
		private MyButton quitar;

		private double getPrecio() {
			return this.precio;
		}

		private int getCantidad() {
			return this.contador;
		}

		private void setIndex(int i) {
			indexAtList = i;
		}

		private int getIndex() {
			return indexAtList;
		}

		private ListItem(String name, double price, int cantidad) {
			this.nombre = name;
			this.contador = cantidad;
			this.precio = price;

			this.setMaximumSize(new Dimension(1024, 45));
			this.setMinimumSize(new Dimension(100, 45));
			// this.setPreferredSize(new Dimension(384, 45));

			textArea = new JTextArea();
			textArea.setText(this.nombre);
			// textArea.setMaximumSize(new Dimension(0, 45));
			// textArea.setMinimumSize(new Dimension(174, 45));
			// textArea.setPreferredSize(new Dimension(174, 45));
			textArea.setFont(new Font("Verdana", Font.PLAIN, 22));

			countArea = new JTextArea();
			countArea.setText("" + contador);
			countArea.setMaximumSize(new Dimension(60, 45));
			countArea.setMinimumSize(new Dimension(60, 45));
			countArea.setPreferredSize(new Dimension(60, 45));
			countArea.setFont(new Font("Verdana", Font.PLAIN, 22));

			priceArea = new JTextArea();
			DecimalFormat df = new DecimalFormat();
			df.setMinimumFractionDigits(2);
			priceArea.setText("" + df.format(this.precio) + " $");
			priceArea.setMaximumSize(new Dimension(100, 45));
			priceArea.setMinimumSize(new Dimension(100, 45));
			priceArea.setPreferredSize(new Dimension(100, 45));
			priceArea.setFont(new Font("Verdana", Font.PLAIN, 22));

			quitar = new MyButton("X", 45, 45);
			quitar.setBackground(Color.RED);
			quitar.addActionListener(this);

			this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			this.add(textArea);
			this.add(priceArea);
			this.add(countArea);
			this.add(quitar);

			// model.addElement(this);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			removeItem(this);
		}

		public void setEliminarEnabled(boolean b) {
			this.quitar.setEnabled(b);
		}

	}

	public static TicketPanel generateTicketFromTO(TOTicket to, CACajero c) {
		TicketPanel r = new TicketPanel(c);
		TOElementoCartaExt elem;
		
		for (Iterator<TOElementoCartaExt> iterator = to.getLista().iterator(); iterator.hasNext();) {
			elem = iterator.next();			
			r.addItem(elem, elem.getCantidad());
		}
		
		return r;		
	}

	public TicketPanel(CACajero controler) {
		numElements = 0;
		list = new ArrayList<ListItem>();

		this.controler = controler;

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setVisible(true);
	}

	public void addItem(TOElementoCarta elem, int cantidad) {

		ListItem item = new ListItem(elem.getNombre(), elem.getPrecio(),
				cantidad);
		list.add(item);
		item.setIndex(numElements++);
		this.add(item);
		this.repaint();
		this.revalidate();
	}

	public double precioTotal() {
		double r = 0;

		for (Iterator<ListItem> iterator = list.iterator(); iterator.hasNext();) {
			ListItem i = iterator.next();
			r += i.getPrecio() * i.getCantidad();
		}

		return r;
	}

	private void removeItem(ListItem item) {

		controler.quitarElementoDeTicket(item.nombre);

		list.remove(item.getIndex());
		this.remove(item.getIndex());
		this.removeAll();

		for (int i = 0; i < list.size(); i++) {
			list.get(i).setIndex(i);
			this.add(list.get(i));
		}

		this.repaint();
		this.revalidate();
		numElements--;
	}

	public void setEliminerEnabled(boolean b) {
		for (Iterator<ListItem> iterator = list.iterator(); iterator.hasNext();) {
			iterator.next().setEliminarEnabled(b);
		}
	}

}