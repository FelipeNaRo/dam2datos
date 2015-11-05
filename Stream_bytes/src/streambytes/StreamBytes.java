package streambytes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class StreamBytes{
	
	/*Definir el perfil que se desee para esta función*/
	public void copiaBytes(byte[] a, String f) {
		
		FileOutputStream fo=null;
		// Intenta crear el FileOutputStream del fichero destino
		try {
			fo=new FileOutputStream(f);
		//Si no encuentra el fichero lanza el correspondiente error.
		} catch (FileNotFoundException e) {
			System.err.print("No existe el fichero");
			e.printStackTrace();
		}
		// Intenta escribir el byte en el fichero.
		try {
			fo.write(a);
		// Si hay un error en la escritura lanza el error.
		} catch (IOException e) {
			System.err.print("Error al intentar escribir en el fichero");
			e.printStackTrace();
		}finally{
			// intenta cerrar el Stream de salida para que grabe el byte.
			try {
				fo.close();
			} catch (IOException e) {
				System.err.print("Error al intentar cerrar el stream para escribir datos.");
				e.printStackTrace();
			}

		}


	}
	
	/*Definir el perfil que se desee para esta función*/
	public void abrirFichero(byte[] a, String f)  {
		
		FileInputStream fi=null;
		
		// Intenta crear el FileInputStream del fichero origen
		try {
			fi=new FileInputStream(f);
			
			// Intenta leer el byte del fichero.
			try {	
				fi.read(a);
				// Si hay un error en la escritura lanza el error.
			} catch (IOException e) {
				System.err.print("Fallo al intentar leer el fichero");
				e.printStackTrace();
			}
			//Si no encuentra el fichero lanza el correspondiente error.
		} catch (FileNotFoundException e) {
			System.err.print("No se encuentra el fichero");
			e.printStackTrace();
		}finally{
			try {
				// intenta cerrar el Stream de lectura para que no quede abierto.

				fi.close();
			} catch (IOException e) {
				System.err.print("Fallo al intentar cerrar el stream de lectura");
				e.printStackTrace();
			}

		}
 
	}
	
	public void copiaBytes (String entrada, String salida){
		File fichero=null;
		int longitud=0;
		int cont=0;
		// Creamos la clase File del fichero origen para poder averiguar su longitud en bytes
		fichero=new File(entrada);
		longitud=(int)fichero.length();
		// Creamos un objeto StreamBytes para poder utilizar los métodos.
		StreamBytes a=new StreamBytes();
		// Bucle para ir leyendo los bytes del archivo 1 a 1
		for ( int i=0;i<=longitud;i++){
			// Array donde se guardarán los bytes cada vez que se lean
			byte[] b=new byte[i];
			// Lee el byte a copiar
				a.abrirFichero(b,entrada);
			// Copia el byte
				a.copiaBytes(b,salida);
				cont++;
		}
		
		
		System.out.print("\n Copia completada byte a byte con un total de: "+cont+" bytes");}
		
	}


