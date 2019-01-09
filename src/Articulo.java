
public class Articulo {
	private String codArticulo;
	private String titulo;
	private String autor;
	private String numPaginas;
	
	public Articulo(String codArticulo, String titulo, String autor, String numPaginas) {
		super();
		this.codArticulo = codArticulo;
		this.titulo = titulo;
		this.autor = autor;
		this.numPaginas = numPaginas;
	}
	public String getCodArticulo() {
		return codArticulo;
	}
	public void setCodArticulo(String codArticulo) {
		this.codArticulo = codArticulo;
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
	public String getNumPaginas() {
		return numPaginas;
	}
	public void setNumPaginas(String numPaginas) {
		this.numPaginas = numPaginas;
	}
	@Override
	public String toString() {
		return "Codigo de articulo: "+this.codArticulo+"\n"+"Titulo: "+this.titulo+"\n"+"Autor: "+this.autor+"\n"+"Numero de paginas: "+this.numPaginas;
	}
	
	
}
