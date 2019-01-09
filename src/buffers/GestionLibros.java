package buffers;

import java.io.*;

public class GestionLibros {
	Libro l=new Libro();
	private File flibro=new File("files\\libros.txt");
	private File ftemporal=new File("files\\ltemporal.txt");
	
	public void menu() throws IOException {
		int opcion=0;
		do {
			System.out.println("MENU DE GESTION DE LIBROS");
			System.out.println("----------------------------");
			System.out.println("0. Volver");
			System.out.println("1. Nuevo Libro");
			System.out.println("2. Modificar Libro");
			System.out.println("3. Borrar Libro");
			System.out.println("4. Mostrar Libro");
			
			opcion=PedirDatos.leerEntero("Introduzca una opcion");
			
			switch (opcion) {
			case 0:
				System.out.println("Adios");
				break;
			case 1:
				nuevoLibro();
				break;
			case 2:
				modificarLibro();
				break;
			case 3:
				eliminarLibro();
				break;
			case 4:
				mostrarLibro();
				break;

			default:
				System.out.println("No se ha introducido una opcin correcta");
				break;
			}
			
		}while(opcion!=0);
	}
	
	public boolean buscarLibro(String isbn) throws IOException {
		BufferedReader libros;
		try {
			libros=new BufferedReader(new FileReader(flibro));
		} catch (FileNotFoundException e) {
			return false;
		}
		
		String registro=libros.readLine();
		while(registro!=null) {
			l.descomponerRegistro(registro);
			if(isbn.equals(l.getIsbn())) {
				libros.close();
				return true;
			}
			registro=libros.readLine();
		}
		libros.close();
		return false;
	}
	
	private void nuevoLibro() throws IOException {
		System.out.println("Introduzca los datos del libro a crear");
		String isbn=l.pedirIsbn();
		if(buscarLibro(isbn)) {
			System.out.println("El libro ya existe. No puede volver a crearlo");
			return;
		}
		
		l.pedirLibro(isbn);
		
		BufferedReader libros;
		try {
			libros=new BufferedReader(new FileReader(flibro));
		} catch (FileNotFoundException e) {
			BufferedWriter bw=new BufferedWriter(new FileWriter(flibro));
			bw.close();
			libros=new BufferedReader(new FileReader(flibro));
		}
		BufferedWriter temporal=new BufferedWriter(new FileWriter(ftemporal));
		
		String registro=libros.readLine();
		while(registro!=null) {
			temporal.write(registro);
			temporal.newLine();
			
			registro=libros.readLine();
		}
		temporal.write(l.crearRegistro());
		temporal.newLine();
		temporal.flush();
		libros.close();
		temporal.close();

		if(flibro.delete()) {
			ftemporal.renameTo(flibro);
			System.out.println("Libro creado correctamente");
		}else {
			System.out.println("Se ha producido algún error. No se ha creado el nuevo libro");
		}
	}
	private void modificarLibro() throws IOException {
		Libro nuevolibro= new Libro();
		BufferedReader libros;
		try {
			libros=new BufferedReader(new FileReader(flibro));
		} catch (FileNotFoundException e) {
			System.out.println("No hay ningun libro");
			return;
		}
		
		String isbn=l.pedirIsbn();
		if(!buscarLibro(isbn)) {
			System.out.println("El isbn no existe");
			libros.close();
			return;
		}
		nuevolibro.pedirLibro(isbn);
		
		BufferedWriter temporal=new BufferedWriter(new FileWriter(ftemporal));
		
		String registro=libros.readLine();
		while(registro!=null) {
			l.descomponerRegistro(registro);
			if(!(l.getIsbn().equals(isbn))) {
				temporal.write(registro);
			}else {
				temporal.write(nuevolibro.crearRegistro());
			}
			temporal.newLine();
			registro=libros.readLine();
		}
		
		temporal.flush();
		temporal.close();
		libros.close();
		
		if(flibro.delete()) {
			ftemporal.renameTo(flibro);
			System.out.println("El libro se ha modificado correctamente");
		}else {
			System.out.println("Se ha producido un error. no se ha podido modificar el libro");
		}
	}
	
	
	private void eliminarLibro() throws IOException {
		BufferedReader libros;
		try {
			libros=new BufferedReader(new FileReader(flibro));
		} catch (FileNotFoundException e) {
			System.out.println("No existe ningún libro a eliminar");
			return;
		}
		String isbn=l.pedirIsbn();
		
		BufferedWriter temporal=new BufferedWriter(new FileWriter(ftemporal));
		boolean encontrado=false;
		String registro=libros.readLine();
		while(registro!=null) {
			l.descomponerRegistro(registro);
			if(!(l.getIsbn().equals(isbn))) {
				temporal.write(registro);
				temporal.newLine();
			}else {
				encontrado=true;
			}
			
			registro=libros.readLine();
		}
		
		temporal.flush();
		libros.close();
		temporal.close();
		if(!encontrado) {
			System.out.println("No existe el libro a eliminar");
			ftemporal.delete();
			return;
		}
		
		if(flibro.delete()) {
			ftemporal.renameTo(flibro);
			System.out.println("Libro eliminado correctamente");
		}else {
			System.out.println("no se ha podido eliminar el libro. Intentelo de nuevo");
		}

	}
	private void mostrarLibro() throws IOException {
		BufferedReader libros;
		try {
			libros=new BufferedReader(new FileReader(flibro));
		} catch (FileNotFoundException e) {
			System.out.println("No hay libros. Debe crearlos");
			return;
		}
		
		String registro=libros.readLine();
		while(registro!=null) {
			l.descomponerRegistro(registro);
			System.out.println(l);
			System.out.println("----------------------------------------");
			registro=libros.readLine();
		}
		
		libros.close();
	}
	
}
