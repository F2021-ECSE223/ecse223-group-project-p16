/**
 * Controller implementation for the feature set 2. This includes the following features:
 * registerMember 
 * updateMember
 * 
 * @author Saif Shahin
 */
package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.User;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import java.sql.Date;
import java.util.Collections;
import java.util.List;

public class ClimbSafeFeatureSet2Controller {

	private static ClimbSafe climbsafe = ClimbSafeApplication.getClimbSafe();

	static char quotation = '"'; // Done to make including quotations in the error messages easier.
	// String password="";

	/**
	 * Implementation of the exception class InvalidInputException. This was taken
	 * from class material.
	 * 
	 */

	public static class InvalidInputException extends Exception {

		private static final long serialVersionUID = -5633915762703837868L;

		/**
		 * Calls the super class to handle the exception correctly.
		 * 
		 * @param errorMessage
		 */

		public InvalidInputException(String errorMessage) {
			super(errorMessage);
		}

	}

	/**
	 * Controller implementation for the registerMember feature. This handles the
	 * errors that may arise from the user's input.
	 * 
	 * @param email
	 * @param password
	 * @param name
	 * @param emergencyContact
	 * @param nrWeeks
	 * @param guideRequired
	 * @param hotelRequired
	 * @param itemNames
	 * @param itemQuantities.
	 */

	public static void registerMember(String email, String password, String name, String emergencyContact, int nrWeeks,
			boolean guideRequired, boolean hotelRequired, List<String> itemNames, List<Integer> itemQuantities)
			throws InvalidInputException {

		var error = "";

		if (email.isEmpty() || email == null) {
			error += "Please enter a valid email address.";
		}
		if (email.equals("admin@nmc.nt")) {
			error += "Email cannot be admin@nmc.nt";
		}

		if (email.contains(" ")) {
			error += "Email must not contain any spaces.";
		}

		if (email.indexOf("@") != email.lastIndexOf("@")) {
			error += "Invalid email. Please enter a valid email address.";
		}
		if (email.indexOf("@") == 0) {
			error += "Invalid email. Please enter a valid email address.";
		}
		if (email.indexOf("@") >= email.lastIndexOf(".") - 1) {
			error += "Invalid email. Please enter a valid email address.";
		}
		if (email.lastIndexOf(".") == email.length() - 1) {
			error += "Invalid email. Please enter a valid email address.";
		}
		if (User.getWithEmail(email) instanceof Member) {
			error += "A Member account already exists with the following email.";
		}
		if (User.getWithEmail(email) instanceof Guide) {
			error += "A Guide account already exists with the following email.";
		}

		if (password.contains(" ")) {
			error += "Your passowrd cannot contain spaces. Please enter a valid password.";
		}

		if (password.isEmpty() || password == null) {
			error += "Your password cannot be empty or null. Please enter a valid password.";
		}
		if (name.isEmpty() || name == null) {
			error += "Please enter your name.";
		}
		if (emergencyContact.isEmpty() || emergencyContact == null) {
			error += "Please enter an emergency contact number.";
		}
		if (emergencyContact.length() > 15) {
			error += "Please enter a valid emergency contact number.";
		}
		if (nrWeeks < 0) {
			error += "The number of climbing weeks should be more than or equal to 0.";
		}
		if ((Boolean) guideRequired == null) {
			error += "You must indicate whether you require a mountain guide.";
		}
		if ((Boolean) hotelRequired == null) {
			error += "You must indicate whether you require a hotel";
		}

		// Check if the requested item is a valid equipment.
		for (String itemName : itemNames) {
			if (Equipment.getWithName(itemName) == null) {
				error = "Equipment " + itemName + " does not exist";
			}
		}

		for (int itemQuantity : itemQuantities) {
			if (itemQuantity < 0) {
				error = "You cannot select less than 0 items.";
			}
		}
		Collections.sort(itemQuantities, Collections.reverseOrder());
		for (int itemQuantity : itemQuantities) {
			if (itemQuantity > itemQuantities.get(0)) {
				error = "We do not have that many items in stock.";
			}
		}

		if (!error.isEmpty()) {
			throw new InvalidInputException(error.trim());
		}

		// Attempts to register a member onto the system.
		try {
			ClimbSafeFeatureSet2Controller.registerMember(email, password, name, emergencyContact, nrWeeks,
					guideRequired, hotelRequired, itemNames, itemQuantities);

		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}

	}

