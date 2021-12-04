package ca.mcgill.ecse.climbsafe.javafx.fxml.controllers;

import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet5Controller;
import ca.mcgill.ecse.climbsafe.controller.TOBundleEquipment;
import ca.mcgill.ecse.climbsafe.controller.TOEquipment;
import ca.mcgill.ecse.climbsafe.controller.TOEquipmentBundle;
import ca.mcgill.ecse.climbsafe.javafx.fxml.main.ClimbSafeFxmlView;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * 
 * @author Samuel Valentine
 *
 */
public class UpdateEquipmentBundleController {
  
  private List<TOBundleEquipment> bes = new ArrayList<TOBundleEquipment>();
//  
    @FXML
    private ChoiceBox<TOEquipmentBundle> BundleChoiceBox;
    @FXML
    private TableView<TOBundleEquipment> EquipmentTable;
    @FXML
    private Button RemoveEquipmentButton;
    @FXML
    private Button AddEquipmentButton;
    @FXML
    private Button RefreshTableButton;
    @FXML
    private TextField EquipmentQuantityTextBoxToAdd;
    @FXML
    private TextField NewNameTextField;
    @FXML
    private TextField NewDiscountTextField;
    @FXML
    private ChoiceBox<TOEquipment> SelectEquipmentToAddChoiceBox;
    @FXML
    private Button UpdateBundleButton;
    
