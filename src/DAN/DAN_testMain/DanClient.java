package DAN.DAN_testMain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import generic.RoverClientRunnable;

public class DanClient extends RoverClientRunnable{

	public DanClient(int port, InetAddress host) throws UnknownHostException {
		super(port, host);
	}

	public void run() {
		
		try	{
			
		    // Write message to the server
		    ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
		    // Read message from the server
		    ObjectInputStream inputFromAnotherObject = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
	        
		    try {
	            // FileReader reads text files in the default encoding.
	            FileReader fileReader = new FileReader("Commands.txt");

	            // Always wrap FileReader in BufferedReader.
	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            
	            String command = null;
	            while((command = bufferedReader.readLine()) != null){
	            	
			    	System.out.println("\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
			    	System.out.println("Client Says: SENDING COMMAND - " + command);
			    	System.out.println("\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
			    	
			    	Thread.sleep(5000);
			    	
			    	outputToAnotherObject.writeObject(command);
			    	String message = (String) inputFromAnotherObject.readObject();
			    			    	
			    	System.out.println("\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
			    	System.out.println("Client Says: MESSAGE RECEIVED - " + message);
			    	System.out.println("\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
			    	
			    	Thread.sleep(5000);
	            }
			    // Always close files.
	            bufferedReader.close();
	            
	        }catch(Exception ex) {
	        	ex.printStackTrace();
	        }
	        inputFromAnotherObject.close();
	        outputToAnotherObject.close();
	        
	        closeAll();
	        
		}catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		catch (Exception error) {
			System.out.println("Client: Error:" + error.getMessage());
			error.printStackTrace();
		}
		
	}

}
