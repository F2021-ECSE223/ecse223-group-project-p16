/**
 * Controller implementation for the feature set 2. This includes the following features:
 * registerMember 
 * updateMember
 * 
 * @author Saif Shahin
 */

import java.sql.Date;

import java.util.List;

public class ClimbSafeFeatureSet2Controller {
	
	static char quotation='"'; //Done to make including quotations in the error messages easier.
	static String error; //Initializing the error variable
	
	/**
	 * Implementation of the exception class InvalidInputException. This was taken from class material.
	 * 
	 */ 
	
	public static class InvalidInputException extends Exception {

		  private static final long serialVersionUID = -5633915762703837868L;

		  public InvalidInputException(String errorMessage) {
		    super(errorMessage);
		  }

		}
	
	
	
	/**
	 * Controller implementation for the registerMember feature. This handles the errors
	 * that may arise from the user's input.
	 * 
	 * @param email, password, name, emergencyContact, nrWeeks, guideRequired, hotelRequired, itemNames, itemQuantities.
	 */
	
	

  public static void registerMember(String email, String password, String name,
      String emergencyContact, int nrWeeks, boolean guideRequired, boolean hotelRequired,
      List<String> itemNames, List<Integer> itemQuantities) throws InvalidInputException {
	  
	  
	  try {
		  ClimbSafeFeatureSet2Controller.registerMember(email, password, name, emergencyContact, nrWeeks, guideRequired, 
				  hotelRequired, itemNames, itemQuantities);
		  
	  }catch(RuntimeException e){
		  
		  if(error.startsWith("Cannot create due to duplicate email")) {
			  error="An account with this email adress already exists. Please make sure that you inputted"
			  		+ " your correct email.";
		  }
		  if(error.startsWith("Cannot create due to invalid email")) {
			  error="Please enter a valid email address.";
		  }
		  if(error.startsWith("Cannot create due to invalid password")) {
			  error="Please enter a password with both charecters and numbers. It should be at least 8 charecters"
			  		+ " long.";
		  }
		  if(error.startsWith("Cannot create due to invalid name")) {
			  error="Please enter a valid name. A name cannot have nubers or special charecters.";
		  }
		  
		  if(error.startsWith("Cannot create due to invalid emergencyContact")) {
			  error="Please enter a valid emergency contact number.";
		  }
		  if(error.startsWith("Cannot create due to nrWeeks> climbingSeason")) { //Fix climbingSeason to the duration of the season.
			  error="Please ensure that the number of weeks you wish to traverse the himalayas is not "
			  		+ "greater than the climbing season";
		  }
		  if(error.startsWith("Cannot create due to invalid nrWeeks")) {
			  error="Please enter a valid duration for your climb.";
		  }
		  if(error.startsWith("Cannot create due to invalid guideRequired")) {
			  error="Please input whether you require a mountain guide.";
		  }
		  if(error.startsWith("Cannot create due to invalid hotelRequired")) {
			  error="Please input whether you require a hotel during your stay.";
		  }
		  if(error.startsWith("Cannot create due to invalid itemNames")) {
			  error="Please enter valid equipement names, or enter " + quotation + "NA" + quotation +".";
		  }
		  if(error.startsWith("Cannot create due to invalid itemQuantities")) {
			  error="Please enter a valid quantity, or enter " + quotation + "NA" + quotation + " if you"
					  + " did not select any equipment."; //include the maximum quantity of the said equipment
		  }
		  
		  throw new InvalidInputException(error);
		  
	  }
	  
	  
  }
  
  /**
	 * Controller implementation for the updateMember feature. This handles the errors
	 * that may arise from the user's input.
	 * 
	 * @param email, newPassword, newName, newEmergencyContact, newNrWeeks, newGuideRequired, newHotelRequired, newItemNames, newItemQuantities.
	 */

  public static void updateMember(String email, String newPassword, String newName,
      String newEmergencyContact, int newNrWeeks, boolean newGuideRequired,
      boolean newHotelRequired, List<String> newItemNames, List<Integer> newItemQuantities)
      throws InvalidInputException {
	  
	  try {
		  
		  ClimbSafeFeatureSet2Controller.updateMember(email, newPassword, newName, newEmergencyContact, newNrWeeks, newGuideRequired,
				  newHotelRequired, newItemNames, newItemQuantities);
		  
	  }catch (RuntimeException e) {
		  if(error.startsWith("Cannot create due to duplicate email")) {
			  error="An account with this email adress already exists. Please make sure that you inputted"
			  		+ " your correct email.";
		  }
		  if(error.startsWith("Cannot create due to invalid email")) {
			  error="Please enter a valid email address.";
		  }
		  if(error.startsWith("Cannot create due to invalid newPassword")) {
			  error="Please enter a password with both charecters and numbers. It should be at least 8 charecters"
			  		+ " long.";
		  }
		  if(error.startsWith("Cannot create due to invalid newName")) {
			  error="Please enter a valid name. A name cannot have nubers or special charecters.";
		  }
		  
		  if(error.startsWith("Cannot create due to invalid newEmergencyContact")) {
			  error="Please enter a valid emergency contact number.";
		  }
		  if(error.startsWith("Cannot create due to newNrWeeks> climbingSeason")) { 
			  error="Please ensure that the number of weeks you wish to traverse the himalayas is not "
			  		+ "greater than the climbing season";
		  }
		  if(error.startsWith("Cannot create due to invalid newNrWeeks")) {
			  error="Please enter a valid duration for your climb.";
		  }
		  if(error.startsWith("Cannot create due to invalid newGuideRequired")) {
			  error="Please input whether you require a mountain guide.";
		  }
		  if(error.startsWith("Cannot create due to invalid newHotelRequired")) {
			  error="Please input whether you require a hotel during your stay.";
		  }
		  if(error.startsWith("Cannot create due to invalid newItemNames")) {
			  error="Please enter valid equipement names, or enter " + quotation + "NA" + quotation +".";
		  }
		  if(error.startsWith("Cannot create due to invalid newItemQuantities")) {
			  error="Please enter a valid quantity, or enter " + quotation + "NA" + quotation + " if you"
					  + " did not select any equipment."; 
		  }
		 
		  
		  throw new InvalidInputException(e.getMessage());
	  }
	  
  }

}



