/**This is the interface of the 
 * Server for the file manager
 */

//Import all needed classes

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import FileManager.*;
import ServerDB.*;

public class ServerFrame extends JFrame{
	
	private GridBagLayout layout = new GridBagLayout();
	private JButton AddUserBtn1;
	private JButton UpdateUserBtn1;
	private JButton DeleteUserBtn1;
	private JTextField txtFName;
	private JTextField txtLName;
	private JTextField txtUName1;
	private JPasswordField PassFld;
	private ButtonGroup Grp;
	private JRadioButton userRdo;
	private JRadioButton adminRdo;
	private JTextField txtDUName, txtFileExtension;
	private JTextField txtUName;
	private JTextField txtFFolder;
	private JCheckBox readCBox, writeCBox, deleteCBox;
	private JTextField txtCreateFF1;
	private JTextField txtCreateFF2;
	private JTextField txtCreateFF3;
	private JButton CreateFile_foldBtn1;
	private EventHandler handler;
	private Server_Admin userInfo;	
	private AccessControlList acl;
	Server_Admin admin=new Server_Admin();
	
	//set ServerFrame GUI
	public ServerFrame (){
		super("Lynk-J File Manager: Server");
		
		JTabbedPane tabs  = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		handler = new EventHandler();
		
		setResizable(false);

///1////		
		//set up AddUserPanel and add it to JTabbedPane
		JPanel AddUserPanel = new JPanel(); //create the add user panel (1st panel)
		TitledBorder title0;
		title0 =	BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED),"Add User");
		AddUserPanel.setBorder(title0);
		AddUserPanel.setLayout(layout);
		AddUserPanel.setBackground(Color.WHITE);
		
		txtFName = new JTextField(10); 
		JLabel fName = new JLabel("First Name: ");
		addConstraints(fName, 0, 0,1,1);
		addConstraints(txtFName, 0, 1,1,1);
		AddUserPanel.add(fName);
		AddUserPanel.add(txtFName);
		
		txtLName = new JTextField(10); 
		JLabel lName = new JLabel("Last Name: ");
		addConstraints(lName, 1, 0,1,1);
		addConstraints(txtLName, 1, 1,1,1);
		AddUserPanel.add(lName);
		AddUserPanel.add(txtLName);
	
		
		txtUName1 = new JTextField(10);
		JLabel label1 = new JLabel("User Name: ");
		addConstraints(label1, 2, 0,1,1);
		addConstraints(txtUName1, 2, 1,1,1);
		AddUserPanel.add(label1);
		AddUserPanel.add(txtUName1);
		
		JLabel label20 = new JLabel("Password: ");
		PassFld  = new JPasswordField(10);
		addConstraints(label20, 3, 0,1,1);
		addConstraints(PassFld, 3, 1,1,1);
		AddUserPanel.add(label20);
		AddUserPanel.add(PassFld);
		
		JLabel label21 = new JLabel("Account Type: ");
		Grp  = new ButtonGroup();
		userRdo = new JRadioButton("User", true);
		adminRdo = new JRadioButton("Admin", false);
		userRdo.setBackground(Color.WHITE);
		userRdo.setAlignmentX(LEFT_ALIGNMENT);
		adminRdo.setBackground(Color.WHITE);
		
		Grp.add(userRdo);
		Grp.add(adminRdo);
		addConstraints(label21, 4, 0,1,1);
		addConstraints(userRdo, 4, 1,1,1);
		addConstraints(adminRdo, 4, 2,1,1);
		AddUserPanel.add(label21);
		AddUserPanel.add(userRdo);
		AddUserPanel.add(adminRdo);
		
		AddUserBtn1 = new JButton("Apply");
		AddUserPanel.add(AddUserBtn1);
		addConstraints(AddUserBtn1, 5,  0, 4, 1);
		AddUserBtn1.addActionListener(handler);
		
		JPanel pan1 = new JPanel();
		pan1.add(AddUserPanel);
		tabs.addTab("Create User Account", null, pan1, "1. Create User Account"); //add AddUserPanel to tab and label it
		
		
