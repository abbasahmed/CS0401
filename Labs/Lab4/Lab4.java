import java.util.Scanner;
import java.util.Random;
public class Lab4 {

	public static void main(String[] args) {
		Random dice=new Random();
		Scanner input=new Scanner(System.in);
		boolean keepGoing =true;
		while (keepGoing){
			System.out.println("Please enter the number of times you want to roll the dice: ");
			int rolls = input.nextInt();
			RollDice(rolls, dice);
			System.out.println("Do you want to continue? 1 = Yes, 0 = No");
			int tryAgain= input.nextInt();
			if (tryAgain==1){
				keepGoing=true;
				continue;
			} else if (tryAgain!=1){
				keepGoing = false;
				break;
			}
		}
		input.close();
	}
	public static void RollDice(int times, Random dice){
		int die1=0;
		int die2=0;
		int diceTotal2=0;
		int diceTotal3=0;
		int diceTotal4=0;
		int diceTotal5=0;
		int diceTotal6=0;
		int diceTotal7=0;
		int diceTotal8=0;
		int diceTotal9=0;
		int diceTotal10=0;
		int diceTotal11=0;
		int diceTotal12=0;
		for(int numberOfRolls=0; numberOfRolls<times; numberOfRolls++)
		{
			die1=dice.nextInt(6)+1;
			die2=dice.nextInt(6)+1;
			int sumOfDice=die1+die2;
			if(sumOfDice==2){
				diceTotal2++;
			}
			if(sumOfDice==3){
				diceTotal3++;
				
			}
			if(sumOfDice== 4){
				diceTotal4++;
			
			}
			if(sumOfDice==5){
				diceTotal5++;

			}
			if(sumOfDice==6){
				diceTotal6++;

			}
			if(sumOfDice==7){
				diceTotal7++;
				
			}
			if(sumOfDice==8){
				diceTotal8++;
				
			}
			if(sumOfDice==9){
				diceTotal9++;
				
			}
			if(sumOfDice==10){
				diceTotal10++;
				
			}
			if(sumOfDice==11){
				diceTotal11++;
				
			}
			if(sumOfDice==12){
				diceTotal12++;
				
			}
		}
		System.out.println("Number 2 has occurred "+diceTotal2+ " / "+times);
		System.out.println("Number 3 has occurred "+diceTotal3+" / "+times);
		System.out.println("Number 4 has occurred "+diceTotal4+" / "+times);
		System.out.println("Number 5 has occurred "+diceTotal5+" / "+times);
		System.out.println("Number 6 has occurred "+diceTotal6+" / "+times);
		System.out.println("Number 7 has occurred "+diceTotal7+" / "+times);
		System.out.println("Number 8 has occurred "+diceTotal8+" / "+times);
		System.out.println("Number 9 has occurred "+diceTotal9+" / "+times);
		System.out.println("Number 10 has occurred "+diceTotal10+" / "+times);
		System.out.println("Number 11 has occurred "+diceTotal11+" / "+times);
		System.out.println("Number 12 has occurred "+diceTotal12+" / "+times);
	}

}
