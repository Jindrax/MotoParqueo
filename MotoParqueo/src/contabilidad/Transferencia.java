package contabilidad;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Transferencia implements Serializable{
	private static final long serialVersionUID = -5916100864764222298L;
	private GregorianCalendar fecha;
	private double monto = 0.0;
	private tipoTrans tipo = null;
	/**
	 * @param monto
	 * @param tipo
	 */
	public Transferencia(double monto, tipoTrans tipo) {
		super();
		this.fecha = new GregorianCalendar();
		this.monto = monto;
		this.tipo = tipo;
	}
	public GregorianCalendar getFecha() {
		return fecha;
	}
	public void setFecha(GregorianCalendar fecha) {
		this.fecha = fecha;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public tipoTrans getTipo() {
		return tipo;
	}
	public void setTipo(tipoTrans tipo) {
		this.tipo = tipo;
	}	
}
