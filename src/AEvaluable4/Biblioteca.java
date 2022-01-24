package AEvaluable4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
