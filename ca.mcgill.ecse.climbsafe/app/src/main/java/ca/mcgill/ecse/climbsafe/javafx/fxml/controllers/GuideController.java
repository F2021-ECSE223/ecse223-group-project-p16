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


    /**
	   * registerGuide - Registers a guide in the ClimbSafe database.
	   * @param ActionEvent event
	   * @author Yakir Bender
	   */
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
    
    /**
	   * updateGuide - Updates a guide's information in the ClimbSafe database.
	   * @param ActionEvent event
	   * @author Onyekachi Ezekwem, Saif Shahin, Yakir Bender
	   */
    @FXML
    void updateGuide(ActionEvent event) {
    	TOGuide guide = DeleteGuideBox.getValue();
		String email = EmailTextField.getText();
		String password = PasswordTextField.getText();
		String name = UserNameTextField.getText();
	    String emergencyContact = EmergencyContactTextField.getText();
	    
	    if (guide == null) {
	    	ViewUtils.showError("Please select a guide!");
	    }
	    if (!email.equals(guide.getGuideEmail())) {
	    	ViewUtils.showError("You can not change the guide email!");
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
	    	  DeleteGuideBox.setValue(null);
	      }
	    }

    }

    /**
	   * deleteGuide - Deletes a guide from the ClimbSafe database.
	   * @param ActionEvent event
	   * @author Onyekachi Ezekwem, Saif Shahin, Yakir Bender
	   */
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
	    	  EmailTextField.setText("");
	    	  PasswordTextField.setText("");
	    	  UserNameTextField.setText("");
	    	  EmergencyContactTextField.setText("");
	    	  DeleteGuideBox.setValue(null);
	    	  ViewUtils.makePopupWindow("Deletion Successful", "The following Guide has been deleted: " + guide.getGuideEmail());
	      }
	    }

    }
    
    /**
	 * initialize() - method that refreshes the page on click on any choiceBox
	 * @param ActionEvent event
	 * @author Yakir Bender
	 * **/
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
