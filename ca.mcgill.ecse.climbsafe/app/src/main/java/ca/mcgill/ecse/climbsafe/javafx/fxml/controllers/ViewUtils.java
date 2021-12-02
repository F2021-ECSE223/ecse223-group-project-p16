package ca.mcgill.ecse.climbsafe.javafx.fxml.controllers;

import java.util.ArrayList;
import java.util.List;
	
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet6Controller;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.controller.TOAssignment;
import ca.mcgill.ecse.climbsafe.javafx.fxml.main.ClimbSafeFxmlView;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
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

	  public static ObservableList<TOAssignment> getAssignments() {
	    List<TOAssignment> assignments = ClimbSafeFeatureSet6Controller.getAssignments();
	    // as javafx works with observable list, we need to convert the java.util.List to
	    // javafx.collections.observableList
	   //  System.out.println(assignments.size()+"getAssignments");
	    return FXCollections.observableList(assignments);
	  }
	  public static ObservableList<Integer> getWeekNrs() {
		  ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
		    List<Integer> weeks = new ArrayList<Integer>();
		    for(int i=1;i<=climbSafe.getNrWeeks();i++) {
		    	weeks.add(i);
		    }
		    // as javafx works with observable list, we need to convert the java.util.List to
		    // javafx.collections.observableList
		    return FXCollections.observableList(weeks);
		  }


	@FunctionalInterface
	interface Executable {
	  public void execute() throws Throwable;
	}
}
