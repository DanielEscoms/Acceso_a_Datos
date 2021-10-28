package AEvaluable2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Modelo {

	private String fichero_lectura;
	private String fichero_escritura;

	public Modelo() {
		fichero_lectura="AE02_T1_2_Streams_Groucho.txt";
		fichero_escritura= "ficheroEscritura.txt";
	}
	
	public ArrayList<String> contenidoFichero(String fichero) {
		ArrayList<String> contenidoFichero = new ArrayList<String>();
		File f = new File(fichero);
		try {
			FileReader fr =new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String linea = br.readLine();
			while(linea != null) {
				contenidoFichero.add(linea);
				linea = br.readLine();
			}
			br.close();
			fr.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);	
		}
		return contenidoFichero;
	}
}
