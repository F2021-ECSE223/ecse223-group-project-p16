/*
 * @Author: Saif Shahin
 * 
 * Controller for the Feature Set 2 fxml file.
 */

package ca.mcgill.ecse.climbsafe.javafx.fxml.FxControllers;

import ca.mcgill.ecse.climbsafe.javafx.fxml.main.ClimbSafeFxmlView;
import ca.mcgill.ecse.climbsafe.javafx.fxml.pages.AddElementPage.fxml;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;


public class FeatureSet2FxmlController {

	// initializing the register member interactable components
	private static ClimbSafe climbsafe = ClimbSafeApplication.getClimbSafe();
	boolean regHotelRequired;
	boolean regGuideRequired;
	//List<String> regItemNames = new ArrayList<String>();
	List<Integer> regItemQuantities = new ArrayList<Integer>();

	@FXML
	private Button refreshButton;
	@FXML
	private TextField regNameTextFeild;
	@FXML
	private TextField regEmailTextFeild;
	@FXML
	private TextFeild regPasswordTextFeild;
	@FXML
	private TextFeild regEmergencyContactTextFeild;
	@FXML
	private TextFeild regNrWeeksTextFeild;
	@FXML
	private TextFeild regEquipNamesTextFeild;
	@FXML
	private TextFeild regEquipQuantityTextFeild;
	@FXML
	private Button regReqGuideYesButton;
	@FXML
	private Button regReqGuideNoButton;
	@FXML
	private Button regHotelYesButton;
	@FXML
	private Button regHotelNoButton;
	@FXML
	private Button registerButton;

	// Initializing the update member interactable components
	@FXML
	private TextFeild updateEnterEmailTextFeild;
	@FXML
	private TextFeild updateEnterPasswordTextFeild;
	@FXML
	private TextFeild updateNameTextFeild;
	@FXML
	private TextFeild updateContactNumTextFeild;
	@FXML
	private TextFeild updateNrWeeksTextFeild; //here atm
	@FXML
	private Button updateMtnGuideButtonYes;
	@FXML
	private Button updateMtnGuideButtonNo;
	@FXML
	private Button updateHotelButtonYes;
	@FXML
	private Button updateHotelButtonNo;
	@FXML
	private TextFeild updateEquipNamesTextFeild;
	@FXML
	private TextFeild updatePasswordTextFeild;
	@FXML
	private Button updateButton;

	// Initializing the cancel ooking interactable components
	@FXML
	private TextFeild cancelNameTextFeild;
	@FXML
	private Button cancelBookingButton;
	@FXML
	private TextFeild cancelPasswordTextFeild;
	@FXML
	private TextFeild cancelEmailTextFeild;

	
	@FXML
	public void refreshButton(ActionEvent event) {
		ClimbSafeFxmlView.getInstance().refresh();
	}
	
	
	//Note: from my understanding, my controller does not need an initialize method as it doesn't utilize the overview table.
	
	// Event listeners on the buttons in the GUI
	@FXML
	public void regReqGuideYesButton(ActionEvent event) {
		
	
	
	regReqGuideYesButton.setOnAction(new EventHandler() {
	    @Override
	    public void handle(ActionEvent actionEvent) {
	    	regGuideRequired=true;
	    }
	});
	}

	@FXML
	public void regReqGuideNoButton(ActionEvent event) {

		regReqGuideNoButton.setOnAction(new EventHandler() {
		    @Override
		    public void handle(ActionEvent actionEvent) {
		    	regGuideRequired=false;
		    }
		});
	}

	@FXML
	public void regHotelYesButton(ActionEvent event) {

		regHotelYesButton.setOnAction(new EventHandler() {
		    @Override
		    public void handle(ActionEvent actionEvent) {
		    	regHotelRequired=true;
		    }
		});
		
	}

	@FXML
	public void regHotelNoButton(ActionEvent event) {

		regHotelNoButton.setOnAction(new EventHandler() {
		    @Override
		    public void handle(ActionEvent actionEvent) {
		    	regHotelRequired=false;
		    }
		});
		
	}

