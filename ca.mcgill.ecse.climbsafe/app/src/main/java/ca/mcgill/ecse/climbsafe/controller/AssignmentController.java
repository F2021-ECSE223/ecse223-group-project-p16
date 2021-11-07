package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.Assignment;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;

public class AssignmentController {
	private static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	
	public static void initiateAssignment(int aStartWeek, int aEndWeek, Member aMember, boolean guideRequired) throws InvalidInputException{
		
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
	
	public static void processAssignment(String email, String authCode) throws InvalidInputException{
		
		String error = "";
		
		Member member = (Member) Member.getWithEmail(email);
		
		if (member == null) {
			error = "Member with email address " + email + " does not exist";
		}
		if (authCode.isEmpty()) {
			error = "Invalid Authorization Code";
		}
		
		
		if(!error.isEmpty()) {
			throw new InvalidInputException(error.trim());
		}
		
//		if () {
//			
//		}
		
		try {
			member.getAssignment().setAuthCode(authCode);
			member.getAssignment().pay(member, authCode); //if problem, switch authCode to member.getAssignment.getAuthCode();
			
			
		} catch(RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
		
	}
	
}