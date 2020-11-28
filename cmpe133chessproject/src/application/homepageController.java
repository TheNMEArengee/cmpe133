package application;

import java.io.IOException;

import Controller.PressedAction;
import Controller.ReleaseAction;
import Controller.ResetAction;
import application.EventHandlers.MousePressedAction;
import application.EventHandlers.MouseReleasedAction;
import application.GameBoard.Checkerboard;
import application.GameBoard.CheckerboardPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.Node;
import view.ChessBoard;
import view.ChessPane;


public class homepageController {

	public void GoToLogin(ActionEvent event) throws IOException {
		BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("login.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}


	public void goToGame(ActionEvent event) throws IOException {
		// Event for start button. Sends the user to the game


		/* Current Code */
		//Initializing variables
		BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("homepage.fxml"));
		Scene scene = new Scene(root);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		
		window.close();
		VBox battleField = constructVBox();
		Checkerboard checkerboard = Checkerboard.getInstance();
		checkerboard.setCurrentPlayer(0); // Ensure Player 0 starts every time 'Start' is pressed.
		CheckerboardPane checkerboardPane = new CheckerboardPane(checkerboard);
		
		
		//Back button
		Button backBtn = new Button("Back");
		backBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			window.setScene(scene);
		});
		backBtn.setPrefSize(100, 60);
		
		
		battleField.getChildren().addAll(checkerboardPane, backBtn);
		checkerboardPane.setOnMousePressed(new MousePressedAction(checkerboardPane));	
		checkerboardPane.setOnMouseReleased(new MouseReleasedAction(checkerboardPane, window, scene));
		
		
		BorderPane bp = new BorderPane();
		bp.setCenter(battleField);
		Scene battleFieldInit = new Scene(bp, 480, 650);	
		window.setScene(battleFieldInit);
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


	// Constructs a VBox with the dimensions specified below
	private VBox constructVBox() {
		VBox vbox = new VBox();
		vbox.setSpacing(10);
		vbox.setAlignment(Pos.CENTER);

		return vbox;
	}
}