package AEvaluable6;

import java.util.Scanner;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;

// Se han importado todas las librerías vistas en los apuntes de clase pese a que aún no se usen, ya que cuando se implementen los métodos se utilizarán.

public class Biblioteca {
	// Se ha creado un atributo estático global para que los métodos que se implementen tengan acceso a éste.
	static MongoCollection<Document> coleccion;
	
	/*
	mostrarTodos
	Descripción: método que muestra para cada libro de la lista el id y título, para ello se recorre la lista de libros de la biblioteca 
				 y se muestra por pantalla los datos.
	Entradas: no.
	Salidas: no.
	*/
	public static void mostrarTodosTitulos() {
        
	}
	
	/*
	mostrarLibro
	Descripción: método al que se le pasa por parámetro el identificador del objeto Libro y muestra toda su información.
	Entradas: entero con el identificador del libro.
	Salidas: no.
	*/
	public static void mostrarLibro(int id) {
		
	}
	
	/*
	crearLibro
	Descripción: método que pide información para un nuevo libro, lo crea y lo guarda en la base de datos.
	Entradas: no.
	Salidas: no.
	*/
	public static void crearLibro() {
		
	}
	
	/*
	actualizarLibro
	Descripcion: metodo que, dado el identificador de un libro, actualiza su contenido pidiendo al usuario los nuevos datos
	Entradas: entero con el identificador del libro.
	Salidas: no
	*/
	public static void actualizarLibro(int id) {
		
	}
	
	/*
	borrarLibro
	Descripción: método al que se le pasa por parámetro un número entero como identificador del libro y lo elimina de la base de datos.
	Entradas: número entero con el identificador del objeto de tipo Libro.
	Salidas: no
	*/
	public static void borrarLibro(int id) {
		
	}
	
	/*
	main
	Descripción: método main, muestra el menú e interaciona con el usuario que gestiona la biblioteca. 
	Entradas: no.
	Salidas: no.
	*/
	public static void main(String[] args) {
		
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("Biblioteca");
		coleccion = database.getCollection("Libros");
        //Se ha realizado la conexión con el atributo coleccion
		
		try {
			/* Se asigna una pausa de 0,2s para que no se solapen las escrituras 
			de la conexión con la base de datos con el menú de la biblioteca.*/
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
					mostrarLibro(id);
					break;
				case 3:
					crearLibro();
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
					mongoClient.close();
					break;
				default:
					break;
			}
		}
	}

}
