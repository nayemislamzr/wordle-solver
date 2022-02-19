package application;

import java.util.ArrayList;
import java.util.PriorityQueue;

import javafx.util.Pair;

public class Model {
	private String[][] grid;
	private Cell[][] guiGrid;
	private Cell[] guiKeyBoard;
	private int row, col;
	private int maxRow, maxCol;
	private int currWordLength;
	private int maxWordLength;
	private int currTotalPossibleWords;
	private Solver solver;
	private final int maxGuess = 10;

	public Model(int maxRow, int maxCol) {
		this.maxRow = maxRow;
		this.maxCol = maxCol;
		this.maxWordLength = maxCol;
		grid = new String[20][20]; // for offset
		guiGrid = new Cell[20][20];
		guiKeyBoard = new Cell[26];
		currWordLength = 0;
		solver = new Solver(maxWordLength);
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

	public void submit() {
		String currWord = getCurrWord();
		if (Dictionary.has(currWord)) {
			evaluate(currWord);
			solver.update(currWord);
			currWordLength = 0;
			++row;
			col = 0;
		} else {
			System.out.println("Not in dictionary.");
		}
	}

	public ArrayList<String> getPossibleWords() {
		PriorityQueue<Pair<String, Integer>> guesses = solver.getPossibleGuesses();
		currTotalPossibleWords = guesses.size();
		ArrayList<String> possibleWords = new ArrayList<>();
		int words = 0;
		while (!guesses.isEmpty() && words < maxGuess) {
			String currWord = guesses.poll().getKey();
			possibleWords.add(currWord);
			words++;
		}
		return possibleWords;
	}

	private void evaluate(String currWord) {
		System.out.println("Evaluating : " + currWord);
		Cell[] result = Matcher.match(currWord);
		for (int i = 0; i < maxCol; i++) {
			guiGrid[row][i] = result[i];
			guiKeyBoard[currWord.charAt(i) - 'A'] = result[i];
		}

	}

	public String getCurrWord() {
		String currWord = new String();
		for (int i = 0; i < maxCol; i++)
			currWord += grid[row][i];
		return currWord;
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

	public int getTotalPossibleWords() {
		return currTotalPossibleWords;
	}
}
