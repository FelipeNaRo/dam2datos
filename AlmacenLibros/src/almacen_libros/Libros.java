package almacen_libros;

import java.io.Serializable;

public class Libros implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	String titulo=null;
	String autor=null;
	int anyo=0;
	String editor=null;
	int numPag=0;
public 	Libros(){
	
}

public Libros(String t, String a, int ae, String e, int np){
	
	titulo=t;
	autor=a;
	anyo=ae;
	editor=e;
	numPag=np;
				
}

public String getTitulo() {
	return titulo;
}

public void setTitulo(String titulo) {
	this.titulo = titulo;
}

public String getAutor() {
	return autor;
}

public void setAutor(String autor) {
	this.autor = autor;
}

public int getAnyo() {
	return anyo;
}

public void setAnyo(int anyo) {
	this.anyo = anyo;
}

public String getEditor() {
	return editor;
}

public void setEditor(String editor) {
	this.editor = editor;
}

public int getNumPag() {
	return numPag;
}

public void setNumPag(int numPag) {
	this.numPag = numPag;
}
public void print(){
	System.out.println("Título : "+titulo+"\nautor : "+autor+"\naño de edición : "+anyo+
			"\neditor : "+editor+"\nnúmero de páginas : "+numPag);
}
}
