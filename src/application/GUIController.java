package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GUIController {
	@FXML
	private VBox wordGrid;
	@FXML
	private VBox keyBoard;
	@FXML
	private VBox possibleWords;
	@FXML
	private HBox totalPossibleWords;
	private static VBox staticWordgrid;
	private static VBox staticKeyBoard;
	private static VBox staticPossibleWords;
	private static HBox staticTotalPossibleWords;

	@FXML
	public void initialize() {
		staticWordgrid = wordGrid;
		staticKeyBoard = keyBoard;
		staticPossibleWords = possibleWords;
		staticTotalPossibleWords = totalPossibleWords;
	}

	public static VBox getWordGrid() {
		return staticWordgrid;
	}

	public static VBox getKeyBoard() {
		return staticKeyBoard;
	}

	public static VBox getPossibleWordContainer() {
		return staticPossibleWords;
	}

	public static HBox getTotalPossibleWordContainer() {
		return staticTotalPossibleWords;
	}
	
	@FXML
	public void virtualKeyBoardHandler(ActionEvent event) {
		Button btnObj = (Button) event.getSource();
		String pressedBtn = btnObj.getText();
		Controller.getController().handleEvents(pressedBtn);
		Game.getRoot().requestFocus();
	}
}
