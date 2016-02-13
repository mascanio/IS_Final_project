package casosDePrueba;

import capaDePresentacion.controladorDeAplicacion.CAPrincipal;
import baseDeDatos.carta.BDCarta;
import baseDeDatos.carta.Bebida;
import baseDeDatos.carta.Complemento;
import baseDeDatos.carta.Hamburguesa;
import baseDeDatos.carta.Menu;
import baseDeDatos.carta.Postre;
import baseDeDatos.usuarios.BDUsuarios;
import baseDeDatos.usuarios.User;

public class Main {
	
	private static BDUsuarios bdUsuarios;
	private static BDCarta bdCarta;
	
	public static void generaPruebaBDUsuarios() {
	
		bdUsuarios = new BDUsuarios();
		
		bdUsuarios.addUser(new User(0, 1234, User.RolEmpleado.ADMIN));
		bdUsuarios.addUser(new User(1, 1234, User.RolEmpleado.CAJERO));
		bdUsuarios.addUser(new User(2, null, User.RolEmpleado.CAJERO));
		bdUsuarios.addUser(new User(3, 1234, User.RolEmpleado.SUPERVISOR));
		bdUsuarios.addUser(new User(4, 1234, User.RolEmpleado.GERENTE));
	}
	
	public static void generaPruebaBDCarta() {
		bdCarta = new BDCarta();
		
		bdCarta.addElemento(new Hamburguesa("Hamburguesa", 4.50));
		bdCarta.addElemento(new Postre("Postre", 1.70));
		bdCarta.addElemento(new Bebida("Bebida", 2.00));
		bdCarta.addElemento(new Complemento("Complemento", 1.50));
		bdCarta.addElemento(new Menu("Menu", 6.55));
	}
	
	public static void main(String[] args) {
		generaPruebaBDCarta();
		generaPruebaBDUsuarios();

		
		CAPrincipal.tpvStart(bdCarta, bdUsuarios);
	}

}
