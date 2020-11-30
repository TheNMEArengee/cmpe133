package application.EventHandlers;

import application.Unit;
import application.GameBoard.Checkerboard;
import application.GameBoard.CheckerboardPane;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;


// Used for unit movement (selecting unit to move)
public class MousePressedAction implements EventHandler<MouseEvent> {
	private CheckerboardPane checkerboardPane;
	private GraphicsContext gc;

	// Constructor
	public MousePressedAction(CheckerboardPane checkerboardPane) {
		this.checkerboardPane = checkerboardPane;
		this.gc = checkerboardPane.getGraphicsContext();
	}

	// Main handler
	@Override
	public void handle(MouseEvent e) {
		Checkerboard checkerboard = checkerboardPane.getCheckerBoard();
		int tileSize = checkerboard.getTileSize();

		// Location of mouse press, translated to grid
		int pressedX = (int) e.getX() / tileSize;
		int pressedY = (int) e.getY() / tileSize;

		// Traverse 'units' set in checkerboardPane to redraw/update units
		checkerboardPane.getUnits().forEach(unit -> {
			// If unit's coordinates match calculated mouse press coordinates
			if (unit.getX() == pressedX && unit.getY() == pressedY) {
				//				System.out.println("-----------------------------------");
				//				System.out.println("Selected Unit: " + unit.getX() + ", " + unit.getY() + ".");
				//            	System.out.println("Pressed: " + pressedX + ", " + pressedY);
				colorValidSquares(unit);				
				unit.setSelected(true);
				checkerboardPane.drawUnits();
			}
		});
	}


	private void colorValidSquares(Unit unit) {
		if(unit.getPlayer() == checkerboardPane.getCheckerBoard().getCurrPlayer()) {
			gc.setFill(Color.BLUE);
			gc.fillRect(unit.getX() * 60, unit.getY() * 60, 60, 60);
			if(unit.getRole() == 0) {
				colorPawnSquares(unit);
			}
			else if(unit.getRole() == 1) {
				colorKingSquares(unit);
			}
			else if(unit.getRole() == 2) {
				colorRookSquares(unit);
			}
			else if(unit.getRole() == 3) {
				colorBishopSquares(unit);
			}
			else if(unit.getRole() == 4) {
				colorKnightSquares(unit);
			}
			else {
				colorQueenSquares(unit);
			}
		}

	}

