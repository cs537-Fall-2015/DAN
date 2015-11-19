package DAN.DAN_testMain;

import generic.RoverThreadHandler;

import java.io.IOException;

import DAN.server.DANServer;
import json.Constants;

public class DAN_TestMain {

	public static void main(String[] args) {
		
		//Each module has its own port
		int port_one = Constants.PORT_ONE;
		
		try {
			
			// create a thread for module one
			DANServer serverOne = new DANServer(port_one);
			Thread server_1 = RoverThreadHandler.getRoverThreadHandler().getNewThread(serverOne);
						
			// Server begins listening
			server_1.start();
			
			DANClient clientOne = new DANClient(port_one, null);
			Thread client_1 = RoverThreadHandler.getRoverThreadHandler().getNewThread(clientOne);
			
			//Client Listening
			client_1.start();
	
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
