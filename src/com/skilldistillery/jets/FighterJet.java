package com.skilldistillery.jets;

public class FighterJet extends Jet implements CombatReady{

	public FighterJet() {
		super();
	}
	
	public FighterJet(String model, double speed, int range, long price, String type) {
		super(model, speed, range, price, type);		
	}

	@Override
	public void flightAttack() {
		// Activated when user selects DogFight
		System.out.println("DOGFIGHT!!!!");
		
	}
	
}
