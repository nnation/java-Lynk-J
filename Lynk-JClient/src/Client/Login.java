package Client;


import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;


public class Login extends JFrame 
{

	private int value,port;	
	private JTextField userNameField;
	private JPasswordField pass;
	Image image;
	char[] password;
	private ImageIcon img, enterButtonIcon, cancelButtonIcon; 
	private JLabel logo, logonText, ebText, xpText, passWordText, userNameText, unusedLabel, unusedLabel2,unusedLabel3;
	private JLabel okButtonLabel, cancelButtonLabel;
	private JTextField userName;
	private JButton okButton, cancelButton;
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
	private String userName2;
	
	public Login()
	{
		
				
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		
		
		this.setIconImage(new ImageIcon("Image/EnterButton.jpg").getImage());
	
		
		
		
		
		
		setResizable(false);
		setTitle("Lynk-J File Manager: Client");
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
		cancelButtonIcon=new ImageIcon("Image/CancelButton.png");
		
		enterHandler=new EnterButtonHandler();
		userNameField.setActionCommand("okButton");
		userNameField.addActionListener(enterHandler);
		okButton=new JButton(enterButtonIcon);
		okButton.setBackground(Color.WHITE);
		okButton.setBorder(BorderFactory.createEmptyBorder());
		okButton.addActionListener(enterHandler);
	
		
		cancelButton=new JButton(cancelButtonIcon);
		cancelButton.addActionListener(enterHandler);
		
		
		cancelButton.setBackground(Color.WHITE);
		cancelButton.setBorder(BorderFactory.createEmptyBorder());
		
			
		centerInnerPanel2.add(okButton);
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
	
		
	
	public class EnterButtonHandler implements ActionListener
	{
		
		public void actionPerformed(ActionEvent event)
		{
			
			if(event.getSource()==okButton && userNameField.getText().length()!=0)	
			{
				
				char [] pass1;
				setUserName(userNameField.getText()); 
						
				
				pass1=pass.getPassword();
				String password=new String(pass1);
				
				Client C = new Client(userName1, password);
						
				value = C.verifyClient();
				
				if(value == 1)
				{		
					setVisible(false);
					
				}//end if
					
				if(value == 0 )
				{
					JOptionPane.showMessageDialog(null, "You are not autorized");
				}//end if
					
			}//end if
			
			if(event.getSource()== cancelButton){
				setVisible(false);
			}
		}//end method actionPerformed
					
	}//end enterbuttonHandler
		
			
	

	
	
	public void setUserName(String user)
	{
		userName1=user;
		
	}
	public void setPassword(char[] pass)
	{
		password1=pass;
		
		
		
	}
	
	public String getUserName()
	{
		
		return userName1;
	
	
	}
	
	public char[] getpassWord()
	{
		
		return password1;
	
	
	}
	
	
	
	

}
