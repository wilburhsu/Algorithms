package wilburhsu.LeetCode;

public class _33_SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0)
            return -1;
        //初始数字一定满足nums[left] >= nums[rigth]
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid  = left + (right - left)/2;
            if(nums[mid] == target)
                return mid;
            if(nums[mid] >= nums[left]){//左半部分递增
                if(target >= nums[left] && target <= nums[mid])
                    right = mid - 1;
                else
                    left = mid + 1;
            }else if(nums[mid] <= nums[right]){//右半部分递增，这里的等号加不加都能过
                if(target <= nums[right] && target >= nums[mid])
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }
        return -1;
    }
}
