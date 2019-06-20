package wilburhsu.CodingInterview;

import java.util.HashMap;
import java.util.Map;

public class _29_MoreThanHalfNumber {
    Map<Integer,Integer> map = new HashMap<>();
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
}