	//Highlights all the valid squares a queen can move
	private void colorQueenSquares(Unit unit) {
		colorRookSquares(unit);
		colorBishopSquares(unit);

	}

	
	//Highlights all the valid squares a Knight can move
	private void colorKnightSquares(Unit unit) {
		Unit otherUnit = null;
		

		//Check top squares
		otherUnit = getUnitAt(unit.getX(), unit.getY() - 2);
		if(otherUnit == null) {
			//Check top left square
			otherUnit = getUnitAt(unit.getX() + 1, unit.getY() - 2);
			if(otherUnit == null || (otherUnit != null && areOpposingUnits(unit, otherUnit))) {
				gc.fillRect((unit.getX() + 1) * 60, unit.getY() * 60, 60, 60);
			}
			
			
			
//			if(otherUnit == null || areOpposingUnits(unit, otherUnit)) {
//				gc.fillRect((unit.getX() + 1) * 60, (unit.getY() - 2) * 60, 60, 60);
//			}
			
			otherUnit = getUnitAt(unit.getX() - 1, unit.getY() - 2);
			if(otherUnit == null || (otherUnit != null && areOpposingUnits(unit, otherUnit))) {
				gc.fillRect((unit.getX() - 1) * 60, (unit.getY() - 2) * 60, 60, 60);
			}
//			
//			if(otherUnit == null || areOpposingUnits(unit, otherUnit)) {
//				gc.fillRect((unit.getX() - 1) * 60, (unit.getY() - 2) * 60, 60, 60);
//			}
		}


		//Check left squares
		otherUnit = getUnitAt(unit.getX() - 2, unit.getY());
		if(otherUnit == null) {
			otherUnit = getUnitAt(unit.getX() - 2, unit.getY() + 1);
			if(otherUnit == null || areOpposingUnits(unit, otherUnit)) {
				gc.fillRect((unit.getX() - 2) * 60, (unit.getY() + 1) * 60, 60, 60);
			}
			otherUnit = getUnitAt(unit.getX() - 2, unit.getY() - 1);
			if(otherUnit == null || areOpposingUnits(unit, otherUnit)) {
				gc.fillRect((unit.getX() - 2) * 60, (unit.getY() - 1) * 60, 60, 60);
			}
		}


		//Check right squares
		otherUnit = getUnitAt(unit.getX() + 2, unit.getY());
		if(otherUnit == null) {
			otherUnit = getUnitAt(unit.getX() + 2, unit.getY() + 1);
			if(otherUnit == null || areOpposingUnits(unit, otherUnit)) {
				gc.fillRect((unit.getX() + 2) * 60, (unit.getY() + 1) * 60, 60, 60);
			}
			otherUnit = getUnitAt(unit.getX() - 2, unit.getY() - 1);
			if(otherUnit == null || areOpposingUnits(unit, otherUnit)) {
				gc.fillRect((unit.getX() + 2) * 60, (unit.getY() - 1) * 60, 60, 60);
			}
		}


		//Check bottom squares
		otherUnit = getUnitAt(unit.getX(), unit.getY() + 2);
		if(otherUnit == null) {
			otherUnit = getUnitAt(unit.getX() + 1, unit.getY() + 2);
			if(otherUnit == null || areOpposingUnits(unit, otherUnit)) {
				gc.fillRect((unit.getX() + 1) * 60, (unit.getY() + 2) * 60, 60, 60);
			}
			otherUnit = getUnitAt(unit.getX() - 1, unit.getY() + 2);
			if(otherUnit == null || areOpposingUnits(unit, otherUnit)) {
				gc.fillRect((unit.getX() - 1) * 60, (unit.getY() + 2) * 60, 60, 60);
			}
		}
	}

	
	//Highlights all the valid squares a Bishop can move
	private void colorBishopSquares(Unit unit) {
		Unit otherUnit = null;
		//Check top left diagonal
		for(int i = 1; i <= 7; i++) {
			if(unit.getX() - i >= 0 && unit.getY() - i >= 0) {
				otherUnit = getUnitAt(unit.getX() - i, unit.getY() - i);
//				if(otherUnit != null) {
//					if(!areOpposingUnits(unit, otherUnit)) {
//						break;
//					}
//				}
//				else {
//					gc.fillRect((unit.getX() - i) * 60, (unit.getY() - i) * 60, 60, 60);
//				}			
				if(otherUnit != null) {
					if(!areOpposingUnits(unit, otherUnit)) {
						break;
					}
					else {
						gc.fillRect((unit.getX() - i) * 60, (unit.getY() - i) * 60, 60, 60);
						break;
					}
				}
				else {
					gc.fillRect((unit.getX() - i) * 60, (unit.getY() - i) * 60, 60, 60);
				}
			}
		}

		//Check top right diagonal
		for(int j = 1; j <= 7; j++) {
			if(unit.getX() + j <= 7 && unit.getY() - j >= 0) {
				otherUnit = getUnitAt(unit.getX() + j, unit.getY() - j);
//				if(otherUnit != null) {
//					if(!areOpposingUnits(unit, otherUnit)) {
//						break;
//					}
//				}
//				else {
//					gc.fillRect((unit.getX() + j) * 60, (unit.getY() - j) * 60, 60, 60);
//				}
				if(otherUnit != null) {
					if(!areOpposingUnits(unit, otherUnit)) {
						break;
					}
					else {
						gc.fillRect((unit.getX() + j) * 60, (unit.getY() - j) * 60, 60, 60);
						break;
					}
				}
				else {
					gc.fillRect((unit.getX() + j) * 60, (unit.getY() - j) * 60, 60, 60);
				}
			}
		}	

		//Check bottom left diagonal
		for(int k = 1; k <= 7; k++) {
			if(unit.getX() - k >= 0 && unit.getY() + k <= 7) {
				otherUnit = getUnitAt(unit.getX() - k, unit.getY() + k);
//				if(otherUnit != null) {
//					if(!areOpposingUnits(unit, otherUnit)) {
//						break;
//					}
//				}
//				else {
//					gc.fillRect((unit.getX() - k) * 60, (unit.getY() + k) * 60, 60, 60);
//				}
				if(otherUnit != null) {
					if(!areOpposingUnits(unit, otherUnit)) {
						break;
					}
					else {
						gc.fillRect((unit.getX() - k) * 60, (unit.getY() + k) * 60, 60, 60);
						break;
					}
				}
				else {
					gc.fillRect((unit.getX() - k) * 60, (unit.getY() + k) * 60, 60, 60);
				}
			}
		}	

		//Check bottom right diagonal
		for(int l = 1; l <= 7; l++) {
			if(unit.getX() + l <= 7 && unit.getY() + l <= 7) {
				otherUnit = getUnitAt(unit.getX() + l - l, unit.getY() + l);
//				if(otherUnit != null) {
//					if(!areOpposingUnits(unit, otherUnit)) {
//						break;
//					}
//				}
//				else {
//					gc.fillRect((unit.getX() + l) * 60, (unit.getY() + l) * 60, 60, 60);
//				}
				if(otherUnit != null) {
					if(!areOpposingUnits(unit, otherUnit)) {
						break;
					}
					else {
						gc.fillRect((unit.getX() + l) * 60, (unit.getY() + l) * 60, 60, 60);
						break;
					}
				}
				else {
					gc.fillRect((unit.getX() + l) * 60, (unit.getY() + l) * 60, 60, 60);
				}
			}
		}
	}

	
	//Highlights all the valid squares a Rook can move
	private void colorRookSquares(Unit unit) {
		Unit otherUnit = null;
		
		
		//Check left
		for(int x = unit.getX() - 1; x >= 0; x--) {
			otherUnit = getUnitAt(x, unit.getY());
			if(otherUnit != null) {
				if(!areOpposingUnits(unit, otherUnit)) {
					break;
				}
				else {
					gc.fillRect(x * 60, unit.getY() * 60, 60, 60);
					break;
				}
			}
			else {
				gc.fillRect(x * 60, unit.getY() * 60, 60, 60);
			}
		}
		
		
		//Check right
		for(int x = unit.getX() + 1; x <= 7; x++) {
			otherUnit = getUnitAt(x, unit.getY());
			if(otherUnit != null) {
				if(!areOpposingUnits(unit, otherUnit)) {
					break;
				}
				else {
					gc.fillRect(x * 60, unit.getY() * 60, 60, 60);
					break;
				}
			}
			else {
				gc.fillRect(x * 60, unit.getY() * 60, 60, 60);
			}
		}
		
		
		//Check up
		for(int y = unit.getY() - 1; y >= 0; y--) {
			otherUnit = getUnitAt(unit.getX(), y);
			if(otherUnit != null) {
				if(!areOpposingUnits(unit, otherUnit)) {
					break;
				}
				else {
					gc.fillRect(unit.getX() * 60, y * 60, 60, 60);
					break;
				}
			}
			else {
				gc.fillRect(unit.getX() * 60, y * 60, 60, 60);
			}
		}

		
		//Check down
		for(int y = unit.getY() + 1; y <= 7; y++) {
			otherUnit = getUnitAt(unit.getX(), y);
			if(otherUnit != null) {
				if(!areOpposingUnits(unit, otherUnit)) {
					break;
				}
				else {
					gc.fillRect(unit.getX() * 60, y * 60, 60, 60);
					break;
				}
			}
			else {
				gc.fillRect(unit.getX() * 60, y * 60, 60, 60);
			}
		}
	}


