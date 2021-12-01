package ca.mcgill.ecse.climbsafe.javafx.fxml.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet3Controller;
import ca.mcgill.ecse.climbsafe.javafx.fxml.controllers.ViewUtils;

public class GuideController {

	
    @FXML
    private TextField UserNameTextField;
    
    @FXML
    private TextField EmailTextField;

    @FXML
    private TextField EmergencyContactTextField;

    @FXML
    private TextField PasswordTextField;
    
    @FXML
    private Button AddGuideButton;
    
    @FXML
    private Button UpdateGuideButton;

    @FXML
    private Button DeleteGuideButton;


    @FXML
    void addGuide(ActionEvent event) {
    	
    	String email = EmailTextField.getText();
		String password = PasswordTextField.getText();
		String name = UserNameTextField.getText();
	    String emergencyContact = EmergencyContactTextField.getText();
	    
	    if (email == null || email.trim().isEmpty()) {
	    	ViewUtils.showError("Please input a valid email address");
	    } 
	    else if (password == null || password.trim().isEmpty()) {
	    	ViewUtils.showError("Please input a valid password");
		}
	    else if (name == null || name.trim().isEmpty()) {
	    	ViewUtils.showError("Please input a valid name");
		}
	    else if (emergencyContact == null || emergencyContact.trim().isEmpty()) {
	    	ViewUtils.showError("Please input a valid emergency contact");
		}
	    else {
	      // reset the driver text field if success
	      if (ViewUtils.successful(() -> 
	      ClimbSafeFeatureSet3Controller.registerGuide(email, password, name, emergencyContact))) {
	    	  ViewUtils.makePopupWindow("Registration Successful", name + " has been registered as a Guide.");
	    	  
	    	  EmailTextField.setText("");
	    	  PasswordTextField.setText("");
	    	  UserNameTextField.setText("");
	    	  EmergencyContactTextField.setText("");
	      }
	    }

    }
    
    @FXML
    void updateGuide(ActionEvent event) {
    	
		String email = EmailTextField.getText();
		String password = PasswordTextField.getText();
		String name = UserNameTextField.getText();
	    String emergencyContact = EmergencyContactTextField.getText();
	    
	    if (email == null || email.trim().isEmpty()) {
	    	ViewUtils.showError("Please input a valid email address");
	    } 
	    else if (password == null || password.trim().isEmpty()) {
	    	ViewUtils.showError("Please input a valid password");
		}
	    else if (name == null || name.trim().isEmpty()) {
	    	ViewUtils.showError("Please input a valid name");
		}
	    else if (emergencyContact == null || emergencyContact.trim().isEmpty()) {
	    	ViewUtils.showError("Please input a valid emergency contact");
		}
	    else {
	      // reset the driver text field if success
	      if (ViewUtils.successful(() -> 
	      ClimbSafeFeatureSet3Controller.updateGuide(email, password, name, emergencyContact))) {
	    	  ViewUtils.makePopupWindow("Update Successful", name + "'s Guide information has been updated.");
	    	  
	    	  EmailTextField.setText("");
	    	  PasswordTextField.setText("");
	    	  UserNameTextField.setText("");
	    	  EmergencyContactTextField.setText("");
	      }
	    }

    }

    @FXML
    void deleteGuide(ActionEvent event) {
    	

		String email = EmailTextField.getText();
	    
	    if (email == null || email.trim().isEmpty()) {
	    	ViewUtils.showError("Please input a valid email address");
	    } 
	    else {
	      // reset the driver text field if success
	      if (ViewUtils.successful(() -> 
	      ClimbSafeFeatureSet1Controller.deleteGuide(email))) {
	    	  ViewUtils.makePopupWindow("Deletion Successful", "The Guide with the email " + email + " has been deleted.");
	    	  
	    	  EmailTextField.setText("");
	      }
	    }

    }

}


