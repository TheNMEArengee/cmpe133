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
	
	private Point2D offset = new Point2D(0.0d, 0.0d);
	private boolean movingPiece = false;

	public void goToHomePage(ActionEvent event) throws IOException {
		BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("homepage.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}


//	public void startMovingPiece(MouseEvent evt) {
//		circle.setOpacity(0.4d);
//		offset = new Point2D(evt.getX(), evt.getY());
//
//		origPositionCircle.setOpacity(1.0d);
//		origPositionCircle.setLayoutX( circle.getLayoutX() );
//		origPositionCircle.setLayoutY( circle.getLayoutY() );
//
//		movingPiece = true;
//	}
//
//
//	public void movePiece(MouseEvent evt) {
//
//		Point2D mousePoint = new Point2D(evt.getX(), evt.getY());  
//		Point2D mousePoint_s = new Point2D(evt.getSceneX(), evt.getSceneY());
//		Rectangle r = pickRectangle( mousePointScene.getX(), mousePointScene.getY() );
//		if( !inBoard(mousePoint_s) ) {
//			return;  // don't relocate() b/c will resize Pane
//		}
//
//		Point2D mousePoint_p = circle.localToParent(mousePoint);  
//		circle.relocate(mousePoint_p.getX()-offset.getX(), mousePoint_p.getY()-offset.getY());
//	}
//
//	private boolean inBoard(Point2D pt) { 
//		Point2D panePt = boardPane.sceneToLocal(pt);
//		return panePt.getX()-offset.getX() >= 0.0d 
//				&& panePt.getY()-offset.getY() >= 0.0d 
//				&& panePt.getX() <= boardPane.getWidth() 
//				&& panePt.getY() <= boardPane.getHeight();
//	}
//
//
//	public void finishMovingPiece(MouseEvent evt) {
//
//		offset = new Point2D(0.0d, 0.0d);
//
//		Point2D mousePoint = new Point2D(evt.getX(), evt.getY());
//		Point2D mousePointScene = circle.localToScene(mousePoint);
//
//		Rectangle r = pickRectangle( mousePointScene.getX(), mousePointScene.getY() );
//
//		final Timeline timeline = new Timeline();
//		timeline.setCycleCount(1);
//		timeline.setAutoReverse(false);
//
//		if( r != null ) {
//
//			Point2D rectScene =r.localToScene(r.getX(), r.getY());   
//			Point2D parent = boardPane.sceneToLocal(rectScene.getX(), rectScene.getY());
//
//			timeline.getKeyFrames().add(
//					new KeyFrame(Duration.millis(100), 
//							new KeyValue(circle.layoutXProperty(), parent.getX()),
//							new KeyValue(circle.layoutYProperty(), parent.getY()),
//							new KeyValue(circle.opacityProperty(), 1.0d),
//							new KeyValue(origPositionCircle.opacityProperty(), 0.0d)
//							)
//					);
//		} else {
//
//			timeline.getKeyFrames().add(
//					new KeyFrame(Duration.millis(100), 
//							new KeyValue(circle.opacityProperty(), 1.0d),
//							new KeyValue(origPositionCircle.opacityProperty(), 0.0d)
//							)
//					);
//		}
//
//		timeline.play();
//
//		movingPiece = false;
//	}
}
