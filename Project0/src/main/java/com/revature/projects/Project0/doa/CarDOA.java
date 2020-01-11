package com.revature.projects.Project0.doa;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CarDOA{
	
	public void writeObject(Object obj) {
		String fileName = "Cars.dat";
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

	public Object readObject(String fileName) {
		Object obj = null;
		try (FileInputStream fis = new FileInputStream(fileName);
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			obj = ois.readObject();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
}
