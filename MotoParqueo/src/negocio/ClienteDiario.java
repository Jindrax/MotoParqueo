package negocio;

import java.io.Serializable;

public class ClienteDiario implements Serializable{
	private static final long serialVersionUID = -2964385696207010197L;
	private String placa = "ABC00A";
	private long entradas = 0;
	private long minutosReg = 0;
	private double cobroTotal = 0.0;
	public ClienteDiario(String placa) {
		// TODO Auto-generated constructor stub
		this.placa=placa;
	}
	public long getEntradas() {
		return entradas;
	}
	public void setEntradas(long entradas) {
		this.entradas = entradas;
	}
	public long getMinutosReg() {
		return minutosReg;
	}
	public void setMinutosReg(long minutosReg) {
		this.minutosReg = minutosReg;
	}
	public double getCobroTotal() {
		return cobroTotal;
	}
	public void setCobroTotal(int cobroTotal) {
		this.cobroTotal = cobroTotal;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(placa);
		builder.append("|");
		builder.append(entradas);
		builder.append("|");
		builder.append(minutosReg);
		builder.append("|");
		builder.append(cobroTotal);
		return builder.toString();
	}
	
}
