package contabilidad;

public class TransferenciaDiaria extends Transferencia {
	private static final long serialVersionUID = -3603014590450740466L;
	private long consecutivo;
	private String placa;
	public TransferenciaDiaria(double monto, tipoTrans tipo, long consecutivo, String placa) {
		super(monto, tipo);
		this.consecutivo = consecutivo;
		this.placa = placa;
		// TODO Auto-generated constructor stub
	}
	public long getConsecutivo() {
		return consecutivo;
	}
	public void setConsecutivo(long consecutivo) {
		this.consecutivo = consecutivo;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}	
}
