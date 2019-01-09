package buffers;

public class Cdrom {
	private String codCdrom;
	private String signatura;
	private String titulo;
	private String autor;
	private String materia;
	private String editorial;
	
	public Cdrom() {
		
	}
	
	public Cdrom(String codCdrom, String signatura, String titulo, String autor, String materia, String editorial) {
		super();
		this.codCdrom = codCdrom;
		this.signatura = signatura;
		this.titulo = titulo;
		this.autor = autor;
		this.materia = materia;
		this.editorial = editorial;
	}
	
	public String getCodCdrom() {
		return codCdrom;
	}
	public void setCodCdrom(String codCdrom) {
		this.codCdrom = codCdrom;
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
	public String toString() {
		return "Codigo Cdrom: "+this.codCdrom+"\n"+"Signatira: "+this.signatura+"\n"+"Titulo: "+this.titulo+"\n"+"Autor: "+this.autor+"\n"+"Materia: "+this.materia+"\n"+"Editorial: "+this.editorial;
	}
	
	public String pedirCodCdrom() {
		String codCdrom=PedirDatos.leerCadena("Introduce el codigo del cdrom");
		//this.codCdrom=codCdrom;
		return codCdrom;
	}
	
	public void pedirCdrom(String codCdrom) {
		this.codCdrom=codCdrom;
		
		this.signatura=PedirDatos.leerCadena("Introduce la signatura");
		this.titulo=PedirDatos.leerCadena("Introduce el titulo");
		this.autor=PedirDatos.leerCadena("Introduce el autor");
		this.materia=PedirDatos.leerCadena("Introduce la materia");
		this.editorial=PedirDatos.leerCadena("Introduce la editorial");
		
		//this.codCdrom=codCdrom;
		/*this.signatura=signatura;
		this.titulo=titulo;
		this.autor=autor;
		this.materia=materia;
		this.editorial=editorial;*/
	}
	
	private String addEspacio(String cadena, int longitud) {
		String reg=cadena;
		for(int i=cadena.length();i<longitud;i++) {
			reg=reg+" ";
		}
		return reg.substring(0, longitud);
	}
	
	public String crearRegistro() {
		String ret=addEspacio(this.codCdrom, 4)
				+addEspacio(this.signatura, 11)
				+addEspacio(this.titulo, 30)
				+addEspacio(this.autor, 30)
				+addEspacio(this.materia, 15)
				+addEspacio(this.editorial, 30);
		return ret;
	}
	
	public void descomponerRegistro(String registro) {
		this.codCdrom=registro.substring(0, 4);
		this.signatura=registro.substring(4, 15);
		this.titulo=registro.substring(15, 45);
		this.autor=registro.substring(45, 75);
		this.materia=registro.substring(75, 90);
		this.editorial=registro.substring(90, 120);
		
	}
	
}
