package DAN.server;

import java.awt.BorderLayout;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

import json.Constants;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import generic.RoverClientRunnable;

public class DanClient extends RoverClientRunnable{

	public DanClient(int port, InetAddress host) throws UnknownHostException {
		super(port, host);
	}

	@Override
	public void run() {
		try
		{
			ObjectOutputStream outputToAnotherObject = null;
		    ObjectInputStream inputFromAnotherObject = null;
		    Thread.sleep(5000);
		    JFrame window = new JFrame();
			window.setBounds(100,100, 450, 300);
			window.setTitle("Client");
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

		    
		    // Write message to the server
		    outputToAnotherObject = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
		    // Read message from the server
		    inputFromAnotherObject = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
	            
		    String [] commands = DanClass.getCommands();
		    
		    for(int i = 0; i <commands.length; i++) {
		    	if (commands[i].toUpperCase().contains("OFF"))
		    		Thread.sleep(5000);
		    	outputToAnotherObject.writeObject(commands[i]);
		    	String message = (String) inputFromAnotherObject.readObject();
		    	clientText.append(" \n");
				clientText.append("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
		    	clientText.append("Client : Message from Server - " + message + "\n");
				clientText.append("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
				clientText.append(" \n");
		    }
	           	inputFromAnotherObject.close();
	           	outputToAnotherObject.close();
	           	Thread.sleep(5000);
	 
	        closeAll();
		}
        
		catch (UnknownHostException e) 
		{
			e.printStackTrace();
		}
		
		catch (Exception error) 
		{
			System.out.println("Client: Error:" + error.getMessage());
			error.printStackTrace();
		}
		
	}

}

