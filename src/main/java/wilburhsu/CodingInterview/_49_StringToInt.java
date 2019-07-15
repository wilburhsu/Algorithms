package wilburhsu.CodingInterview;

public class _49_StringToInt{
    public int StrToInt(String str) {
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

    public static void main(String[] args) {
        _49_StringToInt stringToInt = new _49_StringToInt();
        System.out.println(stringToInt.StrToInt("+"));
    }
}

