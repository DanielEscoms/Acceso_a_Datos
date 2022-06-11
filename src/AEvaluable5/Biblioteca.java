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
	// Se ha creado un atributo est�tico global para que los m�todos que se implementen tengan acceso a �ste.
	static Session session;
	
	/*
	mostrarTodos
	Descripci�n: m�todo que muestra para cada libro de la lista el id y t�tulo, para ello se recorre la lista de libros de la biblioteca 
				 y se muestra por pantalla los datos.
	Entradas: no.
	Salidas: no.
	*/
	public static void mostrarTodosTitulos() {
        session.beginTransaction();
		List listaLibros = new ArrayList();
		listaLibros = session.createQuery("FROM Libro").list();
		System.out.println("Los libros de la biblioteca son (id y t�tulo): ");
		for (Object objeto : listaLibros) {
			Libro libro = (Libro) objeto;
			System.out.println("Id: " + libro.getId() + " --> t�tulo: " + libro.getTitulo());
		}
		session.getTransaction().commit();
		//Se aplica el clear para que no de Hibernate Error: a different object with the same identifier value was already associated with the session
		session.clear();
	}
	
	/*
	mostrarLibro
	Descripci�n: m�todo al que se le pasa por par�metro el identificador del objeto Libro y muestra toda su informaci�n.
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
	Descripci�n: m�todo que pide informaci�n para un nuevo libro, lo crea y lo guarda en la base de datos.
	Entradas: no.
	Salidas: no.
	*/
	public static void crearLibro() {
		session.beginTransaction();
		String titulo, autor, anyo_nac, anyo_pub, editorial, num_pag;
		Scanner teclado = new Scanner(System.in);
		System.out.println("Introduce los datos del nuevo libro: ");
		System.out.print("T�tulo: ");
		titulo = teclado.nextLine();
		System.out.print("Autor: ");
		autor = teclado.nextLine();
		System.out.print("A�o de nacimiento: ");
		anyo_nac = teclado.next();
		System.out.print("A�o de publicaci�n: ");
		anyo_pub = teclado.next();
		System.out.print("Editorial: ");
		editorial = teclado.nextLine();
		System.out.print("N�mero de p�ginas: ");
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
		System.out.print("Modificar t�tulo: "); nuevoTitulo = teclado.nextLine();
		if (!nuevoTitulo.equals("=")) {	lib.setTitulo(nuevoTitulo); }
		System.out.print("Modificar autor: "); nuevoAutor = teclado.nextLine();
		if (!nuevoAutor.equals("=")) {	lib.setAutor(nuevoAutor); }
		System.out.print("Modificar a�o de nacimiento: "); nuevoAnyo_nac = teclado.next();
		if (!nuevoAnyo_nac.equals("=")) {	lib.setAnyo_nac(nuevoAnyo_nac); }
		System.out.print("Modificar a�o de publicaci�n: "); nuevoAnyo_pub = teclado.next();
		if (!nuevoAnyo_pub.equals("=")) {	lib.setAnyo_pub(nuevoAnyo_pub); }
		System.out.print("Modificar editorial: "); nuevoEditorial = teclado.nextLine();
		if (!nuevoEditorial.equals("=")) {	lib.setEditorial(nuevoEditorial); }
		System.out.print("Modificar n�mero de p�ginas: "); nuevoNum_pag = teclado.next();
		if (!nuevoNum_pag.equals("=")) {	lib.setNum_pag(nuevoNum_pag); }
		session.update(lib);
		System.out.println("se ha actualizado el libro con ID: " + id);
		session.getTransaction().commit();
		session.clear();
	}
	
	/*
	borrarLibro
	Descripci�n: m�todo al que se le pasa por par�metro un n�mero entero como identificador del libro y lo elimina de la base de datos.
	Entradas: n�mero entero con el identificador del objeto de tipo Libro.
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
	Descripci�n: m�todo main, muestra el men� e interaciona con el usuario que gestiona la biblioteca. 
	Entradas: no.
	Salidas: no.
	*/
	public static void main(String[] args) {
		
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        configuration.addClass(Libro.class);
        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
        session = sessionFactory.openSession();
        //Se ha inicializado la sesi�n con el atributo sessi�n

        Scanner teclado = new Scanner(System.in);
		int elemento = 0;
		int id;

		while (elemento != 6) {
			System.out.println("\nMen� de la biblioteca, seleccione un elemento: "
					+ "\n1. Mostrar todos los t�tulos de la biblioteca"
					+ "\n2. Mostrar informaci�n detallada de un libro"
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
