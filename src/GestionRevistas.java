import java.util.Vector;

public class GestionRevistas {
	Vector<Revista> revistas=new Vector<Revista>();
	public void menu() {
		int opcion=0;
		
		do {
			System.out.println("MENÚ DE GESTIÓN DE REVISTAS");
			System.out.println("----------------------------");
			System.out.println("0. Volver.");
			System.out.println("1. Nueva Revista.");
			System.out.println("2. Modificar Revista.");
			System.out.println("3. Borrar Revista.");
			System.out.println("4. Mostrar Revistas.");
			System.out.println("5. Gestionar Articulos de revistas");

			
			opcion=PedirDatos.leerEntero("Introduzca una opción entre 0 y 5:");
			
			switch (opcion) {
			case 0:
				System.out.println("Hasta luego.");
				break;
				
			case 1:
				nuevaRevista();
				break;
				
			case 2:
				modificarRevista();
				break;
			case 3:
				eliminarRevistas();
				break;
			case 4:
				mostrarRevista();
				break;
			case 5:
				setArticulos();
				break;
			default:
				System.out.println("Ha introducido una opción incorrecta.");
				break;
			}
		} while (opcion!=0);
	}

	private void setArticulos() {		
		String codRevista=PedirDatos.leerCadena("Introduce el codigo de la revista a la que quiera modificar los articulos");
		int pos=buscarRevista(codRevista);
		if(pos==-1) {
			System.out.println("No existe la revista con codigo "+codRevista);
			return;
		}
		
		revistas.elementAt(pos).setMenuArticulo();
		
	}

	private void nuevaRevista() {
		String codRevista=PedirDatos.leerCadena("Introduce el codigo de la revista");
		int pos=buscarRevista(codRevista);
		if(pos!=-1) {
			System.out.println("la revista con codigo "+codRevista+" ya existe");
			return;
		}
		String sigantura=PedirDatos.leerCadena("Introduce la signatura de la revista");
		String nombre=PedirDatos.leerCadena("Introduce el nombre de la revista");
		String materia=PedirDatos.leerCadena("Introduce la materia de la revista");
		
		Revista r=new Revista(codRevista, sigantura, nombre, materia);
		
		revistas.addElement(r);
		System.out.println("La revista se ha creado con exito");
	}
	
	private void modificarRevista() {
		String codRevista=PedirDatos.leerCadena("Introduce el codigo de la revista que quiere modificar");
		int pos=buscarRevista(codRevista);
		if(pos==-1) {
			System.out.println("El codigo de revista "+codRevista+" no existe");
			return;
		}
		
		String sigantura=PedirDatos.leerCadena("Introduce la nueva signatura de la revista");
		String nombre=PedirDatos.leerCadena("Introduce el nueva nombre de la revista");
		String materia=PedirDatos.leerCadena("Introduce la nueva materia de la revista");
		
		Revista r=new Revista(codRevista, sigantura, nombre, materia);
		revistas.setElementAt(r, pos);
		
		System.out.println("La revista se ha modificado \n"+r);
		
	}
	
	private void eliminarRevistas() {		
		String codRevista=PedirDatos.leerCadena("introduce el codigo de la revista que quieres eliminar");
		int pos=buscarRevista(codRevista);
		if(pos==-1) {
			System.out.println("El codigo de resvitas "+codRevista+" no existe");
			return;
		}
		
		Revista r=revistas.remove(pos);
		System.out.println("Se ha borrado la revista "+r);
	}
	
	private void mostrarRevista() {
		for(int i=0; i<revistas.size(); i++) {
			System.out.println(revistas.elementAt(i));
			System.out.println("------------------------------");
		}
	}
	
