package AEvaluable5;

public class Libro {
	
	int id;
	String titulo, autor, anyo_nac, anyo_pub, editorial, num_pag;
	
	public Libro() {
		super();
	}
	
	public Libro(String titulo, String autor, String anyo_nac, String anyo_pub, String editorial, String num_pag) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.anyo_nac = anyo_nac;
		this.anyo_pub = anyo_pub;
		this.editorial = editorial;
		this.num_pag = num_pag;
	}
	
	public Libro(int id, String titulo, String autor, String anyo_nac, String anyo_pub, String editorial,
			String num_pag) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.anyo_nac = anyo_nac;
		this.anyo_pub = anyo_pub;
		this.editorial = editorial;
		this.num_pag = num_pag;
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
	
	public String getAnyo_nac() {
		return anyo_nac;
	}
	
	public void setAnyo_nac(String anyo_nac) {
		this.anyo_nac = anyo_nac;
	}
	public String getAnyo_pub() {
		return anyo_pub;
	}
	
	public void setAnyo_pub(String anyo_pub) {
		this.anyo_pub = anyo_pub;
	}
	
	public String getEditorial() {
		return editorial;
	}
	
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	
	public String getNum_pag() {
		return num_pag;
	}
	
	public void setNum_pag(String num_pag) {
		this.num_pag = num_pag;
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", anyo_nac=" + anyo_nac + ", anyo_pub="
				+ anyo_pub + ", editorial=" + editorial + ", num_pag=" + num_pag + "]";
	}
	
}