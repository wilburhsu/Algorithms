package wilburhsu.LeetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class _20_ValidParentheses {
    /**位运算判断奇偶
     **if(i & 1){
     *    //i是奇数情况执行的代码
     *}
     *else{
     *    //i是偶数情况执行的代码
     *}
     */
    public boolean isValid(String s) {
        if(s.length() == 0)
            return true;
        if((s.length() & 1) == 1)
            return false;
        Map<Character,Character> map = new HashMap<>();
        map.put(')','(');
        map.put(']','[');
        map.put('}','{');

        Stack<Character> stack = new Stack<>();
        for(int i=0;i < s.length();i++){
            char c = s.charAt(i);
            if(map.containsKey(c)){
                char topElement = stack.isEmpty() ?'#':stack.pop();
                if(topElement != map.get(c))
                    return false;
            }else {
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }
}
