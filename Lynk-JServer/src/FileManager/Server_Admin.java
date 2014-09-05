package FileManager;


import ServerDB.Audit;
import ServerDB.Resource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;




/**
 * Class Server_Admin
 * administration class
 */
public class Server_Admin extends Person {

  //
  // Fields
  //

  /**
   * defines the privilege a user has when accessing a file or folder
   */
  private ServerDB.AccessControlList AccRight;
  private Connection conn;
  private Statement stmt, stmt1;
  private ResultSet rs, rs1;
  private JTable table;
  
  private String uName = null, FileName = null, Read = null, Write = null, Delete = null;
  private String FName = null, LName = null, AccType = null, PWord = null;
  private String operationDate;
  //
  // Constructors
  //
  public Server_Admin () { };


  public Server_Admin (String fN, String lN, String uN, char[] pass, String val) { 
	super(fN, lN, uN, pass, val);
	
  };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of AccRight
   * defines the privillage a user has when accessing a file or folder
   * @param newVar the new value of AccRight
   */
  public void setAccRight ( ServerDB.AccessControlList newVar ) {
    AccRight = newVar;
  }

  /**
   * Get the value of AccRight
   * defines the privillage a user has when accessing a file or folder
   * @return the value of AccRight
   */
  public ServerDB.AccessControlList getAccRight ( ) {
    return AccRight;
  }

  //
  // Other methods
  //

  /**
   * view files stored in the database
   */
  public void viewStoreFiles(  )
  {
  }


  /**
   * update or change the level of access a user has
   */
  public void UpdateUserAccess(  )
  {
	  
	  try {
		  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		
		  //connect to database
		  conn = DriverManager.getConnection("jdbc:odbc:Lynk-J DSN" , "Admin", "zxcvbnm");
		  
		  //create Statement
		  stmt = conn.createStatement();
			
		  rs = stmt.executeQuery("SELECT * FROM Person");   
		 
		 		  
		  String pass = new String(super.getPassword());
		  String sql = String.format("INSERT INTO Person(UserName, LastName, FirstName, AccountType, Password) " +
		  		"Values('%s', '%s', '%s', '%s', '%s')", super.getUserName(), super.getLastName(), super.getFirstName(), 
		  		super.getAccType(), pass);
		  
		  stmt.executeUpdate(sql);
		  JOptionPane.showMessageDialog(null , "Account has been created!");

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
	}

	finally {
		try {
			
			stmt.close();
			conn.close();
		} 
		catch (SQLException sqlE) {
			// Auto-generated catch block
		  JOptionPane.showMessageDialog(null, sqlE.getMessage(), "Error Closing Database", 
				  JOptionPane.ERROR_MESSAGE);

		}//end catch  
	}// end finally
  }//end method CreateAcct


  /**
   * create new accounts for the system
   * 
   */
  public void CreateAcct(  )
  {	  
	 	  
	try {
		  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		
		  //connect to database
		  conn = DriverManager.getConnection("jdbc:odbc:Lynk-J DSN" , "Admin", "zxcvbnm");
		  
		  //create Statement
		  stmt = conn.createStatement();
			
		  rs = stmt.executeQuery("SELECT * FROM Person");   
		 
		  while (rs.next()) {
			  
			  if (rs.getString("UserName").compareToIgnoreCase(super.getUserName()) == 0 ){
				  JOptionPane.showMessageDialog(null, super.getUserName() + " already exist");
				  return;
			  }
		  }
		  
		  
		  String pass = new String(super.getPassword());
		 
		  
		  String sql = String.format("INSERT INTO Person(UserName, LastName, FirstName, AccountType, Password) " +
		  		"Values('%s', '%s', '%s', '%s', '%s')", super.getUserName(), super.getLastName(), super.getFirstName(), 
		  		super.getAccType(), pass);
		  
		  stmt.executeUpdate(sql);
		  JOptionPane.showMessageDialog(null , "Account has been created!");

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
	}

	finally {
		try {
			
			stmt.close();
			conn.close();
		} 
		catch (SQLException sqlE) {
			// Auto-generated catch block
		  JOptionPane.showMessageDialog(null, sqlE.getMessage(), "Error Closing Database", 
				  JOptionPane.ERROR_MESSAGE);

		}//end catch  
	}// end finally
  }//end method CreateAcct


