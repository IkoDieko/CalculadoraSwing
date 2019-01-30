//Estrada Guadarrama Diego 6IM8
package diagnostico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Calculadora extends JFrame {

	private static final long serialVersionUID = 1583724102189855698L;
	/** numero tecleado */
	JTextField pantalla;
	/** guarda el resultado de la operacion anterior o el número tecleado */
	double resultado;
        double resultado2;
	/** para guardar la operacion a realizar */
	String operacion;
	/** Los paneles donde colocaremos los botones */
	JPanel panelNumeros, panelOperaciones;
	/** Indica si estamos iniciando o no una operación */
	boolean nuevaOperacion = true;
	/* Constructor. Crea los botones y componentes de la calculadora */
	public Calculadora() {
		super();
		setSize(400, 500);
		setTitle("D I A G N O S T I C O");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);

		// Se van a poner las cosas en el siguiente panel
		JPanel panel = (JPanel) this.getContentPane();
		panel.setLayout(new BorderLayout());

		pantalla = new JTextField("", 20);
		pantalla.setBorder(new EmptyBorder(20, 20, 20, 20));
		pantalla.setFont(new Font("Arial", Font.BOLD, 50));
		pantalla.setHorizontalAlignment(JTextField.RIGHT);
		pantalla.setEditable(false);
		pantalla.setBackground(Color.WHITE);
		panel.add("North", pantalla);

		panelNumeros = new JPanel();
		panelNumeros.setLayout(new GridLayout(4, 3));
		panelNumeros.setBorder(new EmptyBorder(4, 4, 4, 4));
                
                

		for (int n = 9; n >= 0; n--) {
			nuevoBotonNumerico("" + n);
		}
		nuevoBotonNumerico(".");
                nuevoBotonNumerico("DEL");
		
		panel.add("Center", panelNumeros);

		panelOperaciones = new JPanel();
		panelOperaciones.setLayout(new GridLayout(6, 1));
		panelOperaciones.setBorder(new EmptyBorder(4, 4, 4, 4));
                panelOperaciones.setBackground(Color.red);

		nuevoBotonOperacion("+");
		nuevoBotonOperacion("√");
		nuevoBotonOperacion("-");
		nuevoBotonOperacion("sen");
                nuevoBotonOperacion("*");
                nuevoBotonOperacion("cos");
                nuevoBotonOperacion("/");
                nuevoBotonOperacion("tan");
                nuevoBotonOperacion("^");
                
		nuevoBotonOperacion("CLEAR");
                nuevoBotonOperacion("=");

		panel.add("East", panelOperaciones);
		validate();
	}

	/** Crea un boton del teclado numérico y enlaza sus eventos con el listener correspondiente */
	private void nuevoBotonNumerico(String digito) {
		JButton btn = new JButton();
		btn.setText(digito);
                btn.setBackground(Color.GRAY);
                btn.setFont(new Font("Arial", Font.BOLD, 14));
		btn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent evt) {
				JButton btn = (JButton) evt.getSource();
				numeroPulsado(btn.getText());
			}
		});

		panelNumeros.add(btn);
	}

	/** Crea un botón de operacion y lo enlaza con sus eventos */
	private void nuevoBotonOperacion(String operacion) {
		JButton btn = new JButton(operacion);
		btn.setForeground(Color.DARK_GRAY);
                btn.setBackground(Color.pink);
                btn.setFont(new Font("Arial", Font.BOLD, 15));

		btn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent evt) {
				JButton btn = (JButton) evt.getSource();
				operacionPulsado(btn.getText());
			}
		});

		panelOperaciones.add(btn);
	}

	/** Gestiona las pulsaciones de teclas numéricas */
	private void numeroPulsado(String digito) {
            int cant = 1;
             int m = Math.max(0, pantalla.getText().length() - cant); 
                if (digito.equals("DEL")){
                    pantalla.setText(pantalla.getText().substring(0, pantalla.getText().length()-cant));
                }  else if (pantalla.getText().equals("0") || nuevaOperacion) {
			pantalla.setText(digito);
		} else {
			pantalla.setText(pantalla.getText() + digito);
		}
		nuevaOperacion = false;
	}

	/** Gestiona el gestiona las pulsaciones de teclas de operación */
	private void operacionPulsado(String tecla) {
            
		if (tecla.equals("=")) {
                    if (pantalla.getText().equals("")){
                        JOptionPane.showMessageDialog(panelNumeros, "Introducir un numero.");
                    } else {
			calcularResultado();
                    }
		} else if (tecla.equals("CLEAR")) {
			resultado = 0;if (pantalla.getText().equals("")){
                }
			pantalla.setText("");
			nuevaOperacion = true;
		} 
                else {
			operacion = tecla;
			if ((resultado > 0) && !nuevaOperacion) {
				calcularResultado();
			} else {
				resultado = new Double(pantalla.getText());
			}
		}

		nuevaOperacion = true;
                
                
	}

	/** Calcula el resultado y lo muestra por pantalla */
	private void calcularResultado() {
		if (operacion.equals("+")) {
			resultado += new Double(pantalla.getText());
		} else if (operacion.equals("-")) {
			resultado -= new Double(pantalla.getText());
		} else if (operacion.equals("/")) {
                    if (pantalla.getText().equals("0")){
                        JOptionPane.showMessageDialog(panelNumeros, "Error.");
                        pantalla.setText("");
                        nuevaOperacion = true;
                    } else{
			resultado /= new Double(pantalla.getText());
                    }
		} else if (operacion.equals("*")) {
			resultado *= new Double(pantalla.getText());
		} else if (operacion.equals("sen")){
                        resultado = Math.sin(Math.toRadians(new Double(pantalla.getText())));
                } else if (operacion.equals("cos")){
                        resultado = Math.cos(Math.toRadians(new Double(pantalla.getText())));
                } else if (operacion.equals("tan")){
                        resultado = Math.tan(Math.toRadians(new Double(pantalla.getText())));
                } else if (operacion.equals("^")){
                        resultado = Math.pow(resultado, new Double(pantalla.getText()));
                } else if (operacion.equals("√")){
                        resultado = Math.sqrt(new Double(pantalla.getText()));
                } else if (operacion.equals("arctan")){
                        resultado = Math.atan(new Double(pantalla.getText()));
                }

		pantalla.setText("" + resultado);
		operacion = "";
	}
}
