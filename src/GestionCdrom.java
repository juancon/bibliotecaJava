import java.util.Vector;

public class GestionCdrom {
	Vector<Cdrom> cds=new Vector<Cdrom>();
	public void menu() {
		int opcion=0;
		do {
			System.out.println("MENU DE GESTION DE CDs");
			System.out.println("----------------------------");
			System.out.println("0. Volver");
			System.out.println("1. Nuevo CD");
			System.out.println("2. Modificar CDs");
			System.out.println("3. Borrar CDs");
			System.out.println("4. Mostrar CDs");
			
			opcion=PedirDatos.leerEntero("Introduzca una opcion entre 0 y 4");
			
			switch (opcion) {
			case 0:
				System.out.println("Adios");
				break;
			case 1:
				nuevoCd();
				break;
			case 2:
				modificarCd();
				break;
			case 3:
				eliminarCd();
				break;
			case 4:
				mostrarCd();
				break;
			default:
				System.out.println("No se ha introducido una opcion correcta");
				break;
			}
			
		}while(opcion!=0);
	}

	private void nuevoCd() {
		String codCdrom=PedirDatos.leerCadena("Introduzca el codigo del CD");
		int pos=buscarCd(codCdrom);
		if(pos!=-1) {
			System.out.println("El codigo de CD "+codCdrom+" ya existe");
			return;
		}
		
		String signatura=PedirDatos.leerCadena("Introduzca la signatura del CD");
		String titulo=PedirDatos.leerCadena("Introduzca el titulo del CD");
		String autor=PedirDatos.leerCadena("Introduzca el autor del CD");
		String materia=PedirDatos.leerCadena("Introduzca la materia del CD");
		String editorial=PedirDatos.leerCadena("Introduzca la editorial del CD");
		
		Cdrom c=new Cdrom(codCdrom, signatura, titulo, autor, materia, editorial);
		cds.addElement(c);
		
		System.out.println("El CD se ha creado con exito");
		
	}

	private void modificarCd() {
		String codCdrom=PedirDatos.leerCadena("Introduzca el codigo del CD que quiere modificar");
		int pos=buscarCd(codCdrom);
		if(pos==-1) {
			System.out.println("El codigo "+codCdrom+" no existe");
			return;
		}
		
		
		String signatura=PedirDatos.leerCadena("Introduzca la nueva signatura del CD");
		String titulo=PedirDatos.leerCadena("Introduzca el nuevo titulo del CD");
		String autor=PedirDatos.leerCadena("Introduzca el nuevo autor del CD");
		String materia=PedirDatos.leerCadena("Introduzca la nueva materia del CD");
		String editorial=PedirDatos.leerCadena("Introduzca la nueva editorial del CD");
		
		Cdrom c=new Cdrom(codCdrom, signatura, titulo, autor, materia, editorial);
		cds.set(pos, c);
		System.out.println("Se ha modificado el articulo: \n"+c);
	}

	private void eliminarCd() {
		String codCdrom=PedirDatos.leerCadena("Introduzca el codigo del CD que quiere modificar");
		int pos=buscarCd(codCdrom);
		if(pos==-1) {
			System.out.println("El codigo "+codCdrom+" no existe");
			return;
		}
		Cdrom c=cds.remove(pos);
		
		System.out.println("Se la eliminado el CD con codigo "+c);
	}

	private void mostrarCd() {
		for(int i=0;i<cds.size();i++) {
			System.out.println(cds.elementAt(i));
			System.out.println("-----------------------");
		}
		
	}
	
