package almacen_libros;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		int op=0;
		Scanner sc= new Scanner(System.in);
		Almacen_Libros al=new Almacen_Libros();
		System.out.println("¿Que quieres hacer?:");
		System.out.println("1 - Guardar un libro.");
		System.out.println("2 - Guardar varios libros.");
		System.out.println("3 - Recuperar un libro.");
		System.out.println("4 - Modificar Título y/o Autor.");
		System.out.println("5 - Salir.");
		
		try {op=sc.nextInt();
		}catch(InputMismatchException e){
			System.err.print("Pareces tonto. Recuerda, numero del 1 al 5; ");
			Principal.main(null);}
		catch(NoSuchElementException e){
			Principal.main(null);
		}
		if (op!=5){
			al.Seleccion(op);
		}
		System.out.print("¡¡¡ HASTA LUEGO LUCAS......... !!!");
		al.intentarCerrar(sc);
	}
	
}
