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
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JOptionPane;

import contabilidad.Contabilidad;
import contabilidad.ContabilidadMensual;
import persistencia.BancoDeDatos;
import presentacion.Utilidades;

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
	private ContabilidadMensual contabilidadMensual = null;
	private static String horaCierre = "7:30 pm";
	private List<String> baneados = null;
	private CupoDiario cupoDiaAux = null;
	private int mensualidad = 20000;
	private String lastBackup = "";
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
		Locker.setConsecutivo(1);
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
	public CupoDiario ingresarDiario(String placa, int cascos, char tipo){
		ClienteDiario cliente = dataBank.buscarDiario(placa);
		CupoDiario nuevoCupo = null;
		if(cliente!=null){
			switch (tipo) {
			case 'M':
				if (cascos>0) {
					for (Locker next : lockers) {
						if (next.getCantidad() == 0) {
							if (cascos<=next.getPreferido()) {
								next.setCantidad(cascos);
								nuevoCupo = new Moto(next, cliente);
								cliente.setEntradas(cliente.getEntradas() + 1);
								cuposDiarios.add(nuevoCupo);
								return nuevoCupo;
							}
						}
					}
					JOptionPane.showMessageDialog(null, "No hay mas lockers disponibles.", "Sin lockers disponibles.", JOptionPane.WARNING_MESSAGE);
					nuevoCupo = new Moto(cliente);
					cliente.setEntradas(cliente.getEntradas()+1);
					cuposDiarios.add(nuevoCupo);
					return nuevoCupo;
				}else{
					nuevoCupo = new Moto(cliente);
					cliente.setEntradas(cliente.getEntradas()+1);
					cuposDiarios.add(nuevoCupo);
					return nuevoCupo;
				}
			case 'C':
				nuevoCupo = new Carro(cliente);
				cliente.setEntradas(cliente.getEntradas()+1);
				cuposDiarios.add(nuevoCupo);
				return nuevoCupo;
			default:
				return null;
			}
		}
		else{
			cliente = new ClienteDiario(placa);
			switch (tipo) {
			case 'M':
				if (cascos>0) {
					for (Locker next : lockers) {
						if (next.getCantidad() == 0) {
							if (cascos<=next.getPreferido()) {
								next.setCantidad(cascos);
								cliente = new ClienteDiario(placa);
								nuevoCupo = new Moto(next, cliente);
								cliente.setEntradas(cliente.getEntradas() + 1);
								dataBank.adjuntarClienteDiario(cliente);
								cuposDiarios.add(nuevoCupo);
								return nuevoCupo;
							}
						}
					}
					JOptionPane.showMessageDialog(null, "No hay mas lockers disponibles.", "Sin lockers disponibles.", JOptionPane.WARNING_MESSAGE);
					nuevoCupo = new Moto(cliente);
					cliente.setEntradas(cliente.getEntradas()+1);
					cuposDiarios.add(nuevoCupo);
					return nuevoCupo;
				}else{
					nuevoCupo = new Moto(cliente);
					cliente.setEntradas(cliente.getEntradas()+1);
					dataBank.adjuntarClienteDiario(cliente);
					cuposDiarios.add(nuevoCupo);
					return nuevoCupo;
				}
			case 'C':
				nuevoCupo = new Carro(cliente);
				cliente.setEntradas(cliente.getEntradas()+1);
				dataBank.adjuntarClienteDiario(cliente);
				cuposDiarios.add(nuevoCupo);
				return nuevoCupo;
			default:
				return null;
			}
		}
	}
	
	public CupoDiario retirarDiario(int serial){
		CupoDiario retirado = null;
		for(CupoDiario next: cuposDiarios){
			if(next.getSerial()==serial){				
				next.calcularTiempoTrans();
				next.calcularCobro();
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
	
	public void resetBackup(){
		File folder = new File("backups\\");
		File[] carpetas = folder.listFiles();
		Arrays.sort(carpetas);
		if (carpetas.length>5) {
			for (int i = 0; i < carpetas.length - 1; i++) {
				carpetas[i].delete();
			} 
		}
	}
	
	public void backup(){
			GregorianCalendar hoy = new GregorianCalendar();
			guardar("backups\\backup-"+Utilidades.formaterFechaFile(hoy)+"-"+Utilidades.formaterHoraFile(hoy)+".dat");
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
			resetBackup();
			backup();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				oOStream.close();
				archivo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void guardar(String nombre) {
		FileOutputStream archivo = null;
		ObjectOutputStream oOStream = null;
		try {
			File file = new File(nombre);
			archivo = new FileOutputStream(file);
			oOStream = new ObjectOutputStream(archivo);
			idCupoDiario = CupoDiario.getId();
			oOStream.writeObject(this);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				oOStream.close();
				archivo.close();
			} catch (IOException e) {
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
	public CupoMensual ingresarMensual(String nombre, String cedula, String celular, String placa, GregorianCalendar fechaIngreso, String tipo, int mensualidad){
		CupoMensual retorno = new CupoMensual(placa, cedula, nombre, celular, fechaIngreso, tipo, mensualidad);
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
	public void corregirMensual(String nombre, String cedula, String celular, String placa, GregorianCalendar ingreso, String tipo, int mensualidad){
		CupoMensual cupo = buscarCupoMensual(placa);
		cupo.getCliente().setNombre(nombre);
		cupo.getCliente().setCedula(cedula);
		cupo.getCliente().setCelular(celular);
		cupo.getCliente().setPlaca(placa);
		cupo.setFechaIngreso(ingreso);
		cupo.setTipo(tipo);
		cupo.setMensualidad(mensualidad);
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
	public String getLastBackup() {
		return lastBackup;
	}
	public void setLastBackup(String lastBackup) {
		this.lastBackup = lastBackup;
	}
	public CupoDiario ingresoDiarioEspecial(GregorianCalendar fecha, String placa, GregorianCalendar entrada, GregorianCalendar salida){
		ClienteDiario cliente = dataBank.buscarDiario(placa);
		CupoDiario nuevoCupo = null;
		if(cliente!=null){
			nuevoCupo = new Moto(cliente,entrada,salida);
			cliente.setEntradas(cliente.getEntradas()+1);
			nuevoCupo.calcularTiempoTrans();
			nuevoCupo.calcularCobro();
			contabilidad.ingreso(fecha, nuevoCupo);
			nuevoCupo.getCliente().setMinutosReg((long) (nuevoCupo.getCliente().getMinutosReg() + nuevoCupo.getTiempoTranscurrido()));
			nuevoCupo.getCliente().setCobroTotal((int) (nuevoCupo.getCliente().getCobroTotal() + nuevoCupo.getValorCobrado()));
			return nuevoCupo;
		}
		else{
			cliente = new ClienteDiario(placa);
			nuevoCupo = new Moto(cliente,entrada,salida);
			cliente.setEntradas(cliente.getEntradas()+1);
			dataBank.adjuntarClienteDiario(cliente);
			nuevoCupo.calcularTiempoTransEspecial();
			nuevoCupo.calcularCobro();
			contabilidad.ingreso(fecha, nuevoCupo);
			nuevoCupo.getCliente().setMinutosReg((long) (nuevoCupo.getCliente().getMinutosReg() + nuevoCupo.getTiempoTranscurrido()));
			nuevoCupo.getCliente().setCobroTotal((int) (nuevoCupo.getCliente().getCobroTotal() + nuevoCupo.getValorCobrado()));
			return nuevoCupo;
		}
	}
	public CupoDiario ingresoDiarioEspecial(GregorianCalendar fecha, String placa, GregorianCalendar entrada, GregorianCalendar salida, double valor){
		ClienteDiario cliente = dataBank.buscarDiario(placa);
		CupoDiario nuevoCupo = null;
		if(cliente!=null){
			nuevoCupo = new Moto(cliente,entrada,salida);
			cliente.setEntradas(cliente.getEntradas()+1);
			nuevoCupo.calcularTiempoTrans();
			nuevoCupo.calcularCobro();
			nuevoCupo.setValorCobrado(valor);
			contabilidad.ingreso(fecha, nuevoCupo);
			nuevoCupo.getCliente().setMinutosReg((long) (nuevoCupo.getCliente().getMinutosReg() + nuevoCupo.getTiempoTranscurrido()));
			nuevoCupo.getCliente().setCobroTotal((int) (nuevoCupo.getCliente().getCobroTotal() + nuevoCupo.getValorCobrado()));
			return nuevoCupo;
		}
		else{
			cliente = new ClienteDiario(placa);
			nuevoCupo = new Moto(cliente,entrada,salida);
			cliente.setEntradas(cliente.getEntradas()+1);
			dataBank.adjuntarClienteDiario(cliente);
			nuevoCupo.calcularTiempoTrans();
			nuevoCupo.calcularCobro();
			nuevoCupo.setValorCobrado(valor);
			contabilidad.ingreso(fecha, nuevoCupo);
			nuevoCupo.getCliente().setMinutosReg((long) (nuevoCupo.getCliente().getMinutosReg() + nuevoCupo.getTiempoTranscurrido()));
			nuevoCupo.getCliente().setCobroTotal((int) (nuevoCupo.getCliente().getCobroTotal() + nuevoCupo.getValorCobrado()));
			return nuevoCupo;
		}
	}
	public ContabilidadMensual getContabilidadMensual() {
		return contabilidadMensual;
	}
	public void setContabilidadMensual(ContabilidadMensual contabilidadMensual) {
		this.contabilidadMensual = contabilidadMensual;
	}
}
