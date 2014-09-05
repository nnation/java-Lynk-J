


import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

public class Login extends JFrame 
{
	private Connection conn2;
	private Statement stmt2;
	private ResultSet rs;

	private JTextField userNameField;
	private JPasswordField pass;
	Image image;
	char[] password;
	private ImageIcon img, enterButtonIcon, resetButtonIcon,cancelButtonIcon; 
	private JLabel logo, logonText, ebText, xpText, passWordText, userNameText, unusedLabel, unusedLabel2,unusedLabel3;
	private JLabel okButtonLabel, resetButtonLabel;
	private JTextField userName;
	private JButton okButton, resetButton, cancelButton ;
	JPasswordField passWord;
	private JPanel northPanel, southPanel, centerPanel, eastPanel, westPanel, centerInnerPanel2;
	Graphics g;
	BorderLayout border;
	GridBagLayout gridLayout;
	private GridBagConstraints constraints, northPanelConstraints;
	ImageIcon icon;
	private EnterButtonHandler enterHandler;
	String userName1;
	char[] password1;
	
	
	public Login()
	{
		
				
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		
		
		this.setIconImage(new ImageIcon("Image/EnterButton.jpg").getImage());
	
		
		
		
		
		
		setResizable(false);
		setTitle("Lynk-J File Manager: Server");
		setLayout(new BorderLayout());
		
		executeGui();
		setSize(350,270);
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
	}
	
	

	


	public void executeGui()
	{
		
		Container con=getContentPane();
		con.setBackground(Color.WHITE);
		
		JPanel northPanel=new JPanel(new BorderLayout());
		JPanel southPanel=new JPanel();
		JPanel centerPanel=new JPanel(new BorderLayout(3,3));
		
		JPanel centerInnerPanel=new JPanel(new BorderLayout());
		JPanel northInnerPanel=new JPanel(new FlowLayout());
		
		JPanel southInnerPanel=new JPanel(new FlowLayout());
		
		centerInnerPanel2=new JPanel(new FlowLayout());
		centerInnerPanel2.setPreferredSize(new Dimension(10,10));
		
		constraints=new GridBagConstraints();
		northPanelConstraints=new GridBagConstraints();	
		
		southInnerPanel.setBackground(Color.BLUE);
		
		unusedLabel=new JLabel("HELLO");
		unusedLabel.setFont(new Font("Serif",Font.PLAIN,10));
		unusedLabel.setForeground(Color.BLUE);
		southInnerPanel.add(unusedLabel);
		
		
		unusedLabel3=new JLabel("HELLO");
		unusedLabel3.setFont(new Font("Serif",Font.PLAIN,10));
		unusedLabel3.setForeground(Color.BLUE);
		northInnerPanel.setBackground(Color.blue);
		northInnerPanel.add(unusedLabel3);
		centerInnerPanel2.setBackground(Color.white);
				
		//Format Password Field
		pass=new JPasswordField(12);
		pass.setForeground(Color.BLUE);
		pass.setPreferredSize(new Dimension(46,49));
		pass.setBorder(BorderFactory.createTitledBorder(null, "Password"));
				
		
		//Format UserName Field
		userNameField=new JTextField(12);
		userNameField.setForeground(Color.BLUE);
		userNameField.setFont(new Font("Serif", Font.PLAIN, 18));
		userNameField.setBorder(BorderFactory.createTitledBorder("User Name"));
		userNameField.setPreferredSize(new Dimension(46,49));
		
		centerInnerPanel2.add(userNameField);
		centerInnerPanel2.add(pass);
		
		enterButtonIcon=new ImageIcon("Image/EnterButton.png");
		resetButtonIcon=new ImageIcon("Image/resetButton.png");
		cancelButtonIcon=new ImageIcon("Image/cancelButton.png");
		
		enterHandler=new EnterButtonHandler();
		pass.setActionCommand("okButton");
		pass.addActionListener(enterHandler);
		userNameField.setActionCommand("okButton");
		userNameField.addActionListener(enterHandler);
		okButton=new JButton(enterButtonIcon);
		okButton.setBackground(Color.WHITE);
		okButton.setBorder(BorderFactory.createEmptyBorder());
		okButton.addActionListener(enterHandler);
	
		
		resetButton=new JButton(resetButtonIcon);
		resetButton.addActionListener(enterHandler);
		
		
		resetButton.setBackground(Color.WHITE);
		resetButton.setBorder(BorderFactory.createEmptyBorder());
		
		cancelButton=new JButton(cancelButtonIcon);
		cancelButton.setBackground(Color.WHITE);
		cancelButton.setBorder(BorderFactory.createEmptyBorder());
		cancelButton.addActionListener(enterHandler);
		
		centerInnerPanel2.add(okButton);
		centerInnerPanel2.add(resetButton);
		centerInnerPanel2.add(cancelButton);
				
		
		
		centerInnerPanel.setBackground(Color.WHITE);
		centerInnerPanel.add(northInnerPanel,BorderLayout.NORTH);
		centerInnerPanel.add(southInnerPanel,BorderLayout.SOUTH);
		centerInnerPanel.add(centerInnerPanel2,BorderLayout.CENTER);
				
		
		southPanel.setBackground(Color.WHITE);
		unusedLabel2=new JLabel("HELLO");
		unusedLabel2.setForeground(Color.WHITE);
		southPanel.add(unusedLabel2);
		
		centerPanel.add(centerInnerPanel);
		centerPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		centerPanel.setBackground(Color.WHITE);
		
		img=new ImageIcon("Image/ms.jpg");
		logo=new JLabel(img);
		logonText=new JLabel("Login Screen");
		logonText.setFont(new Font("Times", Font.BOLD, 25));
		ebText=new JLabel("@Ebscit3A, 2009");
	
		ebText.setFont(new Font("Times", Font.BOLD, 12));
		
		
		northPanel.setBackground(Color.WHITE);
		northPanel.add(logo, BorderLayout.WEST);
		northPanel.add(ebText, BorderLayout.AFTER_LINE_ENDS);
		
		northPanel.add(logonText, BorderLayout.CENTER);
		northPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
						
		con.add(northPanel, BorderLayout.NORTH);
		con.add(southPanel, BorderLayout.SOUTH);
		//con.add(eastPanel);
		//con.add(westPanel);
		con.add(centerPanel, BorderLayout.CENTER);
		
		
				
	}
	
		
	//Listener for button press
	public class EnterButtonHandler implements ActionListener
	{
		
