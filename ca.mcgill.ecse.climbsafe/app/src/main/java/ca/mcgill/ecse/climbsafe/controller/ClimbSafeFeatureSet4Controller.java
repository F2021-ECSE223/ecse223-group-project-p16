package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;

public class ClimbSafeFeatureSet4Controller {
	
  private static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();

  public static void addEquipment(String name, int weight, int pricePerWeek)
    throws InvalidInputException {
	
	var error = "";
	  
	if (name.isEmpty()|| name == null) {
	  error = "Equipment name cannot be empty or null.";
	}
	if (weight <= 0) {
	  error = "Equipment weight must be larger than 0.";
	}
	if (pricePerWeek < 0) {
	  error = "Equipment price must be greater than or equal to zero.";
	}
	  
	if (!error.isEmpty()) {
	  throw new InvalidInputException(error.trim());
	}
	  
    try {
      climbSafe.addEquipment(name, weight, pricePerWeek);
    } catch (RuntimeException e) {
      error = e.getMessage();
      if (error.startsWith("Cannot create due to duplicate name.")) {
        error = "A bookable item with this name already exists. Please use a different name.";     
      }
      throw new InvalidInputException(error);
    }
	  
  }

  public static void updateEquipment(String oldName, String newName, int newWeight,
    int newPricePerWeek) throws InvalidInputException {
	  
	var error = "";
      
	if (newName.isEmpty()|| newName == null) {
	  error = "New equipment name cannot be empty or null.";
	}
	if (oldName.isEmpty()|| oldName == null) {
	  error = "Old equipment name cannot be empty or null.";
	}
	if (newWeight <= 0) {
	  error = "Equipment weight must be larger than 0.";
	}
	if (newPricePerWeek < 0) {
	  error = "Equipment price must be greater than or equal to zero.";
	}
	  
	BookableItem existingBookableItem = BookableItem.getWithName(oldName);
	Equipment existingEquipment = null;
	
	if (existingBookableItem != null && existingBookableItem instanceof Equipment) {
	  existingBookableItem.setName(newName);
	  existingEquipment = (Equipment) existingBookableItem;
	} else {
	  error = "No existing equipment with name " + oldName + " found.";
	}
	
	if (!error.isEmpty()) {
	  throw new InvalidInputException(error.trim());
	}
	
	try {
      existingEquipment.setWeight(newWeight);
      existingEquipment.setPricePerWeek(newPricePerWeek);
    } catch (RuntimeException e) {
      error = e.getMessage();
      throw new InvalidInputException(error);
    }
	
	  
  }
  
}
