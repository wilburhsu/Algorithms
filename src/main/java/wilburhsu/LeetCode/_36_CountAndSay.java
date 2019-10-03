package wilburhsu.LeetCode;

public class _36_CountAndSay {
    public String countAndSay(int n) {
        //s是上一次循环中获得的报数序列，初始化为1，所以循环从1开始
        String s = "1";
        //i从1开始，不是从0开始，表示循环n-1次
        for(int i = 1;i < n;i++){
            StringBuilder builder = new StringBuilder();
            //count从1开始
            int count = 1;
            char pre  = s.charAt(0);
            //j从1开始
            for(int j = 1;j < s.length();j++){
                char c = s.charAt(j);
                if(c == pre)
                    count++;
                else {
                    builder.append(count).append(pre);
                    //处理完第j个字符后，将c、count重置为前一个字符的状态
                    pre = c;
                    count = 1;
                }
            }
            builder.append(count).append(pre);
            //第i次循环结束后获得第i个报数序列
            s = builder.toString();
        }
        return s;
    }

    public static void main(String[] args) {
        _36_CountAndSay countAndSay = new _36_CountAndSay();
        System.out.println(countAndSay.countAndSay(5));
    }
}
