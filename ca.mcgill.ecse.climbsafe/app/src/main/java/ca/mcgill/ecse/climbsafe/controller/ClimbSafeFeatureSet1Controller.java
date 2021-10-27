package ca.mcgill.ecse.climbsafe.controller;

import java.sql.Date;
import java.time.LocalDate;

import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.User;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Hotel;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;


public class ClimbSafeFeatureSet1Controller {

  public static void setup(Date startDate, int nrWeeks, int priceOfGuidePerWeek)
      throws InvalidInputException {
	  String error = "";
	  if (nrWeeks < 0) {
		  error += "The number of climbing weeks must be greater than or equal to zero ";
	  }
	  if (priceOfGuidePerWeek < 0) {
		  error += "The price of guide per week must be greater than or equal to zero ";
	  }
	  if (startDate.toLocalDate().isBefore(getTodayDate().toLocalDate())) {
		  error += "Invalid date ";
	  }
	  if (error.length() > 0) {
		  throw new InvalidInputException(error);
	  }
	  ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	  
	  try {
		  climbSafe.setNrWeeks(nrWeeks);
		  climbSafe.setPriceOfGuidePerWeek(priceOfGuidePerWeek);
		  climbSafe.setStartDate(startDate);
	  } catch (RuntimeException e ){
		  throw new InvalidInputException(e.getMessage());
	  }
  }

  public static void deleteMember(String email) {
	  User member = User.getWithEmail(email);
	  if (member != null && member instanceof Member) {
		  member.delete();
	  }
  }

  public static void deleteGuide(String email) {
	  User guide = User.getWithEmail(email);
	  if(guide != null && guide instanceof Guide) {
		  guide.delete();
	  }
  }

  // this method needs to be implemented only by teams with seven team members
  public static void deleteHotel(String name) {
	  Hotel hotel = Hotel.getWithName(name);
	  if (hotel != null) {
		  hotel.delete();
	  }
  }
  
  private static Date getTodayDate() {
	    return Date.valueOf(LocalDate.now());
	  }

}