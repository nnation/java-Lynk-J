package FileManager;


/**
 * Class Person
 * Abstract Class. the person class holds all the data of the users of the system
 */
abstract public class Person {

  //
  // Fields
  //

  /**
   * firstname of user
   */
  private String firstName;
  /**
   * lastname of the user
   */
  private String lastName;
  /**
   * name required to access the system
   */
  private String userName;
  /**
   * password required to access the system
   */
  private char[] password;
  /**
   * acctype required to access the system
   */  
  private String AccType;
  
  //
  // Constructors
  //
  public Person () { };


  public Person (String fN, String lN, String uN, char[] pass, String val) { 
  
	firstName = fN;
	lastName = lN;
	userName = uN;
	password = pass;
	AccType = val;

  };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of firstName
   * firstname of user
   * @param newVar the new value of firstName
   */
  public void setFirstName ( String newVar ) {
    firstName = newVar;
  }

  /**
   * Get the value of firstName
   * firstname of user
   * @return the value of firstName
   */
  public String getFirstName ( ) {
    return firstName;
  }

  /**
   * Set the value of lastName
   * lastname of the user
   * @param newVar the new value of lastName
   */
  public void setLastName ( String newVar ) {
    lastName = newVar;
  }

  /**
   * Get the value of lastName
   * lastname of the user
   * @return the value of lastName
   */
  public String getLastName ( ) {
    return lastName;
  }

  /**
   * Set the value of userName
   * name required to access the system
   * @param newVar the new value of userName
   */
  public void setUserName ( String newVar ) {
    userName = newVar;
  }

  /**
   * Get the value of userName
   * name required to access the system
   * @return the value of userName
   */
  public String getUserName ( ) {
    return userName;
  }

  /**
   * Set the value of password
   * password required to access the system
   * @param newVar the new value of password
   */
  public void setPassword ( char[] newVar ) {
    password = newVar;
  }

  /**
   * Get the value of password
   * password required to access the system
   * @return the value of password
   */
  public char[] getPassword ( ) {
    return password;
  }
  
  /**
   * Set the value of Account type
   * Account type for the user/admin required to access the system
   * @param newVar the new value of AccType
   */
  public void setAccType ( String newVar ) {
    AccType = newVar;
  }

  /**
   * Get the value of Account type
   * Account type for the user/admin required to access the system
   * @return the value of AccType
   */
  public String getAccType ( ) {
    return AccType;
  }

  //
  // Other methods
  //


}
