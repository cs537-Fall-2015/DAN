package server;

import callback.CallBack;

public class DanClass {
	static boolean DAN_ON;
	static boolean DAN_PNG_ON;
	static boolean DAN_DE_ON;
	private double DAN_COLLECT_HYD;
	private double speed;
	private double rover_x;
	private double rover_y;
	
	
	
	public DanClass(int i) {
		this.setDAN_ON(true);
		this.setDAN_PNG_ON(true);
		this.setDAN_DE_ON(false);
		this.setDAN_H_CONTENT(0.0);
		this.setSpeed(Math.random()*100);
		this.setrover_x(10.0);
		this.setrover_y(15.0);
	}

	public static boolean isDAN_ON() {
		//CallBack cb = new CallBack();
		//cb.done();
		return DAN_ON;
	}

	public void setDAN_ON(boolean dAN_ON) {
		//CallBack cb = new CallBack();
		//cb.done();
		DAN_ON = dAN_ON;
	}

	public static boolean isDAN_PNG_ON() {
		return DAN_PNG_ON;
	}

	public void setDAN_PNG_ON(boolean dAN_PNG_ON) {
		DAN_PNG_ON = dAN_PNG_ON;
	}

	public static boolean isDAN_DE_ON() {
		return DAN_DE_ON;
	}

	public void setDAN_DE_ON(boolean dAN_DE_ON) {
		DAN_DE_ON = dAN_DE_ON;
	}

	public double getDAN_COLLECT_HYD() {
		return DAN_COLLECT_HYD;
	}

	public void setDAN_H_CONTENT(double dAN_H_COLLECT) {
		DAN_COLLECT_HYD = dAN_H_COLLECT;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public double getrover_x() {
		return rover_x;
	}

	public void setrover_x(double rover_x) {
		this.rover_x = rover_x;
	}

	public double getrover_y() {
		return rover_y;
	}

	public void setrover_y(double rover_y) {
		this.rover_y = rover_y;
	}

}
