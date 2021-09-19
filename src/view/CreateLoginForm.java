package view;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.*;

import service.AES;
import service.RSA;

import java.awt.*;
import java.awt.event.*;
import java.io.UnsupportedEncodingException;
import java.lang.Exception;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

//create CreateLoginForm class to create login form  
//class extends JFrame to create a window where our component add  
//class implements ActionListener to perform an action on button click  
public class CreateLoginForm extends JFrame implements ActionListener {
	// initialize button, panel, label, and text field
	JButton b1;
	JPanel newPanel;
	JLabel userLabel, passLabel;
	final JTextField textField1, textField2;

	// calling constructor
	public CreateLoginForm() {

		// create label for username
		userLabel = new JLabel();
		userLabel.setText("Username"); // set label value for textField1

		// create text field to get username from the user
		textField1 = new JTextField(15); // set length of the text

		// create label for password
		passLabel = new JLabel();
		passLabel.setText("Password"); // set label value for textField2

		// create text field to get password from the user
		textField2 = new JPasswordField(15); // set length for the password

		// create submit button
		b1 = new JButton("SUBMIT"); // set label to button

		// create panel to put form elements
		newPanel = new JPanel(new GridLayout(3, 1));
		newPanel.add(userLabel); // set username label to panel
		newPanel.add(textField1); // set text field to panel
		newPanel.add(passLabel); // set password label to panel
		newPanel.add(textField2); // set text field to panel
		newPanel.add(b1); // set button to panel

		// set border to panel
		add(newPanel, BorderLayout.CENTER);

		// perform action on button click
		b1.addActionListener(this); // add action listener to button
		setTitle("LOGIN FORM"); // set title to the login form
	}

	// define abstract method actionPerformed() which will be called on button click
	public void actionPerformed(ActionEvent ae) // pass action listener as a parameter
	{
		String userValue = textField1.getText(); // get user entered username from the textField1
		String passValue = textField2.getText(); // get user entered pasword from the textField2

		final String claveEncriptacion = "secreto!";
		String datosOriginales = userValue + ',' + passValue;
		/**
		 * encriptacion simetrica
		 */
		AES encriptador = new AES();

		String encriptado;
		try {
			encriptado = encriptador.encriptar(datosOriginales, claveEncriptacion);
			String desencriptado = encriptador.desencriptar(encriptado, claveEncriptacion);
			System.out.println("Cadena Original: " + datosOriginales);
			System.out.println("Escriptado     : " + encriptado);
			System.out.println("Desencriptado  : " + desencriptado);
		} catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException
				| IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/**
		 * encriptacion asimetrica
		 */

	        
		RSA rsa = new RSA();
		//Generamos un par de claves
     //Admite claves de 512, 1024, 2048 y 4096 bits
     try {
		rsa.genKeyPair(512);
	     
	     String file_private = "/tmp/rsa.pri";
	     String file_public = "/tmp/rsa.pub";
	     
	     //Las guardamos asi podemos usarlas despues
	     //a lo largo del tiempo

	     
	     //Ciframos y e imprimimos, el texto cifrado
	     //es devuelto en la variable secure
	     String secure = rsa.Encrypt(datosOriginales);
	     
	     System.out.println("\nCifrado:");
	     System.out.println(secure);
	     
	             
	     
	  
	     
	     //A diferencia de la anterior aca no creamos
	     //un nuevo par de claves, sino que cargamos
	     //el juego de claves que habiamos guadado

	     
	     //Le pasamos el texto cifrado (secure) y nos 
	     //es devuelto el texto ya descifrado (unsecure) 
	     String unsecure = rsa.Decrypt(secure);
	     
	     //Imprimimos
	     System.out.println("\nDescifrado:");
	     System.out.println(unsecure); 
	} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
			| BadPaddingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvalidKeySpecException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NoSuchProviderException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     
  
     
	}
}

//create the main class  
class LoginFormDemo {
	// main() method start
	public static void main(String arg[]) {
		try {
			// create instance of the CreateLoginForm
			CreateLoginForm form = new CreateLoginForm();
			form.setSize(300, 100); // set size of the frame
			form.setVisible(true); // make form visible to the user
		} catch (Exception e) {
			// handle exception
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