	public int buscarCd(String codCdrom) {
		for(int i=0; i<cds.size();i++) {
			if(codCdrom.equals(cds.elementAt(i).getCodCdrom())) {
				return i;
			}
		}
		return -1;
	}
	/*private final int TAM=100;
	private Cdrom[] cdroms=new Cdrom[TAM];
	private int posicion=0;
	
	public void menu() {
		int opcion=0;
		do {
			System.out.println("MENU DE GESTION DE CDs");
			System.out.println("----------------------------");
			System.out.println("0. Volver");
			System.out.println("1. Nuevo CD");
			System.out.println("2. Modificar CDs");
			System.out.println("3. Borrar CDs");
			System.out.println("4. Mostrar CDs");
			
			opcion=PedirDatos.leerEntero("Introduzca una opcion entre 0 y 4");
			
			switch (opcion) {
			case 0:
				System.out.println("Adios");
				break;
			case 1:
				nuevoCd();
				break;
			case 2:
				modificarCd();
				break;
			case 3:
				eliminarCd();
				break;
			case 4:
				mostrarCd();
				break;
			default:
				System.out.println("No se ha introducido una opcion correcta");
				break;
			}
			
		}while(opcion!=0);
	}

	private void nuevoCd() {
		if(posicion>=TAM) {
			System.out.println("Listado de CDs completo");
			return;
		}
		
		String codCdrom=PedirDatos.leerCadena("Introduzca el codigo del CD");
		int pos=buscarCd(codCdrom);
		if(pos!=-1) {
			System.out.println("El codigo de CD "+codCdrom+" ya existe");
			return;
		}
		
		String signatura=PedirDatos.leerCadena("Introduzca la signatura del CD");
		String titulo=PedirDatos.leerCadena("Introduzca el titulo del CD");
		String autor=PedirDatos.leerCadena("Introduzca el autor del CD");
		String materia=PedirDatos.leerCadena("Introduzca la materia del CD");
		String editorial=PedirDatos.leerCadena("Introduzca la editorial del CD");
		
		cdroms[posicion]=new Cdrom(codCdrom, signatura, titulo, autor, materia, editorial);
		posicion++;
		
		System.out.println("El CD se ha creado con exito");
		
	}

	private void modificarCd() {
		if(posicion==0) {
			System.out.println("No hay ningun CD");
			return;
		}
		
		String codCdrom=PedirDatos.leerCadena("Introduzca el codigo del CD que quiere modificar");
		int pos=buscarCd(codCdrom);
		if(pos==-1) {
			System.out.println("El codigo "+codCdrom+" no existe");
			return;
		}
		
		System.out.println("El articulo que quiere modificar es: \n"+cdroms[pos]);
		
		String signatura=PedirDatos.leerCadena("Introduzca la nueva signatura del CD");
		String titulo=PedirDatos.leerCadena("Introduzca el nuevo titulo del CD");
		String autor=PedirDatos.leerCadena("Introduzca el nuevo autor del CD");
		String materia=PedirDatos.leerCadena("Introduzca la nueva materia del CD");
		String editorial=PedirDatos.leerCadena("Introduzca la nueva editorial del CD");
		
		cdroms[pos]=new Cdrom(codCdrom, signatura, titulo, autor, materia, editorial);
		System.out.println("Se ha modificado el articulo: \n"+cdroms[pos]);
	}

	private void eliminarCd() {
		if(posicion==0) {
			System.out.println("No hay ningun CD");
			return;
		}
		
		String codCdrom=PedirDatos.leerCadena("Introduzca el codigo del CD que quiere modificar");
		int pos=buscarCd(codCdrom);
		if(pos==-1) {
			System.out.println("El codigo "+codCdrom+" no existe");
			return;
		}
		
		for(int i=pos;i<posicion-1;i++) {
			cdroms[i]=cdroms[i+1];
		}
		posicion--;
		cdroms[posicion]=null;
		
		System.out.println("Se la eliminado el CD con codigo "+codCdrom);
	}

	private void mostrarCd() {
		if(posicion==0) {
			System.out.println("No hay ningun CD");
			return;
		}
		for(int i=0;i<posicion;i++) {
			System.out.println(cdroms[i]);
			System.out.println("-----------------------");
		}
		
	}
	
	public int buscarCd(String codCdrom) {
		for(int i=0; i<posicion;i++) {
			if(codCdrom.equals(cdroms[i].getCodCdrom())) {
				return i;
			}
		}
		return -1;
	}*/
}
