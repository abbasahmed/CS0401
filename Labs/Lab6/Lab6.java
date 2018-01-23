
import java.util.Scanner;

public class Lab6 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		double[] numbers = null;
		System.out.print("Please enter the number of items you would like to enter: ");
		int arrNum = input.nextInt();
		numbers = new double[arrNum];
		getData(input, numbers);
		max(numbers);
		min(numbers);
		sum(numbers);
		ave(numbers);
		System.out.println("The maximum number is " + max(numbers));
		System.out.println("The minimum number is " + min(numbers));
		System.out.println("The sum of the numbers is " + sum(numbers));
		System.out.println("The average of the numbers is " + ave(numbers));
	}

	public static void getData(Scanner s, double[] numbersInput) {
		for (int i = 0; i < numbersInput.length; i++) {
			System.out.print("Enter number " + (i + 1) + ": ");
			numbersInput[i] = s.nextDouble();
		}
	}

	public static double max(double[] dataMax) {
		double max = dataMax[0];
		for (int i = 1; i < dataMax.length; i++) {
			if (dataMax[i] > max) {
				max = dataMax[i];
			}
		}
		return max;
	}

	public static double min(double[] dataMin) {
		double min = dataMin[0];
		for (int i = 1; i < dataMin.length; i++) {
			if (dataMin[i] < min) {
				min = dataMin[i];
			}
		}
		return min;
	}

	public static double sum(double[] dataSum) {
		double sum = 0;
		for (double i : dataSum) {
			sum += i;
		}
		return sum;
	}

	public static double ave(double[] dataAve) {
		double sum = 0;
		double ave = 0;
		for (double i : dataAve) {
			sum += i;
		}
		ave = sum / (dataAve.length);
		return ave;
	}

}
