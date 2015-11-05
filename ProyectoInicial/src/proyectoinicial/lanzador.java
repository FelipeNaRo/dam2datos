package proyectoinicial;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class lanzador {

	public static void main(String[] args) {
		
		// Metodo principal que lanzará el método elegido.
		
		int opcion=0;// variable para elegir que metodo se usará.
		int lugar;// variable para elegir si es el primero o último lugar.
		boolean primero; //true primero false último.
		String fichero;// variable para recoger el nombre de los ficheros.
		File fichero1;// para el primer fichero o el fichero de busqueda.
		File fichero2;// para el segundo fichero.
		String palabraBuscar;// para la palabra de busqueda.
		// Menú para elegir la tarea a realizar.
		System.out.println("Que quieres hacer:");
		System.out.println("1.- Comparar ficheros.");
		System.out.println("2.- Buscar palabras.");
		Scanner teclado=new Scanner(System.in);
		opcion=teclado.nextInt();
		// Para que no haya una opción distinta.
		while (opcion<1 || opcion>2){
			System.err.println("Recuerda es 1 o 2.\n");
			opcion=teclado.nextInt();
		}
		if(opcion==1){
			//Menú de comparación de archivos.
		System.out.print("Dime el primer archivo: ");
			// Crea el primer File para argumento del metodo.
		fichero=teclado.next();
		fichero1=new File(fichero);
		
		System.out.print("Dime el segundo archivo: ");	
			// Crea el segundo File para argumento del metodo.
		fichero=teclado.next();
		fichero2=new File(fichero);
			//Lanza el metodo comparador y captura los errores que puedan producirse.
		teclado.close();
		try{
		ProyectoInicial.comparacion(fichero1,fichero2);
		}catch(IOException e){
			System.err.println("Se ha producido un error con los archivos");
		}}else{
			//Menú del método de busqueda.
			System.out.print("Dime el archivo: ");
			//Se crea el File para el método.
			fichero=teclado.next();
			fichero1=new File(fichero);
			//Se crea el string de la palabra a buscar
			System.out.print("Dime la palabra a buscar: ");
			palabraBuscar=teclado.next();
			//Se elige si es la primera(true) o última(false) aparición.
			System.out.print("Dime el primero o último lugar(1/2): ");
			lugar=teclado.nextInt();
			if (lugar==1){
				primero=true;
			}else{
				primero=false;
			}
			//Lanza el método buscador y captura los errores que se producen.
		try {
			ProyectoInicial.buscaPalabra(fichero1,palabraBuscar,primero);
		} catch (IOException e) 
		{
			System.err.println("Se ha producido un error con los archivos");
		}
	}

	}}


