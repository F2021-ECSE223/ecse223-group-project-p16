package ca.mcgill.ecse.climbsafe.javafx.fxml.controllers;

import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet5Controller;
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
  
    private List<TOEquipment> Equipments = new ArrayList<TOEquipment>();
    private List<Integer> EquipmentQuantities = new ArrayList<Integer>();
  
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
	private TableView<TOEquipment> EquipmentTable;
	
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
//      
//      TableColumn quantityColumn = new TableColumn("Quantity");
//      quantityColumn.setCellFactory(String);
//      
//      
//            
//      nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
//
//      TableColumn surnameColumn = new TableColumn("Surname");
//      surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
//
//      tab.getColumns().addAll(nameColumn, surnameColumn);
    }
	
	/**
	 * 
	 * @author Samuel Valentine
	 *
	 */
    public static TableColumn<TOEquipment, String> createTableColumn(String header, String propertyName) {
           TableColumn<TOEquipment, String> column = new TableColumn<>(header);
           column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
           return column;
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
        
        
        ClimbSafeFeatureSet5Controller.addEquipmentBundle(name, Integer.parseInt(discount), ViewUtils.getEquipmentNamesListFromTOEquipmentList(Equipments), EquipmentQuantities))) {
            ViewUtils.makePopupWindow("Registration Successful", name + " has been registered as an equipment bundle.");

         
        }
        BundleNameTextField.setText("");
        DiscountTextField.setText("");
        EquipmentQuantityTextField.setText("");
        EquipmentTable.getItems().clear();
        EquipmentChoiceBox.setValue(null);
      }
	}
	
	/**
	 * 
	 * @author Samuel Valentine
	 *
	 */
	@FXML
	public void addEquipmentToBundle(ActionEvent event) {

	  EquipmentTable.getItems().add(EquipmentChoiceBox.getValue());
//	  
//	  if (Equipments == null ) {
//	    Equipments = 
//	  }
	  
	  Equipments.add(EquipmentChoiceBox.getValue());
	  EquipmentQuantities.add(Integer.valueOf(EquipmentQuantityTextField.getText()));
	  
	  EquipmentQuantityTextField.setText("");
	  EquipmentChoiceBox.setValue(null);
	  
	  
	}
}
