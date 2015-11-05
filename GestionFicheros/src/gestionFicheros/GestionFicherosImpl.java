package gestionFicheros;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;

import gestionficheros.FormatoVistas;
import gestionficheros.GestionFicheros;
import gestionficheros.GestionFicherosException;
import gestionficheros.TipoOrden;

public class GestionFicherosImpl implements GestionFicheros {
	private File carpetaDeTrabajo = null;
	private Object[][] contenido;
	private int filas = 0;
	private int columnas = 3;
	private FormatoVistas formatoVistas = FormatoVistas.NOMBRES;
	private TipoOrden ordenado = TipoOrden.DESORDENADO;

	public GestionFicherosImpl() {
		carpetaDeTrabajo = File.listRoots()[0];
		actualiza();
	}

	private void actualiza() {

		String[] ficheros = carpetaDeTrabajo.list(); // obtener los nombres
		// calcular el número de filas necesario
		filas = ficheros.length / columnas;
		if (filas * columnas < ficheros.length) {
			filas++; // si hay resto necesitamos una fila más
		}

		// dimensionar la matriz contenido según los resultados

		contenido = new String[filas][columnas];
		// Rellenar contenido con los nombres obtenidos
		for (int i = 0; i < columnas; i++) {
			for (int j = 0; j < filas; j++) {
				int ind = j * columnas + i;
				if (ind < ficheros.length) {
					contenido[j][i] = ficheros[ind];
				} else {
					contenido[j][i] = "";
				}
			}
		}
	}

	@Override
	public void arriba() {

		System.out.println("holaaa");
		if (carpetaDeTrabajo.getParentFile() != null) {
			carpetaDeTrabajo = carpetaDeTrabajo.getParentFile();
			actualiza();
		}

	}

	@Override
	public void creaCarpeta(String arg0) throws GestionFicherosException {
		File file = new File(carpetaDeTrabajo,arg0);
		//que se pueda escribir -> lanzará una excepción
		if (carpetaDeTrabajo.canWrite()){
			//que no exista -> lanzará una excepción	
			if(!file.exists()){
				//crear la carpeta -> lanzará una excepción
				try{
				file.mkdir();
				}catch( SecurityException e){
					throw new GestionFicherosException( "El directorio "
							+file.getAbsolutePath()
							+" no ha podido ser creado");
				}
			}
			else{
				throw new GestionFicherosException("Error. El directorio "
						+ file.getAbsolutePath()
						+ " ya existe");
			}}else{
				throw new GestionFicherosException("Error. El directorio "
						+ carpetaDeTrabajo.getAbsolutePath()
						+ " no tiene permiso de escritura");
			}
		
		actualiza();
		
	}

	@Override
	public void creaFichero(String arg0) throws GestionFicherosException {
		File file = new File(carpetaDeTrabajo,arg0);
		//que se pueda escribir -> lanzará una excepción
		if (carpetaDeTrabajo.canWrite()){
			//que no exista -> lanzará una excepción	
			if(!file.exists()){
				//crear el archivo -> lanzará una excepción
				try {
					file.createNewFile();
				} catch (IOException e) {
					throw new GestionFicherosException("Error. No se ha creado el archivo "
							+ file.getAbsolutePath());
				}
			}
			else{
				throw new GestionFicherosException("Error. El archivo "
						+ file.getAbsolutePath()
						+ " ya existe");
			}}else{
				throw new GestionFicherosException("Error. El directorio "
						+ carpetaDeTrabajo.getAbsolutePath()
						+ " no tiene permiso de escritura");
			}
		
		actualiza();

	}

	@Override
	public void elimina(String arg0) throws GestionFicherosException {
		
		File file = new File(carpetaDeTrabajo,arg0);
		//que se pueda escribir -> lanzará una excepción
		if (carpetaDeTrabajo.canWrite()){
			//que no exista -> lanzará una excepción	
			if(file.exists()){
				// Comprueba que el directorio está vacio -> lanzará una excepción
				if (!file.isDirectory() || file.list().length==0){
					//crear la carpeta -> lanzará una excepción
				try{
				file.delete();
				}catch( SecurityException e){
					throw new GestionFicherosException( "El directorio "
							+file.getAbsolutePath()
							+" no ha podido ser creado");
				}
				}else{
					throw new GestionFicherosException( "El directorio "
							+file.getAbsolutePath()
							+" no esta vacío");
	
				}}
			
			else{
				throw new GestionFicherosException("Error. El directorio "
						+ file.getAbsolutePath()
						+ " ya existe");
			}}else{
				throw new GestionFicherosException("Error. El directorio "
						+ carpetaDeTrabajo.getAbsolutePath()
						+ " no tiene permiso de escritura");
			}
		
		actualiza();
	}

