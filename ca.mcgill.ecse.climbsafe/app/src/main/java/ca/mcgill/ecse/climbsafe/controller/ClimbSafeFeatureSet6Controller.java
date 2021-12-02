package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.Assignment;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import ca.mcgill.ecse.climbsafe.model.BookedItem;
import ca.mcgill.ecse.climbsafe.model.BundleItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;

public class ClimbSafeFeatureSet6Controller {

  /** Attempts to delete a piece of equipment in the ClimbSafe system.
   * @author Onkeyachi Ezekwem, Youssof Mohamed, Yakir Bender
   * @param name
   * @throws InvalidInputException
  */
  public static void deleteEquipment(String name) throws InvalidInputException {
	  
	  Equipment equipment = null;
	  
	  //Initializes equipment for validation checks
	  if (BookableItem.getWithName(name) instanceof Equipment) {
	  equipment = (Equipment) Equipment.getWithName(name);
	  }
	  
	 
	  var error = "";
	  
	  //Input Validation
	  if (!Equipment.hasWithName(name)) {
		  error = "The piece of equipment does not exist in the system";
		  }
	  
	  if (equipment != null) {
		  List<BundleItem> bundles = equipment.getBundleItems(); 
		  if (bundles != null) {
			  if (equipmentIsInBundle(bundles,  equipment)){
				  error = "The piece of equipment is in a bundle and cannot be deleted";
				  }
			  }  
	 
	  }
	  
	  if (!error.isEmpty()) {
		  throw new InvalidInputException(error.trim());
		  }
	  
	  //Attempts to delete the piece of equipment from the system
	  try {
		  equipment.delete(); 
		  ClimbSafePersistence.save();
		  
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
			}
	  
  }

  /** Attempts to delete an equipment bundle in the ClimbSafe system.
   * @author Yakir Bender
   * @param name
  */
  public static void deleteEquipmentBundle(String name) {
	  
	  BookableItem bundle = BookableItem.getWithName(name);
	  
	  if (bundle != null && bundle instanceof EquipmentBundle) {
		  bundle.delete();
		  ClimbSafePersistence.save();
	  }
  }
  
  /** Checks whether a piece of equipment is part of a bundle
   * @author Onkeyachi Ezekwem, Youssof Mohamed, Yakir Bender
   * @param bundles
   * @param equipment
  */
  private static boolean equipmentIsInBundle(List<BundleItem> bundles, Equipment equipment) {
	  for (BundleItem bundle : bundles) {
		  if(bundle.getEquipment().equals(equipment) && bundle.getEquipment().getWeight() == equipment.getWeight() 
				  && bundle.getEquipment().getPricePerWeek() == equipment.getPricePerWeek() ) {
			  return true;
			  }
	  }
	  return false;
  }

  public static List<TOAssignment> getAssignments() {}

}
