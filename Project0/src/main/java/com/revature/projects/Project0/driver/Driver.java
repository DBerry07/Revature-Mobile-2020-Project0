package com.revature.projects.Project0.driver;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.apache.log4j.PropertyConfigurator;

import com.revature.projects.Project0.dao.BoughtDAO;
import com.revature.projects.Project0.dao.CarDAO;
import com.revature.projects.Project0.dao.OfferDAO;
import com.revature.projects.Project0.dao.UserDAO;
import com.revature.projects.Project0.pojo.Car;
import com.revature.projects.Project0.pojo.User;
import com.revature.projects.Project0.service.UserLoginService;
import com.revature.projects.Project0.util.*;

public class Driver {

	private static Scanner scan = new Scanner(System.in);
	public static CarDAO cDAO = new CarDAO();
	public static UserDAO uDAO = new UserDAO();
	public static OfferDAO oDAO = new OfferDAO();
	public static String usingUsername;
	public static ArrayList<User> users;
	public static ArrayList<Car> cars;
	public static ArrayList<Car> bought;
	public static String uFilename = "users.dat";
	public static String cFilename = "cars.dat";
	public static String bFilename = "bought.dat";

	public static void main(String[] args) {
		PropertyConfigurator.configure("./src/main/resources/log4j.properties");
		String option = "";
		int selection = -1;
		try {
			cars = cDAO.readAll();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		{
			//loggers.logUserArray(users);
			//loggers.logCarArray(cars);
			//loggers.logBoughtArray(bought);
		}

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
		boolean check = uDAO.checkUser(username, password);
		if (check == false) {
			System.out.println("\nInvalid Credentials");
			return;
		} else if (check == true) {
			System.out.println("\nCredentials Accepted!");
			usingUsername = username;
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
		userBought = cDAO.viewBoughtCars(usingUsername);
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
			int select = -1;
			System.out.println("Select car payment: ");
			try {
				select = Integer.parseInt(scan.nextLine());
			}
			catch (NumberFormatException e) {
				System.out.println("Invalid selection");
				continue;
			}
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
				cDAO.updatePayment(car.getCarID(), car.getPayment() - payoff);
				//loggers.logBoughtArray(bought);
				System.out.println("Payment remaining: $" + (car.getPayment() - payoff));
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
		boolean check = uDAO.checkAdmin(username, password);
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
				continue;
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

		try {
			uDAO.insertUser(username, password, admin);
		} catch (SQLException e) {
			System.out.println("Error: Invalid username or password");
			return;
		}
		System.out.println("User created!");
		//users.add(new User(username, password, admin));
	}

	public static void getOffersOnCar() {
		Map<String, Integer> offers = null;
		int selectedOffer = 0;
		while (true) {
			if (cars.isEmpty()) {
				System.out.println("No cars in lot");
				return;
			}
			int carIndex;
			int i = 1;
			System.out.println("Enter the number of the desired car: ");
			carIndex = Integer.parseInt(scan.nextLine());
			if (carIndex <= 0) {
				return;
			}
			if (carIndex > cars.size()) {
				System.out.println("Invalid selection.");
				return;
			}
			Car car = cars.get(carIndex - 1);
			try {
				offers = oDAO.getCarOffers(car.getCarID());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("\n" + car);
			if (offers.isEmpty()) {
				System.out.println("No offers on this car.");
				return;
			}
			System.out.println("Offers: ");
			
			for (String key : offers.keySet()) {
				System.out.println(key + " : " + offers.get(key));
			}

			while (true) {
				System.out.println("Choose a username: ");
				String selectedUser = scan.nextLine();
				if (selectedUser == "0") {
					break;
				} else {
					System.out.println("What do you want to do with this offer?");
					System.out.println("[1] Accept");
					System.out.println("[2] Reject");
					int sel = Integer.parseInt(scan.nextLine());
					if (sel == 1) {
						try {
							oDAO.acceptOffer(car.getCarID(), selectedUser);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							cars = cDAO.readAll();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//loggers.logCarArray(cars);
						System.out.println("Car sold.");
						return;
						
					} else if (sel == 2) {
						try {
							oDAO.rejectOffer(car.getCarID(), selectedUser);
						} catch (SQLException e) {
							System.out.println("Error: Invalid Username");
							return;
						}
						//loggers.logBoughtArray(bought);
						//loggers.logCarArray(cars);
						System.out.println("Offer rejected.");
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
				cDAO.insertCar(make, model, year, colour, price);
				try {
					cars = cDAO.readAll();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
				Car car = cars.get(select - 1);
				cDAO.removeCar(car);
				cars = cDAO.readAll();
				//loggers.logCarArray(cars);
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
			if (car.getOwner() == null) {
				System.out.println("[" + cars.indexOf(car) + 1 + "] " + car);
				i++;
			}
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
			if (select <= 0) {
				return;
			}
			else if (select > cars.size()) {
				System.out.println("Invalid selection");
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
					oDAO.insertOffer(car.getCarID(), usingUsername, offer);
					//loggers.logCarArray(cars);
					System.out.println("Your offer has been logged.");
				}
			}
		} while (select != 0);
	}

	public static void getBoughtCars() {
		
	}
}
