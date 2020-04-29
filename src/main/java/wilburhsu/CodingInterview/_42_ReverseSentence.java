package wilburhsu.CodingInterview;

public class _42_ReverseSentence {
    //此方法Leetcode通过
    public String reverseWords(String s) {
        String[] tmp = s.split(" ");
        StringBuffer result = new StringBuffer();
        for(int i = tmp.length - 1;i >=0 ;i--){
            if(!tmp[i].equals("")){
                result.append(tmp[i]);
                result.append(" ");
            }
        }
        return result.toString().trim();
    }

    public static void main(String[] args) {
        String str = " example   space  ";
        String[] arr = str.split(" ");
        for (String s : arr)
            System.out.print(s+",");
    }

}
