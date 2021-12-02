package ca.mcgill.ecse.climbsafe.controller;

public class ClimbSafeFeatureSet3Controller {
	
  private static ClimbSafe climbsafe = ClimbSafeApplication.getClimbSafe();

  /** Attempts to register a guide in the ClimbSafe system.
   * @author Yakir Bender
   * @param email
   * @param password
   * @param name
   * @param emergencyContact
   * @throws InvalidInputException
  */
  public static void registerGuide(String email, String password, String name,
      String emergencyContact) throws InvalidInputException {}

  public static void updateGuide(String email, String newPassword, String newName,
      String newEmergencyContact) throws InvalidInputException {}

}
