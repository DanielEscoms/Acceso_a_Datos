package examen1ev;

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
		
		Class.forName("com.mysql.cj.jdbc.Driver");  
		Connection con = DriverManager.getConnection(url,user,password);  
		
		Scanner teclado = new Scanner(System.in);
		System.out.print("Indicar el precio maximo: ");
		String precioMaximo = teclado.nextLine();
		
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
		File ficheroEscritura = new File("consulta_" + timeStamp + ".txt");
		FileWriter fw = new FileWriter(ficheroEscritura);
		fw.write("nombre  precio\n");
		fw.write("======  ======\n");
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT nombre, precio FROM precios WHERE precio <= " + precioMaximo); 
		System.out.println("nombre  precio");
		System.out.println("======  ======");
		while(rs.next()) {
			System.out.println(rs.getString(1) + "   " + rs.getString(2));
			fw.write(rs.getString(1) + "   " + rs.getString(2) + "\n");
		}
		fw.close();
		rs.close();
		stmt.close();
		con.close();
		teclado.close();
		
	}

}
