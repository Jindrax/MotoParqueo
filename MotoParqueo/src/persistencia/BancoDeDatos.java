package persistencia;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import negocio.ClienteDiario;
import negocio.ClienteMensual;
import negocio.CupoDiario;

public class BancoDeDatos implements Serializable{
	private static final long serialVersionUID = -7932737173013219132L;
	private GregorianCalendar ultimoBackup;
	private List<ClienteMensual> clientesMensuales;
	private List<ClienteDiario> clientesDiarios;
	private List<CupoDiario> historialDiario;
	/**
	 * 
	 */
	public BancoDeDatos() {
		super();
		clientesMensuales = new ArrayList<ClienteMensual>();
		clientesDiarios = new ArrayList<ClienteDiario>();
		historialDiario = new ArrayList<CupoDiario>();
		// TODO Auto-generated constructor stub
	}
	public GregorianCalendar getUltimoBackup() {
		return ultimoBackup;
	}
	public void setUltimoBackup(GregorianCalendar ultimoBackup) {
		this.ultimoBackup = ultimoBackup;
	}
	public List<ClienteMensual> getClientesMensuales() {
		return clientesMensuales;
	}
	public void setClientesMensuales(List<ClienteMensual> clientesMensuales) {
		this.clientesMensuales = clientesMensuales;
	}
	public List<ClienteDiario> getClientesDiarios() {
		return clientesDiarios;
	}
	public void setClientesDiarios(List<ClienteDiario> clientesDiarios) {
		this.clientesDiarios = clientesDiarios;
	}
	public void adjuntarClienteDiario(ClienteDiario nuevo){
		this.clientesDiarios.add(nuevo);
	}
	public ClienteDiario buscarDiario(String placa) {
		for(ClienteDiario next: clientesDiarios){
			if(next.getPlaca().equals(placa)){
				return next;
			}
		}
		return null;
	}
	public List<CupoDiario> getHistorialDiario() {
		return historialDiario;
	}
	public void setHistorialDiario(List<CupoDiario> historialDiario) {
		this.historialDiario = historialDiario;
	}
	public void registrarCupoDiario(CupoDiario registrado){
		historialDiario.add(registrado);
	}
	public CupoDiario buscarCupoDiario(String placa){
		Collections.reverse(historialDiario);
		for(CupoDiario next: historialDiario){
			if(next.getCliente().getPlaca().equals(placa)){
				Collections.reverse(historialDiario);
				return next;
			}
		}
		Collections.reverse(historialDiario);
		return null;
	}
	public void retirarDelHistorial(CupoDiario cupo){
		historialDiario.remove(cupo);
	}
}

