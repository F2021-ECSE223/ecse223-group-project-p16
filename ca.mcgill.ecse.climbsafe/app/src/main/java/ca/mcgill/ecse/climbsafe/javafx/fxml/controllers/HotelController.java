package ca.mcgill.ecse.climbsafe.javafx.fxml.controllers;

import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet7Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * @author Tushar Srivastava - ID: 260922439
 * HotelController for Iteration 4
 */

/**
 * @author Tushar Srivastava - ID: 260922439
 * HotelController for Iteration 4
 */

public class HotelController {
	@FXML
	private Button AddHotelButton;
	@FXML
	private Button RemoveHotelButton;
	@FXML
	private TextField HotelNameTextField;
	@FXML
	private TextField OldHotelNameTextField;
	@FXML
	private TextField NewHotelNameTextField;
	@FXML
	private TextField NewHotelAddressTextField;
	@FXML
	private TextField NewStarRatingTextField;
	@FXML
	private Button UpdateHotelButton;
	@FXML
	private TextField HotelAddressTextField;
	@FXML
	private TextField  StarRatingTextField;
	@FXML
	private TextField  HotelDeleteTextField;

	/**
	 * This method gets called when the Add Hotel button is clicked on the GUI. 
	 * It takes the user input and sends the necessary parameters (name, address, rating/nrStars) to the addHotel method in the Climbsafe Feature Set 7 controller.
	 **/
	// Event Listener on Button[#AddHotelButton].onAction
	
	@FXML
	void addHotel (ActionEvent event) {
		
		String name = HotelNameTextField.getText();
		String address = HotelAddressTextField.getText();
		String nrStars = StarRatingTextField.getText();
	    
	    if (name == null || name.trim().isEmpty()) {
	    	ViewUtils.showError("Please input a valid hotel name");
	    } 
	    else if (address == null || address.trim().isEmpty()) {
	    	ViewUtils.showError("Please input a valid hotel address");
		}
	    else if (nrStars == null || nrStars.trim().isEmpty()) {
	    	ViewUtils.showError("Please input a valid hotel rating");
		}
	    else {
	      // reset the driver text field if success
	      if (ViewUtils.callController(() -> 
	      ClimbSafeFeatureSet7Controller.addHotel(name, address, Integer.valueOf(nrStars)))) {
	    	  ViewUtils.makePopupWindow("Hotel Added", name + " has been added successfully.");
	    	  
	    	  HotelNameTextField.setText("");
	    	  HotelAddressTextField.setText("");
	    	  StarRatingTextField.setText("");
	      }
	    }
	}
	    
	/**
	 * This method gets called when the Remove Hotel button is clicked on the GUI. 
	 * It takes the user input and sends the necessary parameter to the deleteHotel(String name) method in the Climbsafe Feature Set 1 controller.
	 **/
	// Event Listener on Button[#RemoveHotelButton].onAction
	
	@FXML
	 void removeHotel(ActionEvent event) {
	
		String name= HotelDeleteTextField.getText();
		
		if (name == null || name.trim().isEmpty()) {
		    	ViewUtils.showError("Please input a valid hotel name");
		}
		
		else {
		      // reset the driver text field if success
		      if (ViewUtils.callController(() -> 
		      ClimbSafeFeatureSet1Controller.deleteHotel(name))) {
		    	  ViewUtils.makePopupWindow("Hotel Removed", name + " has been deleted successfully.");
		    	  
		    	  HotelDeleteTextField.setText("");
		      }
		}
		
	}
	
	/**
	 * This method gets called when the Update Hotel button is clicked on the GUI. 
	 * It takes the user input and sends the necessary parameters to the updateHotel(String oldName, String newName, String newAddress, int NewNrStars) method in the Climbsafe Feature Set 7 controller.
	 **/
	// Event Listener on Button[#UpdateHotelButton].onAction
	
	@FXML
	 void updateHotel(ActionEvent event) {
		
		String oldName= OldHotelNameTextField.getText();
		String newName= NewHotelNameTextField.getText();
	    String newAddress= NewHotelAddressTextField.getText();
	    String NewNrStars= NewStarRatingTextField.getText();
	    
	    if (oldName == null || oldName.trim().isEmpty()) {
	    	ViewUtils.showError("Please input a valid hotel name");
	    }
	    else if (newName == null || newName.trim().isEmpty()) {
	    	ViewUtils.showError("Please input a valid hotel name");
	    }
	    else if (newAddress == null || newAddress.trim().isEmpty()) {
	    	ViewUtils.showError("Please input a valid hotel address");
		}
	    else if (NewNrStars == null || NewNrStars.trim().isEmpty()) {
	    	ViewUtils.showError("Please input a valid hotel rating");
		}
	    else {
	      // reset the driver text field if success
	      if (ViewUtils.callController(() -> 
	      ClimbSafeFeatureSet7Controller.updateHotel(oldName, newName, newAddress, Integer.valueOf(NewNrStars)))) {
	    	  ViewUtils.makePopupWindow("Hotel Updated", newName + " has been updated successfully.");
	    	  
	    	  OldHotelNameTextField.setText("");
	    	  NewHotelNameTextField.setText("");
	    	  NewHotelAddressTextField.setText("");
	    	  NewStarRatingTextField.setText("");
	      }
	    }
	}
	 
}

