
public class Revista {
	private String codRevista;
	private String sigantura;
	private String nombre;
	private String materia;
	private GestionArticulos ga=new GestionArticulos();
	
	public Revista(String codRevista, String sigantura, String nombre, String materia) {
		super();
		this.codRevista = codRevista;
		this.sigantura = sigantura;
		this.nombre = nombre;
		this.materia = materia;
	}
	
	
	
	public void setMenuArticulo() {
		ga.menu();
	}
	
	public String getCodRevista() {
		return codRevista;
	}

	public void setCodRevista(String codRevista) {
		this.codRevista = codRevista;
	}

	public String getSigantura() {
		return sigantura;
	}

	public void setSigantura(String sigantura) {
		this.sigantura = sigantura;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	@Override
	public String toString() {
		return "Codigo revista: "+this.codRevista+"\n"+"Signatura: "+this.sigantura+"\n"+"Nombre: "+this.nombre+"\n"+"Materia: "+this.materia;
	}
	
}
