package wilburhsu.LeetCode;

public class _28_ImplementstrStr {
    public int strStr(String haystack, String needle) {
        int index = 0;
        for(int i = 0;i < haystack.length();i++){
            for(int j = 0;j < needle.length();j++){
                if(haystack.charAt(i) != needle.charAt(j))
                    break;
                if(j == needle.length() - 1)
                    index = i;
            }
        }
        return index;
    }
}
