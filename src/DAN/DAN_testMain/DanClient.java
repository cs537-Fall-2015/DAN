package DAN.DAN_testMain;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import generic.RoverClientRunnable;

public class DanClient extends RoverClientRunnable{

	public DanClient(int port, InetAddress host) throws UnknownHostException {
		super(port, host);
	}

	public void run() {
		ClientGUI window = new ClientGUI();
		window.frame.setVisible(true);
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new ClientGUI();
					window.frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
		
		try	{
			
		    // Write message to the server
		    ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
		    // Read message from the server
		    ObjectInputStream inputFromAnotherObject = new ObjectInputStream(getRoverSocket().getSocket().getInputStream());
	        
		    while(true) {
		    	Thread.sleep(1000);
		    	if (window.isDanOn)
		    		break;
		    }
		    do {
			    try {
		            // FileReader reads text files in the default encoding.
		            FileReader fileReader = new FileReader("Commands.txt");
	
		            // Always wrap FileReader in BufferedReader.
		            BufferedReader bufferedReader = new BufferedReader(fileReader);
		            
		            String command = null;
		            while((command = bufferedReader.readLine()) != null){
		            	
				    	window.println("\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
				    	window.println("Client Says: SENDING COMMAND - " + command);
				    	window.println("\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
				    	
				    	Thread.sleep(5000);
				    	
				    	outputToAnotherObject.writeObject(command);
				    	String message = (String) inputFromAnotherObject.readObject();
				    	if (command.equals("DAN_NEUTRON_COUNT")) {
				    		message = message.trim();
				    		String [] getNeutronCount = message.split(" ");
				    		window.plotGraph(getNeutronCount[getNeutronCount.length -1], Double.parseDouble(getNeutronCount[3]));
				    	}
				    			    	
				    	window.println("\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
				    	window.println("Client Says: MESSAGE RECEIVED - " + message);
				    	window.println("\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
				    	
				    	Thread.sleep(5000);
		            }
				    // Always close files.
		            bufferedReader.close();
		            
		        }catch(Exception ex) {
		        	ex.printStackTrace();
		        }
		    } while(ClientGUI.isAutomatic);
		    
	        inputFromAnotherObject.close();
	        outputToAnotherObject.close();
	        
	        closeAll();
	        
		}catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		catch (Exception error) {
			System.out.println("Client: Error:" + error.getMessage());
			error.printStackTrace();
		}
		
	}

}

