package DAN.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONObject;

import callback.CallBack;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import json.Constants;
import json.GlobalReader;
import json.MyWriter;
import generic.RoverServerRunnable;

public class DanServer extends RoverServerRunnable {
	
	public DanServer(int port) throws IOException {
		super(port);
	}
	
	public String turnPngOn(DanClass dan) throws InterruptedException {
		String message = null;
		if(dan.isDAN_ON()) {
			dan.setDAN_PNG_ON(true);
			System.out.println("Generating Pulse Neutron..");
			Thread.sleep(3000);
			message = " Pulsed Neutron is turned on Successfully";
		}
		return message;
	}
	@Override
	public void run() {
		
		DanClass dan = new DanClass ();
		
		JFrame window = new JFrame();
		window.setBounds(100, 100, 450, 300);
		window.setTitle("Rover");
	    window.setVisible(true);
	    JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		window.setContentPane(contentPane);
		final JTextArea clientText = new JTextArea(20, 20);
		JScrollPane scrollPane = new JScrollPane(clientText);
		contentPane.add(scrollPane, "cell 1 1,grow");
		
		final JTextField cmdText = new JTextField();
		contentPane.add(cmdText, "flowx,cell 1 2,alignx left,growy");
		cmdText.setColumns(30);
		
		
		try {
			
			getRoverServerSocket().openSocket();
			
			ObjectInputStream inputFromAnotherObject = new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());
			ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
			
			
			while(true) {
				String messageFromClient = (String) inputFromAnotherObject.readObject();
				String messageToClient= null;
				System.out.println("------------------------------------------------------------------");
				System.out.println("Server : COMMAND RECEIVED - "+messageFromClient);
				System.out.println("------------------------------------------------------------------");
				
				switch(messageFromClient) {
					case "DAN_TURN_ON":
						System.out.println("DAN is turning on. Please wait..");
						Thread.sleep(2000);
						messageToClient = "DAN is turned on";
						dan.setDAN_ON(true);
						break;
					case "DAN_TURN_PNG_ON":
						System.out.println("DAN Pulsed Neutron Generator is turning on. Please wait.....");
						Thread.sleep(1000);
						messageToClient = turnPngOn(dan);
						break;
					case "DAN_TURN_PNG_OFF" :
						messageToClient = "DAN png is turned off";
						break;
					case "DAN_TURN_DE_ON":
						messageToClient = "DAN de is turned on";
						break;
					case "DAN_TURN_DE_OFF":
						messageToClient = "DAN de is turned off";
						break;
					case "DAN_HYD_INFO":
						messageToClient = "DAN hyd info";
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







