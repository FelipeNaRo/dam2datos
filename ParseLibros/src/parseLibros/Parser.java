package parseLibros;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Parser {
	
	private Document doc=null;
	private ArrayList<Libro> libreria=null;
	
	public Parser(){
		libreria=new ArrayList<Libro>();
	}
	public void parseFicheroXml(String fichero){
	DocumentBuilderFactory dbf= DocumentBuilderFactory.newInstance();
	DocumentBuilder db;
	try {
		db= dbf.newDocumentBuilder();
		try {
			doc=db.parse(fichero);
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public void parseDocument(){
		Element docEle=doc.getDocumentElement();
		NodeList nl=docEle.getElementsByTagName("Libro");
		if (nl != null && nl.getLength()>0){
			for (int i=0;i<nl.getLength();i++){
				Element el=(Element)nl.item(i);
				Libro l=getLibro(el);
				libreria.add(l);
				}
			}
	}
	private Libro getLibro(Element libroEle){
		String nombre= getTextValue(libroEle,"titulo");
		String autor= getTextValue(libroEle,"autor");
		int anyo=getIntValue(libroEle,"anyo");
		String editorial= getTextValue(libroEle,"editorial");
		int paginas=getIntValue(libroEle,"paginas");
		Libro libro=new Libro(nombre,autor,anyo,editorial,paginas);
		return libro;
	}
	private String getTextValue(Element ele, String tagname){
		String texVal=null;
		NodeList nl = ele.getElementsByTagName(tagname);
		if(nl != null && nl.getLength()>0){
			Element el=(Element)nl.item(0);
			texVal=el.getFirstChild().getNodeValue();
		}
		return texVal;
	}
	 
	private int getIntValue(Element ele, String tagname){
		return Integer.parseInt(getTextValue(ele,tagname));
	}
	public void print(){
		Iterator<Libro> it=libreria.iterator();
		while (it.hasNext()){
			Libro l=(Libro) it.next();
			l.print();
		}
	}
}
