package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;


public class homepageController {
	
	public void GoToLogin(ActionEvent event) throws IOException {
		AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("tutorial.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
	
	
	public void goToGame(ActionEvent event) throws IOException {



		AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("chessGameBoard1.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
	
	
	public void goToTutorial(ActionEvent event) throws IOException {
		AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("tutorial.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
	
	
	public void quit(ActionEvent event) throws IOException {
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.hide();
	}
}