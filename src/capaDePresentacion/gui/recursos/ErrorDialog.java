package capaDePresentacion.gui.recursos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;

import capaDePresentacion.CFPrincipal;

@SuppressWarnings("serial")
public class ErrorDialog extends JDialog {

	private String mensaje, titulo;
	
	public ErrorDialog(String titulo, String mensaje) {
		super(CFPrincipal.getFrameView(), true);
		this.titulo = titulo;
		this.mensaje = mensaje;
		
		//init();
	}

	
	public void init() {

		this.setPreferredSize(new Dimension(500, 500));
		this.setMinimumSize(new Dimension(500, 500));

		this.setLocationRelativeTo(null);
		this.setUndecorated(true);

		this.pack();
		this.toFront();

		this.setLayout(new GridLayout(0, 3));

		this.setBackground(Color.black);

		JLabel labelTitulo = new JLabel(titulo);
		labelTitulo.setHorizontalAlignment(JLabel.CENTER);
		labelTitulo.setFont(new Font("Arial", 0, 22));
		labelTitulo.setOpaque(true);
		labelTitulo.setBackground(Color.RED);
		labelTitulo.setForeground(Color.white);
		this.add(labelTitulo);
	
		JLabel labelMensaje = new JLabel(mensaje);
		labelTitulo.setHorizontalAlignment(JLabel.CENTER);
		labelTitulo.setFont(new Font("Arial", 0, 16));
		labelTitulo.setOpaque(true);
		labelTitulo.setBackground(Color.BLACK);
		labelTitulo.setForeground(Color.white);
		this.add(labelMensaje);
		
		MyButton ok = new MyButton("OK", 80, 40);
		ok.setBackground(Color.green);
		
		this.add(ok);
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
	}
}
