package wilburhsu.CodingInterview;

import java.util.ArrayList;

public class _41_1_TwoNumbersWithSum {
    public ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {
        ArrayList<Integer> result = new ArrayList<>();
        if(array == null || array.length < 2)
            return result;
        int left = 0 ,right = array.length - 1;
        while(left < right){
            if(array[left] + array[right] == sum){
                result.add(array[left]);
                result.add(array[right]);
                return result;
            }else if(array[left] + array[right] > sum)
                right--;
            else
                left++;
        }
        return result;
    }
}
