package com.revature.projects.Project0.driver;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.projects.Project0.dao.*;
import com.revature.projects.Project0.pojo.*;
import com.revature.projects.Project0.service.*;

public class Driver {

	private static Scanner scan = new Scanner(System.in);

	public static CarDAO cDAO = new CarDAO();
	public static UserDAO uDAO = new UserDAO();
	public static User usingUser;

	public static void main(String[] args) {
		String option = "";
		int selection = 0;

		do {
			System.out.println("\nWelcome to Car World!");
			System.out.println("Please select an option:");
			System.out.println("[1] Log In");
			System.out.println("[2] Log In as Admin");
			System.out.println("[3] Create new User Account");
			System.out.println("[9] Exit\n");
			option = scan.nextLine();

			try {
				selection = Integer.parseInt(option);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("\nNot an option.");
			}

			if (selection == 1) {
				System.out.println("Enter username: ");
				String username = scan.nextLine();
				System.out.println("Enter Password: ");
				String password = scan.nextLine();
				boolean check = UserLoginService.checkUser(username, password);
				if (check == false) {
					System.out.println("\nInvalid Credentials");
					continue;
				} else if (check == true) {
					System.out.println("\nCredentials Accepted!");
					usingUser = UserLoginService.getUser(username, password);
					userLogin();
					continue;
				}
			}

			if (selection == 2) {
				System.out.println("Enter username: ");
				String username = scan.nextLine();
				System.out.println("Enter Password: ");
				String password = scan.nextLine();
				boolean check = UserLoginService.checkAdmin(username, password);
				if (check == false) {
					System.out.println("\nInvalid Credentials");
					continue;
				} else if (check == true) {
					System.out.println("\nCredentials Accepted!");
					adminLogin();
					continue;
				}
			}

			else if (selection == 3) {
				boolean admin = false;
				System.out.println("Enter the desired username: ");
				String username = scan.nextLine();
				System.out.println("Enter the desired password: ");
				String password = scan.nextLine();
				System.out.println("Make this an admin account? (y/n)");
				String question = scan.nextLine();

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

		} while (selection != 9);

		System.out.println("Exiting...");

	}

	public static void userLogin() {
		int selection = 0;
		do {

			System.out.println("\n[1] View Lot");
			System.out.println("[2] View Bought Cars");
			System.out.println("[3] Make an Offer on a Car");
			System.out.println("[9] Return to Menu");
			try {
				selection = Integer.parseInt(scan.nextLine());
			} catch (Exception e) {
				System.out.println("\nInvalid selection");
			}

			if (selection == 1) {
				ArrayList<Car> cars = null;
				try {
					cars = (ArrayList<Car>) cDAO.readCars();
					int i = 1;
					for (Car car : cars) {
						System.out.println("[" + i + "] " + car);
						i++;
					}
				}
				catch (NullPointerException e) {
					System.out.println("No cars in lot!");
				}
				
				
			} else if (selection == 2) {
				int i = 1;
				try {
					for (Car carBought : usingUser.getCars()) {
						System.out.println("[" + i + "]" + " " + carBought);
					}
				}
				catch (NullPointerException e){
					System.out.println("You haven't bought any cars!");
				}
			} else if (selection == 3) {
				int carIndex = -1;
				int offer = 0;
				System.out.println("Enter the number of the desired car:");
				try {
					carIndex = Integer.parseInt(scan.nextLine());
				}
				catch (Exception e) {
					System.out.println("Invalid selection");
					continue;
				}
				ArrayList<Car> cars = (ArrayList<Car>) cDAO.readCars();
				Car car = cars.get(carIndex);
				System.out.println(car);
				System.out.println("Is this the car you want? (y/n)");
				String option = scan.nextLine();
				if (option.toUpperCase().charAt(0) == 'Y') {
					System.out.println("What is your offer? Enter only digits");
					try {
						offer = Integer.parseInt(scan.nextLine());
					}
					catch (Exception e) {
						System.out.println("Invalid offer");
						continue;
					}
					int difference = offer - car.getPrice();
					System.out.println("The difference between the MSRP and your offer price is " + difference);
					System.out.println("Is this acceptable?");
					option = scan.nextLine();
					if (option.toUpperCase().charAt(0) == 'Y') {
						car.addOffer(usingUser, offer);
						System.out.println("Your offer has been logged.");
					}
					else {
						continue;
					}
				}
			}

		} while (selection != 9);
	}

	public static void adminLogin() {
		int selection = 0;
		String option = "y";
		do {
			System.out.println("\nPlease make a selection: ");
			System.out.println("[1] View Lot");
			System.out.println("[2] Add Car");
			System.out.println("[3] Remove Car");
			System.out.println("[4] View Offers");
			System.out.println("[5] View Payments");
			System.out.println("[9] Exit this Menu");

			try {
				selection = Integer.parseInt(scan.nextLine());
			} catch (Exception e) {
				System.out.println("\nInvalid option.");
			}
			if (selection == 1) {
				ArrayList<Car> cars = (ArrayList<Car>) cDAO.readCars();

				int i = 1;
				try {
					for (Car car : cars) {
						System.out.println("[" + i + "] " + car);
						i++;
					}
				} catch (Exception e) {
					System.out.println("Car lot is empty!");
				}
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
			

		} while (selection != 9);
	}

	public static void getOffersOnCar() {
		int carIndex, option = 0;
		int i = 1;
		System.out.println("Enter the number of the desired car: ");
		carIndex = Integer.parseInt(scan.nextLine());
		ArrayList<Car> cars = (ArrayList<Car>) cDAO.readCars();
		Car car = cars.get(carIndex);
		System.out.println("Offers: ");
		for (User key : car.getBids().keySet()) {
			System.out.println("[" + i + "] " + key.toString() + " : " + car.getBids().get(key) + " >> " + (car.getBids().get(key) - car.getPrice()));
		}
		System.out.println("Accept which offer?");
		System.out.println("Enter -1 to exit this menu");
		option = Integer.parseInt(scan.nextLine());
		if (option == -1) {
			return;
		}
		else {
			//TODO write logic for accepting offer
		}
	}
	
	public static void addCar() {
		String make, model, colour = null;
		int price, year = 0;
		System.out.println("Enter car make: ");
		make = scan.nextLine();
		System.out.println("Enter car model: ");
		model = scan.nextLine();
		System.out.println("Enter car year: ");
		try {
			year = Integer.parseInt(scan.nextLine());
		} catch (Exception e) {
			System.out.println("Invalid model year.");
			return;
		}
		System.out.println("Enter car colour: ");
		colour = scan.nextLine();
		System.out.println("Enter price: ");
		try {
			price = Integer.parseInt(scan.nextLine());
		}
		catch (Exception e) {
			System.out.println("Invalid price.");
			return;
		}
		
		System.out.println("\nMake: " + make);
		System.out.println("Model: " + model);
		System.out.println("Colour: " + colour);
		System.out.println("Price: " + price);
		System.out.println("Add this car? (y/n)");
		String option = scan.nextLine();
		if (option.toUpperCase().charAt(0) == 'Y') {
			ArrayList<Car> cars = (ArrayList<Car>) cDAO.readCars();
			if (cars != null) {
				cars.add(new Car(make, model, year, colour, price));
			}
			else {
				cars = new ArrayList<Car>();
				cars.add(new Car(make, model, year, colour, price));
			}
			cDAO.writeObject(cars);
		}
		return;
	}

	public static void removeCar() {
		String input = null;
		int index = -1;

		System.out.println("Enter the number of the car you wish to remove from the lot:");
		input = scan.nextLine();
		try {
			index = Integer.parseInt(input);
		} catch (Exception e) {
			System.out.println("Invalid selection.");
			return;
		}

		ArrayList<Car> cars = (ArrayList<Car>) cDAO.readCars();
		try {
			cars.remove(index);
		} catch (Exception e) {
			System.out.println("Invalid selection.");
			return;
		}

		cDAO.writeObject(cars);
		System.out.println("Car #" + index + " removed.");
		return;

	}

}
