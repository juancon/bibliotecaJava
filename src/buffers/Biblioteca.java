package buffers;

import java.io.*;

public class Biblioteca {
	private GestionLibros gl=new GestionLibros();
	private GestionUsuarios gu= new GestionUsuarios();
	private File filePrestamos=new File("files\\prestamos.txt");
	private File fileTemporal=new File("files\\ptemporal.txt");
	private GestionRevistas gr=new GestionRevistas();
	private Prestamo p=new Prestamo();
	private GestionCdrom gc=new GestionCdrom();
	
	public void menu() throws IOException {
		int opcion=0;
		do {
			System.out.println("0. Salir");
			System.out.println("1. Gestion de Libro");
			System.out.println("2. Gestion de Revistas");
			System.out.println("3. Gestion de CDROM");
			System.out.println("4. Gestion de Usuario");
			System.out.println("5. Realizar Prestamo");
			System.out.println("6. Devolver Prestamo");
			System.out.println("7. Mostrar Prestamos");
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
				System.out.println("Debe introducir una opcion entre 0 y 7");
				break;
			}
		} while (opcion!=0);
		
	}

	private void mostrarPrestamos() throws IOException {
		BufferedReader prestamos;
		try {
			prestamos=new BufferedReader(new FileReader(filePrestamos));
		} catch (FileNotFoundException e) {
			System.out.println("No se ha realizado ningun prestamo");
			return;
		}
		
		String registro=prestamos.readLine();
		while(registro!=null) {
			p.descomponerRegistro(registro);
			System.out.println(p);
			registro=prestamos.readLine();
		}
		prestamos.close();
	}
	
	private void realizarPrestamo() throws IOException {
		//Pedimos el usuario
		String codUsuario=PedirDatos.leerCadena("Introduzca el código del usuario");
		if(!gu.buscarUsuario(codUsuario)) {
			System.out.println("No existe el usuario "+codUsuario+". No se puede realizar el prestamo");
			return;
		}
		//Pedimos el tipo de material
		char tipomaterial=' ';
		do {
			String tipo=PedirDatos.leerCadena("Introduzca el tipo de material (l-Libro,r-Revista,c-CDRom)").toLowerCase();
			while(tipo.length()!=1) {
				tipo=PedirDatos.leerCadena("Debe introducir un único carcater (l-Libro,r-Revista,c-CDRom)").toLowerCase();
			}
			tipomaterial=tipo.charAt(0);
		}while(tipomaterial!='l'&&tipomaterial!='r'&&tipomaterial!='c');
		
		String codMaterial="";
		switch (tipomaterial) {
		case 'l':
			codMaterial=PedirDatos.leerCadena("Dime el ISBN del libro");
			if(!gl.buscarLibro(codMaterial)) {
				System.out.println("No puede prestar el libro con ISBN "+codMaterial+" porque no existe");
				return;
			}
			break;
		case 'r':
			
			break;
		case 'c':
			
			break;
		}
		
		String fprestamo=PedirDatos.leerCadena("Introduzca la fecha de prestamo (dd/mm/yyyy)");
		
		BufferedReader prestamos;
		try {
			prestamos=new BufferedReader(new FileReader(filePrestamos));
		} catch (FileNotFoundException e) {
			BufferedWriter bw=new BufferedWriter(new FileWriter(filePrestamos));
			bw.close();
			prestamos=new BufferedReader(new FileReader(filePrestamos));
		}
		BufferedWriter temporal=new BufferedWriter(new FileWriter(fileTemporal));
		boolean existe=false;
		String registro=prestamos.readLine();
		while(registro!=null) {
			if(p.getFechaDevolucion(registro)!=null) {
				temporal.write(registro);
				temporal.newLine();
				registro=prestamos.readLine();
				continue;
			}
			p.descomponerRegistro(registro);
			if(codUsuario.equals(p.getCodUsuario())&&
				tipomaterial==p.getTipomaterial()&&
				codMaterial.equals(p.getCodMaterial())) {
				System.out.println("El prestamo solicitado ya se ha prestado");
				existe=true;
				break;
			}else {
				temporal.write(registro);
				temporal.newLine();
			}
			
			registro=prestamos.readLine();
		}
		
		if(!existe) {
			p=new Prestamo(codUsuario, tipomaterial, codMaterial, fprestamo);
			registro=p.componerRegistro();
			temporal.write(registro);
			temporal.newLine();
		}
		
		temporal.flush();
		temporal.close();
		prestamos.close();
		
		if(existe) {
			fileTemporal.delete();
		}else {
			if(filePrestamos.delete()) {
				fileTemporal.renameTo(filePrestamos);
			}else {
				System.out.println("Se ha producido un error. Vuelva a intentarlo");
			}
		}
			
	}

	private void devolverPrestamo() throws IOException {
		//Pedimos el tipo de material
		char tipomaterial=' ';
		do {
			String tipo=PedirDatos.leerCadena("Introduzca el tipo de material (l-Libro,r-Revista,c-CDRom)").toLowerCase();
			while(tipo.length()!=1) {
				tipo=PedirDatos.leerCadena("Debe introducir un único carcater (l-Libro,r-Revista,c-CDRom)").toLowerCase();
			}
			tipomaterial=tipo.charAt(0);
		}while(tipomaterial!='l'&&tipomaterial!='r'&&tipomaterial!='c');
		
		String codMaterial="";
		switch (tipomaterial) {
			case 'l':
				codMaterial=PedirDatos.leerCadena("Dime el ISBN del libro");
				break;
			case 'r':
				
				break;
			case 'c':
				
				break;
		}
		
		String fdevolucion=PedirDatos.leerCadena("Introduzca la fecha de devolucion DD/MM/YYYY");
		
		BufferedReader prestamos;
		try {
			prestamos=new BufferedReader(new FileReader(filePrestamos));
		} catch (FileNotFoundException e) {
			System.out.println("No existe el prestamo. No puede devolver nada");
			return;
		}
		BufferedWriter temporal=new BufferedWriter(new FileWriter(fileTemporal));
		
		boolean existe=false;
		String registro=prestamos.readLine();
		while(registro!=null) {
			if(p.getFechaDevolucion(registro)!=null) {
				temporal.write(registro);
				temporal.newLine();
				registro=prestamos.readLine();
				continue;
			}
			
			p.descomponerRegistro(registro);
			if(p.getTipomaterial()==tipomaterial&&
				p.getCodMaterial().equals(codMaterial)) {
				existe=true;
				p.setFechadevolucion(fdevolucion);
				registro=p.componerRegistro();
			}
			temporal.write(registro);
			temporal.newLine();
			
			registro=prestamos.readLine();
		}
		
		temporal.flush();
		temporal.close();
		prestamos.close();
		
		if(!existe) {
			System.out.println("El material no se ha podido devolver porque no se ha prestado");
			fileTemporal.delete();
		}else {
			if(filePrestamos.delete()) {
				fileTemporal.renameTo(filePrestamos);
			}else {
				System.out.println("Se ha producido un error al devolver. Vuelva a intentarlo");
			}
		}
	}

	private int buscarPrestamo(char tipomaterial,String codMaterial) {
		/*for (int i = 0; i < prestamos.size(); i++) {
			if (prestamos.elementAt(i).getCodMaterial().equals(codMaterial)
					&&prestamos.elementAt(i).getTipomaterial()==tipomaterial
					&&prestamos.elementAt(i).getFechadevolucion()==null) {
				return i;
			}
		}
		*/
		return -1;
	}
	
	private int buscarPrestamo(String codUsuario,char tipomaterial,String codMaterial) {
		/*for (int i = 0; i < prestamos.size(); i++) {
			if (prestamos.elementAt(i).getCodUsuario().equals(codUsuario)
					&&prestamos.elementAt(i).getTipomaterial()==tipomaterial
					&&prestamos.elementAt(i).getCodMaterial().equals(codMaterial)
					&&prestamos.elementAt(i).getFechadevolucion()==null) {
				return i;
			}
		}*/
		
		return -1;
	}

	private void gestionUsuarios() throws IOException {
		gu.Menu();
		
	}
	
	private void gestionCdrom() throws IOException {
		gc.menu();
		
	}

	private void gestionRevistas() throws IOException {
		gr.menu();
		
	}

	private void gestionLibros() throws IOException {
		gl.menu();
		
	}
}
