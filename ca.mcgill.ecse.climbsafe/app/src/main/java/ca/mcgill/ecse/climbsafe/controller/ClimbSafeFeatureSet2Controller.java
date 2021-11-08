package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.model.User;


/**
 * Controller implementation for the feature set 2. This includes the following features:
 * registerMember 
 * updateMember
 * 
 * @author Saif Shahin
 * @author Rui Du, Onyekachi Ezekwem, Samuel Valentine, Youssof Mohamed Masoud
 */

public class ClimbSafeFeatureSet2Controller {

	private static ClimbSafe climbsafe = ClimbSafeApplication.getClimbSafe();
	
	

	public static void registerMember(String email, String password, String name,
        String emergencyContact, int nrWeeks, boolean guideRequired, boolean hotelRequired,
        List<String> itemNames, List<Integer> itemQuantities) throws InvalidInputException {
	  
	  var error = "";

      if (email.isEmpty() || email == null) {
          error = "Invalid email";
      }
      if (email.equals("admin@nmc.nt")) {
          error = "The email entered is not allowed for members";
      }
      if (email.contains(" ")) {
          error = "The email must not contain any spaces";
      }
      if (email.indexOf("@") != email.lastIndexOf("@")) {
          error = "Invalid email";
      }
      if (email.indexOf("@") == 0) {
          error = "Invalid email";
      }
      if (email.indexOf("@") >= email.lastIndexOf(".") - 1) {
          error = "Invalid email";
      }
      if (email.lastIndexOf(".") == email.length() - 1) {
          error = "Invalid email";
      }
      if (User.getWithEmail(email) instanceof Member) {
          error = "A member with this email already exists";
      }
      if (User.getWithEmail(email) instanceof Guide) {
          error = "A guide with this email already exists";
      }
      if (password.isEmpty() || password == null) {
          error = "The password cannot be empty";
      }
      if (name.isEmpty() || name == null) {
          error = "The name cannot be empty";
      }
      if (emergencyContact.isEmpty() || emergencyContact == null) {
          error = "The emergency contact cannot be empty";
      }
      
      if (nrWeeks <= 0 || nrWeeks > climbsafe.getNrWeeks()) {
          error = "The number of weeks must be greater than zero and less than or equal to the number of climbing weeks in the climbing season";
      }
      
      // Check if the requested item is a valid equipment.
      for (String itemName : itemNames) {
          if (BookableItem.getWithName(itemName) == null) {
              error = "Requested item not found";
          }
      }

      for (int itemQuantity : itemQuantities) {
          if (itemQuantity <= 0) {
              error = "Item quantity cannot be less than or equal to 0";
          }
      }

      if (!error.isEmpty()) {
        throw new InvalidInputException(error.trim());
      }
      
      // Attempts to register a member onto the system.
      try {
          climbsafe.addMember(email, password, name, emergencyContact, nrWeeks,
                  guideRequired, hotelRequired);
          
          Member addedMember = climbsafe.getMember(climbsafe.getMembers().size() - 1);
          
          for (String bookableItem : itemNames) {
            
            addedMember.addBookedItem(itemQuantities.get(itemNames.indexOf(bookableItem)), climbsafe, BookableItem.getWithName(bookableItem));
            
          }
          
          
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
          error = "Invalid email";
        }
        if (email.equals("admin@nmc.nt")) {
            error = "The email entered is not allowed for members";
        }
        if (email.contains(" ")) {
            error = "The email must not contain any spaces";
        }
        if (email.indexOf("@") != email.lastIndexOf("@")) {
            error = "Invalid email";
        }
        if (email.indexOf("@") == 0) {
            error = "Invalid email";
        }
        if (email.indexOf("@") >= email.lastIndexOf(".") - 1) {
            error = "Invalid email";
        }
        if (email.lastIndexOf(".") == email.length() - 1) {
            error = "Invalid email";
        }
        if (newPassword.isEmpty() || newPassword == null) {
            error = "The password cannot be empty";
        }
        if (newName.isEmpty() || newName == null) {
            error = "The name cannot be empty";
        }
        if (newEmergencyContact.isEmpty() || newEmergencyContact == null) {
            error = "The emergency contact cannot be empty";
        }
        if (newNrWeeks > climbsafe.getNrWeeks() | newNrWeeks == 0) {
            error = "The number of weeks must be greater than zero and less than or equal to the number of climbing weeks in the climbing season";
        }
        if (Member.getWithEmail(email) == null) {
          error = "Member not found";
        }
        
        
        
        
        // Check if the requested item is a valid equipment.
        for (String itemName : newItemNames) {
            if (BookableItem.getWithName(itemName) == null) {
                error = "Requested item not found";
            }
        }


        if (!error.isEmpty()) {
            System.out.println(error);
            throw new InvalidInputException(error.trim());
        }

        // Attempts to update a member in the system.
        try {
          Member.getWithEmail(email).delete();
          registerMember(email, newPassword, newName, newEmergencyContact,
               newNrWeeks, newGuideRequired, newHotelRequired, newItemNames,
               newItemQuantities);
        } 
        
        catch (RuntimeException e) {
            
            throw new InvalidInputException(e.getMessage());
        }

    }

  }
  




