import java.io.*;
import java.util.*;

public class LingoServer {
	// instance variables
	private String[] lingoWords;
	private int size;
	private Lingo lingoStart;

	// reads the file name and reads in all the words
	// and if the words exceed default capacity 10,
	// the size of the array increases by 2 and hence the process
	// goes on

	public LingoServer(String fileName) {
		try {
			Scanner reader = new Scanner(new File(fileName));

			int capacity = 10; // default capacity
			lingoWords = new String[capacity];

			while (reader.hasNextLine()) {
				// checks whether the size is bigger than capacity
				// or not and then the resizing takes place based on
				// whether it needs to be resized or not
				if (size >= capacity) {
					capacity *= 2;
					String[] newCapacity = new String[capacity];
					for (int x = 0; x < lingoWords.length; x++) {
						newCapacity[x] = lingoWords[x];
					}
					lingoWords = newCapacity;
				}

				lingoWords[size] = reader.nextLine();
				size++;
			}
			reader.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	// hasLingo method
	public boolean hasLingo() {
		return size > 0;
	}

	// getLingo Method
	public Lingo getLingo() {
		if (size == 0) {
			return null;
		}

		Random index = new Random();

		int r = index.nextInt(size);
		// picks a random word from the list of words
		String lingoWord = lingoWords[r];
		lingoWord = lingoWord.toUpperCase();

		// passes in the word to the Lingo class

		lingoStart = new Lingo(lingoWord);

		// deletes the used word

		lingoWords[r] = lingoWords[size - 1];
		lingoWords[size - 1] = null;
		size--;

		return lingoStart;
	}

	// to String method shows Lingo Server info

	public String toString() {
		StringBuilder builder = new StringBuilder();
		System.out.print("Lingo Server Info: ");
		builder.append("Number of Words: " + size);
		builder.append(" Capacity: " + lingoWords.length);
		return builder.toString();
	}
}
