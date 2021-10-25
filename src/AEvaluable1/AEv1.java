package AEvaluable1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class AEv1 {

	public static void getInformacion(String[] args) {
		String nombre = args[0];
		File file =new File(nombre);
		System.out.println("Ha seleccionado obtener informacion.");
		System.out.println("- Nombre: " + nombre);
		System.out.print("- Tipo: ");
		System.out.println(file.isDirectory() ? "directorio" : "fichero");
		System.out.println("- Ubicacion: " + file.getAbsolutePath());
		System.out.println("- Ultima modificacion: " + new Date(file.lastModified()));
		System.out.print("- Esta oculto? ");
		System.out.println(file.isHidden() ? "Si" : "No");
		if (file.isDirectory()) {
			System.out.println("- Los elementos que contiene son: ");
			String[] lista = file.list();
			for (String elemento : lista) {
				System.out.println("     " + elemento);
			}
			System.out.println("- Espacio libre: " + file.getFreeSpace()/1024/1024/1024 + " GB");
			System.out.println("- Espacio disponible: " + file.getUsableSpace()/1024/1024/1024 + " GB");
			System.out.println("- Espacio total: " + file.getTotalSpace()/1024/1024/1024 + " GB");
		}
		else System.out.println("- Tamaño: " + file.getTotalSpace() + " Bytes");
		
	}
	
	public static String creaCarpeta(String[] args) {
		String nombre = args[0];
		File file =new File(nombre);
		String ruta = file.getAbsolutePath();
		Scanner teclado = new Scanner(System.in);
		System.out.print("Escriba el nombre de la carpeta a crear: ");
		String nombreCarpeta = teclado.nextLine();
		String rutaCompletaCarpeta = ruta + nombreCarpeta;
		File carpeta = new File(rutaCompletaCarpeta);
		boolean carpetaTrueFalse =carpeta.mkdir();
		if (carpetaTrueFalse) {  
			System.out.println("La carpeta se ha creado satisfactoriamente.");  
		}else{  
			System.out.println("Error !");
		}
		return rutaCompletaCarpeta;
	}
	
	public static void creaFichero(String rutaCarpeta) throws IOException {
		File file2 =new File(rutaCarpeta);
		if (file2.exists()) {
			Scanner teclado = new Scanner(System.in); 
			System.out.print("Escriba el nombre del fichero a crear: ");
			String nombreFichero = teclado.nextLine();
			File fichero = new File (rutaCarpeta,nombreFichero);
			boolean ficheroTrueFalse = fichero.createNewFile();
			if (ficheroTrueFalse) {  
				System.out.println("El fichero se ha creado satisfactoriamente.");  
			}else{  
				System.out.println("Error !");
			}
		}else {
			System.out.println("Error, la carpeta no se ha creado previamente. Debe seleccionar el indice 2.");
		}
	}
	
	public static void elimina(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr); 
		System.out.print("¿Que desea eliminar? \n"
				+ " -1- Un fichero. \n"
				+ " -2- Una carpeta. \n"
				+ "Introduzca el indice que desee: ");
		String numeroString = br.readLine();
		int numero = Integer.parseInt(numeroString);
		String nombre = args[0];
		File file =new File(nombre);
		String ruta = file.getAbsolutePath();
		if (numero == 1) {
			System.out.print("Si el fichero se encuentre dentro de alguna carpeta usa el formato nombreCarpeta/nombreFichero \n" 
					+ "Escriba el nombre del fichero a eliminar: ");
			String nombreFichero = br.readLine();
			String rutaCompletaFichero = ruta + nombreFichero;
			File fichero = new File(rutaCompletaFichero);
			if (fichero.exists()) {
				boolean ficheroTrueFalse = fichero.delete();
				if (ficheroTrueFalse) {  
					System.out.println("El fichero se ha eliminado correctamente.");  
				}else{  
					System.out.println("Error !");
				}
			} else{  
				System.out.println("El fichero no existe!");
			}
		} else if (numero == 2){
			System.out.print("Escriba el nombre de la carpeta a eliminar: ");
			String nombreCarpeta = br.readLine();
			String rutaCompletaCarpeta = ruta + nombreCarpeta;
			File carpeta = new File(rutaCompletaCarpeta);
			if (carpeta.exists()) {
				boolean carpetaTrueFalse = carpeta.delete();
				if (carpetaTrueFalse) {  
					System.out.println("La carpeta se ha eliminado correctamente.");  
				}else{  
					System.out.println("Error !");
				}
			} else{  
				System.out.println("La carpeta no existe!");
			}
		} else {
			System.out.println("Indice erroneo.");
		}
	}
	
	public static void renombra(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr); 
		System.out.print("¿Que desea renombrar? \n"
				+ " -1- Un fichero. \n"
				+ " -2- Una carpeta. \n"
				+ "Introduzca el indice que desee: ");
		String numeroString = br.readLine();
		int numero = Integer.parseInt(numeroString);
		String nombre = args[0];
		File file =new File(nombre);
		String ruta = file.getAbsolutePath();
		if (numero == 1) {
			System.out.print("Si el fichero se encuentre dentro de alguna carpeta usa el formato nombreCarpeta/nombreFichero \n" 
					+ "Escriba el nombre del fichero a renombrar: ");
			String nombreFichero1 = br.readLine();
			String rutaCompletaFichero1 = ruta + nombreFichero1;
			File fichero1 = new File(rutaCompletaFichero1);
			if (fichero1.exists()) {
				System.out.print("Si el fichero se encuentra dentro de alguna carpeta usa el formato nombreCarpeta/nombreFichero \n" 
						+ "Introduce un nombre nuevo: ");
	            String nombreNuevo = br.readLine();
	            String rutaCompletaFichero2 = ruta + nombreNuevo;
	            File fichero2 = new File(rutaCompletaFichero2);
				boolean fichero2TrueFalse = fichero1.renameTo(fichero2);
				if (fichero2TrueFalse) {  
					System.out.println("El fichero se ha renombrado correctamente.");  
				}else{  
					System.out.println("Error !");
				}
			} else{  
				System.out.println("El fichero no existe!");
			}
		} else if (numero == 2){
			System.out.print("Escriba el nombre de la carpeta a renombrar: ");
			String nombreCarpeta1 = br.readLine();
			String rutaCompletaCarpeta1 = ruta + nombreCarpeta1;
			File carpeta1 = new File(rutaCompletaCarpeta1);
			if (carpeta1.exists()) {
				System.out.print("Introduce un nombre nuevo: ");
	            String nombreNuevo = br.readLine();
	            String rutaCompletaCarpeta2 = ruta + nombreNuevo;
	            File carpeta2 = new File(rutaCompletaCarpeta2);
				boolean carpeta2TrueFalse = carpeta1.renameTo(carpeta2);
				if (carpeta2TrueFalse) {  
					System.out.println("La carpeta se ha renombrado correctamente.");  
				}else{  
					System.out.println("Error !");
				}
			} else{  
				System.out.println("La carpeta no existe!");
			}
		} else {
			System.out.println("Indice erroneo.");
		}
	}
	
	public static void main(String[] args) throws IOException, InterruptedException, ParserConfigurationException, SAXException, NumberFormatException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		System.out.print("¿Que desea hacer? \n"
				+ " -0- Cerrar aplicacion. \n"
				+ " -1- Obtener informacion. \n"
				+ " -2- Crear una carpeta en el directorio local. \n"
				+ " -3- Crear un fichero en la carpeta creada. \n"
				+ " -4- Eliminar ficheros/carpetas. \n"
				+ " -5- Renombrar ficheros/carpetas. \n"
				+ "Introduzca un indice para realizar una accion: ");
		String numeroString = br.readLine();
		String rutaCarpeta = "";
		
		try {
			int numero = Integer.parseInt(numeroString);
		while (numero != 0) {
			if (numero >= 0 && numero <= 5) {
				switch (numero) {
					case 1:
						getInformacion(args);
						break;
					case 2:
						rutaCarpeta = creaCarpeta(args);
						break;
					case 3:
						creaFichero(rutaCarpeta);
						break;
					case 4:
						elimina(args);
						break;
					case 5:
						renombra(args);
						break;
					default:
						break;
				}
			} else {
				System.out.println("Indice erroneo.");
			}
			System.out.print("¿Que desea hacer? \n"
					+ " -0- Cerrar aplicacion. \n"
					+ " -1- Obtener informacion. \n"
					+ " -2- Crear una carpeta en el directorio local. \n"
					+ " -3- Crear un fichero en la carpeta creada. \n"
					+ " -4- Eliminar ficheros/carpetas. \n"
					+ " -5- Renombrar ficheros/carpetas. \n"
					+ "Introduzca un indice para realizar otra accion: ");
			numeroString = br.readLine();
			numero = Integer.parseInt(numeroString);
		}
		System.out.print("Hasta luego. Gracias por usar la aplicacion.");
		}catch(NumberFormatException e) {
            System.out.println("It is not numerical string");
        }
	}
}
