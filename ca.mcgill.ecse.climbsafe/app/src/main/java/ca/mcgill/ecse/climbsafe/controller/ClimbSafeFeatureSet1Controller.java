package ca.mcgill.ecse.climbsafe.controller;

import java.sql.Date;
import java.time.LocalDate;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Hotel;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.model.User;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;


/**
 * ClimbSafe controller features for
 * setup, deleteMember, deleteGuide, deleteHotel
 * 
 * @author Onyekachi Ezekwem
 *
 */

public class ClimbSafeFeatureSet1Controller {
  
  /**
   * setup - creates a new season given the appropirate startDate, no. of weeks and priceOfGuidePerWeek
   * @param startDate
   * @param nrWeeks
   * @param priceOfGuidePerWeek
   * @throws InvalidInputException
   * @author Onyekachi Ezekwem
   */
  public static void setup(Date startDate, int nrWeeks, int priceOfGuidePerWeek)
      throws InvalidInputException {
	  String error = "";
	  if (nrWeeks < 0) {
		  error = "The number of climbing weeks must be greater than or equal to zero ";
	  }
	  if (priceOfGuidePerWeek < 0) {
		  error = "The price of guide per week must be greater than or equal to zero ";
	  }
	  if (startDate.toLocalDate().isBefore(getTodayDate().toLocalDate())) {
		  error = "Invalid date ";
	  }
	  if (error.length() > 0) {
		  throw new InvalidInputException(error);
	  }
	  ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	  
	  try {
		  climbSafe.setNrWeeks(nrWeeks);
		  climbSafe.setPriceOfGuidePerWeek(priceOfGuidePerWeek);
		  climbSafe.setStartDate(startDate);
		  ClimbSafePersistence.save();
	  } catch (RuntimeException e ){
		  throw new InvalidInputException(e.getMessage());
	  }
  }
  
  /**
   * deleteMember - delete a member with a given email 
   * @param email
   * @author Onyekachi Ezekwem
   */
  public static void deleteMember(String email) throws InvalidInputException {
	  User member = User.getWithEmail(email);
	  if (member != null && member instanceof Member) {
		  member.delete();
	  }
	  try {
	       	ClimbSafePersistence.save();
	      } catch (RuntimeException e) {
	        throw new InvalidInputException(e.getMessage());
	      }
  }
  
  /**
   * deleteGuide - deletes a guide with a given email
   * @param email
   * @author Onyekachi Ezekwem
   */
  public static void deleteGuide(String email) throws InvalidInputException {
	  User guide = User.getWithEmail(email);
	  if(guide != null && guide instanceof Guide) {
		  guide.delete();
	  }
	  try {
	        ClimbSafePersistence.save();
	      } catch (RuntimeException e) {
	        throw new InvalidInputException(e.getMessage());
	      }
  }

  // this method needs to be implemented only by teams with seven team members
  /**
   * deleteHotel - deletes a hotel with a given name
   * @param name
   * @author Onyekachi Ezekwem
   */
  public static void deleteHotel(String name) throws InvalidInputException {
	  Hotel hotel = Hotel.getWithName(name);
	  if (hotel != null) {
		  hotel.delete();
	  }
	  try {
	        ClimbSafePersistence.save();
	      } catch (RuntimeException e) {
	        throw new InvalidInputException(e.getMessage());
	      }
  }
  
  /**
   * helper method
   * @returns today's date
   * @author Onyekachi Ezekwem
   */
  private static Date getTodayDate() {
	    return Date.valueOf(LocalDate.now());
	  }

}