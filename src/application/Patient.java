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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Patient {
	private Stage stage;
	int width;
	int height;
	private TextField patientID;
	private String patientName;
	private TextField CAC;
	private TextField LM;
	private TextField LAD;
	private TextField LCX;
	private TextField RCA;
	private TextField PDA;	
	Scene prevScene;
	
	Patient(Stage stage, int w, int h, Scene prevScene) {
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
		
		Button back = new Button("Menu");
		back.setOnAction(event -> backToMenu());
		form.add(back, 0,2);
		
		Button enter = new Button("Enter");
		enter.setOnAction(event -> showPatientInfo());
		form.add(enter, 1,2);
		
		Scene patientLoginScene = new Scene(form, width, height);
		stage.setScene(patientLoginScene);
		stage.show();
	}
	
	public void showPatientInfo() {
		
		if(!new File("Patient Records" + File.separator + patientID.getText() + "CTResults.txt").exists()) {
			System.out.println("Patient CT file does not exist");
		} else {
			getPatientInfo();
			GridPane form = new GridPane();
			form.setAlignment(Pos.CENTER);
			form.setVgap(20);
			form.setHgap(100);
			
			Text header = new Text("Hello " + patientName);
			header.setFont(Font.font("null", FontWeight.BOLD, 16));
			form.add(header, 1,0);
		
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
			back.setOnAction(event -> backToMenu());
			form.add(back, 0,8);
			
			Scene CTFormScene = new Scene(form, width, height);
			stage.setScene(CTFormScene);
			stage.show();	
		}
	}
	
	public void getPatientInfo() {
		File infoFile = new File("Patient Records" + File.separator + patientID.getText() + "_PatientInfo.txt");
		File resultsFile = new File("Patient Records" + File.separator + patientID.getText() + "CTResults.txt");
		
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
		CAC.setEditable(false);
		LM.setEditable(false);
		LAD.setEditable(false);
		LCX.setEditable(false);
		RCA.setEditable(false);
		PDA.setEditable(false);
	}
	
	private void backToMenu() {
		stage.setScene(prevScene);
		stage.show();
	}
}
