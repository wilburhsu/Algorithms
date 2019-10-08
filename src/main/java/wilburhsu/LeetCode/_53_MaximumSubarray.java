package wilburhsu.LeetCode;

public class _53_MaximumSubarray {
    public int maxSubArray(int[] nums){
        if(nums == null || nums.length == 0)
            return 0;
        int index = nums[0];
        int max = nums[0];
        for(int i = 1;i < nums.length;i++){
            if(index > 0)
                index += nums[i];
            else
                index = nums[i];
            if(index > max);
                max = index;
        }
        return max;
    }
}
