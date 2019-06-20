package CodingInterview;

public class _10_NumberOf1InBinary {
    public int NumberOf1(int n) {
        int count = 0;
        while(n!=0){
            count++;
            n = (n-1)&n;
        }
        return count;
    }
}
