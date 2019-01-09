package buffers;

import java.io.*;

public class GestionArticulos {
	private Articulo a=new Articulo();
	private File farticulo=new File("files\\articulos.txt");
	private File ftemporal=new File("files\\atemporal.txt");
	private String codRevista;
	
	public void menu(String codRevista) throws IOException {
		int opcion=0;
		this.codRevista=codRevista;
		do {
			System.out.println("MENÚ DE GESTIÓN DE ARTICULOS");
			System.out.println("----------------------------");
			System.out.println("0. Volver.");
			System.out.println("1. Nuevo/Modificar Articulo.");
			System.out.println("2. Borrar Articulo.");
			System.out.println("3. Mostrar Articulos.");

			
			opcion=PedirDatos.leerEntero("Introduzca una opción entre 0 y 3:");
			
			switch (opcion) {
			case 0:
				System.out.println("Hasta luego.");
				break;
				
			case 1:
				nuevoArticulo();
				break;
			case 2:
				eliminarArticulo();
				break;
			case 3:
				mostrarArticulo();
				break;
			default:
				System.out.println("Ha introducido una opción incorrecta.");
				break;
			}
		} while (opcion!=0);
	}

	private void nuevoArticulo() throws IOException {
		boolean cambio=false;
		System.out.println("Introduzca los datos del articulo a crear");
		
		Articulo nuevoa=new Articulo();
		String codArticulo=a.PedirCodarticulo();
		nuevoa.pedirArticulo(codArticulo,this.codRevista);;
		System.out.println(nuevoa);
		
		BufferedReader articulos;
		try {
			articulos=new BufferedReader(new FileReader(farticulo));
		} catch (FileNotFoundException e) {
			BufferedWriter bw=new BufferedWriter(new FileWriter(farticulo));
			bw.close();
			articulos=new BufferedReader(new FileReader(farticulo));
		}
		BufferedWriter temporal=new BufferedWriter(new FileWriter(ftemporal));
		
		String registro=articulos.readLine();
		while(registro!=null) {
			a.descomponerRegistro(registro);
			if(!a.getCodRevista().equals(codRevista)) {
				temporal.write(registro);
			}else {
				if(a.getCodArticulo().equals(nuevoa.getCodArticulo())) {
					temporal.write(nuevoa.crearRegistro());
					cambio=true;
					System.out.println("El articulo se ha modificado correctamente");
				}else {
					temporal.write(registro);
				}
			}
			temporal.newLine();
			registro=articulos.readLine();
		}
		
		if(!cambio) {
			
			temporal.write(nuevoa.crearRegistro());
			temporal.newLine();
			
			System.out.println("El articulo se ha añadido correctamente.");
		}

		temporal.flush();
		articulos.close();
		temporal.close();

		if(farticulo.delete()) {
			ftemporal.renameTo(farticulo);
		}else {
			System.out.println("Se ha producido algún error. No se ha creado el articulo");
		}
	}

	private void eliminarArticulo() throws IOException {		
		BufferedReader articulos;
		try {
			articulos=new BufferedReader(new FileReader(farticulo));
		} catch (FileNotFoundException e) {
			System.out.println("No existe ninguna revista a eliminar");
			return;
		}
		String codArticulo=a.PedirCodarticulo();
		
		BufferedWriter temporal=new BufferedWriter(new FileWriter(ftemporal));
		boolean encontrado=false;
		String registro=articulos.readLine();
		while(registro!=null) {
			a.descomponerRegistro(registro);
			if(!(a.getCodArticulo().equals(codArticulo))) {
				temporal.write(registro);
				temporal.newLine();
			}else {
				encontrado=true;
			}
			
			registro=articulos.readLine();
		}
		
		temporal.flush();
		articulos.close();
		temporal.close();
		if(!encontrado) {
			System.out.println("No existe el articulo a eliminar");
			ftemporal.delete();
			return;
		}
		
		if(farticulo.delete()) {
			ftemporal.renameTo(farticulo);
			System.out.println("Articulo eliminado correctamente");
		}else {
			System.out.println("no se ha podido eliminar el articulo. Intentelo de nuevo");
		}
		
	}

	private void mostrarArticulo() throws IOException {
		BufferedReader articulos;
		try {
			articulos=new BufferedReader(new FileReader(farticulo));
		} catch (FileNotFoundException e) {
			System.out.println("No hay articulos. Debe crearlos");
			return;
		}
		
		String registro=articulos.readLine();
		while(registro!=null) {
			a.descomponerRegistro(registro);
			if(a.getCodRevista().equals(this.codRevista)) {
				System.out.println(a);
				System.out.println("----------------------------------------");
			}
			registro=articulos.readLine();
		}
		
		articulos.close();
		
	}
	
	public void buscarArticulo(String codRevista) throws IOException {
		BufferedReader articulos;
		try {
			articulos=new BufferedReader(new FileReader(farticulo));
		} catch (FileNotFoundException e) {
			return;
		}
		
		String registro=articulos.readLine();
		while(registro!=null) {
			a.descomponerRegistro(registro);
			if(a.getCodRevista().equals(codRevista)) {
				System.out.println(a);
				System.out.println("----------------------------------------");
			}
			registro=articulos.readLine();
		}
		articulos.close();
		
	}
}