package Client;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class Client{
	
	
	private String userName;
	private String Pass;
	private DataOutputStream dataOut = null;
	private DataInputStream dataIn = null;
	private Socket clientSocket;
	private int port=3000;
	
	public  Client(String uName, String uPass)
	{
		userName=uName;
		Pass=uPass;		
	}// end class Client
	
	public int verifyClient()
	{
		int flag = 0;
		
		try
		{
			
			//TODO to be placed in the server user sign in
			//System.out.println("Connecting to Server on Port:" + port);
			
			clientSocket = new Socket("127.0.0.1", port);
			
			if (clientSocket.getPort() == 3000){
				
				flag = 1;
				
				//TODO to be placed in the server user sign in
				//System.out.println(userName+" Connected to Server on" + clientSocket.getRemoteSocketAddress());
			
				OutputStream  outStream = clientSocket.getOutputStream();
				dataOut = new DataOutputStream(outStream);

				InputStream in = clientSocket.getInputStream();
				dataIn = new DataInputStream(in);
				
				
				dataOut.writeUTF(userName + Pass);
				dataOut.flush();
								
				
				int readinData = dataIn.readInt();
				
				
				if (readinData == 1) {
					
					OpenFiles c1 = new OpenFiles(userName, dataOut, dataIn);
				}
				else {
					flag = 0;
				}//end else

				//clientSocket.close();
			}// end if 
			
		}//end try
		
		catch(IOException e)
		{	
			//TODO correct in the end
			flag = 0;	
		}//end catch

		return flag;
	}//end method verifyClient
	
	
	public class SendOCD implements Serializable {
		
		protected String clientMessages;
		protected File file1;
		protected int Int;
		
		public SendOCD(){	}

		public SendOCD(String m, File f){
			clientMessages = m;
			file1 = f;
		}//end constructor
	}//end class


}//end class Client	
			





