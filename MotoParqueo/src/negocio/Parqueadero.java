package negocio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JOptionPane;

import contabilidad.Contabilidad;
import persistencia.BancoDeDatos;

public class Parqueadero implements Serializable{
	private static final long serialVersionUID = -4375449400774660837L;
	private int idClienteMensual = 0;
	private int idCupoDiario = 0;
	private int idCupoMensual = 0;
	private BancoDeDatos dataBank = null;
	private List<CupoDiario> cuposDiarios = null;
	private List<CupoMensual> cuposMensuales = null;
	private List<Locker> lockers = null;
	private Contabilidad contabilidad;
	private static String horaCierre = "7:30 pm";
	private List<String> baneados = null;
	private CupoDiario cupoDiaAux = null;
	private int mensualidad = 20000;
	private long [] valor = {600,900,700};
	/**
	 * 
	 */
	public Parqueadero(int numLock, List<String> prefer) {
		super();
		dataBank = new BancoDeDatos();
		contabilidad = new Contabilidad();
		cuposDiarios = new ArrayList<CupoDiario>();
		cuposMensuales = new ArrayList<CupoMensual>();
		lockers = new ArrayList<Locker>();
		for(int i=0; i<numLock; i++){
			Locker nuevo = new Locker();
			for(String next: prefer){
				if(nuevo.getIdentificador().equals(next)){
					nuevo.setPreferido(1);
				}
			}
			lockers.add(nuevo);
		}
		baneados = new ArrayList<String>();
		cupoDiaAux = null;
		mensualidad = 20000;
	}
	public boolean relistarLockers(int numLock, List<String> prefer){
		lockers = new ArrayList<Locker>();
		Locker.setConsecutivo(0);
		for(int i=0; i<numLock; i++){
			Locker nuevo = new Locker();
			for(String next: prefer){
				if(nuevo.getIdentificador().equals(next)){
					nuevo.setPreferido(1);
				}
			}
			lockers.add(nuevo);
		}
		return true;
	}	
	public long[] getValor() {
		return valor;
	}
	public void setValor(long[] valor) {
		this.valor = valor;
	}
	public int getMensualidad() {
		return mensualidad;
	}
	public void setMensualidad(int mensualidad) {
		this.mensualidad = mensualidad;
	}
	public CupoDiario getCupoDiaAux() {
		return cupoDiaAux;
	}
	public void setCupoDiaAux(CupoDiario cupoDiaAux) {
		this.cupoDiaAux = cupoDiaAux;
	}
	public List<String> getBaneados() {
		return baneados;
	}

	public void setBaneados(List<String> baneados) {
		this.baneados = baneados;
	}

	public static String getHoraCierre() {
		return horaCierre;
	}

	public void setHoraCierre(String horaCierre) {
		Parqueadero.horaCierre = horaCierre;
	}

	public int getIdClienteMensual() {
		return idClienteMensual;
	}
	public void setIdClienteMensual(int idClienteMensual) {
		this.idClienteMensual = idClienteMensual;
	}
	public int getIdCupoMensual() {
		return idCupoMensual;
	}
	public void setIdCupoMensual(int idCupoMensual) {
		this.idCupoMensual = idCupoMensual;
	}
	public BancoDeDatos getDataBank() {
		return dataBank;
	}
	public void setDataBank(BancoDeDatos dataBank) {
		this.dataBank = dataBank;
	}
	public List<CupoDiario> getCuposDiarios() {
		return cuposDiarios;
	}
	public void setCuposDiarios(List<CupoDiario> cuposDiarios) {
		this.cuposDiarios = cuposDiarios;
	}
	public List<CupoMensual> getCuposMensuales() {
		return cuposMensuales;
	}
	public void setCuposMensuales(List<CupoMensual> cuposMensuales) {
		this.cuposMensuales = cuposMensuales;
	}
	public List<Locker> getLockers() {
		return lockers;
	}
	public void setLockers(List<Locker> lockers) {
		this.lockers = lockers;
	}
	public Contabilidad getContabilidad() {
		return contabilidad;
	}
	public void setContabilidad(Contabilidad contabilidad) {
		this.contabilidad = contabilidad;
	}
	
