package wilburhsu.LeetCode;

import java.util.*;

public class _217_ContainsDuplicate {
    //HashSet解法
    public boolean containsDuplicate(int[] nums){
        Set<Integer> set = new HashSet<>();
        for(int i = 0;i < nums.length;i++){
            if(set.contains(nums[i]))
                return true;
            set.add(nums[i]);
        }
        return false;
    }

    //排序解法
    public boolean containsDuplicate2(int[] nums){
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; ++i) {
            if (nums[i] == nums[i + 1]) return true;
        }
        return false;
    }
}
