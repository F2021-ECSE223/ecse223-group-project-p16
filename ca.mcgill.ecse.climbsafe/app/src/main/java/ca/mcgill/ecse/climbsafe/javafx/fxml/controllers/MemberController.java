/**
 * @Author Saif Shahin
 * 
 * This is the fxml controller for the member page of the ClimbSafe Program
 * 
 */

package ca.mcgill.ecse.climbsafe.javafx.fxml.controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import java.util.ArrayList;

import java.util.Arrays;

import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;

import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;

import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet2Controller;

import ca.mcgill.ecse.climbsafe.javafx.fxml.controllers.ViewUtils;

import ca.mcgill.ecse.climbsafe.javafx.fxml.main.ClimbSafeFxmlView;

import ca.mcgill.ecse.climbsafe.model.ClimbSafe;

import java.util.regex.Pattern;


public class MemberController {
	
	// initializing the register member interactable components
		private static ClimbSafe climbsafe = ClimbSafeApplication.getClimbSafe();
		boolean regHotelRequired;
		boolean regGuideRequired;
		//List<String> regItemNames = new ArrayList<String>();
		List<Integer> regItemQuantities = new ArrayList<Integer>();
	
		@FXML
		private Button refreshButton;
		@FXML
		private TextField regNameTextField;
		@FXML
		private TextField regEmailTextField;
		@FXML
		private TextField regPasswordTextField;
		@FXML
		private TextField regEmergencyContactTextField;
		@FXML
		private TextField regNrWeeksTextField;
		@FXML
		private TextField regEquipNamesTextField;
		@FXML
		private TextField regEquipQuantityTextField;
		@FXML
		private Button regReqGuideYesButton;
		@FXML
		private Button regReqGuideNoButton;
		@FXML
		private Button regHotelYesButton;
		@FXML
		private Button regHotelNoButton;
		@FXML
		private Button registerButton;
		@FXML
		private Button updateButton;
		@FXML
		private Button cancelBookingButton;
		@FXML
		private TextField cancelNameTextField;
		@FXML
		private TextField cancelEmailTextField;
		@FXML
		private TextField cancelPasswordTextField;

		@FXML
		public void refreshButton(ActionEvent event) {
			ClimbSafeFxmlView.getInstance().refresh();
		}

	// Event Listener on Button[#regReqGuideYesButton].onAction
	@FXML
	public void regReqGuideYesButton(ActionEvent event) {
		
		this.regGuideRequired=true;
		
	}
	
	@FXML
	public void regReqGuideNoButton(ActionEvent event) {
		
		this.regGuideRequired=false;
		
	}
	
	@FXML
	public void regHotelYesButton(ActionEvent event) {
		
		this.regHotelRequired=true;
		
	}
	
	@FXML
	public void regHotelNoButton(ActionEvent event) {
		
		this.regHotelRequired=false;
		
	}

/**
 *
 * This method gets called when the "Register" button is clicked on the GUI. 
 * It takes in the user input and sends the necessary parameter to the registerMember(String email, String password, 
 * String name, String emergencyContact, int nrWeeks, boolean guideRequired, 
 * boolean hotelRequired, List<String> itemNames, List<Integer> itemQuantities) method 
 * in the Feature Set 2 MVC Controller.
 *
 * @param ActionEvent event- this is the clicking action.
 */
	
	@FXML
	public void registerButton(ActionEvent event) {
	 try {
		String name =regNameTextField.getText();
		String email=regEmailTextField.getText();
		String password=regPasswordTextField.getText();
		String emergencyContact=regEmergencyContactTextField.getText();
		String nrWeeksString=regNrWeeksTextField.getText();
		
		String[] items= regEquipNamesTextField.getText().split(","); 
		List <String> regItemNames = Arrays.asList(items);	

	
		String[] itemQuantities= regEquipQuantityTextField.getText().split(","); 
		int size=itemQuantities.length;
		Integer [] arr=new Integer[size];
		for(int i=0; i<size; i++) {
			arr[i]=Integer.parseInt(itemQuantities[i]);
		}
		List <Integer> regItemQuantities= Arrays.asList(arr);	
		
	
		int nrWeeks=0;
		if(Pattern.matches("[a-zA-Z]+", nrWeeksString)|| nrWeeksString.contains("-")) {
			ViewUtils.showError("Please input a valid number of weeks you would like to climb.");
			return;
		}
		
			nrWeeks=Integer.parseInt(nrWeeksString);
			int hi = nrWeeks;
		
		
		if(email.length()>=1 && password.length()>=1 && name.length()>=1 && emergencyContact.length()>=1
				&& nrWeeksString.length()>=1) {
			
			if(nrWeeks<=0) {
				ViewUtils.makePopupWindow("Registration Failed", "Please input a valid number of weeks you would like to climb.");
			}
		
			//adds the user inputs into the MVC FeatureSet2controlller. 
			else if (ViewUtils.callController(() -> 
		      ClimbSafeFeatureSet2Controller.registerMember(email, password, name, emergencyContact, hi, this.regGuideRequired, 
		    		  this.regHotelRequired, regItemNames, regItemQuantities ))) {
				
				ViewUtils.makePopupWindow("Registration Successful", name+ " has been registered as a member.");
		
				regNameTextField.setText("");
				regEmailTextField.setText("");
				regPasswordTextField.setText("");
				regEmergencyContactTextField.setText("");
				regNrWeeksTextField.setText("");
				items=null;
				itemQuantities=null;
				nrWeeks=0;
				this.regHotelRequired=false;
				this.regGuideRequired=false;
				
			}
		
	}
		else {
			 ViewUtils.makePopupWindow("Registration Failed", "Please input valid entries for all feilds. "
			 		+ "If you need help, see the boxes in blue.");
		}
	} catch (NumberFormatException ex) {
		ViewUtils.makePopupWindow("Registration Failed", "Please enter the equipment quantities you require as a list seperated by commas only.\n"
				+"For example, enter the quanitites as such: (4,3,7) not (4, 3, 7).");
	}
	 
	}
	
