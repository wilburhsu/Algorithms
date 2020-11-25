package wilburhsu.LeetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class StringTest {

//    public int lengthOfLongestSubstring(String s) {
//        if(s.length() == 0 || s.length() == 1)
//            return s.length();
//
//        int fast = 0;
//        int max = 0;
//        int len = s.length();
//        Set<Character> set = new HashSet<>();
//        set.add(s.charAt(0));
//
//        for(int slow = 0;slow < len;++slow){
//            if (slow != 0) {
//                // 左指针向右移动一格，移除一个字符
//                set.remove(s.charAt(slow - 1));
//            }
//            while((fast < len) && !set.contains(s.charAt(fast))){
//                set.add(s.charAt(fast));
//                fast++;
//            }
//            max = Math.max(max, fast - slow);
//        }
//        return max;
//    }

    public int lengthOfLongestSubstring(String s) {
        if (s.length()==0) return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max = 0;
        int left = 0;
        for(int i = 0; i < s.length(); i ++){
            if(map.containsKey(s.charAt(i))){
                left = Math.max(left,map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i),i);
            max = Math.max(max,i-left+1);
        }
        return max;
    }

    public static void main(String[] args) {
        StringTest stringTest = new StringTest();
        String str = "qrsvbspk";
        int max = stringTest.lengthOfLongestSubstring(str);
        System.out.println(max);
    }
}
