package DAN.dev;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import generic.RoverServerRunnable;

public class DANServer2 extends RoverServerRunnable{

	public DANServer2(int port) throws IOException {
		super(port);
	}

	public void run() {
		DANClass2 dan = new DANClass2();

		try {
			getRoverServerSocket().openSocket();

			//input from client
			ObjectInputStream input = new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());
			//output to client
			ObjectOutputStream output = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());

			//infinite running loop for the server to receive messages from the client
			while(true) {
				//read message from client
				String messageFromClient = (String) input.readObject();
				System.out.println("Test2: " + messageFromClient);
				//message sent to client
				String messageToClient = "Waiting to receive message...";
				
				switch(messageFromClient) {
					case "DAN_ROVER_X":
						messageToClient = "Getting rover's X position.";
						Thread.sleep(1000);
						dan.setROVER_X(2.0);					
						break;
					case "DAN_ROVER_Y":
						messageToClient = "Getting rover's Y position";
						Thread.sleep(1000);
						dan.setROVER_Y(3.0);
						break;
					case "DAN_ON":
						messageToClient = "Dan is on.";
						Thread.sleep(1000);
						dan.setDAN_ON(true);
						break;
					case "DAN_PNG_ON":
						messageToClient = "PNG is on.";
						Thread.sleep(2000);
						dan.setPNG_ON(true);
						dan.setNEUTRON_VELOCITY(60.0);
						break;
					case "DAN_DE_ON":
						messageToClient = "DE is on.";
						Thread.sleep(3000);
						dan.setDE_ON(true);
						dan.setNEUTRON_RESPONSE_TIME(30.0);
						dan.setHYD_AMOUNT(5.0);
						break;
					case "DAN_PNG_OFF":
						messageToClient = "PNG is off.";
						Thread.sleep(1000);
						dan.setPNG_ON(false);
						break;
					case "DAN_DE_OFF":
						messageToClient = "DE is off.";
						Thread.sleep(1000);
						dan.setDE_ON(false);
						break;
					case "DAN_HYD_INFO":
						messageToClient = "Information is being written to json file.";
						//send message back to client
						Thread.sleep(2000);
						//output.writeObject(messageToClient);
						break;
					case "DAN_OFF":
						messageToClient = "Dan is off.";
						Thread.sleep(1000);
						dan.setDAN_ON(false);
						break;
					default:
						break;
					}
				output.writeObject(messageToClient);
				
				//check to see if DAN is still on
				if (messageFromClient.equals("DAN_OFF")) {
					break;
				}
			}
			//close all connections
			input.close();
			output.close();
			closeAll();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
	}

}
