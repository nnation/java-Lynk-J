import java.net.Socket;
import java.net.ServerSocket;
import java.awt.Desktop;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Formatter;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.*;






public class Server extends Thread implements Runnable 
{
	
	private ServerSocket serverSocket;
	
	
	public Server(int port)
	{
	
					
			
		
		  ServerFrame tabbedPaneFrame = new ServerFrame(); 
	      tabbedPaneFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); 
	      tabbedPaneFrame.setSize( 400, 400 ); // set frame size 
	      tabbedPaneFrame.setVisible( true ); // display frame 
	 
			try 
			{
				serverSocket=new ServerSocket(port,10);
			} 
			
			catch (IOException e) 
			{
				
				e.printStackTrace();
			}//end catch
			
		}
	
	
	
	public void run()
	{
		while(true)
		{
			
				try
				{
					//System.out.println("Waiting for connections on port" + serverSocket.getLocalPort() );
					//TODO JOptionPane.showMessageDialog(null, "Waiting for Connections On " + serverSocket.getLocalPort());
					
					Socket c = serverSocket.accept();
						
					ClientHandler handler=new ClientHandler(c);
					
					handler.start();
					
				}//end try 
				
				
				catch(IOException e)
				{
					
					e.printStackTrace();
					
				}//end catch
				
				
			}// end while
			
		}// end run	
		
	
	
	}



