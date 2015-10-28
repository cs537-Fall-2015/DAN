package DAN.DAN_testMain;

import generic.RoverThreadHandler;

import java.io.IOException;

import DAN.server.DanServer;
//import module2.ModuleTwoClient;
//import module2.ModuleTwoServer;
import DAN.server.DanClient;
import json.Constants;

public class DAN_testMain {

	public static void main(String[] args) {

		//Each module has its own port
		int port_one = Constants.PORT_ONE;
		//int port_two = Constants.PORT_TWO;

		try {
			// create a thread for module one
			DanServer serverOne = new DanServer(port_one);
			Thread server_1 = RoverThreadHandler.getRoverThreadHandler().getNewThread(serverOne);

			// each server begins listening
			server_1.start();

			// client one server sending messages to server two
			DanClient clientOne = new DanClient(port_one, null); // notice port_two
			Thread client_1 = RoverThreadHandler.getRoverThreadHandler().getNewThread(clientOne);
			// client two server sending messages to server one
			//	ModuleTwoClient clientTwo = new ModuleTwoClient(port_one, null); // notice port_one
			//	Thread client_2 = RoverThreadHandler.getRoverThreadHandler().getNewThread(clientOne);

			// start the thread which communicates through sockets
			client_1.start();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

	}

}

