package DAN.server;

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
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		    	System.out.println("Client : Message from Server - " + message);
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				System.out.println(" ");
		    }
	           	inputFromAnotherObject.close();
	           	outputToAnotherObject.close();
	           	Thread.sleep(5000);
	 
	        closeAll();
		}
        
		catch (UnknownHostException e) 
		{
			e.printStackTrace();
		}
		
		catch (Exception error) 
		{
			System.out.println("Client: Error:" + error.getMessage());
			error.printStackTrace();
		}
		
	}

}

