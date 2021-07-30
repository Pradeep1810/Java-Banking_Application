import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.*;


public class RegistrationScreen {

	public static void main(String[] args) {
		

		registerFrame frame = new registerFrame();
	}

}

	
	
	class registerFrame extends JFrame implements ActionListener
	{
		
		 private    JLabel l1,l2,l3,l4,l5,l6,l7,l8;
		 private    JTextField lname,fname,eid,mno;
		 private    JRadioButton male,female;
		 private    ButtonGroup gender;
		  private   JComboBox day,month,year;
		  private   JTextArea add,screen;
		  private   JCheckBox terms;
		  private   JButton submit;
		  private          JButton log;
	  
		  Connection conn = null;
	   
	  
	 String [] days = { "1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	  String [] months = {"Jan","Feb","March","April","May","Jun","Jul","Aug","Sept","Oct","Nov","Dec" };
	  String [] years = {"1996","1997","1998","1999","2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021" };
	  
	  
	  registerFrame()
	   {
		  conn = DatabaseConnection.dbconnet();
		  
		   setTitle("Job Registration Form ");
		   setLayout(null);
		   getContentPane().setBackground(Color.pink);
		   setSize(680, 600);
		   setLocationRelativeTo(null);
		   setResizable(false);
		   
		  
		   
		   l1 = new JLabel("Last Name :");
		   l1.setBounds(20,80,100,20);
		   add(l1);
		      lname = new JTextField();
		      lname.setBounds(140,80,100,20);
		      add(lname);
		      
		   
		   l2 = new JLabel("First Name :");
		   l2.setBounds(20,130,100,20);
		   add(l2);
		      fname = new JTextField();
		      fname.setBounds(140,130,100,20);
		      add(fname);
		   
		   l3 = new JLabel("Email id :");
		   l3.setBounds(20,180,100,20);
		   add(l3);
		      eid = new JTextField();
		      eid.setBounds(140,180,130,20);
		      add(eid);
		   
		   l4 = new JLabel("Mobile no :");
		   l4.setBounds(20,230,100,20);
		   add(l4);
		      mno = new JTextField();
		      mno.setBounds(140,230,100,20);
		      add(mno);
		   
					   l5 = new JLabel("Gender :");
					   l5.setBounds(20,280,100,20);
					   add(l5);
					      male = new JRadioButton("Male");
					      male.setBounds(140,280,60,20);
					      add(male);
							      female = new JRadioButton("Female");
							      female.setBounds(220,280,70,20);
							      female.setSelected(true);
							      add(female);
									   gender = new ButtonGroup();
									       gender.add(male);
									       gender.add(female);
						
				l6 = new JLabel("DOB :");
				l6.setBounds(20,330,100,20);
		          add(l6);
		               day = new JComboBox(days);
		               day.setBounds(140,330,50,20);
		               add(day);
			               month = new JComboBox(months);
			               month.setBounds(200,330,70,20);
			               add(month);
				               year = new JComboBox(years);
				               year.setBounds(280,330,90,20);
				               add(year);
				               
				   l7 = new JLabel("Address :");			   
		           l7.setBounds(20,380,100,20);
		           add(l7);
		              add = new JTextArea();
		              add.setLineWrap(true);
		              add.setBounds(140,370,200,40);
		              add(add);
		              
		              
		              l8 = new JLabel("");
		              l8.setBounds(30,530,200,20);
		              add(l8);
		           
		         terms = new JCheckBox(" I accept the terms and Condition :");
		         terms.setBounds(80,430,220,20);
		         add(terms);
		        
			       screen = new JTextArea();
				          screen.setBounds(430,100,200,300);
				           screen.setLineWrap(true);
				           screen.setEditable(false);
			           add(screen);
		       
			    submit = new JButton("Submit");
			    submit.setBounds(130,480,100,20);
			    add(submit);
			    submit.addActionListener(this);
			    
			    
			    log = new JButton("Login");
			    log.setBounds(250,480,100,20);
			    add(log);
			    log.addActionListener(this);
		       
			    
			 
		   setDefaultCloseOperation(3);
		   setVisible(true);
	   }

	  

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		  try {
			String genderIS;
			  
			  if(e.getSource()==submit)
			  {	  
					if(male.isSelected()) {
						genderIS = "Male";
					}
					else 
						{
						genderIS ="Female";
						
						}
					if(!lname.getText().isBlank() && !fname.getText().isBlank() && !eid.getText().isBlank() && !add.getText().isBlank())
					{
						if(terms.isSelected())
						{
							l8.setText("Done ");
							
							String lastName = lname.getText();
							String firstName = fname.getText();
							String emailId = eid.getText();
							String mobileNumber = mno.getText();
							String dob = day.getSelectedItem() + "-" +month.getSelectedItem() +"-" +year.getSelectedItem();
							String address = add.getText();
							
							
							//screen.setText("Name :" +lastName +" " +firstName +"\n\n" +"Gender :" +genderIS +"\n\n" +"Email-id :" +" " +emailId +"\n\n" +"Mobile number :" +" " +mobileNumber +"\n\n" +"DOB :" +" " +dob +"\n\n" +"Adddress : " +address);
							
							PreparedStatement pst = conn.prepareStatement("INSERT INTO `account_details`(`lastName`, `Name`, `gender`, `email`, `password`, `mobileNo`, `dob`, `address`) VALUES(?,?,?,?,?,?,?,?)");
							pst.setString(1, lastName);
							pst.setString(2, firstName);
							pst.setString(3, genderIS);
							pst.setString(4, emailId);
							pst.setString(5, mobileNumber);
							pst.setString(6, mobileNumber);
							pst.setString(7, dob);
							pst.setString(8, address);
		
							pst.executeUpdate();
							
							BankDetailsGenerator test = new BankDetailsGenerator();
							test.registerValues( emailId , mobileNumber );
							test.setVisible(true);
							dispose();
						}
							else
							{
								l8.setText("Accept terms & Condition :");
								screen.setText(" ");
								
							}
						
				}
					else {
						JOptionPane.showMessageDialog(null, "Fill all the Fields" ,"" ,JOptionPane.ERROR_MESSAGE);
					}
}
		} 
		  catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(e.getSource()==log)
		{
			LoginScreen call = new LoginScreen();
			call.loginFrame.setVisible(true);
			dispose();

//			//System.exit(0);
			
			
		}
		
		
		
	}
	
	
	
		
	}
	
	
