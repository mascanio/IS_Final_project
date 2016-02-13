package capaDeNegocio.servicioDeAplicacion;

import capaDeNegocio.transfer.TOElementoCarta;
import capaDeNegocio.transfer.TOTicket;
import capaDePresentacion.controladorDeAplicacion.CAPrincipal;

public class Cajero {
	private boolean cantidadActivo;
	
	private TOTicket ticketActual;
	private int cantidadActual;

	private double totalNum;
	private double importeNum;
	private double cambioNum;

	private boolean pagoAcabado;
	private boolean tarjetaActivo;
	
	private boolean hayPunto;
	private boolean hayPrimerDecimal;
	
	public Cajero() {
		this.ticketActual = new TOTicket();
		pagoAcabado = false;
		tarjetaActivo = true;
	}

	public void iniPago() {
		tarjetaActivo = true;
		hayPunto = false;
		hayPrimerDecimal = false;
		totalNum = ticketActual.getPrecioTotal();
	}
	
	public boolean pago(String s) {
		
		if (s.equalsIgnoreCase("tarjeta")) {
			if (importeNum > 0 && importeNum <= totalNum) {
				totalNum -= importeNum;
				importeNum = 0;

				pagoAcabado = (totalNum == 0);
				return true;
			} else {
				pagoAcabado = false;
				return false;
			}
		} else if (s.equalsIgnoreCase("Cheque")) {
			if (importeNum > 0) {

				tarjetaActivo = false;

				totalNum = (((totalNum - importeNum) < 0) ? 0 : totalNum
						- importeNum);
				importeNum = 0;

				pagoAcabado = totalNum == 0;

				return true;
			} else {
				pagoAcabado = false;
				return false;
			}
		} else { // EFECTIVO
			if (importeNum >= totalNum) {

				totalNum -= importeNum;
				importeNum = 0;
				
				if (totalNum < 0) {
					cambioNum = 0 - totalNum;
					totalNum = 0;
				}
				
				pagoAcabado = true;
				return true;				
			} else {
				pagoAcabado = false;
				return false;
			}
		}

	}

	public TOElementoCarta añadirElementoATicket(String s) {
		TOElementoCarta elem = CAPrincipal.getCartaDao().buscaElemento(s);

		if (cantidadActual == 0) {
			cantidadActual = 1;
		}

		ticketActual.addElemento(elem, cantidadActual);

		return elem;
	}

	public void removeItem(String s) {
		ticketActual.eliminaElemento(s);
	}

	public void guardaTicket() {
		// TODO Auto-generated method stub

	}

	public boolean isCantidadActivo() {
		return cantidadActivo;
	}

	public void addCantidad(int n) {
		this.cantidadActual = this.cantidadActual*10 + n;
	}
	
	public void resetCantidad() {
		this.cantidadActual = 0;
	}
	
	public void setCantidadActivo(boolean cantidadActivo) {
		this.cantidadActivo = cantidadActivo;
	}

	public void invertirCantidadActivo() {
		this.cantidadActivo = !this.cantidadActivo;
	}

	public int getCantidadActual() {
		return cantidadActual;
	}

	public void setCantidadActual(int n) {
		this.cantidadActual = n;
	}

	// ////////////////////////////////////////////////////

	public double getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(double totalNum) {
		this.totalNum = totalNum;
	}

	public double getImporteNum() {
		return importeNum;
	}

	public void setImporteNum(double importeNum) {
		this.importeNum = importeNum;
	}

	public double getCambioNum() {
		return cambioNum;
	}

	public void setCambioNum(double cambioNum) {
		this.cambioNum = cambioNum;
	}

	public boolean isPagoAcabado() {
		return pagoAcabado;
	}

	public void setPagoAcabado(boolean pagoAcabado) {
		this.pagoAcabado = pagoAcabado;
	}

	public boolean isTarjetaActivo() {
		return tarjetaActivo;
	}

	public void setTarjetaActivo(boolean tarjetaActivo) {
		this.tarjetaActivo = tarjetaActivo;
	}
	
	public void addImporte(int n) {
		double d = n;
		if (hayPunto) {
			if (hayPrimerDecimal) {
				importeNum += (d / 100);
			} else {
				hayPrimerDecimal = true;
				importeNum += (d / 10);
			}
		} else {
			importeNum = importeNum * 10 + d;
		}
	}
	
	public void addPunto() {
		hayPunto = true;
	}
	
	public void addDobleCero() {
		if (!hayPunto) {
			importeNum*= 100;
		}
	}
	
	public void resetImporte() {
		cambioNum = 0.0;
		importeNum = 0.0;
		hayPunto = false;
		hayPrimerDecimal = false;
	}

	public TOTicket getTicket() {
		return ticketActual;
	}

	public boolean consultaCantidad() {

		return ticketActual.getPrecioTotal()!=0;
	}
}
