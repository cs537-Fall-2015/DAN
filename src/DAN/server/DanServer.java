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
		
		try {
			
			getRoverServerSocket().openSocket();
			
			ObjectInputStream inputFromAnotherObject = new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());
			ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
			
			
			while(true) {
				String messageFromClient = (String) inputFromAnotherObject.readObject();
				String messageToClient= null;
				clientText.append("\n------------------------------------------------------------------\n");
				clientText.append("Server : COMMAND RECEIVED - "+messageFromClient+"\n");
				clientText.append("------------------------------------------------------------------\n");
				
				switch(messageFromClient) {
					case "DAN_TURN_ON":
						clientText.append("DAN is turning on. Please wait..");
						Thread.sleep(2000);
						messageToClient = "DAN is turned on";
						dan.setDAN_ON(true);
						break;
					case "DAN_TURN_PNG_ON":
						clientText.append("DAN Pulsed Neutron Generator is turning on. Please wait.....");
						Thread.sleep(1000);
						messageToClient = turnPngOn(dan, clientText);
						break;
					case "DAN_TURN_PNG_OFF" :
						messageToClient = "DAN png is turned off";
						break;
					case "DAN_TURN_DE_ON":
						messageToClient = "DAN de is turned on";
						clientText.append("Measuring the rate of delayed neutron. Please wait...");
						Thread.sleep(1000);
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
				
				outputToAnotherObject.writeObject(messageToClient);
				if (messageFromClient.equals("DAN_TURN_OFF")) {
					break;
				}
			}
			
			new MyWriter(dan, 10);
			inputFromAnotherObject.close();
          	outputToAnotherObject.close();
			// close the ServerSocket object
			closeAll();
			
			//inform the CCDS group9 about job completion.
			//or else we will get Connection refused exception which is mostly due to Group 9 server being down 
			//new CallBack().done();
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







