package wilburhsu.LeetCode;

public class _26_RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0)
            return 0;
        int i = 0;
        for(int j = 1;j < nums.length;j++){
            if(nums[i] != nums[j])
                nums[++i] = nums[j];
        }
        return i+1;
    }

    //初始写法
    public int removeDuplicates2(int[] nums) {
        if(nums.length == 0)
            return 0;
        if(nums.length == 1)
            return 1;
        int i = 0;
        int j = 1;
        while(j < nums.length){
            if(nums[i] == nums[j])
                j++;
            else if(nums[i] < nums[j]){
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}
