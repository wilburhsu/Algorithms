package wilburhsu.CodingInterview;

public class _54_NumericStrings {
    //这个版本有许多特殊情况不能通过，比如单字符"+"、"-"
    public boolean isNumeric(char[] str) {
        int len = str.length;
        boolean sign = false, decimal = false, hasE = false;
        for(int i = 0; i < len; i++){
            if(str[i] == '+' || str[i] == '-'){
                if(!sign && i > 0 && str[i-1] != 'e' && str[i-1] != 'E')
                    return false;
                if(sign && str[i-1] != 'e' && str[i-1] != 'E')
                    return false;
                sign = true;
            }else if(str[i] == 'e' || str[i] == 'E'){
                if(i == len - 1)
                    return false;
                if(hasE)
                    return false;
                hasE = true;
            }else if(str[i] == '.'){
                if(hasE || decimal)
                    return false;
                decimal = true;
            }else if(str[i] < '0' || str[i] > '9')
                return false;
        }
        return true;
    }

    //完善版
    public boolean isNumeric2(char[] str) {
        if(str == null)
            return false;

        boolean sign = false, decimal = false, hasE = false;  //'+''-'符号、小数点和指数符号出现标志
        for(int i = 0; i < str.length; i++) {
            if(str[i] == '+' || str[i] == '-') {
                //第一次出现+''-'符号只能在第一个字符或者指数符号后一位
                if(!sign && i > 0 && str[i-1] != 'e' && str[i-1] != 'E')
                    return false;
                if(sign && str[i-1] != 'e' && str[i-1] != 'E')
                    return false;  //第二出现'+''-'符号只能在指数符号后一位
                if(i == str.length - 1)
                    return false;  //'+''-'符号不能出现在最后一位上
                sign = true;
            }
            else if(str[i] == 'e' || str[i] == 'E' ) {
                if(i == 0)
                    return false;  //指数符号前必须有整数
                if(i == str.length - 1)
                    return false;  //指数符号后必须有整数
                if(hasE)
                    return false;  //只能有一个指数符号
                hasE = true;
            }
            else if(str[i] == '.') {
                if(hasE)
                    return false;  //小数点只能出现在指数符号之前
                if(i == str.length - 1)
                    return false;  //小数点不能出现在最后一位上
                if(decimal)
                    return false;  //小数点只能出现一次
                decimal = true;
            }
            else if(str[i] < '0' || str[i] > '9')
                return false;  //其它的非数字字符
        }
        return true;
    }

    public boolean isNumeric3(char[] str) {
        String string = String.valueOf(str);
        return string.matches("[\\+-]?[0-9]*(\\.[0-9]*)?([eE][\\+-]?[0-9]+)?");
    }

    public static void main(String[] args) {
        _54_NumericStrings numericStrings = new _54_NumericStrings();
        char[] str  = {'1','2','e','-'};
        //char[] str = {'+','2','.','6','e','1','-','2'};
        System.out.println("<========>" + numericStrings.isNumeric(str));
        System.out.println("<++++++++>" + numericStrings.isNumeric2(str));
        System.out.println("<########>" + numericStrings.isNumeric3(str));
    }
}
