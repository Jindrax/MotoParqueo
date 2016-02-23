/**
 * 
 */
package negocio;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public abstract class CupoDiario implements Serializable{
	private static final long serialVersionUID = -7597938477870651575L;
	protected static int id = 0;
	protected int serial = 0;
	protected GregorianCalendar horaIngreso;
	protected GregorianCalendar horaSalida;
	protected Locker lockerAsignado;
	protected double valorCobrado = 0;
	protected ClienteDiario cliente;
	protected double tiempoTranscurrido = 0;
	protected double valorAsignado = 0;
	protected String tipo;
	/**
	 * @param lockerAsignado
	 */
	public CupoDiario(Locker lockerAsignado, String placa) {
		super();
		this.serial=id+1;
		id++;
		this.lockerAsignado = lockerAsignado;
		this.cliente = new ClienteDiario(placa);
		this.horaIngreso = new GregorianCalendar();
	}
	public CupoDiario(Locker lockerAsignado, ClienteDiario cliente){
		super();
		this.serial=id+1;
		id++;
		this.lockerAsignado=lockerAsignado;
		this.cliente=cliente;
		this.horaIngreso = new GregorianCalendar();
	}
	public CupoDiario(ClienteDiario cliente){
		super();
		this.serial=id+1;
		id++;
		this.lockerAsignado=null;
		this.cliente=cliente;
		this.horaIngreso = new GregorianCalendar();
	}
	public CupoDiario(ClienteDiario cliente, GregorianCalendar entrada, GregorianCalendar salida){
		super();
		this.serial=id+1;
		id++;
		this.lockerAsignado=null;
		this.cliente=cliente;
		this.horaIngreso = entrada;
		this.horaSalida = salida;
	}
	public CupoDiario(String placa){
		super();
		this.serial=id+1;
		id++;
		this.cliente = new ClienteDiario(placa);
		this.horaIngreso = new GregorianCalendar();
	}
	public CupoDiario(String placa, GregorianCalendar entrada, GregorianCalendar salida){
		super();
		this.serial=id+1;
		id++;
		this.cliente = new ClienteDiario(placa);
		this.horaIngreso = entrada;
		this.horaSalida = salida;
	}	
	public CupoDiario(CupoDiario copia){
		super();
		this.serial = copia.getSerial();
		this.horaIngreso = copia.getHoraIngreso();
		this.horaSalida = copia.getHoraSalida();
		this.lockerAsignado = copia.getLockerAsignado();
		this.valorCobrado = copia.getValorCobrado();
		this.cliente = copia.getCliente();
		this.tiempoTranscurrido = copia.getTiempoTranscurrido();
	}
	public double getValorAsignado() {
		return valorAsignado;
	}
	public void setValorAsignado(double valorAsignado) {
		this.valorAsignado = valorAsignado;
	}
	public static int getId() {
		return id;
	}
	public static void setId(int id) {
		CupoDiario.id = id;
	}
	public GregorianCalendar getHoraIngreso() {
		return horaIngreso;
	}
	public void setHoraIngreso(GregorianCalendar horaIngreso) {
		this.horaIngreso = horaIngreso;
	}
	public GregorianCalendar getHoraSalida() {
		return horaSalida;
	}
	public void setHoraSalida(GregorianCalendar horaSalida) {
		this.horaSalida = horaSalida;
	}
	public Locker getLockerAsignado() {
		return lockerAsignado;
	}
	public void setLockerAsignado(Locker lockerAsignado) {
		this.lockerAsignado = lockerAsignado;
	}
	public double getValorCobrado() {
		return valorCobrado;
	}
	public void setValorCobrado(double valorCobrado) {
		this.valorCobrado = valorCobrado;
	}
	public ClienteDiario getCliente() {
		return cliente;
	}
	public void setCliente(ClienteDiario cliente) {
		this.cliente = cliente;
	}
	public int getSerial() {
		return serial;
	}
	public void setSerial(int serial) {
		this.serial = serial;
	}	
	public double getTiempoTranscurrido() {
		return tiempoTranscurrido;
	}
	public void setTiempoTranscurrido(double tiempoTranscurrido) {
		this.tiempoTranscurrido = tiempoTranscurrido;
	}
	public double calcularTiempoTrans(){
		horaSalida = new GregorianCalendar();
		long tiempoTrans = horaSalida.getTimeInMillis() - horaIngreso.getTimeInMillis();
		this.tiempoTranscurrido = TimeUnit.MILLISECONDS.toSeconds(tiempoTrans);
		return this.tiempoTranscurrido;
	}
	public double calcularTiempoTransEspecial(){
		long tiempoTrans = horaSalida.getTimeInMillis() - horaIngreso.getTimeInMillis();
		this.tiempoTranscurrido = TimeUnit.MILLISECONDS.toSeconds(tiempoTrans);
		return this.tiempoTranscurrido;
	}
	public abstract double calcularCobro();
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
