package ca.mcgill.ecse.climbsafe.controller;

import java.util.*;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;

import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.BundleItem;
import ca.mcgill.ecse.climbsafe.model.BookableItem;

public class ClimbSafeFeatureSet5Controller {
  
  private static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
  
  /**
   * This is the controller for the AddEquipmentBundle feature. It includes all necessary input validation to pass all tests.
   * @author Samuel Valentine
   * @param name
   * @param discount
   * @param equipmentNames
   * @param equipmentQuantities
   * @throws InvalidInputException
   */
  public static void addEquipmentBundle(String name, int discount, List<String> equipmentNames,
      List<Integer> equipmentQuantities) throws InvalidInputException {
    
      var error = "";
      
      // -=-=-=-=-=- Input Validation -=-=-=-=-
      
      // Check if the name is empty
      if(name == "") {
        error = "Equipment bundle name cannot be empty";
      }
      
      // Check the validity of the discount input
      if (discount < 0) {
        error = "Discount must be at least 0";
      }
      if (discount > 100) {
        error = "Discount must be no more than 100";
      }
      
      // Check the validity of inputed equipment names vs existing equipment
      for (String equipmentName : equipmentNames) {
        if (Equipment.getWithName(equipmentName) == null) {
          error = "Equipment " + equipmentName + " does not exist";
        }
      }
      
      // Check the validity of the equipmentNames
      List<String> uniqueListOfNames = new ArrayList<>(new HashSet<>(equipmentNames));
      if (uniqueListOfNames.size() < 2) {
        error = "Equipment bundle must contain at least two distinct types of equipment";
      }
      
      // Check the validity of the equipmentQuantities
      for (int equipmentQuantity : equipmentQuantities) {
        if (equipmentQuantity < 1) {
          error = "Each bundle item must have quantity greater than or equal to 1";
        }
      }
      
      // Check if the name already exists
      if (EquipmentBundle.getWithName(name) != null) {
        error = "A bookable item called " + name + " already exists";
      }
      
      // Raise an error if any of the validation checks were caught
      if (!error.isEmpty()) {
        throw new InvalidInputException(error.trim());
      }
      
      // -=-=-=-=-=- Attempting to Create the Bundle with the Desired Bundle Items Inside -=-=-=-=-=-
      try {
        
        // Create a bundle
        EquipmentBundle equipmentBundle = climbSafe.addBundle(name, discount);
        
        // Iterate through all the names and quantities of equipments to add to the bundle
        for (int i = 0; i < equipmentNames.size(); i++) {
          
          // Find the existing equipment with the same name as the passed name
//          Equipment matchedEquipment = null;
//          for (Equipment equipment: climbSafe.getEquipment()) {
//            if (equipment.getName().toString().equals( equipmentNames.get(i).toString())){
//              matchedEquipment = equipment;
//              break;
//            }
//          } 
          
          Equipment matchedEquipment = (Equipment) Equipment.getWithName(equipmentNames.get(i).toString());
          
          // Attempt to add the equipment to the bundle
          BundleItem bundleItem = climbSafe.addBundleItem(equipmentQuantities.get(i), equipmentBundle, matchedEquipment);
          equipmentBundle.addBundleItem(bundleItem);
          
          ClimbSafePersistence.save();
        }
      } 
      catch (RuntimeException e) {
        throw new InvalidInputException(e.getMessage());
    }
  }
 
  
  /**
   * This is the controller for the UpdateEquipmentBundle feature. It includes all necessary input validation to pass all tests.
   * @author Samuel Valentine
   * @param oldName
   * @param newName
   * @param newDiscount
   * @param newEquipmentNames
   * @param newEquipmentQuantities
   * @throws InvalidInputException
   */
  public static void updateEquipmentBundle(String oldName, String newName, int newDiscount,
      List<String> newEquipmentNames, List<Integer> newEquipmentQuantities)
      throws InvalidInputException 
  {
    
    var error = "";
    
    // -=-=-=-=-=- Input Validation -=-=-=-=-=-
    
    // Check oldName is not empty
    if (oldName == "") {
        error = "Equipment bundle name cannot be empty";
    }
    
    // Check newName is not empty
    if (newName == "") {
        error = "Equipment bundle name cannot be empty";
    }
    
    // Check the validity of the discount input
    if (newDiscount < 0) {
      error = "Discount must be at least 0";
    }
    if (newDiscount > 100) {
      error = "Discount must be no more than 100";
    }
    
    // Check the validity of inputed equipment names vs existing equipment
    for (String equipmentName : newEquipmentNames) {
      if (Equipment.getWithName(equipmentName) == null) {
        error = "Equipment " + equipmentName + " does not exist";
      }
    }
    
    // Check the validity of the equipmentNames
    List<String> uniqueListOfNames = new ArrayList<>(new HashSet<>(newEquipmentNames));
    if (uniqueListOfNames.size() < 2) {
      error = "Equipment bundle must contain at least two distinct types of equipment";
    }
    
    // Check the validity of the equipmentQuantities
    for (int equipmentQuantity : newEquipmentQuantities) {
      if (equipmentQuantity < 1) {
        error = "Each bundle item must have quantity greater than or equal to 1";
      }
    }
    
    // Check if equipment old bundle exists
    EquipmentBundle b = null;
    for (EquipmentBundle bundle : climbSafe.getBundles()) {
      if (bundle.getName().equals(oldName)) {
        b = bundle;
        break;
      }
    }
    if (b == null) {
      error = "Equipment bundle " + oldName + " does not exist";
    }
      
    // Check if the new name already exists in the system
    if ((EquipmentBundle.getWithName(newName) != null) && !(newName.toString().strip().equals(oldName.toString().strip()))) {
        error = "A bookable item called " + newName + " already exists";
    }
    
    // Raise an error if any of the validation checks were caught
    if (!error.isEmpty()) {
      System.out.println(error);
      throw new InvalidInputException(error.trim());
    }
    
    
    // -=-=-=-=-=- Attempting to Update the Existing Bundle with the New Parameters -=-=-=-=-=- 
    try {
      
      // Find the old bundle
      BookableItem thisBundle = EquipmentBundle.getWithName(oldName);
      
      // Delete the old bundle
      thisBundle.delete();
      
      // Create the new bundle
      addEquipmentBundle(newName, newDiscount, newEquipmentNames, newEquipmentQuantities);
      
      ClimbSafePersistence.save();
      
    }
    catch (RuntimeException e) {
      throw new InvalidInputException(e.getMessage());
    }
  }
}
