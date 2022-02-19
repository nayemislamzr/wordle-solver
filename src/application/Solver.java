package application;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import javafx.util.Pair;

class ScoreComparator implements Comparator<Pair<String, Integer>> {

	@Override
	public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
		return (o2.getValue() - o1.getValue());
	}

}

class Solver {
	private Set<Integer> blackLetters;
	public ArrayList<Set<Integer>> grayLetters;
	public ArrayList<Integer> whiteLetters;
	private int wordLength;

	public Solver(int wordLen) {
		wordLength = wordLen;
		blackLetters = new HashSet<Integer>();
		grayLetters = new ArrayList<Set<Integer>>();
		whiteLetters = new ArrayList<Integer>();
		for (int i = 0; i <= wordLength; i++) {
			grayLetters.add(new HashSet<Integer>());
			whiteLetters.add(-1);
		}
	}

	public PriorityQueue<Pair<String, Integer>> getPossibleGuesses() {
		ArrayList<String> possibleGuesses = Dictionary.search(blackLetters, grayLetters, whiteLetters, wordLength);
		System.out.println(possibleGuesses.size());
		PriorityQueue<Pair<String, Integer>> wordsWithScore = new PriorityQueue<Pair<String, Integer>>(
				new ScoreComparator());
		for (String word : possibleGuesses) {
			wordsWithScore.add(new Pair<String, Integer>(word, Dictionary.getScore(word)));
		}
		return wordsWithScore;
	}

	public String guessMaxScore() {
		ArrayList<String> possibleGuesses = Dictionary.search(blackLetters, grayLetters, whiteLetters, wordLength);
		String maxPossibleWord = new String();
		int maxScore = Integer.MIN_VALUE;
		for (String word : possibleGuesses) {
			int currScore = Dictionary.getScore(word);
			if (currScore > maxScore) {
				maxScore = currScore;
				maxPossibleWord = word;
			}
		}
		return maxPossibleWord;
	}

	public void update(String guessedWord) {
		Cell[] feedBack = Matcher.match(guessedWord);
		update(guessedWord, feedBack);
	}

	public void update(String guessedWord, Cell[] feedBack) {
		for (int i = 0; i < feedBack.length; i++) {
			if (feedBack[i] == Cell.NOT_MATCHED)
				blackLetters.add(guessedWord.charAt(i) - 'A');
			if (feedBack[i] == Cell.MATCHED_NOT_IN_LOCATION)
				grayLetters.get(i).add(guessedWord.charAt(i) - 'A');
			else if (feedBack[i] == Cell.MATCHED_IN_LOCATION)
				whiteLetters.set(i, guessedWord.charAt(i) - 'A');
		}
	}
}
