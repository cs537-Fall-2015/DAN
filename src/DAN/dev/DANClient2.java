package DAN.dev;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import generic.RoverClientRunnable;

public class DANClient2 extends RoverClientRunnable{

	public DANClient2(int port, InetAddress host) throws UnknownHostException {
		super(port, host);
	}

	public void run() {
		try {
			//Write message to server
			ObjectOutputStream output = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
			//Read message from server
			ObjectInputStream input = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
			
			//test commands
			String [] commands = {"DAN_ROVER_X", "DAN_ROVER_Y", "DAN_ON", "DAN_PNG_ON", "DAN_DE_ON", "DAN_PNG_OFF", "DAN_DE_OFF", "DAN_HYD_INFO", "DAN_OFF"};

			//Send each command to the server
			for (int i = 0; i < commands.length; i++) {
				//write to server
				output.writeObject(commands[i]);
				//read from server
				if(input.available() > 0) {
					String fromServerMessage = (String) input.readObject();
					System.out.println("Message From Server: " + fromServerMessage);
				} else {
					System.out.println("No Response");
				}
			}
			
			//close all connections
			input.close();
			output.close();
			Thread.sleep(2000);
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
