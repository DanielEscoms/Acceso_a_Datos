package AEvaluable2;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador {

	private Modelo modelo;
	private Vista vista;
	private ActionListener actionListenerBuscar, actionListenerReemplazar;
	private String ficheroLectura, ficheroEscritura;
	private String textoBuscar, textoReemplazar;

	public Controlador(Modelo modelo, Vista vista) {
		this.modelo = modelo;
		this.vista = vista;
		control();
	}
	
	public void control() {
		ficheroLectura = modelo.ficheroLectura();
		ficheroEscritura = modelo.ficheroEscritura();
		
		mostrarFichero(ficheroLectura, 1);
		actionListenerBuscar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				textoBuscar = vista.getTextFieldBuscar().getText();
				int contador = modelo.buscarTexto(textoBuscar);
				JFrame jFrame = new JFrame();
		        JOptionPane.showMessageDialog(jFrame, contador);
			}
		};
		vista.getBtnBuscar().addActionListener(actionListenerBuscar);
		
		
		actionListenerReemplazar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				textoBuscar = vista.getTextFieldBuscar().getText();
				textoReemplazar = vista.getTextFieldReemplazar().getText();
				modelo.reemplazarTexto(textoBuscar, textoReemplazar);
				mostrarFichero(ficheroEscritura, 2);
			}
		};
		vista.getBtnReemplazar().addActionListener(actionListenerReemplazar);
		
	}
	
	private void mostrarFichero(String fichero, int numeroTextArea) {
		ArrayList<String> arrayLineas = modelo.contenidoFichero(fichero);
		for (String linea : arrayLineas) {
			if(numeroTextArea == 1) {
				vista.getTextAreaOriginal().append(linea + "\n");
			}else {
				vista.getTextAreaModificado().append(linea + "\n");
			}
		}
	}

}
