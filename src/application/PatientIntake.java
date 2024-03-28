// Vihaan Patel - 1225904942
package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PatientIntake {
	
	private Stage stage;
	int width, height;
	private TextField firstName, lastName, email, phone, insuranceID;
	private TextArea healthHistory;
	private Scene prevScene, intakeFormScene;
	
	PatientIntake(Stage stage, int w, int h, Scene prevScene) {
		this.stage = stage;
		width = w;
		height = h;
		this.prevScene = prevScene;
	}
	
	// display the patient intake form 
	public void showForm() {
		// using a GridPane object to set the form layout
		GridPane form = new GridPane();
		form.setAlignment(Pos.CENTER);
		form.setVgap(20);
		form.setHgap(50);
		
		// form title :
		Text header = new Text("Patient Intake Form");
		header.setFont(Font.font("null", FontWeight.BOLD, 16));
		form.add(header, 1,0);
		
		// form fields :
		form.add(new Label("First Name:"), 0,1);
		firstName = new TextField();
		form.add(firstName, 1,1);
		
		form.add(new Label("Last Name:"), 0,2);
		lastName = new TextField();
		form.add(lastName, 1,2);
		
		form.add(new Label("Email:"), 0,3);
		email = new TextField();
		form.add(email, 1,3);
		
		form.add(new Label("Phone Number:"), 0,4);
		phone = new TextField();
		form.add(phone, 1,4);
		
		form.add(new Label("Insurance ID:"), 0,5);
		insuranceID = new TextField();
		form.add(insuranceID, 1,5);
		
		form.add(new Label("Health History:"), 0,6);
		healthHistory = new TextArea();
		form.add(healthHistory, 1,6);
		
		// space for an alert message when requirements are not met
		Text alert = new Text("");
		form.add(alert, 1,8);
		
		// button to navigate to the previous page
		Button back = new Button("Menu");
		back.setOnAction(event -> setPrevScene());
		form.add(back, 0,7);
		
		// button to save form and navigate to previous page
		Button save = new Button("Save");
		save.setOnAction(event -> saveIntakeForm(alert, save));
		form.add(save, 1,7);		
		
		// setting scene for intake form
		intakeFormScene = new Scene(form, width, height);
		stage.setScene(intakeFormScene);
		stage.show();
	}
	
	// save patient data to a txt file
	private void saveIntakeForm(Text alert, Button save) {
		
		// verify if required fields are filled
		if(firstName.getText().isEmpty() || lastName.getText().isEmpty()) {
			alert.setFill(Color.RED);
			alert.setText("First and last name are required");
		} else {
			// get a unique ID
			String patientID = generatePatientID();
			
			// Create a directory for Patient Records if it doesn't exist
			File dir = new File("Patient Records");
			if(!dir.exists()) {
				dir.mkdirs();
			}
			String filename = "Patient Records" + File.separator + patientID + "_PatientInfo.txt";
			// open file and write form data
			try(BufferedWriter file = new BufferedWriter(new FileWriter(filename))) {
				file.write(patientID + "\n");
				file.write(firstName.getText() + "\n");
				file.write(lastName.getText() + "\n");
				file.write(email.getText() + "\n");
				file.write(phone.getText() + "\n");
				file.write(insuranceID.getText() + "\n");
				file.write(healthHistory.getText() + "\n");
				file.flush();
				file.close();
				// display process success and created user ID
				alert.setText("New File Created. Patient ID : " + patientID);
				alert.setFill(Color.GREEN);
				alert.setFont(Font.font("null", FontWeight.BOLD, 16));
				// disable save button to prevent accidental file creation
				save.setDisable(true);
			} catch (IOException E) {
				// alert on encountering error in File IO
				alert.setFill(Color.RED);
				alert.setText("SYSTEM FAILURE - Failed to save patient file");
			}
		}	
	}
	
	// create a 5-digit unique ID
	private String generatePatientID() {
		String id = Integer.toString((int)(Math.random()*89999+10000));
		// check for duplicate ID
		while(new File(id + "_PatientInfo.txt").exists()) {
			id = Integer.toString((int)(Math.random()*89999+10000));
		}
		return id;
	}
	
	// set previous scene (previous page)
	private void setPrevScene() {
		stage.setScene(prevScene);
		stage.show();
	}
}
