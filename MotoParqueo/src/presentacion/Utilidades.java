package presentacion;

import java.io.File;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import negocio.Locker;

public class Utilidades {

	public static String formaterHora(GregorianCalendar cal){
		int hora = cal.get(GregorianCalendar.HOUR_OF_DAY);
		int minuto = cal.get(GregorianCalendar.MINUTE);
		if(hora>=10 && minuto>=10) {
			return (hora + ":" + minuto);
		}
		if(hora>=10 && minuto<10){
			return (hora + ":0" + minuto);
		}
		if(hora<10 && minuto>=10){
			return ("0"+hora + ":" + minuto);
		}
		if(hora<10 && minuto<10){
			return ("0"+hora + ":0" + minuto);
		}
		return null;
	}
	public static String formaterFecha(GregorianCalendar cal){
		int year = cal.get(GregorianCalendar.YEAR);
		int month = cal.get(GregorianCalendar.MONTH);
		int day = cal.get(GregorianCalendar.DAY_OF_MONTH);
		month++;
		if(day<10 && month>10){
			return "0" + day + "/" + month + "/" + year;
		}
		if(day>10 && month<10){
			return day + "/" + "0" + month + "/" + year;
		}
		if(day>10 && month>10){
			return day + "/" + month + "/" + year;
		}
		return day + "/" + month + "/" + year;
	}
	public static GregorianCalendar traductorFecha(String fecha){
		String[] token = fecha.split("/");
		return new GregorianCalendar(Integer.parseInt(token[2]), Integer.parseInt(token[1])-1, Integer.parseInt(token[0]));
	}
	public static String diferenciaHoras(GregorianCalendar in, GregorianCalendar out){
		long tiempoTrans = out.getTimeInMillis() - in.getTimeInMillis();
		tiempoTrans = TimeUnit.MILLISECONDS.toSeconds(tiempoTrans);
		tiempoTrans = (long) Math.ceil(tiempoTrans/60.0);
		if(tiempoTrans>60){
			int minutos = (int) (tiempoTrans%60);
			int horas = (int) (tiempoTrans/60);
			return horas+" h, "+minutos+" m.";
		}else{
			return tiempoTrans+" m.";
		}
	}
	public static String formaterHoraFile(GregorianCalendar cal){
		String retorno;
		int hora = cal.get(GregorianCalendar.HOUR_OF_DAY);
		int minuto = cal.get(GregorianCalendar.MINUTE);
		int segundo = cal.get(GregorianCalendar.SECOND);
		retorno = String.format("(%2s%2s%2s)", hora,minuto,segundo);
		retorno = retorno.replace(' ', '0');
		return retorno;
	}
	public static String formaterFechaFile(GregorianCalendar cal){
		int year = cal.get(GregorianCalendar.YEAR);
		int month = cal.get(GregorianCalendar.MONTH);
		int day = cal.get(GregorianCalendar.DAY_OF_MONTH);
		month++;
		if(day<10 && month>10){
			return "0" + day + "-" + month + "-" + year;
		}
		if(day>10 && month<10){
			return day + "-" + "0" + month + "-" + year;
		}
		if(day>10 && month>10){
			return day + "-" + month + "-" + year;
		}
		return day + "-" + month + "-" + year;
	}
	public static GregorianCalendar fechaConstructor(String fecha, String hora){
		String[] fechaArray = fecha.split("/");
		int year = Integer.parseInt(fechaArray[2]);
		int month = Integer.parseInt(fechaArray[1])-1;
		int day = Integer.parseInt(fechaArray[0]);
		GregorianCalendar retorno = new GregorianCalendar(year, month, day);
		String[] horaArray = hora.split(":");
		int hour = Integer.parseInt(horaArray[0]);
		int minute = Integer.parseInt(horaArray[1]);
		retorno.set(GregorianCalendar.HOUR_OF_DAY, hour);
		retorno.set(GregorianCalendar.MINUTE, minute);
		retorno.set(GregorianCalendar.SECOND, 0);
		retorno.getTimeInMillis();
		return retorno;
	}
	public static String[] lockerToString(Locker lock){
		String[] retorno = {"",""};
		if(lock==null){
			retorno[0] = "Ninguno";
			retorno[1] = "-";
			return retorno;
		}else{
			retorno[0] = lock.getIdentificador();
			retorno[1] = String.valueOf(lock.getCantidad());
			return retorno;
		}
	}
	public static GregorianCalendar mesAnterior(GregorianCalendar mes){
		GregorianCalendar siguienteCobro = (GregorianCalendar) mes.clone();
		int mesActual = siguienteCobro.get(GregorianCalendar.MONTH);
		int annoActual = siguienteCobro.get(GregorianCalendar.YEAR);
		if (mesActual==0) {
			siguienteCobro.set(GregorianCalendar.MONTH, 11);
			siguienteCobro.set(GregorianCalendar.YEAR,annoActual-1);
		}else{
			siguienteCobro.set(GregorianCalendar.MONTH, mesActual-1);
		}
		return siguienteCobro;
	}
	public static GregorianCalendar mesSiguiente(GregorianCalendar mes){
		GregorianCalendar siguienteCobro = (GregorianCalendar) mes.clone();
		if (siguienteCobro.get(GregorianCalendar.MONTH)<=10) {
			siguienteCobro.set(GregorianCalendar.MONTH, siguienteCobro.get(GregorianCalendar.MONTH) + 1);
		}else{
			siguienteCobro.set(GregorianCalendar.YEAR, siguienteCobro.get(GregorianCalendar.YEAR)+1);
			siguienteCobro.set(GregorianCalendar.MONTH, 0);
		}
		return siguienteCobro;
	}
	public static void createFolder(String path){
		File pathFile = new File(path);
		if (!pathFile.exists()) {
			try {
				if (pathFile.mkdirs()) {
					JOptionPane.showMessageDialog(null, "Se creo el directorio correctamente.");
				} else {
					JOptionPane.showMessageDialog(null, "No se creo el directorio.");
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "No se pudo crear la ruta.");
			}
		}
	}
}
