package wilburhsu.LeetCode;

public class _48_RotateImage {
    public void rotate(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix.length == 1)
            return;
        int top = 0,bottom = matrix.length - 1;
        //水平翻转矩阵
        while(top < bottom){
            int[] tmp = matrix[top];
            matrix[top] = matrix[bottom];
            matrix[bottom] = tmp;
            top++;
            bottom--;
        }

        //对角线翻转矩阵
        int index = 0;
        while(index < matrix.length){
            int i = index,j = index;
            while(i < matrix.length && j < matrix[index].length){
                int tmp = matrix[index][j];
                matrix[index][j] = matrix[i][index];
                matrix[i][index] = tmp;
                i++;
                j++;
            }
            index++;
        }
    }
}
