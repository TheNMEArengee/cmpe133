package application.GameBoard;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import application.Unit;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;

//Checkerboard field's controller basically
public class CheckerboardPane extends Pane {
	private Set<Unit> units; // Player's pawns, or units
	private Unit mostRecentlyMovedUnit; // Most recently moved unit. For undo purposes
	private Unit mostRecentlyRemovedUnit; // Most recently removed unit. For undo purposes
	private Checkerboard checkerboard; // Checkerboard instance
	private GridPane checkerboardGridPane; // Grid to place the checkerboard
	private Group tileGroup; // For checkerboard tiles
	private Canvas canvas;
	private GraphicsContext gc;

	// Constructor for CheckerboardPane
	public CheckerboardPane(Checkerboard checkerboard) {
		this.checkerboard = checkerboard;
		this.checkerboardGridPane = new GridPane();
		tileGroup = new Group();
		checkerboardGridPane.getChildren().add(tileGroup);
		this.units = new HashSet<>();
		this.mostRecentlyMovedUnit = null;
		this.mostRecentlyRemovedUnit = null;
		setUnits();
		canvas = new Canvas(800,480);
		gc = canvas.getGraphicsContext2D();
		draw();
	}

	// Place the player units onto board (init/reset)
	public void setUnits() {
		// Units for player 0
		int player = 0;
		units.add(new Unit(0, 0, player, 2)); //Rook
		units.add(new Unit(1, 0, player, 4)); //Knight
		units.add(new Unit(2, 0, player, 3)); //Bishop
		units.add(new Unit(3, 0, player, 5)); //Queen
		units.add(new Unit(4, 0, player, 1)); //King
		units.add(new Unit(5, 0, player, 3)); //Bishop
		units.add(new Unit(6, 0, player, 4)); //Knight
		units.add(new Unit(7, 0, player, 2)); //Rook

		//Pawns
		int y = 1;
		for (int x = 0; x < 8; x++) {
			units.add(new Unit(x, y, player, 0));
		}

		// Units for Player 1
		player = 1;
		units.add(new Unit(0, 7, player, 2)); //Rook
		units.add(new Unit(1, 7, player, 4)); //Knight
		units.add(new Unit(2, 7, player, 3)); //Bishop
		units.add(new Unit(3, 7, player, 5)); //Queen
		units.add(new Unit(4, 7, player, 1)); //King
		units.add(new Unit(5, 7, player, 3)); //Bishop
		units.add(new Unit(6, 7, player, 4)); //Knight
		units.add(new Unit(7, 7, player, 2)); //Rook

		//Pawns
		y = 6;
		for (int x = 0; x < 8; x++) {
			units.add(new Unit(x, y, player, 0));
		}
	}


	// Used in ctor to draw and show everything
	public void draw() {
		drawBoard();
		drawUnits();
		getChildren().add(checkerboardGridPane);
		getChildren().add(canvas);
	}

	// Draw the checkerboard
	public void drawBoard() {
		int tileSize = checkerboard.getTileSize();
		// Nested for loop to draw 8x8 checkerboard
		// Using i, j since x, y used as coordinate variables
		for (int j = 0; j < checkerboard.getCols(); j++) { // 0-7
			for (int i = 0; i < checkerboard.getRows(); i++) { // 0-7
				// Rectangle(x coord, y coord, width, height)
				int x = checkerboard.getInitX() + (i * tileSize);
				int y = checkerboard.getInitY() + (j * tileSize);
				Rectangle r = new Rectangle(x, y, tileSize, tileSize);
				// Alternate Colors
				if ((i + j) % 2 == 0) {
					r.setFill(Color.GREEN);
				} else {
					r.setFill(Color.WHITE);
				}

				tileGroup.getChildren().add(r);
			}
		}
	}

	// Draw the units using the "units" set that was initialized in setUnits()
	public void drawUnits() {
		int tileSize = checkerboard.getTileSize();
		// Traverse entire "units" set
		units.forEach(u -> {
			int x = 10 + (u.getX() * tileSize);
			int y = 10 + (u.getY() * tileSize);
			Rectangle r = new Rectangle(x, y, 40, 40);


			// Determine color of pieces, check which player the unit belongs to
			if (u.getPlayer() == 0) {
				if (u.getRole() == 0) { // Pawn
					Image i = new Image("img/SoldierWhite.jpg", 40, 40, true, true);
					gc.drawImage(i, x, y, 40, 40);
				}
				else if(u.getRole() == 1) { //King
					Image i = new Image("img/KingWhite.jpg", 40, 40, true, true);
					gc.drawImage(i, x, y, 40, 40);
				}
				else if(u.getRole() == 2) { //Rook
					Image i = new Image("img/CarWhite.jpg", 40, 40, true, true);
					gc.drawImage(i, x, y, 40, 40);
				}
				else if(u.getRole() == 3) { //Bishop
					Image i = new Image("img/KnightWhite.jpg", 40, 40, true, true);
					gc.drawImage(i, x, y, 40, 40);
				}
				else if(u.getRole() == 4) { //Knight
					Image i = new Image("img/HorseWhite.jpg", 40, 40, true, true);
					gc.drawImage(i, x, y, 40, 40);
				}
				else if(u.getRole() == 5) { //Queen
					Image i = new Image("img/QueenWhite.jpg", 40, 40, true, true);
					gc.drawImage(i, x, y, 40, 40);
				}
			} 
			else {
				if (u.getRole() == 0) { // Pawn
					Image i = new Image("img/SoldierBlack.jpg", 40, 40, true, true);
					gc.drawImage(i, x, y, 40, 40);
				}
				else if(u.getRole() == 1) { //King
					Image i = new Image("img/KingBlack.jpg", 40, 40, true, true);
					gc.drawImage(i, x, y, 40, 40);
				}
				else if(u.getRole() == 2) { //Rook
					Image i = new Image("img/CarBlack.jpg", 40, 40, true, true);
					gc.drawImage(i, x, y, 40, 40);
				}
				else if(u.getRole() == 3) { //Bishop
					Image i = new Image("img/KnightBlack.jpg", 40, 40, true, true);
					gc.drawImage(i, x, y, 40, 40);
				}
				else if(u.getRole() == 4) { //Knight
					Image i = new Image("img/HorseBlack.jpg", 40, 40, true, true);
					gc.drawImage(i, x, y, 40, 40);
				}
				else if(u.getRole() == 5) { //Queen
					Image i = new Image("img/QueenBlack.jpg", 40, 40, true, true);
					gc.drawImage(i, x, y, 40, 40);
				}
			}
			r.setStroke(Color.BLACK);
			tileGroup.getChildren().add(r);
		});
	}


	/* Get methods */
	public Checkerboard getCheckerBoard() {
		return this.checkerboard;
	}

	public Set<Unit> getUnits() {
		return units;
	}
	
	public GraphicsContext getGraphicsContext() {
		return gc;
	}

	
	public Unit getMostRecentlyMovedUnit() {
		return mostRecentlyMovedUnit;
	}
	

	public void setMostRecentlyMovedUnit(Unit mostRecentlyMovedUnit) {
		this.mostRecentlyMovedUnit = mostRecentlyMovedUnit;
	}

	public Unit getMostRecentlyRemovedUnit() {
		return mostRecentlyRemovedUnit;
	}

	public void setMostRecentlyRemovedUnit(Unit mostRecentlyRemovedUnit) {
		this.mostRecentlyRemovedUnit = mostRecentlyRemovedUnit;
	}
}
