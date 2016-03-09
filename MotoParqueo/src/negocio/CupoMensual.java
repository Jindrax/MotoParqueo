package negocio;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class CupoMensual implements Serializable{
	private static final long serialVersionUID = -6318213858283242672L;
	private GregorianCalendar fechaIngreso;
	private GregorianCalendar fechaSiguienteCobro;
	private ClienteMensual cliente;
	private String tipo;
	private int mensualidad;
	/**
	 * 
	 */
	public CupoMensual(String placa, String cedula, String nombre, String celular, GregorianCalendar fechaIngreso, String tipo, int mensualidad) {
		super();
		this.cliente = new ClienteMensual(placa, cedula, nombre, celular);
		this.fechaIngreso = fechaIngreso;
		GregorianCalendar siguienteCobro = (GregorianCalendar) fechaIngreso.clone();
		this.fechaSiguienteCobro = siguienteCobro;
		this.tipo = tipo;
		this.mensualidad = mensualidad;
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
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getMensualidad() {
		return mensualidad;
	}
	public void setMensualidad(int mensualidad) {
		this.mensualidad = mensualidad;
	}	
}
