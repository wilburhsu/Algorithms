package wilburhsu.LeetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _169_MajorityElement {
    //解法1：哈希表
    Map<Integer,Integer> map = new HashMap<>();
    public int majorityElement(int[] nums) {
        if(nums.length <= 0)
            return -1;

        for(int i = 0;i < nums.length;i++){
            if(!map.containsKey(nums[i]))
                map.put(nums[i],1);
            else
                map.put(nums[i], map.get(nums[i]).intValue() + 1);

        }

        int half = nums.length/2;
        for(Map.Entry<Integer,Integer> entry: map.entrySet()){
            int key = entry.getKey();
            int value = entry.getValue();
            if(value > half)
                return key;
        }
        return 0;
    }

    //解法2：排序
    public int majorityElement2(int[] array) {
        if(array.length <= 0)
            return 0;
        Arrays.sort(array);
        int half = array.length/2;
        int result = array[half];
        int count = 0;
        for(int i = 0;i < array.length;i++){
            if(array[i] == result)
                count++;
        }
        if(count > half)
            return result;
        return 0;
    }

    //解法3：摩尔投票算法
    public int majorityElement3(int[] array) {
        if(array.length <= 0)
            return 0;
        int num = array[0], count = 1;
        for (int i = 1; i < array.length; i++) {
            if (array[i] == num)
                count++;
            else
                count--;
            if (count == 0) {
                num = array[i];
                count = 1;
            }
        }
        // Verifying
        count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == num)
                count++;
        }
        if (count > array.length/2)
            return num;
        return 0;
    }
}
