package wilburhsu.LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class _13_RomanToInteger {
    //解法一：HashMap，占用额外空间，效率较低
    public int romanToInt(String s) {
        Map<String,Integer> map = new HashMap<>();
        map.put("I",1);
        map.put("V",5);
        map.put("X",10);
        map.put("L",50);
        map.put("C",100);
        map.put("D",500);
        map.put("M",1000);
        map.put("IV",4);
        map.put("IX",9);
        map.put("XL",40);
        map.put("XC",90);
        map.put("CD",400);
        map.put("CM",900);
        int ans = 0;
        for(int i = 0;i < s.length(); ){
            if(i + 1 < s.length() && map.containsKey(s.substring(i,i+2))){
                ans += map.get(s.substring(i,i+2));
                i += 2;
            }else{
                ans += map.get(s.substring(i,i+1));
                i++;
            }
        }
        return ans;
    }

    /**
     * 解法二：高效解法
     * 1.定义一个获取罗马字符对应数字的静态方法，使用switch来获取字符对应数字
     * 2.遍历从第一个字符开始，比较当前字符与下一字符对应数字大小，大于等于则加，否则减，最后一个字符直接加
     * */
    public static int getValue(char c){
        switch (c){
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                throw  new IllegalArgumentException("Illegal character");
        }
    }

    public int romanToInt2(String s){
        int ans = 0;
        for(int i = 0;i < s.length();i++){
            int current = getValue(s.charAt(i));
            //在条件语句（java/c）中，使用‘&&’时，若第一项为false，则判别为false，若为true，则判断下一项。
            //‘||’原则与‘&&’相同
            //所以可避免charAt越界
            if(i == s.length() - 1 || current >= getValue(s.charAt(i + 1)))
                ans += current;
            else
                ans -= current;
        }
        return ans;
    }
}
