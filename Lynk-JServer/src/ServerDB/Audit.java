package ServerDB;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;



/**
 * Class Audit
 * records the activities done by a user on the system
 */
public class Audit {
	
	Connection conn; 
	Statement stmt; 
	ResultSet result;
	private Date date;
	private DateFormat dateFormat;
	private String logDate;
	
  //
  // Fields
  //

  /**
   * records the name of the user
   */
  private String LogUserName;
  /**
   * records the name of the file 
   */
  private String LogFileName;
  /**
   * what was done to file on the system
   */
  private String operation;
  
  private int operationValue;
 
  private  int read,write,delete;
  
  //
  // Constructors
  //
  public Audit () { };


  public Audit (String LUName, String LFileN, int value) { 

	LogUserName = LUName;
	LogFileName = LFileN;
	//TODO
	//operation = op;
	operationValue=value;
	
	if(operationValue==1)
	 		 read =  write = delete = 0;
	  
	 else if(operationValue==2)
	  {
		 read =  write = 0;
		 delete = 1;
	  }
	    			  
	  	  

	  else if(operationValue==3)
	  {
			 read =  delete = 0;
			  write=1;
	  }
	  
	
	  else if(operationValue==4)
	  {
			write =  delete = 1;
			 read=0;
	  }

	  else if(operationValue == 5)
	  {
			 write =  delete = 0;
			 read=1;
		  
	  }

	   else if(operationValue==6)
	  {
		   read =  delete = 1;
			 write=1;
		}

	  else if(operationValue==7)
	  {
		   read =  write = 1;
			 delete=0;
		  
	  }
	  	
	  
	  else if(operationValue==8)
	  {
		  read=write=delete=1;
	  }
	
		
		
  }
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of LogUserName
   * records the name of the user
   * @param newVar the new value of LogUserName
   */
  public void setLogUserName ( String newVar ) {
    LogUserName = newVar;
  }

  /**
   * Get the value of LogUserName
   * records the name of the user
   * @return the value of LogUserName
   */
  public String getLogUserName ( ) {
    return LogUserName;
  }

  /**
   * Set the value of LogFileName
   * records the name of the file
   * @param newVar the new value of LogFileName
   */
  public void setLogFileName ( String newVar ) {
    LogFileName = newVar;
  }

  /**
   * Get the value of LogFileName
   * records the name of the file
   * @return the value of LogFileName
   */
  public String getLogFileName ( ) {
    return LogFileName;
  }

  /**
   * Set the value of operation
   * what was done to file on the system
   * @param newVar the new value of operation
   */
  public void setOperation ( String newVar ) {
    operation = newVar;
  }

  /**
   * Get the value of operation
   * what was done to file on the system
   * @return the value of operation
   */
  
  
  
  
  
  public String getOperation ( ) {
    return operation;
  }

  
  public void writeLogToDataBase()
  {
  
 
		  dateFormat=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		  date= new Date();
		  logDate=dateFormat.format(date);
		  try {
			  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			
			  //connect to database
			  conn = DriverManager.getConnection("jdbc:odbc:Lynk-J DSN" , "Admin", "zxcvbnm");
			  
			  //create Statement
			  stmt = conn.createStatement();
			 
			 		  
			  String sql = String.format("INSERT INTO Audit(UserName, FileName, OperationRead, OperationWrite,OperationDelete,DateType) " +
			  		"Values('%s', '%s', '%d', '%d', '%d', '%s')", LogUserName, LogFileName, read,write,delete,logDate); 
			  		
			  
			  stmt.executeUpdate(sql);
			  
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

	  	  
	  
   
  

  }
