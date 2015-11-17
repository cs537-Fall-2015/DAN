package DAN.server;
import callback.CallBack;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DANClass2 {	
	private boolean DAN_ON; //DAN: Dynamic Albedo of Neutrons
	private boolean PNG_ON; //PNG: Pulsed Neutron Generator
	private boolean DE_ON; //DE: Detector Element
	
	//Day of Mission
	private String DE_TIME;
	
	//The number of neutrons
	private double NEUTRON_COUNT;
	
	public DANClass2() {
		setDAN_ON(false);
		setPNG_ON(false);
		setDE_ON(false);
		setNEUTRON_COUNT(-1.0);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:ms");
		Date currentDate = new Date();
		setDE_TIME(dateFormat.format(currentDate));	
	}

	public boolean isDAN_ON() {
		return DAN_ON;
	}

	public void setDAN_ON(boolean dAN_ON) {
		DAN_ON = dAN_ON;
	}

	public boolean isPNG_ON() {
		return PNG_ON;
	}

	public void setPNG_ON(boolean pNG_ON) {
		PNG_ON = pNG_ON;
	}

	public boolean isDE_ON() {
		return DE_ON;
	}

	public void setDE_ON(boolean dE_ON) {
		DE_ON = dE_ON;
	}

	public String getDE_TIME() {
		return DE_TIME;
	}

	public void setDE_TIME(String date) {
		this.DE_TIME = date;
	}

	public double getNEUTRON_COUNT() {
		return Math.pow(10,7);
	}

	public void setNEUTRON_COUNT(double nEUTRON_COUNT) {
		NEUTRON_COUNT = nEUTRON_COUNT;
	}
	
}
