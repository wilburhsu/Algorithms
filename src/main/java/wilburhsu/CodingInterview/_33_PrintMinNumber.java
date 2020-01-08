package wilburhsu.CodingInterview;

import java.util.Arrays;
import java.util.Comparator;

/**输入一个正整数数组，把数组里所有数字拼接起来排成一个数，
 * 打印能拼接出的所有数字中最小的一个。例如输入数组{3，32，321}，
 * 则打印出这三个数字能排成的最小数字为321323。
 * */
public class _33_PrintMinNumber {
    public String printMinNumber(int[] numbers) {
        if(numbers.length <= 0)
            return null;

        StringBuffer buffer = new StringBuffer();
        int length = numbers.length;
        String[] strArray = new String[length];
        for(int i = 0;i < length;++i)
            strArray[i] = String.valueOf(numbers[i]);
        //自定义比较器
        Arrays.sort(strArray,new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                String c1 = o1 + o2;
                String c2 = o2 + o1;
                return c1.compareTo(c2);
            }
        });

        for(int i = 0; i < strArray.length;++i)
            buffer.append(strArray[i]);
        return buffer.toString();
    }
}
