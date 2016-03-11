package contabilidad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import negocio.CupoDiario;
import persistencia.WinRegistry;
import presentacion.Utilidades;

public class Contabilidad implements Serializable{
	private static final long serialVersionUID = -1706194414246136712L;
	private double cajaActual=0.0;
	private List<RegistroDiario> transferencias;
	
	public Contabilidad() {
		super();
		transferencias = new ArrayList<RegistroDiario>();
	}
	public double getCajaActual() {
		return cajaActual;
	}
	public void setCajaActual(double cajaActual) {
		this.cajaActual = cajaActual;
	}
	public List<RegistroDiario> getTransferencias() {
		return transferencias;
	}
	public void setTransferencias(List<RegistroDiario> transferencias) {
		this.transferencias = transferencias;
	}
	public void ingreso(GregorianCalendar fecha, CupoDiario cupo){
		RegistroDiario ingreso;
		int consecutivo = Integer.parseInt(WinRegistry.leerConfig("Contabilidad", "consecutivo"));
		ingreso = new RegistroDiario(fecha, cupo, consecutivo);
		consecutivo++;
		WinRegistry.guardarConfig("Contabilidad", "consecutivo", String.valueOf(consecutivo));
		transferencias.add(ingreso);
		cajaActual += cupo.getValorCobrado();
	}
	public List<RegistroDiario> getDia(String hoy){
		List<RegistroDiario> retorno = new ArrayList<RegistroDiario>();
		for(RegistroDiario next: transferencias){
			if(hoy.equals(Utilidades.formaterFecha(next.getFecha()))){
				retorno.add(next);
			}
		}
		return retorno;
	}
}
