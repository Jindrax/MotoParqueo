package negocio;

import java.util.GregorianCalendar;

import persistencia.WinRegistry;

public class Carro extends CupoDiario {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8765575958182821065L;

	public Carro(Locker lockerAsignado, String placa) {
		super(lockerAsignado, placa);
		tipo="C";
	}

	public Carro(Locker lockerAsignado, ClienteDiario cliente) {
		super(lockerAsignado, cliente);
		tipo="C";
	}

	public Carro(ClienteDiario cliente) {
		super(cliente);
		tipo="C";
	}

	public Carro(ClienteDiario cliente, GregorianCalendar entrada, GregorianCalendar salida) {
		super(cliente, entrada, salida);
		tipo="C";
	}

	public Carro(String placa) {
		super(placa);
		tipo="C";
	}

	public Carro(String placa, GregorianCalendar entrada, GregorianCalendar salida) {
		super(placa, entrada, salida);
		tipo="C";
	}

	public Carro(CupoDiario copia) {
		super(copia);
		tipo="C";
	}

	@Override
	public double calcularCobro() {
		int tiempoFraccion = Integer.parseInt(WinRegistry.leerConfig("Carro", "tiempoFraccion"));
		long unaHora = Long.parseLong(WinRegistry.leerConfig("Carro", "porHora")),
		porFraccion = Long.parseLong(WinRegistry.leerConfig("Carro", "porFraccion"));

		this.tiempoTranscurrido = Math.ceil(this.tiempoTranscurrido/60.0);
		if(this.tiempoTranscurrido>tiempoFraccion){
			this.valorCobrado=Math.ceil(this.tiempoTranscurrido/60.0)*unaHora;
			this.valorAsignado=valorCobrado;
			return this.valorCobrado;
		}
		else{
			this.valorCobrado=porFraccion;
			this.valorAsignado=valorCobrado;
			return this.valorCobrado;
		}
	}
}
