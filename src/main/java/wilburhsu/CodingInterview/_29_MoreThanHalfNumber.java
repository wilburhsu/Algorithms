package wilburhsu.CodingInterview;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _29_MoreThanHalfNumber {
    Map<Integer,Integer> map = new HashMap<>();
    //HashMap求解
    public int MoreThanHalfNum_Solution(int [] array) {
        if(array.length <= 0)
            return 0;

        for(int i = 0;i< array.length;i++){
            if(!map.containsKey(array[i]))
                map.put(array[i],1);
            else{
                int count = map.get(array[i]).intValue();
                count++;
                map.put(array[i],count);
            }
        }

        int half = array.length/2;
        int value = 0;
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            value = entry.getValue();
            if(value > half)
                return value;
        }
        return value;
    }

    //数组排序后求解
    public int MoreThanHalfNum_Solution2(int [] array) {
        if(array.length <= 0)
            return 0;
        Arrays.sort(array);
        int mid = array.length / 2;
        int result = array[mid];
        int count = 0;
        for(int i = 0;i < array.length;++i)
            if(array[i] == result)
                count++;
        if(count > mid)
            return result;
        return 0;
    }

    //阵地攻守解法
    public int MoreThanHalfNum(int [] array) {
        if(array.length <= 0)
            return 0;

        int count = 1;
        int current = array[0];
        for(int i = 0;i < array.length;++i){
            if(count != 0){
                if(array[i] == current)
                    count++;
                else
                    count--;
            }else {
                current = array[i];
                count = 1;
            }
        }

        int half = array.length / 2;
        count = 0;
        for(int i = 0;i < array.length;++i)
            if(array[i] == current)
                count++;
        if(count > half)
            return current;

        return 0;
    }
}
