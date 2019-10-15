package wilburhsu.LeetCode;

import java.util.ArrayList;
import java.util.List;

public class _118_PascalsTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if(numRows == 0)
            return result;
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        result.add(firstRow);
        for(int i = 1;i < numRows;i++){
            List<Integer> currentRow = new ArrayList<>(i + 1);
            List<Integer> lastRow = result.get(i - 1);
            currentRow.add(0,1);
            for(int j = 1;j < i;j++){
                currentRow.add(j,lastRow.get(j - 1) + lastRow.get(j));
            }
            currentRow.add(i,1);
            result.add(currentRow);
        }

        //初始版本，有错误
//        for(int i = 1;i < numRows;i++){
//            //设定ArrayList初始容量为i+1后，list的长度为已添加的元素的个数
//            List<Integer> currentRow = new ArrayList<>(i + 1);
//            List<Integer> lastRow = result.get(i - 1);
//            currentRow.add(0,1);
//            currentRow.add(currentRow.size() - 1,1);
//            //new一个ArrayList后，它为空，在上面两步中添加两个元素后，currentRow.size() == 2而不是i + 1;
//            //所以在下面的for循环中，j的范围不能为currentRow.size() - 1
//            for(int j = 1;j < currentRow.size() - 1;j++){
//                currentRow.add(j,lastRow.get(j - 1) + lastRow.get(j));
//            }
//            result.add(currentRow);
//        }
        return result;
    }

    public static void main(String[] args) {
        _118_PascalsTriangle pascalsTriangle = new _118_PascalsTriangle();
        System.out.println(pascalsTriangle.generate(5));
    }
}
