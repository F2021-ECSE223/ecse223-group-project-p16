package ca.mcgill.ecse.climbsafe.javafx.fxml.controllers;

import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet6Controller;
import ca.mcgill.ecse.climbsafe.controller.TOEquipmentBundle;
import ca.mcgill.ecse.climbsafe.javafx.fxml.main.ClimbSafeFxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class DeleteEquipmentBundleController {
    @FXML
    private ChoiceBox<TOEquipmentBundle> BundleChoiceBox;
    @FXML
    private Button DeleteBundleButton;
    
    /**
     * 
     * @author Samuel Valentine
     *
     */
    @FXML
    public void initialize() {
      BundleChoiceBox.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
        BundleChoiceBox.setItems(ViewUtils.getEquipmentBundles());
        // reset the choice
        BundleChoiceBox.setValue(null);
      });
    ClimbSafeFxmlView.getInstance().registerRefreshEvent(BundleChoiceBox);
    }
    
    // Event Listener on Button[#DeleteBundleButton].onAction
    /**
     * 
     * @author Samuel Valentine
     *
     */
    @FXML
    public void deleteBundle(ActionEvent event) {
        
        String name = BundleChoiceBox.getValue().getEquipmentBundleName();
        
//         reset the driver text field if success
        if (ViewUtils.successful(() -> 
        
        ClimbSafeFeatureSet6Controller.deleteEquipmentBundle(name))) {
            ViewUtils.makePopupWindow("Deletion Successful", name + " has been deleted.");

         
        }
        BundleChoiceBox.setValue(null);
        
      
      
      
    }
}