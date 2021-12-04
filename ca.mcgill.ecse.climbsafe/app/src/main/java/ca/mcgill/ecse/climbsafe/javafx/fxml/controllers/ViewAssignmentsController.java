package ca.mcgill.ecse.climbsafe.javafx.fxml.controllers;

import static ca.mcgill.ecse.climbsafe.javafx.fxml.controllers.ViewUtils.callController;

import ca.mcgill.ecse.climbsafe.controller.AssignmentController;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet6Controller;
import ca.mcgill.ecse.climbsafe.controller.TOAssignment;
import ca.mcgill.ecse.climbsafe.javafx.fxml.main.ClimbSafeFxmlView;
import ca.mcgill.ecse.climbsafe.model.Member;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;


public class ViewAssignmentsController {
	@FXML
	private TableView<TOAssignment> assignmentsTable;
	@FXML
	private Button viewAssignments;
	@FXML
	private Button initiateAssignments;
	@FXML
	private Button startTrip;
	@FXML
	private Button finishTrip;
	@FXML
	private Button cancelTrip;
	@FXML
	private Button payTrip;
	@FXML
	private ChoiceBox<TOAssignment> membersChoiceBox;
	@FXML
	private ChoiceBox<Integer> weekNrChoiceBox;
	@FXML
	private TextField authCode;
	@FXML
	private ScrollPane scrollPane;
	

