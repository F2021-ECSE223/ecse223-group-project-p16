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

import java.util.Arrays;

import java.util.List;

public class MemberController {
	
	// initializing the register member interactable components
		private static ClimbSafe climbsafe = ClimbSafeApplication.getClimbSafe();
		boolean regHotelRequired;
		boolean regGuideRequired;
		//List<String> regItemNames = new ArrayList<String>();
		//List<Integer> regItemQuantities = new ArrayList<Integer>();
	
		@FXML
		private Button refreshButton;
		@FXML
		private TextField regNameTextFeild;
		@FXML
		private TextField regEmailTextFeild;
		@FXML
		private TextFeild regPasswordTextFeild;
		@FXML
		private TextFeild regEmergencyContactTextFeild;
		@FXML
		private TextFeild regNrWeeksTextFeild;
		@FXML
		private TextFeild regEquipNamesTextFeild;
		@FXML
		private TextFeild regEquipQuantityTextFeild;
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
		private TextFeild cancelNameTextFeild;
		@FXML
		private TextField cancelEmailTextFeild;
		@FXML
		private TextField cancelPasswordTextFeild;

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
		
		String name =regNameTextFeild.getText();
		String email=regEmailTextFeild.getText();
		String password=regPasswordTextFeild.getText();
		String emergencyContact=regEmergencyContactTextFeild.getText();
		String nrWeeksString=regNrWeeksTextFeild.getText();
		
		String[] items= regEquipNamesTextFeild.getText().split(","); 
		List <String> regItemNames = Arrays.asList(items);	
/*
 * I chose to use a try catch block and a pop up message here as converting an array of Strings into a list of 
 * Integers otherwise requires the use of the Stream.map(), whcih is outside the scope of my programming knowledge and thus prone to bugs.
 */
	try {
		String[] itemQuantities= regEquipQuantityTextFeild.getText().split(","); 
		int size=itemQuantities.length;
		Integer [] arr=new Integer[size];
		for(int i=0; i<size; i++) {
			arr[i]=Integer.parseInt(itemQuantities[i]);
		}
		List <Integer> regItemQuantities= Arrays.asList(arr);	
		
	}
	catch (NumberFormatException ex) {
		ViewUtils.makePopupWindow("Please enter the equipment quantities you require as a list seperated by commas only.\n"
				+"For example, enter the quanitites as such: (4,3,7) not (4, 3, 7)");
	}
		int nrWeeks=0;
		try {
			nrWeeks=Integer.parseInt(nrWeeksString); 
		}
		catch (NumberFormatException ex){
			ViewUtils.showError("Please input a valid number of weeks you would like to climb."); //IMPORTANT: Only works if ViewUtils is in the buildpath of the project. 
		}
		
		if(email.length>=1 && password.length>=1 && name.length>=1 && emergencyContact.length>=1
				&& nrWeeksStr.length>=1) {
			
			if(nrWeeks==0) {
				ViewUtils.makePopupWindow("Please input a valid number of weeks you would like to climb.");
			}
		
			//adds the user inputs into the MVC FeatureSet2controlller. 
			else if (ViewUtils.callController(() -> 
		      ClimbSafeFeatureSet2Controller.registerMember(email, password, name, emergencyContact, nrWeeks, this.regGuideRequired, 
		    		  this.regHotelRequired, regItemNames, regItemQuantities ))) {
				
				ViewUtils.makePopupWindow("You have sucessfully registered as a member");
		
				regNameTextFeild.setText("");
				regEmailTextFeild.setText("");
				regPasswordTextFeild.setText("");
				regEmergencyContactTextFeild.setText("");
				regNrWeeksTextFeild.setText("");
				items=null;
				itemQuantities=null;
				nrWeeks=0;
				this.regHotelRequired=false;
				this.regGuideRequired=false;
				
			}
		
	}
		else {
			 ViewUtils.makePopupWindow("Registration Failed, please input valid entries for all feilds. "
			 		+ "If you need help, see the boxes in blue.");
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
		
		String newName =regNameTextFeild.getText();
		String newEmail=regEmailTextFeild.getText();
		String newPassword=regPasswordTextFeild.getText();
		String newEmergencyContact=regEmergencyContactTextFeild.getText();
		String newNrWeeksString=regNrWeeksTextFeild.getText();
		
		String[] newItems= regEquipNamesTextFeild.getText().split(","); 
		List <String> regNewItemNames = Arrays.asList(items);	
	
	try {
		String[] newItemQuantities= regEquipQuantityTextFeild.getText().split(","); 
		int size=newItemQuantities.length;
		Integer [] arr=new Integer[size];
		for(int i=0; i<size; i++) {
			arr[i]=Integer.parseInt(newItemQuantities[i]);
		}
		List <Integer> regNewItemQuantities= Arrays.asList(arr);	
		
	}
	catch (NumberFormatException ex) {
		ViewUtils.makePopupWindow("Please enter the equipment quantities you require as a list seperated by commas only.\n"
				+"For example, enter the quanitites as such: (4,3,7) not (4, 3, 7)");
	}
		
		int newNrWeeks=0;
		try {
			newNrWeeks=Integer.parseInt(newNrWeeksString); 
		}
		catch (NumberFormatException ex){
			ViewUtils.showError("Please input a valid number of weeks you would like to climb."); //IMPORTANT: Only works if ViewUtils is in the buildpath of the project. 
		}
		
		if(newEmail.length>=1 && newPassword.length>=1 && newName.length>=1 && newEmergencyContact.length>=1
				&& newNrWeeksStr.length>=1) {
		
			if(newNrWeeks==0) {
				ViewUtils.makePopupWindow("Please input a valid number of weeks you would like to update your climb to.");
			}
		
			else if(ViewUtils.callController(() -> 
		      ClimbSafeFeatureSet2Controller.updateMember(newEmail, newPassword, newName, newEmergencyContact, newNrWeeks, this.regGuideRequired, 
		    		  this.regHotelRequired, regNewItemNames, regNewItemQuantities ))) {
				
				ViewUtils.makePopupWindow("You have sucessfully updated your booking.");
				
				regNameTextFeild.setText("");
				regEmailTextFeild.setText("");
				regPasswordTextFeild.setText("");
				regEmergencyContactTextFeild.setText("");
				regNrWeeksTextFeild.setText("");
				newItems=null;
				newItemQuantities=null;
				newNrWeeks=0;
				this.regHotelRequired=false;
				this.regGuideRequired=false;
				
			}
	}
		else {
			 ViewUtils.makePopupWindow("You have failed to update your booking, please input valid entries for all feilds. "
			 		+ "If you need help, see the boxes in blue.");
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
		
		String email= cancelEmailTextFeild.getText();
		
		if (email.equals("")) {
			ViewUtils.showError("Please input a valid email address");
		}
		else if(ViewUtils.callController(() -> 
		      ClimbSafeFeatureSet1Controller.deleteMember(email))) {
			
			ViewUtils.makePopupWindow("You have sucessfully updated your booking.");
			
			cancelNameTextFeild.setText("");
			cancelEmailTextFeild.setText("");
			cancelPasswordTextFeild.setText("");
						
		}
		
	}
	
}