///2////		
		//set up DeleteUserPanel and add it to JTabbedPane		
		JPanel DeleteUserPanel = new JPanel(); //create the add user panel (1st panel)
		TitledBorder title1;
		title1 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED),"Delete User");
		DeleteUserPanel.setBackground(Color.WHITE); //set background color
		DeleteUserPanel.setBorder(title1);
		DeleteUserPanel.setLayout(layout);

		JLabel DeleteUserlabel1 = new JLabel("User Name: ");
		txtDUName = new JTextField(10);
		addConstraints(DeleteUserlabel1, 0,0,1,1);
		addConstraints(txtDUName, 0,1,1,1);
		DeleteUserPanel.add(DeleteUserlabel1); // add DeleteUserlabel1 to the panel
		DeleteUserPanel.add(txtDUName); // add txtDUName to the panel
		
		
		DeleteUserBtn1 = new JButton("Delete");
		addConstraints(DeleteUserBtn1, 1, 0, 7, 1);
		DeleteUserPanel.add(DeleteUserBtn1); // add DeleteUserBtn1 to the panel
		DeleteUserBtn1.addActionListener(handler);
		/**
		 * Add to panel
		 */
		JPanel pan2 = new JPanel();
		pan2.add(DeleteUserPanel);
		
		tabs.addTab("Delete User Account", null, pan2, "2. Delete User Account"); //add pan2 to tab and label it
		
///3///
		//set up UpdateUserPanel and add it to JTabbedPane
		JPanel UpdateUserPanel = new JPanel(); //create the update user panel (3nd panel)
		UpdateUserPanel.setBackground(Color.WHITE); //set background color
		
		TitledBorder title;
		title =	BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED),"Update User");
		UpdateUserPanel.setBorder(title);
		UpdateUserPanel.setLayout(layout);
		
		txtUName = new JTextField(10);
		JLabel label3 = new JLabel("User Name: ");
		addConstraints(label3, 0, 0,1,1);
		addConstraints(txtUName, 0, 1,1,1);
		UpdateUserPanel.add(label3);
		UpdateUserPanel.add(txtUName);
				
		txtFFolder = new JTextField(10);
		JLabel label2 = new JLabel("File/Folder: ");
		addConstraints(label2, 1, 0,1,1);
		addConstraints(txtFFolder, 1, 1,1,1);
		UpdateUserPanel.add(label2);
		UpdateUserPanel.add(txtFFolder);
		
		txtFileExtension=new JTextField(10);
		txtFileExtension.setText(".");
		JLabel folderLabel=new JLabel("File Extension: ");
		addConstraints(folderLabel, 2,0,1,1);
		addConstraints(txtFileExtension, 2,1,1,1);
		UpdateUserPanel.add(folderLabel);
		UpdateUserPanel.add(txtFileExtension);
			

		JLabel label31 = new JLabel("Access Type: ");
		readCBox = new JCheckBox("Read");
		readCBox.setBackground(Color.WHITE);
		writeCBox = new JCheckBox("Write");
		writeCBox.setBackground(Color.WHITE);
		deleteCBox = new JCheckBox("Delete");
		deleteCBox.setBackground(Color.WHITE);
		
		addConstraints(label31, 3, 0 , 1 , 1);
		addConstraints(readCBox, 3, 1, 1, 1);
		addConstraints(writeCBox, 4, 1, 1, 1);
		addConstraints(deleteCBox, 5, 1, 1, 1);
				
		UpdateUserPanel.add(label31);
		UpdateUserPanel.add(readCBox);
		UpdateUserPanel.add(writeCBox);
		UpdateUserPanel.add(deleteCBox);
		
		UpdateUserBtn1 = new JButton("Apply");
		UpdateUserPanel.add(UpdateUserBtn1);
		addConstraints(UpdateUserBtn1, 6,  0, 2, 1);
		UpdateUserBtn1.addActionListener(handler);
		
		
		JPanel pan = new JPanel();
		pan.add(UpdateUserPanel);
	
		/**
		 * Add to panel
		 */ 
		tabs.addTab("Update User Account", null, pan, "3. Update User Account"); //add UpdateUserPanel to tab and label it

