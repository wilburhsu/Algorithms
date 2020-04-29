package wilburhsu.CodingInterview;

/**
 * 第12题:打印1到最大的n位数
 * 输入数字n，按顺序打印出从1到最大的n位十进制数。比如输入3，则打印出1、2、3一直到最大的3位数999。
 * */

public class _12_Print1ToMaxOfNDiigts {
    public int[] printNumbers(int n) {
        if(n <= 0)
            return null;

        char[] chars = new char[n];
        for(int i = 0;i < n;++i)
            chars[i] = '9';
        String str = String.valueOf(chars);
        int maxNum = Integer.parseInt(str);

        int[] arr = new int[maxNum];
        for(int i = 0;i < maxNum;++i)
            arr[i] = i + 1;
        return arr;
    }

    public void print1ToMaxOfNDigits2(int n){
        int[] array = new int[n];
        if(n <= 0)
            return;
        printArray(array,0);
    }

    private void printArray(int[] array,int n){
        for(int i = 0; i < 10;i++){
            if(n != array.length){
                array[n] = i;
                printArray(array,n+1);
            }else {
                boolean isFirstNum0 = false;
                for(int j = 0;j<array.length;j++){
                    if(array[j] != 0){
                        System.out.print(array[j]);
                        if(!isFirstNum0)
                            isFirstNum0 = true;
                    }else {
                        if(isFirstNum0)
                            System.out.print(array[j]);
                    }
                }
                System.out.println();
                return;
            }
        }
    }

    public static void main(String[] args) {
        _12_Print1ToMaxOfNDiigts digit = new _12_Print1ToMaxOfNDiigts();
//        digit.print1ToMaxOfNDigits2(3);

        System.out.println("<----->" + digit.printNumbers(1));
    }
}
