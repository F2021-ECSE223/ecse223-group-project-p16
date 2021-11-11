package ca.mcgill.ecse.climbsafe.features;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.function.Executable;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.AssignmentController;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.model.Assignment;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.model.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AssignmentFeatureStepDefinitions {

  /**
   * The existing ClimbSafe system.
   */
  private ClimbSafe climbSafe;

  private String error;
  private int errorCntr;

  @Given("the following ClimbSafe system exists:")
  public void the_following_climb_safe_system_exists(
      io.cucumber.datatable.DataTable climbSafeSystem) {

    climbSafe = ClimbSafeApplication.getClimbSafe();

    List<Map<String, String>> rows = climbSafeSystem.asMaps(String.class, String.class);

    for (Map<String, String> row : rows) {
      climbSafe.setStartDate(Date.valueOf(row.get("startDate")));
      climbSafe.setNrWeeks(Integer.parseInt(row.get("nrWeeks")));
      climbSafe.setPriceOfGuidePerWeek(Integer.parseInt(row.get("priceOfGuidePerWeek")));
    }
  }

  @Given("the following pieces of equipment exist in the system:")
  public void the_following_pieces_of_equipment_exist_in_the_system(
      io.cucumber.datatable.DataTable equipmentTable) {

    List<Map<String, String>> rows = equipmentTable.asMaps(String.class, String.class);

    for (Map<String, String> row : rows) {
      climbSafe.addEquipment(row.get("name"), Integer.parseInt(row.get("weight")),
          Integer.parseInt(row.get("pricePerWeek")));
    }
  }

  @Given("the following equipment bundles exist in the system:")
  public void the_following_equipment_bundles_exist_in_the_system(
      io.cucumber.datatable.DataTable bundleTable) {

    List<Map<String, String>> rows = bundleTable.asMaps(String.class, String.class);

    for (Map<String, String> row : rows) {
      EquipmentBundle bundle =
          climbSafe.addBundle(row.get("name"), Integer.parseInt(row.get("discount")));

      List<String> bundleItems = Arrays.asList(row.get("items").split(","));
      List<String> bundleItemQuantities = Arrays.asList(row.get("quantity").split(","));

      for (int i = 0; i < bundleItems.size(); i++) {

        bundle.addBundleItem(Integer.parseInt(bundleItemQuantities.get(i)), climbSafe,
            (Equipment) Equipment.getWithName(bundleItems.get(i)));

      }
    }
  }

  @Given("the following guides exist in the system:")
  public void the_following_guides_exist_in_the_system(io.cucumber.datatable.DataTable guideTable) {

    List<Map<String, String>> rows = guideTable.asMaps();

    for (Map<String, String> row : rows) {
      climbSafe.addGuide(row.get("email"), row.get("password"), row.get("name"),
          row.get("emergencyContact"));
    }
  }

  @Given("the following members exist in the system:")
  public void the_following_members_exist_in_the_system(
      io.cucumber.datatable.DataTable memberTable) {

    List<Map<String, String>> rows = memberTable.asMaps();

    for (Map<String, String> row : rows) {
      Member member = climbSafe.addMember(row.get("email"), row.get("password"), row.get("name"),
          row.get("emergencyContact"), Integer.parseInt(row.get("nrWeeks")),
          Boolean.parseBoolean(row.get("guideRequired")),
          Boolean.parseBoolean(row.get("hotelRequired")));

      List<String> bookedItems = Arrays.asList(row.get("bookedItems").split(","));
      List<String> bookedItemQuantities = Arrays.asList(row.get("bookedItemQuantities").split(","));

      for (int i = 0; i < bookedItems.size(); i++) {
        member.addBookedItem(Integer.parseInt(bookedItemQuantities.get(i)), climbSafe,
            BookableItem.getWithName(bookedItems.get(i)));
      }
    }
  }

  @When("the administrator attempts to initiate the assignment process")
  public void the_administrator_attempts_to_initiate_the_assignment_process() {

    callController(() -> AssignmentController.initiateAssignment());

  }

  @Then("the following assignments shall exist in the system:")
  public void the_following_assignments_shall_exist_in_the_system(
      io.cucumber.datatable.DataTable assignmentTable) {

    List<Map<String, String>> rows = assignmentTable.asMaps();
    /* List<Assignment> assignments = climbSafe.getAssignments(); */

    for (Map<String, String> row : rows) {

      String memberEmail = row.get("memberEmail");
      String guideEmail = row.get("guideEmail");
      int startWeek = Integer.parseInt(row.get("startWeek"));
      int endWeek = Integer.parseInt(row.get("endWeek"));

      /* boolean assignmentExists = false; */
      
      User user = Member.getWithEmail(memberEmail);
      
      Member member = null;
      
      if (user instanceof Member && user != null) {
        member = (Member) user;
      } else {
        fail("Member with email address " + memberEmail + " does not exist");
      }
      
      Assignment assignment = member.getAssignment();
      
      assertEquals(guideEmail, assignment.getGuide());
      assertEquals(startWeek, assignment.getStartWeek());
      assertEquals(endWeek, assignment.getEndWeek());
      

      /*
       * for (Assignment assignment : assignments) {
       * 
       * if (assignment.getMember().getEmail().equals(memberEmail) &&
       * assignment.getGuide().getEmail().equals(guideEmail) && assignment.getStartWeek() ==
       * startWeek && assignment.getEndWeek() == endWeek) {
       * 
       * assignmentExists = true; break;
       * 
       * }
       * 
       * }
       * 
       * assertTrue(assignmentExists);
       */

    }

  }

  @Then("the assignment for {string} shall be marked as {string}")
  public void the_assignment_for_shall_be_marked_as(String memberEmail, String assignmentExpectedStatus) {
    
    User user = Member.getWithEmail(memberEmail);
    Member member = null;
    
    if (user instanceof Member && user != null) {
      member = (Member) user;
    } else {
      fail("Member with email address " + memberEmail + " does not exist");
    }

    Assignment assignment = member.getAssignment();
    String assignmentStatus = getAssignmentStatus(assignment);
    
    assertEquals(assignmentExpectedStatus, assignmentStatus);
    
  }

  @Then("the number of assignments in the system shall be {string}")
  public void the_number_of_assignments_in_the_system_shall_be(String numberOfAssignments) {

    int expectedNumberOfAssignments = Integer.parseInt(numberOfAssignments);
    int actualNumberOfAssignments = climbSafe.getAssignments().size();
    
    assertEquals(expectedNumberOfAssignments, actualNumberOfAssignments);
    
  }

  @Then("the system shall raise the error {string}")
  public void the_system_shall_raise_the_error(String expectedError) {

    assertTrue(error.contains(expectedError));
    
  }

  @Given("the following assignments exist in the system:")
  public void the_following_assignments_exist_in_the_system(
      io.cucumber.datatable.DataTable assignmentTable) {
    
    List<Map<String, String>> rows = assignmentTable.asMaps();
    
    for (Map<String, String> row: rows) {
      
      Member member = (Member) Member.getWithEmail(row.get("memberEmail"));
      
      Assignment assignment = climbSafe.addAssignment(Integer.parseInt(row.get("startWeek")), Integer.parseInt(row.get("endWeek")), member);
      
      if (row.get("guideEmail").trim().length() != 0) {
        Guide guide = (Guide) Guide.getWithEmail("guideEmail");
        assignment.setGuide(guide);
      }
      
    }

  }

  @When("the administrator attempts to confirm payment for {string} using authorization code {string}")
  public void the_administrator_attempts_to_confirm_payment_for_using_authorization_code(
      String memberEmail, String authCode) {
    
    callController(() -> AssignmentController.pay(memberEmail, authCode));

  }

  @Then("the assignment for {string} shall record the authorization code {string}")
  public void the_assignment_for_shall_record_the_authorization_code(String memberEmail,
      String expectedAuthCode) {
    
    Member member = (Member) Member.getWithEmail(memberEmail);
    
    String actualAuthCode = member.getAssignment().getAuthCode();
    
    assertEquals(expectedAuthCode, actualAuthCode);

  }

  @Then("the member account with the email {string} does not exist")
  public void the_member_account_with_the_email_does_not_exist(String memberEmail) {
    
    assertFalse(Member.hasWithEmail(memberEmail));
    
  }

  @Then("there are {string} members in the system")
  public void there_are_members_in_the_system(String numberOfMembers) {
    
    int expectedNumberOfMembers = Integer.parseInt(numberOfMembers);
    
    int actualNumberOfMembers = climbSafe.getMembers().size();
    
    assertEquals (expectedNumberOfMembers, actualNumberOfMembers);
    
  }

  @Then("the error {string} shall be raised")
  public void the_error_shall_be_raised(String expectedError) {
    
    assertTrue(error.contains(expectedError));
    
  }

  @When("the administrator attempts to cancel the trip for {string}")
  public void the_administrator_attempts_to_cancel_the_trip_for(String memberEmail) {
    
    String expectedState = "Cancelled";
    
    Member member = (Member) Member.getWithEmail(memberEmail);
    Assignment assignment = member.getAssignment();
    String actualState = getAssignmentStatus(assignment);
    
    assertEquals(expectedState, actualState);
    
  }

  @Given("the member with {string} has paid for their trip")
  public void the_member_with_has_paid_for_their_trip(String memberEmail) {
    
    Member member = (Member) Member.getWithEmail(memberEmail);
    
    Assignment assignment = member.getAssignment();
    
    assignment.pay(member, "aAuthCode");
    
  }

  @Then("the member with email address {string} shall receive a refund of {string} percent")
  public void the_member_with_email_address_shall_receive_a_refund_of_percent(String memberEmail,
      String expectedRefundPercent) {
    
    int expectedRefund = Integer.parseInt(expectedRefundPercent);
    
    Member member = (Member) Member.getWithEmail(memberEmail);
    Assignment assignment = member.getAssignment();
    
    int actualRefund = assignment.getRefundPercent();
    
    assertEquals(expectedRefund, actualRefund);
    
  }

  @Given("the member with {string} has started their trip")
  public void the_member_with_has_started_their_trip(String memberEmail) {
    
    Member member = (Member) Member.getWithEmail(memberEmail);
    Assignment assignment = member.getAssignment();
    
    assignment.start(member);
    
  }

  @When("the administrator attempts to finish the trip for the member with email {string}")
  public void the_administrator_attempts_to_finish_the_trip_for_the_member_with_email(
      String memberEmail) {
    
    callController(() -> AssignmentController.finish(memberEmail));
    
  }

  @Given("the member with {string} is banned")
  public void the_member_with_is_banned(String memberEmail) {
    
    Member member = (Member) Member.getWithEmail(memberEmail);
    
    member.ban();
    
  }

  @Then("the member with email {string} shall be {string}")
  public void the_member_with_email_shall_be(String memberEmail, String expectedMemberState) {
    
    Member member = (Member) Member.getWithEmail(memberEmail);
    
    String actualMemberState = member.getMemberStatusFullName();
    
    assertEquals(expectedMemberState, actualMemberState);
    
  }

  @When("the administrator attempts to start the trips for week {string}")
  public void the_administrator_attempts_to_start_the_trips_for_week(String weekNr) {
    
    int weekNum = Integer.parseInt(weekNr);
    
    callController(() -> AssignmentController.start(weekNum));
    
  }

  @Given("the member with {string} has cancelled their trip")
  public void the_member_with_has_cancelled_their_trip(String memberEmail) {
    
    Member member = (Member) Member.getWithEmail(memberEmail);
    Assignment assignment = member.getAssignment();
    
    assignment.cancel();
    
  }

  @Given("the member with {string} has finished their trip")
  public void the_member_with_has_finished_their_trip(String memberEmail) {
    
    Member member = (Member) Member.getWithEmail(memberEmail);
    Assignment assignment = member.getAssignment();
    
    assignment.finish(member);
    
  }
  
  /** Returns assignment status as a string. */
  private String getAssignmentStatus(Assignment assignment) {
    
    String status = assignment.getAssignmentStatusFullName();
    if (status.contains(".")) {
      List<String> statuses = Arrays.asList(status.split("."));
      status = statuses.get(1);
    }
    
    return status;
  }

  /** Calls controller and sets error and counter. */
  private void callController(Executable executable) {
    try {
      executable.execute();
    } catch (InvalidInputException e) {
      error += e.getMessage();
      errorCntr += 1;
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }
}
