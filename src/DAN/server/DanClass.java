package DAN.server;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DANClass {	
	private boolean DAN_ON; //DAN: Dynamic Albedo of Neutrons
	private boolean PNG_ON; //PNG: Pulsed Neutron Generator
	private boolean DE_ON; //DE: Detector Element
	
	//Day and time of Mission
	private String DE_TIME;
	
	//The number of neutrons
	private int NEUTRON_COUNT;
	
	public DANClass() {
		setDAN_ON(false);
		setPNG_ON(false);
		setDE_ON(false);
		setNEUTRON_COUNT(-1);
		
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

	public int getNEUTRON_COUNT() {
		return NEUTRON_COUNT;
	}

	public void setNEUTRON_COUNT(int nEUTRON_COUNT) {
		NEUTRON_COUNT = nEUTRON_COUNT;
	}
	
}
