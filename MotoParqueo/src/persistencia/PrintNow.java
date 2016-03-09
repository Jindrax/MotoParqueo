package persistencia;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

import negocio.CupoDiario;
import negocio.CupoMensual;
import negocio.Parqueadero;
import presentacion.Utilidades;

public class PrintNow {
	
	private static List<LineaRecibo> recibo = null;
	
	public static void printCard(final List<LineaRecibo> Bill) throws PrinterException{
		final PrinterJob job = PrinterJob.getPrinterJob();
		Printable contentToPrint = new Printable(){
			@Override
			public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
				BufferedImage img = null;
				try {
				    img = ImageIO.read(new File("log6464.png"));
				} catch (IOException e) {
				}
				pageFormat.setOrientation(PageFormat.PORTRAIT);
				Paper pPaper = pageFormat.getPaper();
				pPaper.setImageableArea(15, 0, 150 , pPaper.getHeight());
				pageFormat.setPaper(pPaper);
				Graphics2D g2d = (Graphics2D) graphics;
				g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
				g2d.setFont(new Font("Times New Roman", Font.BOLD, 12));
				if (pageIndex >0){return NO_SUCH_PAGE;} //Only one page
				int y = 10;
				g2d.drawImage(img, 36, y, null);
				y+=84;
				for (LineaRecibo next: Bill) {
					g2d.setFont(new Font("Times New Roman", next.mod, next.size));
					g2d.drawString(next.linea, 0, y);
					y = y + 15;
				}
				return PAGE_EXISTS;
			}
		};
		PageFormat pageFormat = new PageFormat();
		pageFormat.setOrientation(PageFormat.PORTRAIT);
		Paper pPaper = pageFormat.getPaper();
		pPaper.setImageableArea(15, 0, 150 , pPaper.getHeight());
		pageFormat.setPaper(pPaper);
		job.setPrintable(contentToPrint, pageFormat);
		//boolean don = job.printDialog();
		try {
			job.print();
		} catch (PrinterException e) {
			System.err.println(e.getMessage());
		}
	}	
	public static void imprimirReciboEntrada(CupoDiario cupo){
		String horaE = Utilidades.formaterHora(cupo.getHoraIngreso());
		recibo = new ArrayList<LineaRecibo>();
		addLinea("    MotoParqueo 259",Font.BOLD,14);
		addLinea("            Luz Stella Garcia Campos",Font.PLAIN, 8);
		addLinea("    39554400-2"+" "+"Regimen Simplificado",Font.PLAIN, 8);
		addLinea("");
		if(new GregorianCalendar().get(GregorianCalendar.HOUR_OF_DAY)>=12){
			if (new GregorianCalendar().get(GregorianCalendar.HOUR_OF_DAY)<19) {
				addLinea("Buenas Tardes", Font.BOLD, 14);
			}else{
				addLinea("Buenas Noches", Font.BOLD, 14);
			}
		}else{
			addLinea("Buenos Dias",Font.BOLD,14);
		}
		addLinea("");
		addLinea(Utilidades.formaterFecha(cupo.getHoraIngreso())+ "       " +String.valueOf(horaE),Font.BOLD,14);
		addLinea(String.valueOf(cupo.getCliente().getPlaca()),Font.BOLD, 14);
		addLinea("");
		addLinea("Recibo: "+String.valueOf(cupo.getSerial()), Font.BOLD, 16);
		if(cupo.getLockerAsignado()!=null){
			addLinea("Casco(s): "+String.valueOf(cupo.getLockerAsignado().getIdentificador())+"-"+String.valueOf(cupo.getLockerAsignado().getCantidad()), Font.BOLD, 14);	
		}else{
			addLinea("Ningun Casco", Font.BOLD, 14);
		}
		addLinea("");
		addLinea("Contacto:");
		addLinea("3004626139"+"  "+"3167417496");
		addLinea("Cll. 9 #2-59");
		addLinea("Hoy servicio hasta", Font.BOLD, 12);
		addLinea(String.valueOf(Parqueadero.getHoraCierre()), Font.BOLD, 12);
		try {
			printCard(recibo);
		} catch (PrinterException e) {
			e.printStackTrace();
		}
	}
	public static void imprimirReciboSalida(CupoDiario cupo){
		String horaE = Utilidades.formaterHora(cupo.getHoraIngreso());
		String horaS = Utilidades.formaterHora(cupo.getHoraSalida());
		recibo = new ArrayList<LineaRecibo>();
		addLinea("    MotoParqueo 259",Font.BOLD,14);
		addLinea("            Luz Stella Garcia Campos",Font.PLAIN, 8);
		addLinea("    39554400-2"+" "+"Regimen Simplificado",Font.PLAIN, 8);
		addLinea("");
		if(new GregorianCalendar().get(GregorianCalendar.HOUR_OF_DAY)>=12){
			if (new GregorianCalendar().get(GregorianCalendar.HOUR_OF_DAY)<19) {
				addLinea("Buenas Tardes", Font.BOLD, 14);
			}else{
				addLinea("Buenas Noches", Font.BOLD, 14);
			}
		}else{
			addLinea("Buenos Dias",Font.BOLD,14);
		}
		addLinea("");
		addLinea(Utilidades.formaterFecha(cupo.getHoraIngreso()),Font.BOLD,14);
		addLinea("Placa:",Font.BOLD,14);
		addLinea(String.valueOf(cupo.getCliente().getPlaca()),Font.BOLD,14);
		if(cupo.getLockerAsignado()!=null){
			addLinea("Casco(s): "+String.valueOf(cupo.getLockerAsignado().getIdentificador())+"-"+String.valueOf(cupo.getLockerAsignado().getCantidad()), Font.BOLD, 14);	
		}else{
			addLinea("Ningun Casco", Font.BOLD, 14);
		}
		addLinea(horaE+"------------->"+horaS,Font.BOLD, 14);
		addLinea(Utilidades.diferenciaHoras(cupo.getHoraIngreso(), cupo.getHoraSalida()),Font.BOLD,14);
		addLinea("Total: $"+ String.valueOf((int)cupo.getValorCobrado()),Font.BOLD, 14);
		addLinea("");
		addLinea("Contacto:");
		addLinea("3004626139"+"  "+"3167417496");
		addLinea("Cll. 9 #2-59");
		addLinea("Hoy servicio hasta", Font.BOLD, 12);
		addLinea(String.valueOf(Parqueadero.getHoraCierre()), Font.BOLD, 12);
		try {
			printCard(recibo);
		} catch (PrinterException e) {
			e.printStackTrace();
		}
	}
	private static void addLinea(String linea){
		recibo.add(new LineaRecibo(linea));
	}
	private static void addLinea(String linea, int mod, int size){
		recibo.add(new LineaRecibo(linea, mod, size));
	}
	public static void printReciboMensual(CupoMensual cupo){
		GregorianCalendar fechaAnt = Utilidades.mesAnterior(cupo.getFechaSiguienteCobro());
		int mensualidad = cupo.getMensualidad();
		recibo = new ArrayList<LineaRecibo>();
		addLinea("    MotoParqueo 259",Font.BOLD,14);
		addLinea("            Luz Stella Garcia Campos",Font.PLAIN, 8);
		addLinea("    39554400-2"+" "+"Regimen Simplificado",Font.PLAIN, 8);
		addLinea("");
		addLinea(Utilidades.formaterFecha(new GregorianCalendar()),Font.BOLD,14);
		String nombre = "";
		StringTokenizer token = new StringTokenizer(cupo.getCliente().getNombre(), " ");
		int i = 2;
		do{
			if(i>0){
				nombre=nombre.concat(token.nextToken());
				nombre=nombre.concat(" ");
				i--;
			}else{
				i=2;
				addLinea(nombre, Font.BOLD, 10);
				nombre="";
			}
		}while(token.hasMoreTokens());
		if(!nombre.equals("")){
			addLinea(nombre, Font.BOLD, 10);
		}
		addLinea("Ha pagado a:");
		addLinea("Luz Stella Garcia Campos");
		addLinea("$"+String.valueOf(mensualidad),Font.BOLD,10);
		addLinea("Por servicio de parqueadero de:");
		addLinea("moto placa:");
		addLinea(cupo.getCliente().getPlaca(), Font.BOLD, 10);
		addLinea("Desde:");
		addLinea(Utilidades.formaterFecha(fechaAnt),Font.BOLD,10);
		addLinea("Hasta:");
		addLinea(Utilidades.formaterFecha(cupo.getFechaSiguienteCobro()),Font.BOLD,10);
		addLinea("");
		addLinea("Contacto:");
		addLinea("3004626139"+"  "+"3167417496");
		addLinea("Cll. 9 #2-59");
		try {
			printCard(recibo);
		} catch (PrinterException e) {
			e.printStackTrace();
		}
	}
	public static void printResumenDia(String first, String last, String total){
		recibo = new ArrayList<LineaRecibo>();
		addLinea(Utilidades.formaterFecha(new GregorianCalendar()), Font.BOLD,14);
		addLinea("Desde:", Font.BOLD, 14);
		addLinea(first, Font.BOLD, 14);
		addLinea("Hasta:", Font.BOLD, 14);
		addLinea(last, Font.BOLD, 14);
		addLinea("Con un total de:", Font.BOLD, 14);
		addLinea(total,Font.BOLD, 14);
		try {
			printCard(recibo);
		} catch (PrinterException e) {
			e.printStackTrace();
		}
	}
}