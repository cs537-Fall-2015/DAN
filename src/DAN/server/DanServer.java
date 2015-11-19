package DAN.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

import generic.RoverServerRunnable;
import json.MyWriter;

public class DANServer extends RoverServerRunnable{
	
	public DANServer(int port) throws IOException {
		super(port);
		
	}

	public void run(){
		
		DANClass dan = new DANClass();
		
		try {
			
			getRoverServerSocket().openSocket();
			
			ObjectInputStream inputFromAnotherObject = new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());
			
			ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
			
			while(true){
				
				String messageFromClient = (String) inputFromAnotherObject.readObject();
				
				System.out.println("\n--------------------------------------------------------------------------------\n");
				System.out.println("Server Says: COMMAND RECEIVED - " + messageFromClient);
				System.out.println("\n--------------------------------------------------------------------------------\n");
				
				String messageToClient = null;
				
				switch(messageFromClient){
				case "DAN_ON":
					System.out.println("\n--------------------------------------------------------------------------------\n");
					System.out.println("Server Says: DAN is powering up. Please wait...");
					System.out.println("\n--------------------------------------------------------------------------------\n");
					Thread.sleep(5000);
					dan.setDAN_ON(true);
					messageToClient = "DAN has powered up.";
					System.out.println("\n--------------------------------------------------------------------------------\n");
					System.out.println("Server Says: " + messageToClient);
					System.out.println("\n--------------------------------------------------------------------------------\n");
					Thread.sleep(2000);
					break;
					
				case "DAN_OFF":
					System.out.println("\n--------------------------------------------------------------------------------\n");
					System.out.println("Server Says: DAN is powering down.");
					System.out.println("\n--------------------------------------------------------------------------------\n");
					Thread.sleep(5000);
					dan.setDAN_ON(false);
					messageToClient = "DAN is off";
					System.out.println("\n--------------------------------------------------------------------------------\n");
					System.out.println("Server Says: " + messageToClient);
					System.out.println("\n--------------------------------------------------------------------------------\n");
					Thread.sleep(2000);
					break;
					
				case "DAN_PNG_ON":
					System.out.println("\n--------------------------------------------------------------------------------\n");
					System.out.println("Server Says: DAN Pulsed Neutron Generator is powering up. Please wait...");
					System.out.println("\n--------------------------------------------------------------------------------\n");
					Thread.sleep(5000);
					dan.setPNG_ON(true);
					messageToClient = "PNG is running";
					System.out.println("\n--------------------------------------------------------------------------------\n");
					System.out.println("Server Says: " + messageToClient);
					System.out.println("\n--------------------------------------------------------------------------------\n");
					Thread.sleep(2000);
					break;
					
				case "DAN_PNG_OFF":
					System.out.println("\n--------------------------------------------------------------------------------\n");
					System.out.println("Server Says: DAN Pulsed Neutron Generator is powering down.");
					System.out.println("\n--------------------------------------------------------------------------------\n");
					Thread.sleep(5000);
					dan.setPNG_ON(false);
					messageToClient = "PNG is off.";
					System.out.println("\n--------------------------------------------------------------------------------\n");
					System.out.println("Server Says: " + messageToClient);
					System.out.println("\n--------------------------------------------------------------------------------\n");
					Thread.sleep(2000);
					break;
					
				case "DAN_DE_ON":
					System.out.println("\n--------------------------------------------------------------------------------\n");
					System.out.println("Server Says: DAN Detector Element is powering up. Please wait...");
					System.out.println("\n--------------------------------------------------------------------------------\n");
					Thread.sleep(5000);
					dan.setDE_ON(true);
					messageToClient = "DE is running";
					System.out.println("\n--------------------------------------------------------------------------------\n");
					System.out.println("Server Says: " + messageToClient);
					System.out.println("\n--------------------------------------------------------------------------------\n");
					Thread.sleep(2000);
					break;
					
				case "DAN_DE_OFF":
					System.out.println("\n--------------------------------------------------------------------------------\n");
					System.out.println("Server Says: DAN Detector Element is powering down.");
					System.out.println("\n--------------------------------------------------------------------------------\n");
					Thread.sleep(5000);
					dan.setDE_ON(false);
					messageToClient = "DE is off.";
					System.out.println("\n--------------------------------------------------------------------------------\n");
					System.out.println("Server Says: " + messageToClient);
					System.out.println("\n--------------------------------------------------------------------------------\n");
					Thread.sleep(2000);
					break;
					
				case "DAN_NEUTRON_COUNT":
					System.out.println("\n--------------------------------------------------------------------------------\n");
					System.out.println("Server Says: Neutron count being generated.");					
					System.out.println("\n--------------------------------------------------------------------------------\n");
					//generate random count
					Random rand = new Random();
					int count = rand.nextInt(1000000000);
					dan.setNEUTRON_COUNT(count);
					Thread.sleep(5000);
					messageToClient = "Neutron count is " + dan.getNEUTRON_COUNT() + " neutrons at time " + dan.getDE_TIME();
					System.out.println("\n--------------------------------------------------------------------------------\n");
					System.out.println("Server Says: " + messageToClient);
					System.out.println("\n--------------------------------------------------------------------------------\n");
					Thread.sleep(2000);
					break;
					
				default:
					System.out.println("\n--------------------------------------------------------------------------------\n");
					System.out.print("Server Says: Command not valid or received.");
					System.out.println("\n--------------------------------------------------------------------------------\n");
					messageToClient = "Command not valid or recieved.";
					System.out.println("\n--------------------------------------------------------------------------------\n");
					System.out.println("Server Says: " + messageToClient);
					System.out.println("\n--------------------------------------------------------------------------------\n");
					Thread.sleep(2000);
					break;
				}
				
				outputToAnotherObject.writeObject(messageToClient);
				
				// check for DAN_OFF command and break the while loop so that server will not receive any further messages from client.
				if(messageFromClient.equals("DAN_OFF"))
					break;
				
			}
			
			// write the object to JSON file. Look at the 10.json file for output.
			new MyWriter(dan, 10);
			
			inputFromAnotherObject.close();
			outputToAnotherObject.close();
			
			// close the ServerSocket object
			closeAll();
						
		} catch (IOException | ClassNotFoundException | InterruptedException e) {
			
			System.out.println("Server: Error - " + e.getMessage());
			
			e.printStackTrace();
		}
		
	}

}
