package wilburhsu.CodingInterview;

public class _9_Fibonacci {
    public int Fibonacci(int n) {
        if(n <= 0)
            return 0;
        if(n == 1)
            return 1;

        int temp1 = 0;//f(n-2)的值
        int temp2 = 1;//f(n-1)的值
        int result = 0;
        for(int i = 2;i <= n;i++){
            result = temp1 + temp2;
            temp1 = temp2;
            temp2 = result;
        }
        return result;
    }
}
