package ca.mcgill.ecse.climbsafe.controller;

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
      throws InvalidInputException {}

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
      int newPricePerWeek) throws InvalidInputException {}

}
