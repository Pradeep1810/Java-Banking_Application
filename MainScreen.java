
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.mysql.cj.protocol.x.ResultMessageListener;
import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

import javax.swing.JScrollPane;

public class MainScreen extends JFrame {

	private JPanel contentPane;
	private JTextField accnoAccPanelField;
	private JTextField mobileNumberField;
	private JTextField otpField;
	private JTextField amountFieldAccPanel;
	private JTextField confirmFieldAmountAccPanel;
	private JLabel      accnoEmailPanelLabel;
	private JTextField accnoEmailPanelField;
	private JLabel     emailIdLabel;
	private JTextField emailIdField;
	private JLabel passwordWithdrawEmailPanelLabel ;
	private JTextField passwordFieldWithdrawEmailPanel;
	private JLabel     amountEmailPanelLabel;
	private JTextField amountEmailPanelField;
	private JLabel     reEnterAmountEmailPanelLabel;
	private JTextField  reAmountFieldAccPanel;
	private JButton     withdrawButtonEmailPanel;
	private JPanel     accountAndOtpPanel;
	private JPanel     emailWithdrawPanel;
	
	private String withdrawOptionsValue [] = {"Email ID and Password" , "Using Account Number and OTP"};
	private String depositOptionsValue [] = {"Email ID and Password" , "Using Account Number and OTP"};
	
