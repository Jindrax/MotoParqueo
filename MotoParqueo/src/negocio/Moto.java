/**
 * 
 */
package negocio;

import java.util.GregorianCalendar;
import persistencia.WinRegistry;

/**
 * @author Todesser
 *
 */
public class Moto extends CupoDiario {

	/**
	 * 
	 */
	private static final long serialVersionUID = 485953634453408438L;

	/**
	 * @param lockerAsignado
	 * @param placa
	 */
	public Moto(Locker lockerAsignado, String placa) {
		super(lockerAsignado, placa);
		tipo="M";
	}

	/**
	 * @param lockerAsignado
	 * @param cliente
	 */
	public Moto(Locker lockerAsignado, ClienteDiario cliente) {
		super(lockerAsignado, cliente);
		tipo="M";
	}

	/**
	 * @param cliente
	 */
	public Moto(ClienteDiario cliente) {
		super(cliente);
		tipo="M";
	}

	/**
	 * @param cliente
	 * @param entrada
	 * @param salida
	 */
	public Moto(ClienteDiario cliente, GregorianCalendar entrada, GregorianCalendar salida) {
		super(cliente, entrada, salida);
		tipo="M";
	}

	/**
	 * @param placa
	 */
	public Moto(String placa) {
		super(placa);
		tipo="M";
	}

	/**
	 * @param placa
	 * @param entrada
	 * @param salida
	 */
	public Moto(String placa, GregorianCalendar entrada, GregorianCalendar salida) {
		super(placa, entrada, salida);
		tipo="M";
	}

	/**
	 * @param copia
	 */
	public Moto(CupoDiario copia) {
		super(copia);
		tipo="M";
	}
	public double calcularCobro(){
		this.valorAsignado=0;
		long porHora = Long.parseLong(WinRegistry.leerConfig("Moto", "porHora")),
		porFraccion = Long.parseLong(WinRegistry.leerConfig("Moto", "porFraccion")),
		tiempoFraccion = Long.parseLong(WinRegistry.leerConfig("Moto", "tiempoFraccion")),
		precioCasco = Long.parseLong(WinRegistry.leerConfig("Lockers", "precioCasco"));
		if(this.lockerAsignado!=null){
			long valorCascos = precioCasco*this.lockerAsignado.getCantidad();
			this.valorAsignado+=valorCascos;
		}
		this.tiempoTranscurrido = Math.ceil(this.tiempoTranscurrido/60.0);
		int horas = (int) (this.tiempoTranscurrido/60);
		int minutos = (int) (this.tiempoTranscurrido%60);
		int fracciones = 0;
		if(minutos%tiempoFraccion<tiempoFraccion && minutos/tiempoFraccion>0){
			horas++;
		}else{
			fracciones++;
		}
		this.valorAsignado += (horas * porHora) + (fracciones * porFraccion);
		this.valorCobrado = this.valorAsignado;
		return this.valorAsignado;
	}
}
