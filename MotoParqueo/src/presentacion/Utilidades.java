package presentacion;

import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

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
		int hora = cal.get(GregorianCalendar.HOUR_OF_DAY);
		int minuto = cal.get(GregorianCalendar.MINUTE);
		int segundo = cal.get(GregorianCalendar.SECOND);
		return ("("+hora+minuto+segundo+")");
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
}
