package wilburhsu.LeetCode;

import java.util.ArrayList;
import java.util.List;

public class _54_SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if(matrix == null || matrix.length == 0)
            return result;

        int row = matrix.length,col = matrix[0].length;
        int left = 0,right = col - 1;
        int top = 0,bottom = row - 1;
        while(left <= right && top <= bottom){
            for(int i = left;i <= right;++i)
                result.add(matrix[top][i]);

            for(int i = top + 1;i <= bottom;++i)
                result.add(matrix[i][right]);

            if(top != bottom)
                for(int i = right - 1;i >= left;--i)
                    result.add(matrix[bottom][i]);

            if(left != right)
                for(int i = bottom - 1;i > top;--i)
                    result.add(matrix[i][left]);

            top++;
            bottom--;
            left++;
            right--;
        }
        return result;
    }
}
