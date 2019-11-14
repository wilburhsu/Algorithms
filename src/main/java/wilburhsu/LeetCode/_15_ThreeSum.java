package wilburhsu.LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _15_ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int target = 0;
        if(nums == null || nums.length == 0)
            return result;
        Arrays.sort(nums);
        for(int i = 0;i < nums.length;++i){
            if(nums[i] > 0)
                break;
            if(i > 0 && nums[i] == nums[i-1])
                continue;
            int prev = i + 1,last = nums.length - 1;
            while(prev < last){
                int tmpSum = nums[i] + nums[prev] + nums[last];
                if(tmpSum < target)
                    prev++;
                else if(tmpSum > target)
                    last--;
                else if(tmpSum == target){
                    result.add(Arrays.asList(nums[i],nums[prev],nums[last]));
                    while(prev < nums.length - 1 && nums[prev] == nums[prev + 1])
                        prev++;
                    while(last > 0 && nums[last] == nums[last - 1])
                        last--;
                    prev++;
                    last--;
                }
            }
        }
        return result;
    }
}
