import java.util.Vector;

public class GestionLibros {
	Vector<Libro> libros=new Vector<Libro>();
	public void menu() {
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
	
	public int buscarLibro(String isbn) {
		for(int i=0;i<libros.size();i++) {
			if(isbn.equals(libros.elementAt(i).getIsbn())) {
				return i;
			}
		}
		return -1;
	}
	
	private void nuevoLibro() {
		String isbn=PedirDatos.leerCadena("Introduzca el isbn del nuevo libro");
		if(buscarLibro(isbn)!=-1) {
			System.out.println("Ese isbn ya existe");
			return;
		}
		
		String signatura=PedirDatos.leerCadena("Introduzca la signatura del nuevo libro");
		String titulo=PedirDatos.leerCadena("Introduzca el titulo del nuevo libro");
		String autor=PedirDatos.leerCadena("Introduzca el autor del nuevo libro");
		String materia=PedirDatos.leerCadena("Introduzca la materia del nuevo libro");
		String editorial=PedirDatos.leerCadena("Introduzca la editorial del nuevo libro");
		
		Libro l=new Libro(isbn, signatura, titulo, autor, materia, editorial);
		libros.addElement(l);
		System.out.println("Se ha añadido el libro: "+l);
		
	}
	private void modificarLibro() {
		String isbn=PedirDatos.leerCadena("Introduzca el isbn del libro que desea modificar");
		int pos=buscarLibro(isbn);
		if(pos==-1) {
			System.out.println("Ese isbn no existe");
			return;
		}
		
		String signatura=PedirDatos.leerCadena("Introduzca la nueva signatura del libro");
		String titulo=PedirDatos.leerCadena("Introduzca el nuevo titulo del libro");
		String autor=PedirDatos.leerCadena("Introduzca el nuevo autor del libro");
		String materia=PedirDatos.leerCadena("Introduzca la nueva materia del libro");
		String editorial=PedirDatos.leerCadena("Introduzca la nueva editorial del libro");
		
		Libro l=new Libro(isbn, signatura, titulo, autor, materia, editorial);
		libros.setElementAt(l, pos);
		System.out.println("Se ha modificado el libro: "+l);
	}
	private void eliminarLibro() {
		String isbn=PedirDatos.leerCadena("Introduzca el isbn del libro que desea eliminar");
		int pos=buscarLibro(isbn);
		if(pos==-1) {
			System.out.println("Ese isbn no existe");
			return;
		}
		
		Libro l=libros.remove(pos);
		System.out.println("Se ha eliminado el lirbo "+l);
	}
	private void mostrarLibro() {
		for(int i=0;i<libros.size();i++) {
			System.out.println(libros.elementAt(i));
			System.out.println("-----------------------------");
		}
		
	}
	
}
