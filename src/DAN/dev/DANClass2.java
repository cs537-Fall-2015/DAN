package DAN.dev;

public class DANClass2 {
	private boolean DAN_ON; //DAN: Dynamic Albedo of Neutrons
	private boolean PNG_ON; //PNG: Pulsed Neutron Generator
	private boolean DE_ON; //DE: Detector Element
	
	//The speed of the neutron generator
	private double NEUTRON_VELOCITY;
	
	//The time for neutron return
	private double NEUTRON_RESPONSE_TIME;
	
	//rover coordinates (for location of hydrogen)
	private double ROVER_X;
	private double ROVER_Y;
	
	//the amount of hydrogen and the depth that it was found
	private double HYD_AMOUNT; //HYD: Hydrogen
	private double HYD_DEPTH; // HYD_DEPTH = NEUTRON_VELOCITY * NEUTRON_RESPONSE_TIME
	
	
	public DANClass2() {
		this.setDAN_ON(false);
		this.setPNG_ON(false);
		this.setDE_ON(false);
		this.setNEUTRON_VELOCITY(-1.0);
		this.setNEUTRON_RESPONSE_TIME(-1.0);
		this.setROVER_X(-1.0);
		this.setROVER_Y(-1.0);
		this.setHYD_AMOUNT(0.0);
		this.HYD_DEPTH = -1.0;
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


	public double getNEUTRON_VELOCITY() {
		return NEUTRON_VELOCITY;
	}


	public void setNEUTRON_VELOCITY(double nEUTRON_VELOCITY) {
		NEUTRON_VELOCITY = nEUTRON_VELOCITY;
	}


	public double getNEUTRON_RESPONSE_TIME() {
		return NEUTRON_RESPONSE_TIME;
	}


	public void setNEUTRON_RESPONSE_TIME(double nEUTRON_RESPONSE_TIME) {
		NEUTRON_RESPONSE_TIME = nEUTRON_RESPONSE_TIME;
	}


	public double getROVER_X() {
		return ROVER_X;
	}


	public void setROVER_X(double rOVER_X) {
		ROVER_X = rOVER_X;
	}


	public double getROVER_Y() {
		return ROVER_Y;
	}


	public void setROVER_Y(double rOVER_Y) {
		ROVER_Y = rOVER_Y;
	}


	public double getHYD_AMOUNT() {
		return HYD_AMOUNT;
	}


	public void setHYD_AMOUNT(double hYD_AMOUNT) {
		HYD_AMOUNT = hYD_AMOUNT;
	}


	public double getHYD_DEPTH() {
		this.HYD_DEPTH = this.NEUTRON_VELOCITY * this.NEUTRON_RESPONSE_TIME;
		return this.HYD_DEPTH;
	}
	
}
