import java.util.Scanner;

/**CS0401 Intermediate programming with Java
 * 
 * CS401 Assignment 2
 * 
 * Name: Abbas Z Ahmed
 * Peoplesoft No. 4165368
 * Pitt Email ID: aza16@pitt.edu
 * 
 * Professor Ramirez 
 * 
 * Classes: Monday and Thursday at 1 PM - 2:15 PM
 * 
 * Lab: Thursday 10 AM-11:50AM
 * 
 * TA: Zinan Zhuang
 * 
 * The purpose of this project is to learn how to create classes and learn how to use text files in
 * the program itself. While doing this we learn how to build a simple cipher to encrypt data of a 
 * text file and also a way to decrypt it.
 * 
 */


/**
 * @author abbas
 *
 */

public class MainClass {

	private static Crypto crypto;                     //Calls the Crypto Class

	public static void main(String[] args) {
		System.out.print("Please enter a password: ");    //Asks the User to input password
		Scanner input = new Scanner(System.in);           //Scanner Declared
		String password = input.next();                   //Takes in the password
		crypto = new Crypto(password);                    //Passes the password in the Crypto Class
		System.out.println("Please select one of the following: ");              //Asks the User to choose
		System.out.println("1) Encrypt a File    [ Press 1 to select ]"); 
		System.out.println("2) Decrypt a File    [ Press 2 to select ]"); 
		int cryptOpt = input.nextInt();
		System.out.print("Input File name: ");              //Asks the user to enter the file name
		String fName = input.next();
		if (cryptOpt == 1) {                                //Executes if user chooses option 1
			encryptFile(fName);                                     //Method to encrypt the file
		} else if (cryptOpt == 2) {                         //Executes if user chooses option 2
			decryptFile(fName);                                     //Method to decrypt the file
		} 
		input.close();
	}

	public static void encryptFile(String fName) {          //Method to encrypt the file
		if (crypto.encryptFile(fName)) {                       //Calls the encryptFile method from Crypto Class   
			System.out.println("File " + fName + " successfully encrypted");
		} else {
			System.out.println("Problem with file!");
		}
		System.out.println("Encryption Agent:\n" + crypto.toString());  //Prints out the Hashcode, Password and Key for the User
	}
	
	public static void decryptFile(String fName) {         //Method to decrypt the file
		if (crypto.decryptFile(fName + ".cyp")){                //Calls the decryptFile method from Crypto Class
			System.out.println("File " + fName+ ".cyp" + " successfully decrypted");
		} else {
			System.out.println("File is not encrypted!");
		}
		System.out.println("Decryption Agent:\n" + crypto.toString());  //Prints out the Hashcode, Password and Key for the User
	}
}

