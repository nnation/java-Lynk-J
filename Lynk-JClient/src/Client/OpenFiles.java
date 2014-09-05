package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.io.DataInputStream;
import java.io.File;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JFrame; 
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
   
	/**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */

	 
public class OpenFiles extends JFrame{

	private String UserN;
	
	public OpenFiles (String UserName, DataOutputStream dataOut, DataInputStream dataIn) 
   { 
		super("Lynk-J File Manager:Client <Login As: "+UserName +">");
		UserN = UserName;
		
		File file=new File("C:/Documents and Settings/Nate/Desktop/Network Folder/");
		
	    setForeground(Color.black);
	    setBackground(Color.lightGray);
		
	    Recursive recur = new Recursive(file, UserN, dataOut, dataIn);
		add(recur);
 	
	    pack();
	    setVisible(true);
		setSize(500,300);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
   } // end constructor
	
	
	/**
	 * Display a file system in a JTree view
	 * 
	 */
	public class Recursive extends JPanel{
	  /** Construct a FileTree */
		private File files;
		private DataOutputStream dataOut = null;
		private DataInputStream dataIn = null;
		private String UName = new String(); 
		
	  public Recursive(File dir, final String UN, final DataOutputStream dataO, final DataInputStream dataI) {
		  
		setLayout(new BorderLayout());

	    UName = UN;
	    dataOut = dataO;
	    dataIn = dataI;
	    
	    // Make a tree list with all the nodes, and make it a JTree
	    JTree tree = new JTree(addNodes(null, dir));

	    // Add a listener
	    tree.addTreeSelectionListener(
	    	new TreeSelectionListener() {

	    		public void valueChanged(TreeSelectionEvent e) {
	    			
	    				DefaultMutableTreeNode node = (DefaultMutableTreeNode) e
	    				.getPath().getLastPathComponent();	    		
	    	
	    		File opfile = new File("C:/Documents and Settings/Nate/Desktop/Network Folder"+getPath(node.getPath()));
	    		    		
	    		if( opfile.isFile() ) {
	        		//getAccess level
	        		int accessLevel = requestAccess(UName, opfile);

	    			if (accessLevel != 0 && accessLevel != 9) {
	    				
						TextEditor TE = new TextEditor(UName, opfile, accessLevel, dataOut, dataIn);    				
						dispose();
						
	    			}//end if
	    		}//end if
	      }// end method valueChanged
	    		
	    });

	    // Lastly, put the JTree into a JScrollPane.
	    JScrollPane scrollpane = new JScrollPane();
	    scrollpane.getViewport().add(tree);
	    
	    add(BorderLayout.CENTER, scrollpane);

	  }

	  /** Add nodes from under "dir" into curTop. Highly recursive. */
	  DefaultMutableTreeNode addNodes(DefaultMutableTreeNode curTop, File dir) {
	    String curPath = dir.getPath();
	    DefaultMutableTreeNode curDir = new DefaultMutableTreeNode(dir.getName());
	    if (curTop != null) { // should only be null at root
	      curTop.add(curDir);
	    }
	    Vector ol = new Vector();
	    String[] tmp = dir.list();
	    for (int i = 0; i < tmp.length; i++)
	      ol.addElement(tmp[i]);
	    Collections.sort(ol, String.CASE_INSENSITIVE_ORDER);
	    File f;
	    Vector files = new Vector();
	    // Make two passes, one for Dirs and one for Files. This is #1.
	    for (int i = 0; i < ol.size(); i++) {
	      String thisObject = (String) ol.elementAt(i);
	      String newPath;
	      if (curPath.equals("."))
	        newPath = thisObject;
	      else
	        newPath = curPath + File.separator + thisObject;
	      if ((f = new File(newPath)).isDirectory())
	        addNodes(curDir, f);
	      else
	        files.addElement(thisObject);
	    }
	    // Pass two: for files.
	    for (int fnum = 0; fnum < files.size(); fnum++)
	      curDir.add(new DefaultMutableTreeNode(files.elementAt(fnum)));
	    return curDir;
	  }

	  public Dimension getMinimumSize() {
	    return new Dimension(200, 400);
	  }

	  public Dimension getPreferredSize() {
	    return new Dimension(200, 400);
	  }
	  
	 /**
	  *  
	  * @param objs
	  * @return String of the path 
	  */
		public String getPath(Object[] objs) {
			StringBuffer buffer = new StringBuffer(); 
			for (int i = 1; i < objs.length; i++) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) objs[i];
				buffer.append('/');
				buffer.append(node.getUserObject());
			}
			return buffer.toString();
		}

		
		public int requestAccess(String Uname,  File node){

			int value = 0;
			
			try {
				dataOut.writeUTF("Open."+node.getName()+":"+Uname + node.getName());
				dataOut.flush();
				
				int readinData = dataIn.readInt();
				
				switch(readinData){
				
					case 0:
					case 1: 				
						JOptionPane.showMessageDialog(null, Uname+"! You do not have access to: "+node.getName());
						value = 0;
						break;
				
					case 2:
						JOptionPane.showMessageDialog(null, Uname+"! You have access to delete only: "+node.getName());
						value = 2;
						break;
						
					case 3:
						JOptionPane.showMessageDialog(null, Uname+"! You have access to write only: "+node.getName());
						value = 3;
						break;
						
					case 4:
						JOptionPane.showMessageDialog(null, Uname+"! You have access to write and delete only: "+node.getName());
						value = 4;
						break;
						
					case 5:
						JOptionPane.showMessageDialog(null, Uname+"! You have access to read only: "+node.getName());
						value = 5;
						break;
						
					case 6:
						JOptionPane.showMessageDialog(null, Uname+"! You have access to read and delete only: "+node.getName());
						value = 6;
						break;
						
					case 7:
						JOptionPane.showMessageDialog(null, Uname+"! You have access to read and write only: "+node.getName());
						value = 7;
						break;
						
					case 8:
						JOptionPane.showMessageDialog(null, Uname+"! You have access to read, write and delete only: "+node.getName());
						value = 8;
						break;
						
					case 9:
						JOptionPane.showMessageDialog(null, node.getName()+"! Is being used.");
						value = 9;
						break;					
						
					case 10:
						break;

				}//end switch
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

							
		  return value;
		}
		
		

	}//end class Recursive
	
	
   
} // end class OpenFiles