    /**
     * @author Samuel Valentine
     */
    @FXML
    public void initialize() {
      BundleChoiceBox.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
        BundleChoiceBox.setItems(ViewUtils.getEquipmentBundles());
        // reset the choice
        BundleChoiceBox.setValue(null);
      });
      
      
      SelectEquipmentToAddChoiceBox.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
        SelectEquipmentToAddChoiceBox.setItems(ViewUtils.getEquipment());
        // reset the choice
        SelectEquipmentToAddChoiceBox.setValue(null);
      });
      
      EquipmentTable.getColumns().add(createTableColumn("Equipment", "equipmentName"));
      EquipmentTable.getColumns().add(createTableColumn("Quantity", "quantity"));
      
      ClimbSafeFxmlView.getInstance().registerRefreshEvent(BundleChoiceBox);
      ClimbSafeFxmlView.getInstance().registerRefreshEvent(SelectEquipmentToAddChoiceBox);
      ClimbSafeFxmlView.getInstance().registerRefreshEvent(EquipmentTable);
      
    }
    
    /**
     * @author Samuel Valentine
     */
    public static TableColumn<TOBundleEquipment, String> createTableColumn(String header, String propertyName) {
       TableColumn<TOBundleEquipment, String> column = new TableColumn<>(header);
       column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
       return column;
   }
    
    /**
     * @author Samuel Valentine
     */
    @FXML
    public void removeEquipment(ActionEvent event) {
      
      if (EquipmentTable.getSelectionModel().getSelectedItem() == null) {
        
        ViewUtils.showError("Please select an item from the table that you would like to remove.");
        
      }
      else {
        TOBundleEquipment be = new TOBundleEquipment(EquipmentTable.getSelectionModel().getSelectedItem().getEquipmentName(),(EquipmentTable.getSelectionModel().getSelectedItem().getQuantity()));
       
        EquipmentTable.getItems().removeAll(EquipmentTable.getSelectionModel().getSelectedItem());
        
        
        bes.clear();
        bes.addAll(EquipmentTable.getItems());
      
      }
    }
    
    /**
     * @author Samuel Valentine
     */
    @FXML
    public void addEquipment(ActionEvent event) {
      
      if (SelectEquipmentToAddChoiceBox.getValue() == null) {
        ViewUtils.showError("Please choose an equipment to add.");
      }
      else if (EquipmentQuantityTextBoxToAdd.getText().isEmpty()) {
        ViewUtils.showError("Please choose a quantity.");        
      }
      else {
        
        if (getBeNames().contains(SelectEquipmentToAddChoiceBox.getValue().getEquipmentName())) {
          
          for (TOBundleEquipment b : bes) {
            
            if (b.getEquipmentName().equals(SelectEquipmentToAddChoiceBox.getValue().getEquipmentName())) {
              
              b.setQuantity(b.getQuantity() + Integer.valueOf(EquipmentQuantityTextBoxToAdd.getText()));
              
              EquipmentTable.getItems().set(bes.indexOf(b), b);
              
              break;
              
            }
            
          }
          
        }
        
        else {
        
          TOBundleEquipment be = new TOBundleEquipment(SelectEquipmentToAddChoiceBox.getValue().getEquipmentName(), Integer.valueOf(EquipmentQuantityTextBoxToAdd.getText()));
          
          EquipmentTable.getItems().add(be);
          
          bes.add(be);
          
        }
        
        
        EquipmentQuantityTextBoxToAdd.setText("");
        SelectEquipmentToAddChoiceBox.setValue(null);
      }
      
    }
    
    /**
     * 
     * @author Samuel Valentine
     * 
     */
    public List<String> getBeNames(){

      List<String> beNames = new ArrayList<String>();
      for (TOBundleEquipment be : bes) {
        beNames.add(be.getEquipmentName());
      }
      return beNames;
    }
    
    /**
     * 
     * @author Samuel Valentine
     * 
     */
    public List<Integer> getBeQuantities(){
      
      List<Integer> beQuantities = new ArrayList<Integer>();
      for (TOBundleEquipment be : bes) {
        beQuantities.add(Integer.valueOf(be.getQuantity()));
      }
      return beQuantities;
    }
    
    
    /**
     * @author Samuel Valentine
     */
    @FXML
    public void updateBundle(ActionEvent event) {
      
      
      
      
      if (NewNameTextField.getText() == null || NewNameTextField.getText().trim().isEmpty()) {
          ViewUtils.showError("Please input a valid name.");
      } 
      else if (NewDiscountTextField.getText() == null || NewDiscountTextField.getText().trim().isEmpty() || (Integer.valueOf(NewDiscountTextField.getText()) < 0)) {
          ViewUtils.showError("Please input a valid discount.");
      }
      else if (BundleChoiceBox.getValue() == null) {
          ViewUtils.showError("Please select a bundle.");
      } 
       
      else {
        String newName = NewNameTextField.getText();
        String oldName = BundleChoiceBox.getValue().getEquipmentBundleName();
        String discount = NewDiscountTextField.getText();
        
        
//         reset the driver text field if success
        if (ViewUtils.successful(() -> 
        
        
        ClimbSafeFeatureSet5Controller.updateEquipmentBundle(oldName, newName, Integer.parseInt(discount), getBeNames(), getBeQuantities()))) {
            ViewUtils.makePopupWindow("Registration Successful", newName + " has been updated.");

         
        }
        NewNameTextField.setText("");
        NewDiscountTextField.setText("");
        EquipmentQuantityTextBoxToAdd.setText("");
        BundleChoiceBox.setValue(null);
        bes.clear();
      }
    }
    
    /**
     * @author Samuel Valentine
     */
    @FXML
    public void refreshTable(ActionEvent event) {
      
      if (BundleChoiceBox.getValue() == null) {
        ViewUtils.showError("Please choose a bundle to set to the table.");
      }
      else {
        
      
      
      EquipmentTable.getItems().clear();
     
      EquipmentTable.getItems().setAll(FXCollections.observableList(ViewUtils.getEquipmentForSpecificBundle(BundleChoiceBox.getValue())));
      
//      String bundleName = BundleChoiceBox.getValue().getEquipmentBundleName();
      
      bes.addAll(ViewUtils.getEquipmentForSpecificBundle(BundleChoiceBox.getValue()));
      }
//      SelectEquipmentToRemoveChoiceBox.setItems(ViewUtils.getEquipmentForSpecificBundle(BundleChoiceBox.getValue()));
    }
    
}