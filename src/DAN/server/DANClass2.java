package DAN.server;

public class DANClass2 {
	static boolean DAN_ON; //DAN: Dynamic Albedo of Neutrons
	static boolean DAN_PNG_ON; //PNG: Pulsed Neutron Generator
	static boolean DAN_DE_ON; //DE: Detector Element
	
	//The speed of the PNG
	private double DAN_SPEED;
	
	//rover coordinates (for location of hydrogen)
	private double ROVER_X;
	private double ROVER_Y;
	
	//the amount of hydrogen and the depth that it was found
	private double DAN_HYD_AMOUNT; //HYD: Hydrogen
	private double HYD_DEPTH;
	
	
	public DANClass2() {
		this.setDAN_ON(false);
		this.setDAN_PNG_ON(false);
		this.setDAN_DE_ON(false);
		this.setDAN_SPEED(0.0);
		this.setROVER_X(0.0);
		this.setROVER_Y(0.0);
		this.setDAN_HYD_AMOUNT(0.0);
		this.setDAN_HYD_AMOUNT(0.0);
	}
	
	public boolean isDAN_ON() {
		return DAN_ON;
	}
	public void setDAN_ON(boolean dAN_ON) {
		DAN_ON = dAN_ON;
	}
	public boolean isDAN_PNG_ON() {
		return DAN_PNG_ON;
	}
	public void setDAN_PNG_ON(boolean dAN_PNG_ON) {
		DAN_PNG_ON = dAN_PNG_ON;
	}
	public boolean isDAN_DE_ON() {
		return DAN_DE_ON;
	}
	public void setDAN_DE_ON(boolean dAN_DE_ON) {
		DAN_DE_ON = dAN_DE_ON;
	}
	public double getDAN_SPEED() {
		return DAN_SPEED;
	}
	public void setDAN_SPEED(double dAN_SPEED) {
		DAN_SPEED = dAN_SPEED;
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
	public double getDAN_HYD_AMOUNT() {
		return DAN_HYD_AMOUNT;
	}
	public void setDAN_HYD_AMOUNT(double dAN_HYD_AMOUNT) {
		DAN_HYD_AMOUNT = dAN_HYD_AMOUNT;
	}
	public double getHYD_DEPTH() {
		return HYD_DEPTH;
	}
	public void setHYD_DEPTH(double hYD_DEPTH) {
		HYD_DEPTH = hYD_DEPTH;
	}
}
