
public class Cdrom {
	private String codCdrom;
	private String signatura;
	private String titulo;
	private String autor;
	private String materia;
	private String editorial;
	
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
	
	
}
