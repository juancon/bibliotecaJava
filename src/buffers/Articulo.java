package buffers;

public class Articulo {
	private String codRevista;
	private String codArticulo;
	private String titulo;
	private String autor;
	private String numPaginas;
	
	public Articulo() {
		// TODO Apéndice de constructor generado automáticamente
	}
	
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
	
	public String getCodRevista() {
		return codRevista;
	}

	public void setCodRevista(String codRevista) {
		this.codRevista = codRevista;
	}

	@Override
	public String toString() {
		return "Codigo de revista: "+this.codRevista+" - "+"Codigo de articulo: "+this.codArticulo+"\n"+"Titulo: "+this.titulo+"\n"+"Autor: "+this.autor+"\n"+"Numero de paginas: "+this.numPaginas;
	}
	
	public String PedirCodarticulo() {
		String codArticulo=PedirDatos.leerCadena("Introduzca el codigo del articulo");
		this.codArticulo=codArticulo;
		return codArticulo;
	}
	
	public void pedirArticulo(String codArticulo, String codRevista) {
		String titulo=PedirDatos.leerCadena("Introduce el titulo del articulo");
		String autor=PedirDatos.leerCadena("Introduce el autor del articulo");
		String numPaginas=PedirDatos.leerCadena("Introduce el numero de paginas del articulo");
		
		this.codArticulo=codArticulo;
		this.codRevista=codRevista;
		this.titulo=titulo;
		this.autor=autor;
		this.numPaginas=numPaginas;
	}
	
	private String addEspacio(String cadena, int longitud) {
		String ret=cadena;
		for(int i=cadena.length();i<longitud;i++) {
			ret+=" ";
		}
		return ret.substring(0, longitud);
	}
	
	public String crearRegistro() {
		String ret=addEspacio(this.codRevista, 4)
				+addEspacio(this.codArticulo, 4)
				+addEspacio(this.titulo, 15)
				+addEspacio(this.autor, 15)
				+addEspacio(this.numPaginas, 3);
		return ret;
	}
	
	public void descomponerRegistro(String registro) {
		this.codRevista=registro.substring(0, 4).trim();
		this.codArticulo=registro.substring(4, 8).trim();
		this.titulo=registro.substring(8, 23).trim();
		this.autor=registro.substring(23, 38).trim();
		this.numPaginas=registro.substring(38, 41).trim();
	}
	
	public String buscarRevistaEnRegistro(String registro) {
		return registro.substring(0,5);
	}
	
}
