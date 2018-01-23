import java.util.*;

/**CS0401 Intermediate programming with Java
 * 
 * CS401 Assignment 3
 * 
 * Name: Abbas Z Ahmed
 * Peoplesoft No. 4165368
 * Pitt Email ID: aza16@pitt.edu
 * 
 * Professor Ramirez 
 * 
 * Classes: Monday and Thursday at 1 PM - 2:15 PM
 * Lab: Thursday 10 AM-11:50AM
 * 
 * The purpose of this project is to experiment with classes to create a simple game of guessing a five letter word.
 * This project deals with the concept of composition of classes.
 * 
 */

/**
 * @author abbas
 *
 */

public class Assig3 {
	// we have to pass in the name of the text file in the command line
	public static void main(String[] args) {
		if (args.length < 1) {
			return;
		}
		// passes the file name in the LingoServer Class
		LingoServer LS = new LingoServer(args[0]);
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t##       #### ##    ##  ######    #######  ");   // just to show enthusiasm for the project
		System.out.println("\t\t\t\t\t\t\t##        ##  ###   ## ##    ##  ##     ##");
		System.out.println("\t\t\t\t\t\t\t##        ##  ####  ## ##        ##     ##");
		System.out.println("\t\t\t\t\t\t\t##        ##  ## ## ## ##   #### ##     ##");
		System.out.println("\t\t\t\t\t\t\t##        ##  ##  #### ##    ##  ##     ##");
		System.out.println("\t\t\t\t\t\t\t##        ##  ##   ### ##    ##  ##     ##");
		System.out.println("\t\t\t\t\t\t\t######## #### ##    ##  ######    #######");
		System.out.println();
		System.out.println("Welcome to Lingo!");
		System.out.print("Would you like to play? (yes/no) > ");     // Asks the user if he/she wants to play
		Scanner input = new Scanner(System.in);
		String userPlays = input.next();
		while (userPlays.equalsIgnoreCase("yes")) { // if yes, then this while loop executes
			Lingo L = LS.getLingo(); 
			boolean matched = false;
			System.out.println("You have 5 tries to guess a 5-letter word");
			System.out.println("\t Letters are in the word in the correct location are in CAPS");
			System.out.println("\t Letters that are in the word in the INcorrect location are in lower case");
			System.out.println("\t Letters that do not appear in the word are shown as hyphens\n");
			System.out.println("Your word begins with " + L.first()); // prints out the first character of the word
			// gives the user 5 tries
			for (int tries = 1; tries <= 5; tries++) {
				System.out.print("Enter guess# " + tries + " > "); // shows the no. of try
				String guess = input.next();
				int[] truthValues = L.guessWord(guess.toUpperCase()); // calls the guessWord method to get the truth values
				char[] result = guess.toCharArray(); // converts the guess to array of its characters
				showResult(truthValues, result); // calls the showResult method
				if (isCorrect(truthValues)) {   // if the user wins 
					System.out.println("You got it!  Congratulations!");
					matched = true;
					break;
				}
			}
			if (!matched) {  // if the user loses 
				System.out.println("Sorry, but you missed it!");
				System.out.println("The answer is " + L.toString());
			}
			System.out.print("Would you like to try another word? (yes/no) > ");  // to ask the user if he/she wants to try again
			userPlays = input.next();
		}

		System.out.println("Thanks for playing! Try again soon!");
		input.close();
	}
	
	// isCorrect method checks if the all the truth values are 2 and if the user guessed the word correctly or not
	
	private static boolean isCorrect(int[] truthVal) {
		for (int i = 0; i < truthVal.length; i++) {
			if (truthVal[i] != 2) {
				return false;
			}
		}

		return true;
	}
	
	// showResult method checks whether if the truth values of the guess's characters are 2,1 or 0 and tells user about the guess he/she entered.
	// Caps letter for correct place, small case letter for the character to be somewhere else, and 0 for the  
	// character to not exist in the word
	
	public static void showResult(int[] A, char[] B) {
		System.out.println("Here are your results: ");
		System.out.println("0 1 2 3 4");
		System.out.println("---------");
		for (int i = 0; i < A.length; i++) {
			if (A[i] == 2) {
				System.out.print(Character.toUpperCase(B[i]) + " ");  // if the letter is in the right place
			} else if (A[i] == 1) {
				System.out.print(Character.toLowerCase(B[i]) + " ");  // if the letter is in the wrong place
			} else if (A[i] == 0) {
				System.out.print("- ");                               // if the letter is not in the word
			}
		}
		System.out.println();
	}

}