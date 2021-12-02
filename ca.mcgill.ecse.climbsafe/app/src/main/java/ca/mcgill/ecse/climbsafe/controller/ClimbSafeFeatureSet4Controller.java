package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;

/**
 * Here are the controller features for addEquipment and updateEquipment
 * as specified by the administrator when defining the set of available equipment.
 * 
 * @author Rui Du
 *
 */

public class ClimbSafeFeatureSet4Controller {
  
  /**
   * The existing ClimbSafe object in the application
   */
  private static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();

  /**
   * addEquipment:
   * 
   * Controller feature to add an equipment to the list of available equipment.
   * 
   * @author Rui Du
   * 
   * @param name Name of the new equipment to add.
   * @param weight The weight of the new equipment. This should be larger than zero.
   * @param pricePerWeek The price per week of renting the equipment.
   * @throws InvalidInputException
   */
  public static void addEquipment(String name, int weight, int pricePerWeek)
    throws InvalidInputException {
    
    var error = "";
      
    if (name.isEmpty()|| name == null) {
      error = "The name must not be empty";
    }
    if (weight <= 0) {
      error = "The weight must be greater than 0";
    }
    if (pricePerWeek < 0) {
      error = "The price per week must be greater than or equal to 0";
    }
    BookableItem item = BookableItem.getWithName(name);
    if (item != null) {
      if (item instanceof Equipment) {
        error = "The piece of equipment already exists";
      }
      else {
        error = "The equipment bundle already exists";
      }
    }
      
    if (!error.isEmpty()) {
      throw new InvalidInputException(error.trim());
    }
      
    try {
      climbSafe.addEquipment(name, weight, pricePerWeek);
      ClimbSafePersistence.save();
    } catch (RuntimeException e) {
      error = e.getMessage();
      if (error.startsWith("Cannot create due to duplicate name.")) {
        error = "A bookable item with this name already exists. Please use a different name.";     
      }
      throw new InvalidInputException(error);
    }
      
  }

  /**
   * updateEquipment:
   * 
   * Controller feature to update the name, weight, or price per week
   * of an existing bookable equipment.
   * 
   * @author Rui Du
   * 
   * @param oldName The old name of the existing equipment. This is used to identify the equipment to update.
   * @param newName The new name to set the equipment to.
   * @param newWeight The new weight to set the equipment to.
   * @param newPricePerWeek The new price per week to set the equipment to.
   * @throws InvalidInputException
   */
  public static void updateEquipment(String oldName, String newName, int newWeight,
    int newPricePerWeek) throws InvalidInputException {
      
    var error = "";
      
    if (newName.isEmpty()|| newName == null) {
      error = "The name must not be empty";
    }
    if (oldName.isEmpty()|| oldName == null) {
      error = "Old equipment name cannot be empty or null.";
    }
    if (newWeight <= 0) {
      error = "The weight must be greater than 0";
    }
    if (newPricePerWeek < 0) {
      error = "The price per week must be greater than or equal to 0";
    }
     
    BookableItem existingBookableItem = BookableItem.getWithName(oldName);
    if (existingBookableItem == null || !(existingBookableItem instanceof Equipment)) {
      error = "The piece of equipment does not exist";
    }
    
    if (!newName.equals(oldName)) {
      BookableItem takenName = BookableItem.getWithName(newName);
      if (takenName != null) { // should be null if the name does not exist in the system
        if (takenName instanceof Equipment) {
          error = "The piece of equipment already exists";
        } else {
          error = "An equipment bundle with the same name already exists";
        }
      }
    }
    
    if (!error.isEmpty()) {
      throw new InvalidInputException(error.trim());
    }
    
    try {
      existingBookableItem.setName(newName);
      Equipment existingEquipment = (Equipment) existingBookableItem;
      existingEquipment.setWeight(newWeight);
      existingEquipment.setPricePerWeek(newPricePerWeek);
      
      ClimbSafePersistence.save();
    } catch (RuntimeException e) {
      error = e.getMessage();
      throw new InvalidInputException(error);
    }
    
      
  }
  
}