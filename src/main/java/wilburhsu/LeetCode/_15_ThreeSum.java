package wilburhsu.LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _15_ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length < 3)
            return result;
        Arrays.sort(nums);
        int target = 0,len = nums.length;
        for(int i = 0;i < len;++i){
            if(nums[i] > 0)
                break;
            List<Integer> tmpResult = new ArrayList<>();
            int prev = i + 1,last = len - 1;
            while(prev < last){
                int tmpSum = nums[i] + nums[prev] + nums[last];
                if(tmpSum > target)
                    last--;
                else if(tmpSum < target)
                    prev++;
                else if(tmpSum == target){
                    tmpResult.add(nums[i]);
                    tmpResult.add(nums[prev]);
                    tmpResult.add(nums[last]);
                    result.add(tmpResult);
                }
            }
        }
        return result;
    }
}