	private JPanel depositPanel;
	private JLabel depositLabel;
	private JPanel emailDepositionPanel;
	private JLabel accnoDepositPanelLabel;
	private JTextField accnoDepositPanelField;
	private JLabel  emailIdDepositPanelLabel;
	private JTextField emailIdDepositField;
	private JLabel passwordDepositEmailPanelLabel;
	private JTextField passwordFieldDepositEmailPanel;
	private JLabel amountEmailDepositPanelLabel;
	private JTextField amountEmailDepositionField;
	private JLabel reEnterAmountEmailDepositLabel;
	private JTextField reAmountEmailDepositField;
	private JButton depositButtonEmailDepositionPanel;
	private JPanel accnoAndOtpDepositionPanel;
	private JLabel accnoLabelDepositionPanel;
	private JTextField accnoDepositionField;
	private JLabel mobileNumberAccnoOtpDepositionPanelLabel;
	private JTextField mobileNumberAccnoOtpDepositionField;
	private JLabel otpAccnoOtpDepositionLabel;
	private JTextField otpAccnoOtpDepositionField;
	private JButton generateOtpButtonDepsoitionPanel;
	private JLabel  amountDepositionLabel;
	private JTextField amountFieldAccnoOtpDepositionPanel;
	private JLabel reEnterAmountAccnoOtpDepositionPanel;
	private JTextField confirmFieldAmountAccnoOtpDepositionPanel;
	private JButton depositButtonAccnoOtpDepositionPanel;
	private JPanel transferPanel;
	private JTextField textField;
	private JTextField benefeciaryIfsc;
	private JTextField beneficiaryAccount;
	private JTextField reEnterBeneficiaryAccount;
	private JLabel lblNewLabel_5;
	private JTextField transferAmount;
	private JLabel lblNewLabel_6;
	private JTextField confirmTransferAmount;
	private JTextField transferOtp;
	private JPanel Profile;
	private JButton btnNewButton_2;
	private JPanel updateInformation;
	private JTextField updateTapEmail;
	private JTextField updateTabPassword;
	private JTextField updateTabMobileNo;
	private JTextField updateTabNominee;
	private JTextArea updateAddress;
	private JLabel lblNewLabel_15;
	private JTextField textField_12;
	private JLabel lblNewLabel_16;
	private JTextField textField_13;
	private JLabel lblNewLabel_17;
	private JLabel balanceVarLabel;
	private JLabel lblNewLabel_19;
	private JTextField textField_14;
	private JLabel lblNewLabel_20;
	private JTextField textField_15;
	private  String otpMessage = "";
	private String passwordRetrived = "";
    private JButton miniStatementButton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen frame = new MainScreen();
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
	public MainScreen() {
		conn = DatabaseConnection.dbconnet();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640, 458);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 624, 419);
		tabbedPane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				
				JTabbedPane pane = (JTabbedPane) e.getSource();
				int index = pane.getSelectedIndex();
				if( index == 1 || index == 2 || index == 4 || index == 5 )
				{
					table.setModel(new DefaultTableModel(
							new Object[][] {
							},
							new String[] {
								"SR.NO", "Withdrawn", "Deposited", "Transferred To ", "Amount Send", "Total Balance"
							}
						));
				}
				
			}
		});
		contentPane.add(tabbedPane);
		
		JPanel withdrawPanel = new JPanel();
		withdrawPanel.setBackground(Color.PINK);
		tabbedPane.addTab("Withdraw", null, withdrawPanel, null);
		withdrawPanel.setLayout(null);
		
		
		JLabel withdrawLabel = new JLabel("Withdraw By :");
		withdrawLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		withdrawLabel.setBounds(57, 31, 99, 25);
		withdrawPanel.add(withdrawLabel);
		
		JComboBox withdrawOptions = new JComboBox (withdrawOptionsValue);
		withdrawOptions.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
              String selected = withdrawOptions.getSelectedItem().toString();
				
				if(selected == withdrawOptionsValue[0])
				{
				
			
				accountAndOtpPanel.setVisible(false);
				emailWithdrawPanel.setVisible(true);
				 
				}
				else if(selected == withdrawOptionsValue[1])
				{
				
				    accountAndOtpPanel.setVisible(true);
				    emailWithdrawPanel.setVisible(false);
					
				}
			}
		});
		withdrawOptions.setBounds(191, 33, 220, 22);
		withdrawPanel.add(withdrawOptions);
		
		 accountAndOtpPanel = new JPanel();
		 accountAndOtpPanel.setBackground(Color.PINK);
		accountAndOtpPanel.setBounds(0, 69, 619, 322);
		accountAndOtpPanel.setVisible(false);
		withdrawPanel.add(accountAndOtpPanel);
		accountAndOtpPanel.setLayout(null);
	
		JLabel accno = new JLabel("Account Number :");
		accno.setFont(new Font("Times New Roman", Font.BOLD, 14));
		accno.setBounds(57, 34, 126, 25);
		accountAndOtpPanel.add(accno);
		
		accnoAccPanelField = new JTextField();
		accnoAccPanelField.setEditable(false);
		accnoAccPanelField.setBounds(191, 37, 166, 20);
		accountAndOtpPanel.add(accnoAccPanelField);
		accnoAccPanelField.setColumns(10);
		
		JLabel mobileNumberLabel = new JLabel("Mobile Number :");
		mobileNumberLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		mobileNumberLabel.setBounds(57, 93, 107, 25);
		accountAndOtpPanel.add(mobileNumberLabel);
		
		mobileNumberField = new JTextField();
		mobileNumberField.setBounds(191, 93, 166, 20);
		accountAndOtpPanel.add(mobileNumberField);
		mobileNumberField.setColumns(10);
		
		JLabel otpLabel = new JLabel("OTP                :");
		otpLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		otpLabel.setBounds(57, 158, 107, 25);
		accountAndOtpPanel.add(otpLabel);
		
		otpField = new JTextField();
		otpField.setBounds(191, 161, 166, 20);
		accountAndOtpPanel.add(otpField);
		otpField.setColumns(10);
		
		JButton generateOtpButton = new JButton("Send OTP ");
		generateOtpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String mobileValue = mobileNumberField.getText();
				
				if(!textField_15.getText().equals(mobileNumberField.getText()) && !mobileValue.isBlank()) {
					JOptionPane.showMessageDialog(null, "Enter the Registered Number");
					}
				if(mobileValue.isBlank())
				{
					JOptionPane.showMessageDialog(accountAndOtpPanel, "Enter Number for OTP : ", "Empty Field Error", JOptionPane.ERROR_MESSAGE);
				}
				if(textField_15.getText().equals(mobileNumberField.getText())) {
					generatedOtp();
					}
			}
		});
		generateOtpButton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
		generateOtpButton.setBounds(376, 91, 107, 23);
		accountAndOtpPanel.add(generateOtpButton);
		
		JLabel amount = new JLabel("Amount          :");
		amount.setFont(new Font("Times New Roman", Font.BOLD, 14));
		amount.setBounds(57, 210, 107, 14);
		accountAndOtpPanel.add(amount);
		
		amountFieldAccPanel = new JTextField();
		amountFieldAccPanel.setBounds(191, 208, 166, 20);
		accountAndOtpPanel.add(amountFieldAccPanel);
		amountFieldAccPanel.setColumns(10);
		
		JLabel reEnterAmount = new JLabel("Confirm Amount :");
		reEnterAmount.setFont(new Font("Times New Roman", Font.BOLD, 14));
		reEnterAmount.setBounds(34, 259, 120, 25);
		accountAndOtpPanel.add(reEnterAmount);
		
		confirmFieldAmountAccPanel = new JTextField();
		confirmFieldAmountAccPanel.setBounds(191, 262, 166, 20);
		accountAndOtpPanel.add(confirmFieldAmountAccPanel);
		confirmFieldAmountAccPanel.setColumns(10);
		
		 
		JButton withdrawButton = new JButton("WITHDRAW");
		withdrawButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					int amount = Integer.parseInt(amountFieldAccPanel.getText());
					int reEnteredAmount = Integer.parseInt(confirmFieldAmountAccPanel.getText());
					int otp = Integer.parseInt(otpField.getText());
					int otpEntered = Integer.parseInt(otpMessage);
					
						if(otp == otpEntered) {
							if(amount==reEnteredAmount)  
								withdraw(amount);       
							else {
								JOptionPane.showMessageDialog(null, "Check the Amount Entered");
								}
						}
						else {
							JOptionPane.showMessageDialog(null, "Enter correct OTP ");
							}
						
					
			}
		});
		withdrawButton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		withdrawButton.setBounds(376, 261, 107, 23);
		accountAndOtpPanel.add(withdrawButton);
		
		 emailWithdrawPanel = new JPanel();
		emailWithdrawPanel.setBackground(Color.PINK);
		emailWithdrawPanel.setBounds(0, 69, 619, 322);
		withdrawPanel.add(emailWithdrawPanel);
		emailWithdrawPanel.setLayout(null);
		
		
		 accnoEmailPanelLabel = new JLabel("Account Number :");
		accnoEmailPanelLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		accnoEmailPanelLabel.setBounds(57, 34, 126, 25);
		emailWithdrawPanel.add(accnoEmailPanelLabel);
		
		accnoEmailPanelField = new JTextField();
		accnoEmailPanelField.setEditable(false);
		accnoEmailPanelField.setBounds(191, 37, 166, 20);
		emailWithdrawPanel.add(accnoEmailPanelField);
		accnoAccPanelField.setColumns(10);
		
		 emailIdLabel = new JLabel(" Email ID :");
		emailIdLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		emailIdLabel.setBounds(57, 93, 107, 25);
		emailWithdrawPanel.add(emailIdLabel);
		
		emailIdField = new JTextField();
		emailIdField.setBounds(191, 93, 166, 20);
		emailWithdrawPanel.add(emailIdField);
		emailIdField.setColumns(10);
		
		 passwordWithdrawEmailPanelLabel = new JLabel("Password :");
		passwordWithdrawEmailPanelLabel.setSize(100, 25);
		passwordWithdrawEmailPanelLabel.setLocation(57, 152);
		passwordWithdrawEmailPanelLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		emailWithdrawPanel.add(passwordWithdrawEmailPanelLabel);
		
		 passwordFieldWithdrawEmailPanel = new JTextField();
		passwordFieldWithdrawEmailPanel.setSize(166, 20);
		passwordFieldWithdrawEmailPanel.setLocation(191, 152);
		emailWithdrawPanel.add(passwordFieldWithdrawEmailPanel);
		
		 amountEmailPanelLabel = new JLabel("Amount          :");
		amountEmailPanelLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		amountEmailPanelLabel.setBounds(57, 217, 107, 25);
		emailWithdrawPanel.add(amountEmailPanelLabel);
		
		amountEmailPanelField = new JTextField();
		amountEmailPanelField.setBounds(191, 217, 166, 20);
		emailWithdrawPanel.add(amountEmailPanelField);
		amountEmailPanelField.setColumns(10);
		
		
		 reEnterAmountEmailPanelLabel = new JLabel("Confirm Amount :");
		 reEnterAmountEmailPanelLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		 reEnterAmountEmailPanelLabel.setBounds(57, 269, 120, 25);
		emailWithdrawPanel.add(reEnterAmountEmailPanelLabel);
		
		reAmountFieldAccPanel = new JTextField();
		reAmountFieldAccPanel.setBounds(191, 269, 166, 20);
		emailWithdrawPanel.add(reAmountFieldAccPanel);
		reAmountFieldAccPanel.setColumns(10);
		
		
		 withdrawButtonEmailPanel = new JButton("WITHDRAW");
		 withdrawButtonEmailPanel.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		int amount = Integer.parseInt(amountEmailPanelField.getText());
				int reEnteredAmount = Integer.parseInt(reAmountFieldAccPanel.getText());
				if(textField_14.getText().equals(emailIdField.getText()))
				{   
					if(passwordFieldWithdrawEmailPanel.getText().equals(passwordRetrived))
					{
						if(amount==reEnteredAmount) 	
							withdraw(amount);	
						else {
							JOptionPane.showMessageDialog(null, "Check the Amount Entered");
							}
					}
					else { 
						JOptionPane.showMessageDialog(null, "Enter correct Password");
					}
				} 
				else { 
					JOptionPane.showMessageDialog(null, "Enter correct email - id");
				}
				
		 	}
		 });
		withdrawButtonEmailPanel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		withdrawButtonEmailPanel.setBounds(376,268,107,23);
		emailWithdrawPanel.add(withdrawButtonEmailPanel);
		
		depositPanel = new JPanel();
		depositPanel.setBackground(Color.PINK);
		tabbedPane.addTab("Deposit", null, depositPanel, null);
		depositPanel.setLayout(null);
		
		 depositLabel = new JLabel("Deposit By :");
		depositLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		depositLabel.setBounds(57, 31, 99, 25);
		depositPanel.add(depositLabel);
		
		JComboBox depositOptions = new JComboBox (depositOptionsValue);
		depositOptions.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
            String selected = depositOptions.getSelectedItem().toString();
				
				if(selected == depositOptionsValue[0])
				{
				
			       emailDepositionPanel.setVisible(true);
			       accnoAndOtpDepositionPanel.setVisible(false);
				
				 
				}
				else if(selected == withdrawOptionsValue[1])
				{
				
				   emailDepositionPanel.setVisible(false);
				   accnoAndOtpDepositionPanel.setVisible(true);
					
				}
			}
		});
		depositOptions.setBounds(191, 33, 220, 22);
		depositPanel.add(depositOptions);
		
		emailDepositionPanel = new JPanel();
		emailDepositionPanel.setBackground(Color.PINK);
		emailDepositionPanel.setBounds(0, 69, 619, 324);
		depositPanel.add(emailDepositionPanel);
		emailDepositionPanel.setLayout(null);
		
		   accnoDepositPanelLabel = new JLabel("Account Number :");
		   accnoDepositPanelLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		   accnoDepositPanelLabel.setBounds(57, 34, 126, 25);
			emailDepositionPanel.add(accnoDepositPanelLabel);
			
			accnoDepositPanelField = new JTextField();
			accnoDepositPanelField.setEditable(false);
			accnoDepositPanelField.setBounds(191, 37, 166, 20);
			emailDepositionPanel.add(accnoDepositPanelField);
			accnoDepositPanelField.setColumns(10);
			
			 emailIdDepositPanelLabel = new JLabel(" Email ID :");
			emailIdDepositPanelLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
			emailIdDepositPanelLabel.setBounds(57, 93, 107, 25);
			emailDepositionPanel.add(emailIdDepositPanelLabel);
			
			 emailIdDepositField = new JTextField();
			 emailIdDepositField.setBounds(191, 93, 166, 20);
			emailDepositionPanel.add(emailIdDepositField);
			emailIdDepositField.setColumns(10);
			
			 passwordDepositEmailPanelLabel = new JLabel("Password :");
			 passwordDepositEmailPanelLabel.setSize(100, 25);
			 passwordDepositEmailPanelLabel.setLocation(57, 152);
			 passwordDepositEmailPanelLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
				emailDepositionPanel.add(passwordDepositEmailPanelLabel);
				
				passwordFieldDepositEmailPanel = new JTextField();
				passwordFieldDepositEmailPanel.setSize(166, 20);
				passwordFieldDepositEmailPanel.setLocation(191, 152);
				emailDepositionPanel.add(passwordFieldDepositEmailPanel);
			
			 amountEmailDepositPanelLabel = new JLabel("Amount          :");
			 amountEmailDepositPanelLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
			 amountEmailDepositPanelLabel.setBounds(57, 217, 107, 25);
			emailDepositionPanel.add(amountEmailDepositPanelLabel);
			
			 amountEmailDepositionField = new JTextField();
			amountEmailDepositionField.setBounds(191, 217, 166, 20);
			emailDepositionPanel.add(amountEmailDepositionField);
			amountEmailDepositionField.setColumns(10);
			
			 reEnterAmountEmailDepositLabel = new JLabel("Confirm Amount :");
			reEnterAmountEmailDepositLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
			reEnterAmountEmailDepositLabel.setBounds(35, 266, 120, 25);
			emailDepositionPanel.add(reEnterAmountEmailDepositLabel);
			
			 reAmountEmailDepositField = new JTextField();
			reAmountEmailDepositField.setBounds(191, 269, 166, 20);
			emailDepositionPanel.add(reAmountEmailDepositField);
			reAmountEmailDepositField.setColumns(10);
			
		    depositButtonEmailDepositionPanel  = new JButton("DEPOSIT");
		    depositButtonEmailDepositionPanel.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		
		    		
		    		
		    		int amount = Integer.parseInt(amountEmailDepositionField.getText());
					int reEnteredAmount = Integer.parseInt(reAmountEmailDepositField.getText()); 
		    		
			
			
			if(!emailIdDepositField.getText().isBlank() && !passwordFieldDepositEmailPanel.getText().isBlank() && 
					!amountEmailDepositionField.getText().isBlank() && !reAmountEmailDepositField.getText().isBlank())
			{
				if(emailIdDepositField.getText().equals(textField_14.getText()))
			
								{ 
									if(passwordFieldDepositEmailPanel.getText().equals(passwordRetrived))
										{
											if(amount==reEnteredAmount)
												   {
												deposit(amount);
												}
											else JOptionPane.showMessageDialog(null, "Check the Amount Entered");
										} else JOptionPane.showMessageDialog(null, "Enter Correct Password");
								 
								} else JOptionPane.showMessageDialog(null, "Enter Correct Email - id");
			} 
			
		    	}
		    });
			depositButtonEmailDepositionPanel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
			depositButtonEmailDepositionPanel.setBounds(376,268,107,23);
			emailDepositionPanel.add(depositButtonEmailDepositionPanel);
			
			accnoAndOtpDepositionPanel = new JPanel();
			accnoAndOtpDepositionPanel.setBackground(Color.PINK);
			accnoAndOtpDepositionPanel.setBounds(0, 69, 619, 324);
			accnoAndOtpDepositionPanel.setVisible(false);
			depositPanel.add(accnoAndOtpDepositionPanel);
			accnoAndOtpDepositionPanel.setLayout(null);
			
			 accnoLabelDepositionPanel = new JLabel("Account Number :");
			accnoLabelDepositionPanel.setFont(new Font("Times New Roman", Font.BOLD, 14));
			accnoLabelDepositionPanel.setBounds(57, 34, 126, 25);
			accnoAndOtpDepositionPanel.add(accnoLabelDepositionPanel);
			
			 accnoDepositionField = new JTextField();
			 accnoDepositionField.setEditable(false);
			accnoDepositionField.setBounds(191, 37, 166, 20);
			accnoAndOtpDepositionPanel.add(accnoDepositionField);
			accnoDepositionField.setColumns(10);
			
			 mobileNumberAccnoOtpDepositionPanelLabel = new JLabel("Mobile Number :");
			mobileNumberAccnoOtpDepositionPanelLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
			mobileNumberAccnoOtpDepositionPanelLabel.setBounds(57, 93, 107, 25);
			accnoAndOtpDepositionPanel.add(mobileNumberAccnoOtpDepositionPanelLabel);
		
			mobileNumberAccnoOtpDepositionField = new JTextField();
			mobileNumberAccnoOtpDepositionField.setBounds(191, 93, 166, 20);
			accnoAndOtpDepositionPanel.add(mobileNumberAccnoOtpDepositionField);
			mobileNumberAccnoOtpDepositionField.setColumns(10);
			
			 otpAccnoOtpDepositionLabel = new JLabel("OTP                :");
			otpAccnoOtpDepositionLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
			otpAccnoOtpDepositionLabel.setBounds(57, 158, 107, 25);
			accnoAndOtpDepositionPanel.add(otpAccnoOtpDepositionLabel);
			
			otpAccnoOtpDepositionField = new JTextField();
			otpAccnoOtpDepositionField.setBounds(191, 161, 166, 20);
			accnoAndOtpDepositionPanel.add(otpAccnoOtpDepositionField);
			otpAccnoOtpDepositionField.setColumns(10);
			
			 generateOtpButtonDepsoitionPanel = new JButton("Send OTP ");
			 generateOtpButtonDepsoitionPanel.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 	
			 		String mobileValue = mobileNumberAccnoOtpDepositionField.getText();
			 		if(!mobileValue.equals(textField_15.getText()) && !mobileValue.isBlank())
			 		{
			 			JOptionPane.showMessageDialog(null, "Enter the Registered Number");
			 		}
					if(mobileValue.isBlank())
					{
						JOptionPane.showMessageDialog(accnoAndOtpDepositionPanel, "Please Enter Number To Generate OTP : ", "Empty Field Error", JOptionPane.ERROR_MESSAGE);
					}
					if(mobileValue.equals(textField_15.getText())) {
						generatedOtp();
						}
			 	}
			 });
			generateOtpButtonDepsoitionPanel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
			generateOtpButtonDepsoitionPanel.setBounds(376, 91, 107, 23);
			accnoAndOtpDepositionPanel.add(generateOtpButtonDepsoitionPanel);
			
			 amountDepositionLabel = new JLabel("Amount          :");
			amountDepositionLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
			amountDepositionLabel.setBounds(57, 210, 107, 14);
			accnoAndOtpDepositionPanel.add(amountDepositionLabel);
			
			amountFieldAccnoOtpDepositionPanel = new JTextField();
			amountFieldAccnoOtpDepositionPanel.setBounds(191, 208, 166, 20);
			accnoAndOtpDepositionPanel.add(amountFieldAccnoOtpDepositionPanel);
			amountFieldAccnoOtpDepositionPanel.setColumns(10);
			
			 reEnterAmountAccnoOtpDepositionPanel = new JLabel("Confirm Amount :");
			reEnterAmountAccnoOtpDepositionPanel.setFont(new Font("Times New Roman", Font.BOLD, 14));
			reEnterAmountAccnoOtpDepositionPanel.setBounds(57, 259, 120, 25);
			accnoAndOtpDepositionPanel.add(reEnterAmountAccnoOtpDepositionPanel);
			
			confirmFieldAmountAccnoOtpDepositionPanel = new JTextField();
			confirmFieldAmountAccnoOtpDepositionPanel.setBounds(191, 262, 166, 20);
			accnoAndOtpDepositionPanel.add(confirmFieldAmountAccnoOtpDepositionPanel);
			confirmFieldAmountAccnoOtpDepositionPanel.setColumns(10);
			
			 depositButtonAccnoOtpDepositionPanel = new JButton("DEPOSIT");
			 depositButtonAccnoOtpDepositionPanel.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		
			 		int amount = Integer.parseInt(amountFieldAccnoOtpDepositionPanel.getText());
					int reEnteredAmount = Integer.parseInt(confirmFieldAmountAccnoOtpDepositionPanel.getText());
					int otp = Integer.parseInt(otpAccnoOtpDepositionField.getText());
					int otpEntered = Integer.parseInt(otpMessage);
					if(otp == otpEntered) {
						if(amount==reEnteredAmount)
						   deposit(amount);
						else JOptionPane.showMessageDialog(null, "Check the Amount Entered");
					}
					else JOptionPane.showMessageDialog(null, "Enter correct OTP ");
			 	}
			 });
			depositButtonAccnoOtpDepositionPanel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
			depositButtonAccnoOtpDepositionPanel.setBounds(376, 261, 107, 23);
			accnoAndOtpDepositionPanel.add(depositButtonAccnoOtpDepositionPanel);
			
			transferPanel = new JPanel();
			transferPanel.setBackground(Color.PINK);
			tabbedPane.addTab("Transfer", null, transferPanel, null);
			transferPanel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Transfer To :");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 23));
			lblNewLabel.setBounds(201, 11, 162, 28);
			transferPanel.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Name :");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_1.setBounds(34, 77, 77, 25);
			transferPanel.add(lblNewLabel_1);
			
			textField = new JTextField();
			textField.setBounds(154, 80, 145, 20);
			transferPanel.add(textField);
			textField.setColumns(10);
			
			JLabel lblNewLabel_2 = new JLabel("IFSC Code :");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_2.setBounds(34, 148, 77, 25);
			transferPanel.add(lblNewLabel_2);
			
			benefeciaryIfsc = new JTextField();
			benefeciaryIfsc.setBounds(154, 151, 145, 20);
			transferPanel.add(benefeciaryIfsc);
			benefeciaryIfsc.setColumns(10);
			
			JLabel lblNewLabel_3 = new JLabel("Account No :");
			lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_3.setBounds(34, 219, 88, 25);
			transferPanel.add(lblNewLabel_3);
			
			beneficiaryAccount = new JTextField();
			beneficiaryAccount.setBounds(154, 222, 145, 20);
			transferPanel.add(beneficiaryAccount);
			beneficiaryAccount.setColumns(10);
			
			JLabel lblNewLabel_4 = new JLabel("Re-Enter  :");
			lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_4.setBounds(34, 291, 77, 25);
			transferPanel.add(lblNewLabel_4);
			
			reEnterBeneficiaryAccount = new JTextField();
			reEnterBeneficiaryAccount.setBounds(154, 294, 145, 20);
			transferPanel.add(reEnterBeneficiaryAccount);
			reEnterBeneficiaryAccount.setColumns(10);
			
			lblNewLabel_5 = new JLabel("Amount :");
			lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_5.setBounds(341, 77, 77, 25);
			transferPanel.add(lblNewLabel_5);
			
			transferAmount = new JTextField();
			transferAmount.setBounds(433, 80, 145, 20);
			transferPanel.add(transferAmount);
			transferAmount.setColumns(10);
			
			lblNewLabel_6 = new JLabel("Re-Enter :");
			lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_6.setBounds(341, 148, 77, 25);
			transferPanel.add(lblNewLabel_6);
			
			confirmTransferAmount = new JTextField();
			confirmTransferAmount.setBounds(433, 151, 140, 20);
			transferPanel.add(confirmTransferAmount);
			confirmTransferAmount.setColumns(10);
			
			JButton btnNewButton = new JButton("GET OTP ");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(textField.getText().isBlank() || benefeciaryIfsc.getText().isBlank() || beneficiaryAccount.getText().isBlank() || reEnterBeneficiaryAccount.getText().isBlank()
							|| transferAmount.getText().isBlank() || confirmTransferAmount.getText().isBlank())
					{
						JOptionPane.showMessageDialog(null, "Fill all the fields");
					}
					else { 
						generatedOtp(); 
						transferOtp.setEnabled(true);}
					
				}
			});
			btnNewButton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
			btnNewButton.setBounds(471, 194, 107, 23);
			transferPanel.add(btnNewButton);
			
			JLabel lblNewLabel_7 = new JLabel("OTP  :");
			lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_7.setBounds(341, 251, 46, 14);
			transferPanel.add(lblNewLabel_7);
			
			transferOtp = new JTextField();
			transferOtp.setEnabled(false);
			transferOtp.setBounds(433, 249, 145, 20);
			transferPanel.add(transferOtp);
			transferOtp.setColumns(10);
			
			JButton btnNewButton_1 = new JButton("TRANSFER");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(transferOtp.isEnabled())
					{
						if(transferOtp.getText().equals(otpMessage)) transfer();
						 else JOptionPane.showMessageDialog(null, "Enter correct otp");
					} else JOptionPane.showMessageDialog(null, "Fill all the feilds ");
				}
			});
			btnNewButton_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
			btnNewButton_1.setBounds(471, 293, 108, 23);
			transferPanel.add(btnNewButton_1);
			
			JPanel Balance = new JPanel();
			Balance.setBackground(Color.PINK);
			tabbedPane.addTab("Balance", null, Balance, null);
			Balance.setLayout(null);
			
			 miniStatementButton = new JButton("MINI STATEMENT :");
			miniStatementButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					resetTable();
					retriveTable();
					
				}
			});
			miniStatementButton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
			miniStatementButton.setBounds(10, 11, 164, 38);
			Balance.add(miniStatementButton);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 60, 599, 320);
			Balance.add(scrollPane);
			
			table = new JTable();
			table.setEnabled(false);
			scrollPane.setViewportView(table);
			table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"SR.NO", "Withdrawn", "Deposited", "Transferred To ", "Amount Send", "Total Balance"
				}
			));
			
			JLabel lblNewLabel_18 = new JLabel("The Data displayed is from Latest to old Transaction");
			lblNewLabel_18.setFont(new Font("Times New Roman", Font.BOLD, 14));
			lblNewLabel_18.setBounds(200, 25, 393, 14);
			Balance.add(lblNewLabel_18);
			
			updateInformation = new JPanel();
			updateInformation.setBackground(Color.PINK);
			tabbedPane.addTab("Update", null, updateInformation, null);
			updateInformation.setLayout(null);
			
			JLabel lblNewLabel_8 = new JLabel("Update Credintials :");
			lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_8.setFont(new Font("Times New Roman", Font.BOLD, 23));
			lblNewLabel_8.setBounds(188, 11, 228, 51);
			updateInformation.add(lblNewLabel_8);
			
			JLabel lblNewLabel_9 = new JLabel("Email :");
			lblNewLabel_9.setFont(new Font("Times New Roman", Font.BOLD, 14));
			lblNewLabel_9.setBounds(10, 94, 66, 25);
			updateInformation.add(lblNewLabel_9);
			
			updateTapEmail = new JTextField();
			updateTapEmail.setEditable(false);
			updateTapEmail.setBounds(94, 97, 137, 20);
			updateInformation.add(updateTapEmail);
			updateTapEmail.setColumns(10);
			
			JLabel lblNewLabel_10 = new JLabel("Password :");
			lblNewLabel_10.setFont(new Font("Times New Roman", Font.BOLD, 14));
			lblNewLabel_10.setBounds(10, 147, 66, 25);
			updateInformation.add(lblNewLabel_10);
			
			updateTabPassword = new JTextField();
			updateTabPassword.setEditable(false);
			updateTabPassword.setBounds(94, 150, 137, 20);
			updateInformation.add(updateTabPassword);
			updateTabPassword.setColumns(10);
			
			JLabel lblNewLabel_11 = new JLabel("Mobile No :");
			lblNewLabel_11.setFont(new Font("Times New Roman", Font.BOLD, 14));
			lblNewLabel_11.setBounds(302, 94, 78, 25);
			updateInformation.add(lblNewLabel_11);
			
			updateTabMobileNo = new JTextField();
			updateTabMobileNo.setEditable(false);
			updateTabMobileNo.setBounds(411, 97, 137, 20);
			updateInformation.add(updateTabMobileNo);
			updateTabMobileNo.setColumns(10);
			
			JLabel lblNewLabel_12 = new JLabel("Nominee :");
			lblNewLabel_12.setFont(new Font("Times New Roman", Font.BOLD, 14));
			lblNewLabel_12.setBounds(302, 147, 78, 25);
			updateInformation.add(lblNewLabel_12);
			
			updateTabNominee = new JTextField();
			updateTabNominee.setEditable(false);
			updateTabNominee.setBounds(411, 150, 137, 20);
			updateInformation.add(updateTabNominee);
			updateTabNominee.setColumns(10);
			
			JLabel lblNewLabel_13 = new JLabel("Address :");
			lblNewLabel_13.setFont(new Font("Times New Roman", Font.BOLD, 14));
			lblNewLabel_13.setBounds(10, 231, 66, 25);
			updateInformation.add(lblNewLabel_13);
			
			JCheckBox chckbxNewCheckBox = new JCheckBox("  EDIT");
			chckbxNewCheckBox.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					if(chckbxNewCheckBox.isSelected())
					{
					updateTabMobileNo.setEditable(true);
					updateTabPassword.setEditable(true);
					updateTapEmail.setEditable(true);
					updateTabNominee.setEditable(true);
                    updateAddress.setEditable(true);
					}
					else
					{
						updateTabMobileNo.setEditable(false);
						updateTabPassword.setEditable(false);
						updateTapEmail.setEditable(false);
						updateTabNominee.setEditable(false);
						 updateAddress.setEditable(false);
					}
				}
			});
			chckbxNewCheckBox.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
			chckbxNewCheckBox.setBounds(385, 327, 66, 25);
			updateInformation.add(chckbxNewCheckBox);
			
			JButton updateButton = new JButton("UPDATE");
			updateButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
								
						try {
							if(!updateTapEmail.getText().isBlank() && !updateTabPassword.getText().isBlank() && !updateTabMobileNo.getText().isBlank() && !updateAddress.getText().isBlank())
							{
							String value0 = textField_12.getText();
							String value001 = textField_13.getText();
							String valueEmail = updateTapEmail.getText();
							String valueMobile = updateTabMobileNo.getText();
							String valuePassword = updateTabPassword.getText();
							String valueAddress = updateAddress.getText();
							PreparedStatement pst = conn.prepareStatement("UPDATE account_details set mobileNo ='"+valueMobile+"' ,password = '"+valuePassword+"' , email = '"+valueEmail+"' , "
									         + "address = '"+valueAddress+"' "
										         	+ "where accountNumber = '"+retriveAccount+"' and Name = '"+value0+"' and lastName = '"+value001+"' ");
					        pst.executeUpdate();
						    JOptionPane.showMessageDialog(null, "updated");		
						    setValues();
							}
							else {
								JOptionPane.showMessageDialog(null, "Error");
							}
						}
						catch (SQLException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, e1);
						}
					
				}
			});
			updateButton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
			updateButton.setBounds(479, 328, 89, 25);
			updateInformation.add(updateButton);
			
			 updateAddress = new JTextArea();
			updateAddress.setEditable(false);
			updateAddress.setLineWrap(true);
			updateAddress.setBounds(94, 221, 238, 118);
			updateInformation.add(updateAddress);
			
			Profile = new JPanel();
			Profile.setBackground(Color.PINK);
			tabbedPane.addTab("Profile ", null, Profile, null);
			Profile.setLayout(null);
			
			btnNewButton_2 = new JButton("LOG OUT");
			btnNewButton_2.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					LoginScreen call = new LoginScreen();
				    call.loginFrame.setVisible(true);
				    dispose();
				}
			});
			btnNewButton_2.setBounds(501, 25, 108, 25);
			Profile.add(btnNewButton_2);
			
			JLabel lblNewLabel_14 = new JLabel("PROFILE ");
			lblNewLabel_14.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_14.setFont(new Font("Times New Roman", Font.BOLD, 23));
			lblNewLabel_14.setBounds(254, 25, 118, 25);
			Profile.add(lblNewLabel_14);
			
			lblNewLabel_15 = new JLabel("NAME :");
			lblNewLabel_15.setFont(new Font("Times New Roman", Font.BOLD, 14));
			lblNewLabel_15.setBounds(10, 131, 64, 25);
			Profile.add(lblNewLabel_15);
			
			textField_12 = new JTextField();
			textField_12.setEditable(false);
			textField_12.setBounds(128, 134, 108, 20);
			Profile.add(textField_12);
			textField_12.setColumns(10);
			
			lblNewLabel_16 = new JLabel("LAST NAME :");
			lblNewLabel_16.setFont(new Font("Times New Roman", Font.BOLD, 14));
			lblNewLabel_16.setBounds(10, 198, 102, 25);
			Profile.add(lblNewLabel_16);
			
			textField_13 = new JTextField();
			textField_13.setEditable(false);
			textField_13.setBounds(128, 201, 108, 20);
			Profile.add(textField_13);
			textField_13.setColumns(10);
			
			lblNewLabel_17 = new JLabel("BALANCE :");
			lblNewLabel_17.setFont(new Font("Times New Roman", Font.BOLD, 14));
			lblNewLabel_17.setBounds(10, 25, 88, 25);
			Profile.add(lblNewLabel_17);
			
			balanceVarLabel = new JLabel("New label");
			balanceVarLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
			balanceVarLabel.setBounds(106, 19, 88, 37);
			Profile.add(balanceVarLabel);
			
			lblNewLabel_19 = new JLabel("EMAIL :");
			lblNewLabel_19.setFont(new Font("Times New Roman", Font.BOLD, 14));
			lblNewLabel_19.setBounds(311, 131, 54, 25);
			Profile.add(lblNewLabel_19);
			
			textField_14 = new JTextField();
			textField_14.setEditable(false);
			textField_14.setBounds(431, 134, 156, 20);
			Profile.add(textField_14);
			textField_14.setColumns(10);
			
			lblNewLabel_20 = new JLabel("MOBILE NO :");
			lblNewLabel_20.setFont(new Font("Times New Roman", Font.BOLD, 14));
			lblNewLabel_20.setBounds(309, 198, 108, 25);
			Profile.add(lblNewLabel_20);
			
			textField_15 = new JTextField();
			textField_15.setEditable(false);
			textField_15.setBounds(431, 201, 132, 20);
			Profile.add(textField_15);
			textField_15.setColumns(10);
			
			JLabel lblNewLabel_21 = new JLabel("Account NO:-");
			lblNewLabel_21.setFont(new Font("Times New Roman", Font.BOLD, 14));
			lblNewLabel_21.setBounds(10, 264, 88, 25);
			Profile.add(lblNewLabel_21);
			
			textField_7 = new JTextField();
			textField_7.setEditable(false);
			textField_7.setBounds(128, 267, 118, 20);
			Profile.add(textField_7);
			textField_7.setColumns(10);
			
			JLabel lblNewLabel_22 = new JLabel("IFSC Code:");
			lblNewLabel_22.setFont(new Font("Times New Roman", Font.BOLD, 14));
			lblNewLabel_22.setBounds(311, 264, 72, 25);
			Profile.add(lblNewLabel_22);
			
			textField_8 = new JTextField();
			textField_8.setEditable(false);
			textField_8.setBounds(431, 267, 132, 20);
			Profile.add(textField_8);
			textField_8.setColumns(10);
			
			JLabel lblNewLabel_23 = new JLabel("Card No :");
			lblNewLabel_23.setFont(new Font("Times New Roman", Font.BOLD, 14));
			lblNewLabel_23.setBounds(10, 332, 88, 25);
			Profile.add(lblNewLabel_23);
			
			textField_9 = new JTextField();
			textField_9.setEditable(false);
			textField_9.setBounds(128, 335, 142, 20);
			Profile.add(textField_9);
			textField_9.setColumns(10);
			
			lblNewLabel_24 = new JLabel("cvv :");
			lblNewLabel_24.setFont(new Font("Times New Roman", Font.BOLD, 15));
			lblNewLabel_24.setBounds(319, 332, 46, 25);
			Profile.add(lblNewLabel_24);
			
			textField_10 = new JTextField();
			textField_10.setEditable(false);
			textField_10.setBounds(431, 335, 132, 20);
			Profile.add(textField_10);
			textField_10.setColumns(10);
			
			
		
			
			
	}
	
    private	String emailIdpased ="";
	private String pwdPassed = "";
	
	public void getValue(String eid , String pwd) {
		
		emailIdpased = eid;
		pwdPassed = pwd;
		retriveAccount();
		
	}
	
	
	private  String retriveAccount ="";
	private  String retriveIfsc = "";
	  private JTextField textField_7;
	  private JTextField textField_8;
	  private JTextField textField_9;
	  private JLabel lblNewLabel_24;
	  private JTextField textField_10;
	private void retriveAccount()
	{
		try {
			PreparedStatement pst = conn.prepareStatement("select * from account_details where email = ? and password = ?");
			pst.setString(1, emailIdpased);
			pst.setString(2, pwdPassed);
			ResultSet result = pst.executeQuery();
			if(result.next())
			{
				retriveAccount = result.getString("accountNumber");
				retriveIfsc     = result.getString("ifsc_Code");
				setValues();
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setValues() {
		
     try {
		PreparedStatement pst = conn.prepareStatement("select * from account_details where accountNumber=? and ifsc_Code=?");
		pst.setString(1, retriveAccount);
		pst.setString(2, retriveIfsc);
		ResultSet result = pst.executeQuery();
		if(result.next())
		{
			String setLastName = result.getString("lastName");
			textField_13.setText(setLastName);
			String setFirstName = result.getString("Name");
			textField_12.setText(setFirstName);
			String setEmail = result.getString("email");
			textField_14.setText(setEmail);
			updateTapEmail.setText(setEmail);
			String setMobile = result.getString("mobileNo");
			textField_15.setText(setMobile);
			updateTabMobileNo.setText(setMobile);
			String setPassword = result.getString("password");
			updateTabPassword.setText(setPassword);
			passwordRetrived = setPassword;
			String setAddress = result.getString("address");
			updateAddress.setText(setAddress);
			String setAccountNo = result.getString("accountNumber");
			textField_7.setText(setAccountNo);
			accnoAccPanelField.setText(setAccountNo);
			accnoEmailPanelField.setText(setAccountNo);
			accnoDepositPanelField.setText(setAccountNo);
			accnoDepositionField.setText(setAccountNo);
			String setIfscCode = result.getString("ifsc_Code");
			textField_8.setText(setIfscCode);
			String setCardNo = result.getString("card_Number");
			textField_9.setText(setCardNo);
			String setCvv = result.getString("cvv");
			textField_10.setText(setCvv);
			String balanceRetrival = result.getString("account_Balance");
			balanceVarLabel.setText(balanceRetrival);
			
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	private  void generatedOtp() {
		String message = "";
		for (int i =1;i<5;i++)
    	{  
			 
              	String nums= "0123456789";
    		Random random = new Random();
    		int indexOtp = random.nextInt(nums.length());
    		 message =message +nums.charAt(indexOtp);	 
    	}
		otpMessage = message;
		JOptionPane.showMessageDialog(transferPanel, message, "Message", JOptionPane.PLAIN_MESSAGE);
		
	}
	
	private long currentAmount = 0;
	private void withdraw(int amountEntered)
	{
		retriveAccount();
		currentAmount = Integer.parseInt(balanceVarLabel.getText());
		currentAmount = currentAmount - amountEntered;
		if(currentAmount<=0) { 
			JOptionPane.showMessageDialog(null, "Cannot carry out Transaction", "", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
		try {
			PreparedStatement pst = conn.prepareStatement("UPDATE `account_details` set account_Balance = '"+currentAmount+"' where accountNumber = '"+retriveAccount+"' ");
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Balance " +currentAmount);
		retriveAccount();
		
       try {
		PreparedStatement pst = conn.prepareStatement("INSERT INTO `transactions` (`account_number`, `withdrawn`,`total_Balance`) VALUES(?,?,?) " );
		pst.setString(1, retriveAccount);
		pst.setString(2, String.valueOf(amountEntered));
		pst.setString(3, String.valueOf(currentAmount));
		pst.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
	 }
	}
	private void deposit(int amountEntered)
	{   retriveAccount();
		currentAmount = Integer.parseInt(balanceVarLabel.getText());
		currentAmount = currentAmount + amountEntered;
		try {
			PreparedStatement pst = conn.prepareStatement("UPDATE `account_details` set account_Balance = '"+currentAmount+"' where accountNumber = '"+retriveAccount+"' ");
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Balance " +currentAmount);
		retriveAccount();
		
		try {
			PreparedStatement pst = conn.prepareStatement("INSERT INTO `transactions` (`account_number`, `deposited`,`total_Balance`) VALUES(?,?,?) " );
			pst.setString(1, retriveAccount);
			pst.setString(2, String.valueOf(amountEntered));
			pst.setString(3, String.valueOf(currentAmount));
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String transfereeAccoutNo = "";
	private String transfereeIfscCode = "";
	private long transfereeBalance = 0;
	private JTable table;
	
	//This method is used to carry out transfer function
	private void transfer()
		{
		  transfereeAccoutNo = beneficiaryAccount.getText();
		  transfereeIfscCode = benefeciaryIfsc.getText();
		  //This try block tries to get the account details of the entered Beneficiary 
		  try {
			PreparedStatement pst = conn.prepareStatement("SELECT * FROM `account_details` WHERE accountNumber = '"+transfereeAccoutNo+"' "
					                + "and ifsc_Code = '"+transfereeIfscCode+"' ");
			ResultSet result = pst.executeQuery();
			if(result.next())
			{
				String balance = result.getString("account_Balance");
				transfereeBalance = Integer.parseInt(balance);
				}
			else
			{
					JOptionPane.showMessageDialog(null, "Check the Beneficary Details", "", JOptionPane.ERROR_MESSAGE);
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
		  //This try block tries to update the beneficiary account balance and The senders balance 
		  try {
				  long transferAmountValue = Integer.parseInt(transferAmount.getText());
				  transfereeBalance = transfereeBalance + transferAmountValue;
				PreparedStatement pst1 = conn.prepareStatement("UPDATE `account_details` SET account_Balance = '"+transfereeBalance+"' Where accountNumber = '"+transfereeAccoutNo+"' ");
				pst1.executeUpdate();
				JOptionPane.showMessageDialog(null, "Transferred amount " +transferAmount.getText() +" " +"to " +transfereeAccoutNo);
				  
				
				
				currentAmount = Integer.parseInt(balanceVarLabel.getText());
				currentAmount = currentAmount - transferAmountValue;
				PreparedStatement pst = conn.prepareStatement("UPDATE `account_details` set account_Balance = '"+currentAmount+"' where accountNumber = '"+retriveAccount+"'");
				pst.executeUpdate(); 
				retriveAccount();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  long tvalue = Integer.parseInt(transferAmount.getText());
			  try {
				PreparedStatement pst2 = conn.prepareStatement("INSERT INTO `transactions` (`account_number` , `transfered_to` , `transfered_amount` , `total_Balance`) values(?,?,?,?)" );
				pst2.setString(1,retriveAccount);
				pst2.setString(2, transfereeAccoutNo);
				pst2.setString(3, String.valueOf(tvalue));
				pst2.setString(4, String.valueOf(currentAmount));
				pst2.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	//This method restrives the table content of the particular account number
	// and displays it in the descending order
	// last 10 transactions are retrived
	private void retriveTable()
	{
		int a ;
		try {
			PreparedStatement pst = conn.prepareStatement("SELECT * FROM `transactions` WHERE account_number = '"+retriveAccount+"' Order by id Desc  ");
			ResultSet res = pst.executeQuery();
			ResultSetMetaData rd = (ResultSetMetaData) res.getMetaData();
			a = rd.getColumnCount();
			DefaultTableModel df = (DefaultTableModel) table.getModel();
			
			
			int j =1;
			int count = 0;
			while (res.next())
			{  
				if(count<11) {
				Vector v1 = new Vector();
				for (int i = 0 ; i<a ; i++)
				{
					v1.add(j);
					v1.add(res.getString("withdrawn"));
					v1.add(res.getString("deposited"));
					v1.add(res.getString("transfered_to"));
					v1.add(res.getString("transfered_amount"));
					v1.add(res.getString("total_Balance"));
				}
				df.addRow(v1);
				++j;
				++count ;
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//This method resets the table to empty by re-initializing the table coulmns
	private void resetTable()
	{
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"SR.NO", "Withdrawn", "Deposited", "Transferred To ", "Amount Send", "Total Balance"
				}
			));
		miniStatementButton.setEnabled(true);
	}
	
	
}
