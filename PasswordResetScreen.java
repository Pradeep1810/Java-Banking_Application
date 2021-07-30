

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.util.Random;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PasswordResetScreen extends JFrame {

	

	private JPanel contentPane;
	
	String [] resetOptionsValue = {"Using Security Question" , "Using OTP on registered Number"};
	private JTextField mobileNumber;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPanel securityQuestionPanel;
	private JPanel otpPanel;
	private JTextField textField;
	private JTextField textField_4;
	private JTextField textField_5;
	private String emailId = "";
	private String mobileValue="";
	private String message="";
	
	
	private String [] securiQuestion = {"What Is your favorite book?" , "What is your favourite movie?",
			 "What was the name of your pet?" , 
			 "Where did you go to school/college?" ,"What is your favorite food?" , "Your favorite place to vacation?" ,
			 "Where did you meet your spouse?"};

	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PasswordResetScreen frame = new PasswordResetScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	Connection conn = null;
	public PasswordResetScreen() {
		conn = DatabaseConnection.dbconnet();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(525, 483);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel resetByLabel = new JLabel("Reset By :");
		resetByLabel.setFont(new Font("Candara", Font.BOLD, 15));
		resetByLabel.setBounds(22, 28, 74, 35);
		contentPane.add(resetByLabel);
		
		JComboBox resetOptions = new JComboBox(resetOptionsValue);
		resetOptions.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				String selected = (String) resetOptions.getSelectedItem();
				
				if(selected == resetOptionsValue[0])
				{
					
					otpPanel.setVisible(false);
					securityQuestionPanel.setVisible(true);
					
				}
				
				else if(selected == resetOptionsValue[1] )
				{
					
					securityQuestionPanel.setVisible(false);
					otpPanel.setVisible(true);
		
				}
			}
		});
		resetOptions.setModel(new DefaultComboBoxModel(new String[] {"Using Security Question", "Using OTP on registered Number"}));
		resetOptions.setFont(new Font("Times New Roman", Font.BOLD, 14));
		resetOptions.setBounds(107, 25, 219, 34);
		contentPane.add(resetOptions);
		
		JLabel loginOptionLabel = new JLabel("LOG IN");
		loginOptionLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				LoginScreen call = new LoginScreen();
				call.loginFrame.setVisible(true);
				dispose();
			}
		});
		loginOptionLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		loginOptionLabel.setBounds(365, 24, 61, 35);
		contentPane.add(loginOptionLabel);
		
		  securityQuestionPanel = new JPanel();
		securityQuestionPanel.setBackground(Color.PINK);
		securityQuestionPanel.setBounds(0, 81, 510, 374);
		contentPane.add(securityQuestionPanel);
		securityQuestionPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Security Question :");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 44, 120, 25);
		securityQuestionPanel.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox(securiQuestion);
		comboBox.setBounds(155, 46, 214, 22);
		securityQuestionPanel.add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Answer :");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 124, 120, 25);
		securityQuestionPanel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(155, 127, 161, 20);
		securityQuestionPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Email - Id :");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_2.setBounds(10, 201, 120, 25);
		securityQuestionPanel.add(lblNewLabel_2);
		
		textField_4 = new JTextField();
		textField_4.setBounds(155, 204, 161, 20);
		securityQuestionPanel.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("NEW PASSWORD :");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_3.setBounds(10, 273, 140, 25);
		securityQuestionPanel.add(lblNewLabel_3);
		
		textField_5 = new JTextField();
		textField_5.setBounds(155, 276, 161, 20);
		securityQuestionPanel.add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnNewButton = new JButton("UPDATE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				emailId = textField_4.getText();
				retrivedDetails();
				String answer = textField.getText();
				String newPassword = textField_5.getText();
				String question = comboBox.getSelectedItem().toString();
				 
				if(answer.equals(secAnswer))
				{ 
					if(question.equals(secQuestion)) { 
						
						try {
							PreparedStatement pst = conn.prepareStatement("UPDATE `account_details` set `password` = '"+newPassword+"' where email = '"+emailId+"' ");
							 pst.executeUpdate();
							 JOptionPane.showMessageDialog(null, "Password Updated");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} else { JOptionPane.showMessageDialog(null, "Enter the correct values in the field");
				}
			}
		});
		btnNewButton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		btnNewButton.setBounds(326, 275, 86, 23);
		securityQuestionPanel.add(btnNewButton);
		
		 otpPanel = new JPanel();
		otpPanel.setBackground(Color.PINK);
		otpPanel.setBounds(0, 81, 510, 374);
		otpPanel.setVisible(false);
		contentPane.add(otpPanel);
		otpPanel.setLayout(null);
		
		JLabel mobileNumberLabel = new JLabel("Mobile Number :");
		mobileNumberLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		mobileNumberLabel.setBounds(20, 51, 102, 14);
		otpPanel.add(mobileNumberLabel);
		
		mobileNumber = new JTextField();
		mobileNumber.setBounds(150, 49, 114, 20);
		otpPanel.add(mobileNumber);
		mobileNumber.setColumns(10);
		
		JLabel otpLabel = new JLabel("OTP :");
		otpLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		otpLabel.setBounds(20, 102, 102, 15);
		otpPanel.add(otpLabel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(150, 100, 114, 20);
		otpPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton generateOtp = new JButton("Generate OTP");
		generateOtp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				 mobileValue = mobileNumber.getText();
				 if(!mobileValue.isBlank())
				 { 
					 option2();
				 
				 }
				 else
				 {
					 JOptionPane.showMessageDialog(null, "Enter number for otp");
				 }
				
				
			}

			
		});
		generateOtp.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
		generateOtp.setBounds(284, 48, 120, 23);
		otpPanel.add(generateOtp);
		
		JButton confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textField_1.getText().equals(message))
				{
					textField_2.setEnabled(true);
					textField_3.setEnabled(true); 
				}
				
			}
		});
		confirmButton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		confirmButton.setBounds(284, 131, 89, 23);
		otpPanel.add(confirmButton);
		
		JLabel newPassLabel = new JLabel("NEW PASSWORD :");
		newPassLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		newPassLabel.setBounds(20, 203, 120, 14);
		otpPanel.add(newPassLabel);
		
		textField_2 = new JTextField();
		textField_2.setEnabled(false);
		textField_2.setBounds(180, 201, 127, 20);
		otpPanel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel confirmPassLabel = new JLabel("CONFIRM PASSWORD :");
		confirmPassLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		confirmPassLabel.setBounds(20, 250, 155, 14);
		otpPanel.add(confirmPassLabel);
		
		textField_3 = new JTextField();
		textField_3.setEnabled(false);
		textField_3.setBounds(180, 248, 127, 20);
		otpPanel.add(textField_3);
		textField_3.setColumns(10);
		
		JButton changePassButton = new JButton("Update");
		changePassButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
					if(textField_2.getText().equals(textField_3.getText()))
					{
						String newPassword = textField_2.getText();
						try {
							PreparedStatement pst = conn.prepareStatement("UPDATE `account_details` set `password` = '"+newPassword+"' where mobileNo = '"+mobileValue+"' ");
							 pst.executeUpdate();
							 JOptionPane.showMessageDialog(null, "Password Updated");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					} else { 
						JOptionPane.showMessageDialog(null, "Entered password doesn't match");
					}			
			}
		});
		changePassButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		changePassButton.setBounds(262, 279, 89, 23);
		otpPanel.add(changePassButton);
		
		setVisible(true);
		
	}
	
	private String giveOtp() {
		
		String generatedOtp = "";
		for (int i =1;i<5;i++)
    	{  
			 
              	String nums= "0123456789";
    		Random random = new Random();
    		int indexOtp = random.nextInt(nums.length());
    		 generatedOtp =generatedOtp +nums.charAt(indexOtp);
    		
    	}
		return generatedOtp;
	}
	
	private String secQuestion;
	private String secAnswer;
	
    private String retrivedNumber = "";
    
	private void retrivedDetails()
	{
		
		try {
		   PreparedStatement pst = conn.prepareStatement("SELECT * FROM `security_questions` WHERE emai_ID = '"+emailId+"' ");
			ResultSet res = pst.executeQuery();
			if(res.next())
			{
				
				secQuestion = res.getString("security_Question");
				secAnswer   = res.getString("security_answer");
				retrivedNumber = res.getString("mobile_number");
				
			}
			else  { 
				JOptionPane.showMessageDialog(null, "Enter the correct Email - Id");
			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void option2otp()
	{
		 message = giveOtp();
		JOptionPane.showMessageDialog(otpPanel, message, "Message", JOptionPane.PLAIN_MESSAGE);
		
	}
	private void option2()
	{
		try {
			
			PreparedStatement pst = conn.prepareStatement("SELECT * FROM `security_questions` WHERE mobile_number = '"+mobileValue+"' ");
			ResultSet res = pst.executeQuery();
			if(res.next())
			{ 
				
				option2otp();
			}
			else {
				JOptionPane.showMessageDialog(null, "Enter registered number");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
