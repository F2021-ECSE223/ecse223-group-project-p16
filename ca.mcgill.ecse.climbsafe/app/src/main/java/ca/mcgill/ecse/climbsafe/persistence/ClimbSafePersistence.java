package ca.mcgill.ecse.climbsafe.persistence;

import java.sql.Date;
import java.time.LocalDate;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;

public class ClimbSafePersistence {
	
	private static String filename = "ClimbSafeDemo.data";
	
	public static void setFilename(String filename) {
		ClimbSafePersistence.filename = filename;
	}
	
	public static void save() {
		PersistenceObjectStream.setFilename(filename);
		save(ClimbSafeApplication.getClimbSafe());
	}
	
	public static void save(ClimbSafe climbsafe) {
		PersistenceObjectStream.setFilename(filename);
		PersistenceObjectStream.serialize(climbsafe);
	}
	
	public static ClimbSafe load() {
		PersistenceObjectStream.setFilename(filename);
		var climbsafe = (ClimbSafe) PersistenceObjectStream.deserialize();
		//model cannot be loaded - create empty ClimbSafe
		if (climbsafe == null) {
			climbsafe = new ClimbSafe(Date.valueOf(LocalDate.now()),0, 0);
		} else {
			climbsafe.reinitialize();
		}
		return climbsafe;
	}

}
