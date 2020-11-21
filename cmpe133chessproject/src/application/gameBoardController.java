package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class gameBoardController {

	public void goToHomePage(ActionEvent event) throws IOException {
		BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("homepage.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}


	public void movePiece(MouseEvent evt) {

		//		Point2D mousePoint = new Point2D(evt.getX(), evt.getY());  
		//		Point2D mousePoint_s = new Point2D(evt.getSceneX(), evt.getSceneY());
		//		Rectangle r = pickRectangle( mousePointScene.getX(), mousePointScene.getY() );
		//		//		 if( !inBoard(mousePoint_s) ) {
		//		//		  return;  // don't relocate() b/c will resize Pane
		//		//		 }
		//
		//		Point2D mousePoint_p = circle.localToParent(mousePoint);  
		//		circle.relocate(mousePoint_p.getX()-offset.getX(), mousePoint_p.getY()-offset.getY());
	}

	//	private boolean inBoard(Point2D pt) { 
	//		 Point2D panePt = boardPane.sceneToLocal(pt);
	//		 return panePt.getX()-offset.getX() >= 0.0d 
	//		   && panePt.getY()-offset.getY() >= 0.0d 
	//		   && panePt.getX() <= boardPane.getWidth() 
	//		   && panePt.getY() <= boardPane.getHeight();
	//		}
}
