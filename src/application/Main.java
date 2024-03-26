package application;
	
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	int width = 1000;
	int height = 700;
	
	public void start(Stage primaryStage) {
		// Open the main menu on startup
		Menu menu = new Menu(primaryStage, width, height);
		menu.showMenu();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
