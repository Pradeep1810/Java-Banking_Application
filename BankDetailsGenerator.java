import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.awt.event.ActionEvent;

public class BankDetailsGenerator extends JFrame {

	private JPanel contentPane;
	private JTextField securityAnswer;
	private JTextField acnoValue;
	private JTextField ifscCodeValue;
	private JTextField cardNumberValue;
	private JTextField cvvValue;
	private JComboBox branches;
	private JComboBox comboBox;
	
	private String [] securiQuestion = {"What Is your favorite book?" , "What is your favourite movie?",
			 "What was the name of your pet?" , 
			 "Where did you go to school/college?" ,"What is your favorite food?" , "Your favorite place to vacation?" ,
			 "Where did you meet your spouse?"};
	 
	 String [] accountType = {"Savings" ,"Current" };
	 
	 String [] branchNames = {"Andheri","Boriwali","Churchgate","Dahisar","Kandivali","Goregaon"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BankDetailsGenerator frame = new BankDetailsGenerator();
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
	public BankDetailsGenerator() {
		
		conn = DatabaseConnection.dbconnet();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 798, 483);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bank Details :");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(244, 11, 255, 37);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Security Question :");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 97, 123, 14);
		contentPane.add(lblNewLabel_1);
		
		 comboBox = new JComboBox(securiQuestion);
		comboBox.setBounds(163, 94, 227, 22);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Security Answer :");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_2.setBounds(10, 163, 123, 14);
		contentPane.add(lblNewLabel_2);
		
		securityAnswer = new JTextField();
		securityAnswer.setBounds(163, 161, 173, 20);
		contentPane.add(securityAnswer);
		securityAnswer.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Branch :");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_3.setBounds(10, 240, 102, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Account Type");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_4.setBounds(10, 312, 102, 14);
		contentPane.add(lblNewLabel_4);
		
		JComboBox comboBox_1 = new JComboBox(accountType);
		comboBox_1.setBounds(166, 309, 136, 22);
		contentPane.add(comboBox_1);
		
		JButton btnNewButton = new JButton("Get Details :");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String ans = securityAnswer.getText();
				if(!ans.isBlank())
				{
					
					accountNumberGenerator();
					cardNumber();
					
					btnNewButton.setEnabled(false);
					int selectedItem = branches.getSelectedIndex();
				
						switch (selectedItem)
						{
						case 0:
							ifscCodeValue.setText("SKG03698");
							break;
						case 1:
							ifscCodeValue.setText("SKG03697");
							break;
						case 2:
						    ifscCodeValue.setText("SKG03696");
						    break;
						case 3:
						    ifscCodeValue.setText("SKG03695");
						    break;
						case 4:
						    ifscCodeValue.setText("SKG03694");
						    break;
						    
						    default:
						    	ifscCodeValue.setText("SKG03693");
						    	break;
						}
						securityQuestionValues();
						cvvGenerator();
			}
			else
				{
					JOptionPane.showMessageDialog(null, "Fill all the Fields");
				}

			}
		});
		btnNewButton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
		btnNewButton.setBounds(311, 355, 110, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_5 = new JLabel("Account Number :");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_5.setBounds(443, 97, 116, 14);
		contentPane.add(lblNewLabel_5);
		
		acnoValue = new JTextField();
		acnoValue.setEditable(false);
		acnoValue.setBounds(598, 95, 146, 20);
		contentPane.add(acnoValue);
		acnoValue.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("IFSC CODE :");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_6.setBounds(443, 163, 116, 14);
		contentPane.add(lblNewLabel_6);
		
		ifscCodeValue = new JTextField();
		ifscCodeValue.setEditable(false);
		ifscCodeValue.setBounds(598, 161, 146, 20);
		contentPane.add(ifscCodeValue);
		ifscCodeValue.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Card Number :");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_7.setBounds(443, 240, 102, 14);
		contentPane.add(lblNewLabel_7);
		
		cardNumberValue = new JTextField();
		cardNumberValue.setEditable(false);
		cardNumberValue.setBounds(598, 238, 146, 20);
		contentPane.add(cardNumberValue);
		cardNumberValue.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("CVV :");
		lblNewLabel_8.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_8.setBounds(443, 312, 91, 14);
		contentPane.add(lblNewLabel_8);
		
		cvvValue = new JTextField();
		cvvValue.setEditable(false);
		cvvValue.setBounds(598, 310, 146, 20);
		contentPane.add(cvvValue);
		cvvValue.setColumns(10);
		
		 branches = new JComboBox(branchNames);
		branches.setBounds(163, 237, 136, 22);
		contentPane.add(branches);
		
		JButton nextButton = new JButton("Next --->");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!btnNewButton.isEnabled())
				{
					JOptionPane.showMessageDialog(null, "it's off");
					LoginScreen call = new LoginScreen();
					dispose();
					call.loginFrame.setVisible(true);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "To proceed first get your Banking Details ");
				}
				
			}
		});
		nextButton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
		nextButton.setBounds(470, 356, 89, 23);
		contentPane.add(nextButton);
	}
	
	private void accountNumberGenerator()
	{
		
		try {
			long acno = 0;
			Random ran = new Random();
			Random ran2 = new Random();
			while (String.valueOf(acno).length()<11)
				{
					acno = acno + 2*(ran.nextInt(11) + ran2.nextInt(11)) + 4567;	
			    }
		PreparedStatement pst1 = conn.prepareStatement("SELECT * FROM `account_details` WHERE accountNumber=?");
			String acnoTest = String.valueOf(acno);
			pst1.setString(1, acnoTest);
			ResultSet res = pst1.executeQuery();
			if(res.next())
			{
				JOptionPane.showMessageDialog(null, "error");
			}
			else
			{
				JOptionPane.showMessageDialog(null, acnoTest);
				acnoValue.setText(acnoTest);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e);
		}
		
	}
	
	private void cardNumber()
		{
			Random rand = new Random();
			int count = 0;
			String cardNo = "";
			for (int i = 0;String.valueOf(cardNo).length()<20;i++)
			{
				if(count == 4)
				{
					cardNo = cardNo +" ";
					count = 0;
				}
				else
				{
					 cardNo = cardNo + ( rand.nextInt(10) );
					 count++;
				}		
			}
			try {
				PreparedStatement pst = conn.prepareStatement("SELECT * FROM `account_details` WHERE card_Number=?");
				pst.setString(1, cardNo);
				ResultSet res = pst.executeQuery();
				if(res.next())
				{
					JOptionPane.showMessageDialog(null, "error");
				}
				else
				{
					JOptionPane.showMessageDialog(null, cardNo);
					cardNumberValue.setText(cardNo);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
	private void cvvGenerator()
	{
		Random ran = new Random();
		String cvv = "";
		for(int i = 0 ; i<3;i++)
		{
			cvv = cvv + ran.nextInt(10);
		}
		try {
				PreparedStatement pst = conn.prepareStatement("SELECT * FROM `account_details` WHERE cvv=?");
				pst.setString(1, cvv);
				ResultSet res = pst.executeQuery();
					if(res.next())
					{
						JOptionPane.showMessageDialog(null, "error");
						
					}
							else
							{
								
								
								cvvValue.setText(cvv);
								 addValues();
						
							}
		}
	catch (SQLException e) {
	// TODO Auto-generated catch block
		JOptionPane.showMessageDialog(null, e);
		}
		
	}
	
	private  void addValues() 
	{
		String accountNo = acnoValue.getText();
		String ifscCode = ifscCodeValue.getText();
		String cardNumber = cardNumberValue.getText();
		String cvvNumber = cvvValue.getText();
		JOptionPane.showMessageDialog(null, ifscCode);
		try {
			PreparedStatement pst = conn.prepareStatement("UPDATE account_details set accountNumber = '"+accountNo+"' ,   card_Number = '"+cardNumber+"' "
					                     + ", cvv = '"+cvvNumber+"' , ifsc_Code = '"+ifscCode+"'  where email = '"+eid+"' ");
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "ADDED ");
			PreparedStatement pst1 = conn.prepareStatement("INSERT INTO `security_questions` (`emai-id` , `account_Number` , `security_Question`, `security_answer` ,`mobile_number`) VALUES(?,?,?,?,?) ");
			pst1.setString(1, eid);
			pst1.setString(2, accountNo);
			pst1.setString(3, secQuestion);
			pst1.setString(4, secAnswer);
			pst1.setString(5, mno); 
			pst1.executeUpdate();
			JOptionPane.showMessageDialog(null, "Sec Added");
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	private String eid ="";
	private String mno = "";
	public void registerValues(String registerEmailId , String mnumber)
	{

		eid = registerEmailId;	
		mno = mnumber;
		
	}
	   private String secQuestion;
	   private String secAnswer;
	private void securityQuestionValues()
	{
		 secQuestion =  comboBox.getSelectedItem().toString();
		 secAnswer = securityAnswer.getText();
		JOptionPane.showMessageDialog(null, secQuestion + " " + secAnswer);
	}
}
