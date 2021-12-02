package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;

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
      List<Integer> equipmentQuantities) throws InvalidInputException {}

  public static void updateEquipmentBundle(String oldName, String newName, int newDiscount,
      List<String> newEquipmentNames, List<Integer> newEquipmentQuantities)
      throws InvalidInputException {}

}
