
// CS 0401 Fall 2016
// Lab 11
// You must modify this file so that it works as described in the lab handout.
import java.util.*;
import java.io.*;

public class lab11 {
	public static void main(String [] args)
	{
		Scanner inScan, fScan = null;
		int [] A = new int[5];
		inScan = new Scanner(System.in);
		while(true){
			try{
				System.out.println("Please enter the file to read from: ");
				String fName = inScan.nextLine();
				fScan = new Scanner(new File(fName));
				break;
			}catch(IOException e1){
				System.out.println("Your file is invalid -- please re-enter");
				
			}
		}
		String nextItem;
		int nextInt = 0;
		int i = 0;
		while (fScan.hasNextLine())
		{
			nextItem = fScan.nextLine();
			try{
			nextInt = Integer.parseInt(nextItem);
			
			A[i] = nextInt;
			i++;
			}
			catch (NumberFormatException e2)
			{
				System.out.println(nextItem+" is not an integer -- ignored");
				
			} catch (ArrayIndexOutOfBoundsException e3){
				int [] B = new int[A.length*2];
				for(int x = 0; x < A.length; x++){
					B[x] = A[x];
				}
				System.out.println("Resizing array from "+A.length+" to "+B.length);
				A = B;
				A[i] = nextInt;
				i++;
			}
		}

		System.out.println("Here are your " + i + " items:");
		
		for (int j = 0; j < i; j++)
		{
			System.out.println(A[j] + " ");
		}
		inScan.close();
	}
}