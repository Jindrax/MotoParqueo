package contabilidad;

import java.io.Serializable;
import java.util.GregorianCalendar;

import negocio.CupoDiario;

public class RegistroDiario implements Serializable{

	private static final long serialVersionUID = -7074029308652051398L;
	private GregorianCalendar fecha;
	private CupoDiario cupo;
	private int consecutivoAsignado;
			
	public GregorianCalendar getFecha() {
		return fecha;
	}
	public void setFecha(GregorianCalendar fecha) {
		this.fecha = fecha;
	}
	public CupoDiario getCupo() {
		return cupo;
	}
	public void setCupo(CupoDiario cupo) {
		this.cupo = cupo;
	}
	public RegistroDiario(GregorianCalendar fecha, CupoDiario cupo, int consecutivoAsignado) {
		this.fecha = fecha;
		this.cupo = cupo;
		this.consecutivoAsignado = consecutivoAsignado;
	}
	public int getConsecutivoAsignado() {
		return consecutivoAsignado;
	}
	public void setConsecutivoAsignado(int consecutivoAsignado) {
		this.consecutivoAsignado = consecutivoAsignado;
	}
}
