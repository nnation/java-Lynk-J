package ServerDB;


/**
 * Class Resource
 * files stored on the system
 */
public class Resource {

  //
  // Fields
  //

  /**
   * name of the file
   */
  private String FileName;
  /**
   * extention of the file whether .txt etc.
   */
  private String FileExtensions;
  /**
   * file to which the file is stored
   */
  private String StoragePath;
  
  //
  // Constructors
  //
  public Resource () { };


 public Resource (String FilN, String StorPath, String FilEx) { 

	FileName = FilN;
	FileExtensions = FilEx;
	StoragePath = StorPath;
};  
  
public Resource (String FilN, String FilEx) { 

		FileName = FilN;
		FileExtensions = FilEx;
		StoragePath = null;
};  
  

  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of FileName
   * name of the file
   * @param newVar the new value of FileName
   */
  public void setFileName ( String newVar ) {
    FileName = newVar;
  }

  /**
   * Get the value of FileName
   * name of the file
   * @return the value of FileName
   */
  public String getFileName ( ) {
    return FileName;
  }

  /**
   * Set the value of FileExtensions
   * extention of the file whether .txt etc.
   * @param newVar the new value of FileExtensions
   */
  public void setFileExtensions ( String newVar ) {
    FileExtensions = newVar;
  }

  /**
   * Get the value of FileExtensions
   * extention of the file whether .txt etc.
   * @return the value of FileExtensions
   */
  public String getFileExtensions ( ) {
    return FileExtensions;
  }

  /**
   * Set the value of StoragePath
   * file to which the file is stored
   * @param newVar the new value of StoragePath
   */
  public void setStoragePath ( String newVar ) {
    StoragePath = newVar;
  }

  /**
   * Get the value of StoragePath
   * file to which the file is stored
   * @return the value of StoragePath
   */
  public String getStoragePath ( ) {
    return StoragePath;
  }


  //
  // Other methods
  //
  

}
