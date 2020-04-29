package wilburhsu.CodingInterview;

import java.util.LinkedHashMap;
import java.util.Map;

public class _55_FirstUniqChar {

    public char firstUniqChar(String s) {
        if(null == s || s.length() == 0)
            return ' ';
        Map<Character,Integer> map = new LinkedHashMap<>();
        for(int i = 0;i < s.length();++i){
            Character c = Character.valueOf(s.charAt(i));
            if(!map.containsKey(c))
                map.put(c,1);
            else{
                int count = map.get(c).intValue();
                count++;
                map.put(c,count);
            }
        }
        for(Map.Entry<Character,Integer> entry : map.entrySet()){
            if(entry.getValue().intValue() == 1)
                return entry.getKey();
        }
        return ' ';
    }

    public static void main(String[] args) {
        String s = "leetcode";
        _55_FirstUniqChar firstUniqChar = new _55_FirstUniqChar();
        

    }
}

