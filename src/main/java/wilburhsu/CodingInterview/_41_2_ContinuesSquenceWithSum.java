package wilburhsu.CodingInterview;

import java.util.ArrayList;

public class _41_2_ContinuesSquenceWithSum {
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(sum < 3)
            return result;
        //两个起点，相当于动态窗口的两边，根据其窗口内的值的和来确定窗口的位置和大小
        int start = 1,end = 2;
        while(start < end){
            //等差数列求和：首项加末项乘以项数除以2
            int tmpSum = (start + end)*(end - start + 1)/2;
            if(tmpSum == sum){
                ArrayList list = new ArrayList<>();
                for(int i = start;i <= end;i++)
                    list.add(i);
                result.add(list);
                start++;
                //如果当前窗口内的值之和小于sum，那么右边窗口右移一下
            }else if(tmpSum < sum)
                end++;
                //如果当前窗口内的值之和大于sum，那么左边窗口右移一下
            else
                start++;
        }
        return result;
    }
}
