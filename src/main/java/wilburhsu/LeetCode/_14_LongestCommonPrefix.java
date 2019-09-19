package wilburhsu.LeetCode;

public class _14_LongestCommonPrefix {
    //解法一：水平扫描
    public String longestCommonPrefix(String[] strs) {
        if(strs.length <= 0)
            return new String("");
        String prefix = strs[0];
        for(int i = 1;i < strs.length;i++){
            while(strs[i].indexOf(prefix) != 0){
                //substring返回的值不包含endIndex，所以这一步每次将prefix减少1位
                prefix = prefix.substring(0,prefix.length() - 1);
                if(prefix.isEmpty())
                    return new String("");
            }
        }
        return prefix;
    }

    //解法二：垂直扫描
    public String longestCommonPrefixVertical(String[] strs){
        if(strs.length <= 0)
            return new String("");
        for(int i = 0;i < strs[0].length();i++){
            char c = strs[0].charAt(i);
            for(int j = 1;j < strs.length;j++){
                if(i == strs[j].length() || strs[j].charAt(i) != c)
                    return strs[0].substring(0,i);
            }
        }
        return strs[0];
    }
}
