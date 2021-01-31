package com.skilldistillery.jets;

public class CargoPlane extends Jet implements CargoCarrier{

	public CargoPlane() {
		super();
	}
	
	public CargoPlane(String model, double speed, int range, long price, String type) {
		super(model, speed, range, price, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void loadUp() {
		// activated when user selects "Load All Cargo Jets"
		System.out.println("Roger that! Loading up all Cargo Carriers!");
		
	}
	
}
