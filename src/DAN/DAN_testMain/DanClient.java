package DAN.DAN_testMain;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

import DAN.server.DanClass;
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
/*		    JFrame window = new JFrame();
		    window.setVisible(true);
			//window.setBounds(100,100, 450, 300);
		    window.setSize(300, 200);
			window.setTitle("Client");
		    
		    JPanel contentPane = new JPanel(new BorderLayout());
			window.setContentPane(contentPane);
			final JTextArea clientText = new JTextArea();
			DefaultCaret caret = (DefaultCaret)clientText.getCaret();
			caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
			JScrollPane scrollPane = new JScrollPane(clientText);
			contentPane.add(scrollPane, BorderLayout.CENTER);
			
			final JTextField cmdText = new JTextField();
			contentPane.add(cmdText,BorderLayout.SOUTH);
			final JButton btnSend = new JButton("Send");
			contentPane.add(btnSend, BorderLayout.LINE_END);
			
			cmdText.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					btnSend.doClick();
					
				}
				
			});

*/
		    
		    // Write message to the server
		    outputToAnotherObject = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
		    // Read message from the server
		    inputFromAnotherObject = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
	        
		    try {
	            // FileReader reads text files in the default encoding.
	            FileReader fileReader = 
	                new FileReader("Commands.txt");

	            // Always wrap FileReader in BufferedReader.
	            BufferedReader bufferedReader = 
	                new BufferedReader(fileReader);
	            String command = null;
	            while((command = bufferedReader.readLine()) != null) {
	                System.out.println(command);
	                if (command.toUpperCase().contains("OFF"))
			    		Thread.sleep(5000);
	                // writing the command to Server
			    	outputToAnotherObject.writeObject(command);
			    	// Reading the message from Server
			    	String message = (String) inputFromAnotherObject.readObject();
/*			    	clientText.append(" \n");
					clientText.append("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
			    	clientText.append("Client : Message from Server - " + message + "\n");
					clientText.append("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
					clientText.append(" \n");
	               
*/					
			    	System.out.println(" \n");
			    	System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			    	System.out.println("Client : Message from Server - " + message + "\n");
			    	System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			    	System.out.println(" \n");
	            }
			    // Always close files.
	            bufferedReader.close();         
	        }        catch(Exception ex) {
	        	ex.printStackTrace();
	        }

		    
		    /* send one by one command to the server
		    for(int i = 0; i <commands.length; i++) {
		    	
		    	// this if loop is just to make it to sleep for 5000 milliseconds before executing every operation
		    	if (commands[i].toUpperCase().contains("OFF"))
		    		Thread.sleep(5000);
		    	
		    	// writing the command to Server
		    	outputToAnotherObject.writeObject(commands[i]);
		    	// Reading the message from Server
		    	String message = (String) inputFromAnotherObject.readObject();

		    	clientText.append(" \n");
				clientText.append("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
		    	clientText.append("Client : Message from Server - " + message + "\n");
				clientText.append("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
				clientText.append(" \n");
		    }*/
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

