package CodingInterview;

/**
 * 面试题3：二位数组中的查找
 * */


public class _3_FindInPartiallySortedMatrix {
    public static boolean find(int[][] matrix,int target){
        if(matrix == null)
            return false;

        int row = 0;//行
        int column = matrix[0].length - 1;//列

        while (row < matrix.length && column >= 0){
            if(matrix[row][column] == target){
                return true;
            }

            if(matrix[row][column] > target)
                column--;
            else
                row++;
        }
        return false;
    }
}
