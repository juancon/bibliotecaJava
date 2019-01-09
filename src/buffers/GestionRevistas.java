package buffers;

import java.io.*;

public class GestionRevistas {
	Revista r=new Revista();
	Articulo a=new Articulo();
	GestionArticulos ga=new GestionArticulos();
	private File frevista=new File("files\\revistas.txt");
	private File farticulo=new File("files\\articulos.txt");
	private File ftemporal=new File("files\\rtemporal.txt");
	
	public void menu() throws IOException {
		int opcion=0;
		
		do {
			System.out.println("MENÚ DE GESTIÓN DE REVISTAS");
			System.out.println("----------------------------");
			System.out.println("0. Volver.");
			System.out.println("1. Nueva/Modificar Revista.");
			System.out.println("2. Borrar Revista.");
			System.out.println("3. Mostrar Revistas.");
			System.out.println("4. Gestionar Articulos de revistas");

			
			opcion=PedirDatos.leerEntero("Introduzca una opción entre 0 y 4:");
			
			switch (opcion) {
			case 0:
				System.out.println("Hasta luego.");
				break;
			case 1:
				nuevaRevista();
				break;
			case 2:
				eliminarRevistas();
				break;
			case 3:
				mostrarRevista();
				break;
			case 4:
				setArticulos();
				break;
			default:
				System.out.println("Ha introducido una opción incorrecta.");
				break;
			}
		} while (opcion!=0);
	}

	private void setArticulos() throws IOException {		
		String codRevista=r.pedirCodrevista();
		boolean existe=false;
		BufferedReader revistas;
		try {
			revistas=new BufferedReader(new FileReader(frevista));
		} catch (FileNotFoundException e) {
			System.out.println("No hay revistas");
			return;
		}
		
		String registro=revistas.readLine();
		while(registro!=null) {
			r.descomponerRegistro(registro);
			if(r.getCodRevista().equals(codRevista)) {
				existe=true;
				break;
			}
			registro=revistas.readLine();
		}
		
		if(!existe) {
			System.out.println("No hay ninguna revista con ese codigo");
			revistas.close();
			return;
		}
		
		revistas.close();
		ga.menu(codRevista);
		
	}

	private void nuevaRevista() throws IOException {
		boolean cambio=false;
		System.out.println("Introduzca los datos de la revista a crear");
		
		Revista nuevar=new Revista();
		String codRevista=r.pedirCodrevista();
		nuevar.pedirRevista(codRevista);
		System.out.println(nuevar);
		
		BufferedReader revistas;
		try {
			revistas=new BufferedReader(new FileReader(frevista));
		} catch (FileNotFoundException e) {
			BufferedWriter bw=new BufferedWriter(new FileWriter(frevista));
			bw.close();
			revistas=new BufferedReader(new FileReader(frevista));
		}
		BufferedWriter temporal=new BufferedWriter(new FileWriter(ftemporal));
		
		String registro=revistas.readLine();
		while(registro!=null) {
			r.descomponerRegistro(registro);
			if(nuevar.getCodRevista().equals(r.getCodRevista())){
				temporal.write(nuevar.crearRegistro());
				cambio=true;
				System.out.println("La revista se ha modificado correctamente");
			}else {
				temporal.write(registro);
			}
			temporal.newLine();
			
			registro=revistas.readLine();
		}
		
		if(!cambio) {
			
			temporal.write(nuevar.crearRegistro());
			temporal.newLine();
			
			System.out.println("La revista se ha añadido correctamente.");
		}

		temporal.flush();
		revistas.close();
		temporal.close();

		if(frevista.delete()) {
			ftemporal.renameTo(frevista);
		}else {
			System.out.println("Se ha producido algún error. No se ha creado la nueva revista");
		}
	}
	
	private void eliminarRevistas() throws IOException {		
		BufferedReader revistas;
		BufferedReader articulos;
		boolean art=false;
		
		try {
			revistas=new BufferedReader(new FileReader(frevista));
		} catch (FileNotFoundException e) {
			System.out.println("No existe ninguna revista a eliminar");
			return;
		}
		String codRevista=r.pedirCodrevista();
		
		try {
			articulos=new BufferedReader(new FileReader(farticulo));
		} catch (FileNotFoundException e) {
			BufferedWriter bw=new BufferedWriter(new FileWriter(farticulo));
			bw.close();
			articulos=new BufferedReader(new FileReader(farticulo));
		}
		
		String registroa=articulos.readLine();
		while(registroa!=null) {
			a.descomponerRegistro(registroa);
			if(a.getCodRevista().equals(codRevista)) {
				art=true;
				break;
			}
			registroa=articulos.readLine();
		}
		articulos.close();
		
		if(art) {
			System.out.println("La revista no se puede borrar porque contiene articulos");
			revistas.close();
			return;
			
		}
		
		BufferedWriter temporal=new BufferedWriter(new FileWriter(ftemporal));
		boolean encontrado=false;
		String registro=revistas.readLine();
		while(registro!=null) {
			r.descomponerRegistro(registro);
			if(!(r.getCodRevista().equals(codRevista))) {
				temporal.write(registro);
				temporal.newLine();
			}else {
				encontrado=true;
			}
			
			registro=revistas.readLine();
		}
		
		temporal.flush();
		revistas.close();
		temporal.close();
		if(!encontrado) {
			System.out.println("No existe la revista a eliminar");
			ftemporal.delete();
			return;
		}
		
		if(frevista.delete()) {
			ftemporal.renameTo(frevista);
			System.out.println("Revista eliminadoa correctamente");
		}else {
			System.out.println("no se ha podido eliminar la revista. Intentelo de nuevo");
		}
	}
	
	private void mostrarRevista() throws IOException {
		BufferedReader revistas;
		try {
			revistas=new BufferedReader(new FileReader(frevista));
		} catch (FileNotFoundException e) {
			System.out.println("No hay revistas. Debe crearlas");
			return;
		}
				
		String registro=revistas.readLine();
		
		while(registro!=null) {
			r.descomponerRegistro(registro);
			System.out.println(r);
			System.out.println("----------------------------------------");
			ga.buscarArticulo(r.getCodRevista());
			registro=revistas.readLine();
		}
		revistas.close();
	}
}
