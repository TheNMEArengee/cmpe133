package application;

import java.io.IOException;

import application.EventHandlers.MousePressedAction;
import application.EventHandlers.MouseReleasedAction;
import application.GameBoard.Checkerboard;
import application.GameBoard.CheckerboardPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Node;


public class homepageController {

	public void GoToLogin(ActionEvent event) throws IOException {
		BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("login.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.close();
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


		//Undo button
		Button undoBtn = new Button("Undo");
		undoBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			//If there is no move to undo, tell the user
			if(checkerboardPane.getMostRecentlyRemovedUnit() == null && checkerboardPane.getMostRecentlyMovedUnit() == null) {
				System.out.println("No move to undo");
			}
			else {
				//Reset position and previous position of recently moved unit, if there is one
				if(checkerboardPane.getMostRecentlyMovedUnit() != null) {
					Unit u = checkerboardPane.getMostRecentlyMovedUnit();
					int oldX = u.getX();
					int oldY = u.getY();
					int math = (oldX + oldY) % 2;
					GraphicsContext gc = checkerboardPane.getGraphicsContext();
					
					
					//Changing values of moved piece
					u.setX(u.getPrevX());
					u.setY(u.getPrevY());
					u.setPrevX(u.getX());
					u.setPrevY(u.getY());
					
					
					//Removing residual image
					gc.clearRect(oldX * checkerboard.getTileSize(), oldY * checkerboard.getTileSize(), 60, 60);
					if((oldX + oldY) % 2 == 0) { // Set fill to green if the square is a gray square
						gc.setFill(Color.GREEN);
					}
					else { // Otherwise, set fill to white
						gc.setFill(Color.WHITE);
					}
					gc.fillRect(oldX * checkerboard.getTileSize(), oldY * checkerboard.getTileSize(), 60, 60);
					checkerboardPane.setMostRecentlyMovedUnit(null);
				}
				
				
				//Return eaten unit to the board and reset value to null, if there is one
				if(checkerboardPane.getMostRecentlyRemovedUnit() != null) {
					checkerboardPane.getUnits().add(checkerboardPane.getMostRecentlyRemovedUnit());
					checkerboardPane.setMostRecentlyRemovedUnit(null);
				}
				checkerboardPane.drawUnits();			
			}
		});
		undoBtn.setPrefSize(100, 60);
		
		
		//Confirm button
		Button confirmBtn = new Button("Confirm");
		confirmBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			if(checkerboardPane.getMostRecentlyMovedUnit() == null) {
				System.out.println("No move made yet");
			}
			else {
				checkerboard.changePlayerTurn();
				System.out.println("Player " + checkerboard.getCurrPlayer() + " ("
						+ checkerboard.getCurrPlayerToString() + ")" + " turn.");
				checkerboardPane.setMostRecentlyMovedUnit(null);
			}

		});
		confirmBtn.setPrefSize(100, 60);
		
		
		HBox turnButtons = constructHBox();
		turnButtons.getChildren().addAll(undoBtn, confirmBtn);


		//Back button
		Button backBtn = new Button("Back");
		backBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			window.close();
			window.setScene(scene);
			window.show();
		});
		backBtn.setPrefSize(100, 60);


		battleField.getChildren().addAll(checkerboardPane, turnButtons, backBtn);
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
		window.close();
		window.setScene(scene);
		window.show();
	}


	public void quit(ActionEvent event) throws IOException {
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.close();
	}


	// Constructs a VBox with the dimensions specified below
	private VBox constructVBox() {
		VBox vbox = new VBox();
		vbox.setSpacing(10);
		vbox.setAlignment(Pos.CENTER);

		return vbox;
	}
	
	
	// Constructs an HBox with the dimensions specified below
		private HBox constructHBox() {
			HBox hbox = new HBox();
			hbox.setSpacing(10);
			hbox.setAlignment(Pos.CENTER);

			return hbox;
		}
}