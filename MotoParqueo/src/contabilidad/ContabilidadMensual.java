package contabilidad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import negocio.CupoMensual;
import presentacion.Utilidades;

public class ContabilidadMensual implements Serializable {

	private static final long serialVersionUID = -1436929838409967238L;
	private List<RegistroMensual> registrosMensuales;

	public ContabilidadMensual() {
		registrosMensuales = new ArrayList<RegistroMensual>();
	}

	public List<RegistroMensual> getRegistrosMensuales() {
		return registrosMensuales;
	}

	public void setRegistrosMensuales(List<RegistroMensual> registrosMensuales) {
		this.registrosMensuales = registrosMensuales;
	}
	
	public void ingresarCobro(CupoMensual cupo){
		String periodo = String.format("%s - %s", Utilidades.formaterFecha(Utilidades.mesAnterior(cupo.getFechaSiguienteCobro())),
				Utilidades.formaterFecha(cupo.getFechaSiguienteCobro()));
		RegistroMensual nuevo = new RegistroMensual(cupo.getCliente().getCedula(), cupo.getCliente().getNombre(),
				cupo.getCliente().getPlaca(), cupo.getMensualidad(), periodo);
		registrosMensuales.add(nuevo);
	}
	public List<RegistroMensual> getDiaMensuales(String hoy){
		List<RegistroMensual> retorno =  new ArrayList<RegistroMensual>();
		for(RegistroMensual next: registrosMensuales){
			if(hoy.compareTo(Utilidades.formaterFecha(next.getFecha()))==0){
				retorno.add(next);
			}
		}
		return retorno;
	}
}
