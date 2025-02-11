package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Node;


public class tutorialController {		
		
	public void goToHomePage(ActionEvent event) throws IOException {
		BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("homepage.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.close();
		window.setScene(scene);
		window.show();
	}
	
	
	public void nextPage(ActionEvent event) throws IOException {
		AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("tutorial2.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.close();
		window.setScene(scene);
		window.show();
	}
	
	
	public void previousPage(ActionEvent event) throws IOException {
		AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("tutorial.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.close();
		window.setScene(scene);
		window.show();
	}
}
