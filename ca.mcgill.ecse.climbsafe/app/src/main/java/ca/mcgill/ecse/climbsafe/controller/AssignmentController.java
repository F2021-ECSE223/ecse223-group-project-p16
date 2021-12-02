package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.Assignment;
import ca.mcgill.ecse.climbsafe.model.Assignment.AssignmentStatusActive;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.model.User;

public class AssignmentController {
  
    private static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
    
    /**
     * Initiate Assignment
     * 
     * @author Youssof Mohamed
     * @author Sam Valentine
     * @author Rui Du
     * @author Yakir Bender
     * @author Saif Shahin
     * @throws InvalidInputException
     */
    public static void initiateAssignment() throws InvalidInputException {
      
      String error = "";
     
      List<Member> members = climbSafe.getMembers();
      List<Guide> guides = climbSafe.getGuides();
      
      try {
        
        for (Guide guide: guides) {
          
          int seasonWeeks = climbSafe.getNrWeeks();
          
          int guideWeeks = seasonWeeks;
          
          int currentWeek = 1;
          
          for (Member member: members) {
            
            // if we have not assigned it yet
            if (member.getAssignment() == null) {
              
              int memberWeeksNumber = member.getNrWeeks();
              
              if (member.getGuideRequired()) {             
               
                if (memberWeeksNumber <= guideWeeks) {
                  
                  Assignment assignment = climbSafe.addAssignment(currentWeek, currentWeek+memberWeeksNumber-1, member);
                  
                  guide.addAssignment(assignment);
                  
                  currentWeek += memberWeeksNumber;
                  
                  guideWeeks -= memberWeeksNumber;
                  
                  ClimbSafePersistence.save();
                }
                
              }
              
              else {
                
                climbSafe.addAssignment(1, memberWeeksNumber, member);
                ClimbSafePersistence.save();
              }
              
            }
            
          }
        
        }
        
      }
      
      catch(RuntimeException e) {
         throw new InvalidInputException(e.getMessage());
      }
        
      
      for (Member member : members) {
        
        if (member.getAssignment() == null) {
        
          error = "Assignments could not be completed for all members";
          break;
          
        }
        
      }
      
      if(!error.isEmpty()) {
        
        throw new InvalidInputException(error.trim());
        
      }      
      
    }
    
    
    /**
     * Payment Process
     * 
     * @author Youssof Mohamed
     * @author Sam Valentine
     * @author Rui Du
     * @author Yakir Bender
     * @author Saif Shahin
     * @throws InvalidInputException
     */
    public static void pay(String email, String aAuthCode) throws InvalidInputException{
      
      // Input validation 
      String error = "";
      
      // Get the member associated with the passed email
      User user = Member.getWithEmail(email);
      Member member = null;
      if (user instanceof Member) {
        member = (Member) user;
      }
      
      if (member == null) {
          error = "Member with email address " + email + " does not exist";
      }
      if (aAuthCode.isEmpty()) {
          error = "Invalid authorization code";
      }
      
      if(!error.isEmpty()) {
        throw new InvalidInputException(error.trim());
      }
      
      // Operation
      try {
        Assignment assignment = member.getAssignment();
        
        assignment.pay(member, aAuthCode);
        // assignment.pay deals with the state change, and also calls assignment.makePayment, which sets the authcode
        ClimbSafePersistence.save();
      }
      catch(RuntimeException e) {
        throw new InvalidInputException(e.getMessage());
      }
    }   
    
    /**
     * Cancellation Process
     * 
     * @author Youssof Mohamed
     * @author Sam Valentine
     * @author Rui Du
     * @author Yakir Bender
     * @author Saif Shahin
     * @throws InvalidInputException
     */
    public static void cancel(String email) throws InvalidInputException{
      
      // Input Validation  
      
      String error = "";
      
      // Get the member associated with the passed email
      User user = Member.getWithEmail(email);
      Member member = null;
      if (user instanceof Member) {
        member = (Member) user;
      }
      
      if (member == null) {
          error = "Member with email address " + email + " does not exist";
      }
      
      if(!error.isEmpty()) {
        throw new InvalidInputException(error.trim());
      }
      
      // Operation
      try {
        Assignment assignment = member.getAssignment(); 
        assignment.cancel();
        // The refunding is already handled by assignment.cancelTrip(), which is called by assignment.cancel():
        ClimbSafePersistence.save();
      }
      catch(RuntimeException e) {
        throw new InvalidInputException(e.getMessage());
      }
    }
    
    
    /**
     * Finishing Process
     * 
     * @author Youssof Mohamed
     * @author Sam Valentine
     * @author Rui Du
     * @author Yakir Bender
     * @author Saif Shahin
     * @throws InvalidInputException
     */
    public static void finish(String email) throws InvalidInputException{
      
      String error = "";
      
      // Get the member associated with the passed email
      User user = Member.getWithEmail(email);
      Member member = null;
      if (user instanceof Member) {
        member = (Member) user;
      }
      
      if (member == null) {
          error = "Member with email address " + email + " does not exist";
      }
      
      if(!error.isEmpty()) {
        throw new InvalidInputException(error.trim());
      }
      
      // Operation
      try {
      
        Assignment assignment = member.getAssignment();
        
        assignment.finish(member);     
        
        ClimbSafePersistence.save();
      }
      
      catch(RuntimeException e) {
        throw new InvalidInputException(e.getMessage());
      }
      
    }
    
    
    /**
     * Starting Process
     * 
     * @author Youssof Mohamed
     * @author Sam Valentine
     * @author Rui Du
     * @author Yakir Bender
     * @author Saif Shahin
     * @throws InvalidInputException
     */
    public static void start(int weekNr) throws InvalidInputException{
      
    	String error ="";
    	
	      try {
	    	  for(Member member: climbSafe.getMembers()) {
	          	if(weekNr==member.getAssignment().getStartWeek()) {
	          		if(member.getAssignment().getAssignmentStatusActive()==AssignmentStatusActive.Assigned) {
	          			error = "Cannot start the trip due to a ban";
	          		}
	  	        	member.getAssignment().start(member);
	  	        	ClimbSafePersistence.save();
	          	}
	          }
	    	  if(!error.isEmpty()) {
	    		  throw new InvalidInputException(error.trim());
	    	  }
	      } catch (RuntimeException e) {
	    	  throw new InvalidInputException(e.getMessage());
	      }
      
    }
    

}