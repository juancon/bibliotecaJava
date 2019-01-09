package buffers;


public class Usuario {
	private String codUsuario;
	private String nombre;
	private String apellido1;
	private String apellido2;
	
	public Usuario() {
		// TODO Apéndice de constructor generado automáticamente
	}
	
	public Usuario(String codUsuario, String nombre, String apellido1, String apellido2) {
		super();
		this.codUsuario = codUsuario;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
	}

	public String getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	
	@Override
	public String toString() {
		return "Codigo de usuario: "+this.codUsuario+"\n"+"Nombre: "+this.nombre+"\n"+"Primer apellido: "+this.apellido1+"\n"+"Segundo apellido: "+this.apellido2;
	}
	
	public String pedirCodusuario() {
		String codUsuario=PedirDatos.leerCadena("Introduce el codigo del usuario");
		return codUsuario;
	}
	
	public void pedirUsuario(String codUsuario) {
		String nombre=PedirDatos.leerCadena("Introduce el nombre del usuario");
		String apellido1=PedirDatos.leerCadena("Introduce el primer apellido del usuario");
		String apellido2=PedirDatos.leerCadena("Introduce el segundo apellido del usuario");
		
		this.codUsuario=codUsuario;
		this.nombre=nombre;
		this.apellido1=apellido1;
		this.apellido2=apellido2;
	}
	
	private String addEspacio(String cadena, int longitud) {
		String ret=cadena;
		for(int i=cadena.length();i<longitud;i++) {
			ret+=" ";
		}
		return ret;
	}
	
	public String crearRegistro() {
		String ret=addEspacio(this.codUsuario, 4)
				+addEspacio(this.nombre, 10)
				+addEspacio(this.apellido1, 10)
				+addEspacio(this.apellido2, 10);
		return ret;
	}
	
	public void descomponerRegistro(String registro) {
		this.codUsuario=registro.substring(0, 4).trim();
		this.nombre=registro.substring(4, 14).trim();
		this.apellido1=registro.substring(14, 24).trim();
		this.apellido2=registro.substring(24, 34).trim();
	}
}
