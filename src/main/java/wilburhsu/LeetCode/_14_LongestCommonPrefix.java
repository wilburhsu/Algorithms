package wilburhsu.LeetCode;

public class _14_LongestCommonPrefix {
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
}