///4///
		//set CreateFile_foldPanel and add it to JTabbedPane
		JPanel CreateFile_foldPanel = new JPanel(); //create the CreateFile_fold panel (4nd panel)
		CreateFile_foldPanel.setBackground(Color.WHITE); //set background color
		
		TitledBorder title2;
		title2 =	BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED),"Update User");
		CreateFile_foldPanel.setBorder(title2);
		CreateFile_foldPanel.setLayout(layout);
		
		JLabel CreateFile_foldLbl1 = new JLabel("Files/Folders Name: ");
		txtCreateFF1  = new JTextField(10);
		addConstraints(CreateFile_foldLbl1, 0,0,1,1);
		addConstraints(txtCreateFF1, 0,1,1,1);
		CreateFile_foldPanel.add(CreateFile_foldLbl1); // add CreateFile_fold1 to the panel
		CreateFile_foldPanel.add(txtCreateFF1); // add txtCreateFF to the panel
		
		JLabel CreateFile_foldLbl2 = new JLabel("Address Path: ");
		txtCreateFF2  = new JTextField(10);
		addConstraints(CreateFile_foldLbl2, 1,0,1,1);
		addConstraints(txtCreateFF2, 1,1,1,1);
		CreateFile_foldPanel.add(CreateFile_foldLbl2); // add CreateFile_foldLbl2 to the panel
		CreateFile_foldPanel.add(txtCreateFF2); // add txtCreateFF2 to the panel

		JLabel CreateFile_foldLbl3 = new JLabel("File Extension: ");
		txtCreateFF3  = new JTextField(10);
		txtCreateFF3.setText(".");
		addConstraints(CreateFile_foldLbl3, 2,0,1,1);
		addConstraints(txtCreateFF3, 2,1,1,1);
		CreateFile_foldPanel.add(CreateFile_foldLbl3); // add CreateFile_foldLbl3 to the panel
		CreateFile_foldPanel.add(txtCreateFF3); // add txtCreateFF3 to the panel

		CreateFile_foldBtn1 = new JButton("Save");
		addConstraints(CreateFile_foldBtn1, 3, 0, 7, 1);
		CreateFile_foldPanel.add(CreateFile_foldBtn1); // add CreateFile_foldBtn1 to the panel
		CreateFile_foldBtn1.addActionListener(handler);
		
		/**
		 * Add to panel
		 */
		JPanel pan3 = new JPanel();
		pan3.add(CreateFile_foldPanel);
		tabs.addTab("Create Files/Folders", null, pan3, "4. Create Files/Folders"); //add pan3 to tab and label it

		
///5///		
		//set up ViewStoreTab and add it to JTabbedPane
		/**
		 * Add View of folders and files that are available
		 */ 
		//TODO make the JFileChooser Functional  and there is a function in Server_Admin to be utilize for this
		JFileChooser vF= new JFileChooser("C:\\Documents and Settings\\Nate\\Desktop\\Network Folder\\");
		tabs.addTab("View Store Files", null, vF, "5. View Store Files"); //add ViewStoreFPanel to tab and label it
		
		
