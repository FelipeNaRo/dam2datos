package almacen_libros;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Almacen_Libros {

	Scanner s=new Scanner(System.in);
	String dato="";
	
	public void Seleccion(int op){
		switch (op){
		case 1: 
			//System.out.print("¿En que fichero quieres guardar el libro?: ");
			dato=confirmarArchivo();
			guardarLibro(GenerarLibro(),dato);
		break;
		case 2: int total=0;
				System.out.print("¿Cuantos libros vas a guardar?: ");
				total=s.nextInt();
				s.reset();
				System.out.print("¿En que fichero quieres guardar el libro?: ");
				dato=confirmarArchivo();
				ArrayList <Libros> libreria =new ArrayList<Libros>();
				for (int i=0;i<total;i++){
					libreria.add(GenerarLibro());	
				}
				guardarLibro(libreria,dato);
		break;
		case 3:
			System.out.print("¿De que fichero quieres sacar el libro?: ");
			dato=s.nextLine();
			mostrarLibro(dato);
		break;
		case 4: System.out.print("¿En que fichero quieres modificar el libro?: ");
		dato=s.nextLine();
		modificarLibro(dato);
		break;
		default: System.out.print("Pareces tonto. Recuerda, del 1 al 5");
		Principal.main(null);
		}
		Principal.main(null);
	}
	public Libros GenerarLibro(){
		
		int ae=0;
		int np=0;
		String valor=null;
		String t=null;
		String a=null;
		String e=null;
		System.out.println("Dime el Título: ");
		t=introducirDatos();
		System.out.println("Dime el Autor: ");
		a=introducirDatos();
		System.out.println("Dime el año de edición: ");
		valor=introducirDatos();
		ae=Integer.parseInt(valor);
		System.out.println("Dime el Editor: ");
		e=introducirDatos();
		System.out.println("Dime el número de páginas: ");
		valor=introducirDatos();
		np=Integer.parseInt(valor);
		Libros libro=new Libros(t,a,ae,e,np);
		
		return libro;
	}
	private void guardarLibro(Object l, String f){
		
		ObjectOutputStream graba=null;
		
		try {
			graba=new ObjectOutputStream(new FileOutputStream(f));
			graba.writeObject(l);
			intentarCerrar(graba);
		} catch (FileNotFoundException e) {
			System.err.print("No se encuentra el fichero "+f);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.print("Error introduciendo datos en el fichero "+f);
			e.printStackTrace();
		}
	}
	private void mostrarLibro(String f){
		Libros l=null;
		ObjectInputStream saca=null;
		try {
			saca=new ObjectInputStream(new FileInputStream(f));
				l=(Libros) saca.readObject();
				l.print();
		} catch (FileNotFoundException e) {
			System.err.println("No existe este fichero: "+f);
		} catch (IOException e) {
			System.err.println("Fallo intentando leer el fichero: "+f);
		} catch (ClassNotFoundException e) {
			System.err.println("No se encuentra la clase Libros en el fichero: "+f);
		}
	}
	private Libros sacarLibroModificar(String f){
		Libros l=null;
		ObjectInputStream saca=null;
		try {
			saca=new ObjectInputStream(new FileInputStream(f));
				l=(Libros) saca.readObject();
				System.out.println("Este es el libro a modificar: ");
				l.print();	
		} catch (FileNotFoundException e) {
			System.err.println("No existe este fichero: "+f);
		} catch (IOException e) {
			System.err.println("Fallo intentando leer el fichero: "+f);
		} catch (ClassNotFoundException e) {
			System.err.println("No se encuentra la clase Libros en el fichero: "+f);
		}
		intentarCerrar(saca);
    return l;
		
	}
	public void intentarCerrar(Closeable c){
		
		if (c!=null){
			try {
				c.close();
			} catch (IOException e) {
				System.err.print("Error cerrando... ");
				e.printStackTrace();
			}
		}
	}
	
	private String confirmarArchivo(){
		String con=null;
		String f=null;
		do{
		 System.out.println("Dime el nombre del archivo: ");
		 f=s.nextLine();
		 System.out.println("Confirma el nombre del archivo ");
		 System.out.println(f+" es correcto?: ");
		 con=s.nextLine();
		}
		 while (!con.toUpperCase().equals("S"));
		 return f;	
	}

	private void modificarLibro(String f){
	Almacen_Libros a=new Almacen_Libros();
	Libros l=null;	
	l=a.sacarLibroModificar(f);
	
	System.out.println("Dime el Nuevo Título del libro: ");
	String titulo=s.nextLine();
	if (!titulo.equals("")){
		l.setTitulo(titulo);
	}
	System.out.println("Dime el Autor nuevo: ");
	String autor=s.nextLine();
	if (!autor.equals("")){
		l.setAutor(autor);
	}
	l.print();
	a.guardarLibro(l, f);
	System.out.println("Voy a guardar el libro de título: "+titulo+
			"\nY con autor: "+autor);

	}
	
	public String introducirDatos(){
		String datos="";
		while (datos.equals("")){
		datos=s.nextLine();}
		return datos;
	}
}
