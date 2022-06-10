package AEvaluable5;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Biblioteca {
	// Se ha creado un atributo est�tico global para que los m�todos que se implementen tengan acceso a �ste.
	static Session session;
	
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
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
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