  /**
   * Delete user accounts from the system
   */
  public void DeleteAcct( String DeleteUser )
  {
	  
		try {
			  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			
			  //connect to database
			  conn = DriverManager.getConnection("jdbc:odbc:Lynk-J DSN", "Admin", "zxcvbnm");
			  
			  //create Statement
			  stmt = conn.createStatement();
				
			  String sql = String.format("DELETE FROM Person WHERE UserName = '%s'", DeleteUser);
			  
			 if (stmt.executeUpdate(sql) == 0) {
				  JOptionPane.showMessageDialog(null, "The User was not found!");
			 }
			 else {
				 JOptionPane.showMessageDialog(null , DeleteUser + " has been deleted!");
			 }
			  

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
		}

		finally {
			try {
				stmt.close();
				conn.close();
			} 
			catch (SQLException sqlE) {
				// Auto-generated catch block
			  JOptionPane.showMessageDialog(null, sqlE.getMessage(), "Error Closing Database", 
					  JOptionPane.ERROR_MESSAGE);

			}//end catch  
		}// end finally

  }//end method DeleteAcct

  /**
   * create files or folders
   */
  public void CreateFiles( Resource File_F )
  {
		try {
			  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			
			  //connect to database
			  conn = DriverManager.getConnection("jdbc:odbc:Lynk-J DSN", "Admin", "zxcvbnm");
			  
			  //create Statement
			  stmt = conn.createStatement();
				
			  String sql = String.format("INSERT INTO Resource(FileName, StoragePath, FileExtension, [Lock]) " +
			  		"VALUES('%s', '%s', '%s', '%d')", File_F.getFileName(), File_F.getStoragePath(), File_F.getFileExtensions(), 0);
			  
			 if (stmt.executeUpdate(sql) == 0) {
				  JOptionPane.showMessageDialog(null, "The File/Folder was not created!");
			 }
			 else {
				 JOptionPane.showMessageDialog(null , File_F.getFileName() + " has been Created!");
			 }
			  

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
		}

		finally {
			try {
				stmt.close();
				conn.close();
			} 
			catch (SQLException sqlE) {
				// Auto-generated catch block
			  JOptionPane.showMessageDialog(null, sqlE.getMessage(), "Error Closing Database", 
					  JOptionPane.ERROR_MESSAGE);

			}//end catch  
		}// end finally	  
  }

  public int VerifyUserAccess(String uName)
  {
  	
  	  int value=0;
  	  try {
  		  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
  		
  	  //connect to database
  		  conn = DriverManager.getConnection("jdbc:odbc:Lynk-J DSN","Admin","zxcvbnm");
  		  
  		  //create Statement
  		  stmt = conn.createStatement();
  		  
  		  rs = stmt.executeQuery("SELECT * FROM Person"); 
  		  
  		  String uName2, pass, U_P;
  		  	
  		  while (rs.next()) 
  		  {
  			
  			 uName2 = rs.getString("UserName");  
  			 pass = rs.getString("Password");
  			 U_P = uName2 + pass;
  			 
  			  //TODO add to the if " && path.equalsIgnoreCase(Pass)"
  			  if(U_P.equalsIgnoreCase(uName))
  			  {
  				  value = 1;
  			  }
  			  
  		  }	  
  			  
  	  }		  
  			  	  
  	  catch (SQLException sqlException) {
  			//Auto-generated catch block
  		  JOptionPane.showMessageDialog(null, sqlException.getMessage(), "Database error", 
  				  JOptionPane.ERROR_MESSAGE);

  	}//end catch  
  	catch (ClassNotFoundException e) {
  		//Auto-generated catch block
  		JOptionPane.showMessageDialog(null, e.getMessage(), "No Class for Driver",
  				JOptionPane.ERROR_MESSAGE);
  	}

  	finally {
  		try {
  			
  			stmt.close();
  			//stmt1.close();
  			conn.close();
  			
  		} 
  		catch (SQLException sqlE) {
  			// Auto-generated catch block
  		  JOptionPane.showMessageDialog(null, sqlE.getMessage(), "Error Closing Database", 
  				  JOptionPane.ERROR_MESSAGE);

  		}//end catch  
  	}// end finally
  	return value;
  	
  }//end method 
  
  
  public JTable viewAclInfo()
  {
  	
  	  //create column names
  	  String[] columnNames={"UserName", "FileName", "Read", "Write","Delete"};
  	
  	  try 
  	  {
  		  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
  		
  	     //connect to database
  		  conn = DriverManager.getConnection("jdbc:odbc:Lynk-J DSN","Admin","zxcvbnm");
  		  
  		  //create Statement
  		  stmt = conn.createStatement();
  		  
  		  rs = stmt.executeQuery("SELECT * FROM AccessControlList"); 
  		  
  		  int c=0;
  	  
  		  String[][] data= new String[100][100];
  		 
  		  while (rs.next()) 
  		  {
  			  			       
  					  uName=rs.getString("UserName");
  					  FileName=rs.getString("FileName");
  					  Read=rs.getString("Read");
  					  Write=rs.getString("Write");
  					  Delete=rs.getString("Delete");
  		  			  
  				  int j=0;
  				  while(j<=4)
  				{
  					data[c][j]=uName;
  					j++;
  					data[c][j]=FileName;
  					j++;
  					data[c][j]=Read;
  					j++;
  					data[c][j]=Write;
  					j++;
  					data[c][j]=Delete;
  					j++;
  				}//end while
  			
  			c++;
  			
  		}//end while
  		  
  		table=new JTable(data,columnNames);
  		  
  	  }
  		   		
  	  catch (SQLException sqlException)
  	  {
  			//Auto-generated catch block
  		  JOptionPane.showMessageDialog(null, sqlException.getMessage(), "Database error", 
  				  JOptionPane.ERROR_MESSAGE);

  	}//end catch  
  	catch (ClassNotFoundException e)
  	{
  		JOptionPane.showMessageDialog(null, e.getMessage(), "No Class for Driver",
  				JOptionPane.ERROR_MESSAGE);
  	}

  	finally 
  	{
  		try 
  		{
  			stmt.close();
  			conn.close();	
  		}//end try 
  		catch (SQLException sqlE)
  		{
  			// Auto-generated catch block
  		  JOptionPane.showMessageDialog(null, sqlE.getMessage(), "Error Closing Database", 
  				  JOptionPane.ERROR_MESSAGE);
  		}//end catch  
  	}// end finally
  	return table;
  	
  }//end method 


