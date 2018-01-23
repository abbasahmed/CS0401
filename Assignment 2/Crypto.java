import java.util.*;
import java.io.*;

public class Crypto {

	private int keyHash; // Hashcode
	private int key; // Key
	private String password; // Password

	public Crypto(String passwordEntered) {
		password = passwordEntered;
		keyHash = password.hashCode();
		key = Math.abs(keyHash) % 256; // Formulates a key which is used to
										// shift characters
	}

	public char shift(char inChar) {
		char outChar = (char) ((inChar + key) % 256); // Shifting for Encryption
		return outChar;
	}

	public char deShift(char inChar) {
		char outChar = (char) ((inChar - key) % 256); // Shifting for Decryption
		return outChar;
	}

	public boolean encryptFile(String fileName) { // Method for Encryption
		int sLength = fileName.length();
		String extension = fileName.substring(sLength - 4, sLength);

		if (extension.equalsIgnoreCase(".cyp")) { // If the file is already
													// encrypted
			System.out.println("File is already encrypted!"); // it won't
																// execute.
			return false;
		}

		File inFile = new File(fileName);
		File outFile = new File(fileName + ".cyp"); // Creates the encrypted
													// file with
													// an extension of .cyp
		Scanner inScanner;
		PrintWriter outWriter;

		try {
			inScanner = new Scanner(inFile); // Checks whether if the file
												// exists
			outWriter = new PrintWriter(outFile);
		} catch (FileNotFoundException e) {
			System.out.println("Problem with file -- cannot encrypt");
			return false;
		}

		outWriter.println(keyHash); // Prints Hashcode of the password to the
									// Encrypted File
									// in the first line.
		while (inScanner.hasNext()) {
			String inString = inScanner.nextLine();

			for (int i = 0; i < inString.length(); i++) { // Encrypts the data
				char charAt = inString.charAt(i);
				char out = shift(charAt); // Calls the shift Method
				outWriter.print(out);
			}
			outWriter.print(shift('\n'));
		}

		inScanner.close();
		outWriter.close();
		return true;
	}

	public boolean decryptFile(String fileName) { // Method for Decryption
		boolean result = true;

		int sLength = fileName.length();
		String extension = fileName.substring(sLength - 4, sLength);

		if (!extension.equalsIgnoreCase(".cyp")) { // Checks whether the file is
			System.out.println("File is not encrypted!"); // encrypted or not.
			return false;
		}

		File inFile = new File(fileName);

		String copyFileName = fileName.substring(0, sLength - 4) + ".copy";
		File outFile = new File(copyFileName);

		Scanner inScanner;
		PrintWriter outWriter;
		int readHash;

		try {
			inScanner = new Scanner(inFile);
			outWriter = new PrintWriter(outFile);

			readHash = inScanner.nextInt(); // Reads the Hashcode from the first
											// line.
			if (readHash != keyHash) { // Checks if the Password is incorrect.
				System.out.println("Incorrect password!");
				result = false;
			}
		} catch (FileNotFoundException e) { // Executes if the file does not
											// exist.
			System.out.println("Problem with file -- source file does not exist");
			return false;
		} catch (InputMismatchException e) {
			System.out.println("Problem with hash stored in file");
			return false;
		}

		while (result && inScanner.hasNext()) {
			String inString = inScanner.nextLine();
			for (int i = 0; i < inString.length(); i++) { // Decrypts the data
				char charAt = inString.charAt(i);
				char out = deShift(charAt); // Calls the deShift Method
				outWriter.print(out);
			}
		}

		inScanner.close();
		outWriter.close();
		return result;
	}

	public String toString() { // toString Method
		StringBuilder builder = new StringBuilder();
		builder.append("Pass: " + password + "\n");
		builder.append("Hash: " + keyHash + "\n");
		builder.append("Key: " + key);
		return builder.toString();
	}

}