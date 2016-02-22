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
	}

	/**
	 * @param lockerAsignado
	 * @param cliente
	 */
	public Moto(Locker lockerAsignado, ClienteDiario cliente) {
		super(lockerAsignado, cliente);
	}

	/**
	 * @param cliente
	 */
	public Moto(ClienteDiario cliente) {
		super(cliente);
	}

	/**
	 * @param cliente
	 * @param entrada
	 * @param salida
	 */
	public Moto(ClienteDiario cliente, GregorianCalendar entrada, GregorianCalendar salida) {
		super(cliente, entrada, salida);
	}

	/**
	 * @param placa
	 */
	public Moto(String placa) {
		super(placa);
	}

	/**
	 * @param placa
	 * @param entrada
	 * @param salida
	 */
	public Moto(String placa, GregorianCalendar entrada, GregorianCalendar salida) {
		super(placa, entrada, salida);
	}

	/**
	 * @param copia
	 */
	public Moto(CupoDiario copia) {
		super(copia);
	}
	public double calcularCobro(){
		long mediaHora = Long.parseLong(WinRegistry.leerConfig("Moto", "mediaHora")),
		unaHora = Long.parseLong(WinRegistry.leerConfig("Moto", "unaHora")),
		porHora = Long.parseLong(WinRegistry.leerConfig("Moto", "porHora"));
		
		this.tiempoTranscurrido = Math.ceil(this.tiempoTranscurrido/60.0);
		if(this.tiempoTranscurrido>60){
			this.valorCobrado=Math.ceil(this.tiempoTranscurrido/60.0)*porHora;
			this.valorAsignado=valorCobrado;
			return this.valorCobrado;
		}
		else{
			if(this.tiempoTranscurrido<=30){
				this.valorCobrado=mediaHora;
				this.valorAsignado=valorCobrado;
				return this.valorCobrado;
			}
			else{
				this.valorCobrado=unaHora;
				this.valorAsignado=valorCobrado;
				return this.valorCobrado;
			}
		}		
	}
}
