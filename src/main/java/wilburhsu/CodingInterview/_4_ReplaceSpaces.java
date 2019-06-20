package wilburhsu.CodingInterview;

/**
 * 面试题5：替换空格
 * */

public class _4_ReplaceSpaces {
    public static String replaceSpace(StringBuffer str){
        if(str == null)
            return null;

        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < str.length(); i++){
            if(String.valueOf(str.charAt(i)).equals(" "))
                sb.append("%20");
            else
                sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    /**
     * @param str 新创建的数组
     * @param length 原始数组的长度
     * @return
     */
    public static String replaceSpaces(char[] str,int length){
        if(str == null)
            return null;

        int numberOfSpace = 0;
        for(int i = 0; i < str.length; i++){
            if(str[i]  == ' ')
                numberOfSpace++;
        }

        int newLength = length + 2 * numberOfSpace;
        int oldIndex = length - 1;
        int newIndex = newLength - 1;
        while(oldIndex >= 0 && newIndex > oldIndex){
            if(str[oldIndex--] != ' ')
                str[newIndex] = str[oldIndex--];
            else{
                str[newIndex--] = '0';
                str[newIndex--] = '2';
                str[newIndex--] = '%';
            }
        }
        return new String(str);
    }

    public static void main(String[] args) {
        String str = "We   are   happy  .";
        int length = str.length();
        char[] olderArray = str.toCharArray();
        // 为简单起见，我们假设给它一个新的空间，空间的大小足以存下替换后的字符
        char[] newArray = new char[100];
        for (int i = 0; i < olderArray.length; i++) {
            newArray[i] = olderArray[i];
        }
        String resultStr = replaceSpaces(newArray,length);
        System.out.println(resultStr);
    }
}


