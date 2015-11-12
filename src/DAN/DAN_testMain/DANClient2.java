package DAN.DAN_testMain;

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
			ObjectOutputStream output = null;
			ObjectInputStream input = null;
			Thread.sleep(2000);



			//Write message to server
			output = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
			//Read message from server
			input = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());

			//test commands
			String [] commands = {"DAN_ON", "DAN_PNG_ON", "DAN_DE_ON", "DAN_RESULTS"};

			//Send each command to the server
			for (int i = 0; i < commands.length; i++) {
				//write to server
				output.writeObject(commands[i]);
				//read from server
				String fromServerMessage = (String) input.readObject();
				System.out.println("Test" + fromServerMessage);
			}
			
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
