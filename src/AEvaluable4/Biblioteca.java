package AEvaluable4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Biblioteca {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // Se carga el driver.
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca_aev04","root","");
			// Se crea la conexión a la Base de Datos que está en la URL correspondiente.
			System.out.println("Conexion correcta");
			
			String titulo, autor, anyo_nac, anyo_pub, editorial, num_pag;
			
			File fichero = new File("AE04_T1_4_JDBC_Datos.csv");
			FileReader fr = new FileReader(fichero);
			BufferedReader br = new BufferedReader(fr);
			String linea = br.readLine(); // Leemos la primera linea que son los nombres de las columnas del excel para empezar el bucle while leyendo datos de libros.
			
			while((linea = br.readLine()) != null) {
				String[] datosLinea = linea.split(";");
				for (int i = 0; i < datosLinea.length; i++) { //Con este bucle se rellenan los campos que estan bacíos escribiendo N.C. (no consta)
					if (datosLinea[i].equals("")) {
						datosLinea[i] = "N.C.";
					}
				}
				
				titulo = datosLinea[0]; // se asignan los datos a las variables para tener una vision mas clara de que dato es cada uno.
				autor = datosLinea[1];
				anyo_nac = datosLinea[2];
				anyo_pub = datosLinea[3];
				editorial = datosLinea[4];
				num_pag = datosLinea[5];
				
				PreparedStatement ps = con.prepareStatement("INSERT INTO libros (titulo,autor,anyo_nac,anyo_pub,editorial,num_pag) "
						+ "VALUES (?,?,?,?,?,?)");
				// preparamos la sentencia de introducir los datos de cada linea en la tabla libros y asignamos los valores a continuación.
				ps.setString(1, titulo);
				ps.setString(2, autor);
				ps.setString(3, anyo_nac);
				ps.setString(4, anyo_pub);
				ps.setString(5, editorial);
				ps.setString(6, num_pag);
				
				int resultadoInsertar = ps.executeUpdate(); // Aquí se lanza la sentencia definitiva.
				if (resultadoInsertar == 1) {
					System.out.println("Datos insertados correctamente");
					System.out.println(ps.toString()); // mostramos la sentencia y los datos insertados.
				}
				ps.close();
				
				
			}
			br.close();
			fr.close();
			
			// Código primera consulta.
			Statement stmt = con.createStatement(); // Se crea la sentencia.
			System.out.println("\nConsulta de Libros (título, autor y año de publicación) de los autores nacidos antes de 1950.\n");
			ResultSet rs = stmt.executeQuery("SELECT titulo,autor,anyo_pub FROM libros WHERE anyo_nac < 1950"); // Se ejecuta la sentencia.
			
			System.out.format("%35s%23s%10s%2s","titulo","autor","anyo_pub","\n"); // Se saca por consola el contenido aplicando el formato adecuado para 3 columna.
			System.out.format("%35s%23s%10s%2s","======","=====","========","\n");
			
			while(rs.next()) { // Se recorren cada una de las líneas del resultado de la consulta.
				System.out.format("%35s%23s%10s%2s",rs.getString(1),rs.getString(2),rs.getString(3),"\n");
			}
			rs.close();
			stmt.close();
			// Fin código primera consulta.
			
			// Código segunda consulta.
			stmt = con.createStatement(); // Se crea la sentencia.
			System.out.println("\nConsulta de Editoriales que hayan publicado al menos un libro en el siglo XXI.\n");
			rs = stmt.executeQuery("SELECT editorial FROM libros WHERE anyo_pub > 2000"); // Se ejecuta la sentencia.
			
			System.out.println("editorial\n========="); // Se saca por consola el contenido aplicando el formato adecuado para 1 columna.
			
			while(rs.next()) { // Se recorren cada una de las líneas del resultado de la consulta.
				System.out.println(rs.getString(1));
			}
			rs.close();
			stmt.close();
			// Fin código segunda consulta.
			
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