	//Highlights all the valid squares a King can move
	private void colorKingSquares(Unit unit) {
		for(int x = unit.getX() - 1; x <= unit.getX() + 1; x++) {
			for(int y = unit.getY() - 1; y <= unit.getY() + 1; y++) {
				Unit otherUnit = getUnitAt(x, y);
				if(otherUnit == null || areOpposingUnits(unit, otherUnit)) {
					gc.fillRect(x * 60, y  * 60, 60, 60);
				}
			}
		}	
	}

	
	//Highlights all the valid squares a Pawn can move
	private void colorPawnSquares(Unit unit) {
		int sideMultiplier = 1;
		if(unit.getPlayer() == 1) {
			sideMultiplier = -1;
		}
		Unit otherUnit = getUnitAt(unit.getX(), unit.getY() + (1 * sideMultiplier));


		//Checking straight ahead
		if(otherUnit == null) {
			gc.fillRect(unit.getX() * 60, (unit.getY() + (1 * sideMultiplier)) * 60, 60, 60);
		}


		//Checking diagonals
		otherUnit = getUnitAt(unit.getX() + 1, unit.getY() + (1 * sideMultiplier));
		if(otherUnit != null && areOpposingUnits(unit, otherUnit)) {
			gc.fillRect((unit.getX() + 1) * 60, (unit.getY() + (1 * sideMultiplier)) * 60, 60, 60);
		}
		otherUnit = getUnitAt(unit.getX() - 1, unit.getY() + (1 * sideMultiplier));
		if(otherUnit != null && areOpposingUnits(unit, otherUnit)) {
			gc.fillRect((unit.getX() - 1) * 60, (unit.getY() + (1 * sideMultiplier)) * 60, 60, 60);
		}
	}


	//Retrieves the unit at the given x, y coordinate
	public Unit getUnitAt(int x, int y) {
		CheckerboardPane cp = checkerboardPane;
		Unit unitAtCoords = null;
		for (Unit u : cp.getUnits()) {
			if ((u.getX() == x) && (u.getY() == y)) {
				unitAtCoords = u;
			}
		}
		return unitAtCoords;
	}


	//Checks if the two input units are opposing sides. Ture if so and false otherwise
	public boolean areOpposingUnits(Unit unit1, Unit unit2) {
		if(unit1.getPlayer() != unit2.getPlayer()) {
			return true;
		}
		return false;
	}



}
