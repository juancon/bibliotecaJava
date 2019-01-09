package buffers;

import java.io.*;

public class GestionCdrom {
	Cdrom c=new Cdrom();
	private File fcdrom=new File("files\\cdroms.txt");
	private File ftemporal=new File("files\\utemporal.txt");
	public void menu() throws IOException {
		int opcion=0;
		do {
			System.out.println("MENU DE GESTION DE CDs");
			System.out.println("----------------------------");
			System.out.println("0. Volver");
			System.out.println("1. Nuevo/modificar CD");
			System.out.println("2. Borrar CDs");
			System.out.println("3. Mostrar CDs");
			
			opcion=PedirDatos.leerEntero("Introduzca una opcion entre 0 y 4");
			
			switch (opcion) {
			case 0:
				System.out.println("Adios");
				break;
			case 1:
				nuevoCd();
				break;
			case 2:
				eliminarCd();
				break;
			case 3:
				mostrarCd();
				break;
			default:
				System.out.println("No se ha introducido una opcion correcta");
				break;
			}
			
		}while(opcion!=0);
	}
	
	public boolean buscarCdrom(String codCdrom) throws IOException {
		BufferedReader cdroms;
		try {
			cdroms=new BufferedReader(new FileReader(fcdrom));
		} catch (FileNotFoundException e) {
			return false;
		}
		
		String registro=cdroms.readLine();
		while(registro!=null) {
			c.descomponerRegistro(registro);
			if(codCdrom.equals(c.getCodCdrom())) {
				cdroms.close();
				return true;
			}
			registro=cdroms.readLine();
		}
		cdroms.close();
		return false;
	}

	private void nuevoCd() throws IOException {
		boolean cambio=false;
		System.out.println("Introduzca los datos del cdrom a crear");
		
		Cdrom nuevoc=new Cdrom();
		String codCdrom=c.pedirCodCdrom();
		nuevoc.pedirCdrom(codCdrom);
		System.out.println(nuevoc);
		
		BufferedReader cdroms;
		try {
			cdroms=new BufferedReader(new FileReader(fcdrom));
		} catch (FileNotFoundException e) {
			BufferedWriter bw=new BufferedWriter(new FileWriter(fcdrom));
			bw.close();
			cdroms=new BufferedReader(new FileReader(fcdrom));
		}
		BufferedWriter temporal=new BufferedWriter(new FileWriter(ftemporal));
		
		
		String registro=cdroms.readLine();
		while(registro!=null) {
			c.descomponerRegistro(registro);
			if(nuevoc.getCodCdrom().equals(c.getCodCdrom())){
				temporal.write(nuevoc.crearRegistro());
				cambio=true;
				System.out.println("El cdrom se ha modificado correctamente");
			}else {
				temporal.write(registro);
			}
			temporal.newLine();
			
			registro=cdroms.readLine();
		}
		
		if(!cambio) {
			
			temporal.write(nuevoc.crearRegistro());
			temporal.newLine();
			
			System.out.println("El cdrom se ha añadido correctamente.");
		}

		temporal.flush();
		cdroms.close();
		temporal.close();

		if(fcdrom.delete()) {
			ftemporal.renameTo(fcdrom);
		}else {
			System.out.println("Se ha producido algún error. No se ha creado el nuevo cdrom");
		}
		
	}

	private void eliminarCd() throws IOException {
		BufferedReader cdroms;
		try {
			cdroms=new BufferedReader(new FileReader(fcdrom));
		} catch (FileNotFoundException e) {
			System.out.println("No existe ningún cdrom a eliminar");
			return;
		}
		String codCdrom=c.pedirCodCdrom();
		
		BufferedWriter temporal=new BufferedWriter(new FileWriter(ftemporal));
		boolean encontrado=false;
		String registro=cdroms.readLine();
		while(registro!=null) {
			c.descomponerRegistro(registro);
			if(!(c.getCodCdrom().equals(codCdrom))) {
				temporal.write(registro);
				temporal.newLine();
			}else {
				encontrado=true;
			}
			
			registro=cdroms.readLine();
		}
		
		temporal.flush();
		cdroms.close();
		temporal.close();
		if(!encontrado) {
			System.out.println("No existe el cdrom a eliminar");
			ftemporal.delete();
			return;
		}
		
		if(fcdrom.delete()) {
			ftemporal.renameTo(fcdrom);
			System.out.println("Cdrom eliminado correctamente");
		}else {
			System.out.println("no se ha podido eliminar el Cdrom. Intentelo de nuevo");
		}
	}

	private void mostrarCd() throws IOException {
		BufferedReader cdroms;
		try {
			cdroms=new BufferedReader(new FileReader(fcdrom));
		} catch (FileNotFoundException e) {
			System.out.println("No hay cdroms. Debe crearlos");
			return;
		}
		
		String registro=cdroms.readLine();
		while(registro!=null) {
			c.descomponerRegistro(registro);
			System.out.println(c);
			System.out.println("----------------------------------------");
			registro=cdroms.readLine();
		}
		
		cdroms.close();
		
	}
	
}