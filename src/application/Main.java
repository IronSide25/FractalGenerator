package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/Window.fxml"));
			primaryStage.setTitle("Fractal Generator");
			primaryStage.setScene(new Scene(root, 1000,1000));
			primaryStage.show();			
		} catch(Exception e) {
			e.printStackTrace();
		}

		System.out.println("aaaaaaaaaaa");
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
