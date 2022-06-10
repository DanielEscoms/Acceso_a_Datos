package examen1evRecu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class App {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, ClassNotFoundException, SQLException {
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document document = dBuilder.parse(new File("config.xml"));
		NodeList nodeList = document.getElementsByTagName("config1");
		Node node = nodeList.item(0);
		Element eElement = (Element) node;
		String url = eElement.getElementsByTagName("url").item(0).getTextContent();
		String user = eElement.getElementsByTagName("user").item(0).getTextContent();
		String password = eElement.getElementsByTagName("password").item(0).getTextContent();
		
		Class.forName("com.mysql.cj.jdbc.Driver");// Se carga el driver.
		Connection con = DriverManager.getConnection(url,user,password);
		// Se crea la conexión a la Base de Datos que está en la URL correspondiente.
		
		
		
		// Código primera consulta.
		Statement stmt = con.createStatement(); // Se crea la sentencia.
		System.out.println("\nConsulta de Precios (id y nombre) de de todos los destinos.\n");
		ResultSet rs = stmt.executeQuery("SELECT id, nombre FROM precios"); // Se ejecuta la sentencia.
		
		System.out.format("%10s%15s%2s"," id "," nombre ","\n"); // Se saca por consola el contenido aplicando el formato adecuado para 2 columnas.
		System.out.format("%10s%15s%2s","====","========","\n");
		
		while(rs.next()) { // Se recorren cada una de las líneas del resultado de la consulta.
			System.out.format("%10s%15s%2s",rs.getString(1),rs.getString(2),"\n");
		}
		rs.close();
		stmt.close();
		// Fin código primera consulta.
		
		Scanner teclado = new Scanner(System.in);
		System.out.print("Indicar el id del destino a seleccionar: ");
		String idDestino = teclado.nextLine();
		
		// Código segunda consulta.
		System.out.println("\nConsulta de Precios (id y nombre) de de todos los destinos.\n");
		
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
		File ficheroEscritura = new File("consulta_" + timeStamp + ".txt");
		FileWriter fw = new FileWriter(ficheroEscritura);
		fw.write("nombre  precio\n");
		fw.write("======  ======\n");
		
		Statement stmt2 = con.createStatement();
		ResultSet rs2 = stmt2.executeQuery("SELECT nombre, precio FROM precios WHERE id = " + idDestino);
		
		while(rs2.next()) {
			System.out.println("Nombre: " + rs2.getString(1) + " --> Precio: " + rs2.getString(2));
			fw.write(rs2.getString(1) + "   " + rs2.getString(2) + "\n");
		}
		fw.close();
		rs.close();
		stmt.close();
		// Fin código segunda consulta.
		
		con.close();
		teclado.close();
	}
}
