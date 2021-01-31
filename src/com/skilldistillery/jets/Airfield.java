package com.skilldistillery.jets;

import java.util.*;

public class Airfield {
	private List<Jet> listOfJets;

	
	// constructors	
	public Airfield() {
		
	}

	public Airfield(List<Jet> listOfJets) {
		this.listOfJets = listOfJets;
	}

	public List<Jet> getListOfJets() {
		return listOfJets;
	}

	public void setListOfJets(List<Jet> listOfJets) {
		this.listOfJets = listOfJets;
	}

	// to display
	@Override
	public String toString() {
		return "Airfield [listOfJets=" + listOfJets + "]";
	}

	
	
	
	
}


