package wilburhsu.LeetCode;

public class _27_RemoveElement {

    /**
     * 当 nums[j] 与给定的值相等时，递增 j 以跳过该元素。只要 nums[j] ≠ val，
     * 我们就复制 nums[j] 到 nums[i] 并同时递增两个索引。
     * 重复这一过程，直到 j 到达数组的末尾，该数组的新长度为 i。
     */
    //该方法不改变数组中元素原本的顺序
    public int removeElement(int[] nums, int val) {
        if(nums.length == 0)
            return 0;
        int i = 0;
        for(int j = 0;j < nums.length;j++){
            if(nums[j] != val){
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

    /**
     * 当我们遇到 nums[i] = valnums[i]=val 时，
     * 我们可以将当前元素与最后一个元素进行交换，并释放最后一个元素。这实际上使数组的大小减少了 1。
     */
    //该方法会改变数组中元素原本的顺序
    public int removeElement2(int[] nums, int val) {
        int i = 0;
        int n = nums.length;
        while(i < n){
            if(nums[i] == val){
                nums[i] = nums[n-1];
                n--;
            }else{
                i++;
            }
        }
        //此处返回i和n都是能AC的
        return i;
    }
}
