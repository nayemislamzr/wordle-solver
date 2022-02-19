package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class GUIController {
	@FXML
	private VBox wordGrid;
	@FXML
	private	VBox keyBoard;
	private static VBox staticWordgrid;
	private static VBox staticKeyBoard;
	@FXML 
	public void initialize() {
		staticWordgrid = wordGrid;
		staticKeyBoard = keyBoard;
	}
	public static VBox getWordGrid() {
		return staticWordgrid;
	}
	public static VBox getKeyBoard() {
		return staticKeyBoard;
	}
	@FXML
	public void virtualKeyBoardHandler(ActionEvent event) {
		Button btnObj = (Button)event.getSource();
		String pressedBtn = btnObj.getText();
		Controller.getController().handleEvents(pressedBtn);
		Game.getRoot().requestFocus();
	}
}
