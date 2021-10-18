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

  /**
   * The existing ClimbSafe system.
   */
  private ClimbSafe climbSafe;

  /**
   * Stores error messages when calling controller methods.
   */
  private String error;

  /**
   * Error counter.
   */
  private int errorCntr;

  /**
   * This method is part of the "given" clause that creates a climbSafe object directly using the
   * model, in order to perform testing.
   * 
   * @author Samuel Valentine, Onyekachi Ezekwem, Rui Du, Youssof Mohamed Masoud.
   * @param dataTable : which includes the ClimbSafe object's attributes (i.e. StartDate, NrWeeks,
   *        and priceOfQuidePerWeek).
   */
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
  }

  /**
   * This method is part of the "given" clause that creates and adds hotel objects directly using
   * the model, in order to perform testing.
   * 
   * @author Samuel Valentine, Onyekachi Ezekwem, Rui Du, Youssof Mohamed Masoud.
   * @param dataTable : which represents the hotels by including their names, addresses, and
   *        ratings.
   */
  @Given("the following hotels exist in the system: \\(p16)")
  public void the_following_hotels_exist_in_the_system_p16(
      io.cucumber.datatable.DataTable dataTable) {

    List<Map<String, String>> rows = dataTable.asMaps();
    for (Map<String, String> columns : rows) {
      climbSafe.addHotel(columns.get("name"), columns.get("address"),
          getRating(Integer.parseInt(columns.get("rating"))));
    }
  }

  /**
   * This method is part of the "when" clause that uses the controller to attempt and delete a
   * hotel.
   * 
   * @author Samuel Valentine, Onyekachi Ezekwem, Rui Du, Youssof Mohamed Masoud.
   * @param string : which represents the name of the hotel.
   */
  @When("the administator attempts to delete the hotel in the system with name {string} \\(p16)")
  public void the_administator_attempts_to_delete_the_hotel_in_the_system_with_name_p16(
      String string) {

    callController(() -> ClimbSafeFeatureSet1Controller.deleteHotel(string));
  }

  /**
   * This method is part of the "then" clause that checks the result of the attempting to delete the
   * hotel via the controller.
   * 
   * @author Samuel Valentine, Onyekachi Ezekwem, Rui Du, Youssof Mohamed Masoud, Yakir Bender.
   * @param string : which represents the number of hotels that will remain in the system after
   *        deletion.
   */
  @Then("the number of hotels in the system shall be {string} \\(p16)")
  public void the_number_of_hotels_in_the_system_shall_be_p16(String string) {

    assertEquals(Integer.parseInt(string), climbSafe.getHotels().size());
  }

  /**
   * This method is part of the "then" clause that checks the result of the attempting to delete the
   * hotel via the controller.
   * 
   * @author Samuel Valentine, Onyekachi Ezekwem, Rui Du, Youssof Mohamed Masoud, Yakir Bender.
   * @param string, string2, string3 : which represents the name, address, and rating of the hotel
   *        that will no longer exist in the system.
   */
  @Then("the hotel with name {string}, address {string}, and rating {string} shall not exist in the system \\(p16)")
  public void the_hotel_with_name_address_and_rating_shall_not_exist_in_the_system_p16(
      String string, String string2, String string3) {

    assertNull(Hotel.getWithName(string));
  }

  /**
   * This method is part of the "then" clause that checks the result of the attempting to delete the
   * hotel via the controller.
   * 
   * @author Samuel Valentine, Onyekachi Ezekwem, Rui Du, Youssof Mohamed Masoud, Yakir Bender.
   * @param datatable : which represents the hotels that will remain in the system after deletion.
   */
  @Then("the following hotels shall exist in the system: \\(p16)")
  public void the_following_hotels_shall_exist_in_the_system_p16(
      io.cucumber.datatable.DataTable dataTable) {

    List<Map<String, String>> rows = dataTable.asMaps();
    for (Map<String, String> columns : rows) {
      assertTrue(Hotel.hasWithName(columns.get("name")));
    }
  }

  /**
   * This After method scraps the existing ClimbSafe system after testing each scenario.
   * 
   * @author Samuel Valentine, Onyekachi Ezekwem, Rui Du, Youssof Mohamed Masoud.
   */
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

  /**
   * This helper method converts integer ratings for hotel to a valid HotelRating value.
   * 
   * @author Youssof Mohamed Masoud.
   * @param nrStars : which represents the hotel rating as an integer number of stars.
   * @return HotelRating : which represents the rating of the hotel.
   */
  private static Hotel.HotelRating getRating(int nrStars) {
    switch (nrStars) {
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
