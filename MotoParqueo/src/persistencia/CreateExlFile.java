package persistencia;

import java.io.*;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import contabilidad.Contabilidad;
import contabilidad.RegistroDiario;
import negocio.CupoDiario;
import presentacion.Utilidades;

public class CreateExlFile{
	
	private static void fillContent(Contabilidad contabilidad, Workbook wb, String hoy){
		String[] fecha = hoy.split("/");
		Sheet sheetTest = wb.getSheet(fecha[0]);
		if(sheetTest==null){
			sheetTest = wb.createSheet(fecha[0]);
		}
		Font fuenteHeader = wb.createFont();
		fuenteHeader.setBold(true);
		fuenteHeader.setFontHeightInPoints((short)20);
		fuenteHeader.setFontName("Times New Roman");
		CellStyle styleHeader = wb.createCellStyle();
		styleHeader.setAlignment(CellStyle.ALIGN_CENTER);
		styleHeader.setFont(fuenteHeader);
		Row rowHeader = sheetTest.createRow((short) 0);
		Cell cell = rowHeader.createCell(0);
		String[] columnas = {"Consecutivo","Tipo","Placa", "H.Entrada", "H.Salida", "Locker", "Cascos", "Tiempo(m)", "Pagado"};
		for(int j=0; j<columnas.length;j++){
			cell = rowHeader.createCell(j);
			cell.setCellValue(columnas[j]);
			cell.setCellStyle(styleHeader);
		}
		int i=1;
		Font fuenteBody = wb.createFont();
		fuenteBody.setBold(false);
		fuenteBody.setFontHeightInPoints((short)16);
		fuenteBody.setFontName("Times New Roman");
		CellStyle styleCons = wb.createCellStyle();
		styleCons.setFont(fuenteBody);
		styleCons.setAlignment(CellStyle.ALIGN_CENTER);
		long total=0;
		for (RegistroDiario next: contabilidad.getDia(hoy)) {
			Row row = sheetTest.createRow((short) i);
			String[] locker = Utilidades.lockerToString(next.getCupo().getLockerAsignado());
			String[] datos = {String.valueOf(next.getConsecutivoAsignado()),next.getCupo().getClass().getSimpleName(),next.getCupo().getCliente().getPlaca(),
					Utilidades.formaterHora(next.getCupo().getHoraIngreso()), Utilidades.formaterHora(next.getCupo().getHoraSalida()),
					locker[0], locker[1], String.valueOf(next.getCupo().getTiempoTranscurrido()),String.valueOf(next.getCupo().getValorCobrado())};
			for(int k=0; k<datos.length; k++){
				cell = row.createCell(k);
				cell.setCellValue(datos[k]);
				cell.setCellStyle(styleCons);
			}
			total+=next.getCupo().getValorCobrado();
			i++;
		}
		CellStyle styleFooter = wb.createCellStyle();
		styleFooter.setFont(fuenteHeader);
		styleFooter.setFillForegroundColor(IndexedColors.RED.getIndex());
	    styleFooter.setFillPattern(CellStyle.SOLID_FOREGROUND);
		Row rowFooter = sheetTest.createRow(i);			//Fila de total
		cell = rowFooter.createCell(columnas.length-2);
		cell.setCellValue("Total");
		cell.setCellStyle(styleFooter);
		cell = rowFooter.createCell(columnas.length-1);
		cell.setCellValue(total);
		cell.setCellType(0);
		cell.setCellStyle(styleFooter);
		for(int k=0; k<columnas.length;k++){
			sheetTest.autoSizeColumn(k);
		}
	}
	
