import java.util.Vector;

public class GestionArticulos {
	Vector<Articulo> articulos=new Vector<Articulo>();
	public void menu() {
		int opcion=0;
		
		do {
			System.out.println("MENÚ DE GESTIÓN DE ARTICULOS");
			System.out.println("----------------------------");
			System.out.println("0. Volver.");
			System.out.println("1. Nuevo Articulo.");
			System.out.println("2. Modificar Articulo.");
			System.out.println("3. Borrar Articulo.");
			System.out.println("4. Mostrar Articulos.");

			
			opcion=PedirDatos.leerEntero("Introduzca una opción entre 0 y 4:");
			
			switch (opcion) {
			case 0:
				System.out.println("Hasta luego.");
				break;
				
			case 1:
				nuevoArticulo();
				break;
				
			case 2:
				modificarArticulo();
				break;
			case 3:
				eliminarArticulo();
				break;
			case 4:
				mostrarArticulo();
				break;
			default:
				System.out.println("Ha introducido una opción incorrecta.");
				break;
			}
		} while (opcion!=0);
	}

	private void nuevoArticulo() {
		String codArticulo=PedirDatos.leerCadena("Introduce el codigo del articulo");
		
		int pos=buscarArticulo(codArticulo);
		if(pos!=-1) {
			System.out.println("El codigo de articulo "+codArticulo+" ya existe");
			return;
		}
		
		String titulo=PedirDatos.leerCadena("introduce el titulo del articulo");
		String autor=PedirDatos.leerCadena("Introduce el autor del articulo");
		String numPaginas=PedirDatos.leerCadena("Introduce el numero de paginas del articulo"); 
		
		Articulo a=new Articulo(codArticulo, titulo, autor, numPaginas);
		articulos.addElement(a);
		System.out.println("El articulo se ha creado con exito");
	}

	private void modificarArticulo() {		
		String codArticulo=PedirDatos.leerCadena("Introduce el codigo del articulo que quieres modificar");
		
		int pos=buscarArticulo(codArticulo);
		if(pos==-1) {
			System.out.println("El codigo de articulo "+codArticulo+" no existe");
			return;
		}
		
		String titulo=PedirDatos.leerCadena("introduce el nuevo titulo del articulo");
		String autor=PedirDatos.leerCadena("Introduce el nuevo autor del articulo");
		String numPaginas=PedirDatos.leerCadena("Introduce el nuevo numero de paginas del articulo");
		
		
		Articulo a=new Articulo(codArticulo, titulo, autor, numPaginas);
		articulos.setElementAt(a, pos);
		System.out.println("El articulo se ha modificado con exito: \n"+a);
		
	}

	private void eliminarArticulo() {		
		String codArticulo=PedirDatos.leerCadena("Introduce el codigo del articulo a eliminar");
		
		int pos=buscarArticulo(codArticulo);
		if(pos==-1) {
			System.out.println("El codigo de articulo "+codArticulo+" no existe");
			return;
		}
		
		Articulo a=articulos.remove(pos);
		
		System.out.println("Se ha eliminado el articulo con codigo "+a);
		
	}

	private void mostrarArticulo() {
		for(int i=0; i<articulos.size();i++) {
			System.out.println(articulos.elementAt(i));
			System.out.println("------------------------");
		}
		
	}
	
	private int buscarArticulo(String codArticulo) {
		for(int i=0; i<articulos.size();i++) {
			if(codArticulo.equals(articulos.elementAt(i).getCodArticulo())) {
				return i;
			}
		}
		return -1;
	}
	
	/*private final int TAM=100;
	private Articulo[] articulos=new Articulo[TAM];
	private int posicion=0;
	
	public void menu() {
		int opcion=0;
		
		do {
			System.out.println("MENÚ DE GESTIÓN DE ARTICULOS");
			System.out.println("----------------------------");
			System.out.println("0. Volver.");
			System.out.println("1. Nuevo Articulo.");
			System.out.println("2. Modificar Articulo.");
			System.out.println("3. Borrar Articulo.");
			System.out.println("4. Mostrar Articulos.");

			
			opcion=PedirDatos.leerEntero("Introduzca una opción entre 0 y 4:");
			
			switch (opcion) {
			case 0:
				System.out.println("Hasta luego.");
				break;
				
			case 1:
				nuevoArticulo();
				break;
				
			case 2:
				modificarArticulo();
				break;
			case 3:
				eliminarArticulo();
				break;
			case 4:
				mostrarArticulo();
				break;
			default:
				System.out.println("Ha introducido una opción incorrecta.");
				break;
			}
		} while (opcion!=0);
	}

	private void nuevoArticulo() {
		if(posicion>=TAM) {
			System.out.println("Listado de articulos completo");
			return;
		}
		
		String codArticulo=PedirDatos.leerCadena("Introduce el codigo del articulo");
		
		int pos=buscarArticulo(codArticulo);
		if(pos!=-1) {
			System.out.println("El codigo de articulo "+codArticulo+" ya existe");
		}
		
		String titulo=PedirDatos.leerCadena("introduce el titulo del articulo");
		String autor=PedirDatos.leerCadena("Introduce el autor del articulo");
		String numPaginas=PedirDatos.leerCadena("Introduce el numero de paginas del articulo"); 
		
		Articulo a=new Articulo(codArticulo, titulo, autor, numPaginas);
		articulos[posicion]=a;
		posicion++;
		System.out.println("El articulo se ha creado con exito");
	}

	private void modificarArticulo() {
		if(posicion==0) {
			System.out.println("No hay ningun articulo");
			return;
		}
		
		String codArticulo=PedirDatos.leerCadena("Introduce el codigo del articulo que quieres modificar");
		
		int pos=buscarArticulo(codArticulo);
		if(pos==-1) {
			System.out.println("El codigo de articulo "+codArticulo+" no existe");
			return;
		}
		System.out.println("El articulo que quiere modifcar es: \n"+articulos[pos]);
		
		String titulo=PedirDatos.leerCadena("introduce el nuevo titulo del articulo");
		String autor=PedirDatos.leerCadena("Introduce el nuevo autor del articulo");
		String numPaginas=PedirDatos.leerCadena("Introduce el nuevo numero de paginas del articulo");
		
		articulos[pos]=new Articulo(codArticulo, titulo, autor, numPaginas);
		System.out.println("El articulo se ha modificado con exito: \n"+articulos[pos]);
		
	}

	private void eliminarArticulo() {
		if(posicion==0) {
			System.out.println("No hay ningun articulo");
			return;
		}
		
		String codArticulo=PedirDatos.leerCadena("Introduce el codigo del articulo a eliminar");
		
		int pos=buscarArticulo(codArticulo);
		if(pos==-1) {
			System.out.println("El codigo de articulo "+codArticulo+" no existe");
			return;
		}
		
		for(int i=pos; i<posicion-1;i++) {
			articulos[i]=articulos[i+1];
		}
		posicion--;
		articulos[posicion]=null;
		
		System.out.println("Se ha eliminado el articulo con codigo "+codArticulo);
		
	}

	private void mostrarArticulo() {
		if(posicion==0) {
			System.out.println("No hay ningun articulo");
			return;
		}
		for(int i=0; i<posicion;i++) {
			System.out.println(articulos[i]);
			System.out.println("------------------------");
		}
		
	}
	
	private int buscarArticulo(String codArticulo) {
		for(int i=0; i<posicion;i++) {
			if(codArticulo.equals(articulos[i].getCodArticulo())) {
				return i;
			}
		}
		return -1;
	}*/
}