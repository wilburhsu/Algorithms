package wilburhsu.LeetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class _268_MissingNumber {
    //方法1：排序
    public int missingNumber(int[] nums){
        Arrays.sort(nums);
        if(nums[0] != 0)
            return 0;
        //判断n是否出现在末位
        //例如[0,1,2]，3是缺失的数字
        if(nums[nums.length - 1] != nums.length)
            return nums.length;
        for(int i = 0;i < nums.length + 1;++i)
            if(nums[i] != i)
                return i;
        return -1;
    }

    //方法2：哈希表
    public int missingNumber2(int[] nums){
        Set<Integer> set = new HashSet<>();
        for(int num : nums)
            set.add(num);
        for(int i = 0;i < nums.length + 1;++i)
            if(!set.contains(i))
                return i;
        return -1;
    }

    //!!!方法3：位运算
    /**
     * 数组中有 n 个数，并且缺失的数在 [0..n] 中。
     * 因此我们可以先得到 [0..n] 的异或值，再将结果对数组中的每一个数进行一次异或运算。
     * 未缺失的数在 [0..n] 和数组中各出现一次，因此异或后得到 0。
     * 而缺失的数字只在 [0..n] 中出现了一次，在数组中没有出现，
     * 因此最终的异或结果即为这个缺失的数字。
     */
    public int missingNumber3(int[] nums){
        int missNum = nums.length;
        for(int i = 0;i < nums.length;++i)
            missNum ^= nums[i] ^ i;
        return missNum;
    }

    //方法4：数学
    /**在线性时间内可以求出数组中所有数的和，
     * 并在常数时间内求出前 n+1个自然数（包括 0）的和，
     * 将后者减去前者，就得到了缺失的数字。
     */
    public int missingNumber4(int[] nums){
        int expectedSum = nums.length * (nums.length + 1)/2;
        int actualSum = 0;
        for(int num : nums)
            actualSum += num;
        return expectedSum - actualSum;
    }

}
