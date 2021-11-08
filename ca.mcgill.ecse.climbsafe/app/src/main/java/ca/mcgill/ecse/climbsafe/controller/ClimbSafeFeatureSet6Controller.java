package ca.mcgill.ecse.climbsafe.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.Assignment;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import ca.mcgill.ecse.climbsafe.model.BookedItem;
import ca.mcgill.ecse.climbsafe.model.BundleItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;

public class ClimbSafeFeatureSet6Controller {
	
  private static ClimbSafe climbsafe = ClimbSafeApplication.getClimbSafe();

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

  /** Retrieves a list of assignments registered in the ClimbSafe system
   * @author Yakir Bender
  */
  public static List<TOAssignment> getAssignments() {
	  
	  List<Assignment> assignments = climbsafe.getAssignments();
	  List<TOAssignment> toAss = new ArrayList<TOAssignment>();
	  
	  //Iterates over each assignment
	  for (int i = 0; i < assignments.size(); i++) {
		  
		  toAss.add(i, new TOAssignment(null, null, null, null, null, 0, 0, 0, 0));
		  
		  //Copies member email & name (if they exist) to new assignment
		  if (assignments.get(i).getMember() != null) toAss.get(i).setMemberEmail(assignments.get(i).getMember().getEmail());
		  if (assignments.get(i).getMember() != null) toAss.get(i).setMemberName(assignments.get(i).getMember().getName());
		  
		  //Copies guide email & name (if they exist) to new assignment
		  if (assignments.get(i).getGuide() != null) toAss.get(i).setGuideEmail(assignments.get(i).getGuide().getEmail());
		  if (assignments.get(i).getGuide() != null) toAss.get(i).setGuideName(assignments.get(i).getGuide().getName());
		  
		  //adds hotel name (if member is staying at a hotel) to new assignment
		  if (assignments.get(i).hasHotel()) toAss.get(i).setHotelName(assignments.get(i).getHotel().getName());
		  
		  //Copies start week & end week to new assignment
		  toAss.get(i).setStartWeek(assignments.get(i).getStartWeek());
		  toAss.get(i).setEndWeek(assignments.get(i).getEndWeek());
		  
		  //Adds cost of guide to new assignment
		  if (!assignments.get(i).hasGuide()) {
			  toAss.get(i).setTotalCostForGuide(0);
		  }
		  else {
			  toAss.get(i).setTotalCostForGuide(assignments.get(i).getMember().getNrWeeks()*climbsafe.getPriceOfGuidePerWeek());
		  }
		  
		  //Adds cost of equipment to new assignment
		  if (!assignments.get(i).getMember().hasBookedItems()) {
			  toAss.get(i).setTotalCostForEquipment(0);
		  }
		  else {
			  toAss.get(i).setTotalCostForEquipment(addAllEquipmentPrices(assignments.get(i)));
		  }
		  
	  }
	  
	  return toAss;
  }
  
  /** Adds the price of all booked items in an assignment
   * @author Yakir Bender
   * @param assignment
  */
  private static int addAllEquipmentPrices(Assignment assignment) {

	  Equipment equipment;
	  EquipmentBundle bundle;
	  double sum = 0;
	  
	  //Iterates over each booked item in the assignment
	  for (BookedItem item : assignment.getMember().getBookedItems())
	  {
		  //If item is a piece of equipment, Adds the item's price to the sum
		  if (item.getItem() instanceof Equipment) {
			  equipment = (Equipment) item.getItem();
			  sum += equipment.getPricePerWeek() * item.getQuantity();
			  }
		  
		  //If item is a bundle, adds each bundle item to the sum individually
		  else {
			  bundle = (EquipmentBundle) item.getItem();
			  for (BundleItem bundleItem : bundle.getBundleItems()) {
				  equipment = bundleItem.getEquipment();
				  
				  //Apply discount if there is a guide registered for this assignment
				  if (assignment.hasGuide()) {
					  sum +=  equipment.getPricePerWeek() * item.getQuantity() * bundleItem.getQuantity() * (100.0 - bundle.getDiscount()) / 100.0;
					  }
				  else {
					  sum +=  equipment.getPricePerWeek() * item.getQuantity() * bundleItem.getQuantity();
					  }
				  }
			  }  	  
		  }
			  
	  return (int)(sum * assignment.getMember().getNrWeeks());
	  }
  
}
