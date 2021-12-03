package ca.mcgill.ecse.climbsafe.javafx.fxml.controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet4Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet6Controller;
import ca.mcgill.ecse.climbsafe.controller.TOEquipment;
import ca.mcgill.ecse.climbsafe.javafx.fxml.main.ClimbSafeFxmlView;
import javafx.event.ActionEvent;

import javafx.scene.control.ChoiceBox;

public class EquipmentController {
  @FXML
  private Button addButton;
  @FXML
  private Button deleteButton;
  @FXML
  private TextField addName;
  @FXML
  private TextField addWeight;
  @FXML
  private TextField addPrice;
  @FXML
  private Button updateButton;
  @FXML
  private ChoiceBox deleteDropDownBar;
  @FXML
  private TextField updateOldName;
  @FXML
  private TextField updateNewName;
  @FXML
  private TextField updateNewWeight;
  @FXML
  private TextField updateNewPrice;


  @FXML
  public void initialize() {
    deleteDropDownBar.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
      deleteDropDownBar.setItems(ViewUtils.getEquipment());
      deleteDropDownBar.setValue(null);
    });

    ClimbSafeFxmlView.getInstance().registerRefreshEvent(deleteDropDownBar);
  }

  // Event Listener on Button[#addButton].onAction
  @FXML
  public void addClicked(ActionEvent event) {

    String name = addName.getText();
    Integer weight = null;
    Integer price = null;
    try {
      weight = Integer.parseInt(addWeight.getText());
    } catch (NumberFormatException e) {
    }
    try {
      price = Integer.parseInt(addPrice.getText());
    } catch (NumberFormatException e) {
    }

    if (name == null || name.trim().isEmpty()) {
      ViewUtils.showError("Please input a valid equipment name");
    } else if (weight == null) {
      ViewUtils.showError("Please input a valid weight");
    } else if (price == null) {
      ViewUtils.showError("Please input a valid price per week");
    } else {
      // reset the driver text field if success
      if (ViewUtils
          .successful(() -> ClimbSafeFeatureSet4Controller.addEquipment(name, Integer.parseInt(addWeight.getText()), Integer.parseInt(addPrice.getText())))) {
        addName.setText("");
        addWeight.setText("");
        addPrice.setText("");
        ViewUtils.makePopupWindow("Equipment adding successful", "Successfully added equipment!");
      }
    }
  }
  
  
  // Event Listener on Button[#deleteButton].onAction
  @FXML
  public void deleteClicked(ActionEvent event) {
    
    TOEquipment equipment = (TOEquipment) deleteDropDownBar.getValue();
    if (equipment == null) {
      ViewUtils.showError("Please select a valid equipment");
    } else {
      ViewUtils.callController(() -> ClimbSafeFeatureSet6Controller.deleteEquipment(equipment.toString()));
      ViewUtils.makePopupWindow("Equipment deleting successful", "Successfully deleted equipment!");
    }
    
  }

  // Event Listener on Button[#updateButton].onAction
  @FXML
  public void updateClicked(ActionEvent event) {
    String newName = updateNewName.getText();
    String oldName = updateOldName.getText();
    Integer newWeight = null;
    Integer newPrice = null;
    try {
      newWeight = Integer.parseInt(updateNewWeight.getText());
    } catch (NumberFormatException e) {
    }
    try {
      newPrice = Integer.parseInt(updateNewPrice.getText());
    } catch (NumberFormatException e) {
    }

    if (newName == null || newName.trim().isEmpty()) {
      ViewUtils.showError("Please input a valid new equipment name");
    } else if (oldName == null || oldName.trim().isEmpty()) {
      ViewUtils.showError("Please input a valid old equipment name");
    } else if (newWeight == null) {
      ViewUtils.showError("Please input a valid weight");
    } else if (newPrice == null) {
      ViewUtils.showError("Please input a valid price per week");
    } else {
      // reset the driver text field if success
      if (ViewUtils
          .successful(() -> ClimbSafeFeatureSet4Controller.updateEquipment(oldName, newName, Integer.parseInt(updateNewWeight.getText()), Integer.parseInt(updateNewPrice.getText())))) {
        addName.setText("");
        addWeight.setText("");
        addPrice.setText("");
        ViewUtils.makePopupWindow("Equipment update successful", "Successfully updated equipment!");
      }
    }
  }
}