	public int getIdCupoDiario() {
		return idCupoDiario;
	}
	public void setIdCupoDiario(int idCupoDiario) {
		this.idCupoDiario = idCupoDiario;
	}
	public CupoDiario ingresarDiario(String placa, int cascos){
		ClienteDiario cliente = dataBank.buscarDiario(placa);
		CupoDiario nuevoCupo = null;
		if(cliente!=null){
			if (cascos>0) {
				for (Locker next : lockers) {
					if (next.getCantidad() == 0) {
						if (cascos<=next.getPreferido()) {
							next.setCantidad(cascos);
							nuevoCupo = new CupoDiario(next, cliente);
							cliente.setEntradas(cliente.getEntradas() + 1);
							cuposDiarios.add(nuevoCupo);
							return nuevoCupo;
						}
					}
				}
				JOptionPane.showMessageDialog(null, "No hay mas lockers disponibles.", "Sin lockers disponibles.", JOptionPane.WARNING_MESSAGE);
				nuevoCupo = new CupoDiario(cliente);
				cliente.setEntradas(cliente.getEntradas()+1);
				cuposDiarios.add(nuevoCupo);
				return nuevoCupo;
			}else{
				nuevoCupo = new CupoDiario(cliente);
				cliente.setEntradas(cliente.getEntradas()+1);
				cuposDiarios.add(nuevoCupo);
				return nuevoCupo;
			}
		}
		else{
			cliente = new ClienteDiario(placa);
			if (cascos>0) {
				for (Locker next : lockers) {
					if (next.getCantidad() == 0) {
						if (cascos<=next.getPreferido()) {
							next.setCantidad(cascos);
							cliente = new ClienteDiario(placa);
							nuevoCupo = new CupoDiario(next, cliente);
							cliente.setEntradas(cliente.getEntradas() + 1);
							dataBank.adjuntarClienteDiario(cliente);
							cuposDiarios.add(nuevoCupo);
							return nuevoCupo;
						}
					}
				}
				JOptionPane.showMessageDialog(null, "No hay mas lockers disponibles.", "Sin lockers disponibles.", JOptionPane.WARNING_MESSAGE);
				nuevoCupo = new CupoDiario(cliente);
				cliente.setEntradas(cliente.getEntradas()+1);
				cuposDiarios.add(nuevoCupo);
				return nuevoCupo;
			}else{
				nuevoCupo = new CupoDiario(cliente);
				cliente.setEntradas(cliente.getEntradas()+1);
				dataBank.adjuntarClienteDiario(cliente);
				cuposDiarios.add(nuevoCupo);
				return nuevoCupo;
			}
		}
	}
	
	public CupoDiario retirarDiario(int serial){
		CupoDiario retirado = null;
		for(CupoDiario next: cuposDiarios){
			if(next.getSerial()==serial){				
				next.calcularTiempoTrans();
				next.calcularCobro(this.valor);
				dataBank.registrarCupoDiario(next);
				retirado = next;
			}
		}
		if (retirado!=null) {
			Locker copia = null;
			if (retirado.getLockerAsignado() != null) {
				copia = new Locker(retirado.getLockerAsignado());
				retirado.getLockerAsignado().setCantidad(0);
			}
			retirado.setLockerAsignado(copia);
			cuposDiarios.remove(retirado);
		}
		return retirado;
	}
	
