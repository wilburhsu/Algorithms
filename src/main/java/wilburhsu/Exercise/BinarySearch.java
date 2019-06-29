package wilburhsu.Exercise;

public class BinarySearch {
    public int binarySearch(int[] array,int key){
        int start = 0;
        int end = array.length;
        while(start <= end){
            int mid = start + (end - start)/2;
            if(array[end] > key)
                end = mid - 1;
            else if(array[start] < key)
                start = mid + 1;
            else
                return start;
        }
        return -1;
    }
}
