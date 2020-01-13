package com.revature.projects.Project0.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.revature.projects.Project0.pojo.Car;

public class CarDAO{
	
	public void writeObject(Object obj) {
		String fileName = "cars.dat";
		try (FileOutputStream fos = new FileOutputStream(fileName);
				ObjectOutputStream oos = new ObjectOutputStream(fos)){
			oos.writeObject(obj);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Object readCars() {
		String filename = "cars.dat";
		Object obj = null;
		try (FileInputStream fis = new FileInputStream(filename);
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			obj = ois.readObject();
		}
		catch (Exception e) {
			writeObject(new ArrayList<Car>());
		}
		return obj;
	}
}
