package AEvaluable3Solucion;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Biblioteca {

	private static ArrayList<Libro> lista = new ArrayList<Libro>();
	private static String ficheroXML = "biblioteca.xml";
	private static int idUltimo = 0;
	
	/*
	recuperarTodos
	Descripcion: metodo que devuelve el contenido del fichero biblioteca.xml como lista de objetos tipo Libro
	Entradas: no
	Salidas: lista de objetos tipo Libro  
	*/
	
	public static ArrayList<Libro> recuperarTodos() {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(new File(ficheroXML));
			Element raiz = document.getDocumentElement();
			NodeList nodeList = document.getElementsByTagName("libro");			 
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				Element eElement = (Element) node;
				int id = Integer.parseInt(eElement.getAttribute("id"));
			    String titulo = eElement.getElementsByTagName("titulo").item(0).getTextContent();
			    String autor = eElement.getElementsByTagName("autor").item(0).getTextContent();
			    String anyo = eElement.getElementsByTagName("anyo").item(0).getTextContent();
			    String editorial = eElement.getElementsByTagName("editorial").item(0).getTextContent();
			    String paginas = eElement.getElementsByTagName("paginas").item(0).getTextContent();
			    Libro lib = new Libro(id,titulo,autor,anyo,editorial,paginas);
			    lista.add(lib);
			    idUltimo = id;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/*
	mostrarTodos
	Descripcion: metodo que muestra para cada libro de la lista el id y titulo
	Entradas: no
	Salidas: no
	*/
	public static void mostrarTodos() {
		System.out.println("\nContenido de la biblioteca: ");
		for (Libro lib : lista) {
			System.out.print("\n" + lib.getId() + " - " + lib.getTitulo());			
		}
	}
	
	
	/*
	recuperarLibro
	Descripcion: metodo que dado un identificador devuelve el objeto Libro con dicho identificador
	Entradas: entero que indica el identificador del libro
	Salidas: objeto Libro con dicho identificador o null si no se encuentra
	*/
	public static Libro recuperarLibro(int identificador) {
		Libro resultado = null;
		for (Libro lib : lista) {
			if (lib.getId() == identificador) {
				resultado = lib;
				break;
			}	
		}
		return resultado;
	}
	
	
	/*
	mostrarLibro
	Descripcion: metodo que dado el identificador de un libro muestra toda su informacion
	Entradas: entero con el identificador del libro
	Salidas: no
	*/
	public static void mostrarLibro(int identificador) {
		Libro lib = recuperarLibro(identificador);
		System.out.println("\nDetalles del libro: ");
		System.out.print("ID: ");
		System.out.println(lib.getId());
		System.out.print("T�tulo: ");
		System.out.println(lib.getTitulo());
		System.out.print("Autor: ");
		System.out.println(lib.getAutor());
		System.out.print("A�o: ");
		System.out.println(lib.getAnyo());
		System.out.print("Editorial: ");
		System.out.println(lib.getEditorial());
		System.out.print("P�ginas: ");
		System.out.println(lib.getPaginas());
	}
	
	
	/*
	crearLibro
	Descripcion: metodo que dado un objeto Libro lo anyade a la lista de libros y actualizar la lista en el fichero XML de la biblioteca
	Entradas: objeto libro nuevo a anyadir a la lista
	Salidas: entero con el identificador del libro recien anyadido
	*/
	public static int crearLibro(Libro lib) {
		lista.add(lib);
		writeXML(lista);
		return lib.getId();
	}
	
	
	/*
	crear
	Descripcion: metodo auxiliar que pide informacion para un nuevo libro, lo crea y llama a crearLibro pasandoselo
	Entradas: no
	Salidas: no
	*/
	public static void crear() {
		String titulo, autor, anyo, editorial, paginas;
		Scanner teclado = new Scanner(System.in);
		System.out.println("Datos del nuevo libro: ");
		System.out.print("T�tulo: ");
		titulo = teclado.next();
		System.out.print("Autor: ");
		autor = teclado.next();
		System.out.print("A�o: ");
		anyo = teclado.next();
		System.out.print("Editorial: ");
		editorial = teclado.next();
		System.out.print("P�ginas: ");
		paginas = teclado.next();
		Libro lib = new Libro(++idUltimo, titulo, autor, anyo, editorial, paginas);
		int id = crearLibro(lib);
		System.out.println("Libro con ID " + id + " creado");
	}
	
	
	/*
	writeXML
	Descripcion: metodo que dado una lista de libros la escribe en el fichero biblioteca.xml
	Entradas: lista de objetos tipo Libro
	Salidas: no
	*/
	public static void writeXML(ArrayList<Libro> lista) {
	    try {
	        DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
	        DocumentBuilder build = dFact.newDocumentBuilder();
	        Document doc = build.newDocument();
	        Element raiz = doc.createElement("biblioteca");
	        doc.appendChild(raiz);
	        for (Libro lib : lista) {
	        	Element libro = doc.createElement("libro");
	        	String id = String.valueOf(lib.getId());
	        	libro.setAttribute("id",id);
		        raiz.appendChild(libro);
	            Element titulo = doc.createElement("titulo");
	            titulo.appendChild(doc.createTextNode(String.valueOf(lib.getTitulo())));
	            libro.appendChild(titulo);
	            Element autor = doc.createElement("autor");
	            autor.appendChild(doc.createTextNode(String.valueOf(lib.getAutor())));
	            libro.appendChild(autor);
	            Element anyo = doc.createElement("anyo");
	            anyo.appendChild(doc.createTextNode(String.valueOf(lib.getAnyo())));
	            libro.appendChild(anyo);
	            Element editorial = doc.createElement("editorial");
	            editorial.appendChild(doc.createTextNode(String.valueOf(lib.getEditorial())));
	            libro.appendChild(editorial);
	            Element paginas = doc.createElement("paginas");
	            paginas.appendChild(doc.createTextNode(String.valueOf(lib.getPaginas())));
	            libro.appendChild(paginas);
	        }
	        TransformerFactory tranFactory = TransformerFactory.newInstance();
	        Transformer aTransformer = tranFactory.newTransformer();
	        aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
	        aTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
	        aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        DOMSource source = new DOMSource(doc);
	        try {
	            FileWriter fw = new FileWriter("biblioteca.xml");
	            StreamResult result = new StreamResult(fw);
	            aTransformer.transform(source, result);
	            fw.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    } catch (TransformerException ex) {
	        System.out.println("Error escribiendo el documento");
	    } catch (ParserConfigurationException ex) {
	        System.out.println("Error construyendo el documento");
	    }
	}
	
	
	/*
	actualizarLibro
	Descripcion: metodo que, dado el identificador de un libro, actualiza su contenido pidiendo al usuario los nuevos valores
	Entradas: entero con el identificador del libro
	Salidas: no
	*/
	public static void actualizarLibro(int identificador) {
		Scanner teclado = new Scanner(System.in);
		String nuevoValor;
		Libro lib = recuperarLibro(identificador);
		System.out.println(lib.toString());
		System.out.println("Actualizar datos (introducir espacio y enter para preservar valor): ");
		System.out.print("Actualizar t�tulo: "); nuevoValor = teclado.nextLine();
		if (!nuevoValor.equals(" ")) lib.setTitulo(nuevoValor);
		System.out.print("Actualizar autor: "); nuevoValor = teclado.nextLine();
		if (!nuevoValor.equals(" ")) lib.setAutor(nuevoValor);
		System.out.print("Actualizar a�o: "); nuevoValor = teclado.nextLine();
		if (!nuevoValor.equals(" ")) lib.setAnyo(nuevoValor);
		System.out.print("Actualizar editorial: "); nuevoValor = teclado.nextLine();
		if (!nuevoValor.equals(" ")) lib.setEditorial(nuevoValor);
		System.out.print("Actualizar p�ginas: "); nuevoValor = teclado.nextLine();
		if (!nuevoValor.equals(" ")) lib.setPaginas(nuevoValor);
		int indice = 0;
		for (Libro l : lista) {
			if (l.getId() == identificador) {
				lista.set(indice,lib);
				break;
			}
			indice++;
		}
		writeXML(lista);
	}
	
	
	/*
	borrarLibro
	Descripcion: metodo que dado un identificador de un libro, lo elimina de la listo y actualiza el fichero biblioteca.xml
	Entradas: entero con el identificador del objeto libro
	Salidas: no
	*/
	public static void borrarLibro(int identificador) {
		Scanner teclado = new Scanner(System.in);
		Libro lib = recuperarLibro(identificador);
		System.out.println(lib.toString());
		System.out.println("�Est� seguro que desea borrar este libro de la biblioteca (s/n)?");
		String respuesta = teclado.next();
		if (!respuesta.equals("s")) return;
		int indice = 0;
		for (Libro l : lista) {
			if (l.getId() == identificador) {
				lista.remove(indice);
				break;
			}
			indice++;
		}
		writeXML(lista);
	}
	
	
	/*
	main
	Descripcion: metodo main, rellena la lista con los libros a partir de biblioteca.xml y muestra y gestiona el menu de la aplicacion
	Entradas: no
	Salidas: no
	*/
	public static void main(String[] args) {
		
		lista = recuperarTodos();
		Scanner teclado = new Scanner(System.in);
		int opcion = 0;
		int id;
		
		while (opcion != 6) {
			System.out.println("\n\n=============================================");
			System.out.println("              MEN� BIBLIOTECA");
			System.out.println("=============================================");
			System.out.println("1. Mostrar todos los t�tulos de la biblioteca");
			System.out.println("2. Mostrar informaci�n detallada de un libro");
			System.out.println("3. Crear nuevo libro");
			System.out.println("4. Actualizar libro");
			System.out.println("5. Borrar libro");
			System.out.println("6. Cerrar la biblioteca");
			System.out.print("\n >>> Elegir una opci�n: ");
			opcion = Integer.parseInt(teclado.next());
			switch(opcion) {
			case 1:
				mostrarTodos();
				break;
			case 2:
				System.out.print(" >> Indica el n�mero del libro a mostrar: ");
				id = Integer.parseInt(teclado.next());
				mostrarLibro(id);
				break;
			case 3:
				crear();
				break;
			case 4:
				System.out.print(" >> Indica el n�mero del libro a actualizar: ");
				id = Integer.parseInt(teclado.next());
				actualizarLibro(id);
				break;
			case 5:
				System.out.print(" >> Indica el n�mero del libro a borrar: ");
				id = Integer.parseInt(teclado.next());
				borrarLibro(id);
				break;
			case 6:
				System.out.println("Bye!");
				teclado.close();
				break;
			default:
				break;
			}
		}
	}

}
