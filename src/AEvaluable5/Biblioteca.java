package AEvaluable5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import AEvaluable5.Libro;

public class Biblioteca {
	// Se ha creado un atributo estático global para que los métodos que se implementen tengan acceso a éste.
	static Session session;
	
	/*
	mostrarTodos
	Descripción: método que muestra para cada libro de la lista el id y título, para ello se recorre la lista de libros de la biblioteca 
				 y se muestra por pantalla los datos.
	Entradas: no.
	Salidas: no.
	*/
	public static void mostrarTodosTitulos() {
        session.beginTransaction();
		List listaLibros = new ArrayList();
		listaLibros = session.createQuery("FROM Libro").list();
		System.out.println("Los libros de la biblioteca son (id y título): ");
		for (Object objeto : listaLibros) {
			Libro libro = (Libro) objeto;
			System.out.println("Id: " + libro.getId() + " --> título: " + libro.getTitulo());
		}
		session.getTransaction().commit();
		//Se aplica el clear para que no de Hibernate Error: a different object with the same identifier value was already associated with the session
		session.clear();
	}
	
	/*
	mostrarLibro
	Descripción: método al que se le pasa por parámetro el identificador del objeto Libro y muestra toda su información.
	Entradas: entero con el identificador del libro.
	Salidas: no.
	*/
	public static void mostrarLibro(int id) {
		session.beginTransaction();
		Libro lib = (Libro) session.get(Libro.class, id);
		if (lib == null) {
			System.out.println("No hay libro con id = " + id);
		} else {
			System.out.println(lib.toString());
		}
		session.getTransaction().commit();
		session.clear();
	}
	
	/*
	crearLibro
	Descripción: método que pide información para un nuevo libro, lo crea y lo guarda en la base de datos.
	Entradas: no.
	Salidas: no.
	*/
	public static void crearLibro() {
		session.beginTransaction();
		String titulo, autor, anyo_nac, anyo_pub, editorial, num_pag;
		Scanner teclado = new Scanner(System.in);
		System.out.println("Introduce los datos del nuevo libro: ");
		System.out.print("Título: ");
		titulo = teclado.nextLine();
		System.out.print("Autor: ");
		autor = teclado.nextLine();
		System.out.print("Año de nacimiento: ");
		anyo_nac = teclado.next();
		System.out.print("Año de publicación: ");
		anyo_pub = teclado.next();
		System.out.print("Editorial: ");
		editorial = teclado.nextLine();
		System.out.print("Número de páginas: ");
		num_pag = teclado.next();
		Libro lib = new Libro(titulo, autor, anyo_nac, anyo_pub, editorial, num_pag);
		Serializable id = session.save(lib);
		System.out.println("Se ha creado el libro con ID: " + id);
		session.getTransaction().commit();
		session.clear();
	}
	
	/*
	actualizarLibro
	Descripcion: metodo que, dado el identificador de un libro, actualiza su contenido pidiendo al usuario los nuevos datos
	Entradas: entero con el identificador del libro.
	Salidas: no
	*/
	public static void actualizarLibro(int id) {
		session.beginTransaction();
		Libro lib = (Libro) session.load(Libro.class, id);
		String nuevoTitulo, nuevoAutor, nuevoAnyo_nac, nuevoAnyo_pub, nuevoEditorial, nuevoNum_pag;
		Scanner teclado = new Scanner(System.in);
		System.out.println("Datos actuales del libro: ");
		System.out.println(lib.toString());
		System.out.println("Introduce los nuevos datos (utiliza el caracter '=' para dejarlo igual)");
		System.out.print("Modificar título: "); nuevoTitulo = teclado.nextLine();
		if (!nuevoTitulo.equals("=")) {	lib.setTitulo(nuevoTitulo); }
		System.out.print("Modificar autor: "); nuevoAutor = teclado.nextLine();
		if (!nuevoAutor.equals("=")) {	lib.setAutor(nuevoAutor); }
		System.out.print("Modificar año de nacimiento: "); nuevoAnyo_nac = teclado.next();
		if (!nuevoAnyo_nac.equals("=")) {	lib.setAnyo_nac(nuevoAnyo_nac); }
		System.out.print("Modificar año de publicación: "); nuevoAnyo_pub = teclado.next();
		if (!nuevoAnyo_pub.equals("=")) {	lib.setAnyo_pub(nuevoAnyo_pub); }
		System.out.print("Modificar editorial: "); nuevoEditorial = teclado.nextLine();
		if (!nuevoEditorial.equals("=")) {	lib.setEditorial(nuevoEditorial); }
		System.out.print("Modificar número de páginas: "); nuevoNum_pag = teclado.next();
		if (!nuevoNum_pag.equals("=")) {	lib.setNum_pag(nuevoNum_pag); }
		session.update(lib);
		System.out.println("se ha actualizado el libro con ID: " + id);
		session.getTransaction().commit();
		session.clear();
	}
	
	/*
	borrarLibro
	Descripción: método al que se le pasa por parámetro un número entero como identificador del libro y lo elimina de la base de datos.
	Entradas: número entero con el identificador del objeto de tipo Libro.
	Salidas: no
	*/
	public static void borrarLibro(int id) {
		session.beginTransaction();
		Libro lib = new Libro();
        lib.setId(id);
        session.delete(lib);  
        System.out.println("Borrado libro " + id);
        session.getTransaction().commit();
        session.clear();
	}
	
	/*
	main
	Descripción: método main, muestra el menú e interaciona con el usuario que gestiona la biblioteca. 
	Entradas: no.
	Salidas: no.
	*/
	public static void main(String[] args) {
		
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        configuration.addClass(Libro.class);
        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
        session = sessionFactory.openSession();
        //Se ha inicializado la sesión con el atributo sessión

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
					session.close();
					break;
				default:
					break;
			}
		}
	}

}
