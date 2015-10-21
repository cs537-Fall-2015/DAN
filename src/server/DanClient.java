package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import json.Constants;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import generic.RoverClientRunnable;

public class DanClient extends RoverClientRunnable{

	public DanClient(int port, InetAddress host) throws UnknownHostException {
		super(port, host);
	}

	@Override
	public void run() {
		try
		{
			ObjectOutputStream outputToAnotherObject = null;
		    ObjectInputStream inputFromAnotherObject = null;
		    Thread.sleep(5000);
		    
		    // Write message to the server
		    outputToAnotherObject = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
		    // Read message from the server
		    inputFromAnotherObject = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
	            
		    String [] commands = DanClass.getCommands();
		    
		    for(int i = 0; i <commands.length; i++) {
		    	if (commands[i].toUpperCase().contains("OFF"))
		    		Thread.sleep(5000);
		    	outputToAnotherObject.writeObject(commands[i]);
		    	String message = (String) inputFromAnotherObject.readObject();
		    	System.out.println(" ");
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		    	System.out.println("Client : Message from Server - " + message);
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				System.out.println(" ");
		    }
	            	/*System.out.println("Client: Sending request to Socket Server");
	 	            
	            	
	            	
	            	outputToAnotherObject.writeObject("HYDROGEN CONTENT??");
	            	
	            	 //read the server response message
		            
		            
		            
		            String message = (String) inputFromAnotherObject.readObject();
		            System.out.println("Client: Message from Server - " + message.toUpperCase()); //Hello Client
	    	        
		            String message1 = (String) inputFromAnotherObject.readObject();
		            System.out.println("Client: Message1 from Server - " + message1.toUpperCase()); //calculating speed of neutrons
					
		            String message2 = (String) inputFromAnotherObject.readObject();
		            System.out.println("Client: Message2 from Server - " + message2.toUpperCase()); //% of Hydrogen

	            	
	        //    }
	          
	            
	            //close resources
	             */
	           	inputFromAnotherObject.close();
	           	outputToAnotherObject.close();
	           	Thread.sleep(5000);
	 
	        closeAll();
		}
        
		catch (UnknownHostException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		catch (Exception error) 
		{
			System.out.println("Client: Error:" + error.getMessage());
			error.printStackTrace();
		}
		
	}

}

