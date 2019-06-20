package CodingInterview;

public class _66_StringPathInMatrix {
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str){
        //参数校验
        if(matrix == null || matrix.length == 0 || matrix.length != rows * cols)
            return false;

        //初始化访问标记矩阵
        int[] flag = new int[rows * cols];

        //以每个点为起始点进行搜索
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(hasPathCore(matrix,rows,cols,i,j,str,0,flag))
                    return true;
            }
        }
        return false;
    }

    /**
     * 回溯搜索算法
     * @param matrix 输入矩阵
     * @param rows 矩阵行数
     * @param cols 矩阵列数
     * @param i 当前处理的行数
     * @param j 当前处理的列数
     * @param str 待搜索的字符串
     * @param k 已经处理str中字符个数
     * @param flag 访问标记数组
     * @return true 存在路径， false 不存在路径
     */

    private boolean hasPathCore(char[] matrix,int rows,int cols,int i,int j,char[] str,int k,int[] flag) {
        int index = i * cols + j;
        if (i < 0 || i >= rows || j < 0 || j >= cols || matrix[index] != str[k] || flag[index] == 1)
            return false;

        if(k == str.length - 1)
            return true;
        flag[index] = 1;

        if (hasPathCore(matrix, rows, cols, i - 1, j, str, k + 1, flag)
                || hasPathCore(matrix, rows, cols, i + 1, j, str, k + 1, flag)
                || hasPathCore(matrix, rows, cols, i, j - 1, str, k + 1, flag)
                || hasPathCore(matrix, rows, cols, i, j + 1, str, k + 1, flag)) {
            return true;
        }

        //如果在上一步中找到在路径上的点，flag[index]不会被重新置为0
        flag[index] = 0;
        return false;
    }

    public static void main(String[] args) {
        _66_StringPathInMatrix path = new _66_StringPathInMatrix();
        System.out.println(path.hasPath("ABCEHJIGSFCSLOPQADEEMNOEADIDEJFMVCEIFGGS".toCharArray(),
                5, 8, "SGGFIECVAASABCEEJIGOEM".toCharArray()));
    }


}
