package buffers;

public class Prestamo {
	private String codUsuario;
	private char tipomaterial;
	private String codMaterial;
	private String fechaprestamo;
	private String fechadevolucion;
	
	public Prestamo() {
		
	}
	
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
		String ret= "Prestamo realizado al usuario " + codUsuario + "\ntipomaterial=" + tipomaterial + "\ncodMaterial=" + codMaterial
				+ "\nfechaprestamo=" + fechaprestamo;
		if(fechadevolucion!=null) {
			ret=ret+"\nfechadevolucion=" + fechadevolucion;
		}
		return ret;
	}
	public void setFechadevolucion(String fechadevolucion) {
		this.fechadevolucion = fechadevolucion;
	}
	
	private String addEspacio(String cadena, int longitud) {
		String ret=cadena;
		for(int i=cadena.length();i<longitud;i++) {
			ret+=" ";
		}
		return ret.substring(0, longitud);
	}
	
	public String componerRegistro() {
		String ret=addEspacio(codUsuario, 4)+
				addEspacio(this.tipomaterial+"", 1)+
				addEspacio(codMaterial, 16)+
				addEspacio(fechaprestamo, 10);
		if(fechadevolucion!=null) {
			ret+=addEspacio(fechadevolucion, 10);
		}
		
		return ret;
	}
	
	public void descomponerRegistro(String registro) {
		this.codUsuario=registro.substring(0, 4).trim();
		this.tipomaterial=registro.substring(4, 5).charAt(0);
		this.codMaterial=registro.substring(5, 21).trim();
		this.fechaprestamo=registro.substring(21, 31).trim();
	}
	
	public String getFechaDevolucion(String registro) {
		try {
			return registro.substring(31,41);
		} catch (StringIndexOutOfBoundsException e) {
			return null;
		}
	}
}
