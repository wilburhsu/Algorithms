package wilburhsu;

import java.util.ArrayList;
import java.util.Stack;



public class Solution {
    public int reverse(int x) {
        if(x < Integer.MIN_VALUE || x > Integer.MAX_VALUE)
            return 0;
        while(x%10 == 0)
            x = x/10;
        String number = String.valueOf(x);
        char[] chars = number.toCharArray();
        int i = 0;
        if(chars[0] == '+' || chars[0] == '-')
            i = 1;
        int j = chars.length - 1;
        char tmp = ' ';
        while(i <= j){
            tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
            i++;
            j--;
        }
        return Integer.valueOf(String.valueOf(chars)).intValue();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.reverse(123000));
    }
}