///6///
		//set internalTab and add it to JTabbedPane
		JTabbedPane internalTab  = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		
		//set up QueryDBPanel and add it to internal panel
		JPanel QueryDBPanel1 = new JPanel(); //create the Query Database panel (6th panel)
		JPanel QueryDBPanel2 = new JPanel(); 
		JPanel QueryDBPanel3 = new JPanel();
		
		JScrollPane scrollTable1 = new JScrollPane();
		JTable Table2 = new JTable();
		
		/**
		 * check if user read/update a file
		 * check user rights
		 * check user information
		 */
		
		
		QueryDBPanel1.add(admin.viewLogTable().getTableHeader(),BorderLayout.PAGE_START);
		
		QueryDBPanel1.add(admin.viewLogTable(), BorderLayout.CENTER);
		
		internalTab.addTab("Check if User Read/Update a File/Folder", null, QueryDBPanel1, "1 Internal Panel"); //add internalTab to tab and label it
		
		
		QueryDBPanel2.add(admin.viewAclInfo().getTableHeader(),BorderLayout.PAGE_START);
		QueryDBPanel2.add(admin.viewAclInfo(), BorderLayout.CENTER);
		
		internalTab.addTab("Check User Rights", null, QueryDBPanel2, "2 Internal Panel"); //add internalTab to tab and label it
		
		
			
		QueryDBPanel3.add(admin.veiwUserInfo().getTableHeader(), BorderLayout.PAGE_START);
		QueryDBPanel3.add(admin.veiwUserInfo(), BorderLayout.CENTER);
		
		internalTab.addTab("Check User Information", null, QueryDBPanel3, "3 Internal Panel"); //add internalTab to tab and label it
		
		tabs.addTab("Query Database", null, internalTab, "6 Panel"); //add internalTab to tab and label it
		
		
		
		add(tabs); //add JTabbedPane to Frame

	}// end ServerFrame constructor
	

	private void addConstraints(Component component, int row, int column, int width, int height)
	{

		GridBagConstraints Constraints = new GridBagConstraints();
		 
		Constraints.gridy = row;
		Constraints.gridx = column;
		Constraints.gridwidth = width;
		Constraints.gridheight = height;		 

		layout.setConstraints(component, Constraints);
	}// end addConstraints
	
	
	public class EventHandler implements ActionListener 
	{
		String Stest = "";
		public void actionPerformed( ActionEvent event){
			
			//Conduct action if Add Button was pressed
			if (event.getSource() == AddUserBtn1){
				//test if any fields are empty
				if (txtFName.getText().length() == 0 || txtLName.getText().length() == 0
						|| txtUName1.getText().length() == 0 || PassFld.getPassword().length == 0){
					
					//Display Message
					JOptionPane.showMessageDialog(ServerFrame.this , "You have empty field!");
					
				}//end if
				else {
					String accType;
					
					if (userRdo.isSelected() == true){
						accType = userRdo.getLabel();
					}//end if
					else { 
						accType = adminRdo.getLabel();
					}// end if
					
					userInfo  = new Server_Admin(txtFName.getText(), txtLName.getText(), txtUName1.getText(), PassFld.getPassword(), accType);
					userInfo.CreateAcct();
					
					
					//Reset Fields to Null
					txtFName.setText(null);
					txtLName.setText(null);
					txtUName1.setText(null);
					PassFld.setText(null);
					userRdo.isEnabled();
				}//end else
				
			}//end Add Button action if
			
			//Else if Delete User button was pressed
			else if (event.getSource() == DeleteUserBtn1) {
				//test if any fields are empty
				if (txtDUName.getText().length() == 0){
					//Display message
					JOptionPane.showMessageDialog(ServerFrame.this , "You have empty field!");
					
				}//end if
				else {
					userInfo = new Server_Admin();
					userInfo.DeleteAcct(txtDUName.getText());
					txtDUName.setText(null);
				}//end else
				
			}// end delete User button Action
			
			
			else if(event.getSource()==UpdateUserBtn1)
			{
				
					
					if (txtUName.getText().length() == 0 || txtFFolder.getText().length() == 0 || txtFileExtension.getText().length() == 0 
							|| txtFileExtension.getText().equals(".") ){
																		
						//Display Message
						JOptionPane.showMessageDialog(ServerFrame.this , "You have empty field!");
					}// end if
					
					else if(readCBox.isSelected() == false && writeCBox.isSelected() ==false && deleteCBox.isSelected() ==false){
						JOptionPane.showMessageDialog(ServerFrame.this , "You have an Unselected checbox");
						
						
					}// end if
					else {
						
						int read = 0, write = 0, delete = 0;
						
						if (readCBox.isSelected() == true){
								read = 1;
						}//end if
						if (writeCBox.isSelected() == true){
							write = 1;
						}//end if
						if (deleteCBox.isSelected() == true){
							delete = 1;
						}//end if
							
						acl = new AccessControlList(txtUName.getText(), txtFFolder.getText(), txtFileExtension.getText(), read, write, delete);
	
						acl.Synchronization();
						
						//reset field to null or blank
						txtUName.setText(null);
						txtFFolder.setText(null);
						txtFileExtension.setText(".");
						readCBox.setSelected(false);
						writeCBox.setSelected(false);
						deleteCBox.setSelected(false);
						
					}//end else
			}
			
			else if(event.getSource()== CreateFile_foldBtn1) {

				if (txtCreateFF1.getText().length() == 0 || txtCreateFF2.getText().length() == 0 || 
						txtCreateFF3.getText().equals(".")){
																	
					//Display Message
					JOptionPane.showMessageDialog(ServerFrame.this , "You have empty field!");
				}// end if
				
				else {
					Resource res = new Resource(txtCreateFF1.getText(), txtCreateFF2.getText(), txtCreateFF3.getText());
					Server_Admin Admin = new Server_Admin();
					Admin.CreateFiles(res);
					
					txtCreateFF1.setText(null);
					txtCreateFF2.setText(null);
					txtCreateFF3.setText(null);
				}//end else
			

				
			}
				
			
			
		}//end method ActionPerformed 
	}// end class Eventhandler

}//end class ServerFrame
