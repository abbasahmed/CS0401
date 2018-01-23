import java.util.Scanner;
/**
 *
 * @author Zinan Zhuang
 */
public class Array {

    public static void main(String[] args) {
        System.out.println("How many items do you want to enter?");             //Ask for the item size
        Scanner keyboard = new Scanner(System.in);                              //initialize a scanner object 
        int size = keyboard.nextInt();                                                                        //store the size in an integer variable
        double []arr = new double[size];                                                                        //initialize an double array with correct size and data type
        for(int i=0;i<size;i++){                                                                  //loop to ask for the value until reaching the size
            System.out.println("Please enter the "+(i+1)+"th value: ");         //ask for the value for ith value
              arr[i] = keyboard.nextInt();                                                                 //store the ith value in the array
        }                                                                       //the loop ends here
       double max = max(arr);                                                   //call max to get the max value
       double min = min(arr);                                                   //call min to get the min value
       double sum = sum(arr);                                                   //call sum to get the sum
       double ave = ave(arr);                                                   //call ave to get the average 
        System.out.println("The max value in this array is: "+max);             //"The max value in this array is: x"
        System.out.println("The min value in this array is: "+min);             //"The min value in this array is: x"
        System.out.println("The sum of this array is: "+sum);                   //"The sum of this array is: x"        
        System.out.println("The average of this array is: "+ave);               //"The average of this array is: x"           
    keyboard.close();
    }                                                                           //main method ends here
   
    public static double max(double[] data){                                    
        return max_rec(data,0);
    }                                                                           
    
        public static double min(double[] data){                                
            return min_rec(data,0);
    }                                                                          
        
        public static double sum(double[] data){                               
            return sum_rec(data, 0);
    }                                                                       
        
        public static double ave(double[] data){                                
            return ave_rec(data, 0);
    }                                                                          
    
        private static double max_rec(double[] array, int index){
        	if(index  == array.length-1){
        		return array[index];
        	}
        	
        	double firstValue= array[index];
        	double nextValue = max_rec(array, index +1);
        	
        	return Math.max(firstValue, nextValue);
        	
        }
        
        private static double min_rec(double[] array, int index){
        	if(index  == array.length-1){
        		return array[index];
        	}
        	
        	double firstValue= array[index];
        	double nextValue = min_rec(array, index +1);
        	
        	return Math.min(firstValue, nextValue);
        }
        
        private static double sum_rec(double[] array, int index){
        	if(index == array.length-1){
        		return array[index];
        		}
        	return array[index]+sum_rec(array, index+1);
        	}
        
        private static double ave_rec(double[] array, int index){
        	return  sum_rec(array,index)/array.length;
        }
}                                                                             