	// Event Listener on Button[#viewAssignments].onAction
	/**
	 * @author Youssof Mohamed
	 */
	@FXML
	  public void initialize() {
	    // initialize the overview table by adding new columns
	    var memberEmailColumn = new TableColumn<TOAssignment, String>("Member Email");
	    memberEmailColumn.setCellValueFactory(data -> Bindings.createStringBinding(
	    		() -> data.getValue().getMemberEmail()));
	    assignmentsTable.getColumns().add(memberEmailColumn);
	    var memberNameColumn = new TableColumn<TOAssignment, String>("Member Name");
	    memberNameColumn.setCellValueFactory(data -> Bindings.createStringBinding(
	    		() -> data.getValue().getMemberName()));
	    assignmentsTable.getColumns().add(memberNameColumn);
	    var guideEmailColumn = new TableColumn<TOAssignment, String>("Guide Email");
	    guideEmailColumn.setCellValueFactory(data -> Bindings.createStringBinding(
	    		() -> data.getValue().getGuideEmail()));
	    assignmentsTable.getColumns().add(guideEmailColumn);
	    var guideNameColumn = new TableColumn<TOAssignment, String>("Guide Name");
	    guideNameColumn.setCellValueFactory(data -> Bindings.createStringBinding(
	    		() -> data.getValue().getGuideName()));
	    assignmentsTable.getColumns().add(guideNameColumn);
	    var hotelNameColumn = new TableColumn<TOAssignment, String>("Hotel Required");
	    hotelNameColumn.setCellValueFactory(data -> Bindings.createStringBinding(
	    		() -> data.getValue().getHotelName()));
	    assignmentsTable.getColumns().add(hotelNameColumn);
	    var startWeekColumn = new TableColumn<TOAssignment, String>("Start Week");
	    startWeekColumn.setCellValueFactory(data -> Bindings.createStringBinding(
	    		() -> Integer.toString(data.getValue().getStartWeek())));
	    assignmentsTable.getColumns().add(startWeekColumn);
	    var endWeekColumn = new TableColumn<TOAssignment, String>("End Week");
	    endWeekColumn.setCellValueFactory(data -> Bindings.createStringBinding(
	    		() -> Integer.toString(data.getValue().getEndWeek())));
	    assignmentsTable.getColumns().add(endWeekColumn);
	    var guideCostColumn = new TableColumn<TOAssignment, String>("Guide Cost");
	    guideCostColumn.setCellValueFactory(data -> Bindings.createStringBinding(
	    		() -> Integer.toString(data.getValue().getTotalCostForGuide())));
	    assignmentsTable.getColumns().add(guideCostColumn);
	    var equipmentCostColumn = new TableColumn<TOAssignment, String>("Equipment Cost");
	    equipmentCostColumn.setCellValueFactory(data -> Bindings.createStringBinding(
	    		() -> Integer.toString(data.getValue().getTotalCostForEquipment())));
	    assignmentsTable.getColumns().add(equipmentCostColumn);
	    var statusColumn = new TableColumn<TOAssignment, String>("Status");
	    statusColumn.setCellValueFactory(data -> Bindings.createStringBinding(
	        () -> data.getValue().getStatus()));
	    assignmentsTable.getColumns().add(statusColumn);
	    var authCodeColumn = new TableColumn<TOAssignment, String>("AuthCode");
	    authCodeColumn.setCellValueFactory(data -> Bindings.createStringBinding(
	    		() -> data.getValue().getAuthorizationCode()));
	    assignmentsTable.getColumns().add(authCodeColumn);
	    var refundColumn = new TableColumn<TOAssignment, String>("Refund");
	    refundColumn.setCellValueFactory(data -> Bindings.createStringBinding(
	    		() -> Integer.toString(data.getValue().getRefundedPercentageAmount())));
	    assignmentsTable.getColumns().add(refundColumn);
	    
	    
	    // member column needs to have customized string
	   
	    
	    // change the color of the cells based on the value in the TOAssignment
	    statusColumn.setCellFactory(col -> new TableCell<>() {
	      @Override public void updateItem(String item, boolean empty) {
	        super.updateItem(item, empty);
	        var row = getTableRow();
	        setText(item);
	        setTextFill(Color.BLACK);
	        
	        if(row.getItem()!=null) {
	        	Member member = (Member) Member.getWithEmail(row.getItem().getMemberEmail());
	        	if (member.getMemberStatus().toString()=="Banned") {
	        		setText(item);
	        		setTextFill(Color.RED);
	        	}
	        }
	      }
	    });
        guideEmailColumn.setCellFactory(col -> new TableCell<>() {
	      @Override 
	      public void updateItem(String item, boolean empty) {
		     super.updateItem(item, empty);
		     var row = getTableRow();
		     setText(item);
		     setTextFill(Color.BLACK);
		     if(row.getItem()!=null) {
		        Member member = (Member) Member.getWithEmail(row.getItem().getMemberEmail());
		        if (!member.isGuideRequired()) {
				   setText("(No Guide Required)");
				   setTextFill(Color.GREY);
				}
		     }
		  }
		});
        guideNameColumn.setCellFactory(col -> new TableCell<>() {
        	@Override public void updateItem(String item, boolean empty) {
        		super.updateItem(item, empty);
        		var row = getTableRow();
        		setText(item);
        		setTextFill(Color.BLACK);
        		if(row.getItem()!=null) {
        			Member member = (Member) Member.getWithEmail(row.getItem().getMemberEmail());
        			if (!member.isGuideRequired()) {
        				setText("(No Guide Required)");
        				setTextFill(Color.GREY);
        			}
        		}
        	}
        });
        hotelNameColumn.setCellFactory(col -> new TableCell<>() {
        	@Override public void updateItem(String item, boolean empty) {
        		super.updateItem(item, empty);
        		var row = getTableRow();
        		setText(item);
        		setTextFill(Color.BLACK);
        		if(row.getItem()!=null) {
        			Member member = (Member) Member.getWithEmail(row.getItem().getMemberEmail());
        			if (!member.getHotelRequired()) {
        				setText("NO");
//        				setTextFill(Color.ORANGE);
        			}else setText("YES");
        		} 
        	}
        });
        
        
        guideNameColumn.setMinWidth(100);
        hotelNameColumn.setMinWidth(100);
        guideEmailColumn.setMinWidth(100);
        statusColumn.setMinWidth(100);
        memberNameColumn.setMinWidth(100);
        memberEmailColumn.setMinWidth(100);
        guideCostColumn.setMinWidth(100);
        equipmentCostColumn.setMinWidth(100);
        refundColumn.setMinWidth(100);
        authCodeColumn.setMinWidth(100);
        startWeekColumn.setMinWidth(100);
        endWeekColumn.setMinWidth(100);
        
        scrollPane.setContent(assignmentsTable);
        
        membersChoiceBox.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
            membersChoiceBox.setItems(ViewUtils.getAssignments());
//            membersChoiceBox.setValue(null);
          });
          weekNrChoiceBox.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
            weekNrChoiceBox.setItems(ViewUtils.getWeekNrs());
            weekNrChoiceBox.setValue(null);
          });

	    // overview table if a refreshable element
        assignmentsTable.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> assignmentsTable.setItems(getAllAssignments()));

	    // register refreshable nodes
        
        ClimbSafeFxmlView.getInstance().registerRefreshEvent(assignmentsTable);
	    ClimbSafeFxmlView.getInstance().registerRefreshEvent(membersChoiceBox);
	    ClimbSafeFxmlView.getInstance().registerRefreshEvent(weekNrChoiceBox);
	    
	}
	/**
	 * @author Youssof Mohamed
	 */
	// Event Listener on Button[#viewAssignments].onAction
	@FXML
	public void viewAssignments(ActionEvent event) {
		ClimbSafeFxmlView.getInstance().refresh();
	}
	/**
	 * @author Youssof Mohamed
	 */
	@FXML
	public void initiateAssignments(ActionEvent event) {
		callController(() -> AssignmentController.initiateAssignment());
	}
	/**
	 * @author Youssof Mohamed
	 */
	@FXML
	public void startTrip(ActionEvent event) {
		
		if(weekNrChoiceBox.getValue()==null) {
			ViewUtils.showError("Please Choose a Week Number!");
		} else {
			int weekNum = weekNrChoiceBox.getValue();
			callController(()->AssignmentController.start(weekNum));
		}
	}
	/**
	 * @author Youssof Mohamed
	 */
	@FXML
	public void finishTrip(ActionEvent event) {
		TOAssignment member = membersChoiceBox.getValue();
		if(membersChoiceBox.getValue()==null) {
			ViewUtils.showError("Please Choose a Member!");
		} else {
		callController(()->AssignmentController.finish(member.getMemberEmail()));
		}
	}
	/**
	 * @author Youssof Mohamed
	 */
	@FXML
	public void cancelTrip(ActionEvent event) {
		
		TOAssignment member = membersChoiceBox.getValue();
		if(membersChoiceBox.getValue()==null) {
			ViewUtils.showError("Please Choose a Member!");
		} else {
			callController(()->AssignmentController.cancel(member.getMemberEmail()));
		}
	}
	/**
	 * @author Youssof Mohamed
	 */
	@FXML
	public void payTrip(ActionEvent event) {
		TOAssignment member = membersChoiceBox.getValue();
		if(membersChoiceBox.getValue()==null) {
			ViewUtils.showError("Please Choose a Member!");
		} else {
			callController(()->AssignmentController.pay(member.getMemberEmail(),authCode.getText()));
		}
	}
	/**
	 * @author Youssof Mohamed
	 */
	public ObservableList<TOAssignment> getAllAssignments() {
	    
	    return FXCollections.observableList(ClimbSafeFeatureSet6Controller.getAssignments());
	}
	/**
	 * @author Youssof Mohamed
	 */
	public static TableColumn<TOAssignment, String> createTableColumn(String header,
		      String propertyName) {
		    TableColumn<TOAssignment, String> column = new TableColumn<>(header);
		    column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
		    return column;
	}

	// Event Listener on TableView[#initiateAssignments].onSort
	

}