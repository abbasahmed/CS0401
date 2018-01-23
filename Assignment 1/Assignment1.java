import java.util.Scanner;

/**CS0401 Intermediate programming with Java
 * 
 * CS401 Assignment 1
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
 * The purpose of this project is to experiment with loops to create a interactive & user friendly shopping cart
 * that will simulate transactions between you and some of your customers.
 * 
 */


/**
 * @author abbas
 *
 */
public class Assignment1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("\t+-+-+-+-+-+-+-+-+-+-+-+-+ +-+-+-+-+-+-+-+-+-+ +-+-+-+-+-+-+-+-+-+-+");
		System.out.println("\t|D|u|m|b|l|e|d|o|r|e|'|s| |D|e|l|i|c|i|o|u|s| |D|e|l|i|c|a|c|i|e|s|");  //just to show enthusiasm for this project
		System.out.println("\t+-+-+-+-+-+-+-+-+-+-+-+-+ +-+-+-+-+-+-+-+-+-+ +-+-+-+-+-+-+-+-+-+-+");
		System.out.println("\t\tWelcome to Dumbledore's Delicious Delicacies");

		Scanner input = new Scanner(System.in);

		System.out.print("Is there a customer in line? (1 = yes, 2 = no) > "); 
		int customer = input.nextInt();                                                        //Taking user's input value and checking if someone's in the line
		while(customer==1){
			boolean hasDiscount = false;                                                          
			int numberofCauldronCakes=0;
			int caulCakes =0;
			int numberOfChocolateFrogs=0;
			int chocFrog =0;
			int smallButBeer=0;
			int largeButBeer1=0;
			int largeButBeer2=0;
			int totalSmallButBeer=0;
			int totalLargeButBeer1=0;
			int totalLargeButBeer2=0;
			int total4nonMember=0;
			int total4Member=0;
			boolean breakIt=false;

			int i=0;
			do {
				System.out.print("What is the Password? ");                                           
				String givenPass= input.next();                                                     //Asking user to enter Password
				if (givenPass.equalsIgnoreCase("Erecto")||givenPass.equalsIgnoreCase("Silencio")){
					hasDiscount = true;                                                              //This will execute only when the user is a member of Dumbledore's Army
					System.out.println("Welcome Dumbledore's Army member!");
					System.out.println("\tYou will get special discounts at DDD!");
					System.out.println("\tDiscount on bags of Cauldron Cakes");
					System.out.println("\tDiscount on Chocolate Frogs");
					System.out.println("\tSmall or Large Butterbeer - same price");
					System.out.println();
					break;
				} else if (i == 0) {
					System.out.println("You entered your password wrong. You can try once more!");   //Giving only 2 tries to the user
				}
				i++;
			} while ((i < 2) || hasDiscount );

			if (hasDiscount==true) {                                                                  //prices for members
				System.out.println("Here is our price list: ");
				System.out.println("\tCauldron Cakes (each) \t\t10 Knuts");
				System.out.println("\tCauldron Cakes (bag of 6) \t50 Knuts");
				System.out.println("\tChocolate Frogs (each) \t\t15 Knuts");
				System.out.println("\tButterbeer (small) \t\t50 Knuts");
				System.out.println("\tButterbeer (large) \t\t50 Knuts");
			} else {                                                                                  //prices for regular customers
				System.out.println("Please enjoy our items at their regular prices.");
				System.out.println("Here is our price list: ");
				System.out.println("\tCauldron Cakes (each)		\t10 Knuts");
				System.out.println("\tCauldron Cakes (bag of 6)	\t55 Knuts");
				System.out.println("\tChocolate Frogs (each) \t\t\t20 Knuts");
				System.out.println("\tButterbeer (small) \t\t\t50 Knuts");
				System.out.println("\tButterbeer (large) \t\t\t75 Knuts");
			}
			while (!breakIt){                                                                         //shopping cart
				System.out.println("Please choose an option:");
				System.out.println("\t1) Update Cauldron Cakes Order");
				System.out.println("\t2) Update Chocolate Frogs Order");
				System.out.println("\t3) Update Butterbeer Order");
				System.out.println("\t4) Check Out");

				int myOption= input.nextInt();                                                        //For updating Cauldron Cakes order
				if (myOption == 1){
					{
						if (numberofCauldronCakes==0)
							System.out.println("No Cauldron Cakes ordered\n");
						else
							System.out.println("You have " +numberofCauldronCakes+ "in your cart.");
					}
					if (hasDiscount==true){                                                           //For members
						System.out.println("How many Cauldron Cakes would you like for: "); 
						System.out.println("\tCauldron Cakes (each) \t\t10 Knuts");
						System.out.println("\tCauldron Cakes (bag of 6) \t50 Knuts");
					}
					else if (hasDiscount==false){                                                     //For regular customers
						System.out.println("How many Cauldron Cakes would you like for: ");
						System.out.println("\tCauldron Cakes (each)		\t10 Knuts");
						System.out.println("\tCauldron Cakes (bag of 6)	\t55 Knuts");
					}
					caulCakes=input.nextInt();
					if (numberofCauldronCakes<0){
						numberofCauldronCakes = 0;
						System.out.print("Negative numbers taken as 0");
					}

					numberofCauldronCakes=caulCakes+numberofCauldronCakes;                               //total number of cakes after updating how much ever times

					if (numberofCauldronCakes%6==5){                                                     //EXTRA CREDIT, Recommending customer to get another cake for updating to a bag
						System.out.println("Adding one more cake can get you another bag, \nwhich can give you a benefit of lower price overall!");
						System.out.println(" ");
					}
				}
				if (myOption==2){                                                                       //For updating Chocolate frogs order
					{
						if (numberOfChocolateFrogs==0)
							System.out.println("No Chocolate Frogs ordered");
						else
							System.out.println("You have " +numberOfChocolateFrogs+ " in your cart.");
					}
					if (hasDiscount==true){
						System.out.println("How many Chocolate Frogs would you like for 15 Knuts each?");
					}
					else if (hasDiscount==false){
						System.out.println("How many Chocolate Frogs would you like for 20 Knuts each?");
					}
					chocFrog = input.nextInt();
					if (chocFrog<0){
						chocFrog = 0;
						System.out.print("Negative numbers taken as 0");
					}
					numberOfChocolateFrogs=chocFrog+numberOfChocolateFrogs;
				}
				if (myOption==3){                                                                       //For updating Butterbeers ordered
					{
						if (totalSmallButBeer==0 && totalLargeButBeer1 == 0 && totalLargeButBeer2 == 0)
							System.out.println("No Butterbeer ordered");
						else	
							System.out.println("You have a total of "+totalSmallButBeer+totalLargeButBeer1+totalLargeButBeer2+" in your cart.");
					}
					if (hasDiscount==true){                                                                            //For members
						System.out.println("How many small butterbeers would you like for 50 knuts each?");
						smallButBeer = input.nextInt();
					}
					else if (hasDiscount==false){                                                                      //For Regular customers
						System.out.println("How many small butterbeers would you like for 50 knuts each?");
						smallButBeer= input.nextInt();
					}
					if (hasDiscount==true){                                                                        //For members
						System.out.println("How many large Butterbeers would you like for 50 Knuts each?");
						largeButBeer1 = input.nextInt();
					}
					else if (hasDiscount==false){                                                                      //For regular customers
						System.out.println("How many large Butterbeers would you like for 75 Knuts each?");
						largeButBeer2 = input.nextInt();
					}
					if (smallButBeer<0){                                                                               //If user inputs negative value
						smallButBeer=0;
						System.out.print("Negative numbers taken as 0");
					}
					if (largeButBeer1<0){
						largeButBeer1=0;
						System.out.println("Negative numbers taken as 0");
					}
					if (largeButBeer2<0){
						largeButBeer2=0;
						System.out.println("Negative numbers taken as 0");
					}
					totalSmallButBeer=totalSmallButBeer+smallButBeer;                         //total small butterbeers for both members and regular customers
					totalLargeButBeer1=totalLargeButBeer1+largeButBeer1;                      //total large butterbeers for members
					totalLargeButBeer2=totalLargeButBeer2+largeButBeer2;                      //total large butterbeers for regular customers
				}
				if (myOption==4){                                                            //For checking out
					int specialPrice=0;
					if (numberofCauldronCakes==0&&numberOfChocolateFrogs==0&&totalSmallButBeer==0&&totalLargeButBeer1==0&&totalLargeButBeer2==0){     //if there's nothing is in the cart
						System.out.print("There is nothing in your shopping cart. Thanks for stopping by!");
						break;
					}
					System.out.println("Here's your subtotal: \n");                           
					int ccakes=numberofCauldronCakes/6;                                //all the values present are for the purpose of calculating item prices in shopping cart
					int singlecakes= numberofCauldronCakes%6;                          //also for the purpose of finding difference between prices of regular items and discounted items
					int ccakesPricedis= ccakes*50;                                     //last but not the least for the extra credit as well!
					int singlecakesPricedis=singlecakes*10;
					int chocFrogPricedis=numberOfChocolateFrogs*15;
					int sbutbeerPricedis=totalSmallButBeer*50;
					int lbutbeerPricedis=totalLargeButBeer1*50;
					int ccakesPrice= ccakes*55;
					int singleCakesPrice=singlecakes*10;
					int chocFrogPrice=numberOfChocolateFrogs*20;
					int sbutbeerPrice=sbutbeerPricedis;
					int lbutbeerPrice=totalLargeButBeer2*75;
					int amount=0;
					total4nonMember= (ccakesPrice+singleCakesPrice+sbutbeerPrice+lbutbeerPrice+chocFrogPrice);               //total price of all items in shopping carts for regular customers
					total4Member= ccakesPricedis+singlecakesPricedis+sbutbeerPricedis+lbutbeerPricedis+chocFrogPricedis;     //total price of all items in shopping cart 
					specialPrice=(int)Math.round(total4Member-(total4Member*0.1));                                           //discounted price of all items in shopping cart 
					String currency;
					if (hasDiscount){
						if(ccakes>0){                                                                 //shopping cart for members
							System.out.println(ccakes+" bags of Cauldron Cakes at 50 Knuts ea.:\t"+(ccakesPricedis));
						}
						if(singlecakes>0){
							System.out.println(singlecakes+" Cauldron Cakes at 10 knuts ea.:\t\t"+(singlecakesPricedis));
						}
						if(numberOfChocolateFrogs>0){
							System.out.println(numberOfChocolateFrogs+" Chcocolate Frogs at 15 Knuts ea.:\t\t"+(chocFrogPricedis));
						}	
						if (totalSmallButBeer>0){
							System.out.println(totalSmallButBeer+" Small Butterbeers at 50 knuts ea.:\t\t"+(sbutbeerPricedis));
						}
						if (totalLargeButBeer1>0){
							System.out.println(totalLargeButBeer1+" Large Butterbeers at 50 knuts ea.:\t\t"+(lbutbeerPricedis));
						}
						System.out.println("\t\t\t\t\t\t-----");
						System.out.println("Total:\t\t\t\t\t\t"+(total4Member));
						System.out.println("Bonus Discount of 10%: \t\t\t\t"+(total4Member*0.1));
						System.out.println("\t\t\t\t\t\t-----");
						System.out.println("New total(rounded off): \t\t\t"+(specialPrice));
						int amountNew=0;
						while(true){
							System.out.println("Please enter your payment amount in the following format: ");
							System.out.println("\t<amount><space><currency>");
							System.out.println("\t\tWhere <amount> = an integer");
							System.out.println("\t\tWhere <space> = a blank space");
							System.out.println("\t\tWhere <currency> = {Knuts, Sickles, Galleons}\t > ");
							amount = input.nextInt();                    //the user's cash input
							currency=input.next();                       //the currency user inputs
							if (currency.equalsIgnoreCase("Sickles")){   //If user's currency is sickles
								amountNew=amount*29;
							}
							if (currency.equalsIgnoreCase("Galleons")){
								amountNew=amount*493;
							}
							if(currency.equalsIgnoreCase("Knuts")){
								amountNew=amount*1;
							}
							if(amountNew>specialPrice){                             //this loop is executed if user inputs amount more than required
								breakIt=true;
								System.out.println("Thank you!");
								System.out.println("Here is your change:\n");
								amountNew=amountNew-specialPrice;
								int finalGal=(amountNew/493);                       //knuts to galleons,sickles and knuts conversion
								int extraKnuts=(amountNew%493);                     //for change calculations
								int finalSickles=(extraKnuts/29);
								int finalKnuts=(extraKnuts%29);
								if (finalGal>0){
									System.out.println("\t\t"+finalGal+" Galleons");
								}
								if (finalSickles>0){
									System.out.println("\t\t"+finalSickles+" Sickles");
								}
								if (finalKnuts>0){
									System.out.println("\t\t"+finalKnuts+" Knuts");
								}
								System.out.print("\tThank you for Shopping at DDD!");
								System.out.println("[\tFun fact: You have saved "+(total4nonMember-specialPrice)+" knuts on this order \n\tBY JUST BEING A MEMBER OF DUMBLEDORE'S ARMY!!\t]");
								break;
							}
							else if (amountNew<specialPrice){                     //this loop is executed if user inputs less amount than required
								System.out.println("I'm sorry but your total is "+total4Member+" and you have given \nonly "+amount+" "+currency);
								System.out.println(" ");
								continue;
							}
							else if (amountNew==specialPrice){                    //this loop is executed if user inputs same amount as required
								breakIt = true;
								System.out.println("Thank you!");                //below line shows the savings made by a member 
								System.out.println("[\tFun fact: You have saved "+(total4nonMember-specialPrice)+" knuts on this order \n\tBY JUST BEING A MEMBER OF DUMBLEDORE'S ARMY!!\t]");
							}
							break;
						}
					}
					else if (!hasDiscount){
						breakIt=true;
						if(ccakes>0){                                    //shopping cart for regular customers
							System.out.println(ccakes+" bags of Cauldron Cakes at 55 Knuts ea.:\t"+(ccakesPrice));
						}
						if(singlecakes>0){
							System.out.println(singlecakes+" Cauldron Cakes at 10 knuts ea.:\t\t"+(singleCakesPrice));
						}
						if(numberOfChocolateFrogs>0){
							System.out.println(numberOfChocolateFrogs+" Chcocolate Frogs at 20 Knuts ea.:\t\t"+(chocFrogPrice));
						}	
						if (totalSmallButBeer>0){
							System.out.println(totalSmallButBeer+" Small Butterbeers at 50 knuts ea.:\t\t"+(sbutbeerPrice));
						}
						if (totalLargeButBeer2>0){
							System.out.println(totalLargeButBeer2+" Large Butterbeers at 75 knuts ea.:\t\t"+(lbutbeerPrice));
						}

						System.out.println("\t\t\t\t\t\t-----");
						System.out.println("Total(rounded off):\t\t\t\t"+(total4nonMember));
						int amountNorm=0;
						while(true){
							System.out.println("Please enter your payment amount in the following format: ");
							System.out.println("\t<amount><space><currency>");
							System.out.println("\t\tWhere <amount> = an integer");
							System.out.println("\t\tWhere <space> = a blank space");
							System.out.println("\t\tWhere <currency> = {Knuts, Sickles, Galleons}\n\t > ");
							amount = input.nextInt();
							currency=input.next();
							if (currency.equalsIgnoreCase("Sickles")){   //currency conversions
								amountNorm=amount*29;
							}
							if (currency.equalsIgnoreCase("Galleons")){
								amountNorm=amount*493;
							}
							if(currency.equalsIgnoreCase("Knuts")){
								amountNorm=amount*1;
							}
							if(amountNorm>total4nonMember){   //this loop is executed if user inputs more amount than required
								breakIt=true;
								System.out.println("Thank you!");
								System.out.println("Here is your change:\n");
								amountNorm=amountNorm-total4nonMember;
								int finalGal=(amountNorm/493);            //knuts to galleons,sickles and knuts conversion
								int extraKnuts=(amountNorm%493);          //for change calculations
								int finalSickles=(extraKnuts/29);
								int finalKnuts=(extraKnuts%29);
								if (finalGal>0){
									System.out.println("\t\t"+finalGal+" Galleons");
								}
								if (finalSickles>0){
									System.out.println("\t\t"+finalSickles+" Sickles");
								}
								if (finalKnuts>0){
									System.out.println("\t\t"+finalKnuts+" Knuts");
								}
								System.out.println("\tThank you for Shopping at DDD!"); 
								break;
							}
							else if (amountNorm<total4nonMember){         //this loop is executed if user inputs less amount than required
								System.out.println("I'm sorry but your total is "+total4nonMember+" and you have given only "+amount+" "+currency);
								System.out.println(" ");
								continue;
							}
							else if (amountNorm==total4nonMember){//this loop is executed if user inputs exact amount as required
								breakIt=true;
								System.out.println("Thank you!");
								System.out.println("\tThank you for Shopping at DDD!");	
							}
							break;
						}
					}
				}
			}
			System.out.println(" ");
			System.out.println("Do you want to shop again at Dumbledore's Delicious Delicacies?"); //allows the user to purchase again
			System.out.println("1 = Yes  and  2 = No ");
			int tryagain=0;
			tryagain=input.nextInt();
			if(tryagain==1){
				System.out.print("Is there a customer in line? (1 = yes, 2 = no) > "); 
				customer = input.nextInt();                                                        //Taking user's input value and checking if someone's in the line
			} else {
				break;
			}
		}
		input.close(); //closes the scanner
	}
} //program ends