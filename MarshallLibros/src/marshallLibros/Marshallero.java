package marshallLibros;

import java.io.File;
import java.util.ArrayList;

public class Marshallero {

	public static void main(String[] args) {
		
		ArrayList<Libro> libreria;
		libreria=new ArrayList<Libro>();
		libreria.add(new Libro("La Biblia","Jesús",1,"Iglesia",666));
		libreria.add(new Libro("Decamerón","Dante",1000,"Infernal",999));
		
		Marshall m=new Marshall(libreria);
		m.crearDocumento();
		m.crearArbolDOM();
		
		File file=new File("libreria.xml");
		m.escribirDocumentoXml(file);
		
		
	}

}