	@Override
	public void entraA(String arg0) throws GestionFicherosException {
		File file = new File(carpetaDeTrabajo, arg0);
		// se controla que el nombre corresponda a una carpeta existente
		if (!file.isDirectory()) {
			throw new GestionFicherosException("Error. Se ha encontrado "
					+ file.getAbsolutePath()
					+ " pero se esperaba un directorio");
		}
		// se controla que se tengan permisos para leer carpeta
		if (!file.canRead()) {
			throw new GestionFicherosException("Alerta. No se puede acceder a "
					+ file.getAbsolutePath() + ". No hay permiso");
		}
		// nueva asignación de la carpeta de trabajo
		carpetaDeTrabajo = file;
		// se requiere actualizar contenido
		actualiza();

	}

	@Override
	public int getColumnas() {
		return columnas;
	}

	@Override
	public Object[][] getContenido() {
		return contenido;
	}

	@Override
	public String getDireccionCarpeta() {
		return carpetaDeTrabajo.getAbsolutePath();
	}

	@Override
	public String getEspacioDisponibleCarpetaTrabajo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEspacioTotalCarpetaTrabajo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getFilas() {
		return filas;
	}

	@Override
	public FormatoVistas getFormatoContenido() {
		return formatoVistas;
	}

	@Override
	public String getInformacion(String arg0) throws GestionFicherosException {
		
		StringBuilder strBuilder=new StringBuilder();
		File file = new File(carpetaDeTrabajo,arg0);
		
		//Controlar que existe. Si no, se lanzará una excepción
		
		if (!file.exists()) {
			throw new GestionFicherosException("Error. El fichero "
					+ file.getAbsolutePath()
					+ " no es válido");
		}
		//Controlar que haya permisos de lectura. Si no, se lanzará una excepción
		
		if (!file.canRead()) {
			throw new GestionFicherosException("No tienes permiso de lectura para: "
					+ file.getAbsolutePath());
		}
		
		//Título
		strBuilder.append("INFORMACIÓN DEL SISTEMA");
		strBuilder.append("\n\n");
		
		//Nombre
		strBuilder.append("Nombre: ");
		strBuilder.append(arg0);
		strBuilder.append("\n");
		
		//Tipo: fichero o directorio
		
		strBuilder.append("Tipo de fichero: ");
		// Comprobamos el tipo de fichero y nos escribirá una opción según sea.
		if (file.isDirectory()){
			strBuilder.append("Directorio");
		}else{
			strBuilder.append("Archivo");
		}
		strBuilder.append("\n");
		
		//Ubicación
		
		strBuilder.append("Ubicación: ");
		strBuilder.append(file.getPath());
		strBuilder.append("\n");
		
		//Fecha de última modificación
		
		strBuilder.append("Última modificación: ");
		// Creamos un objeto DateFormat, para dar formato al tiempo que nos
		// da en milisegundos a fecha.
		
		Date tiempo= new Date(file.lastModified());
		DateFormat t= DateFormat.getDateInstance(DateFormat.FULL);
		String ti=t.format(tiempo);
		strBuilder.append(ti);
		strBuilder.append("\n");
		
		//Si es un fichero oculto o no
		
		strBuilder.append("Oculto: ");
		if (file.isHidden()){
			strBuilder.append("Si");
		}else {
			strBuilder.append("No");
		}
		strBuilder.append("\n");
		
		//Si es directorio: Espacio libre, espacio disponible, espacio total
		//bytes
		//Para que no salga un numero dificil de leer creamos un objeto DecimalFormat
		//para dar formato legible a los numeros tan grandes de bytes.
		
		DecimalFormat formateador = new DecimalFormat(",###.#");
		
		if(file.isDirectory()){
			
			// obtiene el array de ficheros del directorio y cuenta los archivos que contiene.
			// Si es null el array captura el error y da valor 0 al numero de archivos.
		
			try{
			strBuilder.append("Archivos: "+file.list().length);
			}catch(NullPointerException e){
				strBuilder.append("Archivos: 0");
			}
			strBuilder.append("\n");
			strBuilder.append("Espacio libre: ");
			// Obtiene el espacio libre y le da formato antes de añadir
			strBuilder.append(formateador.format(file.getFreeSpace())+" bytes");
			strBuilder.append("\n");
			strBuilder.append("Espacio Disponible: ");
			// Obtiene el espacio disponible y le da formato antes de añadir
			strBuilder.append(formateador.format(file.getUsableSpace())+" bytes");
			strBuilder.append("\n");
			strBuilder.append("Espacio Total: ");
			// Obtiene el espacio total y le da formato antes de añadir
			strBuilder.append(formateador.format(file.getTotalSpace())+" bytes");
		}else{
			//Obtiene el tamaño y le da formato antes de añadir
			strBuilder.append("Tamaño: ");
			strBuilder.append(formateador.format(file.length())+" bytes");
		}
		
		return strBuilder.toString();
	}

