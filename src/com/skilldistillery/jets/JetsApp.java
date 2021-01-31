package com.skilldistillery.jets;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class JetsApp {

	private Airfield airfield; // populated airfield
	private Scanner scanner = new Scanner(System.in);
//	private Jet gJet = new JetImpl(); // ready Jet impl class to access its fields and behaviors
//	private Jet fJet = new FighterJet();
//	private Jet cJet = new CargoPlane();

	public static void main(String[] args) {
		// to call other classes' methods
		JetsApp jA = new JetsApp();
		jA.launch();
	} // end of main

	// literally starts and launches the Applicaion
	private void launch() {
		int uChoice;
		// populate airfield with txt file at project level
		airfield = new Airfield(populateAirfield());
		// greet user an present menu
		System.out.println("Hello there!\nWelcome the Jets Application\n");
		// prompt user make an integer choice from 1 to 9
		do {
			printUserMenu();
			uChoice = scanner.nextInt();
			scanner.nextLine(); // help with console flow
		} while (uChoice < 1 || uChoice > 9);

		handleUserChoice(uChoice);

	} // end of launch

	// display the menu for the user to choose
	private void printUserMenu() {
		System.out.println("\nPick a number for your choice: ");
		System.out.println("1  -  List The Fleet");
		System.out.println("2  -  Fly All Jets");
		System.out.println("3  -  Fastest Jet");
		System.out.println("4  -  Longest-Range Jet");
		System.out.println("5  -  Load... Cargo Jets");
		System.out.println("6  -  Start A Dogfight!");
		System.out.println("7  -  Add Your Own Jet to Fleet");
		System.out.println("8  -  Remove A Jet");
		System.out.println("9  -  Quit");

	}

	private void handleUserChoice(int choice) {
		switch (choice) {
		case 1:
			System.out.println("\n -- Listing Fleet --\n");
			printFleet(airfield);
			break;
		case 2:
			System.out.println("\n -- Flying All Jets --\n");
			flyAllJets(airfield);
			break;
		case 3:
			System.out.println("\n -- Fastest Jet --\n");
			displayFastestJet(airfield);
			break;
		case 4:
			System.out.println("\n -- Longest Range Jet --\n");
			displayLongestRangeJet(airfield);
			break;
		case 5:
			System.out.println("\n -- Loading All Cargo Jets --\n");
			// TODO ....
			break;
		case 6:
			System.out.println("\n -- Starting A Dog Fight --\n");
			// TODO ....
			break;
		case 7:
			System.out.println("\n -- Add Your Jet --\n");
			// TODO ....
			break;
		case 8:
			System.out.println("\n -- Remove A Jet --\n");
			// TODO ....
			break;
		case 9:
			System.out.println("\n -- Exiting The Program --\n");
			System.out.println("\nThank You, And Have A Nice Day!");
			System.exit(0);
			// TODO ....
			break;
		default:
			System.out.println("\n -- Invalid Entry --");
			break;
		}
	} // end of handlesUserChoice

	private List<Jet> populateAirfield() {
		Jet jet = null;
		// read data from txt file
		List<Jet> jetList = new ArrayList<>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("jets.txt"));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] jetLine = line.split(", ");
				String jetName = jetLine[0];
				double speed = Double.parseDouble(jetLine[1]);
				int range = Integer.parseInt(jetLine[2]);
				long price = Long.parseLong(jetLine[3]);
				jet = new JetImpl(jetName, speed, range, price); // use JetImpl since Jet is an abstract class
				jetList.add(jet);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return jetList;
	}// end of populateAirfield()

	private void printFleet(Airfield airfield) {
		// pretty up choice 1 List Fleet
		List<Jet> printList = airfield.getListOfJets();
		for (Jet jet : printList) {
			
			System.out.println("\t||-------------------------------------------");
			System.out.println("\t||");
			System.out.println("\t|| Jet's Model: " + jet.getModel());
			System.out.println("\t||");
			System.out.println("\t|| Jet's Top Speed: " + jet.getSpeed());
			System.out.println("\t||");
			System.out.println("\t|| Jet's Range: " + jet.getRange());
			System.out.println("\t||");
			System.out.println("\t|| Jet's Price: " + jet.getPrice());
			System.out.println("\t||");
			System.out.println("\t||---------------------------------------------");
			
		}
	}// end of printFleet()

	private void flyAllJets(Airfield airfield) {
		System.out.println("Here the jet's details, and its remainig fligh time: ");
		List<Jet> jetsList = airfield.getListOfJets();
		for (Jet jet : jetsList) {
			jet.fly();
		}
	} // end of flyAllJets()
	
	private void displayFastestJet(Airfield airfield) {
		double fSpeed = 0.0;
		Jet fastestJet = null;
		System.out.println("The Fastest Jet is: ");
		List<Jet> jetsList = airfield.getListOfJets();
		for (Jet jet : jetsList) {
			if (jet.getSpeed() > fSpeed) {
				fastestJet = jet;
				fSpeed = jet.getSpeed();
			}
		}
		System.out.println(fastestJet);		
	}// end of displayFastestJet
	
	private void displayLongestRangeJet(Airfield airfield) {
		int lRange = 0;
		Jet lRJet = null;
		System.out.println("The Longest Range Jet is: ");
		List<Jet> jetsList = airfield.getListOfJets();
		for (Jet jet : jetsList) {
			if (jet.getRange() > lRange) {
				lRJet = jet;
				lRange = jet.getRange();
			}
		}
		System.out.println(lRJet);		
	}// end of displayFastestJet

} // end of JetsApp class
