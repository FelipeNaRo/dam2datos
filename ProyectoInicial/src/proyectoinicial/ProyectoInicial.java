package proyectoinicial;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class ProyectoInicial {
	
	
	public static void comparacion(File f1,File f2)throws IOException{
		
		String st=""; //String auxiliar.
		String st1=""; //String del primer fichero de texto.
		String st2=""; //String del segundo fichero de texto.
		//Se crean los FileRedader para leer los archivos.
		FileReader fr1, fr2; 
		fr1=new FileReader(f1);
		fr2=new FileReader(f2);
		//Se crean los bufferedReader para poder leer palabras.
		BufferedReader br1, br2;
		br1=new BufferedReader(fr1);
		br2=new BufferedReader(fr2);
		//Se crea el String del contenido del primer fichero.
		while((st=br1.readLine())!=null){
			st1= st1+st;
		}
		//Se crea el String del contenido del segundo fichero.
		while((st=br2.readLine())!=null){
			st2= st2+st;
		}
		br1.close();
		br2.close();
		//Realiza la comparación.
		if (st1.equals(st2)){
			System.out.print("Son iguales");
		}else{
			System.out.print("No son iguales");
		}
		
		fr1.close();
		fr2.close();
		}
	public static void buscaPalabra(File f, String palabraBuscar, boolean primero) 
			throws IOException{
		
		String st; //palabra auxiliar para la comparación.
		int pos=0; //Posición de aparición de la palabra en el archivo.
		int posf=0; //Posición final que corresponde a la busqueda.
		int esta=-1; //Variable para indicar si está o no la palabra.
		//Creamos el BufferedReader para leer las palabras del archivo.
		FileReader fr1=new FileReader(f);
		BufferedReader br1=new BufferedReader(fr1);
		//Bucle mientras hayan palabras, se sigue buscando.
		while((st=br1.readLine())!=null){
			pos=pos+1;
			if (palabraBuscar.equals(st)){
				//Se encuentra la primera coincidencia y si es la que se
				//solicita, se interrumpe la busqueda.
				if (primero){
					posf=pos;
					esta=0;
				break;
				//Si es la última, se sigue buscando hasta el final del archivo.
			}else{				
				posf=pos;}
				esta=0;
				}
		}
		// Si no se ha hayado la palabra, la variable que lo indica no varía.
		if (esta!=-1){
		System.out.print("La palabra: "+palabraBuscar+" se encuentra en la linea: "+posf+"\n");
		}else{
			System.out.print("La palabra: "+palabraBuscar+" no se encuentra en el fichero");

		}
		br1.close();
	}
}
