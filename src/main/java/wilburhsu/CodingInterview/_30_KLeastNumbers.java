package wilburhsu.CodingInterview;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;
public class _30_KLeastNumbers {
    HashSet<Integer> set = new HashSet<>();
    ArrayList<Integer> resultList = new ArrayList<>();
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        if(input.length <= 0 || k <= 0 || k > input.length)
            return resultList;
        for(int i = 0;i < input.length;i++){
            set.add(input[i]);
        }
        resultList.addAll(set);
        Collections.sort(resultList);
        while(resultList.size() > k){
            resultList.remove(resultList.size() - 1);
        }
        return resultList;
    }
}