	@FXML
	public void registerButton(ActionEvent event) {
		
		String name = "";
		name=regNameTextFeild.getText();
		String email="";
		email=regEmailTextFeild.getText();
		String password= "";
		password=regPasswordTextFeild.getText();
		String emergencyContact= "";
		emergencyContact=regEmergencyContactTextFeild.getText();
		String nrWeeksString="";
		nrWeeksString=regNrWeeksTextFeild.getText();
	

		
		
		String[] items= regEquipNamesTextFeild.getText().split(","); //possible bug
		List <String> regItemNames = Arrays.asList(items);	
		
		String[] itemQuantities= regEquipQuantityTextFeild.getText().split(",");
		List <String> regItemQuantities= Arrays.asList(itemQuantities);	
				
		int nrWeeks=0;
		try {
			nrWeeks=Integer.parseInt(nrWeeksString);
		}
		catch (NumberFormatException ex){
			ViewUtils.showError("Please input a valid number of weeks you would like to climb.");
		}
		
		if(email.length>=1 && password.length>=1 && name.length>=1 && emergencyContact.length>=1 
				&& !nrWeeksStr.length.equals("")) {
		//adds the user inputs into the MVC FeatureSet2controlller. 
		ClimbSafeFeatureSet2Controller.registerMember(email, password, name,
				emergencyContact, nrWeeks, this.guideRequired, this.hotelRequired, 
				regItemNames, regItemQuantities);
		}
		else {
			ViewUtils.showError("Please input valid entries for all required feilds");
		}
		
	}

	@FXML
	public void updateMtnGuideButtonYes(ActionEvent event) {

		updateMtnGuideButtonYes.setOnAction(new EventHandler() {
		    @Override
		    public void handle(ActionEvent actionEvent) {
		    	regGuideRequired=true;
		    }
		});
		
	}

	@FXML
	public void updateMtnGuideButtonNo(ActionEvent event) {

		updateMtnGuideButtonNo.setOnAction(new EventHandler() {
		    @Override
		    public void handle(ActionEvent actionEvent) {
		    	regGuideRequired=false;
		    }
		});
		
	}

	@FXML
	public void updateHotelButtonYes(ActionEvent event) {
		updateHotelButtonYes.setOnAction(new EventHandler() {
		    @Override
		    public void handle(ActionEvent actionEvent) {
		    	regHotelRequired=true;
		    }
		});
	}

	@FXML
	public void updateHotelButtonNo(ActionEvent event) {

		updateHotelButtonNo.setOnAction(new EventHandler() {
		    @Override
		    public void handle(ActionEvent actionEvent) {
		    	regHotelRequired=false;
		    }
		});
	}

	@FXML
	public void updateButton(ActionEvent event) {

		String email="";
		email=updateEnterEmailTextFeild.getText();
		String newPassword="";
		newPassword=updateEnterPasswordTextFeild.getText();
		String newName= "";
		newName=updateNameTextFeild.getText();
		String newEmergencyContact= "";
		newEmergencyContact=updateContactNumTextFeild.getText();
		String newNrWeeksStr= "";
		newNrWeeksStr=updateNrWeeksTextFeild.getText();
		
		int newNrWeeks=0;
		try {
			newNrWeeks=Integer.parseInt(newNrWeeksStr);
		}
		catch (NumberFormatException ex){
			ViewUtils.showError("Please input a valid number of weeks you would like to climb.");
		}
		
		String[] updateItems= regEwquipNamesTextFeild.getText().split(","); //possible bug. 
		List <String> newItemNames = Arrays.asList(updateItems);	
		
		String[] updateItemQuantities= regEquipQuantityTextFeild.getText().split(",");
		List <String> newItemQuantities= Arrays.asList(updateItemQuantities);	
		
		if(email.length>=1 && newPassword.length>=1 && newName.length>=1 && newEmergencyContact.length>=1 
				&& !newNrWeeksStr.length.equals("")) {
			ClimbSafeFeatureSet2Controller.updateMember(email, newPassword, newName, newEmergencyContact, 
					newNrWeeks,regGuideRequired, regHotelRequired, newItemNames, newItemQuantities);
		}
		else {
			ViewUtils.showError("Please input valid entries for all required feilds");
		}
		
	}
		

	@FXML
	public void cancelBookingButton(ActionEvent event) {
		
		
		String email="";
		email=cancelEmailTextFeild.getText();
		if (email.equals("")) {
			ViewUtils.showError("Please input a valid email address");
		}
		else {
			ClimbSafeFeatureSet1Controller.deleteMember(email);
		}
		
	}
	
	@FunctionalInterface
	interface Executable {
	  public void execute() throws Throwable;
	}

}
