package wilburhsu.CodingInterview;

/**
 * 第二版面试题3：题目二——不修改数组找出重复的数字
 */

public class _51_DuplicationInArrayNoEdit {

    //思路1：通过辅助数组解决，空间复杂度O(N)
    public static int getDuplicationFunction(int[] numbers){
        if(numbers == null || numbers.length <= 0)
            return -1;

        for(int i = 0;i < numbers.length; i++){
            if(numbers[i] < 0 || numbers[i] > numbers.length-1)
                return -1;
        }

        int[] tmpArr = new int[numbers.length];
        for(int i = 0; i < numbers.length; i++){
            if(tmpArr[numbers[i]] == numbers[i])
                return numbers[i];
            tmpArr[numbers[i]] = numbers[i];
        }
        return -1;
    }

    //思路2 时间复杂度O(NlogN) 空间复杂度O(1)
    public static int getDuplication(int[] numbers){
        if(numbers == null || numbers.length<=0)
            return -1;

        for(int i = 0;i < numbers.length; i++){
            if(numbers[i] < 1 || numbers[i]>numbers.length-1)
                return -1;
        }

        int start = 1;
        int end = numbers.length-1;

        while (start <= end){
            int mid = (start + end)/2;
            int count = countRange(numbers,start,mid);

            if(start == end){
                if(count > 1)
                    return start;
                else
                    break;
            }

            if(count > (mid-start+1))
                end = mid;
            else
                start = mid + 1;
        }
        return -1;
    }

    public static int countRange(int[] numbers,int start,int end){
        if(numbers == null)
            return 0;

        int count = 0;
        for(int i = 0;i<numbers.length;i++)
            if(numbers[i] >= start && numbers[i] <= end)
                count++;
        return count;

    }

//    public static int getDuplication(int[] numbers){
//        if(numbers == null || numbers.length <=0)
//            return -1;
//
//        for(int i = 0;i<numbers.length;i++){
//            if(numbers[i] < 1 || numbers[i] >= numbers.length)
//                return -1;
//        }
//
//        int start = 1;
//        int end = numbers.length-1;
//        int count = 0;
//
//        while(start <= end){
//            int mid = (start+end)/2;
//        }
//
//        if(numbers == null)
//            return 0;
//
//        for(int i = 0;i<numbers.length;i++)
//            if(numbers[i] >= start && numbers[i] <= end)
//                count++;
//        return count;
//
//    }

    public static void main(String[] args) {
        int[] numbers = { 1, 3, 5, 4, 2, 5, 6, 1 };
        int result = getDuplication(numbers);
        System.out.println(result);
    }


}
