package wilburhsu.LeetCode;

import java.util.Arrays;
import java.util.Comparator;

public class _56_MergeIntervals {
    public int[][] merge(int[][] intervals) {
        if(intervals == null || intervals.length == 0)
            return intervals;
        Arrays.sort(intervals, new Comparator<int[]>(){
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int low = intervals[0][0];
        int high = intervals[0][1];
        for(int i = 1;i < intervals.length;++i){
            
        }


        return null;
    }
}
