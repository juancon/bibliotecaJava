package buffers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Libro {
	public String isbn;
	private String signatura;
	private String titulo;
	private String autor;
	private String materia;
	private String editorial;
	
	public Libro() {
		// TODO Apéndice de constructor generado automáticamente
	}
	
	public Libro(String isbn, String signatura, String titulo, String autor, String materia, String editorial) {
		super();
		this.isbn = isbn;
		this.signatura = signatura;
		this.titulo = titulo;
		this.autor = autor;
		this.materia = materia;
		this.editorial = editorial;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getSignatura() {
		return signatura;
	}

	public void setSignatura(String signatura) {
		this.signatura = signatura;
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

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		result = prime * result + ((editorial == null) ? 0 : editorial.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + ((materia == null) ? 0 : materia.hashCode());
		result = prime * result + ((signatura == null) ? 0 : signatura.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		if (autor == null) {
			if (other.autor != null)
				return false;
		} else if (!autor.equals(other.autor))
			return false;
		if (editorial == null) {
			if (other.editorial != null)
				return false;
		} else if (!editorial.equals(other.editorial))
			return false;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (materia == null) {
			if (other.materia != null)
				return false;
		} else if (!materia.equals(other.materia))
			return false;
		if (signatura == null) {
			if (other.signatura != null)
				return false;
		} else if (!signatura.equals(other.signatura))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ISBN: "+this.isbn+"\n"+"Signatura: "+this.signatura+"\n"+"Título: "+this.titulo+"\n"+"Autor: "+this.autor+"\n"+"Materia: "+this.materia+"\n"+"Editorial: "+this.editorial;
	}
	
	public String pedirIsbn() throws IOException {
		String isbn;
		System.out.println("Introduce el isbn");
		BufferedReader teclado=new BufferedReader(new InputStreamReader(System.in));
		isbn=teclado.readLine();
		this.isbn=isbn;
		return isbn;
	}
	
	public void pedirLibro(String isbn) {
		
		String signatura=PedirDatos.leerCadena("Introduce la signatura");
		String titulo=PedirDatos.leerCadena("Introduce el titulo");
		String autor=PedirDatos.leerCadena("Introduce el autor");
		String materia=PedirDatos.leerCadena("Introduce la materia");
		String editorial=PedirDatos.leerCadena("Inroduce la editorial");
		
		this.isbn=isbn;
		this.materia=materia;
		this.titulo=titulo;
		this.autor=autor;
		this.signatura=signatura;
		this.editorial=editorial;
		
	}
	
	private String addEspacio(String cadena, int longitud) {
		String reg=cadena;
		for(int i=cadena.length();i<longitud;i++) {
			reg+=" ";
		}
		return reg.substring(0, longitud);
	}
	
	public String crearRegistro() {
		String ret=addEspacio(isbn, 16)
				+addEspacio(signatura, 15)
				+addEspacio(titulo, 30)
				+addEspacio(autor, 30)
				+addEspacio(materia, 15)
				+addEspacio(editorial, 30);
		return ret;
	}
	
	public void descomponerRegistro(String registro) {
		this.isbn=registro.substring(0, 16).trim();
		this.signatura=registro.substring(16, 31).trim();
		this.titulo=registro.substring(31, 61).trim();
		this.autor=registro.substring(61, 91).trim();
		this.materia=registro.substring(91, 106).trim();
		this.editorial=registro.substring(106, 136).trim();
	}
}
