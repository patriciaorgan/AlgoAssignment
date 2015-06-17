/**
 * Patricia Organ - 01110489 - CT853 Algorithms Assignment 2014
 */
package AlgoAssignment;//this is the folder name I have locally for my package
import java.util.*; //importing for the use of Scanner object to receive input from console
//importing the package I placed all the other classes I need in
import algoUtil.*;

public class AlogrithmsTester {

	public static void main(String[] args) {
		
		// declare variable to store users input
		int value;
		//create Scanner object for inputting from users
		Scanner input = new Scanner(System.in);
		
		//create a Reading and Writing instance
		Reading file = new Reading();
		Writing output = new Writing();
		
		//ArrayList so as to pass by reference when needed
		//using List to make it interchangeable from ArrayList if needed
		List<Integer> list = new ArrayList<Integer>();
			
		//to hold the sub class object I have created a SuperClass array
		Sorts[] sort = new Sorts[5];
		//and initialised each array value with one of each sub class object
		sort[0] = new BubbleSort();
		sort[1] = new SelectionSort();
		sort[2] = new InsertionSort();
		sort[3] = new MergeSort();
		sort[4] = new QuickSort();
		
		try{
		//loop to cater for an invalid entry that is not 1 to 5,
		do{	
				System.out.print("Enter your number selection from the list below:\n" +
							"1. Bubble Sort\n" +
							"2. Selection Sort\n" +
							"3. Insertion Sort\n" +
							"4. Merge Sort\n" +
							"5. Quick Sort\n");
					
				value = input.nextInt();
					
				if (value > 0 && value < 6){
						
						//call the method in the Reading Class
						file.readFile(list);
						
						//call the sort method to correspond to the object in the Sorts array
						sort[value-1].Sort(list, 0 , list.size()-1);
						
						//call the method in the Writing Class
						output.writeFile(list, sort[value-1].toString());
						
						//call the common code method from the SuperClass that is inherited by all subclasses
						sort[value-1].testSort(list);
				}	
				else{
						System.out.println("Invalid entry try again");
					}
				
			}while (value < 1 || value > 5);//end while if condition not met
		
		}catch(Exception e){
			System.out.println("Invalid entry program exited, has to be whole number");
		}
			input.close();//close the Scanner object
		
	}// end main class
	
}//end AlgorithmsTester Class

