// CS 0401 Fall 2016
// Assignment 2 Test Program
// Your Crypto class should work with this program to decrypt the files (or not)
// as described in the Assignment 2 description.  To see how this program should 
// work, see file CryptoTest.html

public class DeCryptDemo
{
	public static void main(String [] args)
	{
		Crypto C1 = new Crypto("Inconceivable");
		Crypto C2 = new Crypto("JogHimTooHard");
	
		String F1 = new String("test1.txt.cyp");
		String F2 = new String("test2.txt.cyp");
		String F3 = new String("test1.txt");
	
		tryDecrypt(F2, C1);
		tryDecrypt(F1, C2);
		tryDecrypt(F3, C1);
		tryDecrypt(F1, C1);
		tryDecrypt(F2, C2);
	}
	
	public static void tryDecrypt(String file, Crypto agent)
	{
		// Your Crypto class must have a public method "decryptFile()".  This method
		// takes a file name (String) as an argument and makes an decrypted version
		// of that file, removing the ".cyp" in the name and replacing it with the
		// extension ".copy".  The method should return a boolean result -- true if 
		// everything worked or false if there was a problem (ex: the password did
		// not match, the file did not have the correct extension).  See the Assignment
		// sheet for the details of how the decryption should be done.
		if (agent.decryptFile(file))
		{
			System.out.println("File " + file + " decrypted successfully");
			System.out.println("Decryption agent\n" + agent.toString());
		}
		else
		{
			System.out.println("File " + file + " could not be decrypted");
			System.out.println("Decryption agent\n" + agent.toString());
		}
		System.out.println();
	}
}
		
	