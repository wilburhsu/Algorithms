package wilburhsu.CodingInterview;

import java.util.Arrays;
import java.util.Comparator;

/**输入一个正整数数组，把数组里所有数字拼接起来排成一个数，
 * 打印能拼接出的所有数字中最小的一个。例如输入数组{3，32，321}，
 * 则打印出这三个数字能排成的最小数字为321323。
 * */
public class _33_PrintMinNumber {
    public String printMinNumber(int[] numbers) {
//        if(numbers.length <= 0)
//            return null;//❌返回null无法通过所有测试用例
        //长度为0应该返回"";

        StringBuffer buffer = new StringBuffer();
        int length = numbers.length;
        String[] strArray = new String[length];
        for(int i = 0;i < length;++i)
            strArray[i] = String.valueOf(numbers[i]);
        //自定义比较器
        Arrays.sort(strArray,new Comparator<String>(){
            @Override
            /* 按字典序对c1，c2进行排序
            *  若c1排在c2之后（即c1 > c2，ab > ba），则返回1，那么a > b也即a排在b之后
            *  同理若c1排在c2之前（即c1 < c2，ab < ba），则返回0，那么a < b也即a排在b之前
            *  举例：a=321,b=32，ab=32132,ba=32321,ab<ba,则a排在b前面
            * */
            public int compare(String a, String b) {
                String c1 = a + b;
                String c2 = b + a;
                return c1.compareTo(c2);
            }
        });

        for(int i = 0; i < strArray.length;++i)
            buffer.append(strArray[i]);
        return buffer.toString();
    }
}
