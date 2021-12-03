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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;


public class ViewAssignmentsController {
	@FXML
	private TableView<TOAssignment> assignments;
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
	private ChoiceBox<TOAssignment> members;
	@FXML
	private ChoiceBox<Integer> weekNr;
	@FXML
	private TextField authCode;
	

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
	    assignments.getColumns().add(memberEmailColumn);
	    var memberNameColumn = new TableColumn<TOAssignment, String>("Member Name");
	    memberNameColumn.setCellValueFactory(data -> Bindings.createStringBinding(
	    		() -> data.getValue().getMemberName()));
	    assignments.getColumns().add(memberNameColumn);
	    var guideEmailColumn = new TableColumn<TOAssignment, String>("Guide Email");
	    guideEmailColumn.setCellValueFactory(data -> Bindings.createStringBinding(
	    		() -> data.getValue().getGuideEmail()));
	    assignments.getColumns().add(guideEmailColumn);
	    var guideNameColumn = new TableColumn<TOAssignment, String>("Guide Name");
	    guideNameColumn.setCellValueFactory(data -> Bindings.createStringBinding(
	    		() -> data.getValue().getGuideName()));
	    assignments.getColumns().add(guideNameColumn);
	    var hotelNameColumn = new TableColumn<TOAssignment, String>("Hotel Name");
	    hotelNameColumn.setCellValueFactory(data -> Bindings.createStringBinding(
	    		() -> data.getValue().getHotelName()));
	    assignments.getColumns().add(hotelNameColumn);
	    var startWeekColumn = new TableColumn<TOAssignment, String>("Start Week");
	    startWeekColumn.setCellValueFactory(data -> Bindings.createStringBinding(
	    		() -> Integer.toString(data.getValue().getStartWeek())));
	    assignments.getColumns().add(startWeekColumn);
	    var endWeekColumn = new TableColumn<TOAssignment, String>("End Week");
	    endWeekColumn.setCellValueFactory(data -> Bindings.createStringBinding(
	    		() -> Integer.toString(data.getValue().getStartWeek())));
	    assignments.getColumns().add(endWeekColumn);
	    var guideCostColumn = new TableColumn<TOAssignment, String>("Guide Cost");
	    guideCostColumn.setCellValueFactory(data -> Bindings.createStringBinding(
	    		() -> Integer.toString(data.getValue().getTotalCostForGuide())));
	    assignments.getColumns().add(guideCostColumn);
	    var equipmentCostColumn = new TableColumn<TOAssignment, String>("Equipment Cost");
	    equipmentCostColumn.setCellValueFactory(data -> Bindings.createStringBinding(
	    		() -> Integer.toString(data.getValue().getTotalCostForEquipment())));
	    assignments.getColumns().add(equipmentCostColumn);
	    var statusColumn = new TableColumn<TOAssignment, String>("Status");
	    statusColumn.setCellValueFactory(data -> Bindings.createStringBinding(
	        () -> data.getValue().getStatus()));
	    assignments.getColumns().add(statusColumn);
	    var authCodeColumn = new TableColumn<TOAssignment, String>("AuthCode");
	    authCodeColumn.setCellValueFactory(data -> Bindings.createStringBinding(
	    		() -> data.getValue().getAuthorizationCode()));
	    assignments.getColumns().add(authCodeColumn);
	    var refundColumn = new TableColumn<TOAssignment, String>("Equipment Cost");
	    refundColumn.setCellValueFactory(data -> Bindings.createStringBinding(
	    		() -> Integer.toString(data.getValue().getRefundedPercentageAmount())));
	    assignments.getColumns().add(refundColumn);
	    
	    
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
//				   setTextFill(Color.ORANGE);
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
//        				setTextFill(Color.ORANGE);
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
        				setText("(No Hotel Required)");
//        				setTextFill(Color.ORANGE);
        			}
        		}
        	}
        });
//        guideNameColumn.setMinWidth(100);
//        hotelNameColumn.setMinWidth(100);
//        guideEmailColumn.setMinWidth(100);
//        statusColumn.setMinWidth(100);
//        memberNameColumn.setMinWidth(100);
//        memberEmailColumn.setMinWidth(100);
//        guideCostColumn.setMinWidth(100);
//        equipmentCostColumn.setMinWidth(100);
//        refundColumn.setMinWidth(100);
        
        members.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
            members.setItems(ViewUtils.getAssignments());
            System.out.println("choice box");
        //    members.setValue(null);
          });
          weekNr.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> {
            weekNr.setItems(ViewUtils.getWeekNrs());
            weekNr.setValue(null);
          });

	    // overview table if a refreshable element
        assignments.addEventHandler(ClimbSafeFxmlView.REFRESH_EVENT, e -> assignments.setItems(getAllAssignments()));

	    // register refreshable nodes
	    ClimbSafeFxmlView.getInstance().registerRefreshEvent(assignments);
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
		members.setItems(ViewUtils.getAssignments());
		weekNr.setItems(ViewUtils.getWeekNrs());
	}
	/**
	 * @author Youssof Mohamed
	 */
	@FXML
	public void startTrip(ActionEvent event) {
		int weekNum = weekNr.getValue();
		callController(()->AssignmentController.start(weekNum));
	}
	/**
	 * @author Youssof Mohamed
	 */
	@FXML
	public void finishTrip(ActionEvent event) {
		TOAssignment member = members.getValue();
		callController(()->AssignmentController.finish(member.getMemberEmail()));
	}
	/**
	 * @author Youssof Mohamed
	 */
	@FXML
	public void cancelTrip(ActionEvent event) {
		TOAssignment member = members.getValue();
		callController(()->AssignmentController.cancel(member.getMemberEmail()));
	}
	/**
	 * @author Youssof Mohamed
	 */
	@FXML
	public void payTrip(ActionEvent event) {
		TOAssignment member = members.getValue();
		
		callController(()->AssignmentController.pay(member.getMemberEmail(),authCode.getText()));
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