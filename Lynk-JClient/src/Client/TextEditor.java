
package Client;


import javax.swing.*;

import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextEditor extends JFrame {

	
	private JTextArea textArea;
	private JButton cancel, save, delete;
	private JLabel label1;
	private File file;
	private FileInputOutput Io = null;
	private StringBuffer contents = null;
	private EventHandler handler;
	private int Open_Close = 0;
	private DataInputStream dataIn = null;
	private DataOutputStream dataOut = null;
	private String userName; 

	
	public TextEditor(String Un, File Userfile, int accessLevel, DataOutputStream dataO, DataInputStream dataI)
	{

		super("Text Editor");
		
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		
		file = Userfile;
		userName = Un;
		dataOut = dataO;  //send data to server
		dataIn = dataI;  //read data from server
				
		handler = new EventHandler();
		
		contents = new StringBuffer();
		Io = new FileInputOutput();
		contents = Io.DisplayFileContent(file.getPath());
		
		//if the file was not found or there was an error opening the file close 
		// the TextEditor
		if (contents == null){
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
			
		Container con=getContentPane();
		
		//Menu Bar
		JMenuBar menuBar=new JMenuBar();
		menuBar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		
		//setJMenuBar(menuBar);
		
		//con.add(menuBar);
		JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        
	    menuBar.add(fileMenu);
	    menuBar.add(editMenu);
	        
	    
	    JMenuItem newAct = new JMenuItem("New");
	    JMenuItem edit = new JMenuItem("edit");
	    
	    
	    fileMenu.add(newAct);
	    editMenu.add(edit);
	    
		
		//Main Panel
		JPanel mainPanel=new JPanel(new BorderLayout());
		mainPanel.setSize(300, 300);
		
		//north Panel
		JPanel southPanel=new JPanel(new FlowLayout());
		southPanel.setBackground(Color.GRAY);
		
		//Buttons
		
		cancel=new JButton("Cancel");
		cancel.setSize(new Dimension(50,50));
		cancel.addActionListener(handler);
		
		save=new JButton("Save");
		if (accessLevel == 3 || accessLevel == 4 || accessLevel == 7 || accessLevel == 8){
			save.setEnabled(true);
		}//end if
		else {
			save.setEnabled(false);
		}//end else
		save.addActionListener(handler);
		
		delete = new JButton("Delete");
		if (accessLevel == 2 || accessLevel == 4 || accessLevel == 6 || accessLevel == 8){
			delete.setEnabled(true);
		}//end if
		else {
			delete.setEnabled(false);
		}//end else
		delete.addActionListener(handler);
		
		//Labels
		label1=new JLabel("HELLO");
		label1.setFont(new Font("TimesRoman", Font.PLAIN,  0));
		label1.setForeground(Color.GRAY);
		
		
		//Text Area
		textArea=new JTextArea();
		// show file contents here 
        
		textArea.setText(contents.toString());
		textArea.setWrapStyleWord(true);
		
		JScrollPane areaScrollPane = new JScrollPane(textArea);
		areaScrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane.setPreferredSize(new Dimension(250, 250));
		
		
		//set textArea to readonly
		if (accessLevel == 5 || accessLevel == 6){
			textArea.setEditable(false);
		}
		
		
		southPanel.add(save);
		southPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		southPanel.add(label1);
		southPanel.add(cancel);
		southPanel.add(delete);
		
		//add to main Panel
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		//mainPanel.add(save,BorderLayout.NORTH);
		mainPanel.add(areaScrollPane,BorderLayout.CENTER);
		
		con.add(mainPanel);
		
		
		setJMenuBar(menuBar);
		setVisible(true);
		setSize(400,400);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//constructor
	
	
	public class EventHandler implements ActionListener 
	{
		public void actionPerformed( ActionEvent event){
			
			if (event.getSource() == save){
				
				Io.SaveFileContent(textArea.getText(), file.getPath());
				JOptionPane.showMessageDialog(null, "The file "+file.getName()+" is SAVED!");
				
			}
			
			if (event.getSource() == cancel){
 
				try{
					dataOut.writeUTF("Close."+file.getName()+":"+userName+file.getName());
					dataOut.flush();
					
					Open_Close = dataIn.readInt();
				} 
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dispose();
				OpenFiles c1 = new OpenFiles(userName, dataOut, dataIn);
			}

			
			if (event.getSource() == delete){
				// Attempt to delete it
			    boolean success = file.delete();
			    
			    if (!success) {
			      throw new IllegalArgumentException("Delete: deletion failed");
			    }
				JOptionPane.showMessageDialog(null, "The file "+file.getName()+" is DELETED!");

				try{
					dataOut.writeUTF("Delete."+file.getName()+":"+userName+file.getName());
					dataOut.flush();
					
					Open_Close = dataIn.readInt();
				} 
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				dispose();
				OpenFiles c1 = new OpenFiles(userName, dataOut, dataIn);
			}
			
		}//end method actionPerformed
		
			
	}// end class EventHandler
	
	public int closeTextEditor (){
		
		return Open_Close;
	}

	
	
}//end class TextEditor
