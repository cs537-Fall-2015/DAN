package DAN.server;

import callback.CallBack;

public class DanClass {

	static boolean DAN_ON; //DAN: Dynamic Albedo of Neutrons
	static boolean DAN_PNG_ON; //PNG: Pulsed Neutron Generator
	static boolean DAN_DE_ON; //DE: Detector Element
	private double DAN_HYD_INFO; //HYD: Hydrogen
	private double speed;
	private double rover_x;
	private double rover_y;
	static String commands [] = { "DAN_TURN_ON",
								  "DAN_TURN_PNG_ON",
								  "DAN_TURN_PNG_OFF",
								  "DAN_TURN_DE_ON",
								  "DAN_TURN_DE_OFF",
								  "DAN_HYD_INFO",
								  "DAN_TURN_OFF"
								};
	

	public DanClass() {
		this.setDAN_ON(true);
		this.setDAN_PNG_ON(true);
		this.setDAN_DE_ON(false);
		this.setDAN_HYD_INFO(0.0);
		this.setSpeed(Math.random()*100);
		this.setRover_x(10.0);
		this.setRover_y(15.0);
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

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getRover_x() {
		return rover_x;
	}

	public void setRover_x(double rover_x) {
		this.rover_x = rover_x;
	}

	public double getRover_y() {
		return rover_y;
	}

	public void setRover_y(double rover_y) {
		this.rover_y = rover_y;
	}

	public static void setCommands(String[] commands) {
		DanClass.commands = commands;
	}

	public static String[] getCommands() {
		return commands;
	}

	public double getDAN_HYD_INFO() {
		return DAN_HYD_INFO;
	}

	public void setDAN_HYD_INFO(double dAN_HYD_INFO) {
		DAN_HYD_INFO = dAN_HYD_INFO;
	}

	
}
