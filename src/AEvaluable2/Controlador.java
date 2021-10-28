package AEvaluable2;

import javax.swing.event.AncestorListener;

public class Controlador {

	private Modelo modelo;
	private Vista vista;
	private AncestorListener actionListenesBuscar, actionListenerReemplazar;
	private String ficheroLectura, ficheroEscritura;
	private String textoBuscar, textoReemplazar;

	public Controlador(Modelo modelo, Vista vista) {
		this.modelo = modelo;
		this.vista = vista;
		control();
	}
	
	public void control() {
		
	}

}
