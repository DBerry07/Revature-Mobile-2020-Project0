package com.revature.projects.Project0.driver;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import com.revature.projects.Project0.dao.*;
import com.revature.projects.Project0.pojo.*;
import com.revature.projects.Project0.service.*;

public class Driver {

	private static Scanner scan = new Scanner(System.in);

	public static CarDAO cDAO = new CarDAO();
	public static UserDAO uDAO = new UserDAO();
	public static User usingUser;
	public static ArrayList<User> users;
	public static ArrayList<Car> cars;
	public static ArrayList<Car> bought;

	public static void main(String[] args) {
		String option = "";
		int selection = -1;
		users = (ArrayList<User>) uDAO.readUsers();
		cars = (ArrayList<Car>) cDAO.readCars();
		bought = (ArrayList<Car>) cDAO.readBoughtCars();
		if (users == null) {
			users = new ArrayList<User>();
		}
		if (cars == null) {
			cars = new ArrayList<Car>();
		}
		if (bought == null) {
			bought = new ArrayList<Car>();
		}

		do {
			System.out.println("\nWelcome to Car World!");
			System.out.println("Please select an option:");
			System.out.println("[1] Log In");
			System.out.println("[2] Log In as Admin");
			System.out.println("[3] Create new User Account");
			System.out.println("[0] Exit");
			option = scan.nextLine();

			try {
				selection = Integer.parseInt(option);
			} catch (NumberFormatException e) {
				System.out.println("NOT A VALID OPTION");
			}

			if (selection == 1) {
				userLogin();
			}

			if (selection == 2) {
				adminLogin();
			}

			else if (selection == 3) {
				makeNewUser();
			}

		} while (selection != 0);

		System.out.println("Exiting...");

	}

	public static void userLogin() {
		System.out.println("Enter username: ");
		String username = scan.nextLine();
		System.out.println("Enter Password: ");
		String password = scan.nextLine();
		boolean check = UserLoginService.checkUser(username, password);
		if (check == false) {
			System.out.println("\nInvalid Credentials");
			return;
		} else if (check == true) {
			System.out.println("\nCredentials Accepted!");
			for (User user : users) {
				if (user.getUsername().equals(username)) {
					usingUser = user;
				}
			}
			userMenu();
			return;
		}
	}

	public static void userMenu() {
		int selection = -1;
		do {

			System.out.println("\n[1] View Lot");
			System.out.println("[2] View Bought Cars");
			System.out.println("[3] Make an Offer on a Car");
			System.out.println("[0] Return to Menu");
			try {
				selection = Integer.parseInt(scan.nextLine());
			} catch (Exception e) {
				System.out.println("\nInvalid selection");
			}

			if (selection == 1) {
				viewCarLot();

			} else if (selection == 2) {
				viewBoughtCars();

			} else if (selection == 3) {
				makeOffer();
			}

		} while (selection != 0);
	}

