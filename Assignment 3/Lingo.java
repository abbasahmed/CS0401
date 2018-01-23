
public class Lingo {
	// Instance variables
	private char[] lingoWord;
	private char first;
	private String gameWord;
	private String guessedWord;
	private char[] wordCheck;

	// Constructor takes in the word and converts it
	// into an array of characters.

	public Lingo(String word) {
		gameWord = word;
		lingoWord = gameWord.toCharArray();
	}

	// first method returns the first character of the
	// lingo word.

	public char first() {
		first = lingoWord[0];
		return first;
	}

	// toString method to show the word

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Word: " + gameWord);
		return builder.toString();
	}

	// guessWord method compares the user's guess
	// to the Lingo word by comparing each character
	// in the Lingo word with the characters in user's guess

	public int[] guessWord(String guess) {
		int[] truthVal = new int[5];
		String exChar = "0";
		int guessLength = guess.length();
		if (guessLength > 5) {
			// executes only if guess is longer than the lingo word
			// and eliminates the extra characters
			guess = guess.substring(0, Math.min(guessLength, 5));
		} else if (guessLength < 5) {
			// executes only if guess is shorter than the lingo word
			// and makes the length 5 by adding 0's at the end
			for (int e = 0; e < (5 - guessLength); e++)
				guess += exChar;
		}
		guessedWord = guess;
		wordCheck = guessedWord.toCharArray();
		// this loop checks if the truth value of each character is
		// 2,1 or 0
		for (int i = 0; i < lingoWord.length; i++) {
			if (lingoWord[i] == wordCheck[i]) {
				// this is true when the characters are in the right
				// place
				truthVal[i] = 2;
			} else {
				for (int j = 0; j < wordCheck.length; j++) {
					// this for loop checks the other characters
					if (lingoWord[i] == wordCheck[j]) {
						if (truthVal[j] == 0) {
							truthVal[j] = 1;
							// this is to check for duplicate letters
							break;
						}
					}
				}
			}
		}

		return truthVal;
	}
}