	/**
	 * 
	 * This method gets called when the "Update" button is clicked on the GUI.
	 * It takes in the user input and sends the necessary parameter to the updateMember(String email, String newPassword, 
	 * String newName, String newEmergencyContact, int newNrWeeks, boolean newGuideRequired, 
	 * boolean newHotelRequired, List<String> newItemNames, List<Integer> newItemQuantities) method 
	 * in the Feature Set 2 MVC Controller.
	 * 
	 * @param ActionEvent event- This is the clicking action.
	 */
	
	@FXML
	public void updateButton(ActionEvent event) {
	  try {
		String newName =regNameTextField.getText();
		String newEmail=regEmailTextField.getText();
		String newPassword=regPasswordTextField.getText();
		String newEmergencyContact=regEmergencyContactTextField.getText();
		String newNrWeeksString=regNrWeeksTextField.getText();
		
		String[] newItems= regEquipNamesTextField.getText().split(","); 
		List <String> regNewItemNames = Arrays.asList(newItems);	
	
	
		String[] newItemQuantities= regEquipQuantityTextField.getText().split(","); 
		int size=newItemQuantities.length;
		Integer [] arr=new Integer[size];
		for(int i=0; i<size; i++) {
			arr[i]=Integer.parseInt(newItemQuantities[i]);
		}
		List <Integer> regNewItemQuantities= Arrays.asList(arr);	
		
	
	
		
		int newNrWeeks=0;
		
		if(Pattern.matches("[a-zA-Z]+", newNrWeeksString)|| newNrWeeksString.contains("-")) {
			ViewUtils.showError("Please input a valid number of weeks you would like to climb.");
			return;
		}
		
			newNrWeeks=Integer.parseInt(newNrWeeksString); 
			int hi = newNrWeeks;
		
		
		if(newEmail.length()>=1 && newPassword.length()>=1 && newName.length()>=1 && newEmergencyContact.length()>=1
				&& newNrWeeksString.length()>=1) {
		
			if(newNrWeeks==0) {
				ViewUtils.makePopupWindow("Registration Failed", "Please input a valid number of weeks you would like to update your climb to.");
			}
		
			else if(ViewUtils.callController(() -> 
		      ClimbSafeFeatureSet2Controller.updateMember(newEmail, newPassword, newName, newEmergencyContact, hi, this.regGuideRequired, 
		    		  this.regHotelRequired, regNewItemNames, regNewItemQuantities ))) {
				
				ViewUtils.makePopupWindow("Registration Successful", newName + " has been registered as a Member.");
				
				regNameTextField.setText("");
				regEmailTextField.setText("");
				regPasswordTextField.setText("");
				regEmergencyContactTextField.setText("");
				regNrWeeksTextField.setText("");
				newItems=null;
				newItemQuantities=null;
				newNrWeeks=0;
				this.regHotelRequired=false;
				this.regGuideRequired=false;
				
			}
	}
		else {
			 ViewUtils.makePopupWindow("Registration Failed", "You have failed to update your booking, please input valid entries for all feilds. "
			 		+ "If you need help, see the boxes in blue.");
		}
	  }
	  catch (NumberFormatException ex) {
			ViewUtils.makePopupWindow("Registration Failed", "Please enter the equipment quantities you require as a list seperated by commas only.\n"
					+"For example, enter the quanitites as such: (4,3,7) not (4, 3, 7)");
		}
		  
	  }
		
	
	
	/**
	 * 
	 * This method gets called when the "Cancel Booking" button is clicked on the GUI.
	 * It takes in the user input and sends the necessary parameter to the deleteMember(String email) method
	 * in the Feature Set 1 MVC Controller
	 * 
	 * @param ActionEvent event- This is the clicking action.
	 */
	
	@FXML
	public void cancelBookingButton(ActionEvent event) {
		
		String email= cancelEmailTextField.getText();
		String name= cancelNameTextField.getText();
		
		if (email.equals("")) {
			ViewUtils.showError("Please input a valid email address");
		}
		
		if(name.equals("")) {
			ViewUtils.showError("Please input a valid name");
		}
		
		else if(ViewUtils.callController(() -> 
		      ClimbSafeFeatureSet1Controller.deleteMember(email))) {
			
			ViewUtils.makePopupWindow("Cancellation Successful", name+ "is no longer registered as a Member.");
			
			cancelNameTextField.setText("");
			cancelEmailTextField.setText("");
			cancelPasswordTextField.setText("");
						
		}
		
	}
	
}
