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
		  error = "No. of weeks must be greater than 0";
	  }
	  if (priceOfGuidePerWeek < 0) {
		  error = "Price of guider per week must be greater than 0";
	  }
	  if (startDate.toLocalDate().isBefore(getTodayDate().toLocalDate())) {
		  error = "Start Date must be after today's date";
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
		  throw new InvalidInputException(error);
	  }
  }

  public static void deleteMember(String email) {
	  Member member = (Member) User.getWithEmail(email);
	  if (member != null && member instanceof Member) {
		  member.delete();
	  }
  }

  public static void deleteGuide(String email) {
	  Guide guide = (Guide) User.getWithEmail(email);
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