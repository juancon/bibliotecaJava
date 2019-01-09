package buffers;

import java.io.*;

public class PedirDatos {
	
	static int leerEntero(String frase){
		int entero=0;
		boolean seguir=false;
		do {
			System.out.println(frase);
			BufferedReader teclado=new BufferedReader(new InputStreamReader(System.in));
			try {
				entero=Integer.parseInt(teclado.readLine());
				seguir=true;
			} catch (NumberFormatException e) {
				System.out.println("Debe introducir un numero entero");
			} catch (IOException e) {
				System.out.println("Error de I/O");
				System.exit(0);
			}
		} while (!seguir);
		
		return entero;
	}

	static double leerDouble(String frase) {
		double decimal=0;
		boolean seguir=false;
		do {
			System.out.println(frase);
			BufferedReader teclado=new BufferedReader(new InputStreamReader(System.in));
			try {
				decimal=Double.parseDouble(teclado.readLine());
				seguir=true;
			} catch (NumberFormatException e) {
				System.out.println("Debe introducir un numero");
			} catch (IOException e) {
				System.out.println("Error de I/O");
				System.exit(0);
			}
			
		} while (!seguir);
		
		return decimal;
	}
	
	static String leerCadena(String frase) {
		String cadena="";
		
		System.out.println(frase);
		BufferedReader teclado=new BufferedReader(new InputStreamReader(System.in));
		try {
			cadena=teclado.readLine();
		} catch (IOException e) {
			System.out.println("Error de I/O");
			System.exit(0);
		}
		
		return cadena;
	}
	
	static char leerCaracter(String frase) {
		char car;
		String cadena="";
		do {
			cadena=leerCadena(frase);
			if(cadena.length()!=1) {
				System.out.println("debe introducir un único caracter");
			}
		}while(cadena.length()!=1);
		
		car=cadena.charAt(0);
		return car;
	}
}
