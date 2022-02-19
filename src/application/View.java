package application;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Pair;

public class View {
	private final int totalKeys = 26;
	private VBox fxGrid;
	private VBox fxKeyBoard;
	private int maxRow, maxCol;
	private final ArrayList<String> keyBoard;
	private final BackgroundFill green = new BackgroundFill(Color.rgb(83, 141, 78), null, null);
	private final BackgroundFill yellow = new BackgroundFill(Color.rgb(181, 159, 59), null, null);
	private final BackgroundFill gray = new BackgroundFill(Color.rgb(58, 58, 60), null, null);

	public View(int maxRow, int maxCol) {
		this.maxRow = maxRow;
		this.maxCol = maxCol;
		keyBoard = new ArrayList<String>();
		keyBoard.add("QWERTYUIOP");
		keyBoard.add("ASDFGHJKL");
		keyBoard.add("ZXCVBNM");
		fxGrid = GUIController.getWordGrid();
		fxKeyBoard = GUIController.getKeyBoard();
	}

	/*
	 * For Debug Purpose
	 */
	public void printGrid(String[][] grid, Cell[][] guiGrid) {
		for (int i = 0; i < maxRow; i++) {
			for (int j = 0; j < maxCol; j++) {
				if (guiGrid[i][j] != Cell.BLANK)
					System.out.print(grid[i][j] + " ");
				else
					System.out.print("X ");
			}
			System.out.print("\n");
		}
		System.out.println("\n");
	}

	/*
	 * For GUI purpose
	 */

	public void showGrid(String[][] grid, Cell[][] guiGrid) {
		if (guiGrid == null) {
			System.out.println("No grid Found.");
			return;
		}

		for (int i = 0; i < maxRow; i++) {
			HBox row = (HBox) fxGrid.getChildren().get(i);
			for (int j = 0; j < maxCol; j++) {
				Label cell = (Label) row.getChildren().get(j);
				if (guiGrid[i][j] != Cell.BLANK) {
					if (guiGrid[i][j] == Cell.MATCHED_IN_LOCATION) {
						cell.setBackground(new Background(green));
					} else if (guiGrid[i][j] == Cell.MATCHED_NOT_IN_LOCATION) {
						cell.setBackground(new Background(yellow));
					} else if (guiGrid[i][j] == Cell.NOT_MATCHED) {
						cell.setBackground(new Background(gray));
					}
					cell.setText(grid[i][j]);
				} else {
					cell.setText("");
				}
			}
		}
	}

	private Pair<Integer, Integer> getKeyRow(char c) {
		for (int i = 0; i < keyBoard.size(); i++) {
			for (int j = 0; j < keyBoard.get(i).length(); j++) {
				if (keyBoard.get(i).charAt(j) == c)
					return new Pair<Integer, Integer>(i, j);
			}
		}
		return new Pair<Integer, Integer>(-1, -1);
	}

	public void showKeyBoard(Cell[] guiKeyBoard) {
		for (int i = 0; i < totalKeys; i++) {
			Pair<Integer, Integer> position = getKeyRow((char) (i + 'A'));
			if (position.getKey() == -1 || position.getValue() == -1) {
				System.out.println("GUIKeyBoard Index out of bounds.");
				return;
			}
			HBox row = (HBox) fxKeyBoard.getChildren().get(position.getKey());
			Button btn = (Button) row.getChildren().get(position.getValue());
			if (guiKeyBoard[i] == Cell.MATCHED_IN_LOCATION) {
				btn.setBackground(new Background(green));
			} else if (guiKeyBoard[i] == Cell.MATCHED_NOT_IN_LOCATION) {
				btn.setBackground(new Background(yellow));
			} else if (guiKeyBoard[i] == Cell.NOT_MATCHED) {
				btn.setBackground(new Background(gray));
			}
		}
	}
}
