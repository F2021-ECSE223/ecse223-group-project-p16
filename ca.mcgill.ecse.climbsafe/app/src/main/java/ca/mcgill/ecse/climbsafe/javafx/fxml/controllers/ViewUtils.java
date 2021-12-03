package ca.mcgill.ecse.climbsafe.javafx.fxml.controllers;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet6Controller;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.controller.TOAssignment;
import ca.mcgill.ecse.climbsafe.controller.TOEquipment;
import ca.mcgill.ecse.climbsafe.controller.TOEquipmentBundle;
import ca.mcgill.ecse.climbsafe.controller.TOGuide;
import ca.mcgill.ecse.climbsafe.controller.TOMember;
import ca.mcgill.ecse.climbsafe.javafx.fxml.main.ClimbSafeFxmlView;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewUtils {

	  /** Calls the controller and shows an error, if applicable. */
	  public static boolean callController(Executable executable) {
	    try {
	      executable.execute();
	     ClimbSafeFxmlView.getInstance().refresh();
	      return true;
	    } catch (InvalidInputException e) {
	      showError(e.getMessage());
	    } catch (Throwable t) {
	      t.printStackTrace();
	    }
	    return false;
	  }

	  /** Calls the controller and returns true on success. This method is included for readability. */
	  public static boolean successful(Executable controllerCall) {
	    return callController(controllerCall);
	  }

	  /**
	   * Creates a popup window.
	   *
	   * @param title: title of the popup window
	   * @param message: message to display
	   */
	  public static void makePopupWindow(String title, String message) {
	    Stage dialog = new Stage();
	    dialog.initModality(Modality.APPLICATION_MODAL);
	    VBox dialogPane = new VBox();

	    // create UI elements
	    Text text = new Text(message);
	    Button okButton = new Button("OK");
	    okButton.setOnAction(a -> dialog.close());

	    // display the popup window
	    int innerPadding = 10; // inner padding/spacing
	    int outerPadding = 100; // outer padding
	    dialogPane.setSpacing(innerPadding);
	    dialogPane.setAlignment(Pos.CENTER);
	    dialogPane.setPadding(new Insets(innerPadding, innerPadding, innerPadding, innerPadding));
	    dialogPane.getChildren().addAll(text, okButton);
	    Scene dialogScene = new Scene(dialogPane, outerPadding + 5 * message.length(), outerPadding);
	    dialog.setScene(dialogScene);
	    dialog.setTitle(title);
	    dialog.show();
	  }

	  public static void showError(String message) {
	    makePopupWindow("Error", message);
	  }

	  /**
	   * 
	   * @return a list of TO assignments
	   */
	  public static ObservableList<TOAssignment> getAssignments() {
	    List<TOAssignment> assignments = ClimbSafeFeatureSet6Controller.getAssignments();
	    // as javafx works with observable list, we need to convert the java.util.List to
	    // javafx.collections.observableList
	    return FXCollections.observableList(assignments);
	  }
	  /**
	   * 
	   * @return a list of TO members
	   */
	  public static ObservableList<TOMember> getMembers() {
		  List<Member> members = ClimbSafeApplication.getClimbSafe().getMembers();
		    
		  List<TOMember> TOmembers = new ArrayList<>();
		    
		  for (int i = 0; i < members.size(); i++) {
			  TOmembers.add(i, new TOMember(null, null) );
		    	
			  if (members.get(i) != null) {
				  TOmembers.get(i).setMemberEmail(members.get(i).getEmail());
				  TOmembers.get(i).setMemberName(members.get(i).getName());
				  }
			  }
		  // as javafx works with observable list, we need to convert the java.util.List to
		  // javafx.collections.observableList
		  return FXCollections.observableList(TOmembers);
		  }
	  
	  /**
	   * 
	   * @return a list of equipment
	   */
	  public static ObservableList<TOEquipment> getEquipment() {
	        List<Equipment> equipment = ClimbSafeApplication.getClimbSafe().getEquipment();
	          
	        List<TOEquipment> TOequipment = new ArrayList<>();
	          
	        for (int i = 0; i < equipment.size(); i++) {
	            TOequipment.add(i, new TOEquipment(null, 0, 0) );
	              
	            if (equipment.get(i) != null) {
	                TOequipment.get(i).setEquipmentName(equipment.get(i).getName());
	                TOequipment.get(i).setWeight(equipment.get(i).getWeight());
	                TOequipment.get(i).setPricePerWeek(equipment.get(i).getPricePerWeek());
	                }
	            }
	        // as javafx works with observable list, we need to convert the java.util.List to
	        // javafx.collections.observableList
	        return FXCollections.observableList(TOequipment);
	        }
	  
	  /**
	   * 
	   * @return a list of equipmentBundle
	   */
	  public static ObservableList<TOEquipmentBundle> getEquipmentBundles() {
	        List<EquipmentBundle> equipmentBundles = ClimbSafeApplication.getClimbSafe().getBundles();
	          
	        List<TOEquipmentBundle> TOequipmentBundles = new ArrayList<>();
	          
	        for (int i = 0; i < equipmentBundles.size(); i++) {
	            TOequipmentBundles.add(i, new TOEquipmentBundle(null, 0) );
	              
	            if (equipmentBundles.get(i) != null) {
	                TOequipmentBundles.get(i).setEquipmentBundleName(equipmentBundles.get(i).getName());
	                TOequipmentBundles.get(i).setDiscount(equipmentBundles.get(i).getDiscount());
	                }
	            }
	        // as javafx works with observable list, we need to convert the java.util.List to
	        // javafx.collections.observableList
	        return FXCollections.observableList(TOequipmentBundles);
	        }

	
	  /**
	   * 
	   * @return a list of guides
	   */
	  public static ObservableList<TOGuide> getGuides() {
		  List<Guide> guides = ClimbSafeApplication.getClimbSafe().getGuides();
		    
		  List<TOGuide> TOguides = new ArrayList<>();
		    
		  for (int i = 0; i < guides.size(); i++) {
			  TOguides.add(i, new TOGuide(null, null) );
		    	
			  if (guides.get(i) != null) {
				  TOguides.get(i).setGuideEmail(guides.get(i).getEmail());
				  TOguides.get(i).setGuideName(guides.get(i).getName());
				  }
			  }
		  // as javafx works with observable list, we need to convert the java.util.List to
		  // javafx.collections.observableList
		  return FXCollections.observableList(TOguides);
		  }

	}

  

@FunctionalInterface
interface Executable {
  public void execute() throws Throwable;
}
