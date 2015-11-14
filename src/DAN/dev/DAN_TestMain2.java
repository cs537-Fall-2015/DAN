package DAN.dev;

import java.io.IOException;

import generic.RoverThreadHandler;
import json.Constants;

public class DAN_TestMain2 {
	public static void main(String[] args) {
		//get port number
		int port_one = Constants.PORT_ONE;
		
		try {
			
		//create thread for module one
		DANServer2 server1 = new DANServer2(port_one);
		Thread serverThread1 = RoverThreadHandler.getRoverThreadHandler().getNewThread(server1);
		
		//server starts to listen
		serverThread1.start();
		DANClient2 client1 = new DANClient2(port_one, null);
		Thread clientThread1 = RoverThreadHandler.getRoverThreadHandler().getNewThread(client1);
		
		//client starts to listen
		clientThread1.start();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
