package wilburhsu.LeetCode;

public class _189_RotateArray {
    //解法1：翻转前半部分数组，再翻转后半部分，最后翻转整个数组
    public void rotate(int[] array,int k){
        k %= array.length;
        reverse(array,0,k-1);
        reverse(array,k,array.length-1);
        reverse(array,0,array.length-1);
    }

    public void reverse(int[] array,int start,int end){
        int tmp = 0;
        while(start <= end){
            tmp = array[start];
            array[start] = array[end];
            array[end] = tmp;
            start++;
            end--;
        }
    }

    //解法2：环状替换
    public void rotate2(int array[],int k){
    //Todo

    }

}