	public int buscarRevista(String codRevista) {
		for(int i=0;i<revistas.size();i++) {
			if(codRevista.equals(revistas.elementAt(i).getCodRevista())) {
				return i;
			}
		}
		
		return -1;
	}
	/*private final int TAM=100;
	private Revista[] revistas=new Revista[TAM];
	private int posicion=0;
	
	public void menu() {
		int opcion=0;
		
		do {
			System.out.println("MENÚ DE GESTIÓN DE REVISTAS");
			System.out.println("----------------------------");
			System.out.println("0. Volver.");
			System.out.println("1. Nueva Revista.");
			System.out.println("2. Modificar Revista.");
			System.out.println("3. Borrar Revista.");
			System.out.println("4. Mostrar Revistas.");
			System.out.println("5. Gestionar Articulos de revistas");

			
			opcion=PedirDatos.leerEntero("Introduzca una opción entre 0 y 5:");
			
			switch (opcion) {
			case 0:
				System.out.println("Hasta luego.");
				break;
				
			case 1:
				nuevaRevista();
				break;
				
			case 2:
				modificarRevista();
				break;
			case 3:
				eliminarRevistas();
				break;
			case 4:
				mostrarRevista();
				break;
			case 5:
				setArticulos();
				break;
			default:
				System.out.println("Ha introducido una opción incorrecta.");
				break;
			}
		} while (opcion!=0);
	}

	private void setArticulos() {
		if(posicion==0) {
			System.out.println("No hay ninguna revista");
			return;
		}
		
		String codRevista=PedirDatos.leerCadena("Introduce el codigo de la revista a la que quiera modificar los articulos");
		int pos=buscarRevista(codRevista);
		if(pos==-1) {
			System.out.println("No existe la revista con codigo "+codRevista);
			return;
		}
		
		revistas[pos].setMenuArticulo();
		
	}

	private void nuevaRevista() {
		if(posicion>=TAM) {
			System.out.println("Listado de revistas completo");
			return;
		}
		
		String codRevista=PedirDatos.leerCadena("Introduce el codigo de la revista");
		int pos=buscarRevista(codRevista);
		if(pos!=-1) {
			System.out.println("la revista con codigo "+codRevista+" ya existe");
			return;
		}
		String sigantura=PedirDatos.leerCadena("Introduce la signatura de la revista");
		String nombre=PedirDatos.leerCadena("Introduce el nombre de la revista");
		String materia=PedirDatos.leerCadena("Introduce la materia de la revista");
		
		Revista r=new Revista(codRevista, sigantura, nombre, materia);
		
		revistas[posicion]=r;
		posicion++;
		System.out.println("La revista se ha creado con exito");
	}
	
	private void modificarRevista() {
		if(posicion==0) {
			System.out.println("No hay ninguna revista");
			return;
		}
		String codRevista=PedirDatos.leerCadena("Introduce el codigo de la revista que quiere modificar");
		int pos=buscarRevista(codRevista);
		if(pos==-1) {
			System.out.println("El codigo de revista "+codRevista+" no existe");
			return;
		}
		System.out.println("La revista que quier modificar es: \n"+revistas[pos]);
		
		String sigantura=PedirDatos.leerCadena("Introduce la nueva signatura de la revista");
		String nombre=PedirDatos.leerCadena("Introduce el nueva nombre de la revista");
		String materia=PedirDatos.leerCadena("Introduce la nueva materia de la revista");
		
		revistas[pos]=new Revista(codRevista, sigantura, nombre, materia);
		
		System.out.println("La revista se ha modificado \n"+revistas[pos]);
		
	}
	
	private void eliminarRevistas() {
		if(posicion==0) {
			System.out.println("No hay ninguna revista");
			return;
		}
		
		String codRevista=PedirDatos.leerCadena("introduce el codigo de la revista que quieres eliminar");
		int pos=buscarRevista(codRevista);
		if(pos==-1) {
			System.out.println("El codigo de resvitas "+codRevista+" no existe");
			return;
		}
		
		for(int i=pos; i<posicion-1; i++) {
			revistas[i]=revistas[i+1];
		}
		posicion--;
		
		revistas[posicion]=null;
		System.out.println("Se ha borrado la revista "+codRevista);
	}
	
	private void mostrarRevista() {
		if(posicion==0) {
			System.out.println("No hay ninguna revista");
			return;
		}
		for(int i=0; i<posicion; i++) {
			System.out.println(revistas[i]);
			System.out.println("------------------------------");
		}
	}
	
	public int buscarRevista(String codRevista) {
		for(int i=0;i<posicion;i++) {
			if(codRevista.equals(revistas[i].getCodRevista())) {
				return i;
			}
		}
		
		return -1;
	}*/

}
