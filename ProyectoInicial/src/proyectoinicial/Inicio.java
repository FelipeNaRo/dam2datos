package proyectoinicial;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;

import java.io.File;
import java.io.IOException;

public class Inicio extends JFrame {

	private static final long serialVersionUID = 1L;
	private boolean op;
	private JPanel contentPane;
	private JTextField FOrigen;
	private JTextField FDestino;
	private final ButtonGroup opcion = new ButtonGroup();
	private JTextField retorno;

	public static void main(String[] args) {
		  Inicio frame = new Inicio();
		     frame.setVisible(true);
	}
	
	public Inicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titulo = new JLabel("ORDENADOR DE FICHEROS");
		titulo.setBounds(30, 34, 162, 34);
		contentPane.add(titulo);
		
		JLabel origen = new JLabel("Fichero a ordenar:");
		origen.setBounds(30, 66, 116, 14);
		contentPane.add(origen);
		
		FOrigen = new JTextField();
		FOrigen.setBounds(28, 79, 164, 20);
		contentPane.add(FOrigen);
		FOrigen.setColumns(10);
		
		JLabel destino = new JLabel("Fichero de destino:");
		destino.setBounds(30, 110, 116, 14);
		contentPane.add(destino);
		
		FDestino = new JTextField();
		FDestino.setColumns(10);
		FDestino.setBounds(30, 135, 164, 20);
		contentPane.add(FDestino);
		
		JLabel orden = new JLabel("Orden:");
		orden.setBounds(30, 166, 46, 14);
		contentPane.add(orden);
		
		JRadioButton natural = new JRadioButton("Natural");
		natural.setSelected(true);
		natural.setBounds(83, 162, 109, 23);
		contentPane.add(natural);
		
		JRadioButton inverso = new JRadioButton("Inverso");
		inverso.setBounds(83, 188, 109, 23);
		contentPane.add(inverso);
		opcion.add(natural);
		opcion.add(inverso);
		
		retorno=new JTextField();
		retorno.setEditable(false);
		retorno.setBounds(31, 235, 382, 20);
		contentPane.add(retorno);
		retorno.setColumns(10);
		
		JButton accion = new JButton("Ordenar");
		accion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String ficheroOrigen=FOrigen.getText();
				String ficheroDestino=FDestino.getText();
				if (natural.isSelected()){op=true;}else{
					op=false;
				}
				if (ficheroOrigen.equals("") || ficheroDestino.equals("")){
					retorno.setText("Falta algún fichero");
				}else{
					File Fo=new File(ficheroOrigen);
					File Fd=new File(ficheroDestino);
					try {Inicio.this.dispose();
						ordenarFicheros.ordenar(Fo,Fd,op);
					} catch (IOException e1) {
						e1.printStackTrace();
					}}
				
			}
			
		});
			accion.setBounds(276, 147, 125, 23);
			contentPane.add(accion);
}
	
	public void setRetorno(String s) {
		this.retorno.setText(s);
	}
	}
	
					
