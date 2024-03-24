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

public class CTScan {
	
	private Stage stage;
	int width;
	int height;
	private TextField patientID;
	private TextField CAC;
	private TextField LM;
	private TextField LAD;
	private TextField LCX;
	private TextField RCA;
	private TextField PDA;
	
	
	CTScan(Stage stage, int w, int h) {
		this.stage = stage;
		width = w;
		height = h;
	}
	
	public void showCTScanForm() {
		GridPane form = new GridPane();
		form.setAlignment(Pos.CENTER);
		form.setVgap(20);
		form.setHgap(100);
		
//		Text header = new Text("Patient Intake Form");
//		header.setFont(Font.font("null", FontWeight.BOLD, 16));
//		form.add(header, 1,0);
		
		form.add(new Label("Patient ID:"), 0,0);
		patientID = new TextField();
		form.add(patientID, 1,0);
		
		form.add(new Label("The total Agaston CAC Score: "), 0,1);
		CAC = new TextField();
		form.add(CAC, 1,1);
		
		form.add(new Text("Vessel level Agaston CAC Score "), 1,2);
		
		form.add(new Label("LM:"), 0,3);
		LM = new TextField();
		form.add(LM, 1,3);
		
		form.add(new Label("LAD:"), 0,4);
		LAD = new TextField();
		form.add(LAD, 1,4);
		
		form.add(new Label("LCX:"), 0,5);
		LCX = new TextField();
		form.add(LCX, 1,5);
		
		form.add(new Label("RCA:"), 0,6);
		RCA = new TextField();
		form.add(RCA, 1,6);
		
		form.add(new Label("PDA:"), 0,7);
		PDA = new TextField();
		form.add(PDA, 1,7);
		
		Button save = new Button("Save");
		save.setOnAction(event -> saveCTForm());
		form.add(save, 1,8);
		
		Scene CTFormScene = new Scene(form, width, height);
		stage.setScene(CTFormScene);
		stage.setTitle("CT Scan");
		stage.show();
	}
	
	private boolean isEmpty(TextField field) {
		return field.getText().isEmpty() ? true : false;
	}
	
	public void saveCTForm() {
		
		
		if(isEmpty(CAC)||isEmpty(LM)||isEmpty(LAD)||isEmpty(LCX)||isEmpty(RCA)||isEmpty(PDA)) {
			System.out.println("One or more fields are missing");
		}
		else if(!new File("Patient Records" + File.separator + patientID.getText() + "_PatientInfo.txt").exists()) {
			System.out.println("ID does not exist");
		} else {
			System.out.println("yay");
			String filename = "Patient Records" + File.separator + patientID.getText() + "CTResults.txt";
			try(BufferedWriter file = new BufferedWriter(new FileWriter(filename))) {
				file.write(CAC.getText() + "\n");
				file.write(LM.getText() + "\n");
				file.write(LAD.getText() + "\n");
				file.write(LCX.getText() + "\n");
				file.write(RCA.getText() + "\n");
				file.write(PDA.getText() + "\n");
				file.flush();
				file.close();
				System.out.println("Results file created");
			
			} catch (IOException E) {
				System.out.println("Failed to create Results File");
			}
		}
	}
}
