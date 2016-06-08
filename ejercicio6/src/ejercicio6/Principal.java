package ejercicio6;
import java.util.Scanner;


public class Principal {
	

	public static void main(String[] args) {
		GestionPersistencia gp=new GestionPersistencia();
		Scanner s=new Scanner(System.in);
		Scanner s1=new Scanner(System.in);
		int dato=0;
		String nombre="";
		String dni="";
		int edad=0;
		dato=s.nextInt();
		switch (dato){
		case 1:
			System.out.println("Dime el nombre: ");
			nombre=s1.nextLine();
			System.out.println("Dime la edad: ");
			edad=s.nextInt();
			System.out.println("Dime el dni: ");
			dni=s1.nextLine();
			Persona p= new Persona(nombre,edad,dni);
			gp.insertarPersona(p);
		break;
		case 2:
			System.out.println("Dime el nombre: ");
			nombre=s1.nextLine();
			gp.RecuperarPersonaPorNombre(nombre);
			break;
		case 3:
			System.out.println("Dime la Inicial: ");
			nombre=s1.nextLine();
			gp.RecuperarPersonaPorInicial(nombre);
			break;
			
		case 4:
			System.out.println("Dime el Dni: ");
			dni=s1.nextLine();
			gp.recuperarPersonaPorDNI(dni);
			break;
		case 5:
			System.out.println("Dime la edad menor: ");
			int menor=s.nextInt();			
			System.out.println("Dime la edad mayor: ");
			int mayor=s.nextInt();
			gp.recuperarPersonaPorEdad(menor, mayor);
		case 6:
			gp.recuperarPersonaAvanzada();
			
		}
		s.close();
		s1.close();
	}
}
