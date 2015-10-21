package server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
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
	    //window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setBounds(300, 300, 300, 300);
	    window.setVisible(true);
	    JLabel text = new JLabel("");
	    window.add(text);
	    window.add(new JPanel(){
	    	@Override
	    	public void paintComponent(Graphics g){
	    		super.paintComponent(g);
	    		g.drawRect(100, 100, 105, 105);
	    		g.drawString("DAN", 125, 170 );

	    	}
	    }, BorderLayout.CENTER);
	    
	    

		
		
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
						text.setForeground(Color.GREEN);
						//text.setBackground(Color.GREEN);
						text.setText(messageFromClient);

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
			
			/*while (DanClass.DAN_ON == true) 
			{
				System.out.println("Server: Waiting for client request.......");
				

				// creating socket and waiting for client connection
				getRoverServerSocket().openSocket();
				System.out.println("socket open");
				// read from socket to ObjectInputStream object
				ObjectInputStream inputFromAnotherObject = new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());
				
				// convert ObjectInputStream object to String
				String message = (String) inputFromAnotherObject.readObject();
				System.out.println("Server: Message Received from Client - "+ message.toUpperCase());  //Hydrogen content
				
				// create ObjectOutputStream object
				ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
				
				outputToAnotherObject.writeObject("HELLO");
				
				if (DanClass.DAN_PNG_ON == true)
				{
					DanClass.DAN_DE_ON = true;
					outputToAnotherObject.writeObject("Calculating the speed of Neutrons.... ");
					System.out.println("Server: Speed is " +moduleOneClass.getSpeed());
					double a;
					
					if( moduleOneClass.getSpeed() < 25 )
					{
						// write object to Socket
						//Generating random integers in a range
						//Min + (int)(Math.random() * ((Max - Min) + 1))
						
						a = 50 + (int)(Math.random() * (50 + 1));
						moduleOneClass.setDAN_H_CONTENT(a);
						outputToAnotherObject.writeObject("Percentage of Hydrogen found is : "  + a +  "% at location " +moduleOneClass.getrover_x()+ " " +moduleOneClass.getrover_y());
						
					}
					else if( moduleOneClass.getSpeed() >= 25 && moduleOneClass.getSpeed() < 50)
					{
						a = 25 + (int)(Math.random() * (25 + 1));
						moduleOneClass.setDAN_H_CONTENT(a);
						outputToAnotherObject.writeObject("Percentage of Hydrogen found is : "  + a +  "% at location " +moduleOneClass.getrover_x()+ " " +moduleOneClass.getrover_y());
						
					}
					else if( moduleOneClass.getSpeed() >= 50 && moduleOneClass.getSpeed() < 75)
					{
						a = 0 + (int)(Math.random() * (25 + 1));
						moduleOneClass.setDAN_H_CONTENT(a);
						outputToAnotherObject.writeObject("Percentage of Hydrogen found is : "  + a +  "% at location " +moduleOneClass.getrover_x()+ " " +moduleOneClass.getrover_y()); 
						
					}
					else 
					{
						outputToAnotherObject.writeObject("There is no Hydrogen content present");
						
					}
					
					new MyWriter(moduleOneClass, 19);
					DanClass.DAN_PNG_ON = false;
					DanClass.DAN_DE_ON = false;
					System.out.println("Job Done!!!");
					System.out.println("Server shut down");
				}
				else
				{
					System.out.println("DAN_PNG_ON is false");
					//break;
				}	
				
				DanClass.DAN_ON = false;
				
				//close resources
	           	inputFromAnotherObject.close();
	          	outputToAnotherObject.close();
				
			}
			 
*/			
			inputFromAnotherObject.close();
          	outputToAnotherObject.close();
			// close the ServerSocket object
			closeAll();
			
			//inform the CCDS group9 about job completion.
			//orelse we will get Connection refused exception which is mostly due to Group 9 server being down 
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







