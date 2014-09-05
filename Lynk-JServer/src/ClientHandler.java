
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;

import javax.swing.JOptionPane;

import FileManager.Server_Admin;


public class ClientHandler extends Thread 
{
		
	public Socket S;
	private int FlagTest = 0;
	private DataOutputStream dataOut;
	private DataInputStream dataIn;

	
	public ClientHandler(Socket S)
	{
		this.S=S;	
		
	}
		
	public void run()
	{
				 
		 
		try 
		{
			while(true)
			{	    
				
				dataOut = new DataOutputStream(S.getOutputStream());
				dataIn = new DataInputStream(S.getInputStream());
				
				String clientMessages = dataIn.readUTF();
		
				//TODO ------------------------------------------------
				
				Server_Admin adminAut = new Server_Admin();
					
				if (FlagTest == 0) {
					
					if (adminAut.VerifyUserAccess(clientMessages) == 1){      
					       dataOut.writeInt(1);
					       FlagTest = 3;
					}//end internal if

					else {
					      dataOut.writeInt(0);
					      dataOut.flush();
					}// end internal else
				}//end if
					     
				else {
					int refPoint1 = clientMessages.indexOf(".");
					int refPoint2 = clientMessages.indexOf(":");
					String operation = clientMessages.substring(0,refPoint1);
					String fileName = clientMessages.substring(refPoint1 + 1, refPoint2);
					clientMessages = clientMessages.substring(refPoint2 + 1);
					  
					int lock = 0, close = 0, delete = 0;
					  
					if (operation.equals("Close") || operation.equals("Delete")){
						  						
						adminAut.OpenOrCloseOrDelete(operation, fileName);
							
						close = 1;
						delete = 1;
						dataOut.writeInt(10);
						dataOut.flush();								
					}
					
					if (operation.equals("Open") && close == 0 && delete == 0) {
						
						int task = adminAut.VerifyAccessToFile(clientMessages);
						adminAut.OpenOrCloseOrDelete(operation, fileName);
						
						switch(task){
						 
							 case 0:			
								 dataOut.writeInt(0);
								 dataOut.flush();
							     break;
							     
							 case 1:
								 dataOut.writeInt(1);
								 dataOut.flush();
								 break;
								 
							 case 2:
								 dataOut.writeInt(2);
								 dataOut.flush();
								 break;
								 
							 case 3:
								 dataOut.writeInt(3);
								 dataOut.flush();
								 break;
								 
							 case 4:
								 dataOut.writeInt(4);
								 dataOut.flush();
								 break;
								 
							 case 5:
								 dataOut.writeInt(5);
								 dataOut.flush();
								 break;
								 
							 case 6:
								 dataOut.writeInt(6);
								 dataOut.flush();
								 break;
								 
							 case 7:
								 dataOut.writeInt(7);
								 dataOut.flush();
								 break;
								 
							 case 8:
								 dataOut.writeInt(8);
								 dataOut.flush();
								 break;
	
							 case 9:
								 dataOut.writeInt(9);
								 dataOut.flush();
								 break;

						}//end switch				    				    
					}//end if
				}//end else
				
			}//end while
			
		}// end try
	
		catch (IOException e)
		{
			//TODO ---New to put in server log that user leave for the socket exception
			e.printStackTrace();
		
		}
		finally {
			try {
				dataOut.close();
				dataIn.close();
				S.close();
			}//end try
			catch(IOException ioException){
					ioException.printStackTrace();
					System.exit(1);
			}//end catch
					
		}// end finally block
		
			 
	}// end run function		 
 
	
}//end class
		 
	

		
	
	
	


