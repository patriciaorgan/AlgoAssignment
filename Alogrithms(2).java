import java.util.*; //importing for the use of Scanner object to receive input from console
public class Alogrithms {

	/**
	 * @param args
	 */
	public static int test = 0;
	public static void main(String[] args) {
		// 
		
		int value;
		int[] array = {1,9,4,2,10,7,6,8};
		
		Scanner input = new Scanner(System.in);
		System.out.print("Enter your number selection from the list below:\n" +
				"1. Bubble Sort\n" +
				"2. Selection Sort\n" +
				"3. Insertion Sort\n" +
				"4. Merge Sort\n" +
				"5. Quick Sort\n");
		value = input.nextInt();
		
		PrintArray(array);
		
		switch(value){
		case 1:{
				BubbleSort(array);
				PrintArray(array);
				break;}
		case 2:{
				SelectionSort(array);
				PrintArray(array);
				break;}
		case 3:{
				InsertionSort(array);
				PrintArray(array);
				break;}
		case 4:{
				MergeSort(array,0, array.length-1);
				PrintArray(array);
				break;}
		case 5:{
				QuickSort(array,0,array.length -1);
				PrintArray(array);
				break;}
		}
		input.close();

	}
	
	public static void BubbleSort(int[] array){
		int temp=0; 
		int end = array.length-1 ;
		 
		for (int j=0; j<end; end--){ 
			for (int i =0; i <array.length-1; i++){ 
					if (array[i] >array[i+1]){ 
						temp = array[i]; 
						array[i]= array[i+1]; 
						array[i+1] = temp; 
					} 
			} 
		}
	}	
	
	public static void SelectionSort(int[] array){

		//selection sort wrong in middle 
		int min;
		int temp; 
		int end = array.length-1;
		int minpos = 0;
		for(int j=0; j<end; j++) { 
			min = array[j];
			minpos = j;
			for(int i = j+1; i<=end;i++){ 
				if (array[i]<min){ 
					min = array[i]; 
					minpos=i; 
				} 	
			} 
			 
			 temp = array[j];
			 array[j] = array[minpos];
			 array[minpos] = temp;
		} 
		
	}
	
	public static void InsertionSort(int[] array){
	
	}
	
	public static void MergeSort(int[] array, int start, int end){
		 int[] temp = new int[array.length];

			if (start == 0 && end == 1){
				SortSave(array, temp,start,(int)((start+end)/2),end);
			}

			if (start +1 <end){
				//recursivly call method for left side
				MergeSort(array, start, (int)((start+end)/2));

				//recursivly call method for right side
				MergeSort(array,(int)((start+end)/2 + 1), end);

				//Do the sort and save
				SortSave(array,temp, start, (int)((start+end)/2), end);
			}
	}
	
	
	public static void SortSave(int[] array,int[] temp,int start,int middle,int end){

		        //recursively call sort for right side
			int j=middle+1;
			if (j < end)
				if(  (start!= 0) && (end != array.length-1)){
					SortSave(array,temp,j,(int)((j+end)/2),end);
	      }
			//Do the sort of left side + right side after being done
			//merge and sort result into temp
			int p = start;
			int q = middle+1;
			if (q>end){
				q=end;
			}

			for (int k = start; k <=end; k++){
				if(p<middle+1 && q < end+1){
					if ( array[p] < array[q]){
						temp[k] = array[p];
						p++;
					}else{
					temp[k] = array[q];
					q++;
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
				
				PrintTempArray(temp);
			}
			//Copy result back to array as you go along
			for (int i = start; i <= end; i++) {
			array[i] = temp[i];
			}
			System.out.print("\nAfter loops: ");
			PrintArray(array);
			PrintTempArray(temp);

	}
	

	public static void QuickSort(int[] array, int start, int end){
		if (start < end) { 
			//sorting and setting a split with pivot 
			int partition = PivotSort(array, start, end); 
			
			//split to left side  which in turn calls PivotSort
			QuickSort(array, start, partition-1); 
			//split right side 
			QuickSort(array,partition+1,end); 
		} 
		 
	}
	public static int PivotSort(int[] array,int start,int end) {
	 
		 //pick pivot - pick right most as it a random list
		int temp=0;
		int pivot =array[end] ;
		int leftP=start ;//left starting position 
		
			for( int i = start; i  < end;i++){ 
				if (array[i]<= pivot){ 
					temp = array[i]; 
					array[i]= array[leftP]; 
					array[leftP] = temp;
					leftP++;	//increase index right only if the number was less than pivot			 
				} 
				
			} 
	
			//at end of sort, swap the pivot value into the position we know all the 
			//left side is less than this pivot
		temp = array[leftP]; 
		array[leftP]= array[end]; 
		array[end] = temp;
		// return this left position index it will create the partition that is already sorted based on pivot
		//will mean the pivot number has been placed in the middle and all the number that are lower than it 
		//have been placed to the left and all the remaining on right are larger thanks pivot
		return leftP ;
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
}

