package negocio;

import java.io.Serializable;

public class ClienteMensual implements Serializable{
	private static final long serialVersionUID = -8706000971786535057L;
	private String cedula = "";
	private String nombre = "";
	private String placa = "AAA00A";
	private String celular = "";
	private int meses = 0;	
	public ClienteMensual(String placa, String cedula, String nombre, String celular) {
		this.placa = placa;
		this.cedula = cedula;
		this.nombre = nombre;
		this.celular = celular;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}	
	public int getMeses() {
		return meses;
	}
	public void setMeses(int meses) {
		this.meses = meses;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if(cedula.compareTo("")==0){
			builder.append("-");
		}else{
			builder.append(cedula);
		}
		builder.append("|");
		if(nombre.compareTo("")==0){
			builder.append("-");
		}else{
			builder.append(nombre);
		}
		builder.append("|");
		if(placa.compareTo("")==0){
			builder.append("-");
		}else{
			builder.append(placa);
		}
		builder.append("|");
		if(celular.compareTo("")==0){
			builder.append("-");
		}else{
			builder.append(celular);
		}
		return builder.toString();
	}
		
}
