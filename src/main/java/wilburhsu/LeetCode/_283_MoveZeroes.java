package wilburhsu.LeetCode;

public class _283_MoveZeroes {
    /*
    * 思路：要把0移动到数组后面，就是把非0数给移动到数组前面，
    * 每个非0数需要移动的步数就是这个非0数前面0的个数。
    * 例如：[0, 1, 0, 3, 2]
    * 1需要移动1步，3和2都需要移动2步
    * 设非0数前面的0为count个，其自己在数组中的索引位置为i，
    * 因此依次将非0数与其移动后应该所处位置（i-count）上的数进行交换
    * */
    public void moveZeroes(int[] nums) {
        if(nums.length == 0)
            return;
        int count = 0;
        for(int i = 0;i < nums.length;++i){
            if(nums[i] == 0)
                count++;
            else{
                int tmp = nums[i - count];
                nums[i - count] = nums[i];
                nums[i] = tmp;
            }
        }
    }
}
