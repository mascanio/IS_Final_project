package capaDePresentacion.gui.recursos;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;

import capaDePresentacion.gui.imagenes.CreateImage;
@SuppressWarnings("serial")
public class PanelConFondo extends javax.swing.JPanel {
	String image = null;
	public PanelConFondo(){
		
	}
	
	public PanelConFondo(LayoutManager Layout) {
		
		super(Layout);
		
	}
	
	public void setImage(String g){
		this.image = g;
	}
	
	public void paintComponent(Graphics g){
		Dimension tamanio = getSize();
		ImageIcon imagenFondo = CreateImage.createImageIcon(image);
		g.drawImage(imagenFondo.getImage(),0,0,tamanio.width, tamanio.height, null);
		setOpaque(false);
		super.paintComponent(g);
	}

}