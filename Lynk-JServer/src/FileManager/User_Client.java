package FileManager;
import ServerDB.Audit;


/**
 * Class User_Client
 * user_client inherits from person. user_client holds data on all client users of
 * the system
 */
public class User_Client extends Person {

  //
  // Fields
  //

  /**
   * records all the activities done on the system by the user
   */
  private ServerDB.Audit Log;
  
  //
  // Constructors
  //
  public User_Client () { };


  public User_Client (String fN, String lN, String uN, char[] pass, String val) { 
	super(fN, lN, uN, pass, val);

  };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of Log
   * records all the activities done on the system by the user
   * @param newVar the new value of Log
   */
  public void setLog ( ServerDB.Audit newVar ) {
    Log = newVar;
  }

  /**
   * Get the value of Log
   * records all the activities done on the system by the user
   * @return the value of Log
   */
  public ServerDB.Audit getLog ( ) {
    return Log;
  }

  //
  // Other methods
  //

  /**
   * allows users to read file or folder
   */
  public void ReadFile(  )
  {
  }


  /**
   * allows the user to write to a file
   */
  public void WriteFile(  )
  {
  }


}
