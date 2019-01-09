import java.util.Vector;

public class Biblioteca {
	private GestionLibros gl=new GestionLibros();
	private GestionUsuarios gu= new GestionUsuarios();
	private GestionRevistas gr=new GestionRevistas();
	private GestionCdrom gc=new GestionCdrom();
	
	/*private final int TAM=1000;
	private Prestamo[] prestamos=new Prestamo[TAM];
	private int posicion=0;*/
	Vector<Prestamo> prestamos=new Vector<Prestamo>();
	
	public void menu() {
		int opcion=0;
		do {
			System.out.println("0. Salir");
			System.out.println("1. Gestion de Libro");
			System.out.println("2. Gestion de Revistas");
			System.out.println("3. Gestion de CDROM");
			System.out.println("4. Gestion de Usuario");
			System.out.println("5. Realizar Prestamo");
			System.out.println("6. Devolver Prestamo");
			opcion=PedirDatos.leerEntero("Introduzca una opcion");
			switch (opcion) {
			case 0:
				System.out.println("Adios");
				break;
			case 1:
				gestionLibros();
				break;
			case 2:
				gestionRevistas();
				break;
			case 3:
				gestionCdrom();
				break;
			case 4:
				gestionUsuarios();
				break;
			case 5:
				realizarPrestamo();
				break;
			case 6:
				devolverPrestamo();
				break;
			case 7:
				mostrarPrestamos();
				break;
				
			default:
				System.out.println("Debe introducir una opcion entre 0 y 6");
				break;
			}
		} while (opcion!=0);
		
	}

	private void mostrarPrestamos() {
		for(int i=0;i<prestamos.size();i++) {
			System.out.println(prestamos.elementAt(i));
			System.out.println("----------------------");
		}
		
	}
	
	private void realizarPrestamo() {
		String codUsuario=PedirDatos.leerCadena("Introduzca el código del usuario del préstamo");
		int pos=gu.buscarUsuario(codUsuario);
		if(pos==-1) {
			System.out.println("No existe ningún usuario con código "+codUsuario);
			return;
		}

		char tipomaterial=PedirDatos.leerCaracter("Introduzca el tipo de material (l-Libro,r-Revista,c-CDRom)");
		while(tipomaterial!='l'&&tipomaterial!='r'&&tipomaterial!='c') {
			tipomaterial=PedirDatos.leerCaracter("Valor Incorrecto. Introduzca el tipo de material (l-Libro,r-Revista,c-CDRom)");
		}
		
		String codMaterial="";
		switch (tipomaterial) {
		case 'l':
			codMaterial=PedirDatos.leerCadena("Introduzca el código del libro a prestar");
			pos=gl.buscarLibro(codMaterial);
			if(pos==-1) {
				System.out.println("No existe ningún libro con ISBN "+codMaterial);
				return;
			}
			break;
		case 'r':
			codMaterial=PedirDatos.leerCadena("Introduzca el código de la revista a prestar");
			pos=gr.buscarRevista(codMaterial);
			if(pos==-1) {
				System.out.println("No existe ninguna revista con código "+codMaterial);
				return;
			}
			break;
		case 'c':
			codMaterial=PedirDatos.leerCadena("Introduzca el código del CDRom a prestar");
			pos=gc.buscarCd(codMaterial);
			if(pos==-1) {
				System.out.println("No existe ningún CDRom con código "+codMaterial);
				return;
			}
			break;
		}
		
		pos=buscarPrestamo(tipomaterial, codMaterial);
		if (pos!=-1) {
			System.out.println("El préstamo no se puede realizar ya que ya está prestado");
			return;
		}
		String fechaprestamo=PedirDatos.leerCadena("Introduzca la fecha del prestamo (dd/mm/yyyy)");
		
		Prestamo p=new Prestamo(codUsuario, tipomaterial, codMaterial, fechaprestamo);
		
		
		prestamos.addElement(p);;
		System.out.println("Se ha aladido el prestamo "+p);
		
	}

	private void devolverPrestamo() {
		String codUsuario=PedirDatos.leerCadena("Introduzca el codifo del usuario que quiere realizar la devolucion");
		
		char tipomaterial=PedirDatos.leerCaracter("Introduzca el tipo de material (l-Libro,r-Revista,c-CDRom)");
		while(tipomaterial!='l'&&tipomaterial!='r'&&tipomaterial!='c') {
			tipomaterial=PedirDatos.leerCaracter("Valor Incorrecto. Introduzca el tipo de material (l-Libro,r-Revista,c-CDRom)");
		}
		
		String codMaterial=PedirDatos.leerCadena("Dime el codigo del material a devolver");
		
		int pos=buscarPrestamo(codUsuario, tipomaterial, codMaterial);
		if(pos==-1) {
			System.out.println("No existe ningun prestamo con los datos introducido");
			return;
		}
		
		String fechadevolucion=PedirDatos.leerCadena("Introduzca la fecha de devolucion");
		
		prestamos.elementAt(pos).setFechadevolucion(fechadevolucion);
		
		System.out.println("Prestamo devuelto correctamente");
	}

	private int buscarPrestamo(char tipomaterial,String codMaterial) {
		for (int i = 0; i < prestamos.size(); i++) {
			if (prestamos.elementAt(i).getCodMaterial().equals(codMaterial)
					&&prestamos.elementAt(i).getTipomaterial()==tipomaterial
					&&prestamos.elementAt(i).getFechadevolucion()==null) {
				return i;
			}
		}
		
		return -1;
	}
	
	private int buscarPrestamo(String codUsuario,char tipomaterial,String codMaterial) {
		for (int i = 0; i < prestamos.size(); i++) {
			if (prestamos.elementAt(i).getCodUsuario().equals(codUsuario)
					&&prestamos.elementAt(i).getTipomaterial()==tipomaterial
					&&prestamos.elementAt(i).getCodMaterial().equals(codMaterial)
					&&prestamos.elementAt(i).getFechadevolucion()==null) {
				return i;
			}
		}
		
		return -1;
	}

	private void gestionUsuarios() {
		gu.Menu();
		
	}
	
	private void gestionCdrom() {
		gc.menu();
		
	}

	private void gestionRevistas() {
		gr.menu();
		
	}

	private void gestionLibros() {
		gl.menu();
		
	}
}
