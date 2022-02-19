package application;

public class Controller {
	private static Controller controller;
	private final int maxRow = 6;
	private final int maxColumn = 5;
	private Model model;
	private View view;
	private Solver solver;
	
	private Controller() {
		model = new Model(maxRow, maxColumn);
		view = new View(maxRow, maxColumn);
		solver = new Solver(maxColumn);
	}
	
	public static Controller getController() {
		if(controller == null)
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
			if (model.isComplete()) {
				System.out.println("Complete");
				String currWord = model.getCurrWord();
				if(Dictionary.has(currWord)) {
					solver.update(currWord);
					model.submit();
					System.out.println("Guess : " + solver.guessMaxScore());	
				}
				else {
					System.out.println("Word not in the dictionary.");
				}
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
