package wilburhsu.CodingInterview;

public class _46_Accumulate {
    //用逻辑短路跳出递归
    public int Sum_Solution(int n) {
        int sum = n;
        boolean value = (n > 0) && (sum += Sum_Solution(n - 1)) > 0;
        return sum;
    }
}
