package wilburhsu.LeetCode;

public class _28_ImplementstrStr {
    public int strStr(String haystack, String needle) {
        if(needle.length() == 0)
            return 0;
        //！！！注意i的取值范围
        for(int i = 0;i < haystack.length() - needle.length() + 1;i++){
            if(haystack.charAt(i) == needle.charAt(0)){
                int j = 0;
                for(;j < needle.length();j++){
                    if(haystack.charAt(i + j) != needle.charAt(j))
                        break;
                }
                //取到符合条件的位置后，直接返回，不然有可能取到的不是第一次出现的位置
                if(j == needle.length())
                    return i;
            }
        }
        return -1;
    }
}

/*  最初的一版写法，存在的问题如下：
    public int strStr(String haystack, String needle) {
        if(needle.length() == 0)
            return 0;
        int index = -1;
        //外层循环i的取值范围不当，会导致haystack.charAt(i + j)溢出
        for(int i = 0;i < haystack.length();i++){
            if(haystack.charAt(i) == needle.charAt(0)){
                int j = 0;
                for(;j < needle.length();j++){
                    //这里haystack.charAt(i + j)会边界溢出
                    if(haystack.charAt(i + j) != needle.charAt(j))
                        break;
                }
                //获取到符合条件的索引位置后没有立即返回，导致返回的是最后一次出现的位置索引
                if(j == needle.length())
                    index = i;
            }
        }
        return index;
    }
*/
