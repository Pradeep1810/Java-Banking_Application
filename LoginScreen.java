

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.SwingConstants;
import javax.swing.JToggleButton;
import javax.swing.JPasswordField;
import javax.management.StringValueExp;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginScreen {

	public JFrame loginFrame;
	private JTextField emailValue;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen window = new LoginScreen();
					window.loginFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	Connection conn = null;
	private void initialize() {
		
		conn = DatabaseConnection.dbconnet();  
		
		loginFrame = new JFrame();
		loginFrame.getContentPane().setBackground(Color.PINK);
		loginFrame.setSize(681, 411);
		loginFrame.setLocationRelativeTo(null);
		loginFrame.setResizable(false);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.getContentPane().setLayout(null);
		
		String hint1 = "Email - ID ";
		emailValue = new JTextField(hint1);
		emailValue.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(emailValue.getText().equals(hint1))
				{
					emailValue.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				if(emailValue.getText().equals(""))
				{
					emailValue.setText(hint1);
				}
			}
		});
		emailValue.setBounds(250, 133, 155, 20);
		loginFrame.getContentPane().add(emailValue);
		emailValue.setColumns(10);
		
		JLabel headingLabel = new JLabel("Welcome Back");
		headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 23));
		headingLabel.setBounds(214, 76, 232, 20);
		loginFrame.getContentPane().add(headingLabel);
		
		String hint2 ="Enter Password ";
		passwordField = new JPasswordField(hint2);
		passwordField.setEchoChar((char) 0);
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
			String enteredstring =	String.valueOf(passwordField.getPassword());
				if(!enteredstring.equals(hint2) && !enteredstring.isEmpty())  
				{
					//passwordField.setEchoChar('\u25CF');
					passwordField.setEchoChar((char) 0 );
				}
				else 
				{
					passwordField.setEchoChar((char) 0);
				}
			}
		});
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 12));
		passwordField.setBounds(250, 192, 155, 20);
		loginFrame.getContentPane().add(passwordField);
		
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				String enteredstring =	String.valueOf(passwordField.getPassword());
				
				if(enteredstring.equals(hint2))
				{
					
				 passwordField.setText("");
				 
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				String enteredstring =	String.valueOf(passwordField.getPassword());
				
				if(enteredstring.equals(""))
				{
					
					passwordField.setText(hint2);
				}
			}
		});
		
		JButton registerButton = new JButton("Register");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				registerFrame call = new registerFrame();
				loginFrame.dispose();
				
			}
		});
		registerButton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
		registerButton.setBounds(200, 276, 82, 23);
		loginFrame.getContentPane().add(registerButton);
		
		JLabel lblNewLabel = new JLabel("OR");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(305, 281, 46, 14);
		loginFrame.getContentPane().add(lblNewLabel);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String emailV=emailValue.getText();
					 String passwordvalue = String.valueOf(passwordField.getPassword());
					 
					PreparedStatement pst = conn.prepareStatement("SELECT * FROM account_details WHERE email=? and password=?");
					pst.setString(1, emailV);
					pst.setString(2, passwordvalue);
					ResultSet result = pst.executeQuery();
					if(result.next())
					{
						
						MainScreen call = new MainScreen();
						call.getValue(emailV, passwordvalue);
						loginFrame.dispose();
						call.setVisible(true);
					}
					else
					{
						JOptionPane.showMessageDialog(loginFrame, "Failure ", "title", JOptionPane.PLAIN_MESSAGE);
					}
				}
				 
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

			}
		});
		loginButton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
		loginButton.setBounds(379, 276, 89, 23);
		loginFrame.getContentPane().add(loginButton);
		
		JLabel forgotpassword = new JLabel("Forgot Password?");
		forgotpassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				PasswordResetScreen call = new PasswordResetScreen();
				loginFrame.dispose();
				
			}
		});
		forgotpassword.setHorizontalAlignment(SwingConstants.CENTER);
		forgotpassword.setBounds(214, 236, 123, 14);
		loginFrame.getContentPane().add(forgotpassword);
		
		//below code helps to get focus on component
		loginFrame.addWindowListener( new WindowAdapter() {
		    public void windowOpened( WindowEvent e ){
		        loginFrame.requestFocus();
		    }
		}); 
		
	}
}
