// Jets by Walter S. Valdez at https://github.com/theunmanifested/JetsProject 
// Wk3, SD28, documentation = https://github.com/SkillDistillery/SD28/blob/master/java2/Jets/README.md 
// 
package com.skilldistillery.jets;

public abstract class Jet {
	private String model;
	private double speed; 	// mph, but can also be mach
	private int range; 		// nm, nautical miles
	private long price;
	
	
	
	// constructors can be called by an implementation of this abstract class
	public Jet(String model, double speed, int range, long price) {
		super();
		this.model = model;
		this.speed = speed;
		this.range = range;
		this.price = price;
	}
	
	// methods shared by all descendants

	public Jet() {
	}

	// fly() prints out the jet details and the amount of time the jet can fly 
	// until it runs out of fuel (based on speed and range).
	public void fly() {
		// time = distance / speed, in seconds. Convert to hours, minutes, seconds. Casted to ints so not super precise
		int seconds = (int) ((3600) * (range / speed));
		int p1 = seconds % 60;
        int p2 = seconds / 60;
        int p3 = p2 % 60;
        p2 = p2 / 60;
        System.out.println("Flight Time Left (hrs:mins:sec): " + p2 + ":" + p3 + ":" + p1 + "\n");
        System.out.println(toString());
		
        
	}

	@Override
	public String toString() {
		return "Jet's Model = " + model + ", Speed = " + speed + ", Range = " + range + ", Price = " + price;
	}

	public String getModel() {
		return model;
	}

	public double getSpeed() {
		return speed;
	}

	public int getRange() {
		return range;
	}

	public long getPrice() {
		return price;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	
	
	
	
	
	
}