	private static void fillContent(List<CupoDiario> historial, Workbook wb, String hoy){
		String[] fecha = hoy.split("/");
		Cell cell = null;
		Sheet sheetTest = wb.getSheet(fecha[0]);
		if(sheetTest==null){
			sheetTest = wb.createSheet(fecha[0]);
		}
		Font fuenteHeader = wb.createFont();
		fuenteHeader.setBold(true);
		fuenteHeader.setFontHeightInPoints((short)14);
		fuenteHeader.setFontName("Times New Roman");
		CellStyle styleHeader = wb.createCellStyle();
		styleHeader.setAlignment(CellStyle.ALIGN_CENTER);
		styleHeader.setFont(fuenteHeader);
		Font fuenteBody = wb.createFont();
		fuenteBody.setBold(false);
		fuenteBody.setFontHeightInPoints((short)12);
		fuenteBody.setFontName("Times New Roman");
		CellStyle styleCons = wb.createCellStyle();
		styleCons.setFont(fuenteBody);
		styleCons.setAlignment(CellStyle.ALIGN_CENTER);
		Row rowHeader = sheetTest.createRow((short) 0);
		String[] columnas = {"Tipo","Placa", "H.Entrada", "H.Salida", "Locker", "Cascos", "Tiempo(m)", "Pagado", "Asignado"};
		for(int j=0; j<columnas.length;j++){
			cell = rowHeader.createCell(j);
			cell.setCellValue(columnas[j]);
			cell.setCellStyle(styleHeader);
		}
		int i=1;
		for (CupoDiario next: historial) {
			Row row = sheetTest.createRow((short) i);
			cell = row.createCell(0);
			cell.setCellValue(next.getClass().getSimpleName());
			cell.setCellStyle(styleCons);
			cell = row.createCell(1);
			cell.setCellValue(next.getCliente().getPlaca());
			cell.setCellStyle(styleCons);
			cell = row.createCell(2);
			cell.setCellValue(Utilidades.formaterHora(next.getHoraIngreso()));
			cell.setCellStyle(styleCons);
			cell = row.createCell(3);
			cell.setCellValue(Utilidades.formaterHora(next.getHoraSalida()));
			cell.setCellStyle(styleCons);
			if (next.getLockerAsignado()!=null) {
				cell = row.createCell(4);
				cell.setCellValue(next.getLockerAsignado().getIdentificador());
				cell.setCellStyle(styleCons);
				cell = row.createCell(5);
				cell.setCellValue(next.getLockerAsignado().getCantidad());
				cell.setCellStyle(styleCons);
			}else{
				cell = row.createCell(4);
				cell.setCellValue("Ninguno");
				cell.setCellStyle(styleCons);
				cell = row.createCell(5);
				cell.setCellValue(0);
				cell.setCellStyle(styleCons);
			}
			cell = row.createCell(6);
			cell.setCellValue(next.getTiempoTranscurrido());
			cell.setCellStyle(styleCons);
			cell = row.createCell(7);
			cell.setCellValue(next.getValorCobrado());
			cell.setCellStyle(styleCons);
			cell = row.createCell(8);
			cell.setCellValue(next.getValorAsignado());
			cell.setCellStyle(styleCons);
			i++;
		}
		for(int j=0; j<columnas.length;j++){
			sheetTest.autoSizeColumn(j);
		}
	}
	
	private static void createFolder(String path){
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
	
	public static void guardarContabilidad(Contabilidad contabilidad, String hoy){
		String folder = System.getProperty("user.home")+"\\Documents\\Contabilidad-MotoParqueo\\Diario";
		createFolder(folder);
		String[] fecha = hoy.split("/");
		try {
			File file = new File(folder+"\\"+fecha[1]+"-"+fecha[2]+".xls");
			if (file.exists()) {
				InputStream inp = new FileInputStream(file);
				Workbook wb = WorkbookFactory.create(inp);
				fillContent(contabilidad, wb, hoy);
				FileOutputStream fileOut = new FileOutputStream(file);
				wb.write(fileOut);
				fileOut.close();
				wb.close();
			}else{
				file.createNewFile();
				Workbook wb = new HSSFWorkbook();  // or new XSSFWorkbook();
				fillContent(contabilidad, wb, hoy);
				FileOutputStream fileOut = new FileOutputStream(file);
				wb.write(fileOut);
				fileOut.close();
				wb.close();
			}
		} catch ( Exception ex ) {
			ex.printStackTrace();
			System.out.println(ex);
		}
	}
	public static void guardarHistorialDia(List<CupoDiario> historial, String hoy){
		String folder = System.getProperty("user.home")+"\\AppData\\Local\\MotoParqueo\\HistorialDiario";
		createFolder(folder);
		String[] fecha = hoy.split("/");
		try {
			File file = new File(folder+"\\"+fecha[1]+"-"+fecha[2]+".xls");
			if (file.exists()) {
				InputStream inp = new FileInputStream(file);
				Workbook wb = WorkbookFactory.create(inp);
				fillContent(historial, wb, hoy);
				FileOutputStream fileOut = new FileOutputStream(file);
				wb.write(fileOut);
				fileOut.close();
				wb.close();
			}else{
				file.createNewFile();
				Workbook wb = new HSSFWorkbook();
				fillContent(historial, wb, hoy);
				FileOutputStream fileOut = new FileOutputStream(file);				
				wb.write(fileOut);
				fileOut.close();
				wb.close();
			}
		} catch ( Exception ex ) {
			ex.printStackTrace();
			System.out.println(ex);
		}
	}
}
