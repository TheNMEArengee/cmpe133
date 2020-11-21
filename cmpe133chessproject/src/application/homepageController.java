package application;

import java.io.IOException;

import Controller.PressedAction;
import Controller.ReleaseAction;
import Controller.ResetAction;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
		/*
		 * Old code
		 */	
//		AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("chessGameBoard1.fxml"));
//		Scene scene = new Scene(root);
//		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
//		window.setScene(scene);
//		window.show();
		
		
		/*
		 * Hao's Code
		 */
		ChessBoard chessBoard = ChessBoard.getInstance(68.75,25,25);
		ChessPane pane = new ChessPane(chessBoard);
		pane.setOnMousePressed(new PressedAction(pane));

		pane.setOnMouseReleased(new ReleaseAction(pane));

		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(pane);
		HBox hBox = new HBox();
		hBox.setAlignment(Pos.TOP_CENTER);
		Button button = new Button("Regret");
		button.setOnAction(new ResetAction(pane));
		Button button1 = new Button("back");
		button1.setOnAction(new GoHomeAction(pane));
		hBox.getChildren().add(button);
		hBox.getChildren().add(button1);
		borderPane.setBottom(hBox);
		Scene scene = new Scene(borderPane,600,600);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.setTitle("Chess");
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