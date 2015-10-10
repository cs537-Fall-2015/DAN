package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.json.simple.JSONObject;

import callback.CallBack;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import json.Constants;
import json.GlobalReader;
import json.MyWriter;
import generic.RoverServerRunnable;

public class DanServer extends RoverServerRunnable {
	
	public DanServer(int port) throws IOException {
		super(port);
	}
	
	@Override
	public void run() {
		
		DanClass moduleOneClass = new DanClass(1);

		System.out.println("Server: Power is ON"); 
		
		try {
			
			while (DanClass.DAN_ON == true) 
			{
				System.out.println("Server: Waiting for client request...");

				// creating socket and waiting for client connection
				getRoverServerSocket().openSocket();
				
				// read from socket to ObjectInputStream object
				ObjectInputStream inputFromAnotherObject = new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());
				
				// convert ObjectInputStream object to String
				String message = (String) inputFromAnotherObject.readObject();
				System.out.println("Server: Message Received from Client - "+ message.toUpperCase());  //Hydrogen content
				
				// create ObjectOutputStream object
				ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
				
				outputToAnotherObject.writeObject("HELLO");
				
				if (DanClass.DAN_PNG_ON == true)
				{
					DanClass.DAN_DE_ON = true;
					outputToAnotherObject.writeObject("Calculating the speed of Neutrons.... ");
					System.out.println("Server: Speed is " +moduleOneClass.getSpeed());
					double a;
					
					if( moduleOneClass.getSpeed() < 25 )
					{
						// write object to Socket
						//Generating random integers in a range
						//Min + (int)(Math.random() * ((Max - Min) + 1))
						
						a = 50 + (int)(Math.random() * (50 + 1));
						moduleOneClass.setDAN_H_CONTENT(a);
						outputToAnotherObject.writeObject("Percentage of Hydrogen found is : "  + a +  "% at location " +moduleOneClass.getrover_x()+ " " +moduleOneClass.getrover_y());
						
					}
					else if( moduleOneClass.getSpeed() >= 25 && moduleOneClass.getSpeed() < 50)
					{
						a = 25 + (int)(Math.random() * (25 + 1));
						moduleOneClass.setDAN_H_CONTENT(a);
						outputToAnotherObject.writeObject("Percentage of Hydrogen found is : "  + a +  "% at location " +moduleOneClass.getrover_x()+ " " +moduleOneClass.getrover_y());
						
					}
					else if( moduleOneClass.getSpeed() >= 50 && moduleOneClass.getSpeed() < 75)
					{
						a = 0 + (int)(Math.random() * (25 + 1));
						moduleOneClass.setDAN_H_CONTENT(a);
						outputToAnotherObject.writeObject("Percentage of Hydrogen found is : "  + a +  "% at location " +moduleOneClass.getrover_x()+ " " +moduleOneClass.getrover_y()); 
						
					}
					else 
					{
						outputToAnotherObject.writeObject("There is no Hydrogen content present");
						
					}
					
					new MyWriter(moduleOneClass, 19);
					DanClass.DAN_PNG_ON = false;
					DanClass.DAN_DE_ON = false;
					System.out.println("Job Done!!!");
					System.out.println("Server shut down");
				}
				else
				{
					System.out.println("DAN_PNG_ON is false");
					//break;
				}	
				
				DanClass.DAN_ON = false;
				
				//close resources
	           	inputFromAnotherObject.close();
	          	outputToAnotherObject.close();
				
			} 
			
			// close the ServerSocket object
			closeAll();
			
			//inform the CCDS group9 about job completion.
			//orelse we will get Connection refused exception which is mostly due to Group 9 server being down 
			//new CallBack().done();
		}
		
		catch (IOException e)
		{
			 e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (Exception error) 
		{
			System.out.println("Server: Error: " + error.getMessage());
			error.printStackTrace();
		}

	}
}







