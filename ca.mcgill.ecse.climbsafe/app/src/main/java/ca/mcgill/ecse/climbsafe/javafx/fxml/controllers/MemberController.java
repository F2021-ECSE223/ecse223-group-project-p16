/**
 * 
 * Controller for the feature set 2 GUI.
 * 
 * @author Saif Shahin
 * TODO Possible bug in the action methods of the buttons. Eclipse is not
 * 		allowing me to test the fxml controller. It must be tested before it is implemented.
 * TODO Address comments on lines 94, 251, 291, 305.
 *		
 * 
 */

package ca.mcgill.ecse.climbsafe.javafx.fxml.controllers;

import java.beans.EventHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet2Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class MemberController {

	// initializing the register member interactable components

	boolean regHotelRequired;
	boolean regGuideRequired;
	//List<String> regItemNames = new ArrayList<String>();
	List<Integer> regItemQuantities = new ArrayList<Integer>();

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

	// Initializing the update member interactable components
	@FXML
	private TextField updateEnterEmailTextField;
	@FXML
	private TextField updateEnterPasswordTextField;
	@FXML
	private TextField updateNameTextField;
	@FXML
	private TextField updateContactNumTextField;
	@FXML
	private TextField updateNrWeeksTextField; //here atm
	@FXML
	private Button updateMtnGuideButtonYes;
	@FXML
	private Button updateMtnGuideButtonNo;
	@FXML
	private Button updateHotelButtonYes;
	@FXML
	private Button updateHotelButtonNo;
	@FXML
	private TextField updateEquipNamesTextField;
	@FXML
	private TextField updatePasswordTextField;
	@FXML
	private Button updateButton;

	// Initializing the cancel ooking interactable components
	@FXML
	private TextField cancelNameTextField;
	@FXML
	private Button cancelBookingButton;
	@FXML
	private TextField cancelPasswordTextField;
	@FXML
	private TextField cancelEmailTextField;

	
	//Note: from my understanding, my controller does not need an initialize method.
	
	// Event listeners on the buttons in the GUI
	@FXML
	public void regReqGuideYesButton(ActionEvent event) {
		
	
	
	regReqGuideYesButton.setOnAction(new EventHandler() {
	    @Override
	    public void handle(ActionEvent actionEvent) {
	    	regGuideRequired=true;
	    }
	});
	}

	@FXML
	public void regReqGuideNoButton(ActionEvent event) {

		regReqGuideNoButton.setOnAction(new EventHandler() {
		    @Override
		    public void handle(ActionEvent actionEvent) {
		    	regGuideRequired=false;
		    }
		});
	}

	@FXML
	public void regHotelYesButton(ActionEvent event) {

		regReqHotelYesButton.setOnAction(new EventHandler() {
		    @Override
		    public void handle(ActionEvent actionEvent) {
		    	regHotelRequired=true;
		    }
		});
		
	}

	@FXML
	public void regHotelNoButton(ActionEvent event) {

		regReqHotelNoButton.setOnAction(new EventHandler() {
		    @Override
		    public void handle(ActionEvent actionEvent) {
		    	regHotelRequired=false;
		    }
		});
		
	}

	@FXML
	public void registerButton(ActionEvent event) {
		
		String name = "";
		name=regNameTextField.getText();
		String email="";
		email=regEmailTextField.getText();
		String password= "";
		password=regPasswordTextField.getText();
		String emergencyContact= "";
		emergencyContact=regEmergencyContactTextField.getText();
		String nrWeeksString="";
		nrWeeksString=regNrWeeksTextField.getText();
	

		
		
		String[] items= regEquipNamesTextField.getText().split(","); //possible bug
		List <String> regItemNames = Arrays.asList(items);	
		
		String[] itemQuantities= regEquipQuantityTextField.getText().split(",");
		List <String> regItemQuantities= Arrays.asList(itemQuantities);	
				
		int nrWeeks=0;
		try {
			nrWeeks=Integer.parseInt(nrWeeksString);
		}
		catch (NumberFormatException ex){
			ViewUtils.showError("Please input a valid number of weeks you would like to climb.");
		}
		
		if(email.length>=1 && password.length>=1 && name.length>=1 && emergencyContact.length>=1 
				&& !nrWeeksStr.length.equals("")) {
		//adds the user inputs into the MVC FeatureSet2controlller. 
		ClimbSafeFeatureSet2Controller.registerMember(email, password, name,
				emergencyContact, nrWeeks, this.guideRequired, this.hotelRequired, 
				regItemNames, regItemQuantities);
		}
		else {
			ViewUtils.showError("Please input valid entries for all required feilds");
		}
		/*
		
		if(nrWeeksString.matches("[0-9]+")) {
			 nrWeeks= (int)nrWeeksString;
			 
		}
		
		if(name==null) {
			ViewUtils.showError("Please input a valid name"); //how do i link this to the MVC controllet?
			
		}
		else {
			if(sucessful(()-> 
		}
		*/
	}

	@FXML
	public void updateMtnGuideButtonYes(ActionEvent event) {

		updateMtnGuideButtonYes.setOnAction(new EventHandler() {
		    @Override
		    public void handle(ActionEvent actionEvent) {
		    	regGuideRequired=true;
		    }
		});
		
	}

	@FXML
	public void updateMtnGuideButtonNo(ActionEvent event) {

		updateMtnGuideButtonNo.setOnAction(new EventHandler() {
		    @Override
		    public void handle(ActionEvent actionEvent) {
		    	regGuideRequired=false;
		    }
		});
		
	}

	@FXML
	public void updateHotelButtonYes(ActionEvent event) {
		updateHotelButtonYes.setOnAction(new EventHandler() {
		    @Override
		    public void handle(ActionEvent actionEvent) {
		    	regHotelRequired=true;
		    }
		});
	}

	@FXML
	public void updateHotelButtonNo(ActionEvent event) {

		updateHotelButtonNo.setOnAction(new EventHandler() {
		    @Override
		    public void handle(ActionEvent actionEvent) {
		    	regHotelRequired=false;
		    }
		});
	}

	@FXML
	public void updateButton(ActionEvent event) {

		///do i need to do error handling like null cheking? or is that handled in the MVC controller?
		String email="";
		email=updateEnterEmailTextField.getText();
		String newPassword="";
		newPassword=updateEnterPasswordTextField.getText();
		String newName= "";
		newName=updateNameTextField.getText();
		String newEmergencyContact= "";
		newEmergencyContact=updateContactNumTextField.getText();
		String newNrWeeksStr= "";
		newNrWeeksStr=updateNrWeeksTextField.getText();
		
		int newNrWeeks=0;
		try {
			newNrWeeks=Integer.parseInt(newNrWeeksStr);
		}
		catch (NumberFormatException ex){
			ViewUtils.showError("Please input a valid number of weeks you would like to climb.");
		}
		
		String[] updateItems= regEwquipNamesTextField.getText().split(","); //possible bug. 
		List <String> newItemNames = Arrays.asList(updateItems);	
		
		String[] updateItemQuantities= regEquipQuantityTextField.getText().split(",");
		List <String> newItemQuantities= Arrays.asList(updateItemQuantities);	
		
		if(email.length>=1 && newPassword.length>=1 && newName.length>=1 && newEmergencyContact.length>=1 
				&& !newNrWeeksStr.length.equals("")) {
			ClimbSafeFeatureSet2Controller.updateMember(email, newPassword, newName, newEmergencyContact, 
					newNrWeeks,regGuideRequired, regHotelRequired, newItemNames, newItemQuantities);
		}
		else {
			ViewUtils.showError("Please input valid entries for all required feilds");
		}
		
	}
		

	@FXML
	public void cancelBookingButton(ActionEvent event) {
		//how do i check for the user name and password? should i remove those elements from the UI? 
		//It will look less professional if i do.
		
		String email="";
		email=cancelEmailTextField.getText();
		if (email.equals("")) {
			ViewUtils.showError("Please input a valid email address");
		}
		else {
			ClimbSafeFeatureSet1Controller.deleteMember(email);
		}
		
	}
	
	//Is this correct?
	@FunctionalInterface
	interface Executable {
	  public void execute() throws Throwable;
	}

}