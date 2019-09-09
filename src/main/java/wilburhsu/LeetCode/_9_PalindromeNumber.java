package wilburhsu.LeetCode;

public class _9_PalindromeNumber {
    /**
     * 数字技巧总结：
     * 假设数字为n位数（在int范围内）
     * 得到最小n位数div的方法如下：
     * while(x/div >= 10)
     *     div *= 10;
     * 在得到最小n位数之后，可得到数字的最高位为：x/div
     * 去掉最高位数字的方法：x%div
     * 得到数字最低位的方法：x%10
     * 去掉最低位数字的方法：x/10
     * */

    public boolean isPalindrome(int x) {
        if(x < 0)
            return false;
        int div = 1;
        //得到最小n位数的方法
        while(x/div >= 10)
            div *= 10;
        while (x > 0){
            int left = x / div;
            int right = x % 10;
            if(left != right)
                return false;
            x = (x % div) / 10;//去掉最高位后再去掉最低位
            div /= 100;
        }
        return true;
    }

    public boolean isPalindrome2(int x) {
        int pop = 0;
        while(pop <= x){
            pop = x%10;
        }

        return true;
    }

    public static void main(String[] args) {
        Object object = new Object();
        object.hashCode();
    }
}
