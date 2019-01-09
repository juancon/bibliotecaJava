package buffers;

import java.io.IOException;

public class TestBiblioteca {

	public static void main(String[] args) {
		try {
			(new Biblioteca()).menu();
		} catch (IOException e) {
			System.out.println("Error de I/O");
		}
	}

}
