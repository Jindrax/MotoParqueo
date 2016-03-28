/**
 * 
 */
package negocio;

import java.io.Serializable;

public class Locker implements Serializable{

	private static final long serialVersionUID = -5709577426968279370L;
	private static int consecutivo = 1;
	private String identificador = "1";
	private int cantidad = 0;
	private int preferido = 4;
	/**
	 * 
	 */
	public Locker() {
		super();
		identificador = String.valueOf(consecutivo);
		consecutivo++;
		this.preferido = 4;
	}
	public Locker(Locker copia){
		this.identificador = copia.getIdentificador();
		this.cantidad = copia.getCantidad();
	}
	
	/**
	 * @return the preferido
	 */
	public int getPreferido() {
		return preferido;
	}
	/**
	 * @param preferido the preferido to set
	 */
	public void setPreferido(int preferido) {
		this.preferido = preferido;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getIdentificador() {
		return identificador;
	}
	@Override
	public String toString() {
		return identificador + "\n";
	}
	public static int getConsecutivo() {
		return consecutivo;
	}
	public static void setConsecutivo(int consecutivo) {
		Locker.consecutivo = consecutivo;
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Locker copia = new Locker(this);
		return copia;
	}
		
}
