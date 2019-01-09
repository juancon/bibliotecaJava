import java.util.Vector;

public class GestionUsuarios {
	/*private final int TAMANIO=100;
	private Usuario[] user=new Usuario[TAMANIO];
	private int posicion=0;
	
	public void Menu() {
		int opcion=0;
		do {
			System.out.println("MENU DE GESTION DE USUARIO");
			System.out.println("--------------------------------");
			System.out.println("0. Volver");
			System.out.println("1. Nuevo Usuario");
			System.out.println("2. Modificar Usuario");
			System.out.println("3. Borrar Usuario");
			System.out.println("4. Mostrar Usuarios");
			
			opcion=PedirDatos.leerEntero("Introduzca una opcion");
			
			switch (opcion) {
			case 0:
				System.out.println("Adios");
				break;
			case 1:
				nuevoUsuario();
				break;
			case 2:
				modificarUsuario();
				break;
			case 3:
				eliminarUsuario();
				break;
			case 4:
				mostrarUsuario();
				break;
			default:
				System.out.println("No se ha introducido una opcin correcta");
				break;
			}
			
		}while(opcion!=0);
	}
	
	private void mostrarUsuario() {
		for (int i=0; i<posicion; i++) {
			System.out.println(this.user[i]);
			System.out.println("--------------------------------");
			
		}
		
	}

	private void nuevoUsuario() {
		if(posicion>=TAMANIO) {
			System.out.println("Listado de usuarios completos");
			return;
		}
		String codUsuario=PedirDatos.leerCadena("Introduzca el codigo de usuario");
		int pos=buscarUsuario(codUsuario);
		if(pos!=-1) {
			System.out.println("El codigo "+codUsuario+" ya existe");
			return;
		}
		
		String nombre=PedirDatos.leerCadena("CodigoCorrecto. Ahora introduzca el nombre");
		String apellido1=PedirDatos.leerCadena("Introduzca el primer apellido");
		String apellido2=PedirDatos.leerCadena("Introduzca el segundo apellido");
				
		Usuario u=new Usuario(codUsuario, nombre, apellido1, apellido2);
		user[posicion]=u;
		posicion++;
	}
	
	private void modificarUsuario() {
		if (posicion==0) {
			System.out.println("No hay usuarios");
			return;
		}
		String codUsuario=PedirDatos.leerCadena("Introduzca el codigo del usuario a modificar");
		int pos=buscarUsuario(codUsuario);
		if(pos==-1) {
			System.out.println("El usuario "+codUsuario+" no existe");
			return;
		}
		System.out.println("El usuario a modificar es: \n"+user[pos]);
		String nombre=PedirDatos.leerCadena("Introduzca el nuevo nombre");
		String apellido1=PedirDatos.leerCadena("Introduzca el nuevo primer apellido");
		String apellido2=PedirDatos.leerCadena("Introduzca el nuevo segundo apellido");
		user[pos]=new Usuario(codUsuario, nombre, apellido1, apellido2);
		System.out.println("El usuario se ha modificado \n"+user[pos]);
	}
	
	private void eliminarUsuario() {
		if (posicion==0) {
			System.out.println("No hay usuarios");
			return;
		}
		String codUsuario=PedirDatos.leerCadena("Introduzca el codigo de usuario a eliminar");
		int pos=buscarUsuario(codUsuario);
		if(pos==-1) {
			System.out.println("El usuario "+codUsuario+" no existe");
			return;
		}
		for(int i=pos; i<posicion-1;i++) {
			this.user[i]=this.user[i+1];
		}
		posicion--;
		this.user[posicion]=null;
		System.out.println("El codigo de usuario "+codUsuario+" se ha eliminado");
	}
	
	public int buscarUsuario(String codUsuario) {
		for(int i=0; i<posicion;i++) {
			if(codUsuario.equals(user[i].getCodUsuario())) {
				return i;
			}
		}
		return -1;
	}*/
	
	Vector<Usuario> usuarios=new Vector<Usuario>();
	public void Menu() {
		int opcion=0;
		do {
			System.out.println("MENU DE GESTION DE USUARIO");
			System.out.println("--------------------------------");
			System.out.println("0. Volver");
			System.out.println("1. Nuevo Usuario");
			System.out.println("2. Modificar Usuario");
			System.out.println("3. Borrar Usuario");
			System.out.println("4. Mostrar Usuarios");
			
			opcion=PedirDatos.leerEntero("Introduzca una opcion");
			
			switch (opcion) {
			case 0:
				System.out.println("Adios");
				break;
			case 1:
				nuevoUsuario();
				break;
			case 2:
				modificarUsuario();
				break;
			case 3:
				eliminarUsuario();
				break;
			case 4:
				mostrarUsuario();
				break;
			default:
				System.out.println("No se ha introducido una opcin correcta");
				break;
			}
			
		}while(opcion!=0);
	}
	
	public int buscarUsuario(String codUsuario) {
		for(int i=0;i<usuarios.size();i++) {
			if(codUsuario.equals(usuarios.elementAt(i).getCodUsuario())) {
				return i;
			}
		}
		return -1;
		
	}
	
	private void nuevoUsuario() {
		String codUsuario=PedirDatos.leerCadena("Introduzca el codigo del nuevo usuario");
		if(buscarUsuario(codUsuario)!=-1) {
			System.out.println("El usuario ya existe");
			return;
		}
		
		String nombre=PedirDatos.leerCadena("Introduzca el nombre del nuevo usuario");
		String apellido1=PedirDatos.leerCadena("Introduzca el primer apellido del nuevo usuario");
		String apellido2=PedirDatos.leerCadena("Introduzca el segundo apellido del nuevo usuario");
		
		Usuario u=new Usuario(codUsuario, nombre, apellido1, apellido2);
		usuarios.addElement(u);
		System.out.println("Se ha añadido el usuario "+u);
		
	}
	private void modificarUsuario() {
		String codUsuario=PedirDatos.leerCadena("Introduzca el codigo del usuario que desea modificar");
		int pos=buscarUsuario(codUsuario);
		if(pos==-1) {
			System.out.println("El usuario no existe");
			return;
		}
		
		String nombre=PedirDatos.leerCadena("Introduzca el nuevo nombre del usuario");
		String apellido1=PedirDatos.leerCadena("Introduzca el nuevo primer apellido del usuario");
		String apellido2=PedirDatos.leerCadena("Introduzca el nuevo segundo apellido del usuario");
		
		Usuario u=new Usuario(codUsuario, nombre, apellido1, apellido2);
		usuarios.setElementAt(u, pos);
		System.out.println("Se ha añadido el usuario "+u);
		
	}
	private void eliminarUsuario() {
		String codUsuario=PedirDatos.leerCadena("Introduzca el codigo del usuario que desea eliminar");
		int pos=buscarUsuario(codUsuario);
		if(pos==-1) {
			System.out.println("El usuario no existe");
			return;
		}
		
		Usuario u=usuarios.remove(pos);
		System.out.println("Se ha eliminado el usuario "+u);
		
	}
	private void mostrarUsuario() {
		for(int i=0;i<usuarios.size();i++) {
			System.out.println(usuarios.elementAt(i));
			System.out.println("---------------------------------");
		}
		
	}
}
