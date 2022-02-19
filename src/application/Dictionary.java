package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import javafx.util.Pair;

class Trie {
	public int childCount;
	public List<Integer> directChild;
	public Trie[] childs;
	public boolean isEnd;

	public Trie() {
		childCount = 0;
		directChild = new ArrayList<Integer>();
		childs = new Trie[26];
		isEnd = false;
	}

	public static void insert(Trie node, String word) {
		int index = 0;
		while (index < word.length()) {
			node.childCount++;
			int childIndex = word.charAt(index) - 'A';
			if (node.childs[childIndex] == null) {
				node.childs[childIndex] = new Trie();
				node.directChild.add(childIndex);
			}
			node = node.childs[childIndex];
			index++;
		}
		node.childCount++;
		node.isEnd = true;
	}

	public static boolean has(Trie node, String word) {
		int index = 0;
		while (index < word.length()) {
			int childIndex = word.charAt(index) - 'A';
			if (node.childs[childIndex] == null)
				return false;
			node = node.childs[childIndex];
			index++;
		}
		return node.isEnd;
	}

	public static String getRandomWord(Trie node, int length) {
		int index = 0;
		String randomWord = new String();
		Random random = new Random();
		while (index < length) {
			int childs = node.directChild.size();
			int letterNeeded = length - index - 2;
			ArrayList<Integer> potentialChilds = new ArrayList<Integer>();
			for (int i = 0; i < childs; i++) {
				int childIndex = node.directChild.get(i);
				if (node.childs[childIndex].childCount >= letterNeeded)
					potentialChilds.add(childIndex);
			}
			if (potentialChilds.size() == 0)
				return randomWord;
			int selectedChild = potentialChilds.get(random.nextInt(potentialChilds.size()));
			randomWord += (char) (selectedChild + 'A');
			node = node.childs[selectedChild];
			index++;
		}
		return randomWord;
	}

	public static ArrayList<String> search(Trie node, Set<Integer> blackLetters, ArrayList<Set<Integer>> grayLetters,
			ArrayList<Integer> whiteLetters, int wordLen) {
		Stack<Pair<Trie, String>> searchNode = new Stack<Pair<Trie, String>>();
		ArrayList<String> possibleWords = new ArrayList<String>();
		searchNode.add(new Pair<Trie, String>(node, new String()));
		while (searchNode.isEmpty() == false) {
			Trie currNode = searchNode.peek().getKey();
			String wordSoFar = searchNode.peek().getValue();
			searchNode.pop();
			if(currNode == null) {
				continue;
			}
			if (currNode.isEnd && wordSoFar.length() == wordLen) {
				possibleWords.add(wordSoFar);
				continue;
			}
			if (whiteLetters.get(wordSoFar.length()) != -1) {
				int rightLetter = whiteLetters.get(wordSoFar.length());
				searchNode.add(
						new Pair<Trie, String>(currNode.childs[rightLetter], wordSoFar + (char) (rightLetter + 'A')));
				continue;
			}
			for (int child : currNode.directChild) {
				if (blackLetters.contains(child) == false
						&& grayLetters.get(wordSoFar.length()).contains(child) == false) {
					searchNode.add(new Pair<Trie, String>(currNode.childs[child], wordSoFar + (char) (child + 'A')));
				}
			}
		}
		return possibleWords;
	}
}



public class Dictionary {
	private final static String filePath = "src/application/texts/dictionary.txt";
	private static int[] score = new int[26];
	private static Trie root;

	private static Trie getRoot() {
		if (root == null)
			root = new Trie();
		return root;
	}

	private static void add(String word) {

		for (int i = 0; i < word.length(); i++) {
			score[word.charAt(i) - 'A']++;
		}

		Trie node = getRoot();
		Trie.insert(node, word);
	}

	public static int getScore(char c) {
		return score[c - 'A'];
	}

	public static int getScore(String word) {
		int score = 0;
		for (int i = 0; i < word.length(); i++) {
			score += getScore(word.charAt(i));
		}
		return score;
	}

	public static boolean has(String word) {
		return Trie.has(root, word);
	}

	public static void load() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(Dictionary.filePath));
			String line = reader.readLine();
			while (line != null) {
				Dictionary.add(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getRandomWord(int wordLength) {
		Trie node = getRoot();
		return (Trie.getRandomWord(node, wordLength));
	}

	public static ArrayList<String> search(Set<Integer> blackLetters, ArrayList<Set<Integer>> grayLetters,
			ArrayList<Integer> whiteLetters, int wordLength) {
		return Trie.search(getRoot(), blackLetters, grayLetters, whiteLetters, wordLength);
	}

//	public static void main(String[] args) {
//		Dictionary.load();
//		int[] result = new int[100];
//		
//		int tests = 10000;
//
//		for(int i = 0 ; i < tests ; i++) {
//			Solver solver = new Solver(5);
//			String guessedWord = Dictionary.getRandomWord(5);
//			Matcher.setWord(guessedWord);
//			int tries = 1;
//			String word = new String();
////			System.out.println(guessedWord);
//			solver.update("CRANE");
//			while(!(word = solver.guessMaxScore()).equals(guessedWord)) {
//				++tries;
////				System.out.println(tries + " : " + word);
//				solver.update(word);
//			}
//			++result[tries];
//		}
//
//		int success = 0;
//		
//		for(int i = 1 ; i <= 6 ; i++) {
//			success+= result[i];
//		}
//		
//		System.out.println(((double)success/(double)tests)*100);
//		
////		int tries = 0;
////		Solver solver = new Solver(5);
////		while (tries < 7) {
////			Scanner in = new Scanner(System.in);
////			String word = in.nextLine();
////			Cell[] feedBack = new Cell[5];
////			for (int i = 0; i < 5; i++) {
////				int x = in.nextInt();
////				if (x == 0)
////					feedBack[i] = Cell.NOT_MATCHED;
////				else if (x == 1)
////					feedBack[i] = Cell.MATCHED_NOT_IN_LOCATION;
////				else if (x == 2)
////					feedBack[i] = Cell.MATCHED_IN_LOCATION;
////			}
////			solver.update(word, feedBack);
////			word = solver.guessMaxScore();
////			System.out.println(word);
////			tries++;
////		}
//
//	}
}
