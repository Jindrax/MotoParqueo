package negocio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Config implements Serializable{
	private static final long serialVersionUID = 8595611351927552883L;
	private static int precioMediaHora=600;
	private static int precioUnaHora=900;
	private static int precioPorHora=700;
	private static int precioPorDia=4000;
	private static int numeroLockers=32;
	private static List<String> preferidos;
	public static int getPrecioMediaHora() {
		return precioMediaHora;
	}
	public static int getPrecioUnaHora() {
		return precioUnaHora;
	}
	public static int getPrecioPorHora() {
		return precioPorHora;
	}
	public static int getPrecioPorDia() {
		return precioPorDia;
	}
	public static int getNumeroLockers() {
		return numeroLockers;
	}
	public static List<String> getPreferidos() {
		return preferidos;
	}
	public static void inicializar(){
		File ruta = new File("config.cfg");
		Scanner lector = null;
		try {
			lector = new Scanner(ruta);
			do {
				StringTokenizer token = new StringTokenizer(lector.nextLine(),"=");
				switch(token.nextToken()){
				case "numeroLockers":
					numeroLockers = Integer.parseInt(token.nextToken().trim());
					break;
				case "precioMediaHora":
					precioMediaHora=Integer.parseInt(token.nextToken().trim());
					break;
				case"precioUnaHora":
					precioUnaHora=Integer.parseInt(token.nextToken().trim());
					break;
				case"precioPorHora":
					precioPorHora=Integer.parseInt(token.nextToken().trim());
					break;
				case"precioPorDia":
					precioPorDia=Integer.parseInt(token.nextToken().trim());
					break;
				case "preferidos":
					preferidos = new ArrayList<String>(); 
					String preferido = token.nextToken().trim();
					String[] preferArray = preferido.split(":");
					for(int i=0; i<preferArray.length; i++){
						preferidos.add(preferArray[i]);
					}
					break;
				}
			} while (lector.hasNextLine());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			lector.close();
		}
	}
}