	public void guardar() {
		FileOutputStream archivo = null;
		ObjectOutputStream oOStream = null;
		try {
			File file = new File("archivo.dat");
			archivo = new FileOutputStream(file);
			oOStream = new ObjectOutputStream(archivo);
			idCupoDiario = CupoDiario.getId();
			oOStream.writeObject(this);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				oOStream.close();
				archivo.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	public Parqueadero cargar(){
		File ruta = null;
		FileInputStream archivo = null;
		ObjectInputStream oIStream = null;
		Parqueadero cargado = null;
		try {
			ruta = new File("archivo.dat");			
			if (ruta.exists()) {
				try {
					archivo = new FileInputStream(ruta);
					oIStream = new ObjectInputStream(archivo);
					cargado = (Parqueadero) oIStream.readObject();
					CupoDiario.setId(cargado.getIdCupoDiario());
				} catch (IOException e) {
					guardar();
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} 
			}else{
				ruta.createNewFile();
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cargado;
	}
	public void banear(String ban){
		baneados.add(ban);
	}
	public void unban(String ban){
		baneados.remove(ban);
	}
	public CupoMensual ingresarMensual(String nombre, String cedula, String celular, String placa, GregorianCalendar fechaIngreso){
		CupoMensual retorno = new CupoMensual(placa, cedula, nombre, celular, fechaIngreso);
		cuposMensuales.add(retorno);
		return retorno;
	}
	public boolean retirarMensual(String placa) {
		for(CupoMensual next: cuposMensuales){
			if(placa.equals(next.getCliente().getPlaca())){
				cuposMensuales.remove(next);
				return true;
			}
		}
		return false;
	}
	public CupoDiario buscarCupoDiario(String placa){
		for(CupoDiario next: cuposDiarios){
			if(next.getCliente().getPlaca().equals(placa)){
				return next;
			}
		}
		return null;
	}
	public boolean corregirDiario(String placa, int cascos){
		cupoDiaAux.getCliente().setPlaca(placa);
		cupoDiaAux.getLockerAsignado().setCantidad(cascos);
		return true;
	}
	public CupoMensual buscarCupoMensual(String placa){
		for(CupoMensual next: cuposMensuales){
			if(next.getCliente().getPlaca().equals(placa)){
				return next;
			}
		}
		return null;
	}
	public void corregirMensual(String nombre, String cedula, String celular, String placa, GregorianCalendar ingreso){
		CupoMensual cupo = buscarCupoMensual(placa);
		cupo.getCliente().setNombre(nombre);
		cupo.getCliente().setCedula(cedula);
		cupo.getCliente().setCelular(celular);
		cupo.getCliente().setPlaca(placa);
		cupo.setFechaIngreso(ingreso); 
	}
	private GregorianCalendar calcularSigCobro(GregorianCalendar ingreso){
		GregorianCalendar siguienteCobro = (GregorianCalendar) ingreso.clone();
		if (siguienteCobro.get(GregorianCalendar.MONTH)<=10) {
			siguienteCobro.set(GregorianCalendar.MONTH, siguienteCobro.get(GregorianCalendar.MONTH) + 1);
		}else{
			siguienteCobro.set(GregorianCalendar.YEAR, siguienteCobro.get(GregorianCalendar.YEAR)+1);
			siguienteCobro.set(GregorianCalendar.MONTH, 0);
		}
		return siguienteCobro;
	}
	public CupoMensual pagoMensual(String placa){
		for(CupoMensual next: cuposMensuales){
			if(next.getCliente().getPlaca().equals(placa)){
				next.setFechaSiguienteCobro(calcularSigCobro(next.getFechaSiguienteCobro()));
				return next;
			}
		}
		return null;
	}
	public boolean anularCupoDiario(CupoDiario cupo){
		if (cuposDiarios.remove(cupo)) {
			if (cupo.getLockerAsignado()!=null) {
				try {
					Locker lockerHistorial = (Locker) cupo.getLockerAsignado().clone();
					cupo.getLockerAsignado().setCantidad(0);
					cupo.setLockerAsignado(lockerHistorial);
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
			cupo.setHoraSalida(new GregorianCalendar());
			cupo.setTiempoTranscurrido(0);
			cupo.setValorCobrado(0);
			dataBank.getHistorialDiario().add(cupo);
			return true;
		}else{
			return false;
		}		
	}
	public void setValor(int pos, long val){
		this.valor[pos] = val;
	}
	public long getValor(int pos){
		return this.valor[pos];
	}
	public CupoDiario resucitar(String placa){
		CupoDiario cupo = dataBank.buscarCupoDiario(placa);
		if (cupo!=null) {
			dataBank.retirarDelHistorial(cupo);
			cupo.setHoraSalida(null);
			cupo.setTiempoTranscurrido(0);
			cupo.setValorAsignado(0);
			cupo.setValorCobrado(0);
			if (cupo.getLockerAsignado() != null) {
				Locker locker = cupo.getLockerAsignado();
				cupo.setLockerAsignado(null);
				for (Locker next : lockers) {
					if (locker.getIdentificador().equals(next.getIdentificador())) {
						if (next.getCantidad()==0) {
							next.setCantidad(locker.getCantidad());
							cupo.setLockerAsignado(next);
						}
					}
				}
				if (cupo.getLockerAsignado() == null) {
					int cascos = locker.getCantidad();
					for (Locker next : lockers) {
						if (next.getCantidad() == 0) {
							if (cascos <= next.getPreferido()) {
								if (cupo.getLockerAsignado()==null) {
									next.setCantidad(cascos);
									cupo.setLockerAsignado(next);
								}
							}
						}
					}
				}
			}
			cuposDiarios.add(cupo);
			return cupo;
		}
		return null;
	}
}
