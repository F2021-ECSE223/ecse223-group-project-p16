package ca.mcgill.ecse.climbsafe.javafx.fxml.controllers;

import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet3Controller;
import ca.mcgill.ecse.climbsafe.controller.TOGuide;
import ca.mcgill.ecse.climbsafe.javafx.fxml.main.ClimbSafeFxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

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
    private ChoiceBox<TOGuide> DeleteGuideBox;
    
    @FXML
    private Button RegisterGuideButton;
    
    @FXML
    private Button UpdateGuideButton;

    @FXML
    private Button DeleteGuideButton;


    @FXML
    void registerGuide(ActionEvent event) {
    	
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
	      if (ViewUtils.callController(() -> 
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
	      if (ViewUtils.callController(() -> 
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
    	

		TOGuide guide = DeleteGuideBox.getValue();
	    
	    if (guide == null) {
	    	ViewUtils.showError("Please select a valid email address.");
	    } 
	    else {
	      // reset the driver text field if success
	      if (ViewUtils.callController(() -> 
	      ClimbSafeFeatureSet1Controller.deleteGuide(guide.getGuideEmail()))) {
	    	  ViewUtils.makePopupWindow("Deletion Successful", "The following Guide has been deleted: " + guide.getGuideEmail());
	      }
	    }

    }
    
    @FXML
    public void initialize() {
      // the choice boxes listen to the refresh event
      DeleteGuideBox.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
    	DeleteGuideBox.setItems(ViewUtils.getGuides());
        // reset the choice
    	DeleteGuideBox.setValue(null);
      });

      // let the application be aware of the refreshable node
      ClimbSafeFxmlView.getInstance().registerRefreshEvent(DeleteGuideBox);
    }

}
