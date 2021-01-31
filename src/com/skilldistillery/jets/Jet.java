package com.skilldistillery.jets;

public abstract class Jet {
	private String model;
	private double speed; 	// mph, but can also be mach
	private int range; 		// nm, nautical miles
	private long price;
	private String type; 	// facilitates edits to jets 
	
	
	
	// constructors can be called by an implementation of this abstract class
	public Jet(String model, double speed, int range, long price, String type) {
		super();
		this.model = model;
		this.speed = speed;
		this.range = range;
		this.price = price;
		this.type = type;
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
        System.out.println(toString());
        System.out.println("Flight Time Left (hrs:mins:sec): " + p2 + ":" + p3 + ":" + p1 + "\n");
		
        
	}

	@Override
	public String toString() {
		return "Jet [model=" + model + ", speed=" + speed + ", range=" + range + ", price=" + price + ", type=" + type
				+ "]";
	}

	public String getModel() {
		return model;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
