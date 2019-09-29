package wilburhsu.LeetCode;

public class _35_SearchInsertPosition {
    //解法一：二分查找
    //📌排序元素中的查找，应该立即想到最高效的二分查找
    public int searchInsert(int[] nums, int target){
        int low = 0;
        int high = nums.length - 1;
        while(low <= high){
            int mid = low + ((high - low)>>1);
            if(target == nums[mid])
                return mid;
            if(target < nums[mid])
                high = mid - 1;
            else
                low = mid + 1;
        }
        return low;
    }

    //解法二：线性搜索（不推荐）
    public int searchInsert2(int[] nums, int target) {
        if(nums.length == 0)
            return 0;
        if(target <= nums[0])
            return 0;
        if(target > nums[nums.length - 1])
            return nums.length;
        if(target == nums[nums.length - 1])
            return nums.length - 1;
        for(int i = 0;i < nums.length - 1;i++){
            if(nums[i] == target)
                return i;
            if(target > nums[i] && target < nums[i+1])
                return i+1;
        }
        return 0;
    }
}
