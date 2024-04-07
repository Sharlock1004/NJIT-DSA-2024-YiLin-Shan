package oy.tol.tira.books;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

public class Algorithms {
    public static <T extends Comparable<T>> void sort(T []array){
        
      int i = array.length-1;
      
      for(int j=0;j<i;j++){
       for(int k=0;k<i;k++){
         if (array[k].compareTo(array[k+1]) > 0 ) {
            T tmp = array[k];
            array[k] = array[k+1];
            array[k+1] = tmp;
         }
        
      }
    }
}



public static <T extends Comparable<T>> int binarySearch(T aValue, T [] fromArray, int fromIndex, int toIndex) {
   while (fromIndex<=toIndex) {
    int middle=(fromIndex+toIndex)/2;
    int compareResult=aValue.compareTo(fromArray[middle]);
    if(compareResult==0){
      return middle;
    }
    else if(compareResult<0){
      toIndex=middle-1;
    }
    else{
      fromIndex=middle+1;
    }
   
   }
  
   return -1;

}

public static <E extends Comparable<E>> void fastSort(E [] array) {
  quickSort(array, 0, array.length - 1);
}

public static <E extends Comparable<E>> void quickSort(E [] array, int begin, int end) {
  if(begin<end){
    int q=partition(array, begin, end);
  
    quickSort(array, begin, q-1);
    if(q<100){
    quickSort(array, q+1, end);
    }
    
  }
  
 

}

private static <E extends Comparable<E>> int partition(E [] array, int begin, int end) {
  E x=array[end];
  int i=begin-1;
  for(int j=begin;j<end;j++){
   if(array[j].compareTo(x)<=0){
     i++;
     E temp=array[i];
     array[i]=array[j];
     array[j]=temp;
   }
  
  }
  E temp2=array[i+1];
  array[i+1]=array[end];
  array[end]=temp2;
  return i+1;
  }
 

   
  public static <T> int partitionByRule(T [] pairs,int count,Predicate<T> judgeNullPredicate){
    int left = 0;
    int right = count - 1;

    while (left <= right) {
        while (left <= right && !judgeNullPredicate.test(pairs[left])) {
            left++;
        }

        while (left <= right && judgeNullPredicate.test(pairs[right])) {
            right--;
        }

        if (left < right) {
          T temp=pairs[left];
          pairs[left]=pairs[right];
          pairs[right]=temp;
            left++;
            right--;
        }
    }
    return left;

}

public static <T> void sortWithComparator( T[] array, Comparator<? super T> comparator) {
    Arrays.sort(array, comparator);
}
}

