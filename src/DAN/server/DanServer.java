package DAN.server;


import java.awt.BorderLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

import json.MyWriter;
import generic.RoverServerRunnable;

public class DanServer extends RoverServerRunnable {
	
	public DanServer(int port) throws IOException {
		super(port);
	}
	
	public String turnPngOn(DanClass dan, JTextArea clientText) throws InterruptedException {
		String message = null;
		if(dan.isDAN_ON()) {
			dan.setDAN_PNG_ON(true);
			clientText.append("\nGenerating Pulse Neutron..\n");
			Thread.sleep(3000);
			message = " Pulsed Neutron is turned on Successfully";
		}
		return message;
	}
	public void setHydFromSpeed(DanClass dan, JTextArea clientText) {
		float hydInfo;
		clientText.append("\n Speed of the Neutron is " +dan.getSpeed()+ "\n");

		if (dan.getSpeed() < 25) {
			hydInfo = 50 + (int)(Math.random() * (50 + 1));
			dan.setDAN_HYD_INFO(hydInfo);
		}
		else if( dan.getSpeed() >= 25 && dan.getSpeed() < 50)
		{
			hydInfo = 25 + (int)(Math.random() * (25 + 1));
			dan.setDAN_HYD_INFO(hydInfo);
		}
		else if(dan.getSpeed() >= 50 && dan.getSpeed() < 75)
		{
			hydInfo = 0 + (int)(Math.random() * (25 + 1));
		}
		else 
		{
			hydInfo = 0;
									
		}
		dan.setDAN_HYD_INFO(hydInfo);
	}	

	@Override
	public void run() {
		
		DanClass dan = new DanClass ();
		
		// GUI starts here
		JFrame window = new JFrame();
		window.setBounds(100,100, 450, 300);
		window.setTitle("Rover");
	    window.setVisible(true);
	    JPanel contentPane = new JPanel(new BorderLayout());
		window.setContentPane(contentPane);
		final JTextArea clientText = new JTextArea();
		DefaultCaret caret = (DefaultCaret)clientText.getCaret();
		 caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		JScrollPane scrollPane = new JScrollPane(clientText);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		final JTextField cmdText = new JTextField();
		contentPane.add(cmdText,BorderLayout.SOUTH);
		// Initialized all GUI
		
		try {
			
			getRoverServerSocket().openSocket();
			
			// Getting Input from Client
			ObjectInputStream inputFromAnotherObject = new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());
			// Writing output to Client
			ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
			
			// this loops run indefinitely, so that server will receive the commands one by one.
			while(true) {
				// Reading the message from client.
				String messageFromClient = (String) inputFromAnotherObject.readObject();
				// Store the messages to "messageToClient" before write the output to Client
				String messageToClient= null;
				// Append the output to the Text area(GUI)
				clientText.append("\n------------------------------------------------------------------\n");
				clientText.append("Server : COMMAND RECEIVED - "+messageFromClient+"\n");
				clientText.append("------------------------------------------------------------------\n");
				
				switch(messageFromClient) {
					case "DAN_TURN_ON":
						clientText.append("DAN is turning on. Please wait..");
						// Server thread sleep for 2000 milliseconds
						Thread.sleep(2000);
						messageToClient = "DAN is turned on";
						// call the "setDAN_ON method to set the dan is turned on as true. 
						dan.setDAN_ON(true);
						break;
					case "DAN_TURN_PNG_ON":
						clientText.append("DAN Pulsed Neutron Generator is turning on. Please wait.....");
						Thread.sleep(1000);
						// call the turnpngOn method to turn the png on.
						messageToClient = turnPngOn(dan, clientText);
						break;
					case "DAN_TURN_PNG_OFF" :
						messageToClient = "DAN png is turned off";
						break;
					case "DAN_TURN_DE_ON":
						messageToClient = "DAN de is turned on";
						clientText.append("Measuring the rate of delayed neutron. Please wait...");
						Thread.sleep(1000);
						// call setHydFromSpeed will set the hydrogen content which is calculated from the speed.
						setHydFromSpeed(dan, clientText);
						break;
					case "DAN_TURN_DE_OFF":
						messageToClient = "DAN de is turned off";
						break;
					case "DAN_HYD_INFO":

						messageToClient = "Percentage of hydrogen found is " + dan.getDAN_HYD_INFO()+ "%";
						break;
					case "DAN_TURN_OFF":
						messageToClient = "DAN turned off";
						break;
					default:
						break;
				}
				// write the message to the client (Don't do multiple write. It may stuck if you don't read in client after you from server)
				outputToAnotherObject.writeObject(messageToClient);
				// check if DAN_TURN_OFF and break the loop. So that server will not receieve any message from client.
				// we may need work on the below line later to go to sleep for few mins and start to work again.
				if (messageFromClient.equals("DAN_TURN_OFF")) {
					break;
				}
			}
			// write the object to JSON file. you can look at the 10.json file for output.
			new MyWriter(dan, 10);
			
			inputFromAnotherObject.close();
          	outputToAnotherObject.close();
			// close the ServerSocket object
			closeAll();

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







