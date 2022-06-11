package AEvaluable3;

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
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Biblioteca {

	private static String biblioteca = "biblioteca.xml";
	private static ArrayList<Libro> lista = new ArrayList<Libro>();
	private static int idUltimo = 0;

	
	/*
	recuperarTodos
	Descripcion: método que devuelve una lista de objetos de tipo Libroque son todos los libros de la biblioteca extraídos a partir 
				 del fichero "biblioteca.xml".
	Entradas: no.
	Salidas: lista de objetos de tipo Libro.
	*/
	public static ArrayList<Libro> recuperarTodos() {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(new File(biblioteca));
			Element raiz = document.getDocumentElement();
			NodeList nodeList = document.getElementsByTagName("libro");			 
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node nodo = nodeList.item(i);
				Element elemento = (Element) nodo;
				int id = Integer.parseInt(elemento.getAttribute("id"));
			    String titulo = elemento.getElementsByTagName("titulo").item(0).getTextContent();
			    String autor = elemento.getElementsByTagName("autor").item(0).getTextContent();
			    String anyo_publicacion = elemento.getElementsByTagName("anyo_publicacion").item(0).getTextContent();
			    String editorial = elemento.getElementsByTagName("editorial").item(0).getTextContent();
			    String numero_paginas = elemento.getElementsByTagName("numero_paginas").item(0).getTextContent();
			    Libro nuevoLibro = new Libro(id,titulo,autor,anyo_publicacion,editorial,numero_paginas);
			    lista.add(nuevoLibro);
			    idUltimo = id;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	
	/*
	pedirDatosLibro Descripción: método auxiliar que pide información para un nuevo libro, lo crea y llama al método crearLibro pasandoselo 
					como parámetro.
	Entradas: no.
	Salidas: no.
	*/
	public static void pedirDatosLibro() {
		String titulo;
		String autor;
		String anyoPublicacion;
		String editorial;
		String numeroPaginas;
		Scanner teclado = new Scanner(System.in);
		System.out.println("Introduce los datos del nuevo libro: ");
		System.out.print("Título: ");
		titulo = teclado.next();
		System.out.print("Autor: ");
		autor = teclado.next();
		System.out.print("Año de publicación: ");
		anyoPublicacion = teclado.next();
		System.out.print("Editorial: ");
		editorial = teclado.next();
		System.out.print("Número de páginas: ");
		numeroPaginas = teclado.next();
		Libro libro = new Libro(++idUltimo, titulo, autor, anyoPublicacion, editorial, numeroPaginas);
		int id = crearLibro(libro);
		System.out.println("Se ha creado el libro con ID " + id);
	}

	
	/*
	crearLibro
	Descripción: método al que se le pasa por parámetro un objeto de tipo Libro, lo anyade a la lista de libros y actualiza la lista 
				 en el fichero XML de la biblioteca, para ello se prepara el DOM y posteriormente se guarda el DOM en el disco.
	Entradas: objeto libro nuevo a añadir a la lista.
	Salidas: número entero con el identificador del libro anyadido.
	*/
	public static int crearLibro(Libro libro) {
		lista.add(libro);
		escribirXML(lista);
		
		return libro.getId();
	}
	
	
	/*
	escribirXML
	Descripción: método al que se le pasa por parámetro una lista de objetos de tipo Libro y los escribe en el fichero "biblioteca.xml". 
	Entradas: lista de objetos de tipo Libro.
	Salidas: no.
	*/
	public static void escribirXML(ArrayList<Libro> lista) {
		try{
			//Preparar el DOM
			DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
			DocumentBuilder build = dFact.newDocumentBuilder();
			Document doc = build.newDocument();
			Element raiz = doc.createElement("biblioteca");
			doc.appendChild(raiz);
			for (Libro lib : lista) {
	        	Element librox = doc.createElement("libro");
	        	String id = String.valueOf(lib.getId());
	        	librox.setAttribute("id",id);
		        raiz.appendChild(librox);
	            Element titulo = doc.createElement("titulo");
	            titulo.appendChild(doc.createTextNode(String.valueOf(lib.getTitulo())));
	            librox.appendChild(titulo);
	            Element autor = doc.createElement("autor");
	            autor.appendChild(doc.createTextNode(String.valueOf(lib.getAutor())));
	            librox.appendChild(autor);
	            Element anyoPublicacion = doc.createElement("anyo_publicacion");
	            anyoPublicacion.appendChild(doc.createTextNode(String.valueOf(lib.getAnyoPublicacion())));
	            librox.appendChild(anyoPublicacion);
	            Element editorial = doc.createElement("editorial");
	            editorial.appendChild(doc.createTextNode(String.valueOf(lib.getEditorial())));
	            librox.appendChild(editorial);
	            Element numeroPaginas = doc.createElement("numero_paginas");
	            numeroPaginas.appendChild(doc.createTextNode(String.valueOf(lib.getNumeroPaginas())));
	            librox.appendChild(numeroPaginas);
	        }
			
			//Guardar el DOM en el disco
			TransformerFactory tranFactory = TransformerFactory.newInstance();
	        Transformer aTransformer = tranFactory.newTransformer();
	        aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
	        aTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
	        aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        DOMSource source = new DOMSource(doc);
	        FileWriter fw = new FileWriter("biblioteca.xml");
            StreamResult result = new StreamResult(fw);
            aTransformer.transform(source, result);
            fw.close();
            System.out.println("Se ha actualizado la biblioteca");
	        
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/*
	recuperarLibro
	Descripción: método al que se le pasa por parámetro un número entero como identificador del libro, se recorre la lista de libros 
				 para ver cuando coinciden los id, de modo que devuelve el objeto Libro con dicho identificador.
	Entradas: número entero como el identificador del libro.
	Salidas: objeto de tipo Libro con dicho identificador o null en caso de no encontrarlo.
	*/
	public static Libro recuperarLibro(int identificador) {
		Libro libroRecuperado = null;
		for (Libro lib : lista) {
			if (identificador == lib.getId()) {
				libroRecuperado = lib;
				break;
			}	
		}
		return libroRecuperado;
	}
	
	
	/*
	mostrarLibro
	Descripción: método al que se le pasa por parámetro un objeto de tipo Libro y muestra toda su información.
	Entradas: objeto de tipo Libro .
	Salidas: no.
	*/
	public static void mostrarLibro(Libro libro) {
		System.out.print("\nCaracterísticas del libro con id: ");
		System.out.println(libro.getId());
		System.out.print("Título: ");
		System.out.println(libro.getTitulo());
		System.out.print("Autor: ");
		System.out.println(libro.getAutor());
		System.out.print("Anyo de publicacion: ");
		System.out.println(libro.getAnyoPublicacion());
		System.out.print("Editorial: ");
		System.out.println(libro.getEditorial());
		System.out.print("Número de páginas: ");
		System.out.println(libro.getNumeroPaginas());
	}
	
	
	/*
	mostrarTodos
	Descripción: método que muestra para cada libro de la lista el id y título, para ello se recorre la lista de libros de la biblioteca 
				 y se muestra por pantalla los datos.
	Entradas: no.
	Salidas: no.
	*/
	public static void mostrarTodosTitulos() {
		System.out.println("Los libros de la biblioteca son (id y título): ");
		for (Libro lib : lista) {
			System.out.print("Id: " + lib.getId() + " --> título: " + lib.getTitulo()+"\n");
		}
	}
	
	
	/*
	borrarLibro
	Descripción: método al que se le pasa por parámetro un número entero como identificador del libro, lo elimina de la lista y actualiza el fichero "biblioteca.xml".
	Entradas: número entero con el identificador del objeto de tipo Libro.
	Salidas: no
	*/
	public static void borrarLibro(int identificador) {
		Scanner teclado = new Scanner(System.in);
		Libro libro = recuperarLibro(identificador);
		System.out.println(libro.toString());
		int indice = 0;
		for (Libro lib : lista) {
			if (identificador == lib.getId()) {
				lista.remove(indice); //Elimina el elemento que se encuentra en la posición indicada. Devuelve el elemento eliminado.
				break;
			}
			indice++;
		}
		escribirXML(lista);
	}
	
	
	/*
	actualizarLibro
	Descripcion: metodo que, dado el identificador de un libro, actualiza su contenido pidiendo al usuario los nuevos valores
	Entradas: entero con el identificador del libro
	Salidas: no
	*/
	public static void actualizarLibro(int identificador) {
		Scanner teclado = new Scanner(System.in);
		String nuevoDato;
		Libro lib = recuperarLibro(identificador);
		System.out.println(lib.toString());
		System.out.println("Introduce los nuevos valores");
		System.out.print("Modificar título: ");
		nuevoDato = teclado.nextLine();
		lib.setTitulo(nuevoDato);
		System.out.print("Modificar autor: ");
		nuevoDato = teclado.nextLine();
		lib.setAutor(nuevoDato);
		System.out.print("Modificar año de publicación: ");
		nuevoDato = teclado.nextLine();
		lib.setAnyoPublicacion(nuevoDato);
		System.out.print("Modificar editorial: ");
		nuevoDato = teclado.nextLine();
		lib.setEditorial(nuevoDato);
		System.out.print("Modificar número de páginas: ");
		nuevoDato = teclado.nextLine();
		lib.setNumeroPaginas(nuevoDato);
		int indice = 0;
		for (Libro libro : lista) {
			if (libro.getId() == identificador) {
				lista.set(indice,lib); //Sustituye el elemento que se encuentra en la posición indicada por el objeto lib. Devuelve el elemento sustituido.
				break;
			}
			indice++;
		}
		escribirXML(lista);
	}
	
	
	/*
	main
	Descripción: método main, recupera todos los libros a partir del fichero "biblioteca.xml" y los extrae a 
				 la variable lista, posteriormente muestra el menú e interaciona con el usuario que la gestiona. 
	Entradas: no.
	Salidas: no.
	*/
	public static void main(String[] args) {
		lista = recuperarTodos();
		Scanner teclado = new Scanner(System.in);
		int elemento = 0;
		int id;

		while (elemento != 6) {
			System.out.println("\nMenú de la biblioteca, seleccione un elemento: "
					+ "\n1. Mostrar todos los títulos de la biblioteca"
					+ "\n2. Mostrar información detallada de un libro"
					+ "\n3. Crear nuevo libro"
					+ "\n4. Actualizar libro"
					+ "\n5. Borrar libro"
					+ "\n6. Cerrar la biblioteca");
			System.out.print("\n Elemento elegido: ");
			elemento = Integer.parseInt(teclado.next());
			switch (elemento) {
				case 1:
					mostrarTodosTitulos();
					break;
				case 2:
					System.out.print("Indica el identificador del libro a mostrar: ");
					id = Integer.parseInt(teclado.next());// Se recibe el identificador por teclado
					Libro libro = recuperarLibro(id);
					mostrarLibro(libro);
					break;
				case 3:
					pedirDatosLibro();
					break;
				case 4:
					System.out.print("Indica el identificador del libro a modificar: ");
					id = Integer.parseInt(teclado.next());
					actualizarLibro(id);
					break;
				case 5:
					System.out.print("Indica el identificador del libro a borrar: ");
					id = Integer.parseInt(teclado.next());
					borrarLibro(id);
					break;
				case 6:
					System.out.println("Gracias por usar la biblioteca.");
					teclado.close();
					break;
				default:
					break;
			}
		}

	}

}
