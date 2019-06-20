package CodingInterview;

public class _8_MinNumberInRotatedArray {
    public int minNumberInRotateArray(int [] array) {
        if(array.length <= 0)
            return 0;

        int low = 0;
        int high = array.length - 1;
        int mid = low;//mid初始化为low，是处理旋转数组是其本身的情况，此时直接返回array[mid]

        while(array[low] >= array[high]){
            if(high - low == 1){
                mid = high;
                break;
            }

            mid = low + (high - low)/2;
            if(array[low] == array[high] && array[mid] == array[low])
                return minInOrder(array,low,high);

            if(array[mid] >= array[low])
                low = mid;
            else if(array[high] >= array[mid])
                high = mid;
        }
        return array[mid];
    }

    public int minInOrder(int[] array,int low,int high){
        int result = array[low];
        for(int i = low;i <= high;i++){
            if(result > array[i])
                result = array[i];
        }
        return result;
    }

    public static void main(String[] args) {
        _8_MinNumberInRotatedArray number = new _8_MinNumberInRotatedArray();
        int[] array = {3,5,7,8,9,1,2};
        System.out.println(number.minNumberInRotateArray(array));
    }
}
