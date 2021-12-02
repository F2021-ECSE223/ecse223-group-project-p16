package ca.mcgill.ecse.climbsafe.javafx.fxml.controllers;

import static ca.mcgill.ecse.climbsafe.javafx.fxml.controllers.ViewUtils.callController;

import ca.mcgill.ecse.climbsafe.controller.AssignmentController;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet6Controller;
import ca.mcgill.ecse.climbsafe.controller.TOAssignment;
import ca.mcgill.ecse.climbsafe.javafx.fxml.main.ClimbSafeFxmlView;
import ca.mcgill.ecse.climbsafe.model.Assignment;
import ca.mcgill.ecse.climbsafe.model.Member;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


public class ViewAssignmentsController {
	@FXML
	private TableView<TOAssignment> assignments;
	@FXML
	private Button viewAssignments;
	@FXML
	private Button initiateAssignments;
	@FXML
	private Button start;
	@FXML
	private Button finish;
	@FXML
	private Button cancel;
	@FXML
	private Button pay;
	@FXML
	private ChoiceBox<TOAssignment> members;
	@FXML
	private ChoiceBox<Integer> weekNr;
	

	// Event Listener on Button[#viewAssignments].onAction
	
	@FXML
	  public void initialize() {
	    // initialize the overview table by adding new columns
	    assignments.getColumns().add(createTableColumn("Member Email", "memberEmail"));
	    var memberColumn = new TableColumn<TOAssignment, String>("Member Name");
	    memberColumn.setCellValueFactory(data -> Bindings.createStringBinding(
	        () -> data.getValue().getMemberName()));
	    assignments.getColumns().add(memberColumn);
	    
	    var guideEmailColumn = new TableColumn<TOAssignment, String>("Guide Email");
	    guideEmailColumn.setCellValueFactory(data -> Bindings.createStringBinding(
	    		() -> data.getValue().getGuideEmail()));
	    assignments.getColumns().add(guideEmailColumn);
	    
	    var guideNameColumn = new TableColumn<TOAssignment, String>("Guide Name");
	    guideNameColumn.setCellValueFactory(data -> Bindings.createStringBinding(
	    		() -> data.getValue().getGuideName()));
	    assignments.getColumns().add(guideNameColumn);
	  
	    assignments.getColumns().add(createTableColumn("Guide Email", "guideEmail"));
	    assignments.getColumns().add(createTableColumn("Guide Name", "guideName"));
	    assignments.getColumns().add(createTableColumn("Hotel Name", "hotelName"));
	    assignments.getColumns().add(createTableColumn("Start Week","startWeek"));
	    assignments.getColumns().add(createTableColumn("End Week","endWeek"));
	    assignments.getColumns().add(createTableColumn("Guide Cost","totalCostForGuide"));
	    assignments.getColumns().add(createTableColumn("Equipment Cost","totalCostForEquipment"));
	    
	    
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
	          setText(item + " (Banned)");
	          setTextFill(Color.RED);
	        }
	      }
	    });
        guideEmailColumn.setCellFactory(col -> new TableCell<>() {
	      @Override public void updateItem(String item, boolean empty) {
		        super.updateItem(item, empty);
		        var row = getTableRow();
		        setText(item);
		        setTextFill(Color.BLACK);
		        
		        Member member = (Member) Member.getWithEmail(row.getItem().getMemberEmail());
		        if (row.getItem() != null && !member.isGuideRequired()) {
		          setText("No Guide");
		          setTextFill(Color.RED);
		        }
		      }
		    });
        guideNameColumn.setCellFactory(col -> new TableCell<>() {
        	@Override public void updateItem(String item, boolean empty) {
        		super.updateItem(item, empty);
        		var row = getTableRow();
        		setText(item);
        		setTextFill(Color.BLACK);
        		
        		Member member = (Member) Member.getWithEmail(row.getItem().getMemberEmail());
        		if (row.getItem() != null && !member.isGuideRequired()) {
        			setText("No Guide");
        			setTextFill(Color.RED);
        		}
        	}
        });


	    // overview table if a refreshable element
        assignments.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> assignments.setItems(getAllAssignments()));

	    // register refreshable nodes
	    ClimbSafeFxmlView.getInstance().registerRefreshEvent(assignments);
	}
	// Event Listener on Button[#viewAssignments].onAction
	@FXML
	public void viewAssignments(MouseEvent event) {
		ClimbSafeFxmlView.getInstance().refresh();
	}
	
	@FXML
	public void initiateAssignments(MouseEvent event) {
		callController(() -> AssignmentController.initiateAssignment());
	}
	
	@FXML
	public void startTrip(MouseEvent event) {
		int weekNum = weekNr.getValue();
		callController(()->AssignmentController.start(weekNum));
	}
	
	@FXML
	public void finishTrip(MouseEvent event) {
		TOAssignment member = members.getValue();
		callController(()->AssignmentController.finish(member.getMemberEmail()));
	}
	
	@FXML
	public void cancelTrip(MouseEvent event) {
		TOAssignment member = members.getValue();
		callController(()->AssignmentController.cancel(member.getMemberEmail()));
	}
	
	@FXML
	public void payTrip(MouseEvent event) {
		TOAssignment member = members.getValue();
		Member realMember = (Member) Member.getWithEmail(member.getMemberEmail());
		Assignment assignment = realMember.getAssignment();
		
		callController(()->AssignmentController.pay(member.getMemberEmail(),assignment.getAuthCode()));
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