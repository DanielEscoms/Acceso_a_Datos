package AEvaluable2Solucion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Modelo {
	
	private String fichero_lectura; 
	private String fichero_escritura;
	
	public Modelo() {
		fichero_lectura = "AE02_T1_2_Streams_Groucho.txt";
		fichero_escritura = "AE02_T1_2_Streams_Groucho_2.txt";
	}
	
	public ArrayList<String> contenidoFichero(String fichero) {
		ArrayList<String> contenidoFichero = new ArrayList<String>();
		File f = new File(fichero);
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String linea = br.readLine();
			while (linea != null) {
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
	
	public String ficheroEscritura() {
		return fichero_escritura;
	}
	
	public String ficheroLectura() {
		return fichero_lectura;
	}
	
	public int buscarTexto(String textoBuscar) {
		int coincidencias = 0;
		File f1 = new File(fichero_lectura);
		try {
			FileReader fr = new FileReader(f1);
			BufferedReader br = new BufferedReader(fr);
			String linea = br.readLine();
			int indice = -1;
			while (linea != null) {
				indice = linea.indexOf(textoBuscar);
				while (indice >= 0) {
					coincidencias++;
					indice = linea.indexOf(textoBuscar, (indice + 1));
				}
				linea = br.readLine();
			}
			br.close();
			fr.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return coincidencias;
	}
	
	public void reemplazarTexto(String textoBuscar, String textoReemplazar) {
		File f1 = new File(ficheroLectura());
		File f2 = new File(ficheroEscritura());
		try {
			FileReader fr = new FileReader(f1);
			BufferedReader br = new BufferedReader(fr);
			FileWriter fw = new FileWriter(f2);
			BufferedWriter bw = new BufferedWriter(fw);
			String linea = br.readLine();
			String lineaNueva;
			while (linea != null) {
				lineaNueva = linea.replaceAll(textoBuscar, textoReemplazar);
				bw.write(lineaNueva);
				bw.newLine();
				linea = br.readLine();
			}
			br.close();
			fr.close();
			bw.close();
			fw.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
		
}
