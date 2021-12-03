package ca.mcgill.ecse.climbsafe.javafx.fxml.controllers;

import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet5Controller;
import ca.mcgill.ecse.climbsafe.controller.TOEquipment;
import ca.mcgill.ecse.climbsafe.controller.TOEquipmentBundle;
import ca.mcgill.ecse.climbsafe.javafx.fxml.main.ClimbSafeFxmlView;
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
  
    private List<TOEquipment> Equipments = new ArrayList<TOEquipment>();
    private List<Integer> EquipmentQuantities = new ArrayList<Integer>();
  
    @FXML
    private ChoiceBox<TOEquipmentBundle> BundleChoiceBox;
    @FXML
    private TableView<TOEquipment> EquipmentTable;
    @FXML
    private Button RemoveEquipmentButton;
    @FXML
    private Button AddEquipmentButton;
    @FXML
    private Button RefreshTableButton;
    @FXML
    private TextField EquipmentQuantityTextBoxToAdd;
    @FXML
    private TextField EquipmentQuantityTextBoxToRemove;
    @FXML
    private TextField NewNameTextField;
    @FXML
    private TextField NewDiscountTextField;
    @FXML
    private ChoiceBox<TOEquipment> SelectEquipmentToRemoveChoiceBox;
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
      
      ClimbSafeFxmlView.getInstance().registerRefreshEvent(BundleChoiceBox);
      ClimbSafeFxmlView.getInstance().registerRefreshEvent(SelectEquipmentToAddChoiceBox);
      ClimbSafeFxmlView.getInstance().registerRefreshEvent(SelectEquipmentToRemoveChoiceBox);
      ClimbSafeFxmlView.getInstance().registerRefreshEvent(EquipmentTable);
      
    }
    
    /**
     * @author Samuel Valentine
     */
    public static TableColumn<TOEquipment, String> createTableColumn(String header, String propertyName) {
       TableColumn<TOEquipment, String> column = new TableColumn<>(header);
       column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
       return column;
   }
    
    /**
     * @author Samuel Valentine
     */
    @FXML
    public void removeEquipment(ActionEvent event) {
      EquipmentTable.getItems().remove(SelectEquipmentToRemoveChoiceBox.getValue().getEquipmentName());
      
      Equipments.remove(SelectEquipmentToRemoveChoiceBox.getValue());
      EquipmentQuantities.remove(Integer.valueOf(EquipmentQuantityTextBoxToRemove.getText()));
      EquipmentQuantityTextBoxToRemove.setText("");
      SelectEquipmentToRemoveChoiceBox.setValue(null);
      
    }
    
    /**
     * @author Samuel Valentine
     */
    @FXML
    public void addEquipment(ActionEvent event) {
      
      EquipmentTable.getItems().add(SelectEquipmentToAddChoiceBox.getValue());
      
      Equipments.add(SelectEquipmentToAddChoiceBox.getValue());
      EquipmentQuantities.add(Integer.valueOf(EquipmentQuantityTextBoxToAdd.getText()));
      EquipmentQuantityTextBoxToAdd.setText("");
      SelectEquipmentToAddChoiceBox.setValue(null);
    }
    
    /**
     * @author Samuel Valentine
     */
    @FXML
    public void updateBundle(ActionEvent event) {
      
      String oldName = BundleChoiceBox.getValue().getEquipmentBundleName();
      String newName = NewNameTextField.getText();
      String discount = NewDiscountTextField.getText();
      
      if (newName == null || newName.trim().isEmpty()) {
          ViewUtils.showError("Please input a valid name");
      } 
      else if (discount == null || discount.trim().isEmpty() || (Integer.valueOf(discount) < 0)) {
          ViewUtils.showError("Please input a valid discount");
      }
      else {

//         reset the driver text field if success
        if (ViewUtils.successful(() -> 
        
        
        ClimbSafeFeatureSet5Controller.updateEquipmentBundle(oldName, newName, Integer.parseInt(discount), ViewUtils.getEquipmentNamesListFromTOEquipmentList(Equipments), EquipmentQuantities))) {
            ViewUtils.makePopupWindow("Registration Successful", newName + " has been updated.");

         
        }
        NewNameTextField.setText("");
        NewDiscountTextField.setText("");
        EquipmentQuantityTextBoxToAdd.setText("");
        EquipmentQuantityTextBoxToRemove.setText("");
//        EquipmentTable.getItems().clear();
        BundleChoiceBox.setValue(null);
      }
    }
    
    /**
     * @author Samuel Valentine
     */
    @FXML
    public void setTable(ActionEvent event) {
      System.out.println("helloooo");
    }
    
    /**
     * @author Samuel Valentine
     */
    @FXML
    public void refreshTable(ActionEvent event) {
      EquipmentTable.getItems().clear();
     
      EquipmentTable.getItems().setAll(ViewUtils.getEquipmentForSpecificBundle(BundleChoiceBox.getValue()));
      
      SelectEquipmentToRemoveChoiceBox.setItems(ViewUtils.getEquipmentForSpecificBundle(BundleChoiceBox.getValue()));
    }
    
}