		//Run event when user press a button
		public void actionPerformed(ActionEvent event)
		{
			Action text=userNameField.getAction();
			if(event.getSource() == okButton) { 
					if(userNameField.getText() == null || pass.getPassword().length == 0) {
						userNameField.getAction();
						JOptionPane.showMessageDialog(Login.this, "Can not have empty fields!");
					}//end if 
					else {
						if (tryLogin(userNameField, pass) == 1){
							
							setVisible(false);
							JOptionPane.showMessageDialog(Login.this, "Welcome to Lynk-J File Manager!");
							Thread serv = new Server(3000);
							serv.start();
							
						}
						else {
							userNameField.setText(null);
							pass.setText(null);
						}
					}// end else
			}//end if
			
			if (event.getSource() == resetButton) {
				userNameField.setText(null);
				pass.setText(null);
				
			}//end if
			
			if (event.getSource() == cancelButton) {
				setVisible(false);
				
			}//end if
			
				
			
		}//end method actionPerformed
		
			
	}// end class EnterButtonHandler
	
	public int tryLogin(JTextField userN, JPasswordField userP) {
		try {
			  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			
			  //connect to database
			  conn2 = DriverManager.getConnection("jdbc:odbc:Lynk-J DSN" , "Admin", "zxcvbnm");
			  
			  //create Statement
			  stmt2 = conn2.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			 			  
			  rs = stmt2.executeQuery("SELECT * FROM Person");   
				 
			  int flag = 0;
			  String Un, AccType, Pass, Pass1 = new String(userP.getPassword());
			  
			  while (rs.next()) {
				  Un = rs.getString("UserName");
				  Pass = rs.getString("Password");
				  AccType = rs.getString("AccountType");
				  
				  if (Un.equals(userN.getText()) && Pass.equals(Pass1) && AccType.equals("Admin") ){
					  flag = 1;
				  }//end if

				  if (Un.equals(userN.getText()) && Pass.equals(Pass1) && AccType.equals("User") ){
					  
					  JOptionPane.showMessageDialog(Login.this, "You do not have Permission to Login.\nYou must be an Administrator to login!");
					  return 0;
				  
				  }//end if

			  }//end while
			  
			  //test if flag was changed, if not data was not found
			  if (flag == 0){
				  JOptionPane.showMessageDialog(Login.this, "You have entered the wrong User Name/Password!");	
				  return 0;
			  }//end if	


		}//end try 
		
		catch (SQLException sqlException) {
				//Auto-generated catch block
			  JOptionPane.showMessageDialog(null, sqlException.getMessage(), "Database error", 
					  JOptionPane.ERROR_MESSAGE);

		}//end catch  
		catch (ClassNotFoundException e) {
			//Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "No Class for Driver",
					JOptionPane.ERROR_MESSAGE);
		}//end catch

		finally {
			try {
				stmt2.close();
				conn2.close();
			}//end try 
			catch (SQLException sqlE) {
				// Auto-generated catch block
			  JOptionPane.showMessageDialog(null, sqlE.getMessage(), "Error Closing Database", 
					  JOptionPane.ERROR_MESSAGE);

			}//end catch  
		}// end finally
		return 1;
	  		
	}// end method tryLogin

	public String getUserName()
	{
		
		return userName1;
	
	
	}
	
	public char[] getpassWord()
	{
		
		return password1;
	
	
	}
	
	
	
	

}
