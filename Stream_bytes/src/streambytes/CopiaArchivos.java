package streambytes;

import java.util.Scanner;

public class CopiaArchivos {

	public static void main(String[] args) {
		String arch, arch2; // Variables donde se dan los nombres de los ficheros
		StreamBytes c=new StreamBytes();// Objeto para ejecutar los metodos de la clase StreamBytes
		// Introducimos los nombres de los ficheros a copiar y la copia
		Scanner sc=new Scanner(System.in);
		System.out.println("Dime el fichero a copiar: ");
		arch=sc.nextLine();
		System.out.println("Dime el fichero de destino: ");
		arch2=sc.nextLine();
		System.out.println("Vamos a proceder a copiar "+arch+
				" en el archivo "+arch2);
		sc.close();
		// Lanzamos la copia propiamente dicha.
		c.copiaBytes(arch,arch2);

	}

}
