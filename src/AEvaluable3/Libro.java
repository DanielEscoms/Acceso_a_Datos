package AEvaluable3;

public class Libro {
	
	private int id;
	private String titulo;
	private String autor;
	private String anyoPublicacion;
	private String editorial;
	private String numeroPaginas;
	
	Libro() {}
	
	Libro(int id, String titulo, String autor, String anyoPublicacion, String editorial, String numeroPaginas) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.anyoPublicacion = anyoPublicacion;
		this.editorial = editorial;
		this.numeroPaginas = numeroPaginas;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getAnyoPublicacion() {
		return anyoPublicacion;
	}
	public void setAnyoPublicacion(String anyoPublicacion) {
		this.anyoPublicacion = anyoPublicacion;
	}
	public String getEditorial() {
		return editorial;
	}
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	public String getNumeroPaginas() {
		return numeroPaginas;
	}
	public void setNumeroPaginas(String numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}
	
	public String toString() {
		return "Información libro: (id: "+id+"; titulo: "+titulo+"; autor: "+autor+"; anyo publicación: "+anyoPublicacion+
				"; editorial: "+editorial+"; número de páginas: "+numeroPaginas+")";
	}
}
