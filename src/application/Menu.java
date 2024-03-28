// Vihaan Patel - 1225904942
package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Menu {
	
	private Stage stage;
	int width;
	int height;
	private Scene menuScene;
	
	Menu(Stage stage, int w, int h) {
		this.stage = stage;
		width = w;
		height = h;
	}
	
	// display startup menu
	public void showMenu() {
		VBox menu = new VBox(20);
		menu.setAlignment(Pos.CENTER);
		
		Text header = new Text("Welcome to Heart Health Imaging and Recording System");
		header.setWrappingWidth(width);
		header.setTextAlignment(TextAlignment.CENTER);
		header.setFont(Font.font("null", FontWeight.BOLD, 24));
		
		Button intakeButton = new Button("Patient Intake");
		intakeButton.setMinWidth(300);
        intakeButton.setMinHeight(100);
		intakeButton.setOnAction(event -> navigate(1));
        
		Button techButton = new Button("CT Scan Tech View");
		techButton.setMinWidth(300);
        techButton.setMinHeight(100);
		techButton.setOnAction(event -> navigate(2));
        
		Button patientViewButton = new Button("Patient View");
		patientViewButton.setMinWidth(300); 
	    patientViewButton.setMinHeight(100);
		patientViewButton.setOnAction(event -> navigate(3));
		
		menu.getChildren().addAll(header, intakeButton, techButton, patientViewButton);
		
		menuScene = new Scene(menu, width, height);
		stage.setScene(menuScene);
		stage.setTitle("Heart Health Imaging and Recording System");
		stage.show();
		
	}
	
	// navigate to specific page based on pressed button
	public void navigate(int portalNum) {
		switch(portalNum) {
		case 1:
			PatientIntake intakeForm = new PatientIntake(stage, width, height, menuScene);
			intakeForm.showForm();
			break;
		case 2:
			CTScan CTForm = new CTScan(stage, width, height, menuScene);
			CTForm.showCTScanForm();
			break;
		case 3:
			PatientPortal patientView = new PatientPortal(stage, width, height, menuScene);
			patientView.showPatientPortal();
			break;
		default:
			System.out.println("System failure");
			System.exit(0);
		}
	}

}
