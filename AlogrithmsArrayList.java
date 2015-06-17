package Assignment1;
import java.util.*; //importing for the use of Scanner object to receive input from console
//imports for the reading from and writitng to files
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import algoUtil.*;


public class AlogrithmsArrayList {

	public static void main(String[] args) {
		//creat teh ArrayList that will hold the inputed values from the file
		//choosing Array list as the size of the file is unknown
		ArrayList<Integer> list = new ArrayList<>();
		//variable declared and initialized for testing sort
		String testResult = "sorted"; 
		
		// declare and initial the variable to store users input
		int value;
		//create Scanner object for inputting from users
		Scanner input = new Scanner(System.in);
		//use a try catch to let user know if issue ready the file
		while (true){
			try{
				
				System.out.println("Enter the name of the file and it's extention .txt," +
						" \nbut please save the file in the Working Directory of your IDE first:");
				String file = input.next();
				System.out.print(file);
				//create a BufferedReader and pass it a FileReader and th file name given by user
				BufferedReader read = new BufferedReader(new FileReader(file));
			
				
				//loop over each line in the file
				while(true){
					String number = read.readLine();
					if (number == null){ 
						//when the last line is reached it will be null so break
						break;
					}
					//add the parsed string as an integer to the ArrayList
					list.add((Integer.parseInt(number)));
	
				}
				read.close();//close the BufferedReader
				
				if (read !=null){
					break;
				}
				
				
			}catch (IOException e){
				System.out.println("Something wrong with file, "+ e);
			}	
			
			
		}
		//input.close();
		
		//loop to cater for an invalid entry that is not 1 to 5, otherwise throw exception when not a number
		try{
			//create Scanner object for inputting from users
			//Scanner input = new Scanner(System.in);
			do{
				
				System.out.print("Enter your number selection from the list below:\n" +
						"1. Bubble Sort\n" +
						"2. Selection Sort\n" +
						"3. Insertion Sort\n" +
						"4. Merge Sort\n" +
						"5. Quick Sort\n");
				value = input.nextInt();
				if (value > 0 && value < 6)
				//use a switch case to decide which method of sort to call
				switch(value){
					case 1:{
							BubbleSort(list);
							break;}
					case 2:{
							SelectionSort(list);
							break;}
					case 3:{
							InsertionSort(list);
							break;}
					case 4:{
							MergeSort(list,0,list.size()-1);
							break;}
					case 5:{
							QuickSort(list,0,list.size()-1);
							break;}
					default:{
							System.out.println("Invalid entry try again");
							break;
						}
				}
				
			}while (value < 1 || value > 5);//end while
			
		
			
		}catch(Exception e){
			System.out.println("Invalid Entry must be a whole number between 1 and 5, " + e);
		}
		input.close();//close the Scanner object
		
		//Test: check through the sorted array to see if it is all sorted and return the result accordingly
		for(int i=0;i<list.size()-1;i++){
			 if (!(list.get(i)<= list.get(i+1))){
				 testResult = "not sorted";
				
			 } 
		 }
		 System.out.println("Array is " + testResult);
		
		try{
			//create FileWriter and BufferedWriter objects
			FileWriter outputFile = new FileWriter("sortedList.txt");
			BufferedWriter output = new BufferedWriter(outputFile);
			
			//loop through the integers in the ArrayList list
			for(int out:list){
				//need to convert the int value to String before writing to file using the String class method
				output.write(String.valueOf(out) + "\n");
			}
			System.out.println("\nFile saved called sortedList.txt. \nYou will find this in the working directory of your IDE.");
			output.close();
			
		}catch (Exception e){
			System.out.println("failed to output because: " + e);
		}
	}// end main class
	
	public static void BubbleSort(ArrayList<Integer> list){
		//Variable to use when swapping
		int temp=0; 
		//Variable to keep track of end of unsorted section on left
		int end = list.size()-1 ;
		 //loop from beginning or array to 1 before the end 
		//but the end moves lower each time as the highest value gets placed at the top
		for (int j=0; j<end; end--){ 
			//loop from beginning of array to the end of unsorted array on left side
			for (int i =0; i <end; i++){ 
					//check each value starting from 0 and move it up one to end if it larger
					if (list.get(i) > list.get(i+1)){ 
						//swap the larger value to the right by 1. the loop will bring it to the end
						temp = list.get(i); 
						list.set(i, list.get(i+1)); 
						list.set(i+1, temp); 
					} 
			} 
		}
	}//end BubbleSort	
	
	public static void SelectionSort(ArrayList<Integer> list){

		int temp; //required for swapping
		int end = list.size()-1; //Variable to make code more readable
		int minpos = 0; //the index in the array where the minimum value is
		//loop through array until 1 before the end
		//each time increasing the start of the unsorted right side of array
		for(int j=0; j<end; j++) { 
			//initially set the min value to be the fist position in unsorted array and then 
			//increase by one in each iteration
			minpos = j; 
			//loop through unsorted right side of array till the last index
			for(int i = j+1; i<=end;i++){ 
				//check all in unsorted right side which is the minimum, each time you find keep record with minpos
				if (list.get(i) < list.get(minpos)){
					minpos=i; 
				} 	
			} 
			 //swap minimum value to the start of the unsorted right side(j)
			 temp = list.get(j);
			 list.set(j, list.get(minpos));
			 list.set(minpos, temp);
		} 
	}//end Selection Sort
	
