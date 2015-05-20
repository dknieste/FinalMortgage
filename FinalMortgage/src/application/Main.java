package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	
	    public static void main(String[] args) {
	        Application.launch(Main.class, (java.lang.String[])null);
	    }

	    @Override
	    public void start(Stage primaryStage) {
	        try {
	            AnchorPane panel = FXMLLoader.load(Main.class.getResource("MortgageView.fxml"));
	            Scene scene = new Scene(panel);
	            primaryStage.setScene(scene);
	            primaryStage.setTitle("Mortgage Calculator");
	            primaryStage.show();
	        } catch (Exception ex) {
	        	ex.printStackTrace();
	    }
	}
}
