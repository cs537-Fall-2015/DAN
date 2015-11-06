package DAN.referenceCode;

public class DanClass {
	
	private boolean DAN_ON;  //Dynamic Albedo of Neutrons
	private boolean PNG_ON;  //Pulsed Neutron Generator
	private boolean DE_ON;   //Detector Element
	private double HYD_INFO; //Hydrogen content
	
	public DanClass() {
		this.setDAN_ON(false);
		this.setPNG_ON(false);
		this.setDE_ON(false);
		this.setHYD_INFO(0.0);
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

	public double getHYD_INFO() {
		return HYD_INFO;
	}

	public void setHYD_INFO(double hYD_INFO) {
		HYD_INFO = hYD_INFO;
	}

}
