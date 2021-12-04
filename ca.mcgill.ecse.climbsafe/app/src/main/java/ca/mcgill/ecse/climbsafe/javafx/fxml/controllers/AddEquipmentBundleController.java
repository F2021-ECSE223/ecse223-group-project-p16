package ca.mcgill.ecse.climbsafe.javafx.fxml.controllers;

import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet5Controller;
import ca.mcgill.ecse.climbsafe.controller.TOBundleEquipment;
import ca.mcgill.ecse.climbsafe.controller.TOEquipment;
import ca.mcgill.ecse.climbsafe.javafx.fxml.main.ClimbSafeFxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AddEquipmentBundleController {
  

    private List<TOBundleEquipment> bes = new ArrayList<TOBundleEquipment>();
  
    @FXML
    private TextField BundleNameTextField;
    @FXML
    private TextField DiscountTextField;
    @FXML
    private Button AddBundle;
    @FXML
    private ChoiceBox<TOEquipment> EquipmentChoiceBox;
    @FXML
    private TextField EquipmentQuantityTextField;
    @FXML
    private Button AddEquipmentToBundleButton;
    @FXML
    private TableView<TOBundleEquipment> EquipmentTable;
    
    /**
     * 
     * @author Samuel Valentine
     *
     */
    @FXML
    public void initialize() {
    // the choice boxes listen to the refresh event
      EquipmentChoiceBox.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
        EquipmentChoiceBox.setItems(ViewUtils.getEquipment());
        // reset the choice
        EquipmentChoiceBox.setValue(null);
      });

      // let the application be aware of the refreshable node
      ClimbSafeFxmlView.getInstance().registerRefreshEvent(EquipmentChoiceBox);
      ClimbSafeFxmlView.getInstance().registerRefreshEvent(EquipmentTable);
      
      
      EquipmentTable.getColumns().add(createTableColumn("Equipment", "equipmentName"));
      EquipmentTable.getColumns().add(createTableColumn("Quantity", "quantity"));

    }
    
    /**
     * 
     * @author Samuel Valentine
     *
     */
    public static TableColumn<TOBundleEquipment, String> createTableColumn(String header, String propertyName) {
           TableColumn<TOBundleEquipment, String> column = new TableColumn<>(header);
           column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
           return column;
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
     * 
     * @author Samuel Valentine
     *
     */
    @FXML
    public void AddEquipmentBundle(ActionEvent event) {
      String name = BundleNameTextField.getText();
      String discount = DiscountTextField.getText();
      
      if (name == null || name.trim().isEmpty()) {
          ViewUtils.showError("Please input a valid name");
      } 
      else if (discount == null || discount.trim().isEmpty() || (Integer.valueOf(discount) < 0)) {
          ViewUtils.showError("Please input a valid discount");
      }
      else {

//         reset the driver text field if success
        if (ViewUtils.successful(() -> 
        
        
        ClimbSafeFeatureSet5Controller.addEquipmentBundle(name, Integer.parseInt(discount), getBeNames(), getBeQuantities()))) {
            ViewUtils.makePopupWindow("Registration Successful", name + " has been registered as an equipment bundle.");
            
            BundleNameTextField.setText("");
            DiscountTextField.setText("");
            EquipmentQuantityTextField.setText("");
            EquipmentTable.getItems().clear();
            EquipmentChoiceBox.setValue(null);
            bes.clear();

         
        }
        
      }
    }
    
    /**
     * 
     * @author Samuel Valentine
     *
     */
    @FXML
    public void addEquipmentToBundle(ActionEvent event) {
      
      if (EquipmentChoiceBox.getValue() == null) {
        ViewUtils.showError("Please choose an equipment to add.");
      }
      else if (EquipmentQuantityTextField.getText().isEmpty()) {
        ViewUtils.showError("Please choose a quantity.");        
      }
      else {
        
        if (getBeNames().contains(EquipmentChoiceBox.getValue().getEquipmentName())) {
          
          for (TOBundleEquipment b : bes) {
            
            if (b.getEquipmentName().equals(EquipmentChoiceBox.getValue().getEquipmentName())) {
              
              b.setQuantity(b.getQuantity() + Integer.valueOf(EquipmentQuantityTextField.getText()));
              
              EquipmentTable.getItems().set(bes.indexOf(b), b);
              
              break;
              
            }
            
          }
          
        }
        
        else {
        
          TOBundleEquipment be = new TOBundleEquipment(EquipmentChoiceBox.getValue().getEquipmentName(), Integer.valueOf(EquipmentQuantityTextField.getText()));
          
          EquipmentTable.getItems().add(be);
          
          bes.add(be);
          
        }
        
        
        EquipmentQuantityTextField.setText("");
        EquipmentChoiceBox.setValue(null);
      }
      
      
    }
}