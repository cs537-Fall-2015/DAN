package DAN.server;

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
				//message sent to client
				String messageToClient = "Waiting to receive message...";
				
				switch(messageFromClient) {
					case "DAN_ROVER_X":
						break;
					case "DAN_ROVER_Y":
						break;
					case "DAN_ON":
						break;
					case "DAN_PNG_ON":
						break;
					case "DAN_DE_ON":
						break;
					case "DAN_PNG_OFF":
						break;
					case "DAN_DE_OFF":
						break;
					case "DAN_HYD_INFO":
						break;
					case "DAN_OFF":
						break;
					default:
						break;
					}
				//send message back to client
				output.writeObject(messageToClient);

				//check to see if DAN is still on
				if (messageFromClient.equals("DAN_OFF")) {
					break;
				}
			}
			//write object to JSON file
			//new MyWriter(dan, 10);

			//close all connections
			input.close();
			output.close();
			closeAll();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
	}

}
