package com.revature.projects.Project0.util;

import java.awt.List;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.revature.projects.Project0.driver.Driver;
import com.revature.projects.Project0.pojo.Car;
import com.revature.projects.Project0.pojo.User;

public class loggers {
	
	static final Logger log = Logger.getLogger(Driver.class);
	
	public static void logUserArray(ArrayList<User> users) {
		log.debug("==USERS==");
		log.debug(users.toString());
		for (User each : users) {
			log.debug(each.getUsername() + " " + each.getAdmin() + " " + each.getPassword());
		}
	}
	public static void logCarArray(ArrayList<Car> cars) {
		log.debug("==LOT CARS==");
		for (Car car : cars) {
			log.debug(car.getYear() + " " + car.getColour() + " " + car.getMake() + " " + car.getModel() + " "
					+ car.getPrice());
			log.debug("Owner: " + car.getOwner() + ", Payment: " + car.getPayment());
		}
	}
	public static void logBoughtArray(ArrayList<Car> bought) {
		log.debug("==BOUGHT CARS==");
		for (Car car : bought) {
			log.debug(car.getYear() + " " + car.getColour() + " " + car.getMake() + " " + car.getModel() + " "
					+ car.getPrice());
			log.debug("Owner: " + car.getOwner() + ", Payment: " + car.getPayment());
		}
	}
}