	public static void InsertionSort(ArrayList<Integer> list){
		//declare variable for counting
		int count;
		//declare variable to hold value to be inserted
		int insert; 
		//unsorted start position index, using this for more readability to code
		int unSstart=1;
		//loop from start to 1 before end of array
		for( int i=0; i< list.size()-1; i++){
			//this variable count will be counting each time the insert value (start value of unsorted right side)
			//is less then values in sorted array left side
			count=0;
			insert= list.get(unSstart);
			//loop from end of sorted left side down to zero
			for (int j=unSstart-1; j >= 0; j--){
				//identify if the new insert is less then sorted values
				if (insert < list.get(j)){
					//as we have saved the value for the insert we can overwrite it
					//by moving each value up one on array that is less then insert
					list.set(j+1, list.get(j));
					count++;
				}
			}
			//and at the end we place the insert where we want it, based on count we know
			//it is this far down the sorted side
			list.set(unSstart-count, insert);
			//we move the starting position up one could have used i+1 but more readable this way
			unSstart++;
				
		}
	}//end Insertion Sort
	
	public static void MergeSort(ArrayList<Integer> list, int start, int end){
			//create a temp array and initializing to the size of the ArrayList
			int[] temp = new int[list.size()];
			
			//when there is only 2 elements in the passed start and end 
			//there will be no need to merge just sort
			if (end-start == 1){
				SortSave(list, temp, start,(int)((start+end)/2), end);
			}
			//check to ensure the size from start to end is more then 2 in size
			else if(start+1 < end){
				//Recursively call method for left side
				MergeSort(list, start, (int)((start+end)/2));

				//Recursively call method for right side
				MergeSort(list, (int)((start+end)/2 + 1), end);

				//Do the sort and save
				SortSave(list, temp, start, (int)((start+end)/2), end);
			}
	}//end Merge Sort
	public static void SortSave(ArrayList<Integer> list, int[] temp, int start, int middle, int end){

		    
			int p = start; //left side position marker
			int q = middle+1; //right side position marker	
			int j=middle+1;
			//recursively call sort for right side
			if (j < end){
					//if(  (start!= 0) && (end != array.length-1)){
					SortSave(list,temp,j,(int)((j+end)/2),end);
					//}
			}
	      
			//Do the sort of left side + right side after being done
			//merge and sort result into temp
			
			//check if the value of q after adding 1 is not greater then size of array
			if (q > end ){ q=end; }
			//loop for each element from passed start and end
			for (int k = start; k <=end; k++){
					//check to ensure don't loop too far so we go up to middle and up to end not passed
					if( (p <= middle ) && (q <= end) ){
						//compare p value to q value and move up one when success is found on either side
						if ( list.get(p) < list.get(q)){
							//when find lowest value place in temp array at beginning (in relation to passed values)
							//and next time it will move up one with counter k
							temp[k] = list.get(p);
							p++; //increase left side as value was found on left
						}else{
							temp[k] = list.get(q);
							q++; //increase right side as value was found on right
						}
						
					}
					
					//if the left side has reached the end
					//copy the remaining right sorted elements
					else if( p == middle+1){
						temp[k] = list.get(q);
						q++;
					}
					//if the right side has reached the end
					//copy the remaining left sorted element
					else if( q == end+1){
						temp[k] = list.get(p);
						p++;
					}	
			}// end for loop
			
			//Copy result back to array as you go along
			for (int i = start; i <= end; i++) {
				list.set(i,temp[i]);
			}//end for loop
	}// end SortSave method part of Merge Sort
	
	public static void QuickSort(ArrayList<Integer> list, int start, int end){
		if (start < end) { 
			//sorting and setting a split with pivot 
			int partition = PivotSort(list, start, end); 
			//split to left side, which in turn calls PivotSort
			QuickSort(list, start, partition-1); 
			//split right side, which in turn calls PivotSort
			QuickSort(list,partition+1,end); 
		} 
		 
	}//end QuickSort method
	public static int PivotSort(ArrayList<Integer> list,int start,int end) {
	 
		 //pick pivot - pick right most as it a random list
		int pivot =list.get(end) ;
		//variable used for the swapping
		int temp=0;
		//represent left side end but will start at the beginning of the passed value start, 
		//as the left side will always start with only one element
		int leftendP=start ;
			
		for( int i = start; i  < end;i++){ 
			if (list.get(i) <= pivot){ 
				//swap value less then pivot
				temp = list.get(i); 
				list.set(i, list.get(leftendP)); 
				list.set(leftendP, temp);
				leftendP++;	//increase index right only if the number was less than pivot			 
				} 	
				
		} 	//end for loop
		//at end of sort, swap the pivot value into the end position of the left side
		//we know all is less than this pivot
		temp = list.get(leftendP); 
		list.set(leftendP, list.get(end)); 
		list.set(end, temp);
		// return this left position index it will create the partition that is already sorted based on pivot
		//will mean the pivot number has been placed in the middle and all the number that are lower than it 
		//have been placed to the left and all the remaining on right are larger thanks pivot
		return leftendP ;
	}//end Pivot Sort required with QuickSort 
	
	
}//end AlgorithmsArrayList Class

