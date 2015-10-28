package DAN.referenceCode;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import generic.RoverClientRunnable;

public class DanClient2 extends RoverClientRunnable{

	public DanClient2(int port, InetAddress host)
			throws UnknownHostException {
		super(port, host);
	}

	@Override
	public void run() {
		try{
			ObjectOutputStream outputToAnotherObject = null;
		    ObjectInputStream inputFromAnotherObject = null;
		    Thread.sleep(5500);
		    
		    inputFromAnotherObject=new ObjectInputStream(getRoverSocket().getNewSocket().getInputStream());
		    
		    outputToAnotherObject = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
		    outputToAnotherObject.writeObject("Output Socket Power is ON");
		    
	        closeAll();
		}	        
        catch (UnknownHostException e) {
			e.printStackTrace();
		}
		catch (Exception error) {
			System.out.println("Client: Error:" + error.getMessage());
		}
		
	}

}


