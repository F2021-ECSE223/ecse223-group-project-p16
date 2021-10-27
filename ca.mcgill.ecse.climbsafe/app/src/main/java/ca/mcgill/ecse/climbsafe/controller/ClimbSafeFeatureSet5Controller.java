package ca.mcgill.ecse.climbsafe.controller;

import java.util.*;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;

import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.BundleItem;

public class ClimbSafeFeatureSet5Controller {
  
  private static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
  
  /**
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
        error += "Name cannot be empty";
      }
      
      // Check the validity of the discount input
      if (discount < 0 | discount > 100) {
        error += "Discount must be in range 0 to 100, as it is a percentage.";
      }
      
      // Check the validity of the equipmentNames
      List<String> uniqueListOfNames = new ArrayList<>(new HashSet<>(equipmentNames));
      if (uniqueListOfNames.size() < 2) {
        error += "Bundles must have at least 2 different equipments in them.";
      }
      
      // Check the validity of the equipmentQuantities
      for (int equipmentQuantity : equipmentQuantities) {
        if (equipmentQuantity <= 0) {
          error += "Bundle Items must have greater quantity thwan zero.";
        }
      }
      
      // -=-=-=-=-=- Attempting to Create the Bundle with the Desired Bundle Items Inside -=-=-=-=-=-
      try {
        
        // Create a bundle
        EquipmentBundle equipmentBundle = climbSafe.addBundle(name, discount);
        
        // Iterate through all the names and quantities of equipments to add to the bundle
        for (int i = 1; i <= equipmentNames.size(); i++) {
          
          // Find the existing equipment with the same name as the passed name
          Equipment matchedEquipment = null;
          for (Equipment equipment: climbSafe.getEquipment()) {
            if (equipment.getName() == equipmentNames.get(i)){
              matchedEquipment = equipment;
            }
          }
          
          // Attempt to add the equipment to the bundle
          climbSafe.addBundleItem(equipmentQuantities.get(i), equipmentBundle, matchedEquipment);
        }
      } 
      catch (RuntimeException e) {
        throw new InvalidInputException(e.getMessage());
    }
  }
  
  
  
  /**
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
        error += "oldName cannot be empty";
    }
    
    // Check newName is not empty
    if (newName == "") {
        error += "newName cannot be empty";
    }
    
    // Check the validity of the discount input
    if (newDiscount < 0 | newDiscount > 100) {
      error += "Discount must be in range 0 to 100, as it is a percentage.";
    }
    
    // Check the validity of the equipmentNames
    List<String> uniqueListOfNames = new ArrayList<>(new HashSet<>(newEquipmentNames));
    if (uniqueListOfNames.size() < 2) {
      error += "Bundles must have at least 2 different equipments in them.";
    }
    
    // Check the validity of the equipmentQuantities
    for (int equipmentQuantity : newEquipmentQuantities) {
      if (equipmentQuantity <= 0) {
        error += "Bundle Items must have greater quantity thwan zero.";
      }
    }
    
    // -=-=-=-=-=- Attempting to Update the Existing Bundle with the New Parameters -=-=-=-=-=- 
    try {
      
      EquipmentBundle thisBundle = null;
      for (EquipmentBundle bundle : climbSafe.getBundles()) {
        if (bundle.getName() == oldName) {
          thisBundle = bundle;
        }
      }
      
      // Remove all the current bundle items from the bundle.
      for (BundleItem bundleItem : thisBundle.getBundleItems()) {
        thisBundle.removeBundleItem(bundleItem);
      }

      // Add all the new bundle items to the bundle, as we did above.
      // Iterate through all the names and quantities of equipments to add to the bundle
      for (int i = 1; i <= newEquipmentNames.size(); i++) {
        
        // Find the existing equipment with the same name as the passed name
        Equipment matchedEquipment = null;
        for (Equipment equipment: climbSafe.getEquipment()) {
          if (equipment.getName() == newEquipmentNames.get(i)){
            matchedEquipment = equipment;
          }
        }
        
        // Attempt to add the equipment to the bundle
        climbSafe.addBundleItem(newEquipmentQuantities.get(i), thisBundle, matchedEquipment);
      }
      
      // Change bundle name      
      thisBundle.setName(newName);
      
    }
    catch (RuntimeException e) {
      throw new InvalidInputException(e.getMessage());
    }
  }
}
