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
		this.valorAsignado=0;
		long porHora = Long.parseLong(WinRegistry.leerConfig("Carro", "porHora")),
		porFraccion = Long.parseLong(WinRegistry.leerConfig("Carro", "porFraccion")),
		tiempoFraccion = Long.parseLong(WinRegistry.leerConfig("Carro", "tiempoFraccion"));
		if(this.lockerAsignado!=null){
			this.valorAsignado+=300;
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
		this.valorAsignado = (horas * porHora) + (fracciones * porFraccion);
		this.valorCobrado = this.valorAsignado;
		return this.valorAsignado;
	}
}