	@Override
	public boolean getMostrarOcultos() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getNombreCarpeta() {
		return carpetaDeTrabajo.getName();
	}

	@Override
	public TipoOrden getOrdenado() {
		return ordenado;
	}

	@Override
	public String[] getTituloColumnas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getUltimaModificacion(String arg0)
			throws GestionFicherosException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String nomRaiz(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int numRaices() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void renombra(String arg0, String arg1)
			throws GestionFicherosException {
		// creamos dos objetos File, uno para el archivo origen y 
		// otro para el archivo destino.
		File file = new File(carpetaDeTrabajo,arg0);
		File file1 = new File(carpetaDeTrabajo,arg1);
		//que se pueda escribir -> lanzará una excepción
		if (carpetaDeTrabajo.canWrite()){
			//que no exista el fichero a renombrar-> lanzará una excepción
			if (file.exists()){
				//que no exista ya el fichero de destino -> lanzará una excepción
			if(!file1.exists()){
				//renombrar -> lanzará una excepción
				try{
				file.renameTo(file1);
				}catch( SecurityException e){
					throw new GestionFicherosException( "El directorio/fichero "
							+file.getAbsolutePath()
							+" no ha podido renombrado");
				}
			}
			else{
				throw new GestionFicherosException("Error. El directorio/fichero "
						+ file1.getAbsolutePath()
						+ " ya existe");
			}
			}else{
				throw new GestionFicherosException("Error. El directorio/fichero "
						+ file.getAbsolutePath()
						+ " no existe");
			}}else{
				throw new GestionFicherosException("Error. El directorio "
						+ carpetaDeTrabajo.getAbsolutePath()
						+ " no tiene permiso de escritura");
			}
		
		actualiza();


	}

	@Override
	public boolean sePuedeEjecutar(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sePuedeEscribir(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sePuedeLeer(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setColumnas(int arg0) {
		columnas = arg0;

	}

	@Override
	public void setDirCarpeta(String arg0) throws GestionFicherosException {
		File file = new File(arg0);

		// se controla que la dirección exista y sea directorio
		if (!file.isDirectory()) {
			throw new GestionFicherosException("Error. Se esperaba "
					+ "un directorio, pero " + file.getAbsolutePath()
					+ " no es un directorio.");
		}

		// se controla que haya permisos para leer carpeta
		if (!file.canRead()) {
			throw new GestionFicherosException(
					"Alerta. No se puede acceder a  " + file.getAbsolutePath()
							+ ". No hay permisos");
		}

		// actualizar la carpeta de trabajo
		carpetaDeTrabajo = file;

		// actualizar el contenido
		actualiza();

	}

	@Override
	public void setFormatoContenido(FormatoVistas arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMostrarOcultos(boolean arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setOrdenado(TipoOrden arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSePuedeEjecutar(String arg0, boolean arg1)
			throws GestionFicherosException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSePuedeEscribir(String arg0, boolean arg1)
			throws GestionFicherosException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSePuedeLeer(String arg0, boolean arg1)
			throws GestionFicherosException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setUltimaModificacion(String arg0, long arg1)
			throws GestionFicherosException {
		// TODO Auto-generated method stub

	}

}
