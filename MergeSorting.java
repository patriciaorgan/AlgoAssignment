public class MergeSorting{

public static void main(String[] args){
  int[] array = {6,5,8,3,1,2,7,9,4}

  for(int i = 0,i <array.length -1,i++)
  System.out.print(array[i] +" ");
  MergeSort(array,0,array.length -1);

}
public static void mergeSort(array, start,end){
   public int[9] temp;

		if (start = 0 and end = 1){
      SortSave(array,start,(int)(start+end)/2,end);
    }

		if (start +1<end){
      //recursivly call method for left side
			mergeSort(array,start, (int)(start+end)/2);

      //recursivly call method for right side
			mergeSort(array,(start+end)/2+1, end);

			//Do the sort and save
			SortSave(array, start, (int)(start+end)/2, end);
    }
	}
public static void SortSave(array,start,middle,end){

	        //recursively call sort for right side
		int j=middle+1;
		if (j < end)
			if(  (start <> 0 && end <>array.length-1)){
				sortSave(array,j,(int)(j+end)/2,end);
      }
		//Do the sort of left side + right side after being done
		//merge and sort result into temp
		int p = start;
		int q = middle+1;
		if (q>end){ q=end;}

		for (int k = start, k< =end, k++){
			if(p<=middle+1 && q <= end+1){
				if ( array[p] <array[q]){
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
			elseif( q == end +1){
				temp[k] = array[p];
				p++;
			}
		}
		//Copy result back to array as you go along
		for (int i = start, i <end, i++) {
		array[i] = temp[i];
		}

	}

}
