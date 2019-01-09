
public class Usuario {
	private String codUsuario;
	private String nombre;
	private String apellido1;
	private String apellido2;
	
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
	
	
}