	/**
	 * Controller implementation for the updateMember feature. This handles the
	 * errors that may arise from the user's input.
	 * 
	 * @param email
	 * @param newPassword
	 * @param newName
	 * @param newEmergencyContact
	 * @param newNrWeeks
	 * @param newGuideRequired
	 * @param newHotelRequired
	 * @param newItemNames
	 * @param newItemQuantities
	 */

	public static void updateMember(String email, String newPassword, String newName, String newEmergencyContact,
			int newNrWeeks, boolean newGuideRequired, boolean newHotelRequired, List<String> newItemNames,
			List<Integer> newItemQuantities) throws InvalidInputException {

		var error = "";

		if (email.isEmpty() || email == null) {
			error += "Please enter a valid email address.";
		}
		if (email.equals("admin@nmc.nt")) {
			error += "Email cannot be admin@nmc.nt";
		}

		if (email.contains(" ")) {
			error += "Email must not contain any spaces.";
		}

		if (email.indexOf("@") != email.lastIndexOf("@")) {
			error += "Invalid email. Please enter a valid email address.";
		}
		if (email.indexOf("@") == 0) {
			error += "Invalid email. Please enter a valid email address.";
		}
		if (email.indexOf("@") >= email.lastIndexOf(".") - 1) {
			error += "Invalid email. Please enter a valid email address.";
		}
		if (email.lastIndexOf(".") == email.length() - 1) {
			error += "Invalid email. Please enter a valid email address.";
		}
		if (User.getWithEmail(email) instanceof Member) {
			error += "A Member account already exists with the following email.";
		}
		if (User.getWithEmail(email) instanceof Guide) {
			error += "A Guide account already exists with the following email.";
		}

		if (newPassword.contains(" ")) {
			error += "Your passowrd cannot contain spaces. Please enter a valid password.";
		}

		if (newPassword.isEmpty() || newPassword == null) {
			error += "Your password cannot be empty or null. Please enter a valid password.";
		}
		if (newName.isEmpty() || newName == null) {
			error += "Please enter your name.";
		}
		if (newEmergencyContact.isEmpty() || newEmergencyContact == null) {
			error += "Please enter an emergency contact number.";
		}
		if (newEmergencyContact.length() > 15) {
			error += "Please enter a valid emergency contact number.";
		}
		if (newNrWeeks < 0) {
			error += "The number of climbing weeks should be more than or equal to 0.";
		}
		if ((Boolean) newGuideRequired == null) {
			error += "You must indicate whether you require a mountain guide.";
		}
		if ((Boolean) newHotelRequired == null) {
			error += "You must indicate whether you require a hotel";
		}

		// Check if the requested item is a valid equipment.
		for (String itemName : newItemNames) {
			if (Equipment.getWithName(itemName) == null) {
				error = "Equipment " + itemName + " does not exist";
			}
		}

		for (int itemQuantity : newItemQuantities) {
			if (itemQuantity < 0) {
				error = "You cannot select less than 0 items.";
			}
		}
		Collections.sort(newItemQuantities, Collections.reverseOrder());
		for (int itemQuantity : newItemQuantities) {
			if (itemQuantity > newItemQuantities.get(0)) {
				error = "We do not have that many items in stock.";
			}
		}

		if (!error.isEmpty()) {
			throw new InvalidInputException(error.trim());
		}

		// Attempts to update a member in the system.
		try {
			ClimbSafeFeatureSet2Controller.updateMember(email, newPassword, newName, newEmergencyContact, newNrWeeks,
					newGuideRequired, newHotelRequired, newItemNames, newItemQuantities);

		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}

	}
}


