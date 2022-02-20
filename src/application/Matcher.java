package application;

public class Matcher {
	private static String selectedWord = new String();
	public static void setWord(String word) {
		selectedWord = word;
	}
	public static Cell[] match(String userWord) {
		Cell[] result = new Cell[userWord.length()];
		int letterCnt[] = new int[26];
		for(int i = 0 ; i < selectedWord.length() ; i++) {
			++letterCnt[selectedWord.charAt(i)-'A'];
		}
		for(int i = 0 ; i < userWord.length() ; i++) {
			if(selectedWord.charAt(i) == userWord.charAt(i)) {
				result[i] = Cell.MATCHED_IN_LOCATION;
				--letterCnt[userWord.charAt(i) - 'A'];
			}
			else 
				result[i] = Cell.NOT_MATCHED;
		}
		for(int i = 0 ; i < userWord.length() ; i++) {
			if(selectedWord.charAt(i) != userWord.charAt(i)) {
				if(letterCnt[userWord.charAt(i) - 'A'] > 0) {
					result[i] = Cell.MATCHED_NOT_IN_LOCATION;
					--letterCnt[userWord.charAt(i) - 'A'];
				}
			}
		}
		return result;
	}
}
