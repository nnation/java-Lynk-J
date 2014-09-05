package ServerDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;


/**
 * Class AccessControlList
 * Stores particulars and access type og the user
 */
public class AccessControlList extends Resource {

  //
  // Fields
  //

  /**
   * the level of access a user has
   */
  private int read, write, delete;
  /**
   * name used to access the system
   */
  private String UName;
  
  private Connection conn1, conn2, conn3;
  private Statement stmt1, stmt2, stmt3;
  private ResultSet rs1, rs2;

  //
  // Constructors
  //
  public AccessControlList () { };


  public AccessControlList (String UN, String fileName, String fileExtension, int read, int write, int delete) { 
	
	super(fileName, fileExtension);
	this.read = read;
	this.write = write;
	this.delete = delete;
	
	UName = UN;
	
	
  };  

  //
  // Methods
  //


  //
  // Accessor methods
  //

    

  /**
   * Set the value of AccessLevel
   * the level of access a user has
   * @param newVar the new value of AccessLevel
   */
  public void setAccessLevel ( int read, int write, int delete ) {
	    this.read = read;
		this.write = write;
		this.delete = delete;
  }

  /**
   * Get the value of AccessLevel
   * the level of access a user has
   * @return the value of AccessLevel
   */
  public int getReadValue ( ) {
    return read;
  }
  
  public int getWriteValue ( ) {
	    return write;
	  }

  
  public int getDeleteValue ( ) {
	    return delete;
	  }
  
  
  /**
   * Set the value of UName
   * name used to access the system
   * @param newVar the new value of UName
   */
  public void setUName ( String newVar ) {
    UName = newVar;
  }

  /**
   * Get the value of UName
   * name used to access the system
   * @return the value of UName
   */
  public String getUName ( ) {
    return UName;
  }

  //
  // Other methods
  //

  /**
   * Send information to database
   * User name, access level, storage path
   */
  public void Synchronization()
  {
		try {
			  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			
			  //connect to database
			  conn1 = DriverManager.getConnection("jdbc:odbc:Lynk-J DSN" , "Admin", "zxcvbnm");
			  
			  //create Statement
			  stmt1 = conn1.createStatement();
			 	
			  String sql; 
			  
			  if (TestUserExist() == 0) {
				  return;
			  }//end if
			  else if (TestUserExist() == 1) {
				  sql = String.format("INSERT INTO AccessControlList ( UserName, FileName, [Read], [Write], [Delete] )" +
						  "VALUES ('%s', '%s', %d, %d, %d);", UName, super.getFileName(),read,write,delete);	
			  
			  }// end else if
			  else {
				  sql = String.format("UPDATE AccessControlList SET [Read] = %d, [Write] = %d, [Delete] = %d" +
				  		" WHERE UserName = '%s' AND FileName = '%s'", read,write,delete, UName, super.getFileName());			  		
			  }
			 
			  if (stmt1.executeUpdate(sql) >= 0) {
				 JOptionPane.showMessageDialog(null , UName+" "+"access has been changed for File/Folder "+super.getFileName()+"!");
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
				conn1.close();
			}//end try 
			catch (SQLException sqlE) {
				// Auto-generated catch block
			  JOptionPane.showMessageDialog(null, sqlE.getMessage(), "Error Closing Database", 
					  JOptionPane.ERROR_MESSAGE);

			}//end catch  
		}// end finally

  }// end method Synchronization

  
  public int TestUserExist(){
	  
	  int flag = 0;
		try {
			  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			
			  //connect to database
			  conn2 = DriverManager.getConnection("jdbc:odbc:Lynk-J DSN" , "Admin", "zxcvbnm");
			  
			  //create Statement
			  stmt2 = conn2.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			 			  
			  rs1 = stmt2.executeQuery("SELECT * FROM Person");
				 
			  String str1;
			  
			  while (rs1.next()) {
				  
				  str1 = rs1.getString("UserName");
				  
				  if (str1.equals(UName)){
					  flag = UserPrivilage();
					  
				  }//end if				  
				  
			  }//end while
			  
			  //test if flag was changed, if not data was not found
			  if (flag == 0){
				  JOptionPane.showMessageDialog(null, UName + " does not exist!");
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
		return flag;
	  
  }//end method TestUser
  
  
  public int UserPrivilage(){

		try {
			  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			
			  //connect to database
			  conn3 = DriverManager.getConnection("jdbc:odbc:Lynk-J DSN" , "Admin", "zxcvbnm");
			  
			  //create Statement
			  stmt3 = conn3.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			 			  
			  rs2 = stmt3.executeQuery("SELECT * FROM AccessControlList");
				 
			  String str1, str2;
			  int val1, val2, val3;
			  
			  while (rs2.next()) {
				  
				  str1 = rs2.getString(1);
				  str2 = rs2.getString(2);
				  val1 = rs2.getInt(3);
				  val2 = rs2.getInt(4);
				  val3 = rs2.getInt(5);
				  
				  if ( str1.equals(UName) && str2.equals(super.getFileName()) && 
						  val1 == read && val2 == write && val3 == delete){
					  
					  JOptionPane.showMessageDialog(null, UName + " already have the selected privilege to "+super.getFileName()+"!");
					  
					  return 1;
				  }//end if
				  
				  if (str1.equals(UName) == true && str2.equals(super.getFileName()) == true){
					return 2;  
				  }//end if 
				  
			  }//end while
	  

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
				stmt3.close();
				conn3.close();
			}//end try 
			catch (SQLException sqlE) {
				// Auto-generated catch block
			  JOptionPane.showMessageDialog(null, sqlE.getMessage(), "Error Closing Database", 
					  JOptionPane.ERROR_MESSAGE);

			}//end catch  
		}// end finally
		return 1;

  }//end method UserPrivilage
  
}
