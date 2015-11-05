package parseLibros;

public class Parseador {

	public static void main(String[] args) {
	
		Parser parser=new Parser();
		parser.parseFicheroXml("libros.xml");
		parser.parseDocument();
		parser.print();

	}

}
