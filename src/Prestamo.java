
public class Prestamo {
	private String codUsuario;
	private char tipomaterial;
	private String codMaterial;
	private String fechaprestamo;
	private String fechadevolucion;
	public Prestamo(String codUsuario, char tipomaterial, String codMaterial, String fechaprestamo) {
		super();
		this.codUsuario = codUsuario;
		this.tipomaterial = tipomaterial;
		this.codMaterial = codMaterial;
		this.fechaprestamo = fechaprestamo;
	}
	
	public String getCodUsuario() {
		return codUsuario;
	}



	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}



	public char getTipomaterial() {
		return tipomaterial;
	}



	public void setTipomaterial(char tipomaterial) {
		this.tipomaterial = tipomaterial;
	}



	public String getCodMaterial() {
		return codMaterial;
	}



	public void setCodMaterial(String codMaterial) {
		this.codMaterial = codMaterial;
	}



	public String getFechaprestamo() {
		return fechaprestamo;
	}



	public void setFechaprestamo(String fechaprestamo) {
		this.fechaprestamo = fechaprestamo;
	}



	public String getFechadevolucion() {
		return fechadevolucion;
	}



	@Override
	public String toString() {
		return "Prestamo realizado al usuario " + codUsuario + "\ntipomaterial=" + tipomaterial + "\ncodMaterial=" + codMaterial
				+ "\nfechaprestamo=" + fechaprestamo + "\nfechadevolucion=" + fechadevolucion;
	}
	public void setFechadevolucion(String fechadevolucion) {
		this.fechadevolucion = fechadevolucion;
	}
	
	
	
}