package wilburhsu.LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class _56_MergeIntervals {
    public int[][] merge(int[][] intervals) {
        if(intervals == null || intervals.length == 0)
            return intervals;
        Arrays.sort(intervals, new Comparator<int[]>(){
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        List<int[]> res = new ArrayList<>();
        for(int i = 0;i < intervals.length;++i){
            int low = intervals[i][0];
            int high = intervals[i][1];
            while (i < intervals.length - 1 && intervals[i + 1][0] <= high) {
                i++;
                high = Math.max(high, intervals[i][1]);
            }
            res.add(new int[]{low, high});
        }
        return res.toArray(new int[0][]);
    }

    public int[][] merge2(int[][] intervals) {
        if(intervals == null || intervals.length == 0)
            return intervals;
        Arrays.sort(intervals, new Comparator<int[]>(){
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        List<int[]> res = new ArrayList<>();
        int low = intervals[0][0];
        int high = intervals[0][1];
        for(int i = 1;i < intervals.length;++i){
            if(intervals[i][0] <= high)
                high = Math.max(high, intervals[i][1]);
            else if(intervals[i][0] > high){
                res.add(new int[]{low,high});
                low = intervals[i][0];
                high = intervals[i][1];
            }
        }
        res.add(new int[]{low, high});
        int[][] result = new int[res.size()][2];
        for (int i = 0; i < res.size(); i++) {
            result[i] = res.get(i);
        }
        return result;
    }
}
