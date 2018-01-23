import java.util.*;
public class Lab3 {

	public static void main(String[] args) {

	    Scanner inScan = new Scanner(System.in);

	    int base;
	    int X;
	    int response;

	  do{   
	    System.out.println("Please enter a base > 1");
	    base = inScan.nextInt();
	    System.out.println("Please enter a number, X>0.");
	    X = inScan.nextInt();
	    if (X > 0 && base > 1){
	        System.out.println("Logarithm base " +base+ " of " +X+" is ");
	         for (int n=0 ; X>base ;n++){

	            X=(X/base);
	            System.out.println(n);
	        }
	       
	    } else if(base<0){
	    	System.exit(base);
	    } 
	        System.out.println("Would you like to go again? Press 1, press 0 to quit");
	        response = inScan.nextInt(); 
	  } while (response == 1);

	  
	  inScan.close();
	 }
	
	}