  public JTable veiwUserInfo()
  {
  	
  	  //create column names
  	  String[] columnNames={"UserName", "LastName", "FirstName", "AccountType","Password"};
  	
  	  try 
  	  {
  		  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
  		
  	     //connect to database
  		  conn = DriverManager.getConnection("jdbc:odbc:Lynk-J DSN","Admin","zxcvbnm");
  		  
  		  //create Statement
  		  stmt = conn.createStatement();
  		  
  		  rs = stmt.executeQuery("SELECT * FROM Person"); 
  		  
  		  int c=0;
  	  
  		  String[][] data= new String[100][100];
  		 
  		  while (rs.next()) 
  		  {
  			  			       
  					  uName = rs.getString("UserName");
  					  FName = rs.getString("FirstName");
  					  LName = rs.getString("LastName");
  					  AccType = rs.getString("AccountType");
  					  PWord = rs.getString("Password");
  		  			  
  				  int j=0;
  				  while(j<=4)
  				{
  					data[c][j]=uName;
  					j++;
  					data[c][j]=FName;
  					j++;
  					data[c][j]=LName;
  					j++;
  					data[c][j]=AccType;
  					j++;
  					data[c][j]=PWord;
  					j++;
  				}
  			
  			c++;
  			
  			 }
  		  
  		table=new JTable(data,columnNames);
  		  
  	  }
  		 
  	  
  	 
  		
  	  catch (SQLException sqlException)
  	  {
  			//Auto-generated catch block
  		  JOptionPane.showMessageDialog(null, sqlException.getMessage(), "Database error", 
  				  JOptionPane.ERROR_MESSAGE);

  	}//end catch  
  	catch (ClassNotFoundException e)
  	{
  		JOptionPane.showMessageDialog(null, e.getMessage(), "No Class for Driver",
  				JOptionPane.ERROR_MESSAGE);
  	}//end catch

  	finally 
  	{
  		try 
  		{
  			
  			stmt.close();
  			conn.close();
  		}//end try
  		
  		catch (SQLException sqlE)
  		{
  			// Auto-generated catch block
  		  JOptionPane.showMessageDialog(null, sqlE.getMessage(), "Error Closing Database", 
  				  JOptionPane.ERROR_MESSAGE);

  		}//end catch  
  	}// end finally
  	return table;
  	
  }//end method 
  
  public JTable viewLogTable()
  {
  	
  	  //create column names
  	  String[] columnNames={"UName", "FName", "Read", "Write","Delete","Date"};
  	
  	  try 
  	  {
  		  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
  		
  	     //connect to database
  		  conn = DriverManager.getConnection("jdbc:odbc:Lynk-J DSN","Admin","zxcvbnm");
  		  
  		  //create Statement
  		  stmt = conn.createStatement();
  		  
  		  rs = stmt.executeQuery("SELECT * FROM Audit"); 
  		  
  		  int c=0;
  	  
  		  String[][] data= new String[100][100];
  		 
  		  while (rs.next()) 
  		  {
  			  			       
  					  uName=rs.getString("UserName");
  					  FileName=rs.getString("FileName");
  					  Read=rs.getString("OperationRead");
  					  Write=rs.getString("OperationWrite");
  					  Delete=rs.getString("OperationDelete");
  		  			  operationDate=rs.getString("DateType");
  				  
  		  		  int j=0;
  				 
  		  		  while(j<=4)
  				  {
  					data[c][j]=uName;
  					j++;
  					data[c][j]=FileName;
  					j++;
  					data[c][j]=Read;
  					j++;
  					data[c][j]=Write;
  					j++;
  					data[c][j]=Delete;
  					j++;
  					data[c][j]=operationDate;
  				}//end while
  
  			c++;
  			
  		}//end while
  		  
  		table=new JTable(data,columnNames);
  		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  
  	  }
  		   		
  	  catch (SQLException sqlException)
  	  {
  			//Auto-generated catch block
  		  JOptionPane.showMessageDialog(null, sqlException.getMessage(), "Database error", 
  				  JOptionPane.ERROR_MESSAGE);

  	}//end catch  
  	catch (ClassNotFoundException e)
  	{
  		JOptionPane.showMessageDialog(null, e.getMessage(), "No Class for Driver",
  				JOptionPane.ERROR_MESSAGE);
  	}

  	finally 
  	{
  		try 
  		{
  			stmt.close();
  			conn.close();	
  		}//end try 
  		catch (SQLException sqlE)
  		{
  			// Auto-generated catch block
  		  JOptionPane.showMessageDialog(null, sqlE.getMessage(), "Error Closing Database", 
  				  JOptionPane.ERROR_MESSAGE);
  		}//end catch  
  	}// end finally
  	return table;
  	
  }//end method 

  
  
  
  

  /**
   * search the database for existing file and folders for specific operations
   */
  public int VerifyAccessToFile(String UName_File)
  {
	  	
  	  int value = 0;
  	  
  	  try {
  		  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
  		
  	  //connect to database
  		  conn = DriverManager.getConnection("jdbc:odbc:Lynk-J DSN","Admin","zxcvbnm");
  		  
  		  //create Statement
  		  stmt = conn.createStatement();
 
  		  rs = stmt.executeQuery("SELECT * FROM AccessControlList"); 
  		  
  		  String uName2 = null, fileName, U_F, holdFileName = new String();
  		  int read, write, delete;  		  
  		    		
  		  //get access and level type to a file
  		  while (rs.next()) 
  		  {
  			 uName2 = rs.getString("UserName");  
  			 fileName = rs.getString("FileName");
			 read = rs.getInt("Read");
			 write = rs.getInt("Write");
			 delete = rs.getInt("Delete");
  			 
  			 U_F = uName2 + fileName;
  			 
  			  //User can not access 
  			  if(U_F.equals(UName_File) && read == 0 && write == 0 && delete == 0)
  			  {
  				  holdFileName = fileName;
  				  value = 1;
  			  }//end if

  			//User can delete only
  			  else if(U_F.equals(UName_File) && read == 0 && write == 0 && delete == 1)
  			  {
  				holdFileName = fileName;
  				  value = 2;
  			  }//end if
  			    			  
  			  	  
  			  
  			  
  			//User can write only
  			  else if(U_F.equals(UName_File) && read == 0 && write == 1 && delete == 0)
  			  {
  				holdFileName = fileName;
  				  value = 3;
  			  }//end if
  			  
  			//User can write and delete only
  			  else if(U_F.equals(UName_File) && read == 0 && write == 1 && delete == 1)
  			  {
  				holdFileName = fileName;
  				  value = 4;
  			  }//end if

  			//User can read only
  			  else if(U_F.equals(UName_File) && read == 1 && write == 0 && delete == 0)
  			  {
  				holdFileName = fileName;
  				  value = 5;
  			  }//end if

  			 //User can read and delete only
  			  else if(U_F.equals(UName_File) && read == 1 && write == 0 && delete == 1)
  			  {
  				holdFileName = fileName;
  				  value = 6;
  			  }//end if

  			  //User can read and write only
  			  else if(U_F.equals(UName_File) && read == 1 && write == 1 && delete == 0)
  			  {
  				holdFileName = fileName;
  				  value = 7;
  			  }//end if
  			  	
  			  //User can read, write and delete only
  			  else if(U_F.equals(UName_File) && read == 1 && write == 1 && delete == 1)
  			  {
  				holdFileName = fileName;
  				  value = 8;
  			  }//end if
  			  
  		  }//end while	  
  		  
  		  if(value!=0)
  		  {
  		  Audit auditLog=new Audit(uName2,holdFileName,value);
  		  auditLog.writeLogToDataBase();
  		  
  		  }
  		//Check if the file is lock in the database
  		  if (checkLock(holdFileName) == true){
  			  value = 9;
  		  }
  			  
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
  			
  			stmt.close();
  			conn.close();
  			
  		}//end try
  		catch (SQLException sqlE) {
  			// Auto-generated catch block
  		  JOptionPane.showMessageDialog(null, sqlE.getMessage(), "Error Closing Database", 
  				  JOptionPane.ERROR_MESSAGE);

  		}//end catch  
  	}// end finally
  	
