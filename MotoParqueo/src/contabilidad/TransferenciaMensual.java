package contabilidad;

import java.util.GregorianCalendar;

public class TransferenciaMensual extends Transferencia {
	private static final long serialVersionUID = 5998493724931568303L;
	private String placa;
	private String nombre;
	public TransferenciaMensual(GregorianCalendar fecha, double monto, tipoTrans tipo, String cedula, String nombre) {
		super(fecha, monto, tipo);
		this.placa = cedula;
		this.nombre = nombre;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
