package AEvaluable1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Scanner;

public class Solucion_AE01 {

	public static void getInformacion(String strFichero) {
		File fichero = new File(strFichero);
		System.out.println("Nombre: " + fichero.getName());
		if (fichero.isFile())
			System.out.println("Tipo: Fichero");
		else
			System.out.println("Tipo: Directorio");
		System.out.println("Ruta: " + fichero.getAbsolutePath());
		System.out.println("Fecha última modificación: " + new Date(fichero.lastModified()));
		System.out.println("Oculto: " + fichero.isHidden());
		if (fichero.isFile())
			System.out.println("Tamaño: " + fichero.length());
		else {
			System.out.println("Elementos: " + fichero.list().length);
			System.out.println("Espacio libre: " + fichero.getFreeSpace()/1024/1024/1024 + " GB");
			System.out.println("Espacio ocupado: " + (fichero.getTotalSpace()/1024/1024/1024 - fichero.getFreeSpace()/1024/1024/1024) + " GB");
			System.out.println("Espacio total: " + fichero.getTotalSpace()/1024/1024/1024 + " GB");
		}
	}
	
	public static void creaCarpeta() {
		Scanner teclado = new Scanner(System.in);
		System.out.print("Nombre de la carpeta a crear: ");
		String nombreCarpeta = teclado.nextLine();
		File nuevaCarpeta = new File(nombreCarpeta);
		if (!nuevaCarpeta.exists()) {
			nuevaCarpeta.mkdir();
			System.out.println("Carpeta creada con éxito");		
		} else
			System.out.println("La carpeta ya existe");
	}
	
	public static void creaFichero() {
		Scanner teclado = new Scanner(System.in);
		System.out.print("Nombre del fichero a crear: ");
		String nombreFichero = teclado.nextLine();
		File nuevoFichero = new File(nombreFichero);
		if (!nuevoFichero.exists()) {
			try {
				nuevoFichero.createNewFile();
				System.out.println("Fichero creado con éxito");	
			} catch (IOException e) {
				System.err.println("Error en la creación del fichero");
				e.printStackTrace();
			}	
		} else
			System.out.println("El fichero ya existe");
	}
	
	public static void elimina(String strDirectorio) {
		mostrarContenido(strDirectorio);
		File directorio = new File(strDirectorio);
		String[] contenido = directorio.list();
		Scanner teclado = new Scanner(System.in);
		System.out.print("Número del fichero/directorio a eliminar: ");
		int numero = teclado.nextInt();
		File itemEliminar = new File(contenido[numero-1]);  //-1 para obtener el indice correcto
		itemEliminar.delete();
		mostrarContenido(strDirectorio);
	}
	
	public static void renombra(String strDirectorio) {
		mostrarContenido(strDirectorio);
		File directorio = new File(strDirectorio);
		String[] contenido = directorio.list();
		Scanner teclado = new Scanner(System.in);
		System.out.print("Número del fichero/directorio a renombrar: ");
		String numero = teclado.nextLine();
		File itemRenombrar = new File(contenido[Integer.parseInt(numero)-1]);  //-1 para obtener el indice correcto
		System.out.print("Introducir nuevo nombre: ");
		String nuevoNombre = teclado.nextLine();
		File nuevoFichero = new File(nuevoNombre);
		boolean exito = itemRenombrar.renameTo(nuevoFichero);
		if (exito)
			System.out.println("Fichero renombrado con éxito");
		else
			System.out.println("Error al renombrar el fichero");
		mostrarContenido(strDirectorio);
	}
	
	public static void mostrarContenido(String strDirectorio) {
		File directorio = new File(strDirectorio);
		String[] contenido = directorio.list();
		System.out.println("\nContenido del directorio: \n");
		int contador = 1;
		for (String elemento : contenido) {
			System.out.println(contador++ + ". " + elemento);
		}
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		String directorio = args[0];
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String opcion = "0";
		while(!opcion.equals("6")) {
			System.out.println("\nDirectorio de trabajo pasado como parámetro en args[0]: " + directorio);
			System.out.println("1. Información");
			System.out.println("2. Crear carpeta");
			System.out.println("3. Crear fichero");
			System.out.println("4. Eliminar fichero / carpeta");
			System.out.println("5. Renombrar fichero / carpeta");
			System.out.println("6. Finalizar");
			System.out.print("Escoge una opción: ");
			opcion = br.readLine();
			switch (opcion) {
				case "1":
					getInformacion(directorio);
					break;
				case "2":
					creaCarpeta();
					break;
				case "3":
					creaFichero();
					break;
				case "4":
					elimina(directorio);
					break;
				case "5":
					renombra(directorio);
					break;
				case "6":
					break;
				default:
					break;
			}
		}
	}
}
