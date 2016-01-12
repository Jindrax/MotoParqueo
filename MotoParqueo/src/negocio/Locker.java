/**
 * 
 */
package negocio;

import java.io.Serializable;

public class Locker implements Serializable{

	private static final long serialVersionUID = -5709577426968279370L;
	private static int consecutivo = 0;
	private String identificador = "A";
	private int cantidad = 0;
	private int preferido = 4;
	/**
	 * 
	 */
	public Locker() {
		super();
		if(consecutivo/26<=0){
			char aux = (char) (65 + consecutivo);
			identificador = String.valueOf(aux);
		}
		else{
			int intaux = consecutivo/26;
			char aux[] = new char[2];
			aux[0] = (char) (65 + intaux-1);
			aux[1] = (char) (65 + consecutivo%26);
			identificador = String.valueOf(aux);
		}
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
