package DAN.DAN_testMain;

import generic.RoverThreadHandler;

import java.io.IOException;

import DAN.server.DanServer;
import DAN.server.DanClient;
import json.Constants;

public class DAN_testMain {

	public static void main(String[] args) {

		//Each module has its own port
		int port_one = Constants.PORT_ONE;

		try {
			// create a thread for module one
			DanServer serverOne = new DanServer(port_one);
			Thread server_1 = RoverThreadHandler.getRoverThreadHandler().getNewThread(serverOne);

			// each server begins listening
			server_1.start();

			// client one server sending messages to server two
			DanClient clientOne = new DanClient(port_one, null); // notice port_two
			Thread client_1 = RoverThreadHandler.getRoverThreadHandler().getNewThread(clientOne);

			// start the thread which communicates through sockets
			client_1.start();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

	}

}

