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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PatientIntake {
	
	private Stage stage;
	int width;
	int height;
	private TextField firstName;
	private TextField lastName;
	private TextField email;
	private TextField phone;
	private TextField insuranceID;
	private TextArea healthHistory;
	
	PatientIntake(Stage stage, int w, int h) {
		this.stage = stage;
		width = w;
		height = h;
	}
	
	public void showForm() {
		GridPane form = new GridPane();
		form.setAlignment(Pos.CENTER);
		form.setVgap(20);
		form.setHgap(50);
		
		Text header = new Text("Patient Intake Form");
		header.setFont(Font.font("null", FontWeight.BOLD, 16));
		form.add(header, 1,0);
		
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
		
		Button save = new Button("Save");
		save.setOnAction(event -> saveIntakeForm());
		form.add(save, 1,7);
		
		Scene intakeFormScene = new Scene(form, width, height);
		stage.setScene(intakeFormScene);
		stage.setTitle("Patient Intake Form");
		stage.show();
	}
	
	
	public void saveIntakeForm() {
		
		if(firstName.getText().isEmpty() || lastName.getText().isEmpty()) {
			System.out.println("First name and last name are required");
			
		} else {
			String patientID = generatePatientID();
			String dirname = "Patient Records";
			File dir = new File(dirname);
			// Create a directory for Patient Records if it doesn't exist
			if(!dir.exists()) {
				dir.mkdirs();
			}
			
			String filename = dirname + File.separator + patientID + "_PatientInfo.txt";
			
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
				System.out.println("Patient file created");
			} catch (IOException E) {
				System.out.println("Failed to create Patient File");
			}
		}
		
		
	}
	
	private String generatePatientID() {
		String id = Integer.toString((int)(Math.random()*89999+10000));
		while(new File(id + "_PatientInfo.txt").exists()) {
			id = Integer.toString((int)(Math.random()*89999+10000));
		}
		return id;
	}
}