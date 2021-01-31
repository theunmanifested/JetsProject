// Jets by Walter S. Valdez at https://github.com/theunmanifested/JetsProject 
// Wk3, SD28, documentation = https://github.com/SkillDistillery/SD28/blob/master/java2/Jets/README.md 
//
// Notes: Adding and Removing Jets is done locally, meaning not editing the jets.txt file
//        and the only types approved for adding are: "Fighter" and "Cargo Carrier". More features to come!!! (if needed)

package com.skilldistillery.jets;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class JetsApp {

	private Airfield airfield; // populated airfield
	private Scanner scanner = new Scanner(System.in);
	private boolean keepGoing = true;
	
	// Main

	public static void main(String[] args) {
		// to call other classes' methods
		JetsApp jA = new JetsApp();
		jA.launch();
	} // end of main

	// Methods
	
	// literally starts and launches the Applicaion
	private void launch() {
		int uChoice;
		
		// populate airfield with txt file at project level
		airfield = new Airfield(populateAirfield());
		// greet user an present menu and repeat until Quit
		do {
			System.out.println("\n\t   ----- Jets Application -----\n");
			// prompt user make an integer choice from 1 to 9
			do {
				printUserMenu();
				uChoice = scanner.nextInt();
				scanner.nextLine(); // help with console flow
			} while (uChoice < 1 || uChoice > 9);

			handleUserChoice(uChoice);
		} while (keepGoing);
	} // end of launch

	// display the menu for the user to choose
	private void printUserMenu() {
		System.out.println("\nPick a number for your choice: ");
		System.out.println("1  -  List The Fleet");
		System.out.println("2  -  Fly All Jets");
		System.out.println("3  -  Fastest Jet");
		System.out.println("4  -  Longest-Range Jet");
		System.out.println("5  -  Load Cargo Jets");
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
			loadAllCargoJets(airfield);
			break;
		case 6:
			System.out.println("\n -- Starting A Dog Fight --\n");
			startDogfight(airfield);

			break;
		case 7:
			System.out.println("\n -- Add Your Jet --\n");
			airfield.setListOfJets(addRemoveJet(airfield, choice));
			System.out.println(" --- Fleet List Updated --- \n");
			printFleet(airfield);
			break;
		case 8:
			System.out.println("\n -- Remove A Jet --\n");
			airfield.setListOfJets(addRemoveJet(airfield, choice));
			System.out.println(" --- Fleet List Updated --- \n");
			printFleet(airfield);
			break;
		case 9:
			System.out.println("\n -- Exiting The Program --\n");
			System.out.println("\nThank You, And Have A Nice Day!");
			keepGoing = false;
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
				String type = jetLine[4];
				jet = new JetImpl(jetName, speed, range, price, type); // use JetImpl since Jet is an abstract class
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
			System.out.println("\t|| Type of Jet: " + jet.getType());
			System.out.println("\t||");
			System.out.println("\t||-------------------------------------------");

		}
	}// end of printFleet()

	private void flyAllJets(Airfield airfield) {
		System.out.println("Here are the jet's details, and its remainig fligh time: \n");
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

	private void loadAllCargoJets(Airfield airfield) {
		// iterate thru the jets and search by type
		CargoPlane cC = new CargoPlane(); // instance of CargoPlane will let use its class attributes and CargoCarrier
											// Interface and it
		List<Jet> jetsList = airfield.getListOfJets();
		for (Jet jet : jetsList) {
			if (jet.getType().toLowerCase().equals("cargo carrier")) {
				cC.loadUp(); // as long as we find one, this will print and run
				System.out.println("Loading Up: " + jet);
			}
		}

	} // end of loadAllCargoJets
	
	private void startDogfight(Airfield airfield) {
		FighterJet fJ = new FighterJet();
		List<Jet> jetsList = airfield.getListOfJets();
		for (Jet jet : jetsList) {
			if (jet.getType().toLowerCase().equals("fighter")) {
				fJ.flightAttack(); // as long as we find one, this will print and run
				System.out.println("Dogfighting with: " + jet);
			}
		}
	} // end of startDogfight
	
	private List<Jet> addRemoveJet(Airfield airfield, int choice) {
		// store temp Jet attributes
		Jet tJet = null;
		List<Jet> jetList = airfield.getListOfJets();
		boolean isGoodType = false, isMatchingJet = false;
		int typeChoice = 0;
		String newType = "", renName = "";
		// find out what type of plane to add/remove
		switch (choice) {
		// Adding a Jet
		case 7:
			System.out.println("So, you want to add a Jet: \n");
			System.out.print("What is the name/model of the Jet? ");
			String newName = scanner.nextLine();
//			tJet.setModel(newName);
			System.out.print("What is the top Speed? ");
			double newSpeed = scanner.nextDouble();
//			tJet.setSpeed(newSpeed);
			System.out.print("What is the range? ");
			int newRange = scanner.nextInt();
//			tJet.setRange(newRange);
			System.out.println("What the jet's price/cost? ");
			long newPrice = scanner.nextLong();
			do {
				System.out.print("What is the Jet's Type? (Enter only: \"Fighter\" or \"Cargo Carrier\"): ");
				newType = scanner.nextLine();
				if (newType.equalsIgnoreCase("fighter")) {
					typeChoice = 1;
					isGoodType = true;
				} else if (newType.equalsIgnoreCase("cargo carrier")) {
					typeChoice = 2;
					isGoodType = true;
				} 				
			} while (!isGoodType);
			System.out.println("... Adding New Jet ....");
			if (typeChoice == 1) {
				tJet = new FighterJet(newName, newSpeed, newRange, newPrice, newType);
				jetList.add(tJet);
				return jetList;
			} else if (typeChoice == 2) {
				tJet = new CargoPlane(newName, newSpeed, newRange, newPrice, newType);
				jetList.add(tJet);
				return jetList;
			}			
			break;
		// Removing a Jet
		case 8:
			System.out.println("So, you want to remove a Jet: \n");
			System.out.println("Here's a lisf of the Fleet: ");
			printFleet(airfield);
			do {
				System.out.print("What is the name/model of the jet you would like to remove? ");	
				renName = scanner.nextLine();
				// iterate through the jet list
				for (Jet jet : jetList) {
					// check if name exists/matches
					renName = renName.toLowerCase();
					String tempCompRemJet = jet.getModel().toLowerCase();					
					if (renName.equals(tempCompRemJet)) {
						System.out.println("... Removing " + renName +  " Jet ....");
						jetList.remove(jet);
						isMatchingJet = true;
						return jetList;
						
					} 
				}
				System.out.println("Sorry, but that's not a valid name/model. Please try again!");
			} while (!isMatchingJet);
			
		break;
		} // end of switch (choice)
		return jetList;
	} // end of addRemoveJet

} // end of JetsApp class
