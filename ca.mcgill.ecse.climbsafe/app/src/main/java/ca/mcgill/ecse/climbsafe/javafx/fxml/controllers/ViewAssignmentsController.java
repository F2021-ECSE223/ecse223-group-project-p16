package ca.mcgill.ecse.climbsafe.javafx.fxml.controllers;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.AssignmentController;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet6Controller;
import ca.mcgill.ecse.climbsafe.controller.TOAssignment;
import ca.mcgill.ecse.climbsafe.javafx.fxml.main.ClimbSafeFxmlView;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Member;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;


public class ViewAssignmentsController {
	private static ClimbSafe climbsafe = ClimbSafeApplication.getClimbSafe();
	@FXML
	private TableView<TOAssignment> initiateAssignment;
	@FXML
	private Button viewAssignments;
	@FXML
	private Button initiateAssignments;

	// Event Listener on Button[#viewAssignments].onAction
	
	@FXML
	  public void initialize() {
	    // initialize the overview table by adding new columns
	    initiateAssignment.getColumns().add(createTableColumn("Hotel", "name"));
//	    var statusColumn = createTableColumn("Member", "memberStatus");
	    var memberColumn = new TableColumn<TOAssignment, String>("Member");
	    memberColumn.setCellValueFactory(data -> Bindings.createStringBinding(
	        () -> data.getValue().getMemberName()));
	    initiateAssignment.getColumns().add(memberColumn);
	    initiateAssignment.getColumns().add(createTableColumn("Member", "nrWeeks"));
	    initiateAssignment.getColumns().add(createTableColumn("Member", "memberStatus"));

	    // member column needs to have customized string
	   

	    // change the color of the cells based on the value in the TOAssignment
	    memberColumn.setCellFactory(col -> new TableCell<>() {
	      @Override public void updateItem(String item, boolean empty) {
	        super.updateItem(item, empty);
	        var row = getTableRow();
	        setText(item);
	        setTextFill(Color.BLACK);
	        
	        Member member = (Member) Member.getWithEmail(row.getItem().getMemberEmail());
	        if (row.getItem() != null && member.getMemberStatus().toString()=="Banned") {
	          setText(item + " (in repair)");
	          setTextFill(Color.ORANGE);
	        }
	      }
	    });
////
//	    statusColumn.setCellFactory(col -> new TableCell<>() {
//	      @Override public void updateItem(String item, boolean empty) {
//	        super.updateItem(item, empty);
//	        var row = getTableRow();
//	        setText(item);
//	        setTextFill(Color.BLACK);
//	        if (row.getItem() != null && row.getItem().) {
//	          setText(item + " (sick)");
//	          setTextFill(Color.RED);
//	        }
//	      }
//	    });

//	    // configure data picker
//	    // set editable to false so that the user cannot choose from the calendar
//	    datePicker.setEditable(false);
//	    // set default value to today
//	    datePicker.setValue(LocalDate.now());

	    // overview table if a refreshable element
	    initiateAssignment.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> initiateAssignment.setItems(getAllAssignments()));

	    // register refreshable nodes
	    ClimbSafeFxmlView.getInstance().registerRefreshEvent(initiateAssignment);
	}
	// Event Listener on Button[#viewAssignments].onAction
	@FXML
	public void viewAssignments(ActionEvent event) {
		ClimbSafeFxmlView.getInstance().refresh();
	}
	
	@FXML
	public void initiateAssignments(ActionEvent event) {
		
	}
	public ObservableList<TOAssignment> getAllAssignments() {
	    
	    return FXCollections.observableList(ClimbSafeFeatureSet6Controller.getAssignments());
	}
	
	public static TableColumn<TOAssignment, String> createTableColumn(String header,
		      String propertyName) {
		    TableColumn<TOAssignment, String> column = new TableColumn<>(header);
		    column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
		    return column;
	}

	// Event Listener on TableView[#initiateAssignments].onSort
	

}
