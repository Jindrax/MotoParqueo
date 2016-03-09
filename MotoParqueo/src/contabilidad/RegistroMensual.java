package contabilidad;

import java.io.Serializable;
import java.util.GregorianCalendar;

import persistencia.WinRegistry;

public class RegistroMensual implements Serializable {

	private static final long serialVersionUID = 4847900716442852021L;
	private GregorianCalendar fecha;
	private int consecutivo;
	private String id;
	private String nombre;
	private String placa;
	private int monto;
	private String periodo;
	public RegistroMensual(String id, String nombre, String placa, int monto, String periodo) {
		super();
		this.fecha = new GregorianCalendar();
		int consecutivoActual = Integer.parseInt(WinRegistry.leerConfig("ContabilidadMes", "mesConsecutivo"));
		consecutivoActual++;
		this.consecutivo = consecutivoActual;
		WinRegistry.guardarConfig("ContabilidadMes", "mesConsecutivo", String.valueOf(consecutivoActual));
		this.id = id;
		this.nombre = nombre;
		this.placa = placa;
		this.monto = monto;
		this.periodo = periodo;
	}
	public int getConsecutivo() {
		return consecutivo;
	}
	public void setConsecutivo(int consecutivo) {
		this.consecutivo = consecutivo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public int getMonto() {
		return monto;
	}
	public void setMonto(int monto) {
		this.monto = monto;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public GregorianCalendar getFecha() {
		return fecha;
	}
	public void setFecha(GregorianCalendar fecha) {
		this.fecha = fecha;
	}
}
