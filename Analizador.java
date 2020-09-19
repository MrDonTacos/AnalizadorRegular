package analizadorRegular;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analizador {

	private JFrame frame;
	private JTextField textField;
	private String oracion = "";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Analizador window = new Analizador();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Analizador() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 530, 373);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		ArrayList<Character> frase = new ArrayList<Character>();
		
		JTextPane textPane = new JTextPane();
		textPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				//Se verifica el input entrante no sea un Enter 
				if(e.getKeyChar() != '\n')
					//Se verifica si el input entrante es un backspace
					if(e.getKeyChar() == '\b')
					{	//En caso de serlo, elimina la última letra de la oración					
						if(oracion.length()>0)
						{
							StringBuffer sb = new StringBuffer(oracion); 
							sb.deleteCharAt(sb.length()-1);
							oracion = sb.toString();
						}
					}
					//En caso de no ser un backspace se concatena el valor entrante al string
					else
					oracion += e.getKeyChar();
				//En caso de que la tecla sea un enter, se verifica todo lo que se almacenó en la oración
				else
				{
					if(oracion.matches("([A-Za-z]|[_][A-Za-z])[\\w]+"))
					{
						if(oracion.matches("[A-Z]{4}[0-9]{6}[HM][A-Z]{5}[0-9]{2}"))
							textField.setText("Se ha validado una CURP");
						else
							textField.setText("Se ha validado una Variable");
					}
					else if(oracion.matches("([0][1-9]|[1-9]|[1][0-2])(:)([0-5][0-9])(\\s)?(am)|(pm)"))
					{
						textField.setText("Se ha validado una Hora");
					}
					else if(oracion.matches("[+-]?[0-9]*([.]([0]*[1-9]*|[1-9]*[0-9]*))*"))
					{
						if(oracion.matches("([0-1])*[1]([0-1])*[1]([0-1])*[1]([0-1])*[1]([0-1])*"))
						{
							textField.setText("Se ha validado un número Binario");
						}
						else
							textField.setText("Se ha validado un número Real");
					}
					else if(oracion.matches("[(]([2-9]{3}|[2-9]{2})[)][0-9]{3}[-][0-9]{4}"))
					{
						textField.setText("Se ha validado un número teléfonico de México");
					}
					else if(oracion.matches("[a-zA-Z]([\\w]|[\\.])*@[\\.]([A-Za-z]|[\\.])*[a-zA-Z]"))
					{
						textField.setText("Se ha validado un correo");
					}
					else
					{
						textField.setText("No coincide con ninguna de las validaciones anteriores");
					}
					oracion = "";
				}
			}
		});
		textPane.setBounds(12, 12, 372, 312);
		frame.getContentPane().add(textPane);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(396, 12, 114, 65);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}
}
