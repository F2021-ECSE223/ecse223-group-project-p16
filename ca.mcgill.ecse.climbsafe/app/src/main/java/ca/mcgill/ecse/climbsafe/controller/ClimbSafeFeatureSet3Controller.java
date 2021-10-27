package ca.mcgill.ecse.climbsafe.controller;


import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.User;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.model.Administrator;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;


public class ClimbSafeFeatureSet3Controller {
	
  private static ClimbSafe climbsafe = ClimbSafeApplication.getClimbSafe();

  public static void registerGuide(String email, String password, String name,
      String emergencyContact) throws InvalidInputException {
	  
	  var error = "";
	  
	  if (password.isEmpty() || password == null) {
		  error += "Password cannot be empty or null.";
		  }
	  if (password.contains(" ")) {
		  error += "Password cannot contain spaces";
		  }
	  if (name.isEmpty() || name == null) {
		  error += "Name cannot be empty or null.";
		  }
	  if (emergencyContact.isEmpty() || emergencyContact == null) {
		  error += "Emergency contact cannot be empty or null.";
		  }
	  if (email.isEmpty() || email == null) {
		  error += "Email address cannot be empty or null.";
		  }
	  if (email.contains(" ")) {
		  error += "Email address cannot contain spaces";
		  }
	  
	  if (email.indexOf("@") == 0) {
		  error += "Invalid email";
		  }
	  if (email.indexOf("@") != email.lastIndexOf("@")) {
		  error += "Invalid email";
		  }
	  if (email.indexOf("@") >= email.lastIndexOf(".") - 1) {
		  error += "Invalid email";
		  }
	  if (email.lastIndexOf(".") == email.length() - 1) {
		  error += "Invalid email";
		  }
	  
	  if (Guide.getWithEmail(email) != null) {
		  error += "Email already linked to a guide account";
		  }
	  if (Member.getWithEmail(email) != null) {
		  error += "Email already linked to a member account";
		  }
	  if (email.equals("admin@nmc.nt") && Administrator.hasWithEmail(email)) {
		  error += "Email cannot be admin@nmc.nt";
		  }
	  
	  if (!error.isEmpty()) {
		  throw new InvalidInputException(error.trim());
		  }

		try {
			climbsafe.addGuide(email, password, name, emergencyContact);
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
				}
  }

  public static void updateGuide(String email, String newPassword, String newName,
      String newEmergencyContact) throws InvalidInputException {
	  
	  var error = "";
	  
	  if (newPassword.isEmpty() || newPassword == null) {
		  error += "Password cannot be empty or null.";
		  }
	  if (newPassword.contains(" ")) {
		  error += "Password cannot contain spaces";
		  }
	  if (newName.isEmpty() || newName == null) {
		  error += "Name cannot be empty or null.";
		  }
	  if (newEmergencyContact.isEmpty() || newEmergencyContact == null) {
		  error += "Emergency contact cannot be empty or null.";
		  }
	  
	  if (!error.isEmpty()) {
		  throw new InvalidInputException(error.trim());
		  }

		try {
			Guide testGuide = (Guide) User.getWithEmail(email);
			testGuide.setPassword(newPassword);
			testGuide.setName(newName);
			testGuide.setEmergencyContact(newEmergencyContact);
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
				}
  }

  
}
