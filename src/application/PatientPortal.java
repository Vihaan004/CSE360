// Vihaan Patel - 1225904942
package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

public class PatientPortal {
	
	private Stage stage;
	int width, height;
	private TextField patientID, CAC, LM, LAD, LCX, RCA, PDA;
	private String patientName;
	private Scene prevScene, patientLoginScene, patientInfoScene;
	
	PatientPortal(Stage stage, int w, int h, Scene prevScene) {
		this.stage = stage;
		width = w;
		height = h;
		this.prevScene = prevScene;
		patientName = "";
	}
	
	public void showPatientPortal() {
		GridPane form = new GridPane();
		form.setAlignment(Pos.CENTER);
		form.setVgap(10);
		form.setHgap(50);
		
		form.add(new Label("Enter Patient ID"), 0,0);
		patientID = new TextField();
		form.add(patientID, 1,0);
		
		// space for an alert message when requirements are not met
		Text alert = new Text("");
		form.add(alert, 1,3);
				
		Button back = new Button("Menu");
		back.setOnAction(event -> setPrevScene());
		form.add(back, 0,2);
		
		Button enter = new Button("Enter");
		enter.setOnAction(event -> showPatientInfo(alert));
		form.add(enter, 1,2);
		
		// setting patient login scene
		patientLoginScene = new Scene(form, width, height);
		stage.setScene(patientLoginScene);
		stage.show();
	}
	
	public void showPatientInfo(Text alert) {
		// search for the Results file by ID
		if(!new File("Patient Records" + File.separator + patientID.getText() + "CTResults.txt").exists()) {
			alert.setFill(Color.RED);
			alert.setText("CT file for "+ patientID.getText() + " does not exist");
		} else {
			getPatientInfo();
			GridPane form = new GridPane();
			form.setAlignment(Pos.CENTER);
			form.setVgap(20);
			form.setHgap(100);
			
			// title :
			Text header = new Text("Hello " + patientName);
			header.setFont(Font.font("null", FontWeight.BOLD, 16));
			form.add(header, 1,0);
			
			// fields :
			form.add(new Label("The total Agaston CAC Score: "), 0,1);
			form.add(CAC, 1,1);
			
			form.add(new Text("Vessel level Agaston CAC Score "), 1,2);
			
			form.add(new Label("LM:"), 0,3);
			form.add(LM, 1,3);
			
			form.add(new Label("LAD:"), 0,4);
			form.add(LAD, 1,4);
			
			form.add(new Label("LCX:"), 0,5);
			form.add(LCX, 1,5);
			
			form.add(new Label("RCA:"), 0,6);
			form.add(RCA, 1,6);
			
			form.add(new Label("PDA:"), 0,7);
			form.add(PDA, 1,7);
			
			Button back = new Button("Menu");
			back.setOnAction(event -> setPrevScene());
			form.add(back, 0,8);
			
			// setting patient information scene
			patientInfoScene = new Scene(form, width, height);
			stage.setScene(patientInfoScene);
			stage.show();	
		}
	}
	
	
	// retrieve patient data from Patient Records 
	public void getPatientInfo() {
		// search for info and results file by ID
		File infoFile = new File("Patient Records" + File.separator + patientID.getText() + "_PatientInfo.txt");
		File resultsFile = new File("Patient Records" + File.separator + patientID.getText() + "CTResults.txt");
		
		// open info file and read data (get patient name)
		try(Scanner info = new Scanner(infoFile);) {
			if(info.hasNextLine()) {
	            info.nextLine();
	            if(info.hasNextLine()) {
	                patientName += info.nextLine() + " "; 
	            }
	            if(info.hasNextLine()) {
	            	patientName += info.nextLine();
	            }
	        }
			info.close();
		} catch (FileNotFoundException e) {
			System.out.println("SYSTEM FAILURE");
			System.exit(0);
		}
		// open results file and read data
		try(Scanner results = new Scanner(resultsFile)) {
			if(results.hasNextLine()) {
				CAC = new TextField(results.nextLine());
				LM = new TextField(results.nextLine());
				LAD = new TextField(results.nextLine());
				LCX = new TextField(results.nextLine());
				RCA = new TextField(results.nextLine());
				PDA = new TextField(results.nextLine());
			}
			results.close();
		} catch (FileNotFoundException e) {
			System.out.println("SYSTEM FAILURE");
			System.exit(0);
		}
		// disable text field editable property
		CAC.setEditable(false);
		LM.setEditable(false);
		LAD.setEditable(false);
		LCX.setEditable(false);
		RCA.setEditable(false);
		PDA.setEditable(false);
	}
	
	// set previous scene (previous page)
	private void setPrevScene() {
		stage.setScene(prevScene);
		stage.show();
	}
}
