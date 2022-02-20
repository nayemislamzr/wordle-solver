package application;

import java.util.ArrayList;

import javafx.util.Pair;

public class Controller {
	private static Controller controller;
	ArrayList<Pair<String,Integer>> possibleWords;
	int totalPossibleWords;
	private final int maxRow = 6;
	private final int maxColumn = 5;
	private Model model;
	private View view;

	private Controller() {
		model = new Model(maxRow, maxColumn);
		view = new View(maxRow, maxColumn);
		possibleWords = model.getPossibleWords();
		totalPossibleWords = model.getTotalPossibleWords();
		view.showTotalPossibleWords(totalPossibleWords);
		view.showPossibleWords(possibleWords);
	}

	public static Controller getController() {
		if (controller == null)
			controller = new Controller();
		return controller;
	}

	private boolean isLetter(String key) {
		if (key.length() > 1 || key == null)
			return false;
		return (Character.isAlphabetic(key.charAt(0)));
	}

	public void handleEvents(String key) {
		if (isLetter(key)) {
			if (model.canInsert()) {
				model.insert(key.toString());
			}
		} else if (key.equals("ENTER")) {
			if (model.isComplete() && Dictionary.has(model.getCurrWord())) {
				model.submit();
				possibleWords = model.getPossibleWords();
				totalPossibleWords = model.getTotalPossibleWords();
				view.showPossibleWords(possibleWords);
				view.showTotalPossibleWords(totalPossibleWords);
			}
		} else if (key.equals("DELETE") || key.equals("BACK_SPACE")) {
			if (model.canDelete()) {
				model.delete();
			}
		}
		view.showGrid(model.getGrid(), model.getGUIGrid());
		view.showKeyBoard(model.getGUIKeyboard());
	}
}
