package capaDePresentacion.gui.recursos;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class MyButton extends JButton {
	
	public MyButton(String text) {
		super(text);
		this.setName(text);
		this.setSize(120, 70);
	}
	
	public MyButton(String text, int x, int y) {
		super(text);
		//this.setSize(120, 50);
		setMaximumSize(new Dimension(x,y));
		setMinimumSize(new Dimension(x,y));
		setPreferredSize(new Dimension(x,y));
	}

	public MyButton(ImageIcon createImageIcon) {
		super(createImageIcon);
		this.setBorder(null);
		this.setBounds(new Rectangle());
	}
	
}
