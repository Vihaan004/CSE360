package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		int width = 1000;
		int height = 700;
		
		Group root = new Group();
		root.getChildren().addAll();
		
		Scene scene = new Scene(root, width, height);
		setupStage(stage, scene);
	}
	
	private void setupStage(Stage stage, Scene scene)
	{
		stage.setTitle("Heart Health Imaging and Recording System");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
