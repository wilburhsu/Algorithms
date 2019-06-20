package CodingInterview;

import java.util.Arrays;

/**
 * 第二版第21题：调整数组顺序使奇数位于偶数前面
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于位于数组的后半部分。
 * 注意：此题牛客网OJ要求保证奇数和奇数，偶数和偶数之间的相对位置不变
 * */

public class _14_ReorderArray {

    public void reOrderArray(int [] array) {
        if(array == null || array.length == 0)
            return;

        for(int i = 1;i < array.length;i++){
            int j = i-1;//j指向当前被判断值的前一个值
            if(array[i] % 2 != 0){//i指向的为奇数
                while(j >= 0){
                    if(array[j] % 2 != 0)
                        break;
                    if(array[j] % 2 == 0){
                        int tmp = array[j+1];
                        array[j+1] = array[j];
                        array[j] = tmp;
                        j--;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        _14_ReorderArray test = new _14_ReorderArray();
        int[] array = {1,2,3,4,5,6,7};
        System.out.println(Arrays.toString(array));
        test.reOrderArray(array);
        System.out.println(Arrays.toString(array));
    }

}
