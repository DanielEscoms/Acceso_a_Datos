package AEvaluable2;

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
		fichero_escritura = "ficheroEscritura.txt";
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

	public String ficheroLectura() {
		return fichero_lectura;
	}

	public String ficheroEscritura() {
		return fichero_escritura;
	}

	public int buscarTexto(String textoBuscar) {
		int contador = 0;
		File f = new File(ficheroLectura());
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String linea = br.readLine();
			while (linea != null) {
				while (linea.indexOf(textoBuscar) > -1) {
					linea = linea.substring(linea.indexOf(textoBuscar) + textoBuscar.length(), linea.length());
					contador++;
				}
				linea = br.readLine();
			}
			br.close();
			fr.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return contador;
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
			while(linea != null) {
				linea = linea.replaceAll(textoBuscar, textoReemplazar);
				bw.write(linea);
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
