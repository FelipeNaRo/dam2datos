package proyectoinicial;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ordenarFicheros {

public static void ordenar(File arch,File arch2,boolean op) throws IOException{

	
		String linea;
		boolean orden;
		ArrayList<String> lineas = new ArrayList<String>();
		ArrayList<String> lineasI = new ArrayList<String>();
		FileReader fr = new FileReader (arch);
		BufferedReader br = new BufferedReader(fr);
		FileWriter fw = new FileWriter(arch2);
		BufferedWriter bw = new BufferedWriter(fw);
		orden=op;
		
		try {
		while((linea=br.readLine())!=null){
		lineas.add(linea);
		}
		Collections.sort(lineas);
		for (int j = 0 ; j < lineas.size() ; j++){
			linea = lineas.get(j);
			if (orden){
			bw.write(linea);
			bw.newLine();}else{
			lineasI.add(linea);}
			}
		if(!orden){
		Collections.reverse(lineasI);
		for (int j = 0 ; j < lineasI.size() ; j++){
			linea = lineasI.get(j);
			bw.write(linea);
			bw.newLine();
			}
			}
		bw.close();
		fw.close();
		Inicio i=new Inicio();
		i.setVisible(true);
		i.setRetorno("Operación realizada con éxito");
			}//FIN DEL TRY
			catch(IOException e){
			System.out.println(e);
			}
			finally{
			try{ //el bloque finally se ejecuta siempre, por eso, si se cierra el fichero
			if( fr != null){ //al final del primer try, y ha dado un error antes, pasaría
			fr.close(); //al 1er catch y luego saldría, dejándolo abierto. Es conveniente
			} //cerrarlo aquí, comprobando que no sea -por un error anterior, como
			}catch (IOException e){ // no tener permisos de lectura o que no exista - de valor null.
			}
			}
		}

}
