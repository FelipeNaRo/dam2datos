package marshallLibros;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Marshall {
	
	private Document doc=null;
	private ArrayList<Libro> libreria=null;

	public Marshall(ArrayList<Libro> l) {
		libreria=l;	}
	public void crearDocumento(){
		DocumentBuilderFactory dbf= DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db= dbf.newDocumentBuilder();
			doc=db.newDocument();
		} catch (ParserConfigurationException e) {
			System.err.print("Error en la realización del archivo DOM");
		}
		}
	public void crearArbolDOM(){
		Element docEle=doc.createElement("libreria");
		doc.appendChild(docEle);
		
		Iterator<Libro> it=libreria.iterator();
		while (it.hasNext()){
			Libro l=(Libro)it.next();
			Element libroEle=setLibro(l);
			docEle.appendChild(libroEle);
		}
	}
	private Element setLibro(Libro l){
		
		Element libroEle=doc.createElement("libro");
		
		Element tituloEle=doc.createElement("titulo");
		Text tituloTexto=doc.createTextNode(l.getTitulo());
		tituloEle.appendChild(tituloTexto);
		libroEle.appendChild(tituloEle);
		Element autorEle=doc.createElement("autor");
		Text autorTexto=doc.createTextNode(l.getAutor());
		autorEle.appendChild(autorTexto);
		libroEle.appendChild(autorEle);
		Element anyoEle=doc.createElement("anyo");
		Text anyoTexto=doc.createTextNode(Integer.toString(l.getAnyo()));
		anyoEle.appendChild(anyoTexto);
		libroEle.appendChild(anyoEle);
		Element editorEle=doc.createElement("editorial");
		Text editorTexto=doc.createTextNode(l.getEditor());
		editorEle.appendChild(editorTexto);
		libroEle.appendChild(editorEle);
		Element paginasEle=doc.createElement("paginas");
		Text paginasTexto=doc.createTextNode(Integer.toString(l.getNumPag()));
		paginasEle.appendChild(paginasTexto);
		libroEle.appendChild(paginasEle);
		
		return libroEle;
	}
	public void escribirDocumentoXml(File file){
		try {
			Transformer trans=TransformerFactory.newInstance().newTransformer();
			trans.setOutputProperty(OutputKeys.INDENT,"yes");
			
			StreamResult result=new StreamResult(file);
			DOMSource source=new DOMSource(doc);
			trans.transform(source, result);
			
		} catch (TransformerConfigurationException
				| TransformerFactoryConfigurationError e) {
			System.err.print("Error de creación de la factoria de transformación en XML");
		} catch (TransformerException e) {
			System.err.print("Error de transformación en XML");
		}
	}
}
