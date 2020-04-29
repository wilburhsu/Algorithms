package wilburhsu.CodingInterview;

public class _49_StringToInt{
    //牛客网题解
    public int strToInt(String str) {
        int flag = 0;
        if(str == null || str.length() == 0)
            return 0;
        if(str.length() == 1){
            if(str.charAt(0) < '0' || str.charAt(0) > '9'){
                return 0;
            }else
                return str.charAt(0) - '0';
        }
        int res = 0;
        if(str.length() > 1){
            if(str.charAt(0) == '+')
                flag = 1;
            else if(str.charAt(0) == '-')
                flag = 2;
            int start = flag > 0 ? 1 : 0;
            for(int i = start; i < str.length();i++){
                if(str.charAt(i) < '0' || str.charAt(i) > '9')
                    return 0;
                res = res * 10 + str.charAt(i) - '0';
            }
        }
        if((flag == 1 && res > Integer.MAX_VALUE)
                || (flag == 2 && res < Integer.MIN_VALUE)) {
            flag = 0;
            return 0;
        }
        return flag == 2 ? -res : res;
    }

    //LeetCode题解
    public int strToInt2(String str) {
        int flag = 0;//全局变量，若为0则是非法输入，区分0和非法输入
        if(str == null || str.length() == 0)
            return 0;
        //字符串长度为1时的单独判断，区别单独一个“+”或“-”与有符号整数
        if(str.length() == 1){
            if(str.charAt(0) < '0' || str.charAt(0) > '9'){
                return 0;
            }else
                return str.charAt(0) - '0';
        }
        int res = 0;
        if(str.length() > 1){
            int start = 0;
            while(str.charAt(start) == ' '){
                start++;
                if(start >= str.length())
                    return 0;
            }

            if(str.charAt(start) == '+')
                flag = 1;//1表示有符号正数
            else if(str.charAt(start) == '-')
                flag = 2;//2表示有符号负数
            start = flag > 0 ? ++start : start;//有符号整数从第[1]位开始转换
            for(int i = start; i < str.length();i++){
                if(str.charAt(i) < '0' || str.charAt(i) > '9')
                    break;
                //字符转整数的计算方法str.charAt(i) - '0'
                int num = str.charAt(i) - '0';
                //判断是否溢出
                if(res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && num > 7)){
                    return flag == 2 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
                res = res * 10 + num;
            }
        }
        return flag == 2 ? -res : res;
    }

    public static void main(String[] args) {
        _49_StringToInt stringToInt = new _49_StringToInt();
        System.out.println(stringToInt.strToInt2("  "));
    }
}

