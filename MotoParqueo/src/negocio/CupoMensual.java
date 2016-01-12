package negocio;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class CupoMensual implements Serializable{
	private static final long serialVersionUID = -6318213858283242672L;
	private GregorianCalendar fechaIngreso;
	private GregorianCalendar fechaSiguienteCobro;
	private ClienteMensual cliente;
	/**
	 * 
	 */
	public CupoMensual(String placa, String cedula, String nombre, String celular, GregorianCalendar fechaIngreso) {
		super();
		this.cliente = new ClienteMensual(placa, cedula, nombre, celular);
		this.fechaIngreso = fechaIngreso;
		GregorianCalendar siguienteCobro = (GregorianCalendar) fechaIngreso.clone();
		if (siguienteCobro.get(GregorianCalendar.MONTH)<=10) {
			siguienteCobro.set(GregorianCalendar.MONTH, siguienteCobro.get(GregorianCalendar.MONTH) + 1);
		}else{
			siguienteCobro.set(GregorianCalendar.YEAR, siguienteCobro.get(GregorianCalendar.YEAR)+1);
			siguienteCobro.set(GregorianCalendar.MONTH, 0);
		}
		this.fechaSiguienteCobro = siguienteCobro;
	}
	public GregorianCalendar getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(GregorianCalendar fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public GregorianCalendar getFechaSiguienteCobro() {
		return fechaSiguienteCobro;
	}
	public void setFechaSiguienteCobro(GregorianCalendar fechaSiguienteCobro) {
		this.fechaSiguienteCobro = fechaSiguienteCobro;
	}
	public ClienteMensual getCliente() {
		return cliente;
	}
	public void setCliente(ClienteMensual cliente) {
		this.cliente = cliente;
	}	
}
