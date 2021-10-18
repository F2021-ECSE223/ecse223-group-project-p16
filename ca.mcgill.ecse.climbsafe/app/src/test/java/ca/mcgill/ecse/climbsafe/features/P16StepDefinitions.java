package ca.mcgill.ecse.climbsafe.features;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Hotel;
import ca.mcgill.ecse.climbsafe.model.Hotel.HotelRating;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.function.Executable;



public class P16StepDefinitions {
	
	private ClimbSafe climbSafe;
	private String error;
	private int errorCntr;
	
  @Given("the following ClimbSafe system exists: \\(p16)")
  public void the_following_climb_safe_system_exists_p16(
      io.cucumber.datatable.DataTable dataTable) {
	
    climbSafe = ClimbSafeApplication.getClimbSafe();
    error = "";
    errorCntr = 0;
    
    List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
    for (Map<String, String> columns : rows) {
    	climbSafe.setStartDate(Date.valueOf(columns.get("startDate")));
    	climbSafe.setNrWeeks((Integer.parseInt(columns.get("nrWeeks"))));
    	climbSafe.setPriceOfGuidePerWeek((Integer.parseInt(columns.get("priceOfGuidePerWeek"))));
    }
    
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    
  }

  @Given("the following hotels exist in the system: \\(p16)")
  public void the_following_hotels_exist_in_the_system_p16(
      io.cucumber.datatable.DataTable dataTable) {
	  
	List<Map<String, String>> rows = dataTable.asMaps();
	   for (Map<String, String> columns : rows) {
		   climbSafe.addHotel(columns.get("name"), columns.get("address"), getRating(Integer.parseInt(columns.get("rating"))));
	   }
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    
  }

  @When("the administator attempts to delete the hotel in the system with name {string} \\(p16)")
  public void the_administator_attempts_to_delete_the_hotel_in_the_system_with_name_p16(
      String string) {
    // Write code here that turns the phrase above into concrete actions
    callController(() -> ClimbSafeFeatureSet1Controller.deleteHotel(string));
  }

  @Then("the number of hotels in the system shall be {string} \\(p16)")
  public void the_number_of_hotels_in_the_system_shall_be_p16(String string) {
    // Write code here that turns the phrase above into concrete actions
    assertEquals(Integer.parseInt(string), climbSafe.getHotels().size());
  }

  @Then("the hotel with name {string}, address {string}, and rating {string} shall not exist in the system \\(p16)")
  public void the_hotel_with_name_address_and_rating_shall_not_exist_in_the_system_p16(
      String string, String string2, String string3) {
    // Write code here that turns the phrase above into concrete actions
    assertNull(Hotel.getWithName(string));
  }

  @Then("the following hotels shall exist in the system: \\(p16)")
  public void the_following_hotels_shall_exist_in_the_system_p16(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    List<Map<String, String>> rows = dataTable.asMaps();
    for (Map<String, String> columns : rows) {
    	assertTrue(Hotel.hasWithName(columns.get("name")));
    }
  }
  

  @After
  public void tearDown() {
    climbSafe.delete();
  }
  
  
  
  /** Calls controller and sets error and counter. */
  private void callController(Executable executable) {
    try {
      executable.execute();
    } catch (InvalidInputException e) {
      error += e.getMessage();
      errorCntr += 1;
    } catch (Throwable t) {
      fail();
    }
  }
  
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
