package Assignment1;
import java.util.*; //importing for the use of Scanner object to receive input from console
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Alogrithmsv1 {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		
		ArrayList<Integer> list = new ArrayList<>();
		try{
			//create a BufferedReader and pass it a FileReader
			BufferedReader read = new BufferedReader(new FileReader("RandomNumbers.txt"));
			
			//loop over each line in the file
			while(true){
				String number = read.readLine();
				if (number == null){ 
					break;
				}
				//add the parsed string as an integer to the ArrayList
				list.add((Integer.parseInt(number)));

			}
			read.close();
		}catch (IOException e){
			System.out.println("something wrong with file");
		}
	
		
		// declare and initial the variable to store users input
		int value=0;
		int[] array = {6,3,7,2,8,5,2,3,7,4,7,8,9,3,2,4,5,0};
		
		Scanner input = new Scanner(System.in);
		
		//loop to cater for an invalid entry that is not 1 to 5
		try{
		while (value < 1 || value > 5){
			System.out.print("Enter your number selection from the list below:\n" +
					"1. Bubble Sort\n" +
					"2. Selection Sort\n" +
					"3. Insertion Sort\n" +
					"4. Merge Sort\n" +
					"5. Quick Sort\n");
			value = input.nextInt();
			//use a switch case to decide which method of sort to call
			switch(value){
			case 1:{
					PrintArray(array);
					BubbleSort(array);
					PrintArray(array);
					break;}
			case 2:{
					PrintArray(array);
					SelectionSort(array);
					PrintArray(array);
					break;}
			case 3:{
					PrintArray(array);
					InsertionSort(array);
					PrintArray(array);
					break;}
			case 4:{
					PrintArray(array);
					MergeSort(array,0, array.length-1);
					PrintArray(array);
					break;}
			case 5:{
					PrintArray(array);
					QuickSort(array,0,array.length -1);
					PrintArray(array);
					break;}
			default:{
				System.out.println("Invalid entry try again");
				}
			}
		}
		}catch(Exception e){
			System.out.println("Invalid Entry must be a whole number between 1 and 5");
			
		}
		input.close();//close the Scanner object

	}
	
	public static void BubbleSort(int[] array){
		//Variable to use when swapping
		int temp=0; 
		//Variable to keep track of end of unsorted section on left
		int end = array.length-1 ;
		 //loop from beginning or array to 1 before the end 
		//but the end moves lower each time as the highest value gets placed at the top
		for (int j=0; j<end; end--){ 
			//loop from beginning of array to the end of unsorted array on left side
			for (int i =0; i <end; i++){ 
					//check each value starting from 0 and move it up one to end if it larger
					if (array[i] >array[i+1]){ 
						//swap the larger value to the right by 1. the loop will bring it to the end
						temp = array[i]; 
						array[i]= array[i+1]; 
						array[i+1] = temp; 
					} 
			} 
		}
	}	
	
	public static void SelectionSort(int[] array){

		//issue with array size 10?? but works fine with ArrayList large
		int temp; //required for swapping
		int end = array.length-1; //Variable to make code more readable
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
				if (array[i]<array[minpos]){
					minpos=i; 
				} 	
			} 
			 //swap minimum value to the start of the unsorted right side(j)
			 temp = array[j];
			 array[j] = array[minpos];
			 array[minpos] = temp;
		} 
		
	}
	
	public static void InsertionSort(int[] array){
		//declare variable for counting
		int count;
		//declare variable to hold value to be inserted
		int insert; 
		//unsorted start position index, using this for more readability to code
		int unSstart=1;
		//loop from start to 1 before end of array
		for( int i=0; i<array.length-1; i++){
			//this variable count will be counting each time the insert value (start value of unsorted right side)
			//is less then values in sorted array left side
			count=0;
			insert= array[unSstart];
			//loop from end of sorted left side down to zero
			for (int j=unSstart-1; j >= 0; j--){
				//identify if the new insert is less then sorted values
				if (insert < array[j]){
					//as we have saved the value for the insert we can overwrite it
					//by moving each value up one on array that is less then insert
					array[j+1] = array[j];
					count++;
				}
			}
			//and at the end we place the insert where we want it, based on count we know
			//it is this far down the sorted side
			array[unSstart-count]=insert;
			//we move the starting position up one could have used i+1 but more readable this way
			unSstart++;
				
		}

	}
	
	public static void MergeSort(int[] array, int start, int end){
		 int[] temp = new int[array.length];

			if (start == 0 && end == 1){
				SortSave(array, temp,start,(int)((start+end)/2),end);
			}

			if (start +1 <end){
				//Recursively call method for left side
				MergeSort(array, start, (int)((start+end)/2));

				//Recursively call method for right side
				MergeSort(array,(int)((start+end)/2 + 1), end);

				//Do the sort and save
				SortSave(array,temp, start, (int)((start+end)/2), end);
			}
	}
	
	
	public static void SortSave(int[] array,int[] temp,int start,int middle,int end){

		    //recursively call sort for right side
			int j=middle+1;
				if (j < end){
					//if(  (start!= 0) && (end != array.length-1)){
					SortSave(array,temp,j,(int)((j+end)/2),end);
					
					//}
				}
	      
			//Do the sort of left side + right side after being done
			//merge and sort result into temp
			int p = start; //left side position marker
			int q = middle+1; //right side position marker
			//check if the value of q after adding 1 is not greater then size of array
			if (q > end ){ q=end; }
			//loop for each element from passed start and end
			for (int k = start; k <=end; k++){
					//check to ensure don't loop too far so we go up to middle and up to end not passed
					if( (p <= middle ) && (q <= end) ){
						//compare p value to q value and move up one when success is found on either side
						if ( array[p] < array[q]){
							//when find lowest value place in temp array at beginning (in relation to passed values)
							//and next time it will move up one with counter k
							temp[k] = array[p];
							p++; //increase left side as value was found on left
						}else{
							temp[k] = array[q];
							q++; //increase right side as value was found on right
						}
						
					}
					
					//if the left side has reached the end
					//copy the remaining right sorted elements
					else if( p== middle +1){
						temp[k] = array[q];
						q++;
					}
					//if the right side has reached the end
					//copy the remaining left sorted element
					else if( q == end +1){
						temp[k] = array[p];
						p++;
					}	
			}// end for loop
			
			//Copy result back to array as you go along
			for (int i = start; i <= end; i++) {
				array[i] = temp[i];
			}//end for loop
	}// end method
	

	public static void QuickSort(int[] array, int start, int end){
		if (start < end) { 
			//sorting and setting a split with pivot 
			int partition = PivotSort(array, start, end); 
			//split to left side, which in turn calls PivotSort
			QuickSort(array, start, partition-1); 
			//split right side, which in turn calls PivotSort
			QuickSort(array,partition+1,end); 
		} 
		 
	}
	public static int PivotSort(int[] array,int start,int end) {
	 
		 //pick pivot - pick right most as it a random list
		int pivot =array[end] ;
		//variable used for the swapping
		int temp=0;
		//represent left side end but will start at the beginning of the passed value start, 
		//as the left side will always start with only one element
		int leftendP=start ;
			
		for( int i = start; i  < end;i++){ 
			if (array[i]<= pivot){ 
				//swap value less then pivot
				temp = array[i]; 
				array[i]= array[leftendP]; 
				array[leftendP] = temp;
				leftendP++;	//increase index right only if the number was less than pivot			 
				} 	
				
		} 	//end for loop
		//at end of sort, swap the pivot value into the end position of the left side
		//we know all is less than this pivot
		temp = array[leftendP]; 
		array[leftendP]= array[end]; 
		array[end] = temp;
		// return this left position index it will create the partition that is already sorted based on pivot
		//will mean the pivot number has been placed in the middle and all the number that are lower than it 
		//have been placed to the left and all the remaining on right are larger thanks pivot
		return leftendP ;
	} 
	
	public static void PrintArray(int[] array){
		System.out.print("\nArray: ");
		for (int i = 0; i <array.length; i++){
			System.out.print(array[i]+" ");
		}
	}
	public static void PrintTempArray(int[] array){
		System.out.print("\nTemp Array: ");
		for (int i = 0; i <array.length; i++){
			System.out.print(array[i]+" ");
		}
	}
	
	public static void PrintList(ArrayList<Integer> list){
		for(int value:list){
			System.out.println(value);
		}
			
	}
	
}

