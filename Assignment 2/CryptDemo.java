
public class CryptDemo
{
	public static void main(String [] args)
	{
		// Constructor for Crypto class takes a String argument.  This will be the
		// password for encryptions done with this object.  The password will be used
		// to generate to other data items for the object:  
		//		1) the hash (using the String hashCode() method) 
		//		2) the key (the "shift" amount for the actual encryption)
		// See the Assignment 2 sheet for more information on these items.
		Crypto C1 = new Crypto("Inconceivable");
		Crypto C2 = new Crypto("JogHimTooHard");
		String F1 = new String("test1.txt");
		String F2 = new String("test2.txt");
		String F3 = new String("test.cyp");
		String F4 = new String("test-dne.txt");
	
		tryEncrypt(F1, C1);
		tryEncrypt(F2, C2); 
		tryEncrypt(F3, C1);
		tryEncrypt(F4, C1);
	}
	
	public static void tryEncrypt(String file, Crypto agent)
	{
		// Your Crypto class must have a public method "encryptFile()".  This method
		// takes a file name (String) as an argument and makes an encrypted version
		// of that file with extension ".cyp".  The method should return a boolean
		// result -- true if everything worked or false if there was a problem (ex:
		// the original file did not exist).  See the Assignment sheet for the details
		// of how the encryption should be done and the format of the resulting file.
		if (agent.encryptFile(file))
		{
			System.out.println("File " + file + " encrypted successfully");
			System.out.println("Encryption agent:\n" + agent.toString());
		}
		else
		{
			System.out.println("File " + file + " NOT encrypted");
			System.out.println("Encryption agent:\n" + agent.toString());
		}
		System.out.println();
	}
}