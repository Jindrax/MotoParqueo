package contabilidad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import presentacion.Utilidades;

public class Contabilidad implements Serializable{
	private static final long serialVersionUID = -1706194414246136712L;
	private double cajaActual=0.0;
	private List<Transferencia> transferencias;
	private long consecutivo = 0;
	
	public Contabilidad() {
		super();
		transferencias = new ArrayList<Transferencia>();
	}
	public double getCajaActual() {
		return cajaActual;
	}
	public void setCajaActual(double cajaActual) {
		this.cajaActual = cajaActual;
	}
	public List<Transferencia> getTransferencias() {
		return transferencias;
	}
	public void setTransferencias(List<Transferencia> transferencias) {
		this.transferencias = transferencias;
	}
	public long getConsecutivo() {
		return consecutivo;
	}
	public void setConsecutivo(long consecutivo) {
		this.consecutivo = consecutivo;
	}
	public void ingreso(GregorianCalendar fecha, double valor, tipoTrans tipo, String placa){
		Transferencia ingreso;
		ingreso = new TransferenciaDiaria(fecha, valor, tipoTrans.diario, consecutivo, placa);
		consecutivo++;
		transferencias.add(ingreso);
		cajaActual += valor;
	}
	public void ingresoEspecial(GregorianCalendar fecha, double valor, tipoTrans tipo, String placa){
		Transferencia ingreso;
		ingreso = new TransferenciaDiaria(fecha, valor, tipoTrans.diario, consecutivo, placa);
		consecutivo++;
		transferencias.add(ingreso);
	}
	public void ingreso(GregorianCalendar fecha, double valor, tipoTrans tipo, String cedula, String nombre){
		Transferencia ingreso;
		ingreso = new TransferenciaMensual(fecha, valor, tipoTrans.mensual, cedula, nombre);
		transferencias.add(ingreso);
	}
	public List<Transferencia> getDia(String hoy){
		List<Transferencia> retorno = new ArrayList<Transferencia>();
		for(Transferencia next: transferencias){
			if(hoy.equals(Utilidades.formaterFecha(next.getFecha()))&&next.getTipo()==tipoTrans.diario){
				retorno.add(next);
			}
		}
		return retorno;
	}
	public List<Transferencia> getMensualDia(String hoy){
		List<Transferencia> retorno = new ArrayList<Transferencia>();
		for(Transferencia next: transferencias){
			if(hoy.equals(Utilidades.formaterFecha(next.getFecha()))&&next.getTipo()==tipoTrans.mensual){
				retorno.add(next);
			}
		}
		return retorno;
	}
}
