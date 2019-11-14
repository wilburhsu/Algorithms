package wilburhsu.LeetCode;

public class _33_SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0)
            return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] == target)
                return mid;

            if(nums[left] <= nums[mid] ){//左半部分是升序
                
            }else{

            }
        }
        return -1;
    }
}