  	return value;
  	
  }//end method 
  
  
  //Check if the file is lock in the database
  public boolean checkLock(String holdFileName){
	  
  	  boolean value = false;
  	  
  	  try {
  		  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
  		
  	  //connect to database
  		  conn = DriverManager.getConnection("jdbc:odbc:Lynk-J DSN","Admin","zxcvbnm");
  		  
  		  //create Statement
  		  stmt1 = conn.createStatement(); 
  		  rs1 = stmt1.executeQuery("SELECT * FROM Resource");  
  		  
  		  String fileName;
  		  int lock;
  		  
  		  while (rs1.next()){
  			fileName = rs1.getString("FileName");
  			lock = rs1.getInt("Lock");
  			
  			if (holdFileName.equals(fileName) && lock == 1){
  				value = true;
  			}
  		  }
  		  
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
  			
  			stmt1.close();
  			conn.close();
  			
  		  }//end try
  		  catch (SQLException sqlE) {
  			// Auto-generated catch block
  			  JOptionPane.showMessageDialog(null, sqlE.getMessage(), "Error Closing Database", 
  				  JOptionPane.ERROR_MESSAGE);

  		  }//end catch  
  	  }// end finally
  	
  	  return value;
  		  
  }

  public void OpenOrCloseOrDelete(String oper, String fileN){
	  
	  int value = 0;
	  String sql = new String();
	  
	  String operation = oper;
	  String fileName = fileN;
		
	  try {
			  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			
			  //connect to database
			  conn = DriverManager.getConnection("jdbc:odbc:Lynk-J DSN", "Admin", "zxcvbnm");
			  
			  //create Statement
			  stmt1 = conn.createStatement();
				
			  if (operation.equals("Open")){
				  
				  value = 1;
				  sql = String.format("UPDATE Resource SET [Lock] = '%d'" +
					  		" WHERE FileName = '%s'",value, fileName);			  		
				 
				  stmt1.executeUpdate(sql);
			  }

			  if (operation.equals("Close")){
				  
				  value = 0;
				  sql = String.format("UPDATE Resource SET [Lock] = '%d'" +
					  		" WHERE FileName = '%s'", value, fileName);			  		
				 
				  stmt1.executeUpdate(sql);
			  }
			  
			  if (operation.equals("Delete")){
				  
				  value = 0;
				  sql = String.format("DELETE from Resource WHERE FileName = '%s'", fileName);			  		
				 
				  stmt1.executeUpdate(sql);
			  }			  
			  

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
		}

		finally {
			try {
				stmt1.close();
				conn.close();
			} 
			catch (SQLException sqlE) {
				// Auto-generated catch block
			  JOptionPane.showMessageDialog(null, sqlE.getMessage(), "Error Closing Database", 
					  JOptionPane.ERROR_MESSAGE);

			}//end catch  
		}// end finally	  

  }
  
  
  
}//Server_Admin Class



