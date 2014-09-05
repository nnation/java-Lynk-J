package Client;


import java.io.*;

import javax.swing.JOptionPane;

class FileInputOutput {
	
    public void SaveFileContent(String content, String path)
    {              
            FileOutputStream out; // declare a file output object
    
            PrintStream p; // declare a print stream object

            try
            {
                    // Create a new file output stream
                    // connected to "textfile.txt"
                    out = new FileOutputStream(path);
                    
                    // Connect print stream to the output stream
                    p = new PrintStream( out );
	
                    p.println (content);

                    p.close();
            }
            catch (Exception e)
            {
                    System.err.println ("Error writing to file");
            }
    }

    
    public StringBuffer DisplayFileContent(String path)
	{
 
    	StringBuffer contents = new StringBuffer(); 
		BufferedReader reader = null; 

            try
			{
               // Open the file that is the first 
               // command line parameter			   
			   reader = new BufferedReader(new FileReader(path)); 
			   String text = null; 

               // Continue to read lines while 
               // there are still some left to read
               while ((text = reader.readLine()) != null)
			   {
                    // Print file line to screen
            	   contents.append(text).append(System.getProperty("line.separator"));
			   }
			   
			}
            
  			catch (FileNotFoundException e) 
			{
		 		 JOptionPane.showMessageDialog(null, "File Not Found!");
		 		 return null;
			}//end catch
			 
			 catch (IOException e) 
			 {
		 		 JOptionPane.showMessageDialog(null, "Error reading file!");
		 		 return null;
			 }//end catch 
			 
			 finally { 
				 try  {        
					 if (reader != null) {
						 reader.close();
					 }//end if                 
				 }//end try 
		
				 catch (IOException e) {	                
					 e.printStackTrace(); 
				 }//end catch 

			 }//end finally
			
			return contents;
	}// end method DisplayFileContent
    
}//end class