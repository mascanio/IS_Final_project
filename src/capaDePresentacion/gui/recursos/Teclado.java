package capaDePresentacion.gui.recursos;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

//TEMPLATE  PATRON APLICADO 
@SuppressWarnings("serial")
public abstract class Teclado extends JPanel implements ActionListener {
	
	private MyButton[] numpad;
	private MyButton punto, borrar, atras, intro, doble;

	public Teclado() {

		this.setLayout(new BorderLayout());

		setAlignmentX(Component.CENTER_ALIGNMENT);
		setMaximumSize(new Dimension(384,384));
		setMinimumSize(new Dimension(384,384));
		setPreferredSize(new Dimension(384,384));
		
		JPanel mainPanel = new JPanel(new GridBagLayout());
		this.add(mainPanel);
		GridBagConstraints c = new GridBagConstraints();

		numpad = new MyButton[10];

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;

		c.gridwidth = 1;
		c.gridheight = 1;
		
		int num = 1;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				//numpad[num] = new MyButton(CreateImage.createImageIcon(num + ".jpg"));
				numpad[num] = new MyButton("" + num);
				c.gridx = j;
				c.gridy = i;
				mainPanel.add(numpad[num], c);
				numpad[num].addActionListener(this);
				numpad[num].setName("" + num);
				num++;
			}
		}

		numpad[0] = new MyButton("0");
		c.gridx = 1;
		c.gridy = 3;
		numpad[0].addActionListener(this);
		mainPanel.add(numpad[0], c);
		numpad[0].setName("0");

		punto = new MyButton(".");
		c.gridx = 0;
		c.gridy = 3;
		punto.addActionListener(this);
		mainPanel.add(punto, c);
		punto.setName("punto");

		borrar = new MyButton("C");
		c.gridx = 2;
		c.gridy = 3;
		borrar.addActionListener(this);
		borrar.setName("borrar");
		mainPanel.add(borrar, c);
		
		atras = new MyButton("ATRAS");
		atras.setBackground(Color.RED);
		c.gridx = 0;
		c.gridy = 4;
		mainPanel.add(atras, c);
		atras.setName("atras");
		
		atras.addActionListener(this);
		
		doble = new MyButton("00");
		c.gridx = 1;
		c.gridy = 4;
		mainPanel.add(doble, c);
		doble.setName("doble");
		doble.addActionListener(this);
		
		intro = new MyButton("INTRO");
		intro.setBackground(Color.GREEN);
		c.gridx = 2;
		c.gridy = 4;
		mainPanel.add(intro, c);
		intro.setName("intro");
		intro.addActionListener(this);

		this.setBackground(Color.BLACK);
		mainPanel.setBackground(Color.BLACK);
		this.setOpaque(true);
	}
	
	public void setAtrasEnabled(boolean b) {
		atras.setEnabled(b);
	}
	public void setIntroEnabled(boolean b) {
		intro.setEnabled(b);
	}
	public void setPuntoEnabled(boolean b) {
		punto.setEnabled(b);
	}
	
	public void setTextAtras(String s){
		atras.setText(s);
		atras.setName(s);
		
	}
	
}
