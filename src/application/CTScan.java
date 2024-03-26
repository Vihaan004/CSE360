package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CTScan {
	
	private Stage stage;
	int width, height;
	private TextField patientID, CAC, LM, LAD, LCX, RCA, PDA;
	private Scene prevScene, CTScanScene;
	
	CTScan(Stage stage, int w, int h, Scene prevScene) {
		this.stage = stage;
		width = w;
		height = h;
		this.prevScene = prevScene;
	}
	
	// display the CT Scan report form 
	public void showCTScanForm() {
		GridPane form = new GridPane();
		form.setAlignment(Pos.CENTER);
		form.setVgap(20);
		form.setHgap(100);
		
		// form title : 
		Text header = new Text("CT Scan Form");
		header.setFont(Font.font("null", FontWeight.BOLD, 16));
		form.add(header, 1,0);
		
		// form fields:
		form.add(new Label("Patient ID:"), 0,1);
		patientID = new TextField();
		form.add(patientID, 1,1);
		
		form.add(new Label("The total Agaston CAC Score: "), 0,2);
		CAC = new TextField();
		form.add(CAC, 1,2);
		
		form.add(new Text("Vessel level Agaston CAC Score "), 1,3);
		
		form.add(new Label("LM:"), 0,4);
		LM = new TextField();
		form.add(LM, 1,4);
		
		form.add(new Label("LAD:"), 0,5);
		LAD = new TextField();
		form.add(LAD, 1,5);
		
		form.add(new Label("LCX:"), 0,6);
		LCX = new TextField();
		form.add(LCX, 1,6);
		
		form.add(new Label("RCA:"), 0,7);
		RCA = new TextField();
		form.add(RCA, 1,7);
		
		form.add(new Label("PDA:"), 0,8);
		PDA = new TextField();
		form.add(PDA, 1,8);
		
		// space for an alert message when requirements are not met
		Text alert = new Text("");
		form.add(alert, 1,10);
		
		Button back = new Button("Menu");
		back.setOnAction(event -> setPrevScene());
		form.add(back, 0,9);
		
		Button save = new Button("Save");
		save.setOnAction(event -> saveCTForm(alert, save));
		form.add(save, 1,9);
		
		CTScanScene = new Scene(form, width, height);
		stage.setScene(CTScanScene);
		stage.show();
	}
	
	// check if a TextField object is empty of text
	private boolean isEmpty(TextField field) {
		return field.getText().isEmpty() ? true : false;
	}
	
	private void saveCTForm(Text alert, Button save) {
		// verify if all fields are filled
		if(isEmpty(CAC)||isEmpty(LM)||isEmpty(LAD)||isEmpty(LCX)||isEmpty(RCA)||isEmpty(PDA)) {
			alert.setFill(Color.RED);
			alert.setText("One or more fields are missing");
		}
		// search for patient file by ID
		else if(!new File("Patient Records" + File.separator + patientID.getText() + "_PatientInfo.txt").exists()) {
			alert.setFill(Color.RED);
			alert.setText("Invalid ID");
		} else {
			String filename = "Patient Records" + File.separator + patientID.getText() + "CTResults.txt";
			// open file and write CT Scan data
			try(BufferedWriter file = new BufferedWriter(new FileWriter(filename))) {
				file.write(CAC.getText() + "\n");
				file.write(LM.getText() + "\n");
				file.write(LAD.getText() + "\n");
				file.write(LCX.getText() + "\n");
				file.write(RCA.getText() + "\n");
				file.write(PDA.getText() + "\n");
				file.flush();
				file.close();
				alert.setText("Results file saved for : " + patientID.getText());
				alert.setFill(Color.GREEN);
				// disable save button
				save.setDisable(true);
			} catch (IOException E) {
				// alert on encountering error in File IO
				alert.setFill(Color.RED);
				alert.setText("SYSTEM FAILURE - Failed to save results file");
			}
		}
	}

	// set previous scene (previous page)
	private void setPrevScene() {
		stage.setScene(prevScene);
		stage.show();
		
	}
}
