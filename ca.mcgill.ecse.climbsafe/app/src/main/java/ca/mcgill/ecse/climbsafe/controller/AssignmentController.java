package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.Assignment;
import ca.mcgill.ecse.climbsafe.model.Assignment.AssignmentStatusActive;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.model.User;

public class AssignmentController {
    private static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
    
    public static void initiateAssignment() {
      
    }
    
    public static void initiateAssignment2(int aStartWeek, int aEndWeek, Member aMember, boolean guideRequired) throws InvalidInputException{
      
      
        String error = "";
        if(guideRequired) {
            boolean isAvailable = false;
            for(Guide guide: climbSafe.getGuides()) {
                int nrWeeks = aMember.getNrWeeks();
                if(nrWeeks<=getAvailableWeeks(guide)) {
                    isAvailable = true;
                    break;
                }
                
            }
            if(!isAvailable) {
                error = "Assignments could not be completed for all members";
            }
            
        }
        
        if(!error.isEmpty()) {
            throw new InvalidInputException(error.trim());
        }
        
        
        try {
            climbSafe.addAssignment(aStartWeek,aEndWeek,aMember);
        } catch(RuntimeException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }
    
    private static int getAvailableWeeks(Guide guide) {
        int assignedWeeks = 0;
        for(Assignment assignment: guide.getAssignments()) {
            assignedWeeks+=assignment.getEndWeek()-assignment.getStartWeek()+1;
        }
        return climbSafe.getNrWeeks()-assignedWeeks;
    }
    
    
    
    public static void pay(String email, String aAuthCode) throws InvalidInputException{
      
      // Input validation 
      String error = "";
      
      User user = Member.getWithEmail(email);
      
      Member member = null;
      
      if (user instanceof Member) {
        member = (Member) user;
      }
      
      if (member == null) {
          error = "Member with email address " + email + " does not exist";
      }
      if (aAuthCode.isEmpty()) {
          error = "Invalid Authorization Code";
      }
      
      if(!error.isEmpty()) {
        throw new InvalidInputException(error.trim());
      }
      
      // Operation
      try {
        Assignment assignment = member.getAssignment();
        
        assignment.pay(member, aAuthCode);
        // assignment.pay deals with the state change, and also calls assignment.makePayment, which sets the authcode
        
      }
      catch(RuntimeException e) {
        throw new InvalidInputException(e.getMessage());
      }
    }   
    
    
    public static void cancel(String email) throws InvalidInputException{
      
      // Input Validation  
   // Input validation 
      String error = "";
      
      Member member = (Member) Member.getWithEmail(email); //TODO: casting?
      
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
        
      }
      catch(RuntimeException e) {
        throw new InvalidInputException(e.getMessage());
      }
    }
    
    
    
    public static void finish(String email) throws InvalidInputException{
      
      // Input Validation
      String error = "";
      
      Member member = (Member) Member.getWithEmail(email); //TODO: casting?
      
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
      }
      
      catch(RuntimeException e) {
        throw new InvalidInputException(e.getMessage());
      }
      
    }
    
    
    
    public static void start(String email) throws InvalidInputException{
      
      // Input Validation
      
      String error = "";
      
      if(!error.isEmpty()) {
        throw new InvalidInputException(error.trim());
      }
      
      Member member = (Member) Member.getWithEmail(email); //TODO: casting?
      
      
      // Operation
      try {
        
        Assignment assignment = member.getAssignment();
        
        assignment.start(member);
        
      }
      
      catch(RuntimeException e) {
        throw new InvalidInputException(e.getMessage());
      }
      
    }
    

}