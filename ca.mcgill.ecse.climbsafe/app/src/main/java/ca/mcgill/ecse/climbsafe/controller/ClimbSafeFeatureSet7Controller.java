package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Hotel;
import ca.mcgill.ecse.climbsafe.model.Hotel.HotelRating;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;


/**
	 * Add/Update Hotel Feature
	 * @author Youssof Mohamed
	 */
public class ClimbSafeFeatureSet7Controller {

	
	/**
	 * createHotel
	 * @author Youssof Mohamed
	 * @param name
	 * @param address
	 * @param aRating
	 * @throws InvalidInputException
	 */
	public static void addHotel(String name, String address, int nrStars) throws InvalidInputException {
		var error = "";
		if(name=="") {
			error="Name cannot be empty";
		}
		if(address=="") {
			error="Address cannot be empty";
		}
		if(nrStars>5 || nrStars<1) {
			error="Number of stars must be between 1 and 5";
		}
		if(Hotel.getWithName(name)!=null) {
			error = "Hotel already exists in the system";
		}
		if(!error.isEmpty()) {
			throw new InvalidInputException(error.trim());
		}
		try {
			climbSafe.addHotel(name, address, getRating(nrStars));
			ClimbSafePersistence.save();
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}

	
	/**updateHotel
	 * @author Youssof Mohamed
	 * @param oldName
	 * @param newName
	 * @param newAddress
	 * @param newRating
	 * @throws InvalidInputException
	 */
	public static void updateHotel(String oldName, String newName, String newAddress, int NewNrStars) throws InvalidInputException {
		var error = "";
		if(oldName=="") {
			error="oldName cannot be empty";
		} else if(Hotel.getWithName(oldName)==null) {
			error = "Hotel does not exist in the system";
		}
		if(newName=="") {
			error="Name cannot be empty ";
		} else if(Hotel.getWithName(newName)!=null) {
			error="New name already linked to another hotel";
		}
		if(newAddress=="") {
			error="Address cannot be empty ";
		}
		if(NewNrStars<1 || NewNrStars>5) {
			error="Number of stars must be between 1 and 5";
		}
		if(!error.isEmpty()) {
			throw new InvalidInputException(error.trim());
		}
		
		try {
			Hotel myHotel = Hotel.getWithName(oldName);
			myHotel.setName(newName);
			myHotel.setAddress(newAddress);
			myHotel.setRating(getRating(NewNrStars));
			ClimbSafePersistence.save();
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
		
	}
	
	
	/**getRating()
	 * Uses switch statement to return a HotelRating value that corresponds to the input integer
	 * @author Youssof Mohamed
	 * @param nrStars
	 */
	private static Hotel.HotelRating getRating(int nrStars){
		switch(nrStars) {
		  case 1:
			  return HotelRating.OneStar;
		  case 2:
			  return HotelRating.TwoStars;
		  case 3:
			  return HotelRating.ThreeStars;
		  case 4:
			  return HotelRating.FourStars;
		 default:
			  return HotelRating.FiveStars;
		}
	}
	
}
