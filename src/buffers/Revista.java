package buffers;

public class Revista {
	private String codRevista;
	private String signatura;
	private String nombre;
	private String materia;
	//private GestionArticulos ga=new GestionArticulos();
	
	public Revista() {
		// TODO Apéndice de constructor generado automáticamente
	}
	
	public Revista(String codRevista, String sigantura, String nombre, String materia) {
		super();
		this.codRevista = codRevista;
		this.signatura = sigantura;
		this.nombre = nombre;
		this.materia = materia;
	}
	
	
	
	/*public void setMenuArticulo() {
		ga.menu();
	}*/
	
	public String getCodRevista() {
		return codRevista;
	}

	public void setCodRevista(String codRevista) {
		this.codRevista = codRevista;
	}

	public String getSigantura() {
		return signatura;
	}

	public void setSigantura(String sigantura) {
		this.signatura = sigantura;
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
		return "Codigo revista: "+this.codRevista+"\n"+"Signatura: "+this.signatura+"\n"+"Nombre: "+this.nombre+"\n"+"Materia: "+this.materia;
	}
	
	public String pedirCodrevista() {
		String codRevista="";
		codRevista=PedirDatos.leerCadena("Introduce el codigo de la revista");
		this.codRevista=codRevista;
		return codRevista;
	}
	
	public void pedirRevista(String codRevista) {
		
		//String codRevista=PedirDatos.leerCadena("Introduce el codigo de la revista");
		String signatura="";
		signatura=PedirDatos.leerCadena("Introduce la signatura de la revista");
		String nombre="";
		nombre=PedirDatos.leerCadena("Introduce el nombre de la revista");
		String materia="";
		materia=PedirDatos.leerCadena("Introduce la materia de la revista");
		
		this.codRevista=codRevista;
		this.signatura=signatura;
		this.nombre=nombre;
		this.materia=materia;
	}
	
	private String addEspacio(String cadena, int longitud) {
		String reg=cadena;
		for(int i=cadena.length();i<longitud;i++) {
			reg=reg+" ";
		}
		return reg.substring(0, longitud);
	}
	
	public String crearRegistro() {
		String ret=addEspacio(this.codRevista, 4)
				+addEspacio(this.signatura, 15)
				+addEspacio(this.nombre, 30)
				+addEspacio(this.materia, 15);
		return ret;
	}
	
	public void descomponerRegistro(String registro) {
		this.codRevista=registro.substring(0, 4).trim();
		this.signatura=registro.substring(4, 19).trim();
		this.nombre=registro.substring(19, 49).trim();
		this.materia=registro.substring(49, 64).trim();
		
	}
	
}
