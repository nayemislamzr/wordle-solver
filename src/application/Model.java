package application;

public class Model {
	private String[][] grid;
	private Cell[][] guiGrid;
	private Cell[] guiKeyBoard;
	private int row, col;
	private int maxRow, maxCol;
	private int currWordLength;
	private int maxWordLength;

	public Model(int maxRow, int maxCol) {
		this.maxRow = maxRow;
		this.maxCol = maxCol;
		this.maxWordLength = maxCol;
		grid = new String[20][20]; // for offset
		guiGrid = new Cell[20][20];
		guiKeyBoard = new Cell[26];
		currWordLength = 0;
		row = 0;
		col = 0;
	}

	public boolean canInsert() {
		return (currWordLength < maxWordLength && row < maxRow);
	}

	public void insert(String letter) {
		++currWordLength;
		guiGrid[row][col] = Cell.ALLOCATED;
		grid[row][col] = letter;
		++col;
	}

	public boolean canDelete() {
		return (currWordLength > 0);
	}

	public void delete() {
		--currWordLength;
		--col;
		guiGrid[row][col] = Cell.BLANK;
	}

	public boolean isComplete() {
		return (currWordLength == maxWordLength);
	}

	public String getCurrWord() {
		String currWord = new String();
		for (int i = 0; i < maxCol; i++)
			currWord += grid[row][i];
		return currWord;
	}
	
	public void submit() {
		String currWord = getCurrWord();
		if (Dictionary.has(currWord)) {
			evaluate(currWord);
			currWordLength = 0;
			++row;
			col = 0;
		} else {
			System.out.println("Not in dictionary.");
		}
	}

	private void evaluate(String currWord) {
		System.out.println("Evaluating : " + currWord);
		Cell[] result = Matcher.match(currWord);
		for (int i = 0; i < maxCol; i++) {
			guiGrid[row][i] = result[i];
			guiKeyBoard[currWord.charAt(i) - 'A'] = result[i]; 
		}
			
	}

	public String[][] getGrid() {
		return grid;
	}

	public Cell[][] getGUIGrid() {
		return guiGrid;
	}
	
	public Cell[] getGUIKeyboard() {
		return guiKeyBoard;
	}
}
