package capaDePresentacion.gui.admin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import baseDeDatos.usuarios.User.RolEmpleado;

import capaDePresentacion.CFPrincipal;
import capaDePresentacion.controladorDeAplicacion.admin.CAAdmin;

@SuppressWarnings("serial")
public class PreguntaEditar extends JDialog {
	private CAAdmin controler;
	private int id;
	
	public PreguntaEditar(CAAdmin controler,int id)
	{
		
		super(CFPrincipal.getFrameView(), true);
		this.id=id;		
		this.controler=controler;
	    setMinimumSize(new Dimension(300,120));
		setPreferredSize(new Dimension (300,120));
		setLocationRelativeTo(null);
		init();
		setVisible(true);
		
	}

	private void init() {
		String[] puestos = {"Cajero", "Supervisor","Gerente","Admin"};
		final JComboBox<String> opcion= new JComboBox<String>(puestos);
		JButton reset = new JButton("Reset Pass");
		JButton edit = new JButton("Editar Rol");
		setLayout(new BorderLayout());
		add(reset,BorderLayout.NORTH);
		add(edit,BorderLayout.CENTER);
		add(opcion,BorderLayout.SOUTH);
		edit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controler.editarElegido("Editar",id,RolEmpleado.valueOf((String) opcion.getSelectedItem()));
				cerrar();
				
			}
		});
reset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controler.editarElegido("Reset Pass",id,null);
				cerrar();
			}
		});
		
	}
	
	private void cerrar()
	{
		this.dispose();
	}
	

}
