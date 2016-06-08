package ejercicio6;

import java.util.ArrayList;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Constraint;
import com.db4o.query.Query;

public class GestionPersistencia {
	ObjectContainer bbdd;
	
	public void insertarPersona(Persona p){
		ObjectContainer bbdd= Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),"prueba.db4o");
		
try{
			bbdd.store(p);
			bbdd.commit();
			System.out.println(" Se ha guardado correctamente ");
			
		}finally{
			bbdd.close();
		}
	}

	public void RecuperarPersonaPorNombre(String nombre){
		bbdd= Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),"prueba.db4o");
		
		try{
					Query q=bbdd.query();
					q.constrain(Persona.class);
					q.descend("nombre").constrain(nombre);
					ObjectSet <Persona> res=q.execute();
					for (Persona p2: res){
					p2.print();
					}
				}finally{
					bbdd.close();
				}
	}
	public ArrayList<Persona> RecuperarPersonaPorInicial(String inicial){
		ArrayList<Persona> listaPersonas = new ArrayList<Persona>();
		bbdd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "prueba.db4o");
		
		try{
			Query q = bbdd.query();
			q.constrain(Persona.class);
			q.descend("nombre").constrain(inicial).startsWith(true);
			
			ObjectSet<Persona> res = q.execute();
			
			for(Persona p2: res){
				p2.print();
				listaPersonas.add(p2);
			}
		}
		finally{
			bbdd.close();
		}
		
		return listaPersonas;
	}
	public Persona recuperarPersonaPorDNI(String dni){
		bbdd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "prueba.db4o");
		Persona persona = null;
		
		try{
			Query q = bbdd.query();
			q.constrain(Persona.class);
			q.descend("dni").constrain(dni);
			
			ObjectSet<Persona> res = q.execute();
			
			for(Persona p2: res){
				p2.print();
				persona = p2;
			}
		}
		finally{
			bbdd.close();
		}
		
		return persona;
	} 
	
	public ArrayList<Persona> recuperarPersonaPorEdad(int menor, int mayor){
		ArrayList<Persona> listaPersonas = new ArrayList<Persona>();
		bbdd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "prueba.db4o");
		
		try{
			Query consulta = bbdd.query();
			consulta.constrain(Persona.class);
			Constraint c1 = consulta.descend("edad").constrain(menor).greater();
			Constraint c2 = consulta.descend("edad").constrain(mayor).smaller();
			c1.and(c2);
			
			ObjectSet<Persona> resultado = consulta.execute();
			
			for(Persona p2: resultado){
				p2.print();
				listaPersonas.add(p2);
			}
		}
		finally{
			bbdd.close();
		}
		
		return listaPersonas;
	}
	
	public ArrayList<Persona> recuperarPersonaAvanzada(){
		ArrayList<Persona> listaPersonas = new ArrayList<Persona>();
		bbdd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "prueba.db4o");
		
		try{
			Query consulta = bbdd.query();
			consulta.constrain(Persona.class);
			Constraint c1 = consulta.descend("edad").constrain(30).greater();
			Constraint c2 = consulta.descend("dni").constrain("20").startsWith(true);
			Constraint c3 = consulta.descend("nombre").constrain("o").endsWith(true);
			c1.or(c2).or(c3);
			
			ObjectSet<Persona> resultado = consulta.execute();
			
			for(Persona p2: resultado){
				p2.print();
				listaPersonas.add(p2);
			}
		}
		finally{
			bbdd.close();
		}
		
		return listaPersonas;
	}
}
