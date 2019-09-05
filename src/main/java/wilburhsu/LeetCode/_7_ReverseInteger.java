package wilburhsu.LeetCode;

public class _7_ReverseInteger {
    public int reverse(int x){
        int reverse = 0;
        while(x != 0){
            int pop = x % 10;
            x = x/10;
            if(x > Integer.MAX_VALUE/10 || x == Integer.MIN_VALUE/10 && pop > Integer.MIN_VALUE%10)
                return 0;
            if(x < Integer.MIN_VALUE/10 || x == Integer.MIN_VALUE/10 && pop < Integer.MIN_VALUE%10)
                return 0;
            reverse = reverse * 10 + pop;
        }
        return reverse;
    }
}
