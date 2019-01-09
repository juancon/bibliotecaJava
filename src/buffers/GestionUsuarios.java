package buffers;

import java.io.*;

public class GestionUsuarios {
	Usuario u=new Usuario();
	private File fusuario=new File("files\\usuarios.txt");
	private File ftemporal=new File("files\\utemporal.txt");
	
	public void Menu() throws IOException {
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
	
	public boolean buscarUsuario(String codUsuario) throws IOException {
		BufferedReader usuarios;
		try {
			usuarios=new BufferedReader(new FileReader(fusuario));
		} catch (FileNotFoundException e) {
			return false;
		}
		
		String registro=usuarios.readLine();
		while(registro!=null) {
			u.descomponerRegistro(registro);
			if(codUsuario.equals(u.getCodUsuario())) {
				usuarios.close();
				return true;
			}
			registro=usuarios.readLine();
		}
		usuarios.close();
		return false;
	}
	
	private void nuevoUsuario() throws IOException {
		System.out.println("Introduzca los datos del usuario a crear");
		String codUsuario=u.pedirCodusuario();
		if(buscarUsuario(codUsuario)) {
			System.out.println("El usuario ya existe. No puede volver a crearlo");
			return;
		}
		
		u.pedirUsuario(codUsuario);
		
		RandomAccessFile fusuarios;
		try {
			fusuarios=new RandomAccessFile(fusuario, "r");
		} catch (FileNotFoundException e) {
			BufferedWriter bw=new BufferedWriter(new FileWriter(fusuario));
			bw.close();
			fusuarios=new RandomAccessFile(fusuario, "r");
			
		}
		
		/*
		BufferedReader usuarios;
		try {
			usuarios=new BufferedReader(new FileReader(fusuario));
		} catch (FileNotFoundException e) {
			BufferedWriter bw=new BufferedWriter(new FileWriter(fusuario));
			bw.close();
			usuarios=new BufferedReader(new FileReader(fusuario));
		}
		BufferedWriter temporal=new BufferedWriter(new FileWriter(ftemporal));
		
		String registro=usuarios.readLine();
		while(registro!=null) {
			temporal.write(registro);
			temporal.newLine();
			
			registro=usuarios.readLine();
		}
		temporal.write(u.crearRegistro());
		temporal.newLine();
		temporal.flush();
		usuarios.close();
		temporal.close();

		if(fusuario.delete()) {
			ftemporal.renameTo(fusuario);
			System.out.println("Usario creado correctamente");
		}else {
			System.out.println("Se ha producido algún error. No se ha creado el nuevo usuario");
		}
*/
		
	}
	private void modificarUsuario() throws IOException {
		Usuario nuevousuario= new Usuario();
		BufferedReader usuarios;
		try {
			usuarios=new BufferedReader(new FileReader(fusuario));
		} catch (FileNotFoundException e) {
			System.out.println("No hay ningun usuario");
			return;
		}
		
		String codUsuario=u.pedirCodusuario();
		if(!buscarUsuario(codUsuario)) {
			System.out.println("El usuario no existe");
			usuarios.close();
			return;
		}
		nuevousuario.pedirUsuario(codUsuario);
		
		BufferedWriter temporal=new BufferedWriter(new FileWriter(ftemporal));
		
		String registro=usuarios.readLine();
		while(registro!=null) {
			u.descomponerRegistro(registro);
			if(!(u.getCodUsuario().equals(codUsuario))) {
				temporal.write(registro);
			}else {
				temporal.write(nuevousuario.crearRegistro());
			}
			temporal.newLine();
			registro=usuarios.readLine();
		}
		
		temporal.flush();
		temporal.close();
		usuarios.close();
		
		if(fusuario.delete()) {
			ftemporal.renameTo(fusuario);
			System.out.println("El libro se ha modificado correctamente");
		}else {
			System.out.println("Se ha producido un error. no se ha podido modificar el libro");
		}	
	}
	
	private void eliminarUsuario() throws IOException {
		BufferedReader usuarios;
		try {
			usuarios=new BufferedReader(new FileReader(fusuario));
		} catch (FileNotFoundException e) {
			System.out.println("No existe ningún usuario a eliminar");
			return;
		}
		String codUsuario=u.pedirCodusuario();
		
		BufferedWriter temporal=new BufferedWriter(new FileWriter(ftemporal));
		boolean encontrado=false;
		String registro=usuarios.readLine();
		while(registro!=null) {
			u.descomponerRegistro(registro);
			if(!(u.getCodUsuario().equals(codUsuario))) {
				temporal.write(registro);
				temporal.newLine();
			}else {
				encontrado=true;
			}
			
			registro=usuarios.readLine();
		}
		
		temporal.flush();
		usuarios.close();
		temporal.close();
		if(!encontrado) {
			System.out.println("No existe el usuario a eliminar");
			ftemporal.delete();
			return;
		}
		
		if(fusuario.delete()) {
			ftemporal.renameTo(fusuario);
			System.out.println("Usuario eliminado correctamente");
		}else {
			System.out.println("no se ha podido eliminar el usuario. Intentelo de nuevo");
		}
		
	}
	private void mostrarUsuario() throws IOException {
		BufferedReader usuarios;
		try {
			usuarios=new BufferedReader(new FileReader(fusuario));
		} catch (FileNotFoundException e) {
			System.out.println("No hay usuarios. Debe crearlos");
			return;
		}
		
		String registro=usuarios.readLine();
		while(registro!=null) {
			u.descomponerRegistro(registro);
			System.out.println(u);
			System.out.println("----------------------------------------");
			registro=usuarios.readLine();
		}
		
		usuarios.close();
	}
}