	public static void viewBoughtCars() {
		int i = 1;
		ArrayList<Car> userBought = new ArrayList<Car>();
		for (Car each : bought) {
			if (each.getOwner().equals(usingUser.getUsername())) {
				userBought.add(each);
			}
		}
		System.out.println("\n");
		if (userBought.isEmpty()) {
			System.out.println("You haven't bought any cars!");
			return;
		}
		for (Car car : userBought) {
			System.out.println("[" + i + "]" + " " + car.getYear() + " " + car.getColour() + " " + car.getMake() + " "
					+ car.getModel() + " : " + car.getPayment());
			i++;
		}

		while (true) {
			Car car = null;
			System.out.println("Select car payment: ");
			int select = Integer.parseInt(scan.nextLine());
			int payoff = 0;
			if (select == 0) {
				return;
			}
			try {
				car = userBought.get(select - 1);
			} catch (IndexOutOfBoundsException e) {
				System.out.println("INVALID SELECTION");
				continue;
			}
			System.out
					.println("\n" + car.getYear() + " " + car.getColour() + " " + car.getMake() + " " + car.getModel());
			System.out.println("Payment remaining: $" + car.getPayment());
			System.out.println("Make a payment? (y/n)");
			if (scan.nextLine().toUpperCase().charAt(0) == 'Y') {
				System.out.println("Enter amount: ");
				try {
					payoff = Integer.parseInt(scan.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("NOT A VALID NUMBER");
					continue;
				}
				if (payoff > car.getPayment() || payoff <= 0) {
					System.out.println("INVALID AMOUNT");
					return;
				}
				car.setPayment(car.getPayment() - payoff);
				cDAO.writeBought(bought);
				System.out.println("Payment remaining: $" + car.getPayment());
				return;
			}
			return;
		}
	}

	public static void adminLogin() {
		System.out.println("Enter username: ");
		String username = scan.nextLine();
		System.out.println("Enter Password: ");
		String password = scan.nextLine();
		boolean check = UserLoginService.checkAdmin(username, password);
		if (check == false) {
			System.out.println("\nInvalid Credentials");
			return;
		} else if (check == true) {
			System.out.println("\nCredentials Accepted!");
			adminMenu();
			return;
		}
	}

	public static void adminMenu() {
		int selection = -1;
		String option = "y";
		do {
			System.out.println("\nPlease make a selection: ");
			System.out.println("[1] View Lot");
			System.out.println("[2] Add Car");
			System.out.println("[3] Remove Car");
			System.out.println("[4] View Offers");
			System.out.println("[5] View Payments");
			System.out.println("[0] Exit this Menu");

			try {
				selection = Integer.parseInt(scan.nextLine());
			} catch (Exception e) {
				System.out.println("\nInvalid option.");
			}
			if (selection == 1) {
				viewCarLot();
				/*
				 * System.out.println("\nEdit Car Listing? (y/n)"); option = scan.nextLine(); if
				 * (option.toUpperCase().charAt(0) == 'Y') { int sel = 0; do {
				 * System.out.println("\n[1] Add Car"); System.out.println("[2] Remove Car");
				 * System.out.println("[9] Exit this Menu");
				 * 
				 * option = scan.nextLine(); try { sel = Integer.parseInt(option); } catch
				 * (Exception e) { System.out.println("Invalid selection."); } if (sel == 1) {
				 * addCar(); } else if (sel == 2) { removeCar(); } } while (sel != 9); }
				 */
			}

			if (selection == 2) {
				addCar();
			}
			if (selection == 3) {
				removeCar();
			}
			if (selection == 4) {
				getOffersOnCar();
			}
			if (selection == 5) {
				getBoughtCars();
			}

		} while (selection != 0);
	}

	public static void makeNewUser() {
		boolean admin = false;
		System.out.println("Enter the desired username: ");
		String username = scan.nextLine();
		if (username.length() <= 3) {
			System.out.println("INVALID USERNAME");
			return;
		}
		System.out.println("Enter the desired password: ");
		String password = scan.nextLine();
		if (password.length() <= 3) {
			System.out.println("INVALID USERNAME");
			return;
		}
		System.out.println("Make this an admin account? (y/n)");
		String question = scan.nextLine();
		
		if (question.length() < 1) {
			System.out.println("INVALID SELECTION");
			return;
		}
		if (question.toUpperCase().charAt(0) == 'Y') {
			admin = true;
		}

		boolean res = UserLoginService.createUser(username, password, admin);
		if (res == true) {
			System.out.println("Success! New user '" + username + "' has been created!");
		} else {
			System.out.println("Failure! Invalid username!");
		}
	}

	public static void getOffersOnCar() {
		ArrayList<String> userOffer = new ArrayList<String>();
		int selectedOffer = 0;
		while (true) {
			if (cars.isEmpty()) {
				System.out.println("No cars in lot");
				return;
			}
			Set<String> keys = null;
			int carIndex;
			int i = 1;
			System.out.println("Enter the number of the desired car: ");
			carIndex = Integer.parseInt(scan.nextLine());
			if (carIndex == 0) {
				return;
			}
			Car car = cars.get(carIndex - 1);
			System.out.println("\n" + car);
			System.out.println("Offers: ");
			try {
				keys = car.getOffers().keySet();
				for (String key : keys) {
					System.out.println("[" + i + "] " + key + " : " + car.getOffers().get(key));
					userOffer.add(key);
					i++;
				}
			} catch (Exception e) {
				System.out.println("No offers");
				return;
			}

			while (true) {
				System.out.println("Choose an offer: ");
				selectedOffer = Integer.parseInt(scan.nextLine());
				if (selectedOffer == 0) {
					break;
				} else {
					System.out.println("What do you want to do with this offer?");
					System.out.println("[1] Reject");
					System.out.println("[2] Accept");
					int sel = Integer.parseInt(scan.nextLine());
					if (sel == 1) {
						car.getOffers().remove(userOffer.get(selectedOffer - 1));
						cDAO.writeCar(cars);
						System.out.println("Offer rejected.");
					} else if (sel == 2) {
						car.setOwner(userOffer.get(selectedOffer - 1),
								car.getOffers().get(userOffer.get(selectedOffer - 1)));
						bought.add(car);
						cDAO.writeBought(bought);
						cars.remove(carIndex - 1);
						cDAO.writeCar(cars);
						System.out.println("Car sold.");
						return;
					}
				}
			}
		}
	}

	public static void addCar() {
		while (true) {
			String make, model, colour = null;
			int price, year = 0;
			System.out.println("Enter car make: ");
			make = scan.nextLine();
			if (make == "0") {
				return;
			}
			System.out.println("Enter car model: ");
			model = scan.nextLine();
			if (model == "0") {
				return;
			}
			System.out.println("Enter car year: ");
			try {
				year = Integer.parseInt(scan.nextLine());
			} catch (Exception e) {
				System.out.println("Invalid model year.");
				continue;
			}
			if (year == 0) {
				return;
			}
			System.out.println("Enter car colour: ");
			colour = scan.nextLine();
			if (colour == "0") {
				return;
			}
			System.out.println("Enter price: ");
			try {
				price = Integer.parseInt(scan.nextLine());
			} catch (Exception e) {
				System.out.println("Invalid price.");
				return;
			}
			if (price == 0) {
				return;
			}

			System.out.println("\nMake: " + make);
			System.out.println("Model: " + model);
			System.out.println("Colour: " + colour);
			System.out.println("Price: " + price);
			System.out.println("Add this car? (y/n)");
			String option = scan.nextLine();
			if (option.toUpperCase().charAt(0) == 'Y') {
				cars.add(new Car(make, model, year, colour, price));
				cDAO.writeCar(cars);
				return;
			}
		}
	}

	public static void removeCar() {
		int select = -1;
		do {
			String input = null;

			System.out.println("Enter the number of the car you wish to remove from the lot:");
			input = scan.nextLine();
			try {
				select = Integer.parseInt(input);
			} catch (Exception e) {
				System.out.println("Invalid selection.");
				continue;
			}

			if (select == 0) {
				return;
			}

			try {
				cars.remove(select - 1);
				cDAO.writeCar(cars);
			} catch (Exception e) {
				System.out.println("Invalid selection.");
				continue;
			}

			System.out.println("Car #" + select + " removed.");
			return;
		} while (select != 0);

	}

	public static void viewCarLot() {
		int i = 1;
		if (cars.isEmpty()) {
			System.out.println("NO CARS IN AVAILABLE");
			return;
		}
		for (Car car : cars) {
			System.out.println("[" + i + "] " + car);
			i++;
		}
	}

	public static void makeOffer() {
		int select = -1;
		if (cars.isEmpty()) {
			System.out.println("No cars in lot!");
			return;
		}
		do {
			int offer = 0;
			System.out.println("Enter the number of the desired car:");
			try {
				select = Integer.parseInt(scan.nextLine());
			} catch (Exception e) {
				System.out.println("Invalid selection.");
				continue;
			}
			if (select == 0) {
				return;
			}
			Car car = cars.get(select - 1);
			System.out.println(car);
			System.out.println("Is this the car you want? (y/n)");
			String option = scan.nextLine();
			if (option.toUpperCase().charAt(0) == 'Y') {
				System.out.println("What is your offer? Enter only digits");
				try {
					offer = Integer.parseInt(scan.nextLine());
				} catch (Exception e) {
					System.out.println("Invalid offer");
					return;
				}
				System.out.println("Is this acceptable?");
				option = scan.nextLine();
				if (option.toUpperCase().charAt(0) == 'Y') {
					car.addOffer(usingUser.getUsername(), offer);
					cDAO.writeCar(cars);
					System.out.println("Your offer has been logged.");
				}
			}
		} while (select != 0);
	}

	public static void getBoughtCars() {
		try {
			for (Car each : bought) {
				System.out.println(each.getYear() + " " + each.getColour() + " " + each.getMake() + " "
						+ each.getModel() + " - " + each.getOwner() + " " + each.getPayment());
			}
		} catch (NullPointerException e) {
			System.out.println("No one has bought a car yet!");
			return;
		}
	}
}